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

package v2.models.response.getCalculation.incomeTaxAndNics.summary

import play.api.libs.json.{JsError, Json}
import support.UnitSpec
import v2.fixtures.getCalculation.incomeTaxAndNics.summary.NicSummaryFixtures._

class NicSummarySpec extends UnitSpec {

  "NicSummary" should {

    "read correctly from json" when {

      "provided with empty json" in {
        nicSummaryEmptyJson.as[NicSummary] shouldBe NicSummary.empty
      }

      "provided with json with empty models" in {
        nicSummaryEmptyModelJson.as[NicSummary] shouldBe NicSummary.empty
      }

      "provided with filled json" in {
        nicSummaryFilledJson.as[NicSummary] shouldBe nicSummaryFilledModel
      }
    }

    "write correctly to json" when {

      "provided with an empty model" in {
        Json.toJson(NicSummary.empty) shouldBe nicSummaryEmptyJson
      }

      "provided with a filled model" in {
        Json.toJson(nicSummaryFilledModel) shouldBe nicSummaryOutputJson
      }
    }

    "read from invalid JSON" should {
      "produce a JsError" in {
        val invalidJson = Json.parse(
          """
            |{
            |   "class2Nics":{
            |      "amount": true
            |   },
            |   "class4Nics":{
            |      "totalAmount": 200.25
            |   },
            |   "totalNic": 300.25
            |}
          """.stripMargin
        )
        invalidJson.validate[NicSummary] shouldBe a[JsError]
      }
    }
  }
}
