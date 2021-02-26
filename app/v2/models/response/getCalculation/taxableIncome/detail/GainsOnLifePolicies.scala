/*
 * Copyright 2021 HM Revenue & Customs
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

package v2.models.response.getCalculation.taxableIncome.detail

import play.api.libs.functional.syntax._
import play.api.libs.json.{JsPath, Json, OWrites, Reads}
import utils.NestedJsonReads

case class GainsOnLifePolicies(incomeReceived: BigInt,
                               taxableIncome: BigInt,
                               totalUkGainsWithTaxPaid: Option[BigInt],
                               totalForeignGainsOnLifePoliciesWithTaxPaid: Option[BigInt])

object GainsOnLifePolicies extends NestedJsonReads {
  implicit val reads: Reads[GainsOnLifePolicies] = {
    val gainsOnLifePoliciesJsPath: JsPath = JsPath \ "calculation" \ "taxCalculation" \ "incomeTax" \ "gainsOnLifePolicies"
    val chargeableEventGainsIncomeJsPath: JsPath = JsPath \ "calculation" \ "chargeableEventGainsIncome"
    (
      (gainsOnLifePoliciesJsPath \ "incomeReceived").read[BigInt] and
        (gainsOnLifePoliciesJsPath \ "taxableIncome").read[BigInt] and
        (chargeableEventGainsIncomeJsPath \ "totalGainsWithTaxPaid").readNestedNullable[BigInt] and
        (chargeableEventGainsIncomeJsPath \ "totalForeignGainsOnLifePoliciesTaxPaid").readNestedNullable[BigInt]
    )(GainsOnLifePolicies.apply _)
  }

  implicit val writes: OWrites[GainsOnLifePolicies] = Json.writes[GainsOnLifePolicies]
}