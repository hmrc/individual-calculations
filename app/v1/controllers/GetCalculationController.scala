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
import play.api.libs.json._
import play.api.mvc.{Action, AnyContent, ControllerComponents, Result}
import sangria._
import sangria.execution.{ErrorWithResolver, Executor, QueryAnalysisError}
import sangria.marshalling.playJson._
import sangria.parser.QueryParser
import utils.Logging
import v1.controllers.requestParsers.GetCalculationParser
import v1.models.errors._
import v1.models.outcomes.ResponseWrapper
import v1.models.request.getCalculation.GetCalculationRawData
import v1.models.response.getCalculation.GetCalculationResponse
import v1.services.{EnrolmentsAuthService, GetCalculationService, MtdIdLookupService}

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}

@Singleton
class GetCalculationController @Inject()(val authService: EnrolmentsAuthService,
                                         val lookupService: MtdIdLookupService,
                                         getCalculationParser: GetCalculationParser,
                                         getCalculationService: GetCalculationService,
                                         cc: ControllerComponents)(implicit ec: ExecutionContext)
  extends AuthorisedController(cc)
    with BaseController
    with Logging {

  implicit val endpointLogContext: EndpointLogContext =
    EndpointLogContext(
      controllerName = "GetCalculationMetadataController",
      endpointName = "getCalculation"
    )


  def getCalculation(nino: String, calculationId: String): Action[AnyContent] =
    authorisedAction(nino).async {
      implicit request => {
        getCalculationData(nino, calculationId)
          .map(desResponse =>
            Future.successful(Ok(Json.toJson(desResponse.responseData))
              .withApiHeaders(desResponse.correlationId)
              .as(MimeTypes.JSON))
          )
          .leftMap { errorWrapper =>
            val correlationId = getCorrelationId(errorWrapper)
            Future.successful(errorResult(errorWrapper).withApiHeaders(correlationId))
          }.merge
      }.flatten
    }

  def getCalculationGraphQL(nino: String, calculationId: String): Action[JsValue] =
    authorisedAction(nino).async(parse.json) {
      implicit request => {
        getCalculationData(nino, calculationId)
          .map { desResponse =>
            val query = (request.body \ "query").as[String]
            parseGraphQLRequest(query, desResponse)
          }
          .leftMap { errorWrapper =>
            val correlationId = getCorrelationId(errorWrapper)
            Future.successful(errorResult(errorWrapper).withApiHeaders(correlationId))
          }.merge
      }.flatten
    }

  private def getCalculationData[T](nino: String, calculationId: String)
                           (implicit request: UserRequest[T]): EitherT[Future, ErrorWrapper, ResponseWrapper[GetCalculationResponse]] = {
    val rawData = GetCalculationRawData(nino, calculationId)
    for {
      parsedRequest <- EitherT.fromEither[Future](getCalculationParser.parseRequest(rawData))
      desResponse <- EitherT(getCalculationService.getCalculation(parsedRequest))
    } yield desResponse
  }

  private def parseGraphQLRequest(query: String, response: ResponseWrapper[GetCalculationResponse]): Future[Result] = {
    QueryParser.parse(query) match {
      case Failure(error)    =>
        logger.info("GraphQL Query failed with error", error)
        Future.successful(InternalServerError(Json.toJson(DownstreamError)).withApiHeaders(response.correlationId))
      case Success(queryAst) => executeGraphQLQuery(queryAst)(response)
    }
  }

  // TODO: Move this into its own object/trait/whatever to test it thoroughly
  private def withoutNull(json: JsValue): JsValue = json match {
    case JsObject(fields) =>
      JsObject(fields.flatMap {
        case (_, JsNull) => None
        case (s, js)     => Some((s, withoutNull(js)))
      })
    case JsArray(fields)  =>
      JsArray(fields.flatMap {
        case JsNull => None
        case js     => Some(withoutNull(js))
      })
    case other            => other
  }

  private def executeGraphQLQuery(query: ast.Document)(response: ResponseWrapper[GetCalculationResponse]): Future[Result] = {
    Executor.execute(GetCalculationResponse.schema, query, response.responseData)
      .map(withoutNull)
      .map(
        Ok(_)
          .withApiHeaders(response.correlationId)
          .as(MimeTypes.JSON)
      ).recover {
      case error: QueryAnalysisError =>
        logger.info("GraphQL Query failed with error", error)
        InternalServerError(Json.toJson(DownstreamError)).withApiHeaders(response.correlationId)
      case error: ErrorWithResolver  =>
        logger.info("GraphQL Query failed with error", error)
        InternalServerError(Json.toJson(DownstreamError)).withApiHeaders(response.correlationId)
    }
  }

  private def errorResult(errorWrapper: ErrorWrapper) = {
    (errorWrapper.error: @unchecked) match {
      case BadRequestError | NinoFormatError | CalculationIdFormatError =>
        BadRequest(Json.toJson(errorWrapper))
      case NotFoundError                                                => NotFound(Json.toJson(errorWrapper))
      case DownstreamError                                              => InternalServerError(Json.toJson(errorWrapper))
    }
  }

}
