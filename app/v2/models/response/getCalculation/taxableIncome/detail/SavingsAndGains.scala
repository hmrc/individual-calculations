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

case class SavingsAndGains(incomeReceived: BigInt,
                           taxableIncome: BigInt,
                           totalOfAllGains: BigInt,
                           totalUkSavingsAndSecurities: Option[BigInt],
                           ukSavings: Option[Seq[UkSaving]],
                           ukSecurities: Option[Seq[UkSecurity]],
                           totalGainsWithNoTaxPaidAndVoidedIsa: Option[BigInt],
                           totalForeignGainsOnLifePoliciesNoTaxPaid: Option[BigInt],
                           totalForeignSavingsAndGainsIncome: Option[BigInt])

object SavingsAndGains extends NestedJsonReads {
  implicit val reads: Reads[SavingsAndGains] = {
    val savingsAndGainsJsPath: JsPath = JsPath \ "calculation" \ "taxCalculation" \ "incomeTax" \ "savingsAndGains"
    val savingsAndGainsIncomeJsPath: JsPath = JsPath \ "calculation" \ "savingsAndGainsIncome"
    val ukSavingsAndGainsIncomeJsPath: JsPath = JsPath \ "calculation" \ "savingsAndGainsIncome" \ "ukSavingsAndGainsIncome"
    val chargeableEventGainsIncomeJsPath: JsPath = JsPath \ "calculation" \ "chargeableEventGainsIncome"
    (
      (savingsAndGainsJsPath \ "incomeReceived").read[BigInt] and
        (savingsAndGainsJsPath \ "taxableIncome").read[BigInt] and
        (chargeableEventGainsIncomeJsPath \ "totalOfAllGains").read[BigInt] and
        (savingsAndGainsIncomeJsPath \ "totalUkSavingsAndGains").readNestedNullable[BigInt] and
        ukSavingsAndGainsIncomeJsPath.readIncomeSourceTypeSeq[UkSaving](incomeSourceType = "09") and
        ukSavingsAndGainsIncomeJsPath.readIncomeSourceTypeSeq[UkSecurity](incomeSourceType = "18") and
        (chargeableEventGainsIncomeJsPath \ "totalGainsWithNoTaxPaidAndVoidedIsa").readNestedNullable[BigInt] and
        (chargeableEventGainsIncomeJsPath \ "totalForeignGainsOnLifePoliciesNoTaxPaid").readNestedNullable[BigInt] and
        (savingsAndGainsIncomeJsPath \ "chargeableForeignSavingsAndGains").readNestedNullable[BigInt]
    )(SavingsAndGains.apply _)
  }

  implicit val writes: OWrites[SavingsAndGains] = Json.writes[SavingsAndGains]
}