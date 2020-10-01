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

package v1.models.response.getCalculation.taxableIncome.detail.payPensionsProfit.selfEmployment

import play.api.libs.json.{JsObject, JsValue, Json}
import support.UnitSpec
import v1.fixtures.getCalculation.taxableIncome.{TaxableIncomeJsonFixture, TaxableIncomeModelsFixture}

class SelfEmploymentSpec extends UnitSpec {

  "SelfEmployment" when {
    "read from valid JSON with 1 Self Employment item" should {
      "produce a sequence containing the expected SelfEmployment object" in {
        TaxableIncomeJsonFixture.oneSelfEmploymentOnlyDesJson.as[Seq[SelfEmployment]] shouldBe
          Seq(TaxableIncomeModelsFixture.selfEmploymentModel1)
      }
    }

    "read from valid JSON with 1 Self Employment item where Bsas and LossClaimsDetail are missing" should {
      "produce a sequence containing the expected SelfEmployment object without Bsas or LossClaimsDetail" in {
        val noBsasOrLossClaimsDesJson: JsValue = Json.parse(
          """
            |{
            |   "metadata":{
            |      "calculationId":"041f7e4d-87d9-4d4a-a296-3cfbdf92f7e2",
            |      "taxYear":2018,
            |      "requestedBy":"customer",
            |      "requestedTimestamp":"2019-02-15T09:35:15.094Z",
            |      "calculationReason":"customerRequest",
            |      "calculationTimestamp":"2019-02-15T09:35:15.094Z",
            |      "calculationType":"inYear",
            |      "intentToCrystallise":false,
            |      "crystallised":false,
            |      "crystallisationTimestamp":"2019-02-15T09:35:15.094Z",
            |      "periodFrom":"2018-01-01",
            |      "periodTo":"2019-01-01"
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
            |         }
            |      ],
            |      "savingsAndGainsIncome":[
            |         {
            |            "incomeSourceId":"SAVKB1UVwUTBQGJ",
            |            "incomeSourceType":"09",
            |            "incomeSourceName":"UK Savings Account ONE",
            |            "grossIncome":90101.11,
            |            "netIncome":90201.11,
            |            "taxDeducted":90301.11
            |         },
            |         {
            |            "incomeSourceId":"SAVKB2UVwUTBQGJ",
            |            "incomeSourceType":"09",
            |            "incomeSourceName":"UK Savings Account TWO",
            |            "grossIncome":90102.11,
            |            "netIncome":90202.11,
            |            "taxDeducted":90302.11
            |         },
            |         {
            |            "incomeSourceId":"SAVKB3UVwUTBQGJ",
            |            "incomeSourceType":"18",
            |            "incomeSourceName":"UK Securities Account ONE",
            |            "grossIncome":11101.11,
            |            "netIncome":11201.11,
            |            "taxDeducted":11301.11
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
            |         }
            |      }
            |   }
            |}
          """.stripMargin
        )

        noBsasOrLossClaimsDesJson.as[Seq[SelfEmployment]] shouldBe
          Seq(TaxableIncomeModelsFixture.selfEmploymentModel1.copy(
            lossClaimsDetail = None, bsas = None))
      }
    }

    "read from valid JSON with multiple Self Employment items" should {
      "produce a sequence containing the expected SelfEmployment objects" in {
        TaxableIncomeJsonFixture.desJson.as[Seq[SelfEmployment]] shouldBe
          Seq(
            TaxableIncomeModelsFixture.selfEmploymentModel1,
            TaxableIncomeModelsFixture.selfEmploymentModel2
          )
      }
    }

    "read from empty JSON" should {
      "return an empty sequence" in {
        val emptyJson: JsValue = JsObject.empty
        emptyJson.as[Seq[SelfEmployment]].isEmpty shouldBe true
      }
    }

    "written to JSON" should {
      "return the expected JsObject" in {
        val mtdJson: JsValue = (TaxableIncomeJsonFixture.mtdJson \ "detail" \ "payPensionsProfit" \
          "businessProfitAndLoss" \ "selfEmployments").get
        Json.toJson(Seq(
          TaxableIncomeModelsFixture.selfEmploymentModel1,
          TaxableIncomeModelsFixture.selfEmploymentModel2
        )) shouldBe mtdJson
      }
    }

  }
}
