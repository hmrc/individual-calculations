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

class BusinessProfitAndLossSpec extends UnitSpec {
  val desJson: JsValue = Json.parse("""{
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

  val selfEmploymentsOnlyDesJson: JsValue = Json.parse("""{
      |    "selfEmployments" : [
      |      {"param":"value"},
      |      {"param":"value2"},
      |      {"param":"value3"}
      |    ]
      |}""".stripMargin)

  val invalidDesJson: JsValue = Json.parse("""{
      |    "selfEmployments" : [
      |      {"params":"value"},
      |      {"param":"value2"},
      |      {"param":"value3"}
      |    ],
      |    "ukPropertyFhl" : [
      |      {"paramz":"value"},
      |      {"param":"value2"},
      |      {"param":"value3"}
      |    ],
      |    "ukPropertyNonFhl" : [
      |      {"paramo":"value"},
      |      {"param":"value2"},
      |      {"param":"value3"}
      |    ]
      |}""".stripMargin)

  val emptyJson: JsValue = JsObject.empty

  val selfEmployments: Seq[SelfEmployments]                = Seq(SelfEmployments("value"), SelfEmployments("value2"), SelfEmployments("value3"))
  val ukPropertyFhl: Seq[UkPropertyFhl]                    = Seq(UkPropertyFhl("value"), UkPropertyFhl("value2"), UkPropertyFhl("value3"))
  val ukPropertyNonFhl: Seq[UkPropertyNonFhl]              = Seq(UkPropertyNonFhl("value"), UkPropertyNonFhl("value2"), UkPropertyNonFhl("value3"))
  val businessProfitAndLossResponse: BusinessProfitAndLoss = BusinessProfitAndLoss(Some(selfEmployments), Some(ukPropertyFhl), Some(ukPropertyNonFhl))
  val selfEmploymentsOnlyResponse: BusinessProfitAndLoss   = businessProfitAndLossResponse.copy(ukPropertyFhl = None, ukPropertyNonFhl = None)
  val emptyResponse: BusinessProfitAndLoss                 = BusinessProfitAndLoss(None, None, None)

  "BusinessProfitAndLoss" when {
    "read from valid Json" should {
      "return a JsSuccess" in {
        desJson.validate[BusinessProfitAndLoss] shouldBe a[JsSuccess[_]]
      }
      "with the expected BusinessProfitAndLoss object" in {
        desJson.as[BusinessProfitAndLoss] shouldBe businessProfitAndLossResponse
      }
    }
    "read from valid Json with only selfEmployments" should {
      "return a JsSuccess" in {
        selfEmploymentsOnlyDesJson.validate[BusinessProfitAndLoss] shouldBe a[JsSuccess[_]]
      }
      "with the expected BusinessProfitAndLoss object" in {
        selfEmploymentsOnlyDesJson.as[BusinessProfitAndLoss] shouldBe selfEmploymentsOnlyResponse
      }
    }
    "read from empty Json" should {
      "return a JsSuccess" in {
        selfEmploymentsOnlyDesJson.validate[BusinessProfitAndLoss] shouldBe a[JsSuccess[_]]
      }
      "with an empty BusinessProfitAndLoss object" in {
        emptyJson.as[BusinessProfitAndLoss].isEmpty shouldBe true
      }
    }
    "read from invalid Json" should {
      "return a JsError" in {
        invalidDesJson.validate[BusinessProfitAndLoss] shouldBe a[JsError]
      }
    }
    "written to Json" should {
      "return the expected JsObject" in {
        Json.toJson(businessProfitAndLossResponse) shouldBe desJson
        Json.toJson(selfEmploymentsOnlyResponse) shouldBe selfEmploymentsOnlyDesJson
      }
    }
  }

}
