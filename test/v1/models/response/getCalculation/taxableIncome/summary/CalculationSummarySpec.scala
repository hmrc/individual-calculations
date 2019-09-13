/*
 * Copyright 2019 HM Revenue & Customs
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

package v1.models.response.getCalculation.taxableIncome.summary

import play.api.libs.json.{ JsError, JsSuccess, Json }
import support.UnitSpec
import v1.fixtures.taxableIncome.calculationSummary.SummaryFixtures._

class CalculationSummarySpec extends UnitSpec {

  "Summary" when {
    "read from valid Json" should {
      "return a JsSuccess" in {
        summaryDesJson.validate[CalculationSummary] shouldBe a[JsSuccess[_]]
      }
      "with the expected Summary object" in {
        summaryDesJson.as[CalculationSummary] shouldBe summaryResponse
      }
    }
    "read from invalid Json" should {
      "return a JsError" in {
        summaryInvalidJson.validate[CalculationSummary] shouldBe a[JsError]
      }
    }
    "written to Json" should {
      "return the expected JsObject" in {
        Json.toJson(summaryResponse) shouldBe summaryWrittenJson
      }
    }
  }
}
