/*
 * Copyright 2020 HM Revenue & Customs
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

import play.api.libs.json.{JsError, JsValue, Json}
import support.UnitSpec
import v1.fixtures.getCalculation.taxableIncome.{TaxableIncomeJsonFixture, TaxableIncomeModelsFixture}

class DividendsSpec extends UnitSpec {

  "Dividends" when {
    "read from valid JSON" should {
      "produce the expected Dividends object" in {
        val desJson: JsValue = (TaxableIncomeJsonFixture.desJson \ "calculation" \ "taxCalculation" \
          "incomeTax" \ "dividends").get
        desJson.as[Dividends] shouldBe TaxableIncomeModelsFixture.dividendsModel
      }
    }

    "read from invalid JSON" should {
      "return a JsError" in {
        val invalidDesJson: JsValue = Json.parse(
          """
            |{
            |    "incomeReceived": 100
            |}
          """.stripMargin
        )

        invalidDesJson.validate[Dividends] shouldBe a[JsError]
      }
    }

    "written to JSON" should {
      "produce the expected JsObject" in {
        val mtdJson: JsValue = (TaxableIncomeJsonFixture.mtdJson \ "detail" \ "dividends").get
        Json.toJson(TaxableIncomeModelsFixture.dividendsModel) shouldBe mtdJson
      }
    }
  }
}
