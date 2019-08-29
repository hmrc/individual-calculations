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

import cats.data.EitherT
import cats.implicits._
import javax.inject.{Inject, Singleton}
import uk.gov.hmrc.http.HeaderCarrier
import utils.Logging
import v1.connectors.TaxCalcConnector
import v1.controllers.EndpointLogContext
import v1.models.domain.CalculationType
import v1.models.errors._
import v1.models.outcomes.ResponseWrapper
import v1.models.request.GetCalculationRequest
import v1.models.response.GetCalculationResponse
import v1.support.DesResponseMappingSupport

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class GetCalculationService @Inject()(connector: TaxCalcConnector) extends DesResponseMappingSupport with Logging {

  private val surfacedCalculationTypes = List(CalculationType.crystallisation, CalculationType.inYear)

  def getCalculation(request: GetCalculationRequest)(
      implicit hc: HeaderCarrier,
      ec: ExecutionContext,
      logContext: EndpointLogContext): Future[Either[ErrorWrapper, ResponseWrapper[GetCalculationResponse]]] = {

    val result = for {
      desResponseWrapper <- EitherT(connector.getCalculation(request)).leftMap(mapDesErrors(desErrorMap))
    } yield desResponseWrapper
    result.flatMap(_.toErrorWhen(nonMatchingCalcFilter).toEitherT).value
  }

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
