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
package v1.models.des.taxCalculation.componentObjects

import play.api.libs.json.{Json, Reads}
import v1.models.des.taxCalculation.fieldObjects.{CalculationReason, CalculationType, RequestedBy}

case class Metadata(
                     calculationId: String,
                     taxYear: String,
                     requestedBy: RequestedBy,
                     requestedTimestamp: Option[String],
                     calculationReason: CalculationReason,
                     calculationTimestamp: String,
                     calculationType: CalculationType,
                     intentToCrystallise: Option[Boolean],
                     crystallised: Option[Boolean],
                     crystallisationTimestamp: Option[String],
                     periodFrom: String,
                     periodTo: String
                   ) extends ComponentObject {
  override val description: String = "An object representing the metadata for the calculation."
  override val required: Boolean = true
}

object Metadata {
  implicit val reads: Reads[Metadata] = Json.reads[Metadata]
  implicit val writes: Reads[Metadata] = Json.reads[Metadata]
}
