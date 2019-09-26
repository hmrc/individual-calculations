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

package v1.models.des

import play.api.libs.json._

sealed trait LossType

object LossType {
  case object INCOME extends LossType
  case object CLASS4NICS extends LossType

  implicit val reads: Reads[LossType] = implicitly[Reads[String]].collect(JsonValidationError("error.expected.lossType")){
    case "income" => INCOME
    case "class4nics" => CLASS4NICS
  }

  implicit val writes: Writes[LossType] = Writes[LossType](ts => JsString(ts.toString.toLowerCase()))
}
