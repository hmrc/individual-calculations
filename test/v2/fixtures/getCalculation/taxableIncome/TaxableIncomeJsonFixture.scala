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

package v2.fixtures.getCalculation.taxableIncome

import play.api.libs.json.{JsValue, Json}

object TaxableIncomeJsonFixture {

  val desJson: JsValue = Json.parse(
    """
      |{
      |   "metadata": {
      |      "calculationId": "041f7e4d-87d9-4d4a-a296-3cfbdf92f7e2",
      |      "taxYear": 2018,
      |      "requestedBy": "customer",
      |      "requestedTimestamp": "2019-02-15T09:35:15.094Z",
      |      "calculationReason": "customerRequest",
      |      "calculationTimestamp": "2019-02-15T09:35:15.094Z",
      |      "calculationType": "inYear",
      |      "intentToCrystallise": false,
      |      "crystallised": false,
      |      "crystallisationTimestamp": "2019-02-15T09:35:15.094Z",
      |      "periodFrom": "2018-01-01",
      |      "periodTo": "2019-01-01"
      |   },
      |   "inputs": {
      |      "annualAdjustments": [
      |         {
      |            "incomeSourceId": "AaIS12345678910",
      |            "incomeSourceType": "01",
      |            "ascId": "10000001",
      |            "receivedDateTime": "2019-07-17T08:15:28Z",
      |            "applied": true
      |         },
      |         {
      |            "incomeSourceId": "AbIS12345678910",
      |            "incomeSourceType": "01",
      |            "ascId": "10000002",
      |            "receivedDateTime": "2019-07-17T08:15:28Z",
      |            "applied": true
      |         },
      |         {
      |            "incomeSourceId": "AcIS12345678910",
      |            "incomeSourceType": "02",
      |            "ascId": "10000003",
      |            "receivedDateTime": "2019-07-17T08:15:28Z",
      |            "applied": false
      |         },
      |         {
      |            "incomeSourceId": "AdIS12345678910",
      |            "incomeSourceType": "04",
      |            "ascId": "12345678",
      |            "receivedDateTime": "2019-07-17T08:15:28Z",
      |            "applied": true
      |         },
      |         {
      |            "incomeSourceId": "AeIS12345678910",
      |            "incomeSourceType": "03",
      |            "ascId": "87654321",
      |            "receivedDateTime": "2019-07-17T08:15:28Z",
      |            "applied": false
      |         },
      |         {
      |            "incomeSourceId": "AfIS12345678910",
      |            "incomeSourceType": "15",
      |            "ascId": "10000006",
      |            "receivedDateTime": "2019-07-17T08:15:28Z",
      |            "applied": false
      |         }
      |      ],
      |      "lossesBroughtForward": [
      |         {
      |            "lossId": "LLIS12345678901",
      |            "incomeSourceId": "AaIS12345678910",
      |            "incomeSourceType": "01",
      |            "submissionTimestamp": "2019-07-13T07:51:43Z",
      |            "lossType": "income",
      |            "taxYearLossIncurred": 2018,
      |            "currentLossValue": 10101,
      |            "mtdLoss": true
      |         },
      |         {
      |            "lossId": "LLIS12345678902",
      |            "incomeSourceId": "AbIS12345678910",
      |            "incomeSourceType": "01",
      |            "submissionTimestamp": "2019-07-13T07:51:43Z",
      |            "lossType": "income",
      |            "taxYearLossIncurred": 2018,
      |            "currentLossValue": 10102,
      |            "mtdLoss": true
      |         },
      |         {
      |            "lossId": "LLIS12345678903",
      |            "incomeSourceId": "AcIS12345678910",
      |            "incomeSourceType": "02",
      |            "submissionTimestamp": "2019-07-13T07:51:43Z",
      |            "lossType": "income",
      |            "taxYearLossIncurred": 2018,
      |            "currentLossValue": 20101,
      |            "mtdLoss": true
      |         },
      |         {
      |            "lossId": "LLIS12345678904",
      |            "incomeSourceId": "AdIS12345678910",
      |            "incomeSourceType": "04",
      |            "submissionTimestamp": "2019-07-13T07:51:43Z",
      |            "lossType": "income",
      |            "taxYearLossIncurred": 2018,
      |            "currentLossValue": 40101,
      |            "mtdLoss": true
      |         },
      |         {
      |            "lossId": "LLIS12345678905",
      |            "incomeSourceId": "AeIS12345678910",
      |            "incomeSourceType": "03",
      |            "submissionTimestamp": "2019-07-13T07:51:43Z",
      |            "lossType": "income",
      |            "taxYearLossIncurred": 2018,
      |            "currentLossValue": 50101,
      |            "mtdLoss": false
      |         },
      |         {
      |            "lossId": "LLIS12345678906",
      |            "incomeSourceId": "AfIS12345678910",
      |            "incomeSourceType": "15",
      |            "submissionTimestamp": "2019-07-13T07:51:43Z",
      |            "lossType": "income",
      |            "taxYearLossIncurred": 2018,
      |            "currentLossValue": 60101,
      |            "mtdLoss": false
      |         }
      |      ]
      |   },
      |   "calculation": {
      |      "businessProfitAndLoss": [
      |         {
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
      |            "totalIncomeTaxLossesCarriedForward": 101601,
      |            "class4Loss": 101501,
      |            "totalBroughtForwardClass4Losses": 101701,
      |            "carrySidewaysIncomeTaxLossesUsed": 101401,
      |            "broughtForwardClass4LossesUsed": 101801,
      |            "carrySidewaysClass4LossesUsed": 101901,
      |            "totalClass4LossesCarriedForward": 101119
      |         },
      |         {
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
      |            "totalIncomeTaxLossesCarriedForward": 101602,
      |            "class4Loss": 101502,
      |            "totalBroughtForwardClass4Losses": 101702,
      |            "carrySidewaysIncomeTaxLossesUsed": 101402,
      |            "broughtForwardClass4LossesUsed": 101802,
      |            "carrySidewaysClass4LossesUsed": 101902,
      |            "totalClass4LossesCarriedForward": 101392
      |         },
      |         {
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
      |            "totalIncomeTaxLossesCarriedForward": 2011,
      |            "broughtForwardCarrySidewaysIncomeTaxLossesUsed": 2020,
      |            "class4Loss": 2015,
      |            "totalBroughtForwardClass4Losses": 2016,
      |            "broughtForwardClass4LossesUsed": 2017,
      |            "carrySidewaysIncomeTaxLossesUsed": 2014,
      |            "totalClass4LossesCarriedForward": 2019
      |         },
      |         {
      |            "incomeSourceId": "AdIS12345678910",
      |            "incomeSourceType": "04",
      |            "incomeSourceName": "UK Property FHL",
      |            "totalIncome": 4001.11,
      |            "totalExpenses": 4002.11,
      |            "netProfit": 4003.11,
      |            "netLoss": 4004.11,
      |            "totalAdditions": 4005.11,
      |            "totalDeductions": 4006.11,
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
      |         },
      |         {
      |            "incomeSourceId": "AeIS12345678910",
      |            "incomeSourceType": "03",
      |            "incomeSourceName": "EEA Property FHL",
      |            "totalIncome": 5001.11,
      |            "totalExpenses": 5002.11,
      |            "netProfit": 5003.11,
      |            "netLoss": 5004.11,
      |            "totalAdditions": 5005.11,
      |            "totalDeductions": 5006.11,
      |            "taxableProfit": 5008,
      |            "adjustedIncomeTaxLoss": 5009,
      |            "totalBroughtForwardIncomeTaxLosses": 5010,
      |            "lossForCSFHL": 5011,
      |            "broughtForwardIncomeTaxLossesUsed": 5012,
      |            "taxableProfitAfterIncomeTaxLossesDeduction": 5013,
      |            "totalIncomeTaxLossesCarriedForward": 5014,
      |            "class4Loss": 5015,
      |            "totalBroughtForwardClass4Losses": 5016,
      |            "broughtForwardClass4LossesUsed": 5017,
      |            "carrySidewaysClass4LossesUsed": 5018,
      |            "totalClass4LossesCarriedForward": 5019
      |         },
      |         {
      |            "incomeSourceId": "AfIS12345678910",
      |            "incomeSourceType": "15",
      |            "incomeSourceName": "Foreign Property",
      |            "totalIncome": 6001.11,
      |            "totalExpenses": 6002.11,
      |            "netProfit": 6003.11,
      |            "netLoss": 6004.11,
      |            "totalAdditions": 6005.11,
      |            "totalDeductions": 6006.11,
      |            "accountingAdjustments": 6007.11,
      |            "taxableProfit": 6008,
      |            "adjustedIncomeTaxLoss": 6009,
      |            "totalBroughtForwardIncomeTaxLosses": 6010,
      |            "lossForCSFHL": 6011,
      |            "broughtForwardIncomeTaxLossesUsed": 6012,
      |            "taxableProfitAfterIncomeTaxLossesDeduction": 6013,
      |            "totalIncomeTaxLossesCarriedForward": 6011,
      |            "broughtForwardCarrySidewaysIncomeTaxLossesUsed": 6020,
      |            "class4Loss": 6015,
      |            "totalBroughtForwardClass4Losses": 6016,
      |            "broughtForwardClass4LossesUsed": 6017,
      |            "carrySidewaysIncomeTaxLossesUsed": 6014,
      |            "totalClass4LossesCarriedForward": 6019
      |         }
      |      ],
      |      "savingsAndGainsIncome": {
      |         "totalUkSavingsAndGains": 7016,
      |         "chargeableForeignSavingsAndGains": 7019,
      |         "ukSavingsAndGainsIncome": [
      |            {
      |               "incomeSourceId": "SAVKB1UVwUTBQGJ",
      |               "incomeSourceType": "09",
      |               "incomeSourceName": "UK Savings Account ONE",
      |               "grossIncome": 90101.11,
      |               "netIncome": 90201.11,
      |               "taxDeducted": 90301.11
      |            },
      |            {
      |               "incomeSourceId": "SAVKB2UVwUTBQGJ",
      |               "incomeSourceType": "09",
      |               "incomeSourceName": "UK Savings Account TWO",
      |               "grossIncome": 90102.11,
      |               "netIncome": 90202.11,
      |               "taxDeducted": 90302.11
      |            },
      |            {
      |               "incomeSourceType": "18",
      |               "grossIncome": 11101.11,
      |               "netIncome": 11201.11,
      |               "taxDeducted": 11301.11
      |            }
      |         ]
      |      },
      |      "incomeSummaryTotals": {
      |         "totalSelfEmploymentProfit": 6001,
      |         "totalPropertyProfit": 6002,
      |         "totalFHLPropertyProfit": 6003,
      |         "totalUKOtherPropertyProfit": 6004,
      |         "totalForeignPropertyProfit": 6005,
      |         "totalEeaFhlProfit": 6006,
      |         "totalEmploymentIncome": 6012
      |      },
      |      "taxCalculation": {
      |         "incomeTax": {
      |            "totalIncomeReceivedFromAllSources": 7001,
      |            "totalAllowancesAndDeductions": 7002,
      |            "totalTaxableIncome": 100,
      |            "payPensionsProfit": {
      |               "incomeReceived": 7004,
      |               "allowancesAllocated": 7005,
      |               "taxableIncome": 7006,
      |               "incomeTaxAmount": 7007.11,
      |               "taxBands": [
      |                  {
      |                     "name": "SSR",
      |                     "rate": 31,
      |                     "bandLimit": 7008,
      |                     "apportionedBandLimit": 7009,
      |                     "income": 7010,
      |                     "taxAmount": 7011.11
      |                  }
      |               ]
      |            },
      |            "savingsAndGains": {
      |               "incomeReceived": 7012,
      |               "allowancesAllocated": 7013,
      |               "taxableIncome": 7014,
      |               "incomeTaxAmount": 7015.11,
      |               "taxBands": [
      |                  {
      |                     "name": "SSR",
      |                     "rate": 42,
      |                     "bandLimit": 7016,
      |                     "apportionedBandLimit": 7017,
      |                     "income": 7018,
      |                     "taxAmount": 7019
      |                  }
      |               ]
      |            },
      |            "dividends": {
      |               "incomeReceived": 7020,
      |               "allowancesAllocated": 7021,
      |               "taxableIncome": 7022,
      |               "incomeTaxAmount": 7023.11,
      |               "taxBands": [
      |                  {
      |                     "name": "SSR",
      |                     "rate": 83,
      |                     "bandLimit": 7024,
      |                     "apportionedBandLimit": 7025,
      |                     "income": 7026,
      |                     "taxAmount": 7027.11
      |                  }
      |               ]
      |            },
      |            "lumpSums": {
      |               "incomeReceived": 8010,
      |               "taxableIncome": 8020
      |            },
      |            "gainsOnLifePolicies": {
      |               "incomeReceived": 9010,
      |               "taxableIncome": 9020
      |            },
      |            "incomeTaxCharged": 7028,
      |            "totalReliefs": 7029,
      |            "incomeTaxDueAfterReliefs": 7030.11,
      |            "incomeTaxDueAfterGiftAid": 7031.11
      |         }
      |      },
      |      "lossesAndClaims": {
      |         "resultOfClaimsApplied": [
      |            {
      |               "claimId": "CCIS12345678901",
      |               "originatingClaimId": "000000000000211",
      |               "incomeSourceId": "AaIS12345678910",
      |               "incomeSourceType": "01",
      |               "taxYearClaimMade": 2018,
      |               "claimType": "CF",
      |               "mtdLoss": true,
      |               "taxYearLossIncurred": 2018,
      |               "lossAmountUsed": 10101,
      |               "remainingLossValue": 10201,
      |               "lossType": "income"
      |            },
      |            {
      |               "claimId": "CCIS12345678902",
      |               "originatingClaimId": "000000000000212",
      |               "incomeSourceId": "AbIS12345678910",
      |               "incomeSourceType": "01",
      |               "taxYearClaimMade": 2018,
      |               "claimType": "CSGI",
      |               "mtdLoss": true,
      |               "taxYearLossIncurred": 2018,
      |               "lossAmountUsed": 10102,
      |               "remainingLossValue": 10202,
      |               "lossType": "income"
      |            },
      |            {
      |               "claimId": "CCIS12345678903",
      |               "originatingClaimId": "000000000000213",
      |               "incomeSourceId": "AcIS12345678910",
      |               "incomeSourceType": "02",
      |               "taxYearClaimMade": 2018,
      |               "claimType": "CSFHL",
      |               "mtdLoss": true,
      |               "taxYearLossIncurred": 2018,
      |               "lossAmountUsed": 20101,
      |               "remainingLossValue": 20201,
      |               "lossType": "income"
      |            },
      |            {
      |               "claimId": "CCIS12345678904",
      |               "originatingClaimId": "000000000000214",
      |               "incomeSourceId": "AdIS12345678910",
      |               "incomeSourceType": "04",
      |               "taxYearClaimMade": 2018,
      |               "claimType": "CFCSGI",
      |               "mtdLoss": true,
      |               "taxYearLossIncurred": 2018,
      |               "lossAmountUsed": 40101,
      |               "remainingLossValue": 40201,
      |               "lossType": "income"
      |            },
      |            {
      |               "claimId": "CCIS12345678905",
      |               "originatingClaimId": "000000000000215",
      |               "incomeSourceId": "AeIS12345678910",
      |               "incomeSourceType": "03",
      |               "taxYearClaimMade": 2018,
      |               "claimType": "CF",
      |               "mtdLoss": false,
      |               "taxYearLossIncurred": 2018,
      |               "lossAmountUsed": 50101,
      |               "remainingLossValue": 50201,
      |               "lossType": "income"
      |            },
      |            {
      |               "claimId": "CCIS12345678906",
      |               "originatingClaimId": "000000000000216",
      |               "incomeSourceId": "AfIS12345678910",
      |               "incomeSourceType": "15",
      |               "taxYearClaimMade": 2018,
      |               "claimType": "CSFHL",
      |               "mtdLoss": false,
      |               "taxYearLossIncurred": 2018,
      |               "lossAmountUsed": 60101,
      |               "remainingLossValue": 60201,
      |               "lossType": "income"
      |            }
      |         ],
      |         "unclaimedLosses": [
      |            {
      |               "incomeSourceId": "AaIS12345678910",
      |               "incomeSourceType": "01",
      |               "taxYearLossIncurred": 2018,
      |               "currentLossValue": 1001,
      |               "expires": 2020,
      |               "lossType": "income"
      |            },
      |            {
      |               "incomeSourceId": "AbIS12345678910",
      |               "incomeSourceType": "01",
      |               "taxYearLossIncurred": 2018,
      |               "currentLossValue": 1002,
      |               "expires": 2020,
      |               "lossType": "income"
      |            }
      |         ],
      |         "carriedForwardLosses": [
      |            {
      |               "claimId": "CCIS12345678901",
      |               "originatingClaimId": "000000000000211",
      |               "incomeSourceId": "AaIS12345678910",
      |               "incomeSourceType": "01",
      |               "claimType": "CF",
      |               "taxYearClaimMade": 2019,
      |               "taxYearLossIncurred": 2018,
      |               "currentLossValue": 1001,
      |               "lossType": "income"
      |            },
      |            {
      |               "claimId": "CCIS12345678902",
      |               "originatingClaimId": "000000000000212",
      |               "incomeSourceId": "AbIS12345678910",
      |               "incomeSourceType": "01",
      |               "claimType": "CF",
      |               "taxYearClaimMade": 2019,
      |               "taxYearLossIncurred": 2018,
      |               "currentLossValue": 1002,
      |               "lossType": "income"
      |            }
      |         ],
      |         "defaultCarriedForwardLosses": [
      |            {
      |               "incomeSourceId": "AcIS12345678910",
      |               "incomeSourceType": "02",
      |               "taxYearLossIncurred": 2018,
      |               "currentLossValue": 201
      |            },
      |            {
      |               "incomeSourceId": "AdIS12345678910",
      |               "incomeSourceType": "04",
      |               "taxYearLossIncurred": 2018,
      |               "currentLossValue": 401
      |            },
      |            {
      |               "incomeSourceId": "AeIS12345678910",
      |               "incomeSourceType": "03",
      |               "taxYearLossIncurred": 2018,
      |               "currentLossValue": 501
      |            },
      |            {
      |               "incomeSourceId": "AfIS12345678910",
      |               "incomeSourceType": "15",
      |               "taxYearLossIncurred": 2018,
      |               "currentLossValue": 601
      |            }
      |         ],
      |         "claimsNotApplied": [
      |            {
      |               "claimId": "CCIS12345678921",
      |               "incomeSourceId": "AaIS12345678910",
      |               "incomeSourceType": "01",
      |               "taxYearClaimMade": 2018,
      |               "claimType": "CF"
      |            },
      |            {
      |               "claimId": "CCIS12345678922",
      |               "incomeSourceId": "AbIS12345678910",
      |               "incomeSourceType": "01",
      |               "taxYearClaimMade": 2018,
      |               "claimType": "CSGI"
      |            },
      |            {
      |               "claimId": "CCIS12345678923",
      |               "incomeSourceId": "AcIS12345678910",
      |               "incomeSourceType": "02",
      |               "taxYearClaimMade": 2018,
      |               "claimType": "CSFHL"
      |            },
      |            {
      |               "claimId": "CCIS12345678926",
      |               "incomeSourceId": "AfIS12345678910",
      |               "incomeSourceType": "15",
      |               "taxYearClaimMade": 2018,
      |               "claimType": "CSFHL"
      |            }
      |         ]
      |      },
      |      "dividendsIncome": {
      |         "totalUkDividends": 7024,
      |         "chargeableForeignDividends": 7026
      |      },
      |      "chargeableEventGainsIncome": {
      |         "totalOfAllGains": 7015,
      |         "totalGainsWithNoTaxPaidAndVoidedIsa": 7017,
      |         "totalForeignGainsOnLifePoliciesNoTaxPaid": 7018,
      |         "totalGainsWithTaxPaid": 9030,
      |         "totalForeignGainsOnLifePoliciesTaxPaid": 9040
      |      },
      |      "employmentAndPensionsIncome": {
      |         "totalOccupationalPensionIncome": 6007.77,
      |         "totalBenefitsInKind": 6009.99,
      |         "totalPayeEmploymentAndLumpSumIncome": 6010.00,
      |         "tipsIncome": 6020.12
      |      },
      |      "stateBenefitsIncome": {
      |         "totalStateBenefitsIncome": 6008.88
      |      },
      |      "employmentExpenses": {
      |         "totalEmploymentExpenses": 6011.11
      |      },
      |      "seafarersDeductions": {
      |         "totalSeafarersDeduction": 6013.10
      |      },
      |      "foreignTaxForFtcrNotClaimed": {
      |         "foreignTaxOnForeignEmployment": 6014.15
      |      },
      |      "shareSchemesIncome": {
      |         "totalIncome": 6015.20
      |      },
      |      "foreignIncome": {
      |         "chargeableOverseasPensionsStateBenefitsRoyalties": 6016.25,
      |         "chargeableAllOtherIncomeReceivedWhilstAbroad": 6017.30,
      |         "overseasIncomeAndGains": {
      |            "gainAmount": 6018.35
      |         },
      |         "totalForeignBenefitsAndGifts": 6019.40
      |      }
      |   },
      |   "messages": {
      |      "info": [
      |         {
      |            "id": "C11101",
      |            "text": "You have entered a large amount in total Gift Aid payments. Please check"
      |         }
      |      ],
      |      "warnings": [
      |         {
      |            "id": "C11102",
      |            "text": "Total amount of one-off Gift Aid payments cannot exceed the total gift aid payments. Please check."
      |         }
      |      ]
      |   }
      |}
    """.stripMargin
  )

  val oneSelfEmploymentOnlyDesJson: JsValue = Json.parse(
    """
      |{
      |   "metadata": {
      |      "calculationId": "041f7e4d-87d9-4d4a-a296-3cfbdf92f7e2",
      |      "taxYear": 2018,
      |      "requestedBy": "customer",
      |      "requestedTimestamp": "2019-02-15T09:35:15.094Z",
      |      "calculationReason": "customerRequest",
      |      "calculationTimestamp": "2019-02-15T09:35:15.094Z",
      |      "calculationType": "inYear",
      |      "intentToCrystallise": false,
      |      "crystallised": false,
      |      "crystallisationTimestamp": "2019-02-15T09:35:15.094Z",
      |      "periodFrom": "2018-01-01",
      |      "periodTo": "2019-01-01"
      |   },
      |   "inputs": {
      |      "annualAdjustments": [
      |         {
      |            "incomeSourceId": "AaIS12345678910",
      |            "incomeSourceType": "01",
      |            "ascId": "10000001",
      |            "receivedDateTime": "2019-07-17T08:15:28Z",
      |            "applied": true
      |         }
      |      ],
      |      "lossesBroughtForward": [
      |         {
      |            "lossId": "LLIS12345678901",
      |            "incomeSourceId": "AaIS12345678910",
      |            "incomeSourceType": "01",
      |            "submissionTimestamp": "2019-07-13T07:51:43Z",
      |            "lossType": "income",
      |            "taxYearLossIncurred": 2018,
      |            "currentLossValue": 10101,
      |            "mtdLoss": true
      |         }
      |      ]
      |   },
      |   "calculation": {
      |      "businessProfitAndLoss": [
      |         {
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
      |            "totalIncomeTaxLossesCarriedForward": 101601,
      |            "class4Loss": 101501,
      |            "totalBroughtForwardClass4Losses": 101701,
      |            "carrySidewaysIncomeTaxLossesUsed": 101401,
      |            "broughtForwardClass4LossesUsed": 101801,
      |            "carrySidewaysClass4LossesUsed": 101901,
      |            "totalClass4LossesCarriedForward": 101119
      |         }
      |      ],
      |      "savingsAndGainsIncome": {
      |         "totalUkSavingsAndGains": 7016,
      |         "chargeableForeignSavingsAndGains": 7019
      |      },
      |      "incomeSummaryTotals": {
      |         "totalSelfEmploymentProfit": 6001,
      |         "totalPropertyProfit": 6002,
      |         "totalFHLPropertyProfit": 6003,
      |         "totalUKOtherPropertyProfit": 6004,
      |         "totalForeignPropertyProfit": 6005,
      |         "totalEeaFhlProfit": 6006,
      |         "totalEmploymentIncome": 6012
      |      },
      |      "taxCalculation": {
      |         "incomeTax": {
      |            "totalIncomeReceivedFromAllSources": 7001,
      |            "totalAllowancesAndDeductions": 7002,
      |            "totalTaxableIncome": 100,
      |            "payPensionsProfit": {
      |               "incomeReceived": 7004,
      |               "allowancesAllocated": 7005,
      |               "taxableIncome": 7006,
      |               "incomeTaxAmount": 7007.11,
      |               "taxBands": [
      |                  {
      |                     "name": "SSR",
      |                     "rate": 31,
      |                     "bandLimit": 7008,
      |                     "apportionedBandLimit": 7009,
      |                     "income": 7010,
      |                     "taxAmount": 7011.11
      |                  }
      |               ]
      |            },
      |            "savingsAndGains": {
      |               "incomeReceived": 7012,
      |               "allowancesAllocated": 7013,
      |               "taxableIncome": 7014,
      |               "incomeTaxAmount": 7015.11,
      |               "taxBands": [
      |                  {
      |                     "name": "SSR",
      |                     "rate": 42,
      |                     "bandLimit": 7016,
      |                     "apportionedBandLimit": 7017,
      |                     "income": 7018,
      |                     "taxAmount": 7019
      |                  }
      |               ]
      |            },
      |            "dividends": {
      |               "incomeReceived": 7020,
      |               "allowancesAllocated": 7021,
      |               "taxableIncome": 7022,
      |               "incomeTaxAmount": 7023.11,
      |               "taxBands": [
      |                  {
      |                     "name": "SSR",
      |                     "rate": 83,
      |                     "bandLimit": 7024,
      |                     "apportionedBandLimit": 7025,
      |                     "income": 7026,
      |                     "taxAmount": 7027.11
      |                  }
      |               ]
      |            },
      |            "lumpSums": {
      |               "incomeReceived": 8010,
      |               "taxableIncome": 8020
      |            },
      |            "gainsOnLifePolicies": {
      |               "incomeReceived": 9010,
      |               "taxableIncome": 9020
      |            },
      |            "incomeTaxCharged": 7028,
      |            "totalReliefs": 7029,
      |            "incomeTaxDueAfterReliefs": 7030.11,
      |            "incomeTaxDueAfterGiftAid": 7031.11
      |         }
      |      },
      |      "lossesAndClaims": {
      |         "resultOfClaimsApplied": [
      |            {
      |               "claimId": "CCIS12345678901",
      |               "originatingClaimId": "000000000000211",
      |               "incomeSourceId": "AaIS12345678910",
      |               "incomeSourceType": "01",
      |               "taxYearClaimMade": 2018,
      |               "claimType": "CF",
      |               "mtdLoss": true,
      |               "taxYearLossIncurred": 2018,
      |               "lossAmountUsed": 10101,
      |               "remainingLossValue": 10201,
      |               "lossType": "income"
      |            }
      |         ],
      |         "unclaimedLosses": [
      |            {
      |               "incomeSourceId": "AaIS12345678910",
      |               "incomeSourceType": "01",
      |               "taxYearLossIncurred": 2018,
      |               "currentLossValue": 1001,
      |               "expires": 2020,
      |               "lossType": "income"
      |            }
      |         ],
      |         "carriedForwardLosses": [
      |            {
      |               "claimId": "CCIS12345678901",
      |               "originatingClaimId": "000000000000211",
      |               "incomeSourceId": "AaIS12345678910",
      |               "incomeSourceType": "01",
      |               "claimType": "CF",
      |               "taxYearClaimMade": 2019,
      |               "taxYearLossIncurred": 2018,
      |               "currentLossValue": 1001,
      |               "lossType": "income"
      |            }
      |         ],
      |         "claimsNotApplied": [
      |            {
      |               "claimId": "CCIS12345678921",
      |               "incomeSourceId": "AaIS12345678910",
      |               "incomeSourceType": "01",
      |               "taxYearClaimMade": 2018,
      |               "claimType": "CF"
      |            }
      |         ]
      |      },
      |      "employmentAndPensionsIncome": {
      |         "totalOccupationalPensionIncome": 6007.77,
      |         "totalBenefitsInKind": 6009.99,
      |         "totalPayeEmploymentAndLumpSumIncome": 6010.00
      |      },
      |      "stateBenefitsIncome": {
      |         "totalStateBenefitsIncome": 6008.88
      |      },
      |      "employmentExpenses": {
      |         "totalEmploymentExpenses": 6011.11
      |      }
      |   },
      |   "messages": {
      |      "info": [
      |         {
      |            "id": "C11101",
      |            "text": "You have entered a large amount in total Gift Aid payments. Please check"
      |         }
      |      ],
      |      "warnings": [
      |         {
      |            "id": "C11102",
      |            "text": "Total amount of one-off Gift Aid payments cannot exceed the total gift aid payments. Please check."
      |         }
      |      ]
      |   }
      |}
    """.stripMargin
  )

  val noValidIncomeSourcesDesJson: JsValue = Json.parse(
    """
      |{
      |   "metadata": {
      |      "calculationId": "041f7e4d-87d9-4d4a-a296-3cfbdf92f7e2",
      |      "taxYear": 2018,
      |      "requestedBy": "customer",
      |      "requestedTimestamp": "2019-02-15T09:35:15.094Z",
      |      "calculationReason": "customerRequest",
      |      "calculationTimestamp": "2019-02-15T09:35:15.094Z",
      |      "calculationType": "inYear",
      |      "intentToCrystallise": false,
      |      "crystallised": false,
      |      "crystallisationTimestamp": "2019-02-15T09:35:15.094Z",
      |      "periodFrom": "2018-01-01",
      |      "periodTo": "2019-01-01"
      |   },
      |   "inputs": {
      |      "annualAdjustments": [
      |         {
      |            "incomeSourceId": "AaIS12345678910",
      |            "incomeSourceType": "99",
      |            "ascId": "10000001",
      |            "receivedDateTime": "2019-07-17T08:15:28Z",
      |            "applied": true
      |         }
      |      ],
      |      "lossesBroughtForward": [
      |         {
      |            "lossId": "LLIS12345678901",
      |            "incomeSourceId": "AaIS12345678910",
      |            "incomeSourceType": "99",
      |            "submissionTimestamp": "2019-07-13T07:51:43Z",
      |            "lossType": "income",
      |            "taxYearLossIncurred": 2018,
      |            "currentLossValue": 10101,
      |            "mtdLoss": true
      |         }
      |      ]
      |   },
      |   "calculation": {
      |      "businessProfitAndLoss": [
      |         {
      |            "incomeSourceId": "AaIS12345678910",
      |            "incomeSourceType": "99",
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
      |            "totalIncomeTaxLossesCarriedForward": 101601,
      |            "class4Loss": 101501,
      |            "totalBroughtForwardClass4Losses": 101701,
      |            "carrySidewaysIncomeTaxLossesUsed": 101401,
      |            "broughtForwardClass4LossesUsed": 101801,
      |            "carrySidewaysClass4LossesUsed": 101901,
      |            "totalClass4LossesCarriedForward": 101119
      |         }
      |      ],
      |      "savingsAndGainsIncome": {
      |         "totalUkSavingsAndGains": 7016,
      |         "chargeableForeignSavingsAndGains": 7019
      |      },
      |      "incomeSummaryTotals": {
      |         "totalSelfEmploymentProfit": 6001,
      |         "totalPropertyProfit": 6002,
      |         "totalFHLPropertyProfit": 6003,
      |         "totalUKOtherPropertyProfit": 6004,
      |         "totalForeignPropertyProfit": 6005,
      |         "totalEeaFhlProfit": 6006,
      |         "totalEmploymentIncome": 6012
      |      },
      |      "taxCalculation": {
      |         "incomeTax": {
      |            "totalIncomeReceivedFromAllSources": 7001,
      |            "totalAllowancesAndDeductions": 7002,
      |            "totalTaxableIncome": 100,
      |            "payPensionsProfit": {
      |               "incomeReceived": 7004,
      |               "allowancesAllocated": 7005,
      |               "taxableIncome": 7006,
      |               "incomeTaxAmount": 7007.11,
      |               "taxBands": [
      |                  {
      |                     "name": "SSR",
      |                     "rate": 31,
      |                     "bandLimit": 7008,
      |                     "apportionedBandLimit": 7009,
      |                     "income": 7010,
      |                     "taxAmount": 7011.11
      |                  }
      |               ]
      |            },
      |            "savingsAndGains": {
      |               "incomeReceived": 7012,
      |               "allowancesAllocated": 7013,
      |               "taxableIncome": 7014,
      |               "incomeTaxAmount": 7015.11,
      |               "taxBands": [
      |                  {
      |                     "name": "SSR",
      |                     "rate": 42,
      |                     "bandLimit": 7016,
      |                     "apportionedBandLimit": 7017,
      |                     "income": 7018,
      |                     "taxAmount": 7019
      |                  }
      |               ]
      |            },
      |            "dividends": {
      |               "incomeReceived": 7020,
      |               "allowancesAllocated": 7021,
      |               "taxableIncome": 7022,
      |               "incomeTaxAmount": 7023.11,
      |               "taxBands": [
      |                  {
      |                     "name": "SSR",
      |                     "rate": 83,
      |                     "bandLimit": 7024,
      |                     "apportionedBandLimit": 7025,
      |                     "income": 7026,
      |                     "taxAmount": 7027.11
      |                  }
      |               ]
      |            },
      |            "lumpSums": {
      |               "incomeReceived": 8010,
      |               "taxableIncome": 8020
      |            },
      |            "gainsOnLifePolicies": {
      |               "incomeReceived": 9010,
      |               "taxableIncome": 9020
      |            },
      |            "incomeTaxCharged": 7028,
      |            "totalReliefs": 7029,
      |            "incomeTaxDueAfterReliefs": 7030.11,
      |            "incomeTaxDueAfterGiftAid": 7031.11
      |         }
      |      },
      |      "lossesAndClaims": {
      |         "resultOfClaimsApplied": [
      |            {
      |               "claimId": "CCIS12345678901",
      |               "originatingClaimId": "000000000000211",
      |               "incomeSourceId": "AaIS12345678910",
      |               "incomeSourceType": "99",
      |               "taxYearClaimMade": 2018,
      |               "claimType": "CF",
      |               "mtdLoss": true,
      |               "taxYearLossIncurred": 2018,
      |               "lossAmountUsed": 10101,
      |               "remainingLossValue": 10201,
      |               "lossType": "income"
      |            }
      |         ],
      |         "unclaimedLosses": [
      |            {
      |               "incomeSourceId": "AaIS12345678910",
      |               "incomeSourceType": "99",
      |               "taxYearLossIncurred": 2018,
      |               "currentLossValue": 1001,
      |               "expires": 2020,
      |               "lossType": "income"
      |            }
      |         ],
      |         "carriedForwardLosses": [
      |            {
      |               "claimId": "CCIS12345678901",
      |               "originatingClaimId": "000000000000211",
      |               "incomeSourceId": "AaIS12345678910",
      |               "incomeSourceType": "99",
      |               "claimType": "CF",
      |               "taxYearClaimMade": 2019,
      |               "taxYearLossIncurred": 2018,
      |               "currentLossValue": 1001,
      |               "lossType": "income"
      |            }
      |         ],
      |         "claimsNotApplied": [
      |            {
      |               "claimId": "CCIS12345678921",
      |               "incomeSourceId": "AaIS12345678910",
      |               "incomeSourceType": "99",
      |               "taxYearClaimMade": 2018,
      |               "claimType": "CF"
      |            }
      |         ]
      |      },
      |      "employmentAndPensionsIncome": {
      |         "totalOccupationalPensionIncome": 6007.77,
      |         "totalBenefitsInKind": 6009.99,
      |         "totalPayeEmploymentAndLumpSumIncome": 6010.00
      |      },
      |      "stateBenefitsIncome": {
      |         "totalStateBenefitsIncome": 6008.88
      |      },
      |      "employmentExpenses": {
      |         "totalEmploymentExpenses": 6011.11
      |      }
      |   },
      |   "messages": {
      |      "info": [
      |         {
      |            "id": "C11101",
      |            "text": "You have entered a large amount in total Gift Aid payments. Please check"
      |         }
      |      ],
      |      "warnings": [
      |         {
      |            "id": "C11102",
      |            "text": "Total amount of one-off Gift Aid payments cannot exceed the total gift aid payments. Please check."
      |         }
      |      ]
      |   }
      |}
    """.stripMargin
  )

  val mtdJson: JsValue = Json.parse(
    """
      |{
      |   "summary": {
      |      "totalIncomeReceivedFromAllSources": 7001,
      |      "totalTaxableIncome": 100
      |   },
      |   "detail": {
      |      "payPensionsProfit": {
      |         "incomeReceived": 7004,
      |         "taxableIncome": 7006,
      |         "totalSelfEmploymentProfit": 6001,
      |         "totalPropertyProfit": 6002,
      |         "totalFHLPropertyProfit": 6003,
      |         "totalUKOtherPropertyProfit": 6004,
      |         "totalForeignPropertyProfit": 6005,
      |         "totalEeaFhlProfit": 6006,
      |         "totalOccupationalPensionIncome": 6007.77,
      |         "totalStateBenefitsIncome": 6008.88,
      |         "totalBenefitsInKind": 6009.99,
      |         "totalPayeEmploymentAndLumpSumIncome": 6010,
      |         "totalEmploymentExpenses": 6011.11,
      |         "totalSeafarersDeduction": 6013.10,
      |         "totalForeignTaxOnForeignEmployment": 6014.15,
      |         "totalEmploymentIncome": 6012,
      |         "totalShareSchemesIncome": 6015.20,
      |         "totalOverseasPensionsStateBenefitsRoyalties": 6016.25,
      |         "totalAllOtherIncomeReceivedWhilstAbroad": 6017.30,
      |         "totalOverseasIncomeAndGains": 6018.35,
      |         "totalForeignBenefitsAndGifts": 6019.40,
      |         "tipsIncome": 6020.12,
      |         "businessProfitAndLoss": {
      |            "selfEmployments": [
      |               {
      |                  "selfEmploymentId": "AaIS12345678910",
      |                  "totalIncome": 100101.11,
      |                  "totalExpenses": 100201.11,
      |                  "netProfit": 100301.11,
      |                  "netLoss": 100401.11,
      |                  "class4Loss": 101501,
      |                  "totalAdditions": 100501.11,
      |                  "totalDeductions": 100601.11,
      |                  "accountingAdjustments": 100701.11,
      |                  "adjustedIncomeTaxLoss": 100901,
      |                  "taxableProfit": 100801,
      |                  "taxableProfitAfterIncomeTaxLossesDeduction": 101301,
      |                  "lossClaimsSummary": {
      |                     "totalBroughtForwardIncomeTaxLosses": 101001,
      |                     "broughtForwardIncomeTaxLossesUsed": 101201,
      |                     "carrySidewaysIncomeTaxLossesUsed": 101401,
      |                     "totalIncomeTaxLossesCarriedForward": 101601,
      |                     "totalBroughtForwardClass4Losses": 101701,
      |                     "broughtForwardClass4LossesUsed": 101801,
      |                     "carrySidewaysClass4LossesUsed": 101901,
      |                     "totalClass4LossesCarriedForward": 101119
      |                  },
      |                  "lossClaimsDetail": {
      |                     "lossesBroughtForward": [
      |                        {
      |                           "lossType": "income",
      |                           "currentLossValue": 10101,
      |                           "taxYearLossIncurred": "2017-18",
      |                           "mtdLoss": true
      |                        }
      |                     ],
      |                     "resultOfClaimsApplied": [
      |                        {
      |                           "claimType": "carry-forward",
      |                           "lossType": "income",
      |                           "lossAmountUsed": 10101,
      |                           "claimId": "CCIS12345678901",
      |                           "remainingLossValue": 10201,
      |                           "taxYearClaimMade": "2017-18",
      |                           "mtdLoss": true,
      |                           "taxYearLossIncurred": "2017-18"
      |                        }
      |                     ],
      |                     "unclaimedLosses": [
      |                        {
      |                           "lossType": "income",
      |                           "currentLossValue": 1001,
      |                           "taxYearLossIncurred": "2017-18"
      |                        }
      |                     ],
      |                     "carriedForwardLosses": [
      |                        {
      |                           "claimType": "carry-forward",
      |                           "lossType": "income",
      |                           "currentLossValue": 1001,
      |                           "claimId": "CCIS12345678901",
      |                           "taxYearClaimMade": "2018-19",
      |                           "taxYearLossIncurred": "2017-18"
      |                        }
      |                     ],
      |                     "claimsNotApplied": [
      |                        {
      |                           "claimType": "carry-forward",
      |                           "claimId": "CCIS12345678921",
      |                           "taxYearClaimMade": "2017-18"
      |                        }
      |                     ]
      |                  },
      |                  "bsas": {
      |                     "bsasId": "10000001",
      |                     "applied": true
      |                  }
      |               },
      |               {
      |                  "selfEmploymentId": "AbIS12345678910",
      |                  "totalIncome": 100102.22,
      |                  "totalExpenses": 100202.22,
      |                  "netProfit": 100302.22,
      |                  "netLoss": 100402.22,
      |                  "class4Loss": 101502,
      |                  "totalAdditions": 100502.22,
      |                  "totalDeductions": 100602.22,
      |                  "accountingAdjustments": 100702.22,
      |                  "adjustedIncomeTaxLoss": 100902,
      |                  "taxableProfit": 100802,
      |                  "taxableProfitAfterIncomeTaxLossesDeduction": 101302,
      |                  "lossClaimsSummary": {
      |                     "totalBroughtForwardIncomeTaxLosses": 101002,
      |                     "broughtForwardIncomeTaxLossesUsed": 101202,
      |                     "carrySidewaysIncomeTaxLossesUsed": 101402,
      |                     "totalIncomeTaxLossesCarriedForward": 101602,
      |                     "totalBroughtForwardClass4Losses": 101702,
      |                     "broughtForwardClass4LossesUsed": 101802,
      |                     "carrySidewaysClass4LossesUsed": 101902,
      |                     "totalClass4LossesCarriedForward": 101392
      |                  },
      |                  "lossClaimsDetail": {
      |                     "lossesBroughtForward": [
      |                        {
      |                           "lossType": "income",
      |                           "currentLossValue": 10102,
      |                           "taxYearLossIncurred": "2017-18",
      |                           "mtdLoss": true
      |                        }
      |                     ],
      |                     "resultOfClaimsApplied": [
      |                        {
      |                           "claimType": "carry-sideways",
      |                           "lossType": "income",
      |                           "lossAmountUsed": 10102,
      |                           "claimId": "CCIS12345678902",
      |                           "remainingLossValue": 10202,
      |                           "taxYearClaimMade": "2017-18",
      |                           "mtdLoss": true,
      |                           "taxYearLossIncurred": "2017-18"
      |                        }
      |                     ],
      |                     "unclaimedLosses": [
      |                        {
      |                           "lossType": "income",
      |                           "currentLossValue": 1002,
      |                           "taxYearLossIncurred": "2017-18"
      |                        }
      |                     ],
      |                     "carriedForwardLosses": [
      |                        {
      |                           "claimType": "carry-forward",
      |                           "lossType": "income",
      |                           "currentLossValue": 1002,
      |                           "claimId": "CCIS12345678902",
      |                           "taxYearClaimMade": "2018-19",
      |                           "taxYearLossIncurred": "2017-18"
      |                        }
      |                     ],
      |                     "claimsNotApplied": [
      |                        {
      |                           "claimType": "carry-sideways",
      |                           "claimId": "CCIS12345678922",
      |                           "taxYearClaimMade": "2017-18"
      |                        }
      |                     ]
      |                  },
      |                  "bsas": {
      |                     "bsasId": "10000002",
      |                     "applied": true
      |                  }
      |               }
      |            ],
      |            "ukPropertyFhl": {
      |               "totalIncome": 4001.11,
      |               "totalExpenses": 4002.11,
      |               "netProfit": 4003.11,
      |               "netLoss": 4004.11,
      |               "totalAdditions": 4005.11,
      |               "totalDeductions": 4006.11,
      |               "adjustedIncomeTaxLoss": 4009,
      |               "taxableProfit": 4008,
      |               "taxableProfitAfterIncomeTaxLossesDeduction": 4013,
      |               "lossClaimsSummary": {
      |                  "lossForCSFHL": 4011,
      |                  "totalBroughtForwardIncomeTaxLosses": 4010,
      |                  "broughtForwardIncomeTaxLossesUsed": 4012,
      |                  "totalIncomeTaxLossesCarriedForward": 4014
      |               },
      |               "lossClaimsDetail": {
      |                  "lossesBroughtForward": [
      |                     {
      |                        "taxYearLossIncurred": "2017-18",
      |                        "currentLossValue": 40101,
      |                        "mtdLoss": true
      |                     }
      |                  ],
      |                  "resultOfClaimsApplied": [
      |                     {
      |                        "claimId": "CCIS12345678904",
      |                        "taxYearClaimMade": "2017-18",
      |                        "claimType": "carry-forward-to-carry-sideways",
      |                        "mtdLoss": true,
      |                        "taxYearLossIncurred": "2017-18",
      |                        "lossAmountUsed": 40101,
      |                        "remainingLossValue": 40201
      |                     }
      |                  ],
      |                  "defaultCarriedForwardLosses": [
      |                     {
      |                        "taxYearLossIncurred": "2017-18",
      |                        "currentLossValue": 401
      |                     }
      |                  ]
      |               },
      |               "bsas": {
      |                  "bsasId": "12345678",
      |                  "applied": true
      |               }
      |            },
      |            "ukPropertyNonFhl": {
      |               "totalIncome": 2001.11,
      |               "totalExpenses": 2002.11,
      |               "netProfit": 2003.11,
      |               "netLoss": 2004.11,
      |               "totalAdditions": 2005.11,
      |               "totalDeductions": 2006.11,
      |               "accountingAdjustments": 2007.11,
      |               "adjustedIncomeTaxLoss": 2009,
      |               "taxableProfit": 2008,
      |               "taxableProfitAfterIncomeTaxLossesDeduction": 2013,
      |               "lossClaimsSummary": {
      |                  "totalBroughtForwardIncomeTaxLosses": 2010,
      |                  "broughtForwardIncomeTaxLossesUsed": 2012,
      |                  "carrySidewaysIncomeTaxLossesUsed": 2014,
      |                  "totalIncomeTaxLossesCarriedForward": 2011,
      |                  "broughtForwardCarrySidewaysIncomeTaxLossesUsed": 2020
      |               },
      |               "lossClaimsDetail": {
      |                  "lossesBroughtForward": [
      |                     {
      |                        "taxYearLossIncurred": "2017-18",
      |                        "currentLossValue": 20101,
      |                        "mtdLoss": true
      |                     }
      |                  ],
      |                  "resultOfClaimsApplied": [
      |                     {
      |                        "claimId": "CCIS12345678903",
      |                        "originatingClaimId": "000000000000213",
      |                        "taxYearClaimMade": "2017-18",
      |                        "claimType": "carry-sideways-fhl",
      |                        "mtdLoss": true,
      |                        "taxYearLossIncurred": "2017-18",
      |                        "lossAmountUsed": 20101,
      |                        "remainingLossValue": 20201
      |                     }
      |                  ],
      |                  "defaultCarriedForwardLosses": [
      |                     {
      |                        "taxYearLossIncurred": "2017-18",
      |                        "currentLossValue": 201
      |                     }
      |                  ],
      |                  "claimsNotApplied": [
      |                     {
      |                        "claimId": "CCIS12345678923",
      |                        "taxYearClaimMade": "2017-18",
      |                        "claimType": "carry-sideways-fhl"
      |                     }
      |                  ]
      |               },
      |               "bsas": {
      |                  "bsasId": "10000003",
      |                  "applied": false
      |               }
      |            },
      |            "eeaPropertyFhl": {
      |               "totalIncome": 5001.11,
      |               "totalExpenses": 5002.11,
      |               "netProfit": 5003.11,
      |               "netLoss": 5004.11,
      |               "totalAdditions": 5005.11,
      |               "totalDeductions": 5006.11,
      |               "adjustedIncomeTaxLoss": 5009,
      |               "taxableProfit": 5008,
      |               "taxableProfitAfterIncomeTaxLossesDeduction": 5013,
      |               "lossClaimsSummary": {
      |                  "lossForCSFHL": 5011,
      |                  "totalBroughtForwardIncomeTaxLosses": 5010,
      |                  "broughtForwardIncomeTaxLossesUsed": 5012,
      |                  "totalIncomeTaxLossesCarriedForward": 5014
      |               },
      |               "lossClaimsDetail": {
      |                  "lossesBroughtForward": [
      |                     {
      |                        "taxYearLossIncurred": "2017-18",
      |                        "currentLossValue": 50101,
      |                        "mtdLoss": false
      |                     }
      |                  ],
      |                  "resultOfClaimsApplied": [
      |                     {
      |                        "claimId": "CCIS12345678905",
      |                        "taxYearClaimMade": "2017-18",
      |                        "claimType": "carry-forward",
      |                        "mtdLoss": false,
      |                        "taxYearLossIncurred": "2017-18",
      |                        "lossAmountUsed": 50101,
      |                        "remainingLossValue": 50201
      |                     }
      |                  ],
      |                  "defaultCarriedForwardLosses": [
      |                     {
      |                        "taxYearLossIncurred": "2017-18",
      |                        "currentLossValue": 501
      |                     }
      |                  ]
      |               },
      |               "bsas": {
      |                  "bsasId": "87654321",
      |                  "applied": false
      |               }
      |            },
      |            "foreignProperty": {
      |               "totalIncome": 6001.11,
      |               "totalExpenses": 6002.11,
      |               "netProfit": 6003.11,
      |               "netLoss": 6004.11,
      |               "totalAdditions": 6005.11,
      |               "totalDeductions": 6006.11,
      |               "accountingAdjustments": 6007.11,
      |               "adjustedIncomeTaxLoss": 6009,
      |               "taxableProfit": 6008,
      |               "taxableProfitAfterIncomeTaxLossesDeduction": 6013,
      |               "lossClaimsSummary": {
      |                  "totalBroughtForwardIncomeTaxLosses": 6010,
      |                  "broughtForwardIncomeTaxLossesUsed": 6012,
      |                  "carrySidewaysIncomeTaxLossesUsed": 6014,
      |                  "totalIncomeTaxLossesCarriedForward": 6011,
      |                  "broughtForwardCarrySidewaysIncomeTaxLossesUsed": 6020
      |               },
      |               "lossClaimsDetail": {
      |                  "lossesBroughtForward": [
      |                     {
      |                        "taxYearLossIncurred": "2017-18",
      |                        "currentLossValue": 60101,
      |                        "mtdLoss": false
      |                     }
      |                  ],
      |                  "resultOfClaimsApplied": [
      |                     {
      |                        "claimId": "CCIS12345678906",
      |                        "originatingClaimId": "000000000000216",
      |                        "taxYearClaimMade": "2017-18",
      |                        "claimType": "carry-sideways-fhl",
      |                        "mtdLoss": false,
      |                        "taxYearLossIncurred": "2017-18",
      |                        "lossAmountUsed": 60101,
      |                        "remainingLossValue": 60201
      |                     }
      |                  ],
      |                  "defaultCarriedForwardLosses": [
      |                     {
      |                        "taxYearLossIncurred": "2017-18",
      |                        "currentLossValue": 601
      |                     }
      |                  ],
      |                  "claimsNotApplied": [
      |                     {
      |                        "claimId": "CCIS12345678926",
      |                        "taxYearClaimMade": "2017-18",
      |                        "claimType": "carry-sideways-fhl"
      |                     }
      |                  ]
      |               },
      |               "bsas": {
      |                  "bsasId": "10000006",
      |                  "applied": false
      |               }
      |            }
      |         }
      |      },
      |      "savingsAndGains": {
      |         "incomeReceived": 7012,
      |         "taxableIncome": 7014,
      |         "totalOfAllGains": 7015,
      |         "totalUkSavingsAndSecurities": 7016,
      |         "ukSavings": [
      |            {
      |               "savingsAccountId": "SAVKB1UVwUTBQGJ",
      |               "savingsAccountName": "UK Savings Account ONE",
      |               "grossIncome": 90101.11,
      |               "netIncome": 90201.11,
      |               "taxDeducted": 90301.11
      |            },
      |            {
      |               "savingsAccountId": "SAVKB2UVwUTBQGJ",
      |               "savingsAccountName": "UK Savings Account TWO",
      |               "grossIncome": 90102.11,
      |               "netIncome": 90202.11,
      |               "taxDeducted": 90302.11
      |            }
      |         ],
      |         "ukSecurities": [
      |            {
      |               "grossIncome": 11101.11,
      |               "netIncome": 11201.11,
      |               "taxDeducted": 11301.11
      |            }
      |         ],
      |         "totalGainsWithNoTaxPaidAndVoidedIsa": 7017,
      |         "totalForeignGainsOnLifePoliciesNoTaxPaid": 7018,
      |         "totalForeignSavingsAndGainsIncome": 7019
      |      },
      |      "dividends": {
      |         "incomeReceived": 7020,
      |         "taxableIncome": 7022,
      |         "totalUkDividends": 7024,
      |         "totalForeignDividends": 7026
      |      },
      |      "lumpSums": {
      |         "incomeReceived": 8010,
      |         "taxableIncome": 8020
      |      },
      |      "gainsOnLifePolicies": {
      |         "incomeReceived": 9010,
      |         "taxableIncome": 9020,
      |         "totalUkGainsWithTaxPaid": 9030,
      |         "totalForeignGainsOnLifePoliciesWithTaxPaid": 9040
      |      }
      |   }
      |}
    """.stripMargin
  )
}