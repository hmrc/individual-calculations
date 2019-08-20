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
import v1.models.domain.selfAssessment.{ CalculationReason, CalculationRequestor, CalculationType }
import play.api.libs.functional.syntax._
import v1.models.requestData.DesTaxYear

case class Metadata(id: String,
                    taxYear: String,
                    requestedBy: CalculationRequestor,
                    requestedTimestamp: Option[String],
                    calculationReason: CalculationReason,
                    calculationTimestamp: String,
                    calculationType: CalculationType,
                    intentToCrystallise: Boolean,
                    crystallised: Boolean,
                    calculationErrorCount: Option[Int])

object Metadata {
  implicit val writes: Writes[Metadata] = Json.writes[Metadata]
  implicit val reads: Reads[Metadata] = (
    (JsPath \ "calculationId").read[String] and
      (JsPath \ "taxYear").read[String].map(desTaxYear => DesTaxYear.toMtd(desTaxYear)) and
      (JsPath \ "requestedBy").read[CalculationRequestor] and
      (JsPath \ "requestedTimestamp").readNullable[String] and
      (JsPath \ "calculationReason").read[CalculationReason] and
      (JsPath \ "calculationTimestamp").read[String] and
      (JsPath \ "calculationType").read[CalculationType] and
      (JsPath \ "intentToCrystallise").readWithDefault[Boolean](false) and
      (JsPath \ "crystallised").readWithDefault[Boolean](false) and
      (JsPath \ "calculationErrorCount").readNullable[Int]
  )(Metadata.apply _)
}
