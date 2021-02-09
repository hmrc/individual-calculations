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

package v1r2.models.response.getCalculation.taxableIncome.detail.payPensionsProfit.foreignProperty

import play.api.libs.json.{JsError, JsObject, JsValue, Json}
import support.UnitSpec
import v1r2.fixtures.getCalculation.taxableIncome.{TaxableIncomeJsonFixture, TaxableIncomeModelsFixture}

class ForeignPropertySpec extends UnitSpec {

  "ForeignProperty" when {
    "read from valid JSON" should {
      "produce the expected ForeignProperty object" in {
        TaxableIncomeJsonFixture.desJson.as[ForeignProperty] shouldBe TaxableIncomeModelsFixture.foreignPropertyModel
      }
    }

    "read from empty JSON" should {
      "produce the expected ForeignProperty object" in {
        val emptyJson: JsValue = JsObject.empty
        emptyJson.as[ForeignProperty] shouldBe ForeignProperty.empty
      }
    }

    "read from non-empty JSON without incomeSourceType '15'" should {
      "produce the expected ForeignProperty object" in {
        TaxableIncomeJsonFixture.oneSelfEmploymentOnlyDesJson.as[ForeignProperty] shouldBe ForeignProperty.empty
      }
    }

    "read from invalid JSON" should {
      "produce a JsError" in {
        val invalidDesJson: JsValue = Json.parse(
          """
            |{
            |   "calculation": {
            |      "lossesAndClaims": {
            |         "resultOfClaimsApplied": "no"
            |      }
            |   }
            |}
          """.stripMargin
        )

        invalidDesJson.validate[ForeignProperty] shouldBe a[JsError]
      }
    }

    "written to JSON" should {
      "produce the expected JsObject" in {
      val mtdJson: JsValue = (TaxableIncomeJsonFixture.mtdJson \ "detail" \ "payPensionsProfit" \
        "businessProfitAndLoss" \ "foreignProperty").get
        Json.toJson(TaxableIncomeModelsFixture.foreignPropertyModel) shouldBe mtdJson
      }
    }
  }
}
