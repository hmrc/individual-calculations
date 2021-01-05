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

package v1.models.response.getCalculation.taxableIncome.detail.payPensionsProfit

import play.api.libs.json.{JsError, JsObject, JsValue, Json}
import support.UnitSpec
import v1.fixtures.getCalculation.taxableIncome.{TaxableIncomeJsonFixture, TaxableIncomeModelsFixture}

class BusinessProfitAndLossSpec extends UnitSpec {

  "BusinessProfitAndLoss" when {
    "reads from valid JSON" should {
      "produce the expected BusinessProfitAndLoss object" in {
        val desJson: JsValue = TaxableIncomeJsonFixture.desJson
        desJson.as[BusinessProfitAndLoss] shouldBe TaxableIncomeModelsFixture.businessProfitAndLossModel
      }
    }

    "read from valid JSON having only SelfEmployment details" should {
      "produce the expected BusinessProfitAndLoss object" in {
        val model: BusinessProfitAndLoss = TaxableIncomeModelsFixture.businessProfitAndLossModel.copy(
          ukPropertyFhl = None, ukPropertyNonFhl = None, eeaPropertyFhl = None, foreignProperty = None
        )

        val desJson: JsValue = Json.parse(
          """
            |{
            |   "inputs":{
            |      "annualAdjustments":[
            |         {
            |            "incomeSourceId":"AaIS12345678910",
            |            "incomeSourceType":"01",
            |            "ascId":"10000001",
            |            "receivedDateTime":"2019-07-17T08:15:28Z",
            |            "applied":true
            |         },
            |         {
            |            "incomeSourceId":"AbIS12345678910",
            |            "incomeSourceType":"01",
            |            "ascId":"10000002",
            |            "receivedDateTime":"2019-07-17T08:15:28Z",
            |            "applied":true
            |         }
            |      ],
            |      "lossesBroughtForward":[
            |         {
            |            "lossId":"LLIS12345678901",
            |            "incomeSourceId":"AaIS12345678910",
            |            "incomeSourceType":"01",
            |            "submissionTimestamp":"2019-07-13T07:51:43Z",
            |            "lossType":"income",
            |            "taxYearLossIncurred":2018,
            |            "currentLossValue":10101,
            |            "mtdLoss":true
            |         },
            |         {
            |            "lossId":"LLIS12345678902",
            |            "incomeSourceId":"AbIS12345678910",
            |            "incomeSourceType":"01",
            |            "submissionTimestamp":"2019-07-13T07:51:43Z",
            |            "lossType":"income",
            |            "taxYearLossIncurred":2018,
            |            "currentLossValue":10102,
            |            "mtdLoss":true
            |         }
            |      ]
            |   },
            |   "calculation":{
            |      "businessProfitAndLoss":[
            |         {
            |            "incomeSourceId":"AaIS12345678910",
            |            "incomeSourceType":"01",
            |            "incomeSourceName":"Self-Employment Business ONE",
            |            "totalIncome":100101.11,
            |            "totalExpenses":100201.11,
            |            "netProfit":100301.11,
            |            "netLoss":100401.11,
            |            "totalAdditions":100501.11,
            |            "totalDeductions":100601.11,
            |            "accountingAdjustments":100701.11,
            |            "taxableProfit":100801,
            |            "adjustedIncomeTaxLoss":100901,
            |            "totalBroughtForwardIncomeTaxLosses":101001,
            |            "lossForCSFHL":101101,
            |            "broughtForwardIncomeTaxLossesUsed":101201,
            |            "taxableProfitAfterIncomeTaxLossesDeduction":101301,
            |            "totalIncomeTaxLossesCarriedForward":101601,
            |            "class4Loss":101501,
            |            "totalBroughtForwardClass4Losses":101701,
            |            "carrySidewaysIncomeTaxLossesUsed":101401,
            |            "broughtForwardClass4LossesUsed":101801,
            |            "carrySidewaysClass4LossesUsed":101901,
            |            "totalClass4LossesCarriedForward":101119
            |         },
            |         {
            |            "incomeSourceId":"AbIS12345678910",
            |            "incomeSourceType":"01",
            |            "incomeSourceName":"Self-Employment Business TWO",
            |            "totalIncome":100102.22,
            |            "totalExpenses":100202.22,
            |            "netProfit":100302.22,
            |            "netLoss":100402.22,
            |            "totalAdditions":100502.22,
            |            "totalDeductions":100602.22,
            |            "accountingAdjustments":100702.22,
            |            "taxableProfit":100802,
            |            "adjustedIncomeTaxLoss":100902,
            |            "totalBroughtForwardIncomeTaxLosses":101002,
            |            "lossForCSFHL":101102,
            |            "broughtForwardIncomeTaxLossesUsed":101202,
            |            "taxableProfitAfterIncomeTaxLossesDeduction":101302,
            |            "totalIncomeTaxLossesCarriedForward":101602,
            |            "class4Loss":101502,
            |            "totalBroughtForwardClass4Losses":101702,
            |            "carrySidewaysIncomeTaxLossesUsed":101402,
            |            "broughtForwardClass4LossesUsed":101802,
            |            "carrySidewaysClass4LossesUsed":101902,
            |            "totalClass4LossesCarriedForward":101392
            |         }
            |      ],
            |      "incomeSummaryTotals":{
            |         "totalSelfEmploymentProfit":6001,
            |         "totalPropertyProfit":6002,
            |         "totalFHLPropertyProfit":6003,
            |         "totalUKOtherPropertyProfit":6004,
            |         "totalForeignPropertyProfit":6005,
            |         "totalEeaFhlProfit": 6006,
            |         "totalEmploymentIncome": 6012
            |      },
            |      "taxCalculation":{
            |         "incomeTax":{
            |            "totalIncomeReceivedFromAllSources":7001,
            |            "totalAllowancesAndDeductions":7002,
            |            "totalTaxableIncome":100,
            |            "payPensionsProfit":{
            |               "incomeReceived":7004,
            |               "allowancesAllocated":7005,
            |               "taxableIncome":7006,
            |               "incomeTaxAmount":7007.11,
            |               "taxBands":[
            |                  {
            |                     "name":"SSR",
            |                     "rate":31,
            |                     "bandLimit":7008,
            |                     "apportionedBandLimit":7009,
            |                     "income":7010,
            |                     "taxAmount":7011.11
            |                  }
            |               ]
            |            },
            |            "incomeTaxCharged":7028,
            |            "totalReliefs":7029,
            |            "incomeTaxDueAfterReliefs":7030.11,
            |            "incomeTaxDueAfterGiftAid":7031.11
            |         },
            |         "totalIncomeTaxNicsCharged":5019.11,
            |         "totalTaxDeducted":5020,
            |         "totalIncomeTaxAndNicsDue":5021.11
            |      },
            |      "lossesAndClaims":{
            |         "resultOfClaimsApplied":[
            |            {
            |               "claimId":"CCIS12345678901",
            |               "originatingClaimId":"000000000000211",
            |               "incomeSourceId":"AaIS12345678910",
            |               "incomeSourceType":"01",
            |               "taxYearClaimMade":2018,
            |               "claimType":"CF",
            |               "mtdLoss":true,
            |               "taxYearLossIncurred":2018,
            |               "lossAmountUsed":10101,
            |               "remainingLossValue":10201,
            |               "lossType":"income"
            |            },
            |            {
            |               "claimId":"CCIS12345678902",
            |               "originatingClaimId":"000000000000212",
            |               "incomeSourceId":"AbIS12345678910",
            |               "incomeSourceType":"01",
            |               "taxYearClaimMade":2018,
            |               "claimType":"CSGI",
            |               "mtdLoss":true,
            |               "taxYearLossIncurred":2018,
            |               "lossAmountUsed":10102,
            |               "remainingLossValue":10202,
            |               "lossType":"income"
            |            }
            |         ],
            |         "unclaimedLosses":[
            |            {
            |               "incomeSourceId":"AaIS12345678910",
            |               "incomeSourceType":"01",
            |               "taxYearLossIncurred":2018,
            |               "currentLossValue":1001,
            |               "expires":2020,
            |               "lossType":"income"
            |            },
            |            {
            |               "incomeSourceId":"AbIS12345678910",
            |               "incomeSourceType":"01",
            |               "taxYearLossIncurred":2018,
            |               "currentLossValue":1002,
            |               "expires":2020,
            |               "lossType":"income"
            |            }
            |         ],
            |         "carriedForwardLosses":[
            |            {
            |               "claimId":"CCIS12345678901",
            |               "originatingClaimId":"000000000000211",
            |               "incomeSourceId":"AaIS12345678910",
            |               "incomeSourceType":"01",
            |               "claimType":"CF",
            |               "taxYearClaimMade":2019,
            |               "taxYearLossIncurred":2018,
            |               "currentLossValue":1001,
            |               "lossType":"income"
            |            },
            |            {
            |               "claimId":"CCIS12345678902",
            |               "originatingClaimId":"000000000000212",
            |               "incomeSourceId":"AbIS12345678910",
            |               "incomeSourceType":"01",
            |               "claimType":"CF",
            |               "taxYearClaimMade":2019,
            |               "taxYearLossIncurred":2018,
            |               "currentLossValue":1002,
            |               "lossType":"income"
            |            }
            |         ],
            |         "claimsNotApplied":[
            |            {
            |               "claimId":"CCIS12345678921",
            |               "incomeSourceId":"AaIS12345678910",
            |               "incomeSourceType":"01",
            |               "taxYearClaimMade":2018,
            |               "claimType":"CF"
            |            },
            |            {
            |               "claimId":"CCIS12345678922",
            |               "incomeSourceId":"AbIS12345678910",
            |               "incomeSourceType":"01",
            |               "taxYearClaimMade":2018,
            |               "claimType":"CSGI"
            |            }
            |         ]
            |      }
            |   }
            |}
          """.stripMargin
        )

        desJson.as[BusinessProfitAndLoss] shouldBe model
      }
    }

    "read from valid JSON having only UkPropertyFhl details" should {
      "produce the expected BusinessProfitAndLoss object" in {
        val model: BusinessProfitAndLoss = TaxableIncomeModelsFixture.businessProfitAndLossModel.copy(
          selfEmployments = None, ukPropertyNonFhl = None, eeaPropertyFhl = None, foreignProperty = None
        )

        val desJson: JsValue = Json.parse(
          """
            |{
            |   "inputs":{
            |      "annualAdjustments":[
            |         {
            |            "incomeSourceId":"AdIS12345678910",
            |            "incomeSourceType":"04",
            |            "ascId":"12345678",
            |            "receivedDateTime":"2019-07-17T08:15:28Z",
            |            "applied":true
            |         }
            |      ],
            |      "lossesBroughtForward":[
            |         {
            |            "lossId":"LLIS12345678904",
            |            "incomeSourceId":"AdIS12345678910",
            |            "incomeSourceType":"04",
            |            "submissionTimestamp":"2019-07-13T07:51:43Z",
            |            "lossType":"income",
            |            "taxYearLossIncurred":2018,
            |            "currentLossValue":40101,
            |            "mtdLoss":true
            |         }
            |      ]
            |   },
            |   "calculation":{
            |      "businessProfitAndLoss":[
            |         {
            |            "incomeSourceId":"AdIS12345678910",
            |            "incomeSourceType":"04",
            |            "incomeSourceName":"UK Property FHL",
            |            "totalIncome":4001.11,
            |            "totalExpenses":4002.11,
            |            "netProfit":4003.11,
            |            "netLoss":4004.11,
            |            "totalAdditions":4005.11,
            |            "totalDeductions":4006.11,
            |            "accountingAdjustments":4007.11,
            |            "taxableProfit":4008,
            |            "adjustedIncomeTaxLoss":4009,
            |            "totalBroughtForwardIncomeTaxLosses":4010,
            |            "lossForCSFHL":4011,
            |            "broughtForwardIncomeTaxLossesUsed":4012,
            |            "taxableProfitAfterIncomeTaxLossesDeduction":4013,
            |            "totalIncomeTaxLossesCarriedForward":4014,
            |            "class4Loss":4015,
            |            "totalBroughtForwardClass4Losses":4016,
            |            "broughtForwardClass4LossesUsed":4017,
            |            "carrySidewaysClass4LossesUsed":4018,
            |            "totalClass4LossesCarriedForward":4019
            |         }
            |      ],
            |      "incomeSummaryTotals":{
            |         "totalSelfEmploymentProfit":6001,
            |         "totalPropertyProfit":6002,
            |         "totalFHLPropertyProfit":6003,
            |         "totalUKOtherPropertyProfit":6004,
            |         "totalForeignPropertyProfit":6005,
            |         "totalEeaFhlProfit": 6006,
            |         "totalEmploymentIncome": 6012
            |      },
            |      "taxCalculation":{
            |         "incomeTax":{
            |            "totalIncomeReceivedFromAllSources":7001,
            |            "totalAllowancesAndDeductions":7002,
            |            "totalTaxableIncome":100,
            |            "payPensionsProfit":{
            |               "incomeReceived":7004,
            |               "allowancesAllocated":7005,
            |               "taxableIncome":7006,
            |               "incomeTaxAmount":7007.11,
            |               "taxBands":[
            |                  {
            |                     "name":"SSR",
            |                     "rate":31,
            |                     "bandLimit":7008,
            |                     "apportionedBandLimit":7009,
            |                     "income":7010,
            |                     "taxAmount":7011.11
            |                  }
            |               ]
            |            },
            |            "incomeTaxCharged":7028,
            |            "totalReliefs":7029,
            |            "incomeTaxDueAfterReliefs":7030.11,
            |            "incomeTaxDueAfterGiftAid":7031.11
            |         },
            |         "totalIncomeTaxNicsCharged":5019.11,
            |         "totalTaxDeducted":5020,
            |         "totalIncomeTaxAndNicsDue":5021.11
            |      },
            |      "lossesAndClaims":{
            |         "resultOfClaimsApplied":[
            |            {
            |               "claimId":"CCIS12345678904",
            |               "originatingClaimId":"000000000000214",
            |               "incomeSourceId":"AdIS12345678910",
            |               "incomeSourceType":"04",
            |               "taxYearClaimMade":2018,
            |               "claimType":"CFCSGI",
            |               "mtdLoss":true,
            |               "taxYearLossIncurred":2018,
            |               "lossAmountUsed":40101,
            |               "remainingLossValue":40201,
            |               "lossType":"income"
            |            }
            |         ],
            |         "defaultCarriedForwardLosses":[
            |            {
            |               "incomeSourceId":"AdIS12345678910",
            |               "incomeSourceType":"04",
            |               "taxYearLossIncurred":2018,
            |               "currentLossValue":401
            |            }
            |         ]
            |      }
            |   }
            |}
          """.stripMargin
        )

        desJson.as[BusinessProfitAndLoss] shouldBe model
      }
    }

    "read from valid JSON having only UkPropertyNonFhl details" should {
      "produce the expected BusinessProfitAndLoss object" in {
        val model: BusinessProfitAndLoss = TaxableIncomeModelsFixture.businessProfitAndLossModel.copy(
          selfEmployments = None, ukPropertyFhl = None, eeaPropertyFhl = None, foreignProperty = None
        )

        val desJson: JsValue = Json.parse(
          """
            |{
            |   "inputs":{
            |      "annualAdjustments":[
            |         {
            |            "incomeSourceId":"AcIS12345678910",
            |            "incomeSourceType":"02",
            |            "ascId":"10000003",
            |            "receivedDateTime":"2019-07-17T08:15:28Z",
            |            "applied":false
            |         }
            |      ],
            |      "lossesBroughtForward":[
            |         {
            |            "lossId":"LLIS12345678903",
            |            "incomeSourceId":"AcIS12345678910",
            |            "incomeSourceType":"02",
            |            "submissionTimestamp":"2019-07-13T07:51:43Z",
            |            "lossType":"income",
            |            "taxYearLossIncurred":2018,
            |            "currentLossValue":20101,
            |            "mtdLoss":true
            |         }
            |      ]
            |   },
            |   "calculation":{
            |      "businessProfitAndLoss":[
            |         {
            |            "incomeSourceId":"AcIS12345678910",
            |            "incomeSourceType":"02",
            |            "incomeSourceName":"UK Property Non-FHL",
            |            "totalIncome":2001.11,
            |            "totalExpenses":2002.11,
            |            "netProfit":2003.11,
            |            "netLoss":2004.11,
            |            "totalAdditions":2005.11,
            |            "totalDeductions":2006.11,
            |            "accountingAdjustments":2007.11,
            |            "taxableProfit":2008,
            |            "adjustedIncomeTaxLoss":2009,
            |            "totalBroughtForwardIncomeTaxLosses":2010,
            |            "lossForCSFHL":2011,
            |            "broughtForwardIncomeTaxLossesUsed":2012,
            |            "taxableProfitAfterIncomeTaxLossesDeduction":2013,
            |            "totalIncomeTaxLossesCarriedForward":2011,
            |            "broughtForwardCarrySidewaysIncomeTaxLossesUsed":2020,
            |            "class4Loss":2015,
            |            "totalBroughtForwardClass4Losses":2016,
            |            "broughtForwardClass4LossesUsed":2017,
            |            "carrySidewaysIncomeTaxLossesUsed":2014,
            |            "totalClass4LossesCarriedForward":2019
            |         }
            |      ],
            |      "incomeSummaryTotals":{
            |         "totalSelfEmploymentProfit":6001,
            |         "totalPropertyProfit":6002,
            |         "totalFHLPropertyProfit":6003,
            |         "totalUKOtherPropertyProfit":6004,
            |         "totalForeignPropertyProfit":6005,
            |         "totalEeaFhlProfit": 6006,
            |         "totalEmploymentIncome": 6012
            |      },
            |      "taxCalculation":{
            |         "incomeTax":{
            |            "totalIncomeReceivedFromAllSources":7001,
            |            "totalAllowancesAndDeductions":7002,
            |            "totalTaxableIncome":100,
            |            "payPensionsProfit":{
            |               "incomeReceived":7004,
            |               "allowancesAllocated":7005,
            |               "taxableIncome":7006,
            |               "incomeTaxAmount":7007.11,
            |               "taxBands":[
            |                  {
            |                     "name":"SSR",
            |                     "rate":31,
            |                     "bandLimit":7008,
            |                     "apportionedBandLimit":7009,
            |                     "income":7010,
            |                     "taxAmount":7011.11
            |                  }
            |               ]
            |            },
            |            "incomeTaxCharged":7028,
            |            "totalReliefs":7029,
            |            "incomeTaxDueAfterReliefs":7030.11,
            |            "incomeTaxDueAfterGiftAid":7031.11
            |         },
            |         "totalIncomeTaxNicsCharged":5019.11,
            |         "totalTaxDeducted":5020,
            |         "totalIncomeTaxAndNicsDue":5021.11
            |      },
            |      "lossesAndClaims":{
            |         "resultOfClaimsApplied":[
            |            {
            |               "claimId":"CCIS12345678903",
            |               "originatingClaimId":"000000000000213",
            |               "incomeSourceId":"AcIS12345678910",
            |               "incomeSourceType":"02",
            |               "taxYearClaimMade":2018,
            |               "claimType":"CSFHL",
            |               "mtdLoss":true,
            |               "taxYearLossIncurred":2018,
            |               "lossAmountUsed":20101,
            |               "remainingLossValue":20201,
            |               "lossType":"income"
            |            }
            |         ],
            |         "defaultCarriedForwardLosses": [
            |            {
            |               "incomeSourceId":"AcIS12345678910",
            |               "incomeSourceType":"02",
            |               "taxYearLossIncurred":2018,
            |               "currentLossValue":201
            |            }
            |         ],
            |         "claimsNotApplied":[
            |            {
            |               "claimId":"CCIS12345678923",
            |               "incomeSourceId":"AcIS12345678910",
            |               "incomeSourceType":"02",
            |               "taxYearClaimMade":2018,
            |               "claimType":"CSFHL"
            |            }
            |         ]
            |      }
            |   }
            |}
          """.stripMargin
        )

        desJson.as[BusinessProfitAndLoss] shouldBe model
      }
    }

    "read from valid JSON having only EeaPropertyFhl details" should {
      "produce the expected BusinessProfitAndLoss object" in {
        val model: BusinessProfitAndLoss = TaxableIncomeModelsFixture.businessProfitAndLossModel.copy(
          selfEmployments = None, ukPropertyFhl = None, ukPropertyNonFhl = None, foreignProperty = None
        )

        val desJson: JsValue = Json.parse(
          """
            |{
            |   "inputs":{
            |      "annualAdjustments":[
            |         {
            |            "incomeSourceId":"AeIS12345678910",
            |            "incomeSourceType":"03",
            |            "ascId":"87654321",
            |            "receivedDateTime":"2019-07-17T08:15:28Z",
            |            "applied":false
            |         }
            |      ],
            |      "lossesBroughtForward":[
            |         {
            |            "lossId":"LLIS12345678905",
            |            "incomeSourceId":"AeIS12345678910",
            |            "incomeSourceType":"03",
            |            "submissionTimestamp":"2019-07-13T07:51:43Z",
            |            "lossType":"income",
            |            "taxYearLossIncurred":2018,
            |            "currentLossValue":50101,
            |            "mtdLoss":false
            |         }
            |      ]
            |   },
            |   "calculation":{
            |      "businessProfitAndLoss":[
            |         {
            |            "incomeSourceId":"AeIS12345678910",
            |            "incomeSourceType":"03",
            |            "incomeSourceName":"EEA Property FHL",
            |            "totalIncome":5001.11,
            |            "totalExpenses":5002.11,
            |            "netProfit":5003.11,
            |            "netLoss":5004.11,
            |            "totalAdditions":5005.11,
            |            "totalDeductions":5006.11,
            |            "accountingAdjustments":5007.11,
            |            "taxableProfit":5008,
            |            "adjustedIncomeTaxLoss":5009,
            |            "totalBroughtForwardIncomeTaxLosses":5010,
            |            "lossForCSFHL":5011,
            |            "broughtForwardIncomeTaxLossesUsed":5012,
            |            "taxableProfitAfterIncomeTaxLossesDeduction":5013,
            |            "totalIncomeTaxLossesCarriedForward":5014,
            |            "class4Loss":5015,
            |            "totalBroughtForwardClass4Losses":5016,
            |            "broughtForwardClass4LossesUsed":5017,
            |            "carrySidewaysClass4LossesUsed":5018,
            |            "totalClass4LossesCarriedForward":5019
            |         }
            |      ],
            |      "incomeSummaryTotals":{
            |         "totalSelfEmploymentProfit":6001,
            |         "totalPropertyProfit":6002,
            |         "totalFHLPropertyProfit":6003,
            |         "totalUKOtherPropertyProfit":6004,
            |         "totalForeignPropertyProfit":6005,
            |         "totalEeaFhlProfit": 6006,
            |         "totalEmploymentIncome": 6012
            |      },
            |      "taxCalculation":{
            |         "incomeTax":{
            |            "totalIncomeReceivedFromAllSources":7001,
            |            "totalAllowancesAndDeductions":7002,
            |            "totalTaxableIncome":100,
            |            "payPensionsProfit":{
            |               "incomeReceived":7004,
            |               "allowancesAllocated":7005,
            |               "taxableIncome":7006,
            |               "incomeTaxAmount":7007.11,
            |               "taxBands":[
            |                  {
            |                     "name":"SSR",
            |                     "rate":31,
            |                     "bandLimit":7008,
            |                     "apportionedBandLimit":7009,
            |                     "income":7010,
            |                     "taxAmount":7011.11
            |                  }
            |               ]
            |            },
            |            "incomeTaxCharged":7028,
            |            "totalReliefs":7029,
            |            "incomeTaxDueAfterReliefs":7030.11,
            |            "incomeTaxDueAfterGiftAid":7031.11
            |         },
            |         "totalIncomeTaxNicsCharged":5019.11,
            |         "totalTaxDeducted":5020,
            |         "totalIncomeTaxAndNicsDue":5021.11
            |      },
            |      "lossesAndClaims":{
            |         "resultOfClaimsApplied":[
            |            {
            |               "claimId":"CCIS12345678905",
            |               "originatingClaimId":"000000000000215",
            |               "incomeSourceId":"AeIS12345678910",
            |               "incomeSourceType":"03",
            |               "taxYearClaimMade":2018,
            |               "claimType":"CF",
            |               "mtdLoss":false,
            |               "taxYearLossIncurred":2018,
            |               "lossAmountUsed":50101,
            |               "remainingLossValue":50201,
            |               "lossType":"income"
            |            }
            |         ],
            |         "defaultCarriedForwardLosses":[
            |            {
            |               "incomeSourceId":"AeIS12345678910",
            |               "incomeSourceType":"03",
            |               "taxYearLossIncurred":2018,
            |               "currentLossValue":501
            |            }
            |         ]
            |      }
            |   }
            |}
          """.stripMargin
        )

        desJson.as[BusinessProfitAndLoss] shouldBe model
      }
    }

    "read from valid JSON having only ForeignProperty details" should {
      "produce the expected BusinessProfitAndLoss object" in {
        val model: BusinessProfitAndLoss = TaxableIncomeModelsFixture.businessProfitAndLossModel.copy(
          selfEmployments = None, ukPropertyNonFhl = None, ukPropertyFhl = None, eeaPropertyFhl = None
        )

        val desJson: JsValue = Json.parse(
          """
            |{
            |   "inputs":{
            |      "annualAdjustments":[
            |         {
            |            "incomeSourceId":"AfIS12345678910",
            |            "incomeSourceType":"15",
            |            "ascId":"10000006",
            |            "receivedDateTime":"2019-07-17T08:15:28Z",
            |            "applied":false
            |         }
            |      ],
            |      "lossesBroughtForward":[
            |         {
            |            "lossId":"LLIS12345678906",
            |            "incomeSourceId":"AfIS12345678910",
            |            "incomeSourceType":"15",
            |            "submissionTimestamp":"2019-07-13T07:51:43Z",
            |            "lossType":"income",
            |            "taxYearLossIncurred":2018,
            |            "currentLossValue":60101,
            |            "mtdLoss":false
            |         }
            |      ]
            |   },
            |   "calculation":{
            |      "businessProfitAndLoss":[
            |         {
            |            "incomeSourceId":"AfIS12345678910",
            |            "incomeSourceType":"15",
            |            "incomeSourceName":"Foreign Property",
            |            "totalIncome":6001.11,
            |            "totalExpenses":6002.11,
            |            "netProfit":6003.11,
            |            "netLoss":6004.11,
            |            "totalAdditions":6005.11,
            |            "totalDeductions":6006.11,
            |            "accountingAdjustments":6007.11,
            |            "taxableProfit":6008,
            |            "adjustedIncomeTaxLoss":6009,
            |            "totalBroughtForwardIncomeTaxLosses":6010,
            |            "lossForCSFHL":6011,
            |            "broughtForwardIncomeTaxLossesUsed":6012,
            |            "taxableProfitAfterIncomeTaxLossesDeduction":6013,
            |            "totalIncomeTaxLossesCarriedForward":6011,
            |            "broughtForwardCarrySidewaysIncomeTaxLossesUsed":6020,
            |            "class4Loss":6015,
            |            "totalBroughtForwardClass4Losses":6016,
            |            "broughtForwardClass4LossesUsed":6017,
            |            "carrySidewaysIncomeTaxLossesUsed":6014,
            |            "totalClass4LossesCarriedForward":6019
            |         }
            |      ],
            |      "incomeSummaryTotals":{
            |         "totalSelfEmploymentProfit":6001,
            |         "totalPropertyProfit":6002,
            |         "totalFHLPropertyProfit":6003,
            |         "totalUKOtherPropertyProfit":6004,
            |         "totalForeignPropertyProfit":6005,
            |         "totalEeaFhlProfit": 6006,
            |         "totalEmploymentIncome": 6012
            |      },
            |      "taxCalculation":{
            |         "incomeTax":{
            |            "totalIncomeReceivedFromAllSources":7001,
            |            "totalAllowancesAndDeductions":7002,
            |            "totalTaxableIncome":100,
            |            "payPensionsProfit":{
            |               "incomeReceived":7004,
            |               "allowancesAllocated":7005,
            |               "taxableIncome":7006,
            |               "incomeTaxAmount":7007.11,
            |               "taxBands":[
            |                  {
            |                     "name":"SSR",
            |                     "rate":31,
            |                     "bandLimit":7008,
            |                     "apportionedBandLimit":7009,
            |                     "income":7010,
            |                     "taxAmount":7011.11
            |                  }
            |               ]
            |            },
            |            "incomeTaxCharged":7028,
            |            "totalReliefs":7029,
            |            "incomeTaxDueAfterReliefs":7030.11,
            |            "incomeTaxDueAfterGiftAid":7031.11
            |         },
            |         "totalIncomeTaxNicsCharged":5019.11,
            |         "totalTaxDeducted":5020,
            |         "totalIncomeTaxAndNicsDue":5021.11
            |      },
            |      "lossesAndClaims":{
            |         "resultOfClaimsApplied":[
            |            {
            |               "claimId":"CCIS12345678906",
            |               "originatingClaimId":"000000000000216",
            |               "incomeSourceId":"AfIS12345678910",
            |               "incomeSourceType":"15",
            |               "taxYearClaimMade":2018,
            |               "claimType":"CSFHL",
            |               "mtdLoss":false,
            |               "taxYearLossIncurred":2018,
            |               "lossAmountUsed":60101,
            |               "remainingLossValue":60201,
            |               "lossType":"income"
            |            }
            |         ],
            |         "defaultCarriedForwardLosses": [
            |            {
            |               "incomeSourceId":"AfIS12345678910",
            |               "incomeSourceType":"15",
            |               "taxYearLossIncurred":2018,
            |               "currentLossValue":601
            |            }
            |         ],
            |         "claimsNotApplied":[
            |            {
            |               "claimId":"CCIS12345678926",
            |               "incomeSourceId":"AfIS12345678910",
            |               "incomeSourceType":"15",
            |               "taxYearClaimMade":2018,
            |               "claimType":"CSFHL"
            |            }
            |         ]
            |      }
            |   }
            |}
          """.stripMargin
        )

        desJson.as[BusinessProfitAndLoss] shouldBe model
      }
    }

    "read from empty JSON" should {
      "produce an empty BusinessProfitAndLoss object" in {
        val emptyJson: JsValue = JsObject.empty
        emptyJson.as[BusinessProfitAndLoss] shouldBe BusinessProfitAndLoss.empty
      }
    }

    "read from invalid JSON" should {
      "produce a JsError" in {
        val invalidDesJson: JsValue = Json.parse(
          """
            |{
            |   "calculation": {
            |      "businessProfitAndLoss": [
            |         {
            |            "incomeSourceType": "04",
            |            "totalIncome": "no"
            |         }
            |      ]
            |   }
            |}
          """.stripMargin
        )

        invalidDesJson.validate[BusinessProfitAndLoss] shouldBe a[JsError]
      }
    }

    "written to JSON" should {
      "produce the expected JsObject" in {
        val mtdJson: JsValue = (TaxableIncomeJsonFixture.mtdJson \ "detail" \ "payPensionsProfit" \
          "businessProfitAndLoss").get
        Json.toJson(TaxableIncomeModelsFixture.businessProfitAndLossModel) shouldBe mtdJson
      }
    }
  }
}
