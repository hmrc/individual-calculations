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
                             tipsIncome: Option[BigDecimal],
                             businessProfitAndLoss: Option[BusinessProfitAndLoss])

object PayPensionsProfit extends NestedJsonReads {

  private case class PayPensionsProfitPart1(incomeReceived: BigInt,
                                            taxableIncome: BigInt,
                                            totalSelfEmploymentProfit: Option[BigInt],
                                            totalPropertyProfit: Option[BigInt],
                                            totalFHLPropertyProfit: Option[BigInt],
                                            totalUKOtherPropertyProfit: Option[BigInt],
                                            totalForeignPropertyProfit: Option[BigInt],
                                            totalEeaFhlProfit: Option[BigInt],
                                            totalOccupationalPensionIncome: Option[BigDecimal],
                                            totalStateBenefitsIncome: Option[BigDecimal],
                                            totalBenefitsInKind: Option[BigDecimal])

  private case class PayPensionsProfitPart2(totalPayeEmploymentAndLumpSumIncome: Option[BigDecimal],
                                            totalEmploymentExpenses: Option[BigDecimal],
                                            totalSeafarersDeduction: Option[BigDecimal],
                                            totalForeignTaxOnForeignEmployment: Option[BigDecimal],
                                            totalEmploymentIncome: Option[BigInt],
                                            totalShareSchemesIncome: Option[BigDecimal],
                                            totalOverseasPensionsStateBenefitsRoyalties: Option[BigDecimal],
                                            totalAllOtherIncomeReceivedWhilstAbroad: Option[BigDecimal],
                                            totalOverseasIncomeAndGains: Option[BigDecimal],
                                            totalForeignBenefitsAndGifts: Option[BigDecimal],
                                            tipsIncome: Option[BigDecimal],
                                            businessProfitAndLoss: Option[BusinessProfitAndLoss])


  private val readsPt1: Reads[PayPensionsProfitPart1] = {
    val pppJsPath: JsPath = JsPath \ "calculation" \ "taxCalculation" \ "incomeTax" \ "payPensionsProfit"
    val incomeSummaryTotalsJsPath: JsPath = JsPath \ "calculation" \ "incomeSummaryTotals"
    val employmentAndPensionsIncJsPath: JsPath = JsPath \ "calculation" \ "employmentAndPensionsIncome"
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
        (employmentAndPensionsIncJsPath \ "totalBenefitsInKind").readNestedNullable[BigDecimal]
      ) (PayPensionsProfitPart1.apply _)
  }

  private val readsPt2: Reads[PayPensionsProfitPart2] = {
    val incomeSummaryTotalsJsPath: JsPath = JsPath \ "calculation" \ "incomeSummaryTotals"
    val employmentAndPensionsIncJsPath: JsPath = JsPath \ "calculation" \ "employmentAndPensionsIncome"
    val foreignIncomeJsPath: JsPath = JsPath \ "calculation" \ "foreignIncome"
    (
      (employmentAndPensionsIncJsPath \ "totalPayeEmploymentAndLumpSumIncome").readNestedNullable[BigDecimal] and
        (JsPath \ "calculation" \ "employmentExpenses" \ "totalEmploymentExpenses").readNestedNullable[BigDecimal] and
        (JsPath \ "calculation" \ "seafarersDeductions" \ "totalSeafarersDeduction").readNestedNullable[BigDecimal] and
        (JsPath \ "calculation" \ "foreignTaxForFtcrNotClaimed" \ "foreignTaxOnForeignEmployment").readNestedNullable[BigDecimal] and
        (incomeSummaryTotalsJsPath \ "totalEmploymentIncome").readNestedNullable[BigInt] and
        (JsPath \ "calculation" \ "shareSchemesIncome" \ "totalIncome").readNestedNullable[BigDecimal] and
        (foreignIncomeJsPath \ "chargeableOverseasPensionsStateBenefitsRoyalties").readNestedNullable[BigDecimal] and
        (foreignIncomeJsPath \ "chargeableAllOtherIncomeReceivedWhilstAbroad").readNestedNullable[BigDecimal] and
        (foreignIncomeJsPath \ "overseasIncomeAndGains" \ "gainAmount").readNestedNullable[BigDecimal] and
        (foreignIncomeJsPath \ "totalForeignBenefitsAndGifts").readNestedNullable[BigDecimal] and
        (employmentAndPensionsIncJsPath \ "tipsIncome").readNestedNullable[BigDecimal] and
        JsPath.readNullable[BusinessProfitAndLoss].mapEmptyModelToNone(BusinessProfitAndLoss.empty)
      )(PayPensionsProfitPart2.apply _)
  }

  private val writesPt1: OWrites[PayPensionsProfitPart1] = Json.writes[PayPensionsProfitPart1]
  private val writesPt2: OWrites[PayPensionsProfitPart2] = Json.writes[PayPensionsProfitPart2]


  private def buildPayPensionsObjects(part1: PayPensionsProfitPart1, part2: PayPensionsProfitPart2): PayPensionsProfit = {
    import part1._
    import part2._

    PayPensionsProfit(
      incomeReceived = incomeReceived,
      taxableIncome = taxableIncome,
      totalSelfEmploymentProfit = totalSelfEmploymentProfit,
      totalPropertyProfit = totalPropertyProfit,
      totalFHLPropertyProfit = totalFHLPropertyProfit,
      totalUKOtherPropertyProfit = totalUKOtherPropertyProfit,
      totalForeignPropertyProfit = totalForeignPropertyProfit,
      totalEeaFhlProfit = totalEeaFhlProfit,
      totalOccupationalPensionIncome = totalOccupationalPensionIncome,
      totalStateBenefitsIncome = totalStateBenefitsIncome,
      totalBenefitsInKind = totalBenefitsInKind,
      totalPayeEmploymentAndLumpSumIncome = totalPayeEmploymentAndLumpSumIncome,
      totalEmploymentExpenses = totalEmploymentExpenses,
      totalSeafarersDeduction = totalSeafarersDeduction,
      totalForeignTaxOnForeignEmployment = totalForeignTaxOnForeignEmployment,
      totalEmploymentIncome = totalEmploymentIncome,
      totalShareSchemesIncome = totalShareSchemesIncome,
      totalOverseasPensionsStateBenefitsRoyalties = totalOverseasPensionsStateBenefitsRoyalties,
      totalAllOtherIncomeReceivedWhilstAbroad = totalAllOtherIncomeReceivedWhilstAbroad,
      totalOverseasIncomeAndGains = totalOverseasIncomeAndGains,
      totalForeignBenefitsAndGifts = totalForeignBenefitsAndGifts,
      tipsIncome = tipsIncome,
      businessProfitAndLoss = businessProfitAndLoss
    )
  }

  private def splitPayPensionsObject(o: PayPensionsProfit): (PayPensionsProfitPart1, PayPensionsProfitPart2) = {
    import o._

    (
      PayPensionsProfitPart1(
        incomeReceived = incomeReceived,
        taxableIncome = taxableIncome,
        totalSelfEmploymentProfit = totalSelfEmploymentProfit,
        totalPropertyProfit = totalPropertyProfit,
        totalFHLPropertyProfit = totalFHLPropertyProfit,
        totalUKOtherPropertyProfit = totalUKOtherPropertyProfit,
        totalForeignPropertyProfit = totalForeignPropertyProfit,
        totalEeaFhlProfit = totalEeaFhlProfit,
        totalOccupationalPensionIncome = totalOccupationalPensionIncome,
        totalStateBenefitsIncome = totalStateBenefitsIncome,
        totalBenefitsInKind = totalBenefitsInKind
      ),
      PayPensionsProfitPart2(
        totalPayeEmploymentAndLumpSumIncome = totalPayeEmploymentAndLumpSumIncome,
        totalEmploymentExpenses = totalEmploymentExpenses,
        totalSeafarersDeduction = totalSeafarersDeduction,
        totalForeignTaxOnForeignEmployment = totalForeignTaxOnForeignEmployment,
        totalEmploymentIncome = totalEmploymentIncome,
        totalShareSchemesIncome = totalShareSchemesIncome,
        totalOverseasPensionsStateBenefitsRoyalties = totalOverseasPensionsStateBenefitsRoyalties,
        totalAllOtherIncomeReceivedWhilstAbroad = totalAllOtherIncomeReceivedWhilstAbroad,
        totalOverseasIncomeAndGains = totalOverseasIncomeAndGains,
        totalForeignBenefitsAndGifts = totalForeignBenefitsAndGifts,
        tipsIncome = tipsIncome,
        businessProfitAndLoss = businessProfitAndLoss
      )
    )
  }

  implicit val writes: OWrites[PayPensionsProfit] = (o: PayPensionsProfit) => {
    val splitData = PayPensionsProfit.splitPayPensionsObject(o)
    Json.toJsObject(splitData._1)(writesPt1) ++ Json.toJsObject(splitData._2)(writesPt2)
  }

  implicit val reads: Reads[PayPensionsProfit] = (
    JsPath.read[PayPensionsProfitPart1](readsPt1) and
      JsPath.read[PayPensionsProfitPart2](readsPt2)
    )(PayPensionsProfit.buildPayPensionsObjects _)
}