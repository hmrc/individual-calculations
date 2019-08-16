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

sealed trait CalculationReason

object CalculationReason extends FieldObject {

  type Enum = Value

  val customerRequest = Value("customerRequest")
  val classToNICEvent = Value("classToNICEvent")
  val newLossEvent = Value("newLossEvent")
  val updatedLossEvent = Value("updatedLossEvent")
  val newClaimEvent = Value("newClaimEvent")
  val updatedClaimEvent = Value("updatedClaimEvent")

  override implicit val reads: Reads[Enum] = Json.reads[Enum]
  override implicit val writes: Writes[Enum] = Json.writes[Enum]
}
