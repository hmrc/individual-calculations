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

import play.api.libs.json.{JsError, JsSuccess, JsValue, Json}
import support.UnitSpec
import v1.models.response.getCalculation.taxableIncome.detail.ukProperty.{UkPropertyFhl, UkPropertyNonFhl}
import v1.models.response.getCalculation.taxableIncome.detail.selfEmployments.SelfEmployments

class PayPensionsProfitSpec extends UnitSpec {
  val desJson: JsValue = Json.parse("""{
      |    "calculation" : {
      |       "taxCalculation" : {
      |         "incomeTax" : {
      |           "payPensionsProfit" : {
      |             "incomeReceived" : 1,
      |             "taxableIncome" : 2
      |             }
      |           }
      |         },
      |       "incomeSummaryTotals" : {
      |         "totalSelfEmploymentProfit" : 3,
      |         "totalPropertyProfit" : 4,
      |         "totalFHLPropertyProfit" : 5,
      |         "totalUKOtherPropertyProfit" : 6
      |         }
      |     },
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

  val desJsonWithoutSummaryTotals: JsValue = Json.parse("""{
      |    "calculation" : {
      |       "taxCalculation" : {
      |         "incomeTax" : {
      |           "payPensionsProfit" : {
      |             "incomeReceived" : 1,
      |             "taxableIncome" : 2
      |             }
      |           }
      |         }
      |     }
      |}""".stripMargin)

  val invalidDesJson: JsValue = Json.parse("""{
      |    "taxableIncome" : 2
      |}""".stripMargin)

  val writtenJson: JsValue = Json.parse("""{
      |    "incomeReceived" : 1,
      |    "taxableIncome" : 2,
      |    "totalSelfEmploymentProfit" : 3,
      |    "totalPropertyProfit" : 4,
      |    "totalFHLPropertyProfit" : 5,
      |    "totalUKOtherPropertyProfit" : 6,
      |    "businessProfitAndLoss" : {
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
      |    }
      |}""".stripMargin)

  val writtenJsonWithoutSummaryTotals: JsValue = Json.parse("""{
      |    "incomeReceived" : 1,
      |    "taxableIncome" : 2
      |}""".stripMargin)

  val selfEmployments: Seq[SelfEmployments]                = Seq(SelfEmployments("value"), SelfEmployments("value2"), SelfEmployments("value3"))
  val ukPropertyFhl: Seq[UkPropertyFhl]                    = Seq(UkPropertyFhl("value"), UkPropertyFhl("value2"), UkPropertyFhl("value3"))
  val ukPropertyNonFhl: Seq[UkPropertyNonFhl]              = Seq(UkPropertyNonFhl("value"), UkPropertyNonFhl("value2"), UkPropertyNonFhl("value3"))
  val businessProfitAndLossResponse: BusinessProfitAndLoss = BusinessProfitAndLoss(Some(selfEmployments), Some(ukPropertyFhl), Some(ukPropertyNonFhl))
  val payPensionsProfitResponse: PayPensionsProfit         = PayPensionsProfit(1, 2, Some(3), Some(4), Some(5), Some(6), Some(businessProfitAndLossResponse))

  val payPensionsProfitResponseWithoutSummaryTotals: PayPensionsProfit =
    PayPensionsProfit(1, 2, None, None, None, None, None)

  "PayPensionsProfit" when {
    "read from valid Json" should {
      "return a JsSuccess" in {
        desJson.validate[PayPensionsProfit] shouldBe a[JsSuccess[_]]
      }
      "with the expected BusinessProfitAndLoss object" in {
        desJson.as[PayPensionsProfit] shouldBe payPensionsProfitResponse
      }
    }
    "read from valid Json without income summary totals" should {
      "return a JsSuccess" in {
        desJsonWithoutSummaryTotals.validate[PayPensionsProfit] shouldBe a[JsSuccess[_]]
      }
      "with the expected BusinessProfitAndLoss object" in {
        desJsonWithoutSummaryTotals.as[PayPensionsProfit] shouldBe payPensionsProfitResponseWithoutSummaryTotals
      }
    }
    "read from invalid Json" should {
      "return a JsError" in {
        invalidDesJson.validate[PayPensionsProfit] shouldBe a[JsError]
      }
    }

    "written to Json" should {
      "return the expected JsObject" in {
        Json.toJson(payPensionsProfitResponse) shouldBe writtenJson
      }
    }
    "written to Json with empty fields" should {
      "return the expected JsObject" in {
        Json.toJson(payPensionsProfitResponseWithoutSummaryTotals) shouldBe writtenJsonWithoutSummaryTotals
      }
    }

  }

}
