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

package v1r2.models.response.getCalculation.taxableIncome.detail.payPensionsProfit.selfEmployment

import play.api.libs.json.{JsError, JsValue, Json}
import support.UnitSpec
import v1r2.fixtures.getCalculation.taxableIncome.{TaxableIncomeJsonFixture, TaxableIncomeModelsFixture}

class SelfEmploymentBsasSpec extends UnitSpec {

  "SelfEmploymentBsas" when {
    "read from valid JSON" should {
      "produce the expected SelfEmploymentBsas object" in {
        val desJson: JsValue = (TaxableIncomeJsonFixture.desJson \ "inputs" \ "annualAdjustments" \ 0).get
        desJson.as[SelfEmploymentBsas] shouldBe TaxableIncomeModelsFixture.selfEmploymentBsasModel1
      }
    }

    "read from invalid JSON" should {
      "produce a JsError" in {
        val invalidDesJson: JsValue = Json.parse(
          """
            |{
            |   "ascId": "bsasId"
            |}
          """.stripMargin
        )

        invalidDesJson.validate[SelfEmploymentBsas] shouldBe a[JsError]
      }
    }

    "writes a valid model" should {
      "return a valid json" in {
        val mtdJson: JsValue = (TaxableIncomeJsonFixture.mtdJson \ "detail" \ "payPensionsProfit" \
          "businessProfitAndLoss" \ "selfEmployments" \ 0 \ "bsas").get
        Json.toJson(TaxableIncomeModelsFixture.selfEmploymentBsasModel1) shouldBe mtdJson
      }
    }
  }
}
