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

package v1.models.response.getCalculation.allowancesAndDeductions.summary

import play.api.libs.functional.syntax._
import play.api.libs.json.{JsPath, Json, Reads, Writes}
import utils.NestedJsonReads

case class CalculationSummary(totalAllowancesAndDeductions: Option[BigInt], totalReliefs: Option[BigInt])

object CalculationSummary extends NestedJsonReads{
  implicit val writes: Writes[CalculationSummary] = Json.writes[CalculationSummary]
  implicit val reads: Reads[CalculationSummary] = (
    (JsPath \ "calculation" \ "taxCalculation" \ "incomeTax" \ "totalAllowancesAndDeductions").readNestedNullable[BigInt] and
      (JsPath \ "calculation" \ "taxCalculation" \ "incomeTax" \ "totalReliefs").readNestedNullable[BigInt]
    )(CalculationSummary.apply _)
}
