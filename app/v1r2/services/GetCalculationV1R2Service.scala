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

import cats.data.EitherT
import cats.implicits._
import javax.inject.{Inject, Singleton}
import uk.gov.hmrc.http.HeaderCarrier
import utils.Logging
import v1.connectors.DesOutcome
import v1r2.connectors.TaxCalcConnector
import v1.controllers.EndpointLogContext
import v1.models.domain.CalculationType
import v1.models.errors.{ErrorWrapper, MtdError, NotFoundError, _}
import v1.models.outcomes.ResponseWrapper
import v1.models.request.getCalculation.GetCalculationRequest
import v1.models.response.getCalculation.MetadataExistence
import v1.support.DesResponseMappingSupport
import v1r2.models.response.getCalculation.GetCalculationResponseV1R2

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class GetCalculationV1R2Service @Inject()(connector: TaxCalcConnector) extends DesResponseMappingSupport with Logging {

  private val surfacedCalculationTypes = List(CalculationType.crystallisation, CalculationType.inYear)

  def getCalculation(request: GetCalculationRequest)(
    implicit hc: HeaderCarrier,
    ec: ExecutionContext,
    logContext: EndpointLogContext,
    correlationId: String): Future[Either[ErrorWrapper, ResponseWrapper[GetCalculationResponseV1R2]]] = {

    val result = for {
      desResponseWrapper <- EitherT(connector.getCalculation(request))
      desResponseWithMetadata <- EitherT(addMetadata(desResponseWrapper))
    } yield desResponseWithMetadata
    result.leftMap(mapDesErrors(desErrorMap)).flatMap(_.toErrorWhen(nonMatchingCalcFilter).toEitherT).value
  }

  private def addMetadata(desResponseWrapper: ResponseWrapper[GetCalculationResponseV1R2]): Future[DesOutcome[GetCalculationResponseV1R2]] =
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

  private def nonMatchingCalcFilter: PartialFunction[GetCalculationResponseV1R2, MtdError] = {
    new PartialFunction[GetCalculationResponseV1R2, MtdError] {
      def isDefinedAt(x: GetCalculationResponseV1R2): Boolean = !(surfacedCalculationTypes contains x.metadata.calculationType)
      def apply(v1: GetCalculationResponseV1R2): MtdError     = NotFoundError
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

