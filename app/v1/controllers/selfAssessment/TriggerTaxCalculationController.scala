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

import cats.data.EitherT
import cats.implicits._
import javax.inject.{Inject, Singleton}
import play.api.http.MimeTypes
import play.api.libs.json.{JsValue, Json}
import play.api.mvc.{Action, AnyContentAsJson, ControllerComponents}
import utils.Logging
import v1.controllers.requestParsers.TriggerTaxCalculationParser
import v1.controllers.{AuthorisedController, BaseController, EndpointLogContext}
import v1.models.errors._
import v1.models.requestData.selfAssessment.TriggerTaxCalculationRawData
import v1.services.{EnrolmentsAuthService, MtdIdLookupService, _}

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class TriggerTaxCalculationController @Inject()(val authService: EnrolmentsAuthService,
                                                val lookupService: MtdIdLookupService,
                                                requestDataParser: TriggerTaxCalculationParser,
                                                triggerTaxCalcService: TriggerTaxCalculationService,
                                                cc: ControllerComponents)(implicit ec: ExecutionContext)
  extends AuthorisedController(cc) with BaseController with Logging {



  implicit val endpointLogContext: EndpointLogContext =
    EndpointLogContext(controllerName = "TriggerTaxCalculationController", endpointName = "triggerTaxCalculation")

  def triggerTaxCalculation(nino: String): Action[JsValue] =
    authorisedAction(nino).async(parse.json) { implicit request =>

      val rawData = TriggerTaxCalculationRawData(nino, AnyContentAsJson(request.body))
      val result =
        for {
          parsedRequest <- EitherT.fromEither[Future](requestDataParser.parseRequest(rawData))
          vendorResponse <- EitherT(triggerTaxCalcService.triggerTaxCalculation(parsedRequest))
        } yield {
          logger.info(
            s"[${endpointLogContext.controllerName}][${endpointLogContext.endpointName}] - " +
              s"Success response received with CorrelationId: ${vendorResponse.correlationId}")

          Accepted(Json.toJson(vendorResponse.responseData))
            .withApiHeaders(vendorResponse.correlationId)
            .as(MimeTypes.JSON)
        }

      result.leftMap { errorWrapper =>
        val correlationId = getCorrelationId(errorWrapper)
        errorResult(errorWrapper).withApiHeaders(correlationId)
      }.merge
    }

  private def errorResult(errorWrapper: ErrorWrapper) = {
    errorWrapper.error match {
      case BadRequestError
           | NinoFormatError
           | TaxYearFormatError
           | RuleTaxYearNotSupportedError
           | RuleTaxYearRangeExceededError
           | RuleIncorrectOrEmptyBodyError =>
        BadRequest(Json.toJson(errorWrapper))
      case RuleNoIncomeSubmissionsExistError => Forbidden(Json.toJson(errorWrapper))
      case DownstreamError => InternalServerError(Json.toJson(errorWrapper))
    }
  }
}
