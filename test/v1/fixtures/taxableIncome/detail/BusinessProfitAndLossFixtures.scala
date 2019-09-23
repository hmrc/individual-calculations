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

package v1.fixtures.taxableIncome.detail

import play.api.libs.json.{JsArray, JsValue, Json}
import v1.models.domain.TypeOfClaim
import v1.models.response.getCalculation.taxableIncome.detail.BusinessProfitAndLoss
import v1.models.response.getCalculation.taxableIncome.detail.ukPropertyFhl.UkPropertyFhl
import v1.models.response.getCalculation.taxableIncome.detail.ukPropertyFhl.detail.{DefaultCarriedForwardLoss => FhlDefaultCarriedForwardLoss, LossBroughtForward => FhlLossBroughtForward, LossClaimsDetail => FhlLossClaimsDetail, ResultOfClaimApplied => FhlResultOfClaimApplied}
import v1.models.response.getCalculation.taxableIncome.detail.ukPropertyFhl.summary.{LossClaimsSummary => FhlLossClaimsSummary}
import v1.models.response.getCalculation.taxableIncome.detail.ukPropertyNonFhl.UkPropertyNonFhl
import v1.models.response.getCalculation.taxableIncome.detail.ukPropertyNonFhl.detail.{ClaimNotApplied, DefaultCarriedForwardLoss => NonFhlDefaultCarriedForwardLoss, LossBroughtForward => NonFhlLossBroughtForward, LossClaimsDetail => NonFhlLossClaimsDetail, ResultOfClaimApplied => NonFhlResultOfClaimApplied}
import v1.models.response.getCalculation.taxableIncome.detail.ukPropertyNonFhl.summary.{LossClaimsSummary => NonFhlLossClaimsSummary}

object BusinessProfitAndLossFixtures {

  //@TODO Add self-employment sequence objects once it is ready
  def businessProfitAndLoss(ukPropertyFhl: Option[UkPropertyFhl],
                            ukPropertyNonFhl: Option[UkPropertyNonFhl]): BusinessProfitAndLoss =
    BusinessProfitAndLoss(None,ukPropertyFhl, ukPropertyNonFhl)

  val ukPropertyFhlObject = Some(UkPropertyFhl(Some(1000.00),Some(1000.00),Some(1000.00),Some(1000.00),
    Some(1000.00),Some(1000.00),Some(1000.00),None,Some(1000),None,
    Some(FhlLossClaimsSummary(Some(1000),Some(1000),Some(1000),Some(100))),
    Some(FhlLossClaimsDetail(Some(List(FhlLossBroughtForward("2054-55",1000, mtdLoss = true))),
      Some(List(FhlResultOfClaimApplied(Some("CCIS12345678901"),"2038-39",TypeOfClaim.`carry-forward`,
        mtdLoss = true,"2050-51",1000,1000))),Some(List(FhlDefaultCarriedForwardLoss("2026-27",1000)))))))

  val ukPropertyFhlWithoutLossClaimsDetailObject = Some(UkPropertyFhl(Some(1000.00),Some(1000.00),Some(1000.00),Some(1000.00),
    Some(1000.00),Some(1000.00),Some(1000.00),None,Some(1000),None,
    Some(FhlLossClaimsSummary(Some(1000),Some(1000),Some(1000),Some(100))),None))

  val ukPropertyNonFhlWithoutLossClaimsDetailObject = Some(UkPropertyNonFhl(Some(1000.00),Some(1000.00),Some(1000.00),Some(1000.00),Some(1000.00),
    Some(1000.00),Some(1000.00),None,Some(1000),Some(1000),Some(NonFhlLossClaimsSummary(Some(1000),Some(1000),Some(100))),None))

  val ukPropertyNonFhlObject = Some(UkPropertyNonFhl(Some(1000.00),Some(1000.00),Some(1000.00),Some(1000.00),Some(1000.00),
    Some(1000.00),Some(1000.00),None,Some(1000),Some(1000),Some(NonFhlLossClaimsSummary(Some(1000),Some(1000),Some(100))),
    Some(NonFhlLossClaimsDetail(Some(List(NonFhlLossBroughtForward("2054-55",1000, mtdLoss = true))),
    Some(List(NonFhlResultOfClaimApplied(Some("CCIS12345678901"),Some("000000000000210"),"2038-39",TypeOfClaim.`carry-forward`,
      mtdLoss = true,"2050-51",1000,1000))),Some(List(NonFhlDefaultCarriedForwardLoss("2026-27",1000))),
      Some(List(ClaimNotApplied("CCIS12345678912","2045-46",TypeOfClaim.`carry-forward`)))))))

  def lossBroughtForwardDesJsonForFhl(incomeSourceType: String):JsValue = Json.parse(
    s"""
      |            {
      |                "lossId": "0yriP9QrW2jTa6n",
      |                "incomeSourceId": "AAIS12345678904",
      |                "incomeSourceType": "$incomeSourceType",
      |                "submissionTimestamp": "2019-07-13T07:51:43Z",
      |                "lossType": "income",
      |                "taxYearLossIncurred": 2055,
      |                "currentLossValue": 1000.00,
      |                "mtdLoss": true
      |            }
    """.stripMargin)

  def resultOfClaimsDesJson(incomeSourceType: String) : JsValue = Json.parse(
    s"""
      | {
      |                    "claimId": "CCIS12345678901",
      |                    "originatingClaimId": "000000000000210",
      |                    "incomeSourceId": "LLIS12345678911",
      |                    "incomeSourceType": "$incomeSourceType",
      |                    "taxYearClaimMade": 2039,
      |                    "claimType": "CF",
      |                    "mtdLoss": true,
      |                    "taxYearLossIncurred": 2051,
      |                    "lossAmountUsed": 1000.00,
      |                    "remainingLossValue": 1000.00,
      |                    "lossType": "income"
      |                }
    """.stripMargin)

    def businessProfitAndLossDesJson(incomeSourceType: String): JsValue = Json.parse(
      s"""
         |{
         |                "incomeSourceId": "LLIS12345678908",
         |                "incomeSourceType": "$incomeSourceType",
         |                "incomeSourceName": "abcdefghijklm",
         |                "totalIncome": 1000.00,
         |                "totalExpenses": 1000.00,
         |                "netProfit": 1000.00,
         |                "netLoss": 1000.00,
         |                "totalAdditions": 1000.00,
         |                "totalDeductions": 1000.00,
         |                "accountingAdjustments": 1000.00,
         |                "taxableProfit": 1000.00,
         |                "finalLoss": 1000.00,
         |                "totalBroughtForwardLosses": 1000.00,
         |                "lossForCSFHL": 1000.00,
         |                "broughtForwardLossesUsed": 1000.00,
         |                "taxableProfitAfterLossesDeduction": 1000.00,
         |                "totalLossesCarriedForward": 1000.00,
         |                "totalBroughtForwardIncomeTaxLosses": 1000.00,
         |                "broughtForwardIncomeTaxLossesUsed": 1000.00,
         |                "totalIncomeTaxLossesCarriedForward":100.00
         |            }
    """.stripMargin)

    def defaultCarriedForwardLosses(incomeSourceType: String): JsValue = Json.parse(
      s"""
         |
       | {
         |   "incomeSourceId": "AAIS12345678912",
         |   "incomeSourceType": "$incomeSourceType",
         |   "taxYearLossIncurred": 2027,
         |   "currentLossValue": 1000.00
         | }
         |
    """.stripMargin)

  def desJson(incomeSourceType: String*): JsValue = Json.parse(
    s"""{
      |"inputs": {
      |        "lossesBroughtForward": ${JsArray(incomeSourceType.map(x => lossBroughtForwardDesJsonForFhl(x)))}
      |    },
      |    "calculation": {
      |       "businessProfitAndLoss":
      |       ${JsArray(incomeSourceType.map(x => businessProfitAndLossDesJson(x)))}
      |        ,
      |        "taxCalculation": {
      |            "incomeTax": {
      |                "totalIncomeReceivedFromAllSources": 2108191510,
      |                "totalAllowancesAndDeductions": 89321074915,
      |                "totalTaxableIncome": 68088189411,
      |                "payPensionsProfit": {
      |                    "incomeReceived": 61640964595,
      |                    "allowancesAllocated": 37316373820,
      |                    "taxableIncome": 26528758114,
      |                    "incomeTaxAmount": 94116371209,
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
}
