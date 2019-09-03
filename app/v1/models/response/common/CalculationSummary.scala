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

package v1.models.response.common

import play.api.libs.functional.syntax._
import play.api.libs.json._
import utils.NestedJsonReads

case class CalculationSummary(incomeTax: IncomeTaxSummary,
                              nics: Option[NicSummary],
                              totalIncomeTaxNicsCharged: Option[BigDecimal],
                              totalTaxDeducted: Option[BigDecimal],
                              totalIncomeTaxAndNicsDue: BigDecimal,
                              taxRegime: Option[String])

object CalculationSummary extends NestedJsonReads {
  implicit val writes: OWrites[CalculationSummary] = Json.writes[CalculationSummary]

  implicit val reads: Reads[CalculationSummary] = (
    (JsPath \ "calculation" \ "taxCalculation" \ "incomeTax").read[IncomeTaxSummary] and
      (JsPath \ "calculation" \ "taxCalculation" \ "nics").readNestedNullable[NicSummary] .map {
        _.flatMap {
          case NicSummary(None, None, None) => None
          case other => Some(other)
        }
      } and
      (JsPath \ "calculation" \ "taxCalculation" \ "totalIncomeTaxNicsCharged").readNestedNullable[BigDecimal] and
      (JsPath \ "calculation" \ "taxCalculation" \ "totalTaxDeducted").readNestedNullable[BigDecimal] and
      (JsPath \ "calculation" \ "taxCalculation" \ "totalIncomeTaxAndNicsDue").read[BigDecimal] and
      (JsPath \ "inputs" \ "personalInformation" \ "taxRegime").readNestedNullable[String]
    ) (CalculationSummary.apply _)
}
