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

package v2.models.response.getCalculation.taxableIncome

import play.api.libs.json._
import support.UnitSpec
import v2.fixtures.getCalculation.taxableIncome.{TaxableIncomeJsonFixture, TaxableIncomeModelsFixture}

class TaxableIncomeDetailSpec extends UnitSpec {

  "TaxableIncomeDetail" when {
    "read from valid JSON" should {
      "produce the expected TaxableIncomeDetail object" in {
        TaxableIncomeJsonFixture.desJson.as[TaxableIncomeDetail] shouldBe TaxableIncomeModelsFixture.taxableIncomeDetailModel
      }
    }

    "read from valid JSON without PayPensionsProfit" should {
      "produce the expected TaxableIncomeDetail object" in {
        val model: TaxableIncomeDetail = TaxableIncomeModelsFixture.taxableIncomeDetailModel.copy(payPensionsProfit = None)
        val pathToPrune: JsPath = JsPath \ "calculation" \ "taxCalculation" \ "incomeTax" \ "payPensionsProfit"
        val desJsonWithoutPPP: JsValue = pathToPrune.prune(TaxableIncomeJsonFixture.desJson).get

        desJsonWithoutPPP.as[TaxableIncomeDetail] shouldBe model
      }
    }

    "read from valid JSON without SavingsAndGains" should {
      "produce the expected TaxableIncomeDetail object" in {
        val model: TaxableIncomeDetail = TaxableIncomeModelsFixture.taxableIncomeDetailModel.copy(savingsAndGains = None)
        val pathToPrune: JsPath = JsPath \ "calculation" \ "taxCalculation" \ "incomeTax" \ "savingsAndGains"
        val desJsonWithoutSAG: JsValue = pathToPrune.prune(TaxableIncomeJsonFixture.desJson).get

        desJsonWithoutSAG.as[TaxableIncomeDetail] shouldBe model
      }
    }

    "read from valid JSON without Dividends" should {
      "produce the expected TaxableIncomeDetail object" in {
        val model: TaxableIncomeDetail = TaxableIncomeModelsFixture.taxableIncomeDetailModel.copy(dividends = None)
        val pathToPrune: JsPath = JsPath \ "calculation" \ "taxCalculation" \ "incomeTax" \ "dividends"
        val desJsonWithoutDividends: JsValue = pathToPrune.prune(TaxableIncomeJsonFixture.desJson).get

        desJsonWithoutDividends.as[TaxableIncomeDetail] shouldBe model
      }
    }

    "read from valid JSON without LumpSums" should {
      "produce the expected TaxableIncomeDetail object" in {
        val model: TaxableIncomeDetail = TaxableIncomeModelsFixture.taxableIncomeDetailModel.copy(lumpSums = None)
        val pathToPrune: JsPath = JsPath \ "calculation" \ "taxCalculation" \ "incomeTax" \ "lumpSums"
        val desJsonWithoutLumpSums: JsValue = pathToPrune.prune(TaxableIncomeJsonFixture.desJson).get

        desJsonWithoutLumpSums.as[TaxableIncomeDetail] shouldBe model
      }
    }

    "read from valid JSON without GainsOnLifePolicies" should {
      "produce the expected TaxableIncomeDetail object" in {
        val model: TaxableIncomeDetail = TaxableIncomeModelsFixture.taxableIncomeDetailModel.copy(gainsOnLifePolicies = None)
        val pathToPrune: JsPath = JsPath \ "calculation" \ "taxCalculation" \ "incomeTax" \ "gainsOnLifePolicies"
        val desJsonWithoutGainsOnLifePolicies: JsValue = pathToPrune.prune(TaxableIncomeJsonFixture.desJson).get

        desJsonWithoutGainsOnLifePolicies.as[TaxableIncomeDetail] shouldBe model
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

        invalidDesJson.validate[TaxableIncomeDetail] shouldBe a[JsError]
      }
    }

    "read from empty JSON" should {
      "produce the expected TaxableIncomeDetail object" in {
        val emptyJson: JsObject = JsObject.empty
        emptyJson.as[TaxableIncomeDetail] shouldBe TaxableIncomeDetail(None, None, None, None, None)
      }
    }

    "written to JSON" should {
      "produce the expected JsObject" in {
        val mtdJson: JsValue = (TaxableIncomeJsonFixture.mtdJson \ "detail").get
        Json.toJson(TaxableIncomeModelsFixture.taxableIncomeDetailModel) shouldBe mtdJson
      }
    }
  }
}
