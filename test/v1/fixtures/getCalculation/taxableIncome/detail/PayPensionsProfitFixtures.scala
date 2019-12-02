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

package v1.fixtures.getCalculation.taxableIncome.detail

import play.api.libs.json.{JsArray, JsValue, Json}
import v1.fixtures.getCalculation.taxableIncome.detail.BusinessProfitAndLossFixtures._
import v1.models.response.getCalculation.taxableIncome.detail.PayPensionsProfit

object PayPensionsProfitFixtures {

  val incomeReceivedPPP: BigInt                  = 1000
  val taxableIncomePPP: BigInt                   = 2000
  val totalSelfEmploymentProfit: Option[BigInt]  = Some(4000)
  val totalPropertyProfit: Option[BigInt]        = Some(1000)
  val totalFHLPropertyProfit: Option[BigInt]     = Some(500)
  val totalUKOtherPropertyProfit: Option[BigInt] = Some(500)

  val payPensionsProfitResponse: PayPensionsProfit = PayPensionsProfit(
    incomeReceivedPPP,
    taxableIncomePPP,
    totalSelfEmploymentProfit,
    totalPropertyProfit,
    totalFHLPropertyProfit,
    totalUKOtherPropertyProfit,
    Some(businessProfitAndLoss(None, ukPropertyFhlObject, ukPropertyNonFhlObject)))

  val payPensionsProfitResponseWithoutOptionalFields: PayPensionsProfit =
    PayPensionsProfit(incomeReceivedPPP, taxableIncomePPP, None, None, None, None, None)

  val payPensionsProfitDesJson: JsValue = Json.parse(s"""{
                                                        |    "calculation" : {
                                                        |       "taxCalculation" : {
                                                        |         "incomeTax" : {
                                                        |           "payPensionsProfit" : {
                                                        |             "incomeReceived" : $incomeReceivedPPP,
                                                        |             "taxableIncome" : $taxableIncomePPP
                                                        |             }
                                                        |           }
                                                        |         },
                                                        |       "incomeSummaryTotals" : {
                                                        |         "totalSelfEmploymentProfit" : ${totalSelfEmploymentProfit.get},
                                                        |         "totalPropertyProfit" : ${totalPropertyProfit.get},
                                                        |         "totalFHLPropertyProfit" : ${totalFHLPropertyProfit.get},
                                                        |         "totalUKOtherPropertyProfit" : ${totalUKOtherPropertyProfit.get}
                                                        |         }
                                                        |     }
                                                        |}""".stripMargin)

  def desJson(incomeSourceType: String*): JsValue = Json.parse(
    s"""{
       |"inputs": {
       |        "lossesBroughtForward": ${JsArray(incomeSourceType.map(x => lossBroughtForwardDesJsonForFhl(x)))}
       |    },
       |    "calculation": {
         |    "incomeSummaryTotals" : {
       |         "totalSelfEmploymentProfit" : ${totalSelfEmploymentProfit.get},
       |         "totalPropertyProfit" : ${totalPropertyProfit.get},
       |         "totalFHLPropertyProfit" : ${totalFHLPropertyProfit.get},
       |         "totalUKOtherPropertyProfit" : ${totalUKOtherPropertyProfit.get}
       |       },
       |       "businessProfitAndLoss":
       |       ${JsArray(incomeSourceType.map(x => businessProfitAndLossDesJson(x)))}
       |        ,
       |        "taxCalculation": {
       |            "incomeTax": {
       |                "totalIncomeReceivedFromAllSources": 2108191510,
       |                "totalAllowancesAndDeductions": 89321074915,
       |                "totalTaxableIncome": 68088189411,
       |                "payPensionsProfit": {
       |                    "incomeReceived": $incomeReceivedPPP,
       |                    "allowancesAllocated": 37316373820,
       |                    "taxableIncome": 26528758114,
       |                    "incomeTaxAmount": 94116371209,
       |                    "taxableIncome" : $taxableIncomePPP,
       |                    "taxBands": [
       |                        {
       |                            "name": "SSR",
       |                            "rate": 39,
       |                            "bandLimit": 97549992711,
       |                            "apportionedBandLimit": 10073393964,
       |                            "income": 30463861685,
       |                            "taxAmount": 30329561574
       |                        }
       |                    ]
       |                },
       |                "incomeTaxCharged": 16364761452,
       |                "totalReliefs": 45905746078,
       |                "incomeTaxDueAfterReliefs": -99999999999.99,
       |                "incomeTaxDueAfterGiftAid": 69148904014
       |            },
       |            "totalIncomeTaxNicsCharged": 65062942055,
       |            "totalTaxDeducted": 49524197674,
       |            "totalIncomeTaxAndNicsDue": -99999999999.99
       |        },
       |        "lossesAndClaims": {
       |            "resultOfClaimsApplied": ${JsArray(incomeSourceType.map(x => resultOfClaimsDesJson(x)))},
       |            "unclaimedLosses": [
       |                {
       |                    "incomeSourceId": "LLIS12345678913",
       |                    "incomeSourceType": "04",
       |                    "taxYearLossIncurred": 2024,
       |                    "currentLossValue": 71438847594,
       |                    "expires": 2079,
       |                    "lossType": "income"
       |                }
       |            ],
       |            "carriedForwardLosses": [
       |                {
       |                    "claimId": "CCIS12345678911",
       |                    "originatingClaimId": "OCIS12345678901",
       |                    "incomeSourceId": "AAIS12345678901",
       |                    "incomeSourceType": "04",
       |                    "claimType": "CF",
       |                    "taxYearClaimMade": 2047,
       |                    "taxYearLossIncurred": 2045,
       |                    "currentLossValue": 49177438626,
       |                    "lossType": "income"
       |                }
       |            ],
       |            "defaultCarriedForwardLosses":
       |                ${JsArray(incomeSourceType.map(x => defaultCarriedForwardLosses(x)))}
       |            ,
       |            "claimsNotApplied": [
       |                {
       |                    "claimId": "CCIS12345678912",
       |                    "incomeSourceId": "AAIS12345678901",
       |                    "incomeSourceType": "${incomeSourceType.find(x => x == "02").getOrElse("10")}",
       |                    "taxYearClaimMade": 2046,
       |                    "claimType": "CF"
       |                }
       |            ]
       |        }
       |    }
       |}""".stripMargin)

  val payPensionsProfitDesJsonWithoutOptionalFields: JsValue = Json.parse(s"""{
      |    "calculation" : {
      |       "taxCalculation" : {
      |         "incomeTax" : {
      |           "payPensionsProfit" : {
      |             "incomeReceived" : $incomeReceivedPPP,
      |             "taxableIncome" : $taxableIncomePPP
      |             }
      |           }
      |         }
      |     }
      |}""".stripMargin)

  val payPensionsProfitWrittenJson: JsValue = Json.parse(
    s"""{
       |	"incomeReceived": 1000,
       |	"taxableIncome": 2000,
       |	"totalSelfEmploymentProfit": 4000,
       |	"totalPropertyProfit": 1000,
       |	"totalFHLPropertyProfit": 500,
       |	"totalUKOtherPropertyProfit": 500,
       |	"businessProfitAndLoss": {
       |		"ukPropertyFhl": {
       |			"totalIncome": 1000,
       |			"totalExpenses": 1000,
       |			"netProfit": 1000,
       |			"netLoss": 1000,
       |			"totalAdditions": 1000,
       |			"totalDeductions": 1000,
       |			"accountingAdjustments": 1000,
       |			"taxableProfit": 1000,
       |      "taxableProfitAfterIncomeTaxLossesDeduction": 1000,
       |			"lossClaimsSummary": {
       |				"lossForCSFHL": 1000,
       |				"totalBroughtForwardIncomeTaxLosses": 1000,
       |				"broughtForwardIncomeTaxLossesUsed": 1000,
       |				"totalIncomeTaxLossesCarriedForward": 100
       |			},
       |			"lossClaimsDetail": {
       |				"lossesBroughtForward": [{
       |					"taxYearLossIncurred": "2054-55",
       |					"currentLossValue": 1000,
       |					"mtdLoss": true
       |				}],
       |				"resultOfClaimsApplied": [{
       |					"claimId": "CCIS12345678901",
       |					"taxYearClaimMade": "2038-39",
       |					"claimType": "carry-forward",
       |					"mtdLoss": true,
       |					"taxYearLossIncurred": "2050-51",
       |					"lossAmountUsed": 1000,
       |					"remainingLossValue": 1000
       |				}],
       |				"defaultCarriedForwardLosses": [{
       |					"taxYearLossIncurred": "2026-27",
       |					"currentLossValue": 1000
       |				}]
       |			}
       |		},
       |		"ukPropertyNonFhl": {
       |			"totalIncome": 1000,
       |			"totalExpenses": 1000,
       |			"netProfit": 1000,
       |			"netLoss": 1000,
       |			"totalAdditions": 1000,
       |			"totalDeductions": 1000,
       |			"accountingAdjustments": 1000,
       |			"taxableProfit": 1000,
       |			"taxableProfitAfterIncomeTaxLossesDeduction": 1000,
       |			"lossClaimsSummary": {
       |				"totalBroughtForwardIncomeTaxLosses": 1000,
       |				"broughtForwardIncomeTaxLossesUsed": 1000,
       |				"totalIncomeTaxLossesCarriedForward": 100
       |			},
       |			"lossClaimsDetail": {
       |				"lossesBroughtForward": [{
       |					"taxYearLossIncurred": "2054-55",
       |					"currentLossValue": 1000,
       |					"mtdLoss": true
       |				}],
       |				"resultOfClaimsApplied": [{
       |					"claimId": "CCIS12345678901",
       |					"originatingClaimId": "000000000000210",
       |					"taxYearClaimMade": "2038-39",
       |					"claimType": "carry-forward",
       |					"mtdLoss": true,
       |					"taxYearLossIncurred": "2050-51",
       |					"lossAmountUsed": 1000,
       |					"remainingLossValue": 1000
       |				}],
       |				"defaultCarriedForwardLosses": [{
       |					"taxYearLossIncurred": "2026-27",
       |					"currentLossValue": 1000
       |				}],
       |				"claimsNotApplied": [{
       |					"claimId": "CCIS12345678912",
       |					"taxYearClaimMade": "2045-46",
       |					"claimType": "carry-forward"
       |				}]
       |			}
       |		}
       |	}
       |}""".stripMargin)

  val payPensionsProfitWrittenJsonWithoutOptionalFields: JsValue = Json.parse(s"""{
      |    "incomeReceived" : $incomeReceivedPPP,
      |    "taxableIncome" : $taxableIncomePPP
      |}""".stripMargin)

  val payPensionsProfitInvalidJson: JsValue = Json.parse(s"""{
      |    "totalSelfEmploymentProfit" : ${totalSelfEmploymentProfit.get},
      |    "totalPropertyProfit" : ${totalPropertyProfit.get},
      |    "totalFHLPropertyProfit" : ${totalFHLPropertyProfit.get},
      |    "totalUKOtherPropertyProfit" : ${totalUKOtherPropertyProfit.get}
      |}""".stripMargin)
}
