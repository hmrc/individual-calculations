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

package v2.services

import cats.data.EitherT
import cats.implicits._
import javax.inject.{Inject, Singleton}
import uk.gov.hmrc.http.HeaderCarrier
import utils.Logging
import v2.connectors.{DesOutcome, TaxCalcConnector}
import v2.controllers.EndpointLogContext
import v2.models.domain.CalculationType
import v2.models.errors._
import v2.models.outcomes.ResponseWrapper
import v2.models.request.getCalculation.GetCalculationRequest
import v2.models.response.getCalculation.{GetCalculationResponse, MetadataExistence}
import v2.support.DesResponseMappingSupport

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class GetCalculationService @Inject()(connector: TaxCalcConnector) extends DesResponseMappingSupport with Logging {

  private val surfacedCalculationTypes = List(CalculationType.crystallisation, CalculationType.inYear)

  def getCalculation(request: GetCalculationRequest)(
    implicit hc: HeaderCarrier,
    ec: ExecutionContext,
    logContext: EndpointLogContext,
    correlationId: String): Future[Either[ErrorWrapper, ResponseWrapper[GetCalculationResponse]]] = {

    val result = for {
      desResponseWrapper <- EitherT(connector.getCalculation(request))
      desResponseWithMetadata <- EitherT(addMetadata(desResponseWrapper))
    } yield desResponseWithMetadata
    result.leftMap(mapDesErrors(desErrorMap)).flatMap(_.toErrorWhen(nonMatchingCalcFilter).toEitherT).value
  }

  private def addMetadata(desResponseWrapper: ResponseWrapper[GetCalculationResponse]): Future[DesOutcome[GetCalculationResponse]] =
    Future.successful { Right {
      desResponseWrapper.map { res =>
        res.copy(res.metadata.copy(metadataExistence = Some(
          MetadataExistence(
            incomeTaxAndNicsCalculated = res.incomeTaxAndNicsCalculated.isDefined,
            messages = res.messages.isDefined,
            taxableIncome = res.taxableIncome.isDefined,
            endOfYearEstimate = res.endOfYearEstimate.isDefined,
            allowancesDeductionsAndReliefs = res.allowancesDeductionsAndReliefs.isDefined
          )
        )))
      }
    }}

  private def nonMatchingCalcFilter: PartialFunction[GetCalculationResponse, MtdError] = {
    new PartialFunction[GetCalculationResponse, MtdError] {
      def isDefinedAt(x: GetCalculationResponse): Boolean = !(surfacedCalculationTypes contains x.metadata.calculationType)
      def apply(v1: GetCalculationResponse): MtdError     = NotFoundError
    }
  }

  private def desErrorMap =
    Map(
      "INVALID_TAXABLE_ENTITY_ID" -> NinoFormatError,
      "INVALID_CALCULATION_ID"    -> CalculationIdFormatError,
      "NOT_FOUND"                 -> NotFoundError,
      "SERVER_ERROR"              -> DownstreamError,
      "SERVICE_UNAVAILABLE"       -> DownstreamError
    )
}
