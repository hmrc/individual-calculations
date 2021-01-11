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

import play.api.libs.json.{JsError, JsObject, Json}
import support.UnitSpec
import v1.fixtures.getCalculation.taxableIncome.{TaxableIncomeJsonFixture, TaxableIncomeModelsFixture}

class TaxableIncomeSpec extends UnitSpec {

  "TaxableIncome" when {
    "read from valid JSON" should {
      "produce the expected TaxableIncome object" in {
        TaxableIncomeJsonFixture.desJson.as[TaxableIncome] shouldBe TaxableIncomeModelsFixture.taxableIncomeModel
      }
    }

    "read from invalid JSON" should {
      "return a JsError" in {
        val invalidDesJson = JsObject.empty
        invalidDesJson.validate[TaxableIncome] shouldBe a[JsError]
      }
    }

    "written to JSON" should {
      "return the expected JsObject" in {
        Json.toJson(TaxableIncomeModelsFixture.taxableIncomeModel) shouldBe TaxableIncomeJsonFixture.mtdJson
      }
    }
  }
}
