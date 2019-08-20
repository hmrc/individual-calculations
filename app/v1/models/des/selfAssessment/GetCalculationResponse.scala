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

package v1.models.des.selfAssessment
import play.api.libs.json.{ JsPath, Json, Reads, Writes }
import v1.models.des.selfAssessment.componentObjects._
import play.api.libs.functional.syntax._

case class GetCalculationResponse(
    metadata: Metadata,
    messages: Messages
)

object GetCalculationResponse {

  def setErrorCount(metadata: Metadata, messages: Messages): GetCalculationResponse ={
    val errorCount = messages.errors.getOrElse(Array.empty).length
    val temp = metadata.copy(calculationErrorCount = Some(errorCount))
    new GetCalculationResponse(temp,messages)
  }

  implicit val writes: Writes[GetCalculationResponse] = Json.writes[GetCalculationResponse]
  implicit val reads: Reads[GetCalculationResponse] = ((JsPath \ "metadata").read[Metadata] and
    (JsPath \ "messages").read[Messages])(GetCalculationResponse.setErrorCount _)
}