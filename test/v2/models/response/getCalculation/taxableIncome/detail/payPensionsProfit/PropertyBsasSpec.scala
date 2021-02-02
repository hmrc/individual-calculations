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

package v2.models.response.getCalculation.taxableIncome.detail.payPensionsProfit

import play.api.libs.json.{JsError, JsValue, Json}
import support.UnitSpec
import v2.fixtures.getCalculation.taxableIncome.{TaxableIncomeJsonFixture, TaxableIncomeModelsFixture}

class PropertyBsasSpec extends UnitSpec {

  "PropertyBsas" when {
    "read from valid JSON" should {
      "produce the expected PropertyBsas object" in {
        val desJson: JsValue = (TaxableIncomeJsonFixture.desJson \ "inputs" \ "annualAdjustments" \ 3).get
        desJson.as[PropertyBsas] shouldBe TaxableIncomeModelsFixture.ukPropertyFhlBsasModel
      }
    }

    "read from invalid JSON" should {
      "return a JsError" in {
        val invalidDesJson: JsValue = Json.parse(
          """
            |{
            |  "ascId" : 200,
            |  "applied" : true
            |}
          """.stripMargin
        )

        invalidDesJson.validate[PropertyBsas] shouldBe a[JsError]
      }
    }

    "written to JSON" should {
      "produce the expected JsObject" in {
        val mtdJson: JsValue = (TaxableIncomeJsonFixture.mtdJson \ "detail" \ "payPensionsProfit" \
          "businessProfitAndLoss" \ "ukPropertyFhl" \ "bsas").get
        Json.toJson(TaxableIncomeModelsFixture.ukPropertyFhlBsasModel) shouldBe mtdJson
      }
    }
  }
}
