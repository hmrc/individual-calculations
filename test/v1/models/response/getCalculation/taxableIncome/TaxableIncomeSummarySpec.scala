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

package v1.models.response.getCalculation.taxableIncome

import play.api.libs.json.{JsError, JsValue, Json}
import support.UnitSpec
import v1.fixtures.getCalculation.taxableIncome.{TaxableIncomeJsonFixture, TaxableIncomeModelsFixture}

class TaxableIncomeSummarySpec extends UnitSpec {

  "TaxableIncomeSummary" when {
    "read from valid JSON" should {
      "produce the expected TaxableIncomeSummary object" in {
        val desJson: JsValue = (TaxableIncomeJsonFixture.desJson \ "calculation" \ "taxCalculation" \ "incomeTax").get
        desJson.as[TaxableIncomeSummary] shouldBe TaxableIncomeModelsFixture.taxableIncomeSummaryModel
      }
    }

    "read from invalid JSON" should {
      "return a JsError" in {
        val invalidDesJson: JsValue = Json.parse(
          """
            |{
            |   "totalIncomeReceivedFromAllSources": 100
            |}
          """.stripMargin
        )

        invalidDesJson.validate[TaxableIncomeSummary] shouldBe a[JsError]
      }
    }

    "written to JSON" should {
      "return the expected JsObject" in {
        val mtdJson: JsValue = (TaxableIncomeJsonFixture.mtdJson \ "summary").get
        Json.toJson(TaxableIncomeModelsFixture.taxableIncomeSummaryModel) shouldBe mtdJson
      }
    }
  }
}
