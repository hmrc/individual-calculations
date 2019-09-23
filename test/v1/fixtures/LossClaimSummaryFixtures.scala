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

import play.api.libs.json.{JsObject, JsValue, Json}
import v1.models.response.getCalculation.taxableIncome.detail.selfEmployment.LossClaimsSummary

object LossClaimSummaryFixtures {

  val incomeSourceId: String                                 = "AAIS12345678904"
  val incomeSourceType: String                               = "01"
  val incomeSourceName: String                               = "abcdefghijklm"
  val totalIncome: BigInt                                    = 79291394
  val totalExpenses: BigInt                                  = 89005890
  val netProfit: BigInt                                      = 93480427
  val netLoss: BigInt                                        = 10017816
  val totalAdditions: BigInt                                 = 39901282
  val totalDeductions: BigInt                                = 80648172
  val accountingAdjustments: BigDecimal                      = -8769926.99
  val taxableProfit: BigInt                                  = 92149284
  val adjustedIncomeTaxLoss: BigDecimal                      = 2
  val totalBroughtForwardIncomeTaxLosses: Option[BigDecimal] = Some(1)
  val lossForCSFHL: BigDecimal                               = 2
  val broughtForwardIncomeTaxLossesUsed: Option[BigDecimal]  = Some(2)
  val taxableProfitAfterIncomeTaxLossesDeduction: BigDecimal = 2
  val totalIncomeTaxLossesCarriedForward: Option[BigDecimal] = Some(3)
  val class4Loss: BigDecimal                                 = 2
  val totalBroughtForwardClass4Losses: Option[BigDecimal]    = Some(1)
  val broughtForwardClass4LossesUsed: Option[BigDecimal]     = Some(2)
  val carrySidewaysClass4LossesUsed: Option[BigDecimal]      = Some(2)
  val totalClass4LossesCarriedForward: Option[BigDecimal]    = Some(3)

  val lossClaimsSummaryResponse = LossClaimsSummary(
    totalBroughtForwardIncomeTaxLosses,
    broughtForwardIncomeTaxLossesUsed,
    totalIncomeTaxLossesCarriedForward,
    totalBroughtForwardClass4Losses,
    broughtForwardClass4LossesUsed,
    carrySidewaysClass4LossesUsed,
    totalClass4LossesCarriedForward
  )

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
      |    "totalBroughtForwardIncomeTaxLosses": ${totalBroughtForwardIncomeTaxLosses.get},
      |    "lossForCSFHL": $lossForCSFHL,
      |    "broughtForwardIncomeTaxLossesUsed": ${broughtForwardIncomeTaxLossesUsed.get},
      |    "taxableProfitAfterIncomeTaxLossesDeduction": $taxableProfitAfterIncomeTaxLossesDeduction,
      |    "totalIncomeTaxLossesCarriedForward": ${totalIncomeTaxLossesCarriedForward.get},
      |    "class4Loss": $class4Loss,
      |    "totalBroughtForwardClass4Losses": ${totalBroughtForwardClass4Losses.get},
      |    "broughtForwardClass4LossesUsed": ${broughtForwardClass4LossesUsed.get},
      |    "carrySidewaysClass4LossesUsed": ${carrySidewaysClass4LossesUsed.get},
      |    "totalClass4LossesCarriedForward": ${totalClass4LossesCarriedForward.get}
      |}""".stripMargin)

  val lossClaimSummaryWrittenJson: JsValue = Json.parse(s"""{
      |    "totalBroughtForwardIncomeTaxLosses": ${totalBroughtForwardIncomeTaxLosses.get},
      |    "broughtForwardIncomeTaxLossesUsed": ${broughtForwardIncomeTaxLossesUsed.get},
      |    "totalIncomeTaxLossesCarriedForward": ${totalIncomeTaxLossesCarriedForward.get},
      |    "totalBroughtForwardClass4Losses": ${totalBroughtForwardClass4Losses.get},
      |    "broughtForwardClass4LossesUsed": ${broughtForwardClass4LossesUsed.get},
      |    "carrySidewaysClass4LossesUsed": ${carrySidewaysClass4LossesUsed.get},
      |    "totalClass4LossesCarriedForward": ${totalClass4LossesCarriedForward.get}
      |}""".stripMargin)

  val lossClaimSummaryInvalidJson: JsValue = Json.parse(s"""{
      |    "totalBroughtForwardIncomeTaxLosses": true
      |}""".stripMargin)

  val emptyJson: JsValue = JsObject.empty

}
