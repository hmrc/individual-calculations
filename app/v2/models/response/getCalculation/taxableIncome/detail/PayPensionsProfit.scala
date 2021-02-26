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
import play.api.libs.json._
import utils.NestedJsonReads
import v2.models.response.getCalculation.taxableIncome.detail.payPensionsProfit.BusinessProfitAndLoss

case class PayPensionsProfit(incomeReceived: BigInt,
                             taxableIncome: BigInt,
                             totalSelfEmploymentProfit: Option[BigInt],
                             totalPropertyProfit: Option[BigInt],
                             totalFHLPropertyProfit: Option[BigInt],
                             totalUKOtherPropertyProfit: Option[BigInt],
                             totalForeignPropertyProfit: Option[BigInt],
                             totalEeaFhlProfit: Option[BigInt],
                             totalOccupationalPensionIncome: Option[BigDecimal],
                             totalStateBenefitsIncome: Option[BigDecimal],
                             totalBenefitsInKind: Option[BigDecimal],
                             totalPayeEmploymentAndLumpSumIncome: Option[BigDecimal],
                             totalEmploymentExpenses: Option[BigDecimal],
                             totalSeafarersDeduction: Option[BigDecimal],
                             totalForeignTaxOnForeignEmployment: Option[BigDecimal],
                             totalEmploymentIncome: Option[BigInt],
                             totalShareSchemesIncome: Option[BigDecimal],
                             totalOverseasPensionsStateBenefitsRoyalties: Option[BigDecimal],
                             totalAllOtherIncomeReceivedWhilstAbroad: Option[BigDecimal],
                             totalOverseasIncomeAndGains: Option[BigDecimal],
                             totalForeignBenefitsAndGifts: Option[BigDecimal],
                             businessProfitAndLoss: Option[BusinessProfitAndLoss])

object PayPensionsProfit extends NestedJsonReads {
  implicit val reads: Reads[PayPensionsProfit] = {
    val pppJsPath: JsPath = JsPath \ "calculation" \ "taxCalculation" \ "incomeTax" \ "payPensionsProfit"
    val incomeSummaryTotalsJsPath: JsPath = JsPath \ "calculation" \ "incomeSummaryTotals"
    val employmentAndPensionsIncJsPath: JsPath = JsPath \ "calculation" \ "employmentAndPensionsIncome"
    val foreignIncomeJsPath: JsPath = JsPath \ "calculation" \ "foreignIncome"
    (
      (pppJsPath \ "incomeReceived").read[BigInt] and
        (pppJsPath \ "taxableIncome").read[BigInt] and
        (incomeSummaryTotalsJsPath \ "totalSelfEmploymentProfit").readNestedNullable[BigInt] and
        (incomeSummaryTotalsJsPath \ "totalPropertyProfit").readNestedNullable[BigInt] and
        (incomeSummaryTotalsJsPath \ "totalFHLPropertyProfit").readNestedNullable[BigInt] and
        (incomeSummaryTotalsJsPath \ "totalUKOtherPropertyProfit").readNestedNullable[BigInt] and
        (incomeSummaryTotalsJsPath \ "totalForeignPropertyProfit").readNestedNullable[BigInt] and
        (incomeSummaryTotalsJsPath \ "totalEeaFhlProfit").readNestedNullable[BigInt] and
        (employmentAndPensionsIncJsPath \ "totalOccupationalPensionIncome").readNestedNullable[BigDecimal] and
        (JsPath \ "calculation" \ "stateBenefitsIncome" \ "totalStateBenefitsIncome").readNestedNullable[BigDecimal] and
        (employmentAndPensionsIncJsPath \ "totalBenefitsInKind").readNestedNullable[BigDecimal] and
        (employmentAndPensionsIncJsPath \ "totalPayeEmploymentAndLumpSumIncome").readNestedNullable[BigDecimal] and
        (JsPath \ "calculation" \ "employmentExpenses" \ "totalEmploymentExpenses").readNestedNullable[BigDecimal] and
        (JsPath \ "calculation" \ "seafarersDeduction" \ "totalSeafarersDeduction").readNestedNullable[BigDecimal] and
        (JsPath \ "calculation" \ "foreignTaxForFtcrNotClaimed" \ "foreignTaxOnForeignEmployment").readNestedNullable[BigDecimal] and
        (incomeSummaryTotalsJsPath \ "totalEmploymentIncome").readNestedNullable[BigInt] and
        (JsPath \ "calculation" \ "shareSchemesIncome" \ "totalIncome").readNestedNullable[BigDecimal] and
        (foreignIncomeJsPath \ "chargeableOverseasPensionsStateBenefitsRoyalties").readNestedNullable[BigDecimal] and
        (foreignIncomeJsPath \ "chargeableAllOtherIncomeReceivedWhilstAbroad").readNestedNullable[BigDecimal] and
        (foreignIncomeJsPath \ "overseasIncomeAndGains" \ "gainAmount").readNestedNullable[BigDecimal] and
        (foreignIncomeJsPath \ "totalForeignBenefitsAndGifts").readNestedNullable[BigDecimal] and
        JsPath.readNullable[BusinessProfitAndLoss].mapEmptyModelToNone(BusinessProfitAndLoss.empty)
    )(PayPensionsProfit.apply _)
  }

  implicit val writes: OWrites[PayPensionsProfit] = Json.writes[PayPensionsProfit]
}