/*
 * Copyright 2020 HM Revenue & Customs
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

package v1.controllers

import cats.data.EitherT
import cats.implicits._
import javax.inject.{Inject, Singleton}
import play.api.http.MimeTypes
import play.api.libs.json.{JsValue, Json}
import play.api.mvc.{Action, AnyContentAsJson, ControllerComponents}
import utils.{IdGenerator, Logging}
import v1.controllers.requestParsers.TriggerTaxCalculationParser
import v1.models.errors._
import v1.models.request.triggerCalculation.TriggerTaxCalculationRawData
import v1.services.{EnrolmentsAuthService, MtdIdLookupService, _}

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class TriggerTaxCalculationController @Inject()(val authService: EnrolmentsAuthService,
                                                val lookupService: MtdIdLookupService,
                                                requestDataParser: TriggerTaxCalculationParser,
                                                triggerTaxCalcService: TriggerTaxCalculationService,
                                                cc: ControllerComponents,
                                                idGenerator: IdGenerator)(implicit ec: ExecutionContext)
  extends AuthorisedController(cc) with BaseController with Logging {



  implicit val endpointLogContext: EndpointLogContext =
    EndpointLogContext(controllerName = "TriggerTaxCalculationController", endpointName = "triggerTaxCalculation")

  def triggerTaxCalculation(nino: String): Action[JsValue] =
    authorisedAction(nino).async(parse.json) { implicit request =>
      implicit val correlationId: String = idGenerator.getCorrelationId
      logger.info(message = s"[${endpointLogContext.controllerName}][${endpointLogContext.endpointName}] " +
        s"with correlationId : $correlationId")
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
        val resCorrelationId = errorWrapper.correlationId
        val result = errorResult(errorWrapper).withApiHeaders(resCorrelationId)
        logger.info(
          s"[${endpointLogContext.controllerName}][${endpointLogContext.endpointName}] - " +
            s"Error response received with CorrelationId: $resCorrelationId")
        result
      }.merge
    }

  private def errorResult(errorWrapper: ErrorWrapper) = {
    (errorWrapper.error: @unchecked) match {
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
