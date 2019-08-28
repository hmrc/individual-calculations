/*
 * Copyright 2019 HM Revenue & Customs
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

package v1.controllers.selfAssessment

import play.api.libs.json.{JsValue, Json}
import play.api.mvc.Result
import uk.gov.hmrc.domain.Nino
import uk.gov.hmrc.http.HeaderCarrier
import v1.controllers.ControllerBaseSpec
import v1.mocks.requestParsers.MockGetCalculationParser
import v1.mocks.services.{MockEnrolmentsAuthService, MockGetCalculationService, MockMtdIdLookupService}
import v1.models.response.selfAssessment.GetCalculationResponse
import v1.models.response.selfAssessment.componentObjects.Metadata
import v1.models.domain.selfAssessment.{CalculationReason, CalculationRequestor, CalculationType}
import v1.models.errors._
import v1.models.outcomes.ResponseWrapper
import v1.models.requestData.selfAssessment.{GetCalculationRawData, GetCalculationRequest}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class GetCalculationMetadataControllerSpec
    extends ControllerBaseSpec
    with MockEnrolmentsAuthService
    with MockMtdIdLookupService
    with MockGetCalculationParser
    with MockGetCalculationService {

  trait Test {
    val hc = HeaderCarrier()

    val controller = new GetCalculationMetadataController(
      authService = mockEnrolmentsAuthService,
      lookupService = mockMtdIdLookupService,
      getCalculationParser = mockGetCalculationParser,
      getCalculationService = mockGetCalculationService,
      cc = cc
    )

    MockedMtdIdLookupService.lookup(nino).returns(Future.successful(Right("test-mtd-id")))
    MockedEnrolmentsAuthService.authoriseUser()
  }

  private val nino          = "AA123456A"
  private val calcId        = "f2fb30e5-4ab6-4a29-b3c1-c7264259ff1c"
  private val correlationId = "X-123"

  val desResponse: JsValue = Json.parse("""{
      |    "metadata":{
      |       "id": "f2fb30e5-4ab6-4a29-b3c1-c7264259ff1c",
      |       "taxYear": "2018-19",
      |       "requestedBy": "customer",
      |       "requestedTimestamp": "2019-11-15T09:25:15.094Z",
      |       "calculationReason": "customerRequest",
      |       "calculationTimestamp": "2019-11-15T09:35:15.094Z",
      |       "calculationType": "inYear",
      |       "intentToCrystallise": false,
      |       "crystallised": false,
      |       "calculationErrorCount": 1
      |       }
      |}""".stripMargin)

  val metadata = Metadata(
    id = "f2fb30e5-4ab6-4a29-b3c1-c7264259ff1c",
    taxYear = "2018-19",
    requestedBy = CalculationRequestor.customer,
    requestedTimestamp = Some("2019-11-15T09:25:15.094Z"),
    calculationReason = CalculationReason.customerRequest,
    calculationTimestamp = "2019-11-15T09:35:15.094Z",
    calculationType = CalculationType.inYear,
    intentToCrystallise = false,
    crystallised = false,
    calculationErrorCount = Some(1)
  )

  val getCalculationResponse = GetCalculationResponse(metadata)

  val rawData                = GetCalculationRawData(nino, calcId)
  val requestData            = GetCalculationRequest(Nino(nino), calcId)

  "handleRequest" should {
    "return OK with metadata information" when {
      "happy path" in new Test {

        MockGetCalculationParser
          .parse(rawData)
          .returns(Right(requestData))

        MockGetCalculationService
          .getCalculation(requestData)
          .returns(Future.successful(Right(ResponseWrapper(correlationId, getCalculationResponse))))

        val result: Future[Result] = controller.getCalculationMetadata(nino, calcId)(fakeGetRequest)

        status(result) shouldBe OK
        contentAsJson(result) shouldBe desResponse
        header("X-CorrelationId", result) shouldBe Some(correlationId)
      }
    }

    "return the error as per spec" when {
      "parser errors occur" must {
        def errorsFromParserTester(error: MtdError, expectedStatus: Int): Unit = {
          s"a ${error.code} error is returned from the parser" in new Test {

            MockGetCalculationParser
              .parse(rawData)
              .returns(Left(ErrorWrapper(Some(correlationId), error, None)))

            val result: Future[Result] = controller.getCalculationMetadata(nino, calcId)(fakeGetRequest)

            status(result) shouldBe expectedStatus
            contentAsJson(result) shouldBe Json.toJson(error)
            header("X-CorrelationId", result) shouldBe Some(correlationId)
          }
        }

        val input = Seq(
          (BadRequestError, BAD_REQUEST),
          (NinoFormatError, BAD_REQUEST),
          (CalculationIdFormatError, BAD_REQUEST),
          (NotFoundError, NOT_FOUND),
          (DownstreamError, INTERNAL_SERVER_ERROR)
        )

        input.foreach(args => (errorsFromParserTester _).tupled(args))
      }

      "service errors occur" must {
        def serviceErrors(mtdError: MtdError, expectedStatus: Int): Unit = {
          s"a $mtdError error is returned from the service" in new Test {

            MockGetCalculationParser
              .parse(rawData)
              .returns(Right(requestData))

            MockGetCalculationService
              .getCalculation(requestData)
              .returns(Future.successful(Left(ErrorWrapper(Some(correlationId), mtdError))))

            val result: Future[Result] = controller.getCalculationMetadata(nino, calcId)(fakeGetRequest)

            status(result) shouldBe expectedStatus
            contentAsJson(result) shouldBe Json.toJson(mtdError)
            header("X-CorrelationId", result) shouldBe Some(correlationId)
          }
        }

        val input = Seq(
          (NinoFormatError, BAD_REQUEST),
          (CalculationIdFormatError, BAD_REQUEST),
          (NotFoundError, NOT_FOUND),
          (DownstreamError, INTERNAL_SERVER_ERROR)
        )

        input.foreach(args => (serviceErrors _).tupled(args))
      }
    }

  }
}
