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

      "provided with empty class4Nics object in the json" in {
        nicDetailDesJsonWithEmptyObject.as[NicDetail] shouldBe NicDetail.empty
      }

      "provided with filled json" in {
        nicDetailDesJson.as[NicDetail] shouldBe nicDetailModel
      }
    }

    "read from invalid JSON" should {
      "produce a JsError" in {
        val invalidJson = Json.parse(
          """
            |{
            |   "calculation": {
            |      "taxCalculation": {
            |         "nics":{
            |            "class2Nics": false,
            |            "class4Nics": true
            |         }
            |      }
            |   }
            |}
          """.stripMargin
        )
        invalidJson.validate[NicDetail] shouldBe a[JsError]
      }
    }

    "write to json correctly" when {

      "provided with an empty model" in {
        Json.toJson(NicDetail.empty) shouldBe nicDetailEmptyJson
      }

      "provided with a filled model" in {
        Json.toJson(nicDetailModel) shouldBe nicDetailMtdJson
      }
    }
  }
}