/*
 * Copyright 2021 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package v1.controllers

import play.api.libs.json.Json
import play.api.mvc.{AnyContentAsJson, Result}
import uk.gov.hmrc.http.HeaderCarrier
import v1.mocks.MockIdGenerator
import v1.mocks.requestParsers.MockTriggerTaxCalculationParser
import v1.mocks.services.{MockEnrolmentsAuthService, MockMtdIdLookupService, MockTriggerTaxCalculationService}
import v1.models.domain.Nino
import v1.models.errors._
import v1.models.outcomes.ResponseWrapper
import v1.models.request.triggerCalculation.{TriggerTaxCalculation, TriggerTaxCalculationRawData, TriggerTaxCalculationRequest}
import v1.models.response.triggerCalculation.TriggerCalculationResponse

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class TriggerTaxCalculationControllerSpec
  extends ControllerBaseSpec
    with MockEnrolmentsAuthService
    with MockMtdIdLookupService
    with MockTriggerTaxCalculationParser
    with MockTriggerTaxCalculationService
    with MockIdGenerator {

  private val nino          = "AA123456A"
  private val correlationId = "X-123"
  private val calcId        = "041f7e4d-87b9-4d4a-a296-3cfbdf92f7e2"

  trait Test {
    val hc: HeaderCarrier = HeaderCarrier()

    val controller = new TriggerTaxCalculationController(
      authService = mockEnrolmentsAuthService,
      requestDataParser = mockTriggerTaxCalculationParser,
      lookupService = mockMtdIdLookupService,
      triggerTaxCalcService = mockTriggerTaxCalculationService,
      cc = cc,
      idGenerator = mockIdGenerator
    )

    MockedMtdIdLookupService.lookup(nino).returns(Future.successful(Right("test-mtd-id")))
    MockedEnrolmentsAuthService.authoriseUser()
    MockIdGenerator.getCorrelationId.returns(correlationId)
  }

  private val requestBodyJson = Json.parse("""{
                                             |  "taxYear" : "2017-18"
                                             |}
    """.stripMargin)

  private val responseBody = Json.parse("""{
                                          |  "id" : "041f7e4d-87b9-4d4a-a296-3cfbdf92f7e2"
                                          |}
    """.stripMargin)

  private val requestBody = TriggerTaxCalculation("2017-18")

  private val rawData     = TriggerTaxCalculationRawData(nino, AnyContentAsJson(requestBodyJson))
  private val requestData = TriggerTaxCalculationRequest(Nino(nino), requestBody)

  "handleRequest" should {
    "return Accepted" when {
      "happy path" in new Test {

        MockTriggerTaxCalculationParser
          .parse(rawData)
          .returns(Right(requestData))

        MockTriggerTaxCalculationService
          .triggerTaxCalculation(requestData)
          .returns(Future.successful(Right(ResponseWrapper(correlationId, TriggerCalculationResponse(calcId)))))

        val result: Future[Result] = controller.triggerTaxCalculation(nino)(fakePostRequest(requestBodyJson))

        status(result) shouldBe ACCEPTED
        contentAsJson(result) shouldBe responseBody
        header("X-CorrelationId", result) shouldBe Some(correlationId)
      }
    }

    "return the error as per spec" when {
      "parser errors occur" must {
        def errorsFromParserTester(error: MtdError, expectedStatus: Int): Unit = {
          s"a ${error.code} error is returned from the parser" in new Test {

            MockTriggerTaxCalculationParser
              .parse(rawData)
              .returns(Left(ErrorWrapper(correlationId, error, None)))

            val result: Future[Result] = controller.triggerTaxCalculation(nino)(fakePostRequest(requestBodyJson))

            status(result) shouldBe expectedStatus
            contentAsJson(result) shouldBe Json.toJson(error)
            header("X-CorrelationId", result) shouldBe Some(correlationId)
          }
        }

        val input = Seq(
          (NinoFormatError, BAD_REQUEST),
          (TaxYearFormatError, BAD_REQUEST),
          (RuleTaxYearNotSupportedError, BAD_REQUEST),
          (RuleTaxYearRangeExceededError, BAD_REQUEST),
          (RuleIncorrectOrEmptyBodyError, BAD_REQUEST),
          (DownstreamError, INTERNAL_SERVER_ERROR)
        )

        input.foreach(args => (errorsFromParserTester _).tupled(args))
      }

      "service errors occur" must {
        def serviceErrors(mtdError: MtdError, expectedStatus: Int): Unit = {
          s"a $mtdError error is returned from the service" in new Test {

            MockTriggerTaxCalculationParser
              .parse(rawData)
              .returns(Right(requestData))

            MockTriggerTaxCalculationService
              .triggerTaxCalculation(requestData)
              .returns(Future.successful(Left(ErrorWrapper(correlationId, mtdError))))

            val result: Future[Result] = controller.triggerTaxCalculation(nino)(fakePostRequest(requestBodyJson))

            status(result) shouldBe expectedStatus
            contentAsJson(result) shouldBe Json.toJson(mtdError)
            header("X-CorrelationId", result) shouldBe Some(correlationId)
          }
        }

        val input = Seq(
          (NinoFormatError, BAD_REQUEST),
          (TaxYearFormatError, BAD_REQUEST),
          (RuleNoIncomeSubmissionsExistError, FORBIDDEN),
          (DownstreamError, INTERNAL_SERVER_ERROR)
        )

        input.foreach(args => (serviceErrors _).tupled(args))
      }
    }
  }
}
