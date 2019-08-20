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

package v1.models.des.selfAssessment.componentObjects

import play.api.libs.json.{ JsPath, Json, Reads, Writes }
import play.api.libs.functional.syntax._

case class Messages(info: Option[Array[Info]], warnings: Option[Array[Warnings]], errors: Option[Array[Errors]])

object Messages {
  implicit val writes: Writes[Messages] = Json.writes[Messages]
  implicit val reads: Reads[Messages] = (
    (JsPath \ "info").readNullable[Array[Info]] and
      (JsPath \ "warnings").readNullable[Array[Warnings]] and
      (JsPath \ "errors").readNullable[Array[Errors]]
  )(Messages.apply _)
}

case class Info(id: String, text: String)

object Info {
  implicit val reads: Reads[Info]   = Json.reads[Info]
  implicit val writes: Writes[Info] = Json.writes[Info]
}

case class Warnings(id: String, text: String)

object Warnings {
  implicit val reads: Reads[Warnings]   = Json.reads[Warnings]
  implicit val writes: Writes[Warnings] = Json.writes[Warnings]
}

case class Errors(id: String, text: String)

object Errors {
  implicit val reads: Reads[Errors]   = Json.reads[Errors]
  implicit val writes: Writes[Errors] = Json.writes[Errors]
}
