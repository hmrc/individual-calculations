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

package v1.models.response.getCalculation.incomeTaxAndNics.detail

import play.api.libs.json.{JsError, JsObject, Json}
import support.UnitSpec
import v1.fixtures.getCalculation.incomeTaxAndNics.detail.Class4NicDetailFixtures._

class Class4NicDetailSpec extends UnitSpec {

  "Class4NicDetail" should {

    "write correctly to json" in {
      Json.toJson(class4NicDetailModel) shouldBe class4NicDetailMtdJson
    }

    "read correctly from json" in {
      class4NicDetailDesJson.as[Class4NicDetail] shouldBe class4NicDetailModel
    }

    "read empty json to an empty object" in {
      JsObject.empty.as[Class4NicDetail] shouldBe Class4NicDetail.empty
    }

    "read from invalid JSON" should {
      "produce a JsError" in {
        val invalidJson = Json.parse(
          """
            |{
            |   "class4Losses":{
            |      "totalClass4LossesAvailable": 3001,
            |      "totalClass4LossesUsed": 3002,
            |      "totalClass4LossesCarriedForward": 3003
            |   },
            |   "totalIncomeLiableToClass4Charge": true,
            |   "totalIncomeChargeableToClass4": 3004,
            |   "class4NicBands":[
            |      {
            |         "name": "name",
            |         "rate": 100.25,
            |         "threshold": 200,
            |         "apportionedThreshold": 300,
            |         "income": 400,
            |         "amount": 500.25
            |      }
            |   ]
            |}
          """.stripMargin
        )
        invalidJson.validate[Class4NicDetail] shouldBe a[JsError]
      }
    }
  }
}
