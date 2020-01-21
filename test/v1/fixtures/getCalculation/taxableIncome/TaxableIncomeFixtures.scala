/*
 * Copyright 2020 HM Revenue & Customs
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

package v1.fixtures.getCalculation.taxableIncome

import play.api.libs.json.{JsObject, JsValue, Json}
import v1.models.des.LossType
import v1.models.des.LossType.INCOME
import v1.models.domain.TypeOfClaim
import v1.models.response.getCalculation.taxableIncome.TaxableIncome
import v1.models.response.getCalculation.taxableIncome.detail._
import v1.models.response.getCalculation.taxableIncome.detail.selfEmployment.SelfEmployment
import v1.models.response.getCalculation.taxableIncome.detail.selfEmployment.detail.{BusinessSourceAdjustableSummary, CarriedForwardLoss, ClaimNotApplied, UnclaimedLoss, LossBroughtForward => SELossBroughtForward, LossClaimsDetail => SELossClaimsDetail, ResultOfClaimApplied => SEResultOfClaimApplied}
import v1.models.response.getCalculation.taxableIncome.detail.selfEmployment.summary.{LossClaimsSummary => SELossClaimsSummary}
import v1.models.response.getCalculation.taxableIncome.detail.ukPropertyFhl.UkPropertyFhl
import v1.models.response.getCalculation.taxableIncome.detail.ukPropertyFhl.detail.{DefaultCarriedForwardLoss => FHLDefaultCarriedForwardLoss, LossBroughtForward => FHLLossBroughtForward, LossClaimsDetail => FHLLossClaimsDetail, ResultOfClaimApplied => FHLResultOfClaimApplied}
import v1.models.response.getCalculation.taxableIncome.detail.ukPropertyFhl.summary.{LossClaimsSummary => FHLLossClaimsSummary}
import v1.models.response.getCalculation.taxableIncome.detail.ukPropertyNonFhl.UkPropertyNonFhl
import v1.models.response.getCalculation.taxableIncome.detail.ukPropertyNonFhl.detail.{DefaultCarriedForwardLoss, LossBroughtForward, LossClaimsDetail, ResultOfClaimApplied, ClaimNotApplied => NonFHLClaimNotApplied}
import v1.models.response.getCalculation.taxableIncome.detail.ukPropertyNonFhl.summary.{LossClaimsSummary => NonFHLLossClaimsSummary}
import v1.models.response.getCalculation.taxableIncome.summary.CalculationSummary

object TaxableIncomeFixtures {

  val taxableIncomeResponse: TaxableIncome = TaxableIncome(
    CalculationSummary(7001,100),
    CalculationDetail(Some(
      PayPensionsProfit(7004,7006,Some(6001),Some(6002),Some(6003),Some(6004),
        Some(BusinessProfitAndLoss(
          Some(List(SelfEmployment("AaIS12345678910",Some(100101.11),Some(100201.11),Some(100301.11),Some(100401.11),
            Some(101501),Some(100501.11),Some(100601.11),Some(100701.11),Some(100901),Some(100801),Some(101301),
            Some(SELossClaimsSummary(Some(101001),Some(101201),Some(101401),Some(101601),Some(101701),Some(101801),Some(101901))),
            Some(SELossClaimsDetail(Some(List(SELossBroughtForward(LossType.INCOME,"2017-18",10101, mtdLoss = true,"AaIS12345678910"))),
              Some(List(SEResultOfClaimApplied(Some("CCIS12345678901"),"2017-18",TypeOfClaim.`carry-forward`, mtdLoss = true, "2017-18",10101,10201,
                LossType.INCOME, "AaIS12345678910"))),Some(List(UnclaimedLoss("2017-18",1001, "2019-20",LossType.INCOME,"AaIS12345678910"))),
              Some(List(CarriedForwardLoss(Some("CCIS12345678901"),TypeOfClaim.`carry-forward`, Some("2018-19"), "2017-18",1001,INCOME, "AaIS12345678910"))),
              Some(List(ClaimNotApplied("CCIS12345678921","2017-18",TypeOfClaim.`carry-forward`, "AaIS12345678910"))))),
            Some(BusinessSourceAdjustableSummary("bsasId", true))),
            SelfEmployment("AbIS12345678910",Some(100102.22),Some(100202.22),Some(100302.22),Some(100402.22),Some(101502),Some(100502.22),Some(100602.22),
              Some(100702.22),Some(100902),Some(100802),Some(101302),Some(SELossClaimsSummary(Some(101002),Some(101202),Some(101402),Some(101602),Some(101702),
                Some(101802),Some(101902))),Some(SELossClaimsDetail(Some(List(SELossBroughtForward(LossType.INCOME, "2017-18",
                10102, mtdLoss = true,"AbIS12345678910"))),Some(List(SEResultOfClaimApplied(Some("CCIS12345678902"),"2017-18",
                TypeOfClaim.`carry-sideways`, mtdLoss = true,"2017-18",10102,10202,LossType.INCOME, "AbIS12345678910"))),
                Some(List(UnclaimedLoss("2017-18",1002, "2019-20", LossType.INCOME, "AbIS12345678910"))),
                Some(List(CarriedForwardLoss(Some("CCIS12345678902"),TypeOfClaim.`carry-forward`,Some("2018-19"),"2017-18",1002,INCOME,"AbIS12345678910"))),
                Some(List(ClaimNotApplied("CCIS12345678922","2017-18",TypeOfClaim.`carry-sideways`, "AbIS12345678910"))))),
            Some(BusinessSourceAdjustableSummary("bsasId", true))))),
          Some(UkPropertyFhl(Some(4001.11),Some(4002.11),Some(4003.11),Some(4004.11),Some(4005.11),Some(4006.11),Some(4007.11),Some(4009),Some(4008),
            Some(4013),Some(FHLLossClaimsSummary(Some(4011),Some(4010),Some(4012),Some(4014))),Some(FHLLossClaimsDetail(
              Some(List(FHLLossBroughtForward("2017-18",40101,mtdLoss = true))),Some(List(FHLResultOfClaimApplied(Some("CCIS12345678904"),"2017-18",
                TypeOfClaim.`carry-forward-to-carry-sideways`,mtdLoss = true,"2017-18",40101,40201))),Some(List(FHLDefaultCarriedForwardLoss("2017-18",401))))))),
          Some(UkPropertyNonFhl(Some(2001.11),Some(2002.11),Some(2003.11),Some(2004.11),Some(2005.11),Some(2006.11),Some(2007.11),Some(2009),Some(2008),Some(2013),
            Some(NonFHLLossClaimsSummary(Some(2010),Some(2012),Some(2014))),Some(LossClaimsDetail(
              Some(List(LossBroughtForward("2017-18",20101,mtdLoss = true))),Some(List(ResultOfClaimApplied(Some("CCIS12345678903"),
                Some("000000000000213"),"2017-18",TypeOfClaim.`carry-sideways-fhl`,mtdLoss = true, "2017-18",20101,20201))),
              Some(List(DefaultCarriedForwardLoss("2017-18",201))),Some(List(NonFHLClaimNotApplied("CCIS12345678923","2017-18",
                TypeOfClaim.`carry-sideways-fhl`))))))))))),Some(SavingsAndGains(7012,7014,
      Some(List(Savings("SAVKB1UVwUTBQGJ","UK Savings Account ONE",90101.11,Some(90201.11),Some(90301.11)),
        Savings("SAVKB2UVwUTBQGJ","UK Savings Account TWO",90102.11,Some(90202.11),Some(90302.11)))))),Some(Dividends(7020,7022))))

  val taxableIncomeWrittenJson: JsValue = Json.parse(
    """
      |{
      |	"summary": {
      |		"totalIncomeReceivedFromAllSources": 7001,
      |		"totalTaxableIncome": 100
      |	},
      |	"detail": {
      |		"payPensionsProfit": {
      |			"incomeReceived": 7004,
      |			"taxableIncome": 7006,
      |			"totalSelfEmploymentProfit": 6001,
      |			"totalPropertyProfit": 6002,
      |			"totalFHLPropertyProfit": 6003,
      |			"totalUKOtherPropertyProfit": 6004,
      |			"businessProfitAndLoss": {
      |				"selfEmployments": [{
      |					"selfEmploymentId": "AaIS12345678910",
      |					"totalIncome": 100101.11,
      |					"totalExpenses": 100201.11,
      |					"netProfit": 100301.11,
      |					"netLoss": 100401.11,
      |					"class4Loss": 101501,
      |					"totalAdditions": 100501.11,
      |					"totalDeductions": 100601.11,
      |					"accountingAdjustments": 100701.11,
      |					"adjustedIncomeTaxLoss": 100901,
      |					"taxableProfit": 100801,
      |					"taxableProfitAfterIncomeTaxLossesDeduction": 101301,
      |					"lossClaimsSummary": {
      |						"totalBroughtForwardIncomeTaxLosses": 101001,
      |						"broughtForwardIncomeTaxLossesUsed": 101201,
      |						"totalIncomeTaxLossesCarriedForward": 101401,
      |						"totalBroughtForwardClass4Losses": 101601,
      |						"broughtForwardClass4LossesUsed": 101701,
      |						"carrySidewaysClass4LossesUsed": 101801,
      |						"totalClass4LossesCarriedForward": 101901
      |					},
      |					"lossClaimsDetail": {
      |						"lossesBroughtForward": [{
      |							"lossType": "income",
      |							"taxYearLossIncurred": "2017-18",
      |							"currentLossValue": 10101,
      |							"mtdLoss": true
      |						}],
      |						"resultOfClaimsApplied": [{
      |							"claimId": "CCIS12345678901",
      |							"taxYearClaimMade": "2017-18",
      |							"claimType": "carry-forward",
      |							"mtdLoss": true,
      |							"taxYearLossIncurred": "2017-18",
      |							"lossAmountUsed": 10101,
      |							"remainingLossValue": 10201,
      |							"lossType": "income"
      |						}],
      |						"unclaimedLosses": [{
      |							"taxYearLossIncurred": "2017-18",
      |							"currentLossValue": 1001,
      |							"expires": "2019-20",
      |							"lossType": "income"
      |						}],
      |						"carriedForwardLosses": [{
      |							"claimId": "CCIS12345678901",
      |							"claimType": "carry-forward",
      |							"taxYearClaimMade": "2018-19",
      |							"taxYearLossIncurred": "2017-18",
      |							"currentLossValue": 1001,
      |							"lossType": "income"
      |						}],
      |						"claimsNotApplied": [{
      |							"claimId": "CCIS12345678921",
      |							"taxYearClaimMade": "2017-18",
      |							"claimType": "carry-forward"
      |						}]
      |					}
      |				}, {
      |					"selfEmploymentId": "AbIS12345678910",
      |					"totalIncome": 100102.22,
      |					"totalExpenses": 100202.22,
      |					"netProfit": 100302.22,
      |					"netLoss": 100402.22,
      |					"class4Loss": 101502,
      |					"totalAdditions": 100502.22,
      |					"totalDeductions": 100602.22,
      |					"accountingAdjustments": 100702.22,
      |					"adjustedIncomeTaxLoss": 100902,
      |					"taxableProfit": 100802,
      |					"taxableProfitAfterIncomeTaxLossesDeduction": 101302,
      |					"lossClaimsSummary": {
      |						"totalBroughtForwardIncomeTaxLosses": 101002,
      |						"broughtForwardIncomeTaxLossesUsed": 101202,
      |						"totalIncomeTaxLossesCarriedForward": 101402,
      |						"totalBroughtForwardClass4Losses": 101602,
      |						"broughtForwardClass4LossesUsed": 101702,
      |						"carrySidewaysClass4LossesUsed": 101802,
      |						"totalClass4LossesCarriedForward": 101902
      |					},
      |					"lossClaimsDetail": {
      |						"lossesBroughtForward": [{
      |							"lossType": "income",
      |							"taxYearLossIncurred": "2017-18",
      |							"currentLossValue": 10102,
      |							"mtdLoss": true
      |						}],
      |						"resultOfClaimsApplied": [{
      |							"claimId": "CCIS12345678902",
      |							"taxYearClaimMade": "2017-18",
      |							"claimType": "carry-sideways",
      |							"mtdLoss": true,
      |							"taxYearLossIncurred": "2017-18",
      |							"lossAmountUsed": 10102,
      |							"remainingLossValue": 10202,
      |							"lossType": "income"
      |						}],
      |						"unclaimedLosses": [{
      |							"taxYearLossIncurred": "2017-18",
      |							"currentLossValue": 1002,
      |							"expires": "2019-20",
      |							"lossType": "income"
      |						}],
      |						"carriedForwardLosses": [{
      |							"claimId": "CCIS12345678902",
      |							"claimType": "carry-forward",
      |							"taxYearClaimMade": "2018-19",
      |							"taxYearLossIncurred": "2017-18",
      |							"currentLossValue": 1002,
      |							"lossType": "income"
      |						}],
      |						"claimsNotApplied": [{
      |							"claimId": "CCIS12345678922",
      |							"taxYearClaimMade": "2017-18",
      |							"claimType": "carry-sideways"
      |						}]
      |					}
      |				}],
      |				"ukPropertyFhl": {
      |					"totalIncome": 4001.11,
      |					"totalExpenses": 4002.11,
      |					"netProfit": 4003.11,
      |					"netLoss": 4004.11,
      |					"totalAdditions": 4005.11,
      |					"totalDeductions": 4006.11,
      |					"accountingAdjustments": 4007.11,
      |					"adjustedIncomeTaxLoss": 4009,
      |					"taxableProfit": 4008,
      |					"taxableProfitAfterIncomeTaxLossesDeduction": 4013,
      |					"lossClaimsSummary": {
      |						"lossForCSFHL": 4011,
      |						"totalBroughtForwardIncomeTaxLosses": 4010,
      |						"broughtForwardIncomeTaxLossesUsed": 4012,
      |						"totalIncomeTaxLossesCarriedForward": 4014
      |					},
      |					"lossClaimsDetail": {
      |						"lossesBroughtForward": [{
      |							"taxYearLossIncurred": "2017-18",
      |							"currentLossValue": 40101,
      |							"mtdLoss": true
      |						}],
      |						"resultOfClaimsApplied": [{
      |							"claimId": "CCIS12345678904",
      |							"taxYearClaimMade": "2017-18",
      |							"claimType": "carry-forward-to-carry-sideways",
      |							"mtdLoss": true,
      |							"taxYearLossIncurred": "2017-18",
      |							"lossAmountUsed": 40101,
      |							"remainingLossValue": 40201
      |						}],
      |						"defaultCarriedForwardLosses": [{
      |							"taxYearLossIncurred": "2017-18",
      |							"currentLossValue": 401
      |						}]
      |					}
      |				},
      |				"ukPropertyNonFhl": {
      |					"totalIncome": 2001.11,
      |					"totalExpenses": 2002.11,
      |					"netProfit": 2003.11,
      |					"netLoss": 2004.11,
      |					"totalAdditions": 2005.11,
      |					"totalDeductions": 2006.11,
      |					"accountingAdjustments": 2007.11,
      |					"adjustedIncomeTaxLoss": 2009,
      |					"taxableProfit": 2008,
      |					"taxableProfitAfterIncomeTaxLossesDeduction": 2013,
      |					"lossClaimsSummary": {
      |						"totalBroughtForwardIncomeTaxLosses": 2010,
      |						"broughtForwardIncomeTaxLossesUsed": 2012,
      |						"totalIncomeTaxLossesCarriedForward": 2014
      |					},
      |					"lossClaimsDetail": {
      |						"lossesBroughtForward": [{
      |							"taxYearLossIncurred": "2017-18",
      |							"currentLossValue": 20101,
      |							"mtdLoss": true
      |						}],
      |						"resultOfClaimsApplied": [{
      |							"claimId": "CCIS12345678903",
      |							"originatingClaimId": "000000000000213",
      |							"taxYearClaimMade": "2017-18",
      |							"claimType": "carry-sideways-fhl",
      |							"mtdLoss": true,
      |							"taxYearLossIncurred": "2017-18",
      |							"lossAmountUsed": 20101,
      |							"remainingLossValue": 20201
      |						}],
      |						"defaultCarriedForwardLosses": [{
      |							"taxYearLossIncurred": "2017-18",
      |							"currentLossValue": 201
      |						}],
      |						"claimsNotApplied": [{
      |							"claimId": "CCIS12345678923",
      |							"taxYearClaimMade": "2017-18",
      |							"claimType": "carry-sideways-fhl"
      |						}]
      |					}
      |				}
      |			}
      |		},
      |		"savingsAndGains": {
      |			"incomeReceived": 7012,
      |			"taxableIncome": 7014,
      |			"ukSavings": [{
      |				"savingsAccountId": "SAVKB1UVwUTBQGJ",
      |				"savingsAccountName": "UK Savings Account ONE",
      |				"grossIncome": 90101.11,
      |				"netIncome": 90201.11,
      |				"taxDeducted": 90301.11
      |			}, {
      |				"savingsAccountId": "SAVKB2UVwUTBQGJ",
      |				"savingsAccountName": "UK Savings Account TWO",
      |				"grossIncome": 90102.11,
      |				"netIncome": 90202.11,
      |				"taxDeducted": 90302.11
      |			}]
      |		},
      |		"dividends": {
      |			"incomeReceived": 7020,
      |			"taxableIncome": 7022
      |		}
      |	}
      |}
      |""".stripMargin)

  val emptyJson: JsObject = JsObject.empty

  val desJson: JsValue = Json.parse(
    """{
      |      "metadata": {
      |        "calculationId": "041f7e4d-87d9-4d4a-a296-3cfbdf92f7e2",
      |        "taxYear": 2018,
      |        "requestedBy": "customer",
      |        "requestedTimestamp": "2019-02-15T09:35:15.094Z",
      |        "calculationReason": "customerRequest",
      |        "calculationTimestamp": "2019-02-15T09:35:15.094Z",
      |        "calculationType": "inYear",
      |        "intentToCrystallise": false,
      |        "crystallised": false,
      |        "crystallisationTimestamp": "2019-02-15T09:35:15.094Z",
      |        "periodFrom": "2018-01-01",
      |        "periodTo": "2019-01-01"
      |      },
      |      "inputs": {
      |        "personalInformation": {
      |          "identifier": "VO123456A",
      |          "dateOfBirth": "1988-08-27",
      |          "taxRegime": "UK",
      |          "statePensionAgeDate": "2053-08-27"
      |        },
      |        "incomeSources": {
      |          "businessIncomeSources": [
      |            {
      |              "incomeSourceId": "AaIS12345678910",
      |              "incomeSourceType": "01",
      |              "incomeSourceName": "Self-Employment Business ONE",
      |              "accountingPeriodStartDate": "2018-01-01",
      |              "accountingPeriodEndDate": "2019-01-01",
      |              "source": "MTD-SA",
      |              "latestPeriodEndDate": "2019-01-01",
      |              "latestReceivedDateTime": "2019-08-06T11:45:01Z",
      |              "finalised": false,
      |              "finalisationTimestamp": "2019-02-15T09:35:15.094Z",
      |              "submissionPeriods": [
      |                {
      |                  "periodId": "abcdefghijk",
      |                  "startDate": "2018-01-01",
      |                  "endDate": "2019-01-01",
      |                  "receivedDateTime": "2019-02-15T09:35:04.843Z"
      |                }
      |              ]
      |            },
      |            {
      |              "incomeSourceId": "AbIS12345678910",
      |              "incomeSourceType": "01",
      |              "incomeSourceName": "Self-Employment Business TWO",
      |              "accountingPeriodStartDate": "2018-01-01",
      |              "accountingPeriodEndDate": "2019-01-01",
      |              "source": "MTD-SA",
      |              "latestPeriodEndDate": "2019-01-01",
      |              "latestReceivedDateTime": "2019-08-06T11:45:01Z",
      |              "finalised": false,
      |              "finalisationTimestamp": "2019-02-15T09:35:15.094Z",
      |              "submissionPeriods": [
      |                {
      |                  "periodId": "abcdefghijk",
      |                  "startDate": "2018-01-01",
      |                  "endDate": "2019-01-01",
      |                  "receivedDateTime": "2019-02-15T09:35:04.843Z"
      |                }
      |              ]
      |            },
      |            {
      |              "incomeSourceId": "AcIS12345678910",
      |              "incomeSourceType": "02",
      |              "incomeSourceName": "UK Property Non-FHL",
      |              "accountingPeriodStartDate": "2018-01-01",
      |              "accountingPeriodEndDate": "2019-01-01",
      |              "source": "MTD-SA",
      |              "latestPeriodEndDate": "2019-01-01",
      |              "latestReceivedDateTime": "2019-08-06T11:45:01Z",
      |              "finalised": false,
      |              "finalisationTimestamp": "2019-02-15T09:35:15.094Z",
      |              "submissionPeriods": [
      |                {
      |                  "periodId": "abcdefghijk",
      |                  "startDate": "2018-01-01",
      |                  "endDate": "2019-01-01",
      |                  "receivedDateTime": "2019-02-15T09:35:04.843Z"
      |                }
      |              ]
      |            },
      |            {
      |              "incomeSourceId": "AdIS12345678910",
      |              "incomeSourceType": "04",
      |              "incomeSourceName": "UK Property FHL",
      |              "accountingPeriodStartDate": "2018-01-01",
      |              "accountingPeriodEndDate": "2019-01-01",
      |              "source": "MTD-SA",
      |              "latestPeriodEndDate": "2019-01-01",
      |              "latestReceivedDateTime": "2019-08-06T11:45:01Z",
      |              "finalised": false,
      |              "finalisationTimestamp": "2019-02-15T09:35:15.094Z",
      |              "submissionPeriods": [
      |                {
      |                  "periodId": "abcdefghijk",
      |                  "startDate": "2018-01-01",
      |                  "endDate": "2019-01-01",
      |                  "receivedDateTime": "2019-02-15T09:35:04.843Z"
      |                }
      |              ]
      |            }
      |          ],
      |          "nonBusinessIncomeSources": [
      |            {
      |              "incomeSourceId": "SAVKB1UVwUTBQGJ",
      |              "incomeSourceType": "09",
      |              "incomeSourceName": "UK Savings Account ONE",
      |              "startDate": "2018-01-01",
      |              "endDate": "2019-01-01",
      |              "source": "MTD-SA",
      |              "periodId": "001",
      |              "latestReceivedDateTime": "2019-08-06T11:45:01Z"
      |            },
      |            {
      |              "incomeSourceId": "SAVKB2UVwUTBQGJ",
      |              "incomeSourceType": "09",
      |              "incomeSourceName": "UK Savings Account TWO",
      |              "startDate": "2018-01-01",
      |              "endDate": "2019-01-01",
      |              "source": "MTD-SA",
      |              "periodId": "001",
      |              "latestReceivedDateTime": "2019-08-06T11:45:01Z"
      |            },
      |            {
      |              "incomeSourceId": "DDIS12345678910",
      |              "incomeSourceType": "10",
      |              "incomeSourceName": "UK Dividends",
      |              "startDate": "2018-01-01",
      |              "endDate": "2019-01-01",
      |              "source": "MTD-SA",
      |              "periodId": "001",
      |              "latestReceivedDateTime": "2019-08-06T11:45:01Z"
      |            }
      |          ]
      |        },
      |        "annualAdjustments": [
      |          {
      |            "incomeSourceId": "AaIS12345678910",
      |            "incomeSourceType": "01",
      |            "bissId": "10000001",
      |            "receivedDateTime": "2019-07-17T08:15:28Z",
      |            "applied": false
      |          },
      |          {
      |            "incomeSourceId": "AbIS12345678910",
      |            "incomeSourceType": "01",
      |            "bissId": "10000002",
      |            "receivedDateTime": "2019-07-17T08:15:28Z",
      |            "applied": true
      |          },
      |          {
      |            "incomeSourceId": "AcIS12345678910",
      |            "incomeSourceType": "02",
      |            "bissId": "10000003",
      |            "receivedDateTime": "2019-07-17T08:15:28Z",
      |            "applied": false
      |          },
      |          {
      |            "incomeSourceId": "AdIS12345678910",
      |            "incomeSourceType": "04",
      |            "bissId": "10000004",
      |            "receivedDateTime": "2019-07-17T08:15:28Z",
      |            "applied": true
      |          }
      |        ],
      |        "lossesBroughtForward": [
      |          {
      |            "lossId": "LLIS12345678901",
      |            "incomeSourceId": "AaIS12345678910",
      |            "incomeSourceType": "01",
      |            "submissionTimestamp": "2019-07-13T07:51:43Z",
      |            "lossType": "income",
      |            "taxYearLossIncurred": 2018,
      |            "currentLossValue": 10101,
      |            "mtdLoss": true
      |          },
      |          {
      |            "lossId": "LLIS12345678902",
      |            "incomeSourceId": "AbIS12345678910",
      |            "incomeSourceType": "01",
      |            "submissionTimestamp": "2019-07-13T07:51:43Z",
      |            "lossType": "income",
      |            "taxYearLossIncurred": 2018,
      |            "currentLossValue": 10102,
      |            "mtdLoss": true
      |          },
      |          {
      |            "lossId": "LLIS12345678903",
      |            "incomeSourceId": "AcIS12345678910",
      |            "incomeSourceType": "02",
      |            "submissionTimestamp": "2019-07-13T07:51:43Z",
      |            "lossType": "income",
      |            "taxYearLossIncurred": 2018,
      |            "currentLossValue": 20101,
      |            "mtdLoss": true
      |          },
      |          {
      |            "lossId": "LLIS12345678904",
      |            "incomeSourceId": "AdIS12345678910",
      |            "incomeSourceType": "04",
      |            "submissionTimestamp": "2019-07-13T07:51:43Z",
      |            "lossType": "income",
      |            "taxYearLossIncurred": 2018,
      |            "currentLossValue": 40101,
      |            "mtdLoss": true
      |          }
      |        ],
      |        "claims": [
      |          {
      |            "claimId": "CCIS12345678901",
      |            "originatingClaimId": "000000000000211",
      |            "incomeSourceId": "AaIS12345678910",
      |            "incomeSourceType": "01",
      |            "submissionTimestamp": "2019-08-13T07:51:43Z",
      |            "taxYearClaimMade": 2018,
      |            "claimType": "CF"
      |          },
      |          {
      |            "claimId": "CCIS12345678902",
      |            "originatingClaimId": "000000000000212",
      |            "incomeSourceId": "AbIS12345678910",
      |            "incomeSourceType": "01",
      |            "submissionTimestamp": "2019-08-13T07:51:43Z",
      |            "taxYearClaimMade": 2018,
      |            "claimType": "CSGI"
      |          },
      |          {
      |            "claimId": "CCIS12345678903",
      |            "originatingClaimId": "000000000000213",
      |            "incomeSourceId": "AcIS12345678910",
      |            "incomeSourceType": "02",
      |            "submissionTimestamp": "2019-08-13T07:51:43Z",
      |            "taxYearClaimMade": 2018,
      |            "claimType": "CSFHL"
      |          },
      |          {
      |            "claimId": "CCIS12345678904",
      |            "originatingClaimId": "000000000000214",
      |            "incomeSourceId": "AdIS12345678910",
      |            "incomeSourceType": "04",
      |            "submissionTimestamp": "2019-08-13T07:51:43Z",
      |            "taxYearClaimMade": 2018,
      |            "claimType": "CFCSGI"
      |          },
      |          {
      |            "claimId": "CCIS12345678921",
      |            "originatingClaimId": "000000000000221",
      |            "incomeSourceId": "AaIS12345678910",
      |            "incomeSourceType": "01",
      |            "submissionTimestamp": "2019-08-13T07:51:43Z",
      |            "taxYearClaimMade": 2018,
      |            "claimType": "CF"
      |          },
      |          {
      |            "claimId": "CCIS12345678922",
      |            "originatingClaimId": "000000000000222",
      |            "incomeSourceId": "AbIS12345678910",
      |            "incomeSourceType": "01",
      |            "submissionTimestamp": "2019-08-13T07:51:43Z",
      |            "taxYearClaimMade": 2018,
      |            "claimType": "CSGI"
      |          },
      |          {
      |            "claimId": "CCIS12345678923",
      |            "originatingClaimId": "000000000000223",
      |            "incomeSourceId": "AcIS12345678910",
      |            "incomeSourceType": "02",
      |            "submissionTimestamp": "2019-08-13T07:51:43Z",
      |            "taxYearClaimMade": 2018,
      |            "claimType": "CSFHL"
      |          },
      |          {
      |            "claimId": "CCIS12345678924",
      |            "originatingClaimId": "000000000000224",
      |            "incomeSourceId": "AdIS12345678910",
      |            "incomeSourceType": "04",
      |            "submissionTimestamp": "2019-08-13T07:51:43Z",
      |            "taxYearClaimMade": 2018,
      |            "claimType": "CFCSGI"
      |          }
      |        ]
      |      },
      |      "calculation": {
      |        "allowancesAndDeductions": {
      |          "personalAllowance": 8001,
      |          "reducedPersonalAllowance": 8002,
      |          "giftOfInvestmentsAndPropertyToCharity": 8003,
      |          "blindPersonsAllowance": 8004,
      |          "lossesAppliedToGeneralIncome": 8005
      |        },
      |        "reliefs": {
      |          "residentialFinanceCosts": {
      |            "amountClaimed": 8006,
      |            "allowableAmount": 8007,
      |            "rate": 2,
      |            "propertyFinanceRelief": 8008
      |          }
      |        },
      |        "taxDeductedAtSource": {
      |          "bbsi": 8009,
      |          "ukLandAndProperty": 8010
      |        },
      |        "giftAid": {
      |          "grossGiftAidPayments": 8011,
      |          "rate": 35,
      |          "giftAidTax": 8012.11
      |        },
      |        "businessProfitAndLoss": [
      |          {
      |            "incomeSourceId": "AaIS12345678910",
      |            "incomeSourceType": "01",
      |            "incomeSourceName": "Self-Employment Business ONE",
      |            "totalIncome": 100101.11,
      |            "totalExpenses": 100201.11,
      |            "netProfit": 100301.11,
      |            "netLoss": 100401.11,
      |            "totalAdditions": 100501.11,
      |            "totalDeductions": 100601.11,
      |            "accountingAdjustments": 100701.11,
      |            "taxableProfit": 100801,
      |            "adjustedIncomeTaxLoss": 100901,
      |            "totalBroughtForwardIncomeTaxLosses": 101001,
      |            "lossForCSFHL": 101101,
      |            "broughtForwardIncomeTaxLossesUsed": 101201,
      |            "taxableProfitAfterIncomeTaxLossesDeduction": 101301,
      |            "totalIncomeTaxLossesCarriedForward": 101401,
      |            "class4Loss": 101501,
      |            "totalBroughtForwardClass4Losses": 101601,
      |            "broughtForwardClass4LossesUsed": 101701,
      |            "carrySidewaysClass4LossesUsed": 101801,
      |            "totalClass4LossesCarriedForward": 101901
      |          },
      |          {
      |            "incomeSourceId": "AbIS12345678910",
      |            "incomeSourceType": "01",
      |            "incomeSourceName": "Self-Employment Business TWO",
      |            "totalIncome": 100102.22,
      |            "totalExpenses": 100202.22,
      |            "netProfit": 100302.22,
      |            "netLoss": 100402.22,
      |            "totalAdditions": 100502.22,
      |            "totalDeductions": 100602.22,
      |            "accountingAdjustments": 100702.22,
      |            "taxableProfit": 100802,
      |            "adjustedIncomeTaxLoss": 100902,
      |            "totalBroughtForwardIncomeTaxLosses": 101002,
      |            "lossForCSFHL": 101102,
      |            "broughtForwardIncomeTaxLossesUsed": 101202,
      |            "taxableProfitAfterIncomeTaxLossesDeduction": 101302,
      |            "totalIncomeTaxLossesCarriedForward": 101402,
      |            "class4Loss": 101502,
      |            "totalBroughtForwardClass4Losses": 101602,
      |            "broughtForwardClass4LossesUsed": 101702,
      |            "carrySidewaysClass4LossesUsed": 101802,
      |            "totalClass4LossesCarriedForward": 101902
      |          },
      |          {
      |            "incomeSourceId": "AcIS12345678910",
      |            "incomeSourceType": "02",
      |            "incomeSourceName": "UK Property Non-FHL",
      |            "totalIncome": 2001.11,
      |            "totalExpenses": 2002.11,
      |            "netProfit": 2003.11,
      |            "netLoss": 2004.11,
      |            "totalAdditions": 2005.11,
      |            "totalDeductions": 2006.11,
      |            "accountingAdjustments": 2007.11,
      |            "taxableProfit": 2008,
      |            "adjustedIncomeTaxLoss": 2009,
      |            "totalBroughtForwardIncomeTaxLosses": 2010,
      |            "lossForCSFHL": 2011,
      |            "broughtForwardIncomeTaxLossesUsed": 2012,
      |            "taxableProfitAfterIncomeTaxLossesDeduction": 2013,
      |            "totalIncomeTaxLossesCarriedForward": 2014,
      |            "class4Loss": 2015,
      |            "totalBroughtForwardClass4Losses": 2016,
      |            "broughtForwardClass4LossesUsed": 2017,
      |            "carrySidewaysClass4LossesUsed": 2018,
      |            "totalClass4LossesCarriedForward": 2019
      |          },
      |          {
      |            "incomeSourceId": "AdIS12345678910",
      |            "incomeSourceType": "04",
      |            "incomeSourceName": "UK Property FHL",
      |            "totalIncome": 4001.11,
      |            "totalExpenses": 4002.11,
      |            "netProfit": 4003.11,
      |            "netLoss": 4004.11,
      |            "totalAdditions": 4005.11,
      |            "totalDeductions": 4006.11,
      |            "accountingAdjustments": 4007.11,
      |            "taxableProfit": 4008,
      |            "adjustedIncomeTaxLoss": 4009,
      |            "totalBroughtForwardIncomeTaxLosses": 4010,
      |            "lossForCSFHL": 4011,
      |            "broughtForwardIncomeTaxLossesUsed": 4012,
      |            "taxableProfitAfterIncomeTaxLossesDeduction": 4013,
      |            "totalIncomeTaxLossesCarriedForward": 4014,
      |            "class4Loss": 4015,
      |            "totalBroughtForwardClass4Losses": 4016,
      |            "broughtForwardClass4LossesUsed": 4017,
      |            "carrySidewaysClass4LossesUsed": 4018,
      |            "totalClass4LossesCarriedForward": 4019
      |          }
      |        ],
      |        "savingsAndGainsIncome": [
      |          {
      |            "incomeSourceId": "SAVKB1UVwUTBQGJ",
      |            "incomeSourceType": "09",
      |            "incomeSourceName": "UK Savings Account ONE",
      |            "grossIncome": 90101.11,
      |            "netIncome": 90201.11,
      |            "taxDeducted": 90301.11
      |          },
      |          {
      |            "incomeSourceId": "SAVKB2UVwUTBQGJ",
      |            "incomeSourceType": "09",
      |            "incomeSourceName": "UK Savings Account TWO",
      |            "grossIncome": 90102.11,
      |            "netIncome": 90202.11,
      |            "taxDeducted": 90302.11
      |          }
      |        ],
      |        "incomeSummaryTotals": {
      |          "totalSelfEmploymentProfit": 6001,
      |          "totalPropertyProfit": 6002,
      |          "totalFHLPropertyProfit": 6003,
      |          "totalUKOtherPropertyProfit": 6004
      |        },
      |        "taxCalculation": {
      |          "incomeTax": {
      |            "totalIncomeReceivedFromAllSources": 7001,
      |            "totalAllowancesAndDeductions": 7002,
      |            "totalTaxableIncome": 7003,
      |            "payPensionsProfit": {
      |              "incomeReceived": 7004,
      |              "allowancesAllocated": 7005,
      |              "taxableIncome": 7006,
      |              "incomeTaxAmount": 7007.11,
      |              "taxBands": [
      |                {
      |                  "name": "SSR",
      |                  "rate": 31,
      |                  "bandLimit": 7008,
      |                  "apportionedBandLimit": 7009,
      |                  "income": 7010,
      |                  "taxAmount": 7011.11
      |                }
      |              ]
      |            },
      |            "savingsAndGains": {
      |              "incomeReceived": 7012,
      |              "allowancesAllocated": 7013,
      |              "taxableIncome": 7014,
      |              "incomeTaxAmount": 7015.11,
      |              "taxBands": [
      |                {
      |                  "name": "SSR",
      |                  "rate": 42,
      |                  "bandLimit": 7016,
      |                  "apportionedBandLimit": 7017,
      |                  "income": 7018,
      |                  "taxAmount": 7019
      |                }
      |              ]
      |            },
      |            "dividends": {
      |              "incomeReceived": 7020,
      |              "allowancesAllocated": 7021,
      |              "taxableIncome": 7022,
      |              "incomeTaxAmount": 7023.11,
      |              "taxBands": [
      |                {
      |                  "name": "SSR",
      |                  "rate": 83,
      |                  "bandLimit": 7024,
      |                  "apportionedBandLimit": 7025,
      |                  "income": 7026,
      |                  "taxAmount": 7027.11
      |                }
      |              ]
      |            },
      |            "incomeTaxCharged": 7028,
      |            "totalReliefs": 7029,
      |            "incomeTaxDueAfterReliefs": 7030.11,
      |            "incomeTaxDueAfterGiftAid": 7031.11,
      |            "totalTaxableIncome" : 100
      |          },
      |          "nics": {
      |            "class2Nics": {
      |              "amount": 5001.11,
      |              "weeklyRate": 5002.11,
      |              "weeks": 23,
      |              "limit": 5004,
      |              "apportionedLimit": 5005,
      |              "underSmallProfitThreshold": false,
      |              "actualClass2Nic": false
      |            },
      |            "class4Nics": {
      |              "totalIncomeLiableToClass4Charge": 5006,
      |              "totalClass4LossesAvailable": 5007,
      |              "totalClass4LossesUsed": 5008,
      |              "totalClass4LossesCarriedForward": 5009,
      |              "totalIncomeChargeableToClass4": 5010,
      |              "totalAmount": 5011.11,
      |              "nic4Bands": [
      |                {
      |                  "name": "ZRT",
      |                  "rate": 1,
      |                  "threshold": 5012,
      |                  "apportionedThreshold": 5013,
      |                  "income": 5014,
      |                  "amount": 5015.11
      |                }
      |              ]
      |            },
      |            "nic2NetOfDeductions": 5016.11,
      |            "nic4NetOfDeductions": 5017.11,
      |            "totalNic": 5018.11
      |          },
      |          "totalIncomeTaxNicsCharged": 5019.11,
      |          "totalTaxDeducted": 5020,
      |          "totalIncomeTaxAndNicsDue": 5021.11
      |        },
      |        "previousCalculation": {
      |          "calculationTimestamp": "2019-02-15T09:35:15.094Z",
      |          "calculationId": "12345678",
      |          "totalIncomeTaxAndNicsDue": 5022.11,
      |          "incomeTaxNicDueThisPeriod": 5023.11
      |        },
      |        "endOfYearEstimate": {
      |          "incomeSource": [
      |            {
      |              "incomeSourceId": "AaIS12345678910",
      |              "incomeSourceType": "01",
      |              "incomeSourceName": "Self-Employment Business ONE",
      |              "taxableIncome": 10001,
      |              "finalised": true
      |            },
      |            {
      |              "incomeSourceId": "AbIS12345678910",
      |              "incomeSourceType": "01",
      |              "incomeSourceName": "Self-Employment Business TWO",
      |              "taxableIncome": 10002,
      |              "finalised": true
      |            },
      |            {
      |              "incomeSourceId": "AcIS12345678910",
      |              "incomeSourceType": "02",
      |              "incomeSourceName": "UK Property Non FHL",
      |              "taxableIncome": 20001,
      |              "finalised": true
      |            },
      |            {
      |              "incomeSourceId": "AdIS12345678910",
      |              "incomeSourceType": "04",
      |              "incomeSourceName": "UK Property FHL",
      |              "taxableIncome": 40001,
      |              "finalised": true
      |            },
      |            {
      |              "incomeSourceId": "SAVKB1UVwUTBQGJ",
      |              "incomeSourceType": "09",
      |              "incomeSourceName": "UK Savings Account ONE",
      |              "taxableIncome": 90001,
      |              "finalised": true
      |            },
      |            {
      |              "incomeSourceId": "SAVKB2UVwUTBQGJ",
      |              "incomeSourceType": "09",
      |              "incomeSourceName": "UK Savings Account TWO",
      |              "taxableIncome": 90002,
      |              "finalised": true
      |            },
      |            {
      |              "incomeSourceId": "DDIS12345678910",
      |              "incomeSourceType": "10",
      |              "incomeSourceName": "UK Dividends",
      |              "taxableIncome": 10001,
      |              "finalised": true
      |            }
      |          ],
      |          "totalEstimatedIncome": 6005,
      |          "totalTaxableIncome": 6006,
      |          "incomeTaxAmount": 6007.11,
      |          "nic2": 6008.11,
      |          "nic4": 6009.11,
      |          "totalNicAmount": 6010.11,
      |          "incomeTaxNicAmount": 6011.11
      |        },
      |        "lossesAndClaims": {
      |          "resultOfClaimsApplied": [
      |            {
      |              "claimId": "CCIS12345678901",
      |              "originatingClaimId": "000000000000211",
      |              "incomeSourceId": "AaIS12345678910",
      |              "incomeSourceType": "01",
      |              "taxYearClaimMade": 2018,
      |              "claimType": "CF",
      |              "mtdLoss": true,
      |              "taxYearLossIncurred": 2018,
      |              "lossAmountUsed": 10101,
      |              "remainingLossValue": 10201,
      |              "lossType": "income"
      |            },
      |            {
      |              "claimId": "CCIS12345678902",
      |              "originatingClaimId": "000000000000212",
      |              "incomeSourceId": "AbIS12345678910",
      |              "incomeSourceType": "01",
      |              "taxYearClaimMade": 2018,
      |              "claimType": "CSGI",
      |              "mtdLoss": true,
      |              "taxYearLossIncurred": 2018,
      |              "lossAmountUsed": 10102,
      |              "remainingLossValue": 10202,
      |              "lossType": "income"
      |            },
      |            {
      |              "claimId": "CCIS12345678903",
      |              "originatingClaimId": "000000000000213",
      |              "incomeSourceId": "AcIS12345678910",
      |              "incomeSourceType": "02",
      |              "taxYearClaimMade": 2018,
      |              "claimType": "CSFHL",
      |              "mtdLoss": true,
      |              "taxYearLossIncurred": 2018,
      |              "lossAmountUsed": 20101,
      |              "remainingLossValue": 20201,
      |              "lossType": "income"
      |            },
      |            {
      |              "claimId": "CCIS12345678904",
      |              "originatingClaimId": "000000000000214",
      |              "incomeSourceId": "AdIS12345678910",
      |              "incomeSourceType": "04",
      |              "taxYearClaimMade": 2018,
      |              "claimType": "CFCSGI",
      |              "mtdLoss": true,
      |              "taxYearLossIncurred": 2018,
      |              "lossAmountUsed": 40101,
      |              "remainingLossValue": 40201,
      |              "lossType": "income"
      |            }
      |          ],
      |          "unclaimedLosses": [
      |            {
      |              "incomeSourceId": "AaIS12345678910",
      |              "incomeSourceType": "01",
      |              "taxYearLossIncurred": 2018,
      |              "currentLossValue": 1001,
      |              "expires": 2020,
      |              "lossType": "income"
      |            },
      |            {
      |              "incomeSourceId": "AbIS12345678910",
      |              "incomeSourceType": "01",
      |              "taxYearLossIncurred": 2018,
      |              "currentLossValue": 1002,
      |              "expires": 2020,
      |              "lossType": "income"
      |            },
      |            {
      |              "incomeSourceId": "AcIS12345678910",
      |              "incomeSourceType": "02",
      |              "taxYearLossIncurred": 2018,
      |              "currentLossValue": 2001,
      |              "expires": 2020,
      |              "lossType": "income"
      |            },
      |            {
      |              "incomeSourceId": "AdIS12345678910",
      |              "incomeSourceType": "04",
      |              "taxYearLossIncurred": 2018,
      |              "currentLossValue": 4001,
      |              "expires": 2020,
      |              "lossType": "income"
      |            }
      |          ],
      |          "carriedForwardLosses": [
      |            {
      |              "claimId": "CCIS12345678901",
      |              "originatingClaimId": "000000000000211",
      |              "incomeSourceId": "AaIS12345678910",
      |              "incomeSourceType": "01",
      |              "claimType": "CF",
      |              "taxYearClaimMade": 2019,
      |              "taxYearLossIncurred": 2018,
      |              "currentLossValue": 1001,
      |              "lossType": "income"
      |            },
      |            {
      |              "claimId": "CCIS12345678902",
      |              "originatingClaimId": "000000000000212",
      |              "incomeSourceId": "AbIS12345678910",
      |              "incomeSourceType": "01",
      |              "claimType": "CF",
      |              "taxYearClaimMade": 2019,
      |              "taxYearLossIncurred": 2018,
      |              "currentLossValue": 1002,
      |              "lossType": "income"
      |            },
      |            {
      |              "claimId": "CCIS12345678903",
      |              "originatingClaimId": "000000000000213",
      |              "incomeSourceId": "AcIS12345678910",
      |              "incomeSourceType": "02",
      |              "claimType": "CSFHL",
      |              "taxYearClaimMade": 2019,
      |              "taxYearLossIncurred": 2018,
      |              "currentLossValue": 2001,
      |              "lossType": "income"
      |            },
      |            {
      |              "claimId": "CCIS12345678904",
      |              "originatingClaimId": "000000000000214",
      |              "incomeSourceId": "AdIS12345678910",
      |              "incomeSourceType": "04",
      |              "claimType": "CFCSGI",
      |              "taxYearClaimMade": 2019,
      |              "taxYearLossIncurred": 2018,
      |              "currentLossValue": 4001,
      |              "lossType": "income"
      |            }
      |          ],
      |          "defaultCarriedForwardLosses": [
      |            {
      |              "incomeSourceId": "AaIS12345678910",
      |              "incomeSourceType": "01",
      |              "taxYearLossIncurred": 2018,
      |              "currentLossValue": 101
      |            },
      |            {
      |              "incomeSourceId": "AbIS12345678910",
      |              "incomeSourceType": "01",
      |              "taxYearLossIncurred": 2018,
      |              "currentLossValue": 102
      |            },
      |            {
      |              "incomeSourceId": "AcIS12345678910",
      |              "incomeSourceType": "02",
      |              "taxYearLossIncurred": 2018,
      |              "currentLossValue": 201
      |            },
      |            {
      |              "incomeSourceId": "AdIS12345678910",
      |              "incomeSourceType": "04",
      |              "taxYearLossIncurred": 2018,
      |              "currentLossValue": 401
      |            }
      |          ],
      |          "claimsNotApplied": [
      |            {
      |              "claimId": "CCIS12345678921",
      |              "incomeSourceId": "AaIS12345678910",
      |              "incomeSourceType": "01",
      |              "taxYearClaimMade": 2018,
      |              "claimType": "CF"
      |            },
      |            {
      |              "claimId": "CCIS12345678922",
      |              "incomeSourceId": "AbIS12345678910",
      |              "incomeSourceType": "01",
      |              "taxYearClaimMade": 2018,
      |              "claimType": "CSGI"
      |            },
      |            {
      |              "claimId": "CCIS12345678923",
      |              "incomeSourceId": "AcIS12345678910",
      |              "incomeSourceType": "02",
      |              "taxYearClaimMade": 2018,
      |              "claimType": "CSFHL"
      |            },
      |            {
      |              "claimId": "CCIS12345678924",
      |              "incomeSourceId": "AdIS12345678910",
      |              "incomeSourceType": "04",
      |              "taxYearClaimMade": 2018,
      |              "claimType": "CFCSGI"
      |            }
      |          ]
      |        }
      |      },
      |      "messages": {
      |        "info": [
      |          {
      |            "id": "C11101",
      |            "text": "You have entered a large amount in total Gift Aid payments. Please check"
      |          }
      |        ],
      |        "warnings": [
      |          {
      |            "id": "C11102",
      |            "text": "Total amount of one-off Gift Aid payments cannot exceed the total gift aid payments. Please check."
      |          }
      |        ]
      |      }
      |    }
    """.stripMargin)
}
