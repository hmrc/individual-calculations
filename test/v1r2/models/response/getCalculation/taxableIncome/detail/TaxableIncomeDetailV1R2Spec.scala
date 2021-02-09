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

package v1r2.models.response.getCalculation.taxableIncome.detail

import play.api.libs.json._
import support.UnitSpec
import v1.fixtures.getCalculation.taxableIncome.{TaxableIncomeJsonFixture, TaxableIncomeModelsFixture}
import v1r2.models.response.getCalculation.taxableIncome.TaxableIncomeDetailV1R2


class TaxableIncomeDetailV1R2Spec extends UnitSpec {

  "TaxableIncomeDetail" when {
    "read from valid JSON" should {
      "produce the expected TaxableIncomeDetail object" in {
        TaxableIncomeJsonFixture.desJsonV1R2.as[TaxableIncomeDetailV1R2] shouldBe TaxableIncomeModelsFixture.taxableIncomeDetailModelV1R2
      }
    }

    "read from valid JSON without PayPensionsProfit" should {
      "produce the expected TaxableIncomeDetail object" in {
        val model: TaxableIncomeDetailV1R2 = TaxableIncomeModelsFixture.taxableIncomeDetailModelV1R2.copy(payPensionsProfit = None)
        val pathToPrune: JsPath = JsPath \ "calculation" \ "taxCalculation" \ "incomeTax" \ "payPensionsProfit"
        val desJsonWithoutPPP: JsValue = pathToPrune.prune(TaxableIncomeJsonFixture.desJsonV1R2).get

        desJsonWithoutPPP.as[TaxableIncomeDetailV1R2] shouldBe model
      }
    }

    "read from valid JSON without SavingsAndGains" should {
      "produce the expected TaxableIncomeDetail object" in {
        val model: TaxableIncomeDetailV1R2 = TaxableIncomeModelsFixture.taxableIncomeDetailModelV1R2.copy(savingsAndGains = None)
        val pathToPrune: JsPath = JsPath \ "calculation" \ "taxCalculation" \ "incomeTax" \ "savingsAndGains"
        val desJsonWithoutSAG: JsValue = pathToPrune.prune(TaxableIncomeJsonFixture.desJsonV1R2).get

        desJsonWithoutSAG.as[TaxableIncomeDetailV1R2] shouldBe model
      }
    }

    "read from valid JSON without Dividends" should {
      "produce the expected TaxableIncomeDetail object" in {
        val model: TaxableIncomeDetailV1R2 = TaxableIncomeModelsFixture.taxableIncomeDetailModelV1R2.copy(dividends = None)
        val pathToPrune: JsPath = JsPath \ "calculation" \ "taxCalculation" \ "incomeTax" \ "dividends"
        val desJsonWithoutDividends: JsValue = pathToPrune.prune(TaxableIncomeJsonFixture.desJsonV1R2).get

        desJsonWithoutDividends.as[TaxableIncomeDetailV1R2] shouldBe model
      }
    }

    "read from valid JSON without LumpSums" should {
      "produce the expected TaxableIncomeDetail object" in {
        val model: TaxableIncomeDetailV1R2 = TaxableIncomeModelsFixture.taxableIncomeDetailModelV1R2.copy(lumpSums = None)
        val pathToPrune: JsPath = JsPath \ "calculation" \ "taxCalculation" \ "incomeTax" \ "lumpSums"
        val desJsonWithoutLumpSums: JsValue = pathToPrune.prune(TaxableIncomeJsonFixture.desJsonV1R2).get

        desJsonWithoutLumpSums.as[TaxableIncomeDetailV1R2] shouldBe model
      }
    }

    "read from valid JSON without GainsOnLifePolicies" should {
      "produce the expected TaxableIncomeDetail object" in {
        val model: TaxableIncomeDetailV1R2 = TaxableIncomeModelsFixture.taxableIncomeDetailModelV1R2.copy(gainsOnLifePolicies = None)
        val pathToPrune: JsPath = JsPath \ "calculation" \ "taxCalculation" \ "incomeTax" \ "gainsOnLifePolicies"
        val desJsonWithoutGainsOnLifePolicies: JsValue = pathToPrune.prune(TaxableIncomeJsonFixture.desJsonV1R2).get

        desJsonWithoutGainsOnLifePolicies.as[TaxableIncomeDetailV1R2] shouldBe model
      }
    }

    "read from invalid JSON" should {
      "produce a JsError" in {
        val invalidDesJson: JsValue = Json.parse(
          """
            |{
            |   "calculation": {
            |      "taxCalculation": {
            |         "incomeTax": {
            |            "dividends": {
            |               "incomeReceived": false
            |            }
            |         }
            |      }
            |   }
            |}
          """.stripMargin
        )

        invalidDesJson.validate[TaxableIncomeDetailV1R2] shouldBe a[JsError]
      }
    }

    "read from empty JSON" should {
      "produce the expected TaxableIncomeDetail object" in {
        val emptyJson: JsObject = JsObject.empty
        emptyJson.as[TaxableIncomeDetailV1R2] shouldBe TaxableIncomeDetailV1R2(None, None, None, None, None)
      }
    }

    "written to JSON" should {
      "produce the expected JsObject" in {
        val mtdJson: JsValue = (TaxableIncomeJsonFixture.mtdJson \ "detail").get
        Json.toJson(TaxableIncomeModelsFixture.taxableIncomeDetailModelV1R2) shouldBe mtdJson
      }
    }
  }
}
