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

class DividendsSpec extends UnitSpec {

  val desJson: JsValue = Json.parse("""{
      |    "incomeReceived":100,
      |    "taxableIncome":200
      |}""".stripMargin)

  val invalidDesJson: JsValue = Json.parse("""{
      |    "incomeReceived":100
      |}""".stripMargin)

  val dividendsResponse = Dividends(100,200)
  "Dividends" when {
    "read from valid Json" should {
      "return a JsSuccess" in{
        desJson.validate[Dividends] shouldBe a[JsSuccess[_]]
      }
      "with the expected Dividends object" in{
        desJson.as[Dividends] shouldBe dividendsResponse
      }
    }
    "read from invalid Json" should {
      "return a JsError" in {
        invalidDesJson.validate[Dividends] shouldBe a[JsError]
      }
    }
    "written to Json" should {
      "return the expected JsObject" in {
        Json.toJson(dividendsResponse) shouldBe desJson
      }
    }
  }

}
