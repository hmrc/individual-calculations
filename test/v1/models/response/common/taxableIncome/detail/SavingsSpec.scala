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
package v1.models.response.common.taxableIncome.detail

import play.api.libs.json.{JsError, JsSuccess, JsValue, Json}
import support.UnitSpec

class SavingsSpec extends UnitSpec {
  val desJson: JsValue = Json.parse("""{
      |    "incomeSourceId":"anId",
      |    "incomeSourceName":"aName",
      |    "grossIncome":300.1,
      |    "netIncome": 12.3,
      |    "taxDeducted": 456.3
      |}""".stripMargin)

  val desJsonWithoutOptionals: JsValue = Json.parse("""{
      |    "incomeSourceId":"anId",
      |    "incomeSourceName":"aName",
      |    "grossIncome":300.1
      |}""".stripMargin)

  val writtenJson: JsValue = Json.parse("""{
      |    "savingsAccountId":"anId",
      |    "savingsAccountName":"aName",
      |    "grossIncome":300.1,
      |    "netIncome": 12.3,
      |    "taxDeducted": 456.3
      |}""".stripMargin)

  val writtenJsonWithoutOptionals: JsValue = Json.parse("""{
      |    "savingsAccountId":"anId",
      |    "savingsAccountName":"aName",
      |    "grossIncome":300.1
      |}""".stripMargin)

  val invalidDesJson: JsValue = Json.parse("""{
     |    "savingsAccountId":100
     |}""".stripMargin)

  val savingsResponse = Savings("anId","aName",300.1,Some(12.3),Some(456.3))
  val savingsResponseWithoutOptionals = Savings("anId","aName",300.1,None, None)

  "Savings" when {
    "read from valid Json" should {
      "return a JsSuccess" in{
        desJson.validate[Savings] shouldBe a[JsSuccess[_]]
      }
      "with the expected Savings object" in{
        desJson.as[Savings] shouldBe savingsResponse
      }
    }
    "read from valid Json with missing optional fields" should {
      "return a JsSuccess" in{
        desJsonWithoutOptionals.validate[Savings] shouldBe a[JsSuccess[_]]
      }
      "with the expected Savings object" in{
        desJsonWithoutOptionals.as[Savings] shouldBe savingsResponseWithoutOptionals
      }
    }
    "read from invalid Json" should {
      "return a JsError" in {
        invalidDesJson.validate[Savings] shouldBe a[JsError]
      }
    }
    "written to Json" should {
      "return the expected JsObject" in {
        Json.toJson(savingsResponse) shouldBe writtenJson
      }
    }
    "written to Json with empty optional fields" should {
      "return the expected JsObject" in {
        Json.toJson(savingsResponseWithoutOptionals) shouldBe writtenJsonWithoutOptionals
      }
    }
  }

}
