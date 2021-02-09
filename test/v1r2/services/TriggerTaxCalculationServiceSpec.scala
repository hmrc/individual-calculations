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

package v1r2.services

import support.UnitSpec
import uk.gov.hmrc.domain.Nino
import uk.gov.hmrc.http.HeaderCarrier
import v1r2.controllers.EndpointLogContext
import v1r2.mocks.connectors.MockTaxCalcConnector
import v1r2.models.errors._
import v1r2.models.outcomes.ResponseWrapper
import v1r2.models.request.triggerCalculation.{TriggerTaxCalculation, TriggerTaxCalculationRequest}
import v1r2.models.response.triggerCalculation.TriggerCalculationResponse

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class TriggerTaxCalculationServiceSpec extends UnitSpec {

  private val nino = "AA123456A"
  private val taxYear = "2017-18"
  private val calcId  = "041f7e4d-87b9-4d4a-a296-3cfbdf92f7e2"
  implicit val correlationId = "X-123"

  private val requestData = TriggerTaxCalculationRequest(Nino(nino), TriggerTaxCalculation(taxYear))

  trait Test extends MockTaxCalcConnector {
    implicit val hc: HeaderCarrier = HeaderCarrier()
    implicit val logContext: EndpointLogContext = EndpointLogContext("c", "ep")

    val service = new TriggerTaxCalculationService(mockTaxCalcConnector)
  }

  val calculationIdResponse = TriggerCalculationResponse(calcId)

  "list calculations service" when {
    "the service call is successful" must {
      "return mapped result" in new Test {
        MockTaxCalcConnector.triggerTaxCalculation(requestData)
          .returns(Future.successful(Right(ResponseWrapper(correlationId, calculationIdResponse))))

        await(service.triggerTaxCalculation(requestData)) shouldBe Right(ResponseWrapper(correlationId, calculationIdResponse))
      }
    }

    "the service call is unsuccessful" must {
      "map errors according to spec" when {

        def serviceError(desErrorCode: String, error: MtdError): Unit =
          s"a $desErrorCode error is returned from the service" in new Test {

            MockTaxCalcConnector.triggerTaxCalculation(requestData)
              .returns(Future.successful(Left(ResponseWrapper(correlationId, DesErrors.single(DesErrorCode(desErrorCode))))))

            await(service.triggerTaxCalculation(requestData)) shouldBe Left(ErrorWrapper(correlationId, error))
          }

        val input = Seq(
          "INVALID_NINO" -> NinoFormatError,
          "INVALID_TAX_YEAR" -> TaxYearFormatError,
          "NO_SUBMISSION_EXIST" -> RuleNoIncomeSubmissionsExistError,
          "INVALID_REQUEST" -> DownstreamError,
          "CONFLICT" -> DownstreamError,
          "INVALID_TAX_CRYSTALLISE" -> DownstreamError,
          "SERVER_ERROR" -> DownstreamError,
          "SERVICE_UNAVAILABLE" -> DownstreamError
        )

        input.foreach(args => (serviceError _).tupled(args))
      }
    }
  }
}
