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

package v1.models.response.getCalculation.incomeTaxAndNics.summary

import play.api.libs.json.{JsValue, Json}
import support.UnitSpec

class NicSummarySpec extends UnitSpec {

  val filledJson: JsValue = Json.parse(
    """
      |{
      | "class2Nics" : {
      |   "amount" : 100.25
      | },
      | "class4Nics" : {
      |   "totalAmount" : 200.25
      | },
      | "totalNic" : 300.25
      |}
    """.stripMargin)

  val emptyModelJson: JsValue = Json.parse(
    """
      |{
      | "class2Nics" : {
      | },
      | "class4Nics" : {
      | }
      |}
    """.stripMargin)

  val outputJson: JsValue = Json.parse(
    """
      |{
      | "class2NicsAmount" : 100.25,
      | "class4NicsAmount" : 200.25,
      | "totalNic" : 300.25
      |}
    """.stripMargin)

  val emptyJson: JsValue = Json.obj()

  val filledModel = NicSummary(Some(100.25), Some(200.25), Some(300.25))

  "NicSummary" should {

    "read correctly from json" when {

      "provided with empty json" in {
        emptyJson.as[NicSummary] shouldBe NicSummary.empty
      }

      "provided with json with empty models" in {
        emptyModelJson.as[NicSummary] shouldBe NicSummary.empty
      }

      "provided with filled json" in {
        filledJson.as[NicSummary] shouldBe filledModel
      }
    }

    "write correctly to json" when {

      "provided with an empty model" in {
        Json.toJson(NicSummary.empty) shouldBe emptyJson
      }

      "provided with a filled model" in {
        Json.toJson(filledModel) shouldBe outputJson
      }
    }
  }
}
