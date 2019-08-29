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

package v1.models.response.getCalculation

import play.api.libs.json.{Json, OWrites, Reads, _}
import v1.models.des.selfAssessment.componentObjects.IncomeTax
import v1.models.response.common.Metadata

case class GetCalculationResponse(metadata: Metadata, incomeTax: Option[IncomeTax] = None)

object GetCalculationResponse {
  implicit val writes: OWrites[GetCalculationResponse] = Json.writes[GetCalculationResponse]

  implicit val reads: Reads[GetCalculationResponse] = new Reads[GetCalculationResponse] {
    override def reads(json: JsValue): JsResult[GetCalculationResponse] = {
      def failureToNone[A](jsResult: JsResult[A]) = {
        jsResult match {
          case JsSuccess(value, path) => JsSuccess(Some(value), path)
          case JsError(errors) => JsSuccess(None, errors.head._1)
        }
      }

      for {
        metadata <- json.validate[Metadata]
        incomeTax <- failureToNone(json.validate[IncomeTax])
      } yield GetCalculationResponse(metadata, incomeTax)
    }
  }
}
