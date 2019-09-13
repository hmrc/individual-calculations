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
package v1.models.response.getCalculation.taxableIncome.detail

import play.api.libs.json._
import support.UnitSpec
import v1.models.response.getCalculation.taxableIncome.detail.selfEmployments.SelfEmployments
import v1.models.response.getCalculation.taxableIncome.detail.ukProperty.{UkPropertyFhl, UkPropertyNonFhl}

class CalculationDetailSpec extends UnitSpec {
  val desJson: JsValue = Json.parse("""{
      |    "calculation" : {
      |    "taxCalculation" : {
      |      "incomeTax" : {
      |         "payPensionsProfit" : {
      |             "incomeReceived" : 1,
      |             "taxableIncome" : 2
      |             },
      |         "savingsAndGains": {
      |           "incomeReceived" : 392,
      |           "taxableIncome": 3920
      |            },
      |        "dividends" : {
      |          "incomeReceived":100,
      |          "taxableIncome":200
      |          }
      |        }
      |      },
      |  "incomeSummaryTotals" : {
      |     "totalSelfEmploymentProfit" : 3,
      |     "totalPropertyProfit" : 4,
      |     "totalFHLPropertyProfit" : 5,
      |     "totalUKOtherPropertyProfit" : 6
      |     },
      |   "savingsAndGainsIncome" : [
      |   {
      |     "incomeSourceId":"anId",
      |     "incomeSourceName":"aName",
      |     "grossIncome":300.1,
      |     "netIncome": 12.3,
      |     "taxDeducted": 456.3
      |     },
      |    {
      |     "incomeSourceId":"anotherId",
      |     "incomeSourceName":"anotherName",
      |     "grossIncome":300.12,
      |     "netIncome": 12.33,
      |     "taxDeducted": 456.34
      |     }
      |     ]
      |    },
      |    "selfEmployments" : [
      |      {"param":"value"},
      |      {"param":"value2"},
      |      {"param":"value3"}
      |    ],
      |    "ukPropertyFhl" : [
      |      {"param":"value"},
      |      {"param":"value2"},
      |      {"param":"value3"}
      |    ],
      |    "ukPropertyNonFhl" : [
      |      {"param":"value"},
      |      {"param":"value2"},
      |      {"param":"value3"}
      |    ]
      |}""".stripMargin)

  val emptyJson: JsObject = JsObject.empty

  val invalidDesJson: JsValue = Json.parse("""{
      |    "incomeReceived":100
      |}""".stripMargin)

  val writtenJson: JsValue = Json
    .parse("""{
        |   "payPensionsProfit":{
        |     "incomeReceived":1,
        |     "taxableIncome":2,
        |     "totalSelfEmploymentProfit":3,
        |     "totalPropertyProfit":4,
        |     "totalFHLPropertyProfit":5,
        |     "totalUKOtherPropertyProfit":6,
        |     "businessProfitAndLoss": {
        |       "selfEmployments":[
        |         {"param":"value"},
        |         {"param":"value2"},
        |         {"param":"value3"}
        |         ],
        |     "ukPropertyFhl":[
        |         {"param":"value"},
        |         {"param":"value2"},
        |         {"param":"value3"}
        |       ],
        |     "ukPropertyNonFhl":[
        |         {"param":"value"},
        |         {"param":"value2"},
        |         {"param":"value3"}
        |       ]
        |     }
        |   },
        | "savingsAndGains": {
        |     "incomeReceived":392,
        |     "taxableIncome":3920,
        |   "savings":[
        |     {
        |       "savingsAccountId":"anId",
        |       "savingsAccountName":"aName",
        |       "grossIncome":300.1,
        |       "netIncome":12.3,
        |       "taxDeducted":456.3
        |     },
        |     {
        |     "savingsAccountId":"anotherId",
        |     "savingsAccountName":"anotherName",
        |     "grossIncome":300.12,"netIncome":12.33,
        |     "taxDeducted":456.34
        |       }
        |     ]
        |   },
        |   "dividends":{
        |   "incomeReceived":100,
        |   "taxableIncome":200
        |     }
        |   }""".stripMargin)

  val selfEmployments: Seq[SelfEmployments]                = Seq(SelfEmployments("value"), SelfEmployments("value2"), SelfEmployments("value3"))
  val ukPropertyFhl: Seq[UkPropertyFhl]                    = Seq(UkPropertyFhl("value"), UkPropertyFhl("value2"), UkPropertyFhl("value3"))
  val ukPropertyNonFhl: Seq[UkPropertyNonFhl]              = Seq(UkPropertyNonFhl("value"), UkPropertyNonFhl("value2"), UkPropertyNonFhl("value3"))
  val businessProfitAndLossResponse: BusinessProfitAndLoss = BusinessProfitAndLoss(Some(selfEmployments), Some(ukPropertyFhl), Some(ukPropertyNonFhl))
  val payPensionsProfitResponse: PayPensionsProfit         = PayPensionsProfit(1, 2, Some(3), Some(4), Some(5), Some(6), Some(businessProfitAndLossResponse))
  val savingsResponse                                      = Savings("anId", "aName", 300.1, Some(12.3), Some(456.3))
  val savingsResponse2                                     = Savings("anotherId", "anotherName", 300.12, Some(12.33), Some(456.34))
  val savingsAndGainsResponse                              = SavingsAndGains(392, 3920, Some(Seq(savingsResponse, savingsResponse2)))
  val dividendsResponse                                    = Dividends(100, 200)
  val detailResponse                                       = CalculationDetail(Some(payPensionsProfitResponse), Some(savingsAndGainsResponse), Some(dividendsResponse))
  val emptyDetailResponse                                  = CalculationDetail(None, None, None)

  "Detail" when {
    "read from valid Json" should {
      "return a JsSuccess" in {
        desJson.validate[CalculationDetail] shouldBe a[JsSuccess[_]]
      }
      "with the expected Detail object" in {
        desJson.as[CalculationDetail] shouldBe detailResponse
      }
    }
    "read from empty Json" should {
      "return a JsSuccess" in {
        emptyJson.validate[CalculationDetail] shouldBe a[JsSuccess[_]]
      }
      "with the expected Detail object" in {
        emptyJson.as[CalculationDetail] shouldBe emptyDetailResponse
      }
    }
    "written to Json" should {
      "return the expected JsObject" in {
        Json.toJson(detailResponse) shouldBe writtenJson
      }
    }
  }

}
