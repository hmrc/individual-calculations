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

package v1.models.response.common

import play.api.libs.json.{JsSuccess, JsValue, Json}
import support.UnitSpec

class IncomeTaxSpec extends UnitSpec {

  val minJson: JsValue = Json.parse(
    """
      |{
      | "calculation" : {
      |   "taxCalculation" : {
      |     "incomeTax" : {
      |       "incomeTaxCharged" : 100.25
      |     },
      |     "totalIncomeTaxAndNicsDue" : 200.25
      |   }
      | }
      |}
    """.stripMargin)

  val emptyModelJson: JsValue = Json.parse(
    """
      |{
      | "calculation" : {
      |   "taxCalculation" : {
      |     "incomeTax" : {
      |       "incomeTaxCharged" : 100.25
      |     },
      |     "totalIncomeTaxAndNicsDue" : 200.25
      |   },
      |   "taxDeductedAtSource" : {
      |   }
      | }
      |}
    """.stripMargin)

  val filledTopLevelJson: JsValue = Json.parse(
    """
       |{
       |  "calculation" : {
       |   "taxCalculation" : {
       |     "incomeTax" : {
       |       "incomeTaxCharged" : 100.25
       |     },
       |     "totalIncomeTaxAndNicsDue" : 200.25
       |   },
       |   "taxDeductedAtSource" : {
       |    "ukLandAndProperty" : 300.25,
       |    "bbsi" : 400.25
       |   }
       | }
       |}
    """.stripMargin)

  val outputEmptyModelJson: JsValue = Json.parse(
    """
      |{
      | "summary" : {
      |   "incomeTax" : {
      |     "incomeTaxCharged" : 100.25
      |   },
      |   "totalIncomeTaxAndNicsDue" : 200.25
      | }
      |}
    """.stripMargin)

  val outputTopLevelFilledJson: JsValue = Json.parse(
    """
      |{
      | "summary" : {
      |   "incomeTax" : {
      |     "incomeTaxCharged" : 100.25
      |   },
      |   "totalIncomeTaxAndNicsDue" : 200.25
      | },
      | "detail" : {
      |   "taxDeductedAtSource" : {
      |     "ukLandAndProperty" : 300.25,
      |     "bbsi" : 400.25
      |   }
      | }
      |}
    """.stripMargin)

  val calcSummary = CalculationSummary(IncomeTaxSummary(100.25, None, None), None, None, None, 200.25)
  val calcDetail = CalculationDetail(None, None, Some(TaxDeductedAtSource(Some(300.25), Some(400.25))), None)
  val minModel = IncomeTax(calcSummary, None)
  val topLevelModel = IncomeTax(calcSummary, Some(calcDetail))

  "IncomeTax" should {

    "read from json correctly" when {

      "provided with the minimum data" in {
        minJson.validate[IncomeTax] shouldBe JsSuccess(minModel)
      }

      "provided with json producing empty models" in {
        emptyModelJson.validate[IncomeTax] shouldBe JsSuccess(minModel)
      }

      "provided with the maximum data" in {
        filledTopLevelJson.validate[IncomeTax] shouldBe JsSuccess(topLevelModel)
      }
    }

    "write to json correctly" when {

      "provided with the minimum model" in {
        Json.toJson(minModel) shouldBe outputEmptyModelJson
      }

      "provided with a model with the top layer filled" in {
        Json.toJson(topLevelModel) shouldBe outputTopLevelFilledJson
      }
    }
  }
}
