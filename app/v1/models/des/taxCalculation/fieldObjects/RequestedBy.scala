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
package v1.models.des.taxCalculation.fieldObjects

import play.api.libs.json.{Json, Reads, Writes}

object RequestedBy extends FieldObject {

  type RequestedBy = Value

  val customer = Value("customer")
  val hmrc = Value("hmrc")
  val agent = Value("agent")

  implicit val reads: Reads[RequestedBy] = Json.reads[RequestedBy]
  implicit val writes: Writes[RequestedBy] = Json.writes[RequestedBy]
}
