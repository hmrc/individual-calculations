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
package v1.models.des.taxCalculation.componentObjects.fieldObjects

import play.api.libs.json.{ Format, Json, Reads, Writes }

sealed trait CalculationReason

object CalculationReason extends Enumeration {
  type CalculationReason = Value
  val customerRequest, class2NICEvent, newLossEvent, updatedLossEvent, newClaimEvent, updatedClaimEvent = Value

  implicit val reads: Reads[CalculationReason]   = Reads.enumNameReads(CalculationReason)
  implicit val writes: Writes[CalculationReason] = Writes.enumNameWrites
  implicit val format: Format[CalculationReason] = Format(reads, writes)
}
