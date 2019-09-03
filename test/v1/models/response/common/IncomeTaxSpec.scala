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

  val json: JsValue = Json.parse(
    """
       |{
       |  "calculation" : {
       |   "taxCalculation" : {
       |     "incomeTax" : {
       |       "incomeTaxCharged" : 100.25,
       |       "payPensionsProfit" : {
       |        "allowancesAllocated" : 300.25,
       |        "incomeTaxAmount" : 400.25,
       |        "taxBands" : []
       |       }
       |     },
       |     "totalIncomeTaxAndNicsDue" : 200.25
       |   }
       | }
       |}
    """.stripMargin)

  val outputJson: JsValue = Json.parse(
    """
      |{
      | "summary" : {
      |   "incomeTax" : {
      |     "incomeTaxCharged" : 100.25
      |   },
      |   "totalIncomeTaxAndNicsDue" : 200.25
      | },
      | "detail" : {
      |   "incomeTax" : {
      |     "payPensionsProfit" : {
      |        "allowancesAllocated" : 300.25,
      |        "incomeTaxAmount" : 400.25,
      |        "taxBands" : []
      |     }
      |   }
      | }
      |}
    """.stripMargin)

  val calcSummary = CalculationSummary(IncomeTaxSummary(100.25, None, None), None, None, None, 200.25, None)
  val calcDetail = CalculationDetail(IncomeTaxDetail(Some(IncomeTypeBreakdown(300.25, 400.25, Seq())), None, None, None), None, None)
  val model = IncomeTax(calcSummary, calcDetail)

  "IncomeTax" should {

    "read from json correctly" in {
      json.validate[IncomeTax] shouldBe JsSuccess(model)
    }

    "write to json correctly" in {
      Json.toJson(model) shouldBe outputJson
    }
  }
}
