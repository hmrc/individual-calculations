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

import play.api.libs.json.{ JsError, JsSuccess, JsValue, Json }
import support.UnitSpec
import v1.fixtures.TaxableIncomeFixtures._

class SavingsAndGainsSpec extends UnitSpec {

  val desJsonWithoutSavings: JsValue = Json.parse("""{
      |   "taxCalculation" : {
      |     "incomeTax" : {
      |       "savingsAndGains" :{
      |        "incomeReceived" : 392,
      |        "taxableIncome": 3920
      |       }
      |     }
      |   }
      |}""".stripMargin)

  val writtenJson: JsValue = Json.parse("""{
      |   "incomeReceived" : 392,
      |   "taxableIncome" : 3920,
      |   "savings" : [
      |   {
      |     "savingsAccountId":"anId",
      |     "savingsAccountName":"aName",
      |     "grossIncome":300.1,
      |     "netIncome": 12.3,
      |     "taxDeducted": 456.3
      |     },
      |   {
      |     "savingsAccountId":"anId2",
      |     "savingsAccountName":"aName2",
      |     "grossIncome":400.1,
      |     "netIncome": 112.3,
      |     "taxDeducted": 556.3
      |     }
      |
      |     ]
      |}""".stripMargin)

  val writtenJsonWithoutSavings: JsValue = Json.parse("""{
      |   "incomeReceived" : 392,
      |   "taxableIncome" : 3920
      |}""".stripMargin)

  val invalidDesJson: JsValue = Json.parse("""{
      |   "taxCalculation" : {
      |     "incomeTax" : {
      |       "savingsAndGains" : {
      |        "taxableIncome": 3920
      |       }
      |     }
      |   }
      |}""".stripMargin)

  val savingsAndGainsResponseWithoutSavings: SavingsAndGains = savingsAndGainsResponse.copy(savings = None)

  "SavingsAndGains" when {
    "read from valid Json" should {
      "return a JsSuccess" in {
        savingsAndGainsDesJson.validate[SavingsAndGains] shouldBe a[JsSuccess[_]]
      }
      "with the expected SavingsAndGains object" in {
        savingsAndGainsDesJson.as[SavingsAndGains] shouldBe savingsAndGainsResponse
      }
    }
    "read from valid Json with missing optional fields" should {
      "return a JsSuccess" in {
        desJsonWithoutSavings.validate[SavingsAndGains] shouldBe a[JsSuccess[_]]
      }
      "with the expected SavingsAndGains object" in {
        desJsonWithoutSavings.as[SavingsAndGains] shouldBe savingsAndGainsResponseWithoutSavings
      }
    }
    "read from invalid Json" should {
      "return a JsError" in {
        invalidDesJson.validate[SavingsAndGains] shouldBe a[JsError]
      }
    }
    "written to Json" should {
      "return the expected JsObject" in {
        Json.toJson(savingsAndGainsResponse) shouldBe writtenJson
      }
    }
    "written to Json with empty optional fields" should {
      "return the expected JsObject" in {
        Json.toJson(savingsAndGainsResponseWithoutSavings) shouldBe writtenJsonWithoutSavings
      }
    }
  }

}
