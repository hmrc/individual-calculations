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

package v1r2.models.response.getCalculation.taxableIncome

import play.api.libs.json.{JsError, JsObject, Json}
import support.UnitSpec
import v1.fixtures.getCalculation.taxableIncome.{TaxableIncomeJsonFixture, TaxableIncomeModelsFixture}


class TaxableIncomeV1R2Spec extends UnitSpec {

  "TaxableIncome" when {
    "read from valid JSON" should {
      "produce the expected TaxableIncome object" in {
        TaxableIncomeJsonFixture.desJsonV1R2.as[TaxableIncomeV1R2] shouldBe TaxableIncomeModelsFixture.taxableIncomeModelV1R2
      }
    }

    "read from invalid JSON" should {
      "return a JsError" in {
        val invalidDesJson = JsObject.empty
        invalidDesJson.validate[TaxableIncomeV1R2] shouldBe a[JsError]
      }
    }

    "written to JSON" should {
      "return the expected JsObject" in {
        Json.toJson(TaxableIncomeModelsFixture.taxableIncomeModelV1R2) shouldBe TaxableIncomeJsonFixture.mtdJson
      }
    }
  }
}
