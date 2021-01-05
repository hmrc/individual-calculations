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

package v1.models.response.getCalculation.taxableIncome.detail

import play.api.libs.json.{JsError, JsPath, JsValue, Json}
import support.UnitSpec
import v1.fixtures.getCalculation.taxableIncome.{TaxableIncomeJsonFixture, TaxableIncomeModelsFixture}

class SavingsAndGainsSpec extends UnitSpec {

  "SavingsAndGains" when {
    val savingsAndGainsModelWithoutSavingsOrSecurities: SavingsAndGains = TaxableIncomeModelsFixture.savingsAndGainsModel.copy(
      ukSavings = None,
      ukSecurities = None
    )

    "read from valid JSON" should {
      "produce the expected SavingsAndGains object" in {
        TaxableIncomeJsonFixture.desJson.as[SavingsAndGains] shouldBe TaxableIncomeModelsFixture.savingsAndGainsModel
      }
    }

    "read from valid JSON with a savingsAndGainsIncome array" should {
      "produce the expected SavingsAndGains object" in {
        val pathToPrune: JsPath = JsPath \ "calculation" \ "savingsAndGainsIncome"
        val desJsonWithoutSavings: JsValue = pathToPrune.prune(TaxableIncomeJsonFixture.desJson).get

        desJsonWithoutSavings.as[SavingsAndGains] shouldBe savingsAndGainsModelWithoutSavingsOrSecurities
      }
    }

    "read from valid JSON with a savings array without any appropriate items" should {
      "produce the expected SavingsAndGains object" in {
        val desJsonWithoutValidSavings: JsValue = Json.parse(
          """
            |{
            |   "calculation": {
            |      "taxCalculation" : {
            |         "incomeTax" : {
            |            "savingsAndGains" : {
            |               "incomeReceived": 7012,
            |               "taxableIncome": 7014
            |            }
            |         }
            |      },
            |      "savingsAndGainsIncome" : [
            |         {
            |            "incomeSourceId":"anId3",
            |            "incomeSourceType": "04",
            |            "incomeSourceName":"aName3",
            |            "grossIncome": 400.1,
            |            "netIncome": 112.3,
            |            "taxDeducted": 556.3
            |         }
            |      ]
            |   }
            |}
          """.stripMargin
        )

        desJsonWithoutValidSavings.as[SavingsAndGains] shouldBe savingsAndGainsModelWithoutSavingsOrSecurities
      }
    }

    "read from invalid JSON" should {
      "return a JsError" in {
        val invalidDesJson: JsValue = Json.parse(
          """
            |{
            |   "calculation": {
            |      "taxCalculation" : {
            |         "incomeTax" : {
            |            "savingsAndGains" : {
            |               "taxableIncome": 3920
            |            }
            |         }
            |      }
            |   }
            |}
          """.stripMargin
        )

        invalidDesJson.validate[SavingsAndGains] shouldBe a[JsError]
      }
    }

    "written to JSON" should {
      "return the expected JsObject" in {
        val mtdJson: JsValue = (TaxableIncomeJsonFixture.mtdJson \ "detail" \ "savingsAndGains").get
        Json.toJson(TaxableIncomeModelsFixture.savingsAndGainsModel) shouldBe mtdJson
      }
    }
  }
}
