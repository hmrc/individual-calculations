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
import v1.models.response.selfAssessment.ListCalculationsResponse
import v1.models.domain.selfAssessment.CalculationType
import v1.models.errors._
import v1.models.outcomes.ResponseWrapper
import v1.models.request.selfAssessment.ListCalculationsRequest
import v1.support.DesResponseMappingSupport

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class ListCalculationsService @Inject()(connector: TaxCalcConnector) extends DesResponseMappingSupport with Logging {

  private val surfacedCalculationTypes = List(CalculationType.crystallisation, CalculationType.inYear)

  def listCalculations(request: ListCalculationsRequest)(implicit hc: HeaderCarrier, ec: ExecutionContext, logContext: EndpointLogContext):
  Future[Either[ErrorWrapper, ResponseWrapper[ListCalculationsResponse]]] = {

    val result = for {
      desResponseWrapper <- EitherT(connector.listCalculations(request)).leftMap(mapDesErrors(desErrorMap))
    } yield desResponseWrapper.map(des => ListCalculationsResponse(des.calculations.filter(calcItem => surfacedCalculationTypes.contains(calcItem.`type`))))

    result.value
  }

  private def desErrorMap =
    Map(
      "INVALID_TAXABLE_ENTITY_ID" -> NinoFormatError,
      "INVALID_TAXYEAR" -> TaxYearFormatError,
      "NOT_FOUND" -> NotFoundError,
      "SERVER_ERROR" -> DownstreamError,
      "SERVICE_UNAVAILABLE" -> DownstreamError
    )
}
