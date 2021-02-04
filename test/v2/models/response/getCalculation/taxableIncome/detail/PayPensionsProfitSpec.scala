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

package v2.models.response.getCalculation.taxableIncome.detail

import play.api.libs.json._
import support.UnitSpec
import v2.fixtures.getCalculation.taxableIncome.{TaxableIncomeJsonFixture, TaxableIncomeModelsFixture}

class PayPensionsProfitSpec extends UnitSpec {

  "PayPensionsProfit" when {
    "read from valid Json" should {
      "produce the expected PayPensionsProfit object" in {
        TaxableIncomeJsonFixture.desJson.as[PayPensionsProfit] shouldBe TaxableIncomeModelsFixture.payPensionsProfitModel
      }
    }

    "read from valid Json without BusinessProfitAndLoss" should {
      "produce the expected PayPensionsProfit object" in {
        val model: PayPensionsProfit = TaxableIncomeModelsFixture.payPensionsProfitModel.copy(businessProfitAndLoss = None)

        val pathToPrune1: JsPath = JsPath \ "calculation" \ "businessProfitAndLoss"
        val prunedDesJson1: JsValue = pathToPrune1.prune(TaxableIncomeJsonFixture.desJson).get

        val pathToPrune2: JsPath = JsPath \ "calculation" \ "lossesAndClaims"
        val prunedDesJson2 = pathToPrune2.prune(prunedDesJson1).get

        val pathToPrune3: JsPath = JsPath \ "inputs"
        val desJsonWithoutBPAL = pathToPrune3.prune(prunedDesJson2).get

        desJsonWithoutBPAL.as[PayPensionsProfit] shouldBe model
      }
    }

    "read from invalid JSON" should {
      "return a JsError" in {
        val invalidDesJson: JsValue = JsObject.empty
        invalidDesJson.validate[PayPensionsProfit] shouldBe a[JsError]
      }
    }

    "written to JSON" should {
      "produce the expected JsObject" in {
        val mtdJson: JsValue = (TaxableIncomeJsonFixture.mtdJson \ "detail" \ "payPensionsProfit").get
        Json.toJson(TaxableIncomeModelsFixture.payPensionsProfitModel) shouldBe mtdJson
      }
    }
  }
}
