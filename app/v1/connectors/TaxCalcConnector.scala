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

package v1.connectors

import config.AppConfig
import javax.inject.{Inject, Singleton}
import uk.gov.hmrc.http.HeaderCarrier
import uk.gov.hmrc.play.bootstrap.http.HttpClient
import v1.connectors.httpparsers.StandardDesHttpParser._
import v1.models.response.selfAssessment.{CalculationIdResponse, GetCalculationResponse, ListCalculationsResponse}
import v1.models.request.EmptyJsonBody
import v1.models.requestData.DesTaxYear
import v1.models.requestData.selfAssessment.{GetCalculationRequest, ListCalculationsRequest, TriggerTaxCalculationRequest}

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class TaxCalcConnector @Inject()(val http: HttpClient,
                                 val appConfig: AppConfig) extends BaseDesConnector {


  def listCalculations(request: ListCalculationsRequest)(
    implicit hc: HeaderCarrier,
    ec: ExecutionContext): Future[DesOutcome[ListCalculationsResponse]] = {

    val nino = request.nino.nino

    val pathParameter =
      Map("taxYear" -> request.taxYear.map(_.value)).collect {
        case (key, Some(value)) => key -> value
      }

    get(
      DesUri[ListCalculationsResponse](s"income-tax/list-of-calculation-results/$nino"), pathParameter.toSeq
    )
  }

  def triggerTaxCalculation(request: TriggerTaxCalculationRequest)(
    implicit hc: HeaderCarrier,
    ec: ExecutionContext): Future[DesOutcome[CalculationIdResponse]] = {

    val nino = request.nino.nino
    val taxYear = DesTaxYear.fromMtd(request.triggerTaxCalc.taxYear)

    post(
      body = EmptyJsonBody,
      DesUri[CalculationIdResponse](s"income-tax/nino/$nino/taxYear/$taxYear/tax-calculation")
    )

  }

  def getCalculation(request: GetCalculationRequest)(
    implicit hc: HeaderCarrier,
    ec: ExecutionContext): Future[DesOutcome[GetCalculationResponse]] = {

    val nino = request.nino.nino
    val calculationId = request.calculationId

    get(
      DesUri[GetCalculationResponse](s"income-tax/03.00.00/calculation-data/$nino/calcId/$calculationId")
    )
  }

}