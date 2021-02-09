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

package v1r2.mocks.connectors

import org.scalamock.handlers.CallHandler
import org.scalamock.scalatest.MockFactory
import uk.gov.hmrc.http.HeaderCarrier
import v1r2.connectors.{DesOutcome, TaxCalcConnector}
import v1r2.models.request.getCalculation.GetCalculationRequest
import v1r2.models.request.listCalculations.ListCalculationsRequest
import v1r2.models.request.triggerCalculation.TriggerTaxCalculationRequest
import v1r2.models.response.getCalculation.GetCalculationResponse
import v1r2.models.response.listCalculations.ListCalculationsResponse
import v1r2.models.response.triggerCalculation.TriggerCalculationResponse

import scala.concurrent.{ExecutionContext, Future}

trait MockTaxCalcConnector extends MockFactory {

  val mockTaxCalcConnector: TaxCalcConnector = mock[TaxCalcConnector]

  object MockTaxCalcConnector {

    def listCalculations(requestData: ListCalculationsRequest): CallHandler[Future[DesOutcome[ListCalculationsResponse]]] = {
      (mockTaxCalcConnector
        .listCalculations(_: ListCalculationsRequest)(_: HeaderCarrier, _: ExecutionContext, _: String))
        .expects(requestData, *, *, *)
    }

    def triggerTaxCalculation(requestData: TriggerTaxCalculationRequest): CallHandler[Future[DesOutcome[TriggerCalculationResponse]]] = {
      (mockTaxCalcConnector
        .triggerTaxCalculation(_: TriggerTaxCalculationRequest)(_: HeaderCarrier, _: ExecutionContext, _: String))
        .expects(requestData, *, *, *)
    }

    def getCalculation(requestData: GetCalculationRequest): CallHandler[Future[DesOutcome[GetCalculationResponse]]] = {
      (mockTaxCalcConnector
        .getCalculation(_: GetCalculationRequest)(_: HeaderCarrier, _:ExecutionContext, _: String))
        .expects(requestData, *, *, *)
    }
  }
}
