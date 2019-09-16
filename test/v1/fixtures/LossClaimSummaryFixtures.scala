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
package v1.fixtures

import play.api.libs.json.{ JsValue, Json }
import v1.models.response.taxableIncome.selfEmployments.LossClaimsSummary

object LossClaimSummaryFixtures {

  val incomeSourceId: String                          = "AAIS12345678904"
  val incomeSourceType: String                        = "01"
  val incomeSourceName: String                        = "abcdefghijklm"
  val totalIncome: BigInt                             = 79291394
  val totalExpenses: BigInt                           = 89005890
  val netProfit: BigInt                               = 93480427
  val netLoss: BigInt                                 = 10017816
  val totalAdditions: BigInt                          = 39901282
  val totalDeductions: BigInt                         = 80648172
  val accountingAdjustments: BigDecimal               = -8769926.99
  val taxableProfit: BigInt                           = 92149284
  val adjustedIncomeTaxLoss: Int                      = 2
  val totalBroughtForwardIncomeTaxLosses: Int         = 1
  val lossForCSFHL: Int                               = 2
  val broughtForwardIncomeTaxLossesUsed: Int          = 2
  val taxableProfitAfterIncomeTaxLossesDeduction: Int = 2
  val totalIncomeTaxLossesCarriedForward: Int         = 3
  val class4Loss: Int                                 = 2
  val totalBroughtForwardClass4Losses: Int            = 4
  val broughtForwardClass4LossesUsed: Int             = 5
  val carrySidewaysClass4LossesUsed: Int              = 6
  val totalClass4LossesCarriedForward: Int            = 7

  val lossClaimSummaryDesJson: JsValue = Json.parse(s"""{
      |    "incomeSourceId": "$incomeSourceId",
      |    "incomeSourceType": "$incomeSourceType",
      |    "incomeSourceName": "$incomeSourceName",
      |    "totalIncome": $totalIncome,
      |    "totalExpenses": $totalExpenses,
      |    "netProfit": $netProfit,
      |    "netLoss": $netLoss,
      |    "totalAdditions": $totalAdditions,
      |    "totalDeductions": $totalDeductions,
      |    "accountingAdjustments": $accountingAdjustments,
      |    "taxableProfit": $taxableProfit,
      |    "adjustedIncomeTaxLoss": $adjustedIncomeTaxLoss,
      |    "totalBroughtForwardIncomeTaxLosses": $totalBroughtForwardIncomeTaxLosses,
      |    "lossForCSFHL": $lossForCSFHL,
      |    "broughtForwardIncomeTaxLossesUsed": $broughtForwardIncomeTaxLossesUsed,
      |    "taxableProfitAfterIncomeTaxLossesDeduction": $taxableProfitAfterIncomeTaxLossesDeduction,
      |    "totalIncomeTaxLossesCarriedForward": $totalIncomeTaxLossesCarriedForward,
      |    "class4Loss": $class4Loss,
      |    "totalBroughtForwardClass4Losses": $totalBroughtForwardClass4Losses,
      |    "broughtForwardClass4LossesUsed": $broughtForwardClass4LossesUsed,
      |    "carrySidewaysClass4LossesUsed": $carrySidewaysClass4LossesUsed,
      |    "totalClass4LossesCarriedForward": $totalClass4LossesCarriedForward
      |  }""".stripMargin)

  val lossClaimSummaryWrittenJson: JsValue = Json.parse(s"""
      |{
      |    "totalBroughtForwardIncomeTaxLosses": $totalBroughtForwardIncomeTaxLosses,
      |    "broughtForwardIncomeTaxLossesUsed": $broughtForwardIncomeTaxLossesUsed,
      |    "totalIncomeTaxLossesCarriedForward": $totalIncomeTaxLossesCarriedForward,
      |    "totalBroughtForwardClass4Losses": $totalBroughtForwardClass4Losses,
      |    "broughtForwardClass4LossesUsed": $broughtForwardClass4LossesUsed,
      |    "carrySidewaysClass4LossesUsed": $carrySidewaysClass4LossesUsed,
      |    "totalClass4LossesCarriedForward": $totalClass4LossesCarriedForward
      |}
      |""".stripMargin)

  val model = LossClaimsSummary(
    totalBroughtForwardIncomeTaxLosses = Some(totalBroughtForwardIncomeTaxLosses),
    broughtForwardIncomeTaxLossesUsed = Some(broughtForwardIncomeTaxLossesUsed),
    totalIncomeTaxLossesCarriedForward = Some(totalIncomeTaxLossesCarriedForward),
    totalBroughtForwardClass4Losses = Some(totalBroughtForwardClass4Losses),
    broughtForwardClass4LossesUsed = Some(broughtForwardClass4LossesUsed),
    carrySidewaysClass4LossesUsed = Some(carrySidewaysClass4LossesUsed),
    totalClass4LossesCarriedForward = Some(totalClass4LossesCarriedForward)
  )


}
