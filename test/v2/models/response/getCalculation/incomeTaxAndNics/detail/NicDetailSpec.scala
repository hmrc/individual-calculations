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

package v2.models.response.getCalculation.incomeTaxAndNics.detail

import play.api.libs.json.{JsError, Json}
import support.UnitSpec
import v2.fixtures.getCalculation.incomeTaxAndNics.detail.NicDetailFixtures._

class NicDetailSpec extends UnitSpec {

  "NicDetail" should {

    "read from json correctly" when {

      "provided with empty json" in {
        nicDetailEmptyJson.as[NicDetail] shouldBe NicDetail.empty
      }

      "provided with empty models in the json" in {
        nicDetailInputJsonWithEmptyModels.as[NicDetail] shouldBe NicDetail.empty
      }

      "provided with filled json" in {
        nicDetailFilledJson.as[NicDetail] shouldBe nicDetailFilledModel
      }
    }

    "write to json correctly" when {

      "provided with an empty model" in {
        Json.toJson(NicDetail.empty) shouldBe nicDetailEmptyJson
      }

      "provided with a filled model" in {
        Json.toJson(nicDetailFilledModel) shouldBe nicDetailOutputJson
      }
    }

    "read from invalid JSON" should {
      "produce a JsError" in {
        val invalidJson = Json.parse(
          s"""
            |{
            |   "class2Nics": ${Json.toJson(class2).toString()},
            |   "class4Nics":{
            |      "totalClass4LossesAvailable": true,
            |      "totalClass4LossesUsed":3002,
            |      "totalClass4LossesCarriedForward":3003,
            |      "totalIncomeLiableToClass4Charge":3003,
            |      "totalIncomeChargeableToClass4":3004,
            |      "nic4Bands":[
            |         {
            |            "name":"name",
            |            "rate":100.25,
            |            "threshold":200,
            |            "apportionedThreshold":300,
            |            "income":400,
            |            "amount":500.25
            |         }
            |      ]
            |   }
            |}
          """.stripMargin
        )
        invalidJson.validate[NicDetail] shouldBe a[JsError]
      }
    }
  }
}
