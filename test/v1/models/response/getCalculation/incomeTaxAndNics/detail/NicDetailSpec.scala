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

package v1.models.response.getCalculation.incomeTaxAndNics.detail

import play.api.libs.json.{JsSuccess, JsValue, Json}
import support.UnitSpec

class NicDetailSpec extends UnitSpec {

  val class2 = Class2NicDetail(Some(100.25), Some(200.25), Some(300.25), Some(400.25), underSmallProfitThreshold = true, Some(false))
  val class4 = Class4NicDetail(
    Some(Class4Losses(Some(3001), Some(3002))),
    Some(3003),
    Some(3004),
    Some(
      Seq(NicBand(
        name = "name",
        rate = 100.25,
        threshold = Some(200.25),
        apportionedThreshold = Some(300.25),
        income = 400.25,
        amount = 500.25
      )))
  )

  val emptyJson: JsValue = Json.obj()

  val inputJsonWithEmptyModels: JsValue = Json.parse(
    s"""
       |{
       | "class4Nics" : {}
       |}
    """.stripMargin)

  val filledJson: JsValue = Json.parse(
    s"""|{
      | "class2Nics" : ${Json.toJson(class2).toString()},
      | "class4Nics" : {
      |  "totalClass4LossesAvailable" : 3001,
      | "totalClass4LossesUsed" : 3002,
      | "totalIncomeLiableToClass4Charge" : 3003,
      | "totalIncomeChargeableToClass4" :3004,
      |	"nic4Bands": [{
      |					"name": "name",
      |					"rate": 100.25,
      |					"threshold": 200.25,
      |					"apportionedThreshold": 300.25,
      |					"income": 400.25,
      |					"amount": 500.25
      |				}]}
      |}""".stripMargin)

  val outputJson: JsValue = Json.parse(
    s"""
      |{
      | "class2Nics" : ${Json.toJson(class2).toString()},
      | "class4Nics" : ${Json.toJson(class4).toString()}
      |}
    """.stripMargin)

  val emptyModel = NicDetail(None, None)
  val filledModel = NicDetail(Some(class2), Some(class4))

  "NicDetail" should {

    "read from json correctly" when {

      "provided with empty json" in {
        emptyJson.validate[NicDetail] shouldBe JsSuccess(emptyModel)
      }

      "provided with empty models in the json" in {
        inputJsonWithEmptyModels.validate[NicDetail] shouldBe JsSuccess(emptyModel)
      }

      "provided with filled json" in {
        filledJson.validate[NicDetail] shouldBe JsSuccess(filledModel)
      }
    }

    "write to json correctly" when {

      "provided with an empty model" in {
        Json.toJson(emptyModel) shouldBe emptyJson
      }

      "provided with a filled model" in {
        Json.toJson(filledModel) shouldBe outputJson
      }
    }
  }
}
