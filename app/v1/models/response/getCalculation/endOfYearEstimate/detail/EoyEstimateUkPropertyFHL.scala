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

package v1.models.response.getCalculation.endOfYearEstimate.detail

import play.api.libs.functional.syntax._
import play.api.libs.json.{ JsPath, Json, Reads, Writes }

case class EoyEstimateUkPropertyFHL(taxableIncome: BigInt, finalised: Option[Boolean])

object EoyEstimateUkPropertyFHL {
  implicit val writes: Writes[EoyEstimateUkPropertyFHL] = Json.writes[EoyEstimateUkPropertyFHL]
  implicit val reads: Reads[EoyEstimateUkPropertyFHL] = (
    (JsPath \ "taxableIncome").read[BigInt] and
      (JsPath \ "finalised").readNullable[Boolean]
  )(EoyEstimateUkPropertyFHL.apply _)
}
