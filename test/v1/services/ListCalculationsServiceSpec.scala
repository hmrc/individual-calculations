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

package v1.services

import support.UnitSpec
import uk.gov.hmrc.domain.Nino
import uk.gov.hmrc.http.HeaderCarrier
import v1.controllers.EndpointLogContext
import v1.mocks.connectors.MockTaxCalcConnector
import v1.models.domain.{CalculationRequestor, CalculationType}
import v1.models.errors._
import v1.models.outcomes.ResponseWrapper
import v1.models.request.DesTaxYear
import v1.models.request.listCalculations.ListCalculationsRequest
import v1.models.response.listCalculations.{CalculationListItem, ListCalculationsResponse}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class ListCalculationsServiceSpec extends UnitSpec {

  private val nino = "AA123456A"
  private val taxYear = "2017-18"
  private val correlationId = "X-123"

  private val requestData = ListCalculationsRequest(Nino(nino), DesTaxYear(taxYear))

  trait Test extends MockTaxCalcConnector {
    implicit val hc: HeaderCarrier = HeaderCarrier()
    implicit val logContext: EndpointLogContext = EndpointLogContext("c", "ep")

    val service = new ListCalculationsService(mockTaxCalcConnector)
  }

  val listCalcResponse = ListCalculationsResponse(
    Seq(
      CalculationListItem(
        id = "f2fb30e5-4ab6-4a29-b3c1-c7264259ff1c",
        calculationTimestamp = "2019-03-17T09:22:59Z",
        `type` = CalculationType.inYear,
        requestedBy = Some(CalculationRequestor.hmrc)
      ),
      CalculationListItem(
        id = "cf63c46a-1a4f-3c56-b9ea-9a82551d27bb",
        calculationTimestamp = "2019-06-17T18:45:59Z",
        `type` = CalculationType.crystallisation,
        requestedBy = None
      )
    ))
  val listCalcResponseBiss = ListCalculationsResponse(
    Seq(
      CalculationListItem(
        id = "f2fb30e5-4ab6-4a29-b3c1-c7264259ff1c",
        calculationTimestamp = "2019-03-17T09:22:59Z",
        `type` = CalculationType.inYear,
        requestedBy = Some(CalculationRequestor.hmrc)
      ),
      CalculationListItem(
        id = "cf63c46a-1a4f-3c56-b9ea-9a82551d27bb",
        calculationTimestamp = "2019-06-17T18:45:59Z",
        `type` = CalculationType.crystallisation,
        requestedBy = None
      ),
      CalculationListItem(
        id = "cf63c46a-1a4f-3c56-b9ea-9a82551d27bb",
        calculationTimestamp = "2019-06-17T18:45:59Z",
        `type` = CalculationType.biss,
        requestedBy = None
      )
    ))

  "list calculations service" when {
    "the service call is successful" must {
      "return mapped result" in new Test {
        MockTaxCalcConnector.listCalculations(requestData)
          .returns(Future.successful(Right(ResponseWrapper(correlationId, listCalcResponse))))

        await(service.listCalculations(requestData)) shouldBe Right(ResponseWrapper(correlationId, listCalcResponse))
      }

      "return mapped result filtering any non crystallisation or inYear results" in new Test {
        MockTaxCalcConnector.listCalculations(requestData)
          .returns(Future.successful(Right(ResponseWrapper(correlationId, listCalcResponseBiss))))

        await(service.listCalculations(requestData)) shouldBe Right(ResponseWrapper(correlationId, listCalcResponse))
      }
    }

    "the service call is unsuccessful" must {
      "map errors according to spec" when {

        def serviceError(desErrorCode: String, error: MtdError): Unit =
          s"a $desErrorCode error is returned from the service" in new Test {

            MockTaxCalcConnector.listCalculations(requestData)
              .returns(Future.successful(Left(ResponseWrapper(correlationId, DesErrors.single(DesErrorCode(desErrorCode))))))

            await(service.listCalculations(requestData)) shouldBe Left(ErrorWrapper(Some(correlationId), error))
          }

        val input = Seq(

          ("INVALID_TAXABLE_ENTITY_ID", NinoFormatError),
          ("INVALID_TAXYEAR", TaxYearFormatError),
          ("NOT_FOUND", NotFoundError),
          ("SERVER_ERROR", DownstreamError),
          ("SERVICE_UNAVAILABLE", DownstreamError)
        )

        input.foreach(args => (serviceError _).tupled(args))
      }
    }
  }
}
