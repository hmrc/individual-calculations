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

class CalculationDetailSpec extends UnitSpec {

  val emptyJson: JsValue = Json.obj()

  val inputJsonWithEmptyModels: JsValue = Json.parse(
    """
      |{
      | "calculation" : {
      |   "taxCalculation" : {
      |     "incomeTax" : {
      |
      |     },
      |     "nics" : {
      |
      |     }
      |   },
      |   "taxDeductedAtSource" : {
      |
      |   }
      | }
      |}
    """.stripMargin)

  val inputJson: JsValue = Json.parse(
    """
      |{
      | "calculation" : {
      |   "taxCalculation" : {
      |     "incomeTax" : {
      |        "payPensionsProfit" : {
      |           "allowancesAllocated" : 200.25,
      |           "incomeTaxAmount": 200.50,
      |           "taxBands" : [
      |
      |           ]
      |        }
      |     },
      |     "nics" : {
      |       "class2Nics" : {
      |         "underSmallProfitThreshold" : true
      |       }
      |     }
      |   },
      |   "taxDeductedAtSource" : {
      |     "ukLandAndProperty" : 300.25,
      |     "bbsi" : 300.50
      |   },
      |   "giftAid" : {
      |     "grossGiftAidPayments" : 400.25,
      |     "rate" : 400.50,
      |     "giftAidTax" : 400.75
      |   }
      | }
      |}
    """.stripMargin)

  val outputJson: JsValue = Json.parse(
    """
      |{
      | "incomeTax" : {
      |   "payPensionsProfit" : {
      |     "allowancesAllocated" : 200.25,
      |     "incomeTaxAmount" : 200.50,
      |     "taxBands" : []
      |   }
      | },
      | "nics" : {
      |   "class2Nics" : {
      |     "underSmallProfitThreshold" : true
      |   }
      | },
      | "taxDeductedAtSource" : {
      |   "ukLandAndProperty" : 300.25,
      |   "bbsi" : 300.50
      | },
      | "giftAid" : {
      |   "grossGiftAidPayments" : 400.25,
      |   "rate" : 400.50,
      |   "giftAidTax" : 400.75
      | }
      |}
    """.stripMargin)

  val minModel = CalculationDetail(None, None, None, None)
  val filledModel = CalculationDetail(
    Some(IncomeTaxDetail(Some(IncomeTypeBreakdown(200.25, 200.50, Seq())), None, None)),
    Some(NicDetail(Some(Class2NicDetail(None, None, None, None, true, None)), None)),
    Some(TaxDeductedAtSource(Some(300.25), Some(300.50))),
    Some(GiftAid(400.25, 400.50, 400.75))
  )

  "CalculationDetail" should {

    "write to json correctly" when {

      "provided with a minimal model" in {
        Json.toJson(minModel) shouldBe emptyJson
      }

      "provided with a top level model" in {
        Json.toJson(filledModel) shouldBe outputJson
      }
    }

    "read from json correctly" when {

      "provided with empty json" in {
        emptyJson.validate[CalculationDetail] shouldBe JsSuccess(minModel)
      }

      "provided with json with empty models" in {
        inputJsonWithEmptyModels.validate[CalculationDetail] shouldBe JsSuccess(minModel)
      }

      "provided with json containing all top level models" in {
        inputJson.validate[CalculationDetail] shouldBe JsSuccess(filledModel)
      }
    }
  }
}
