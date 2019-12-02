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

package v1.models.response.getCalculation.incomeTaxAndNics.summary

import play.api.libs.json.{JsSuccess, Json}
import support.UnitSpec
import v1.fixtures.getCalculation.incomeTaxAndNics.summary.CalculationSummaryFixtures._

class CalculationSummarySpec extends UnitSpec {

  "CalculationDetail" should {

    "write to json correctly" when {

      "provided with the minimum model" in {
        Json.toJson(minModel) shouldBe minOutputJson
      }
    }

    "read from json correctly" when {

      "provided with the minimum json" in {
        minInputJson.validate[CalculationSummary] shouldBe JsSuccess(minModel)
      }

      "provided with json with empty models" in {
        inputJsonWithEmptyModels.validate[CalculationSummary] shouldBe JsSuccess(minModel)
      }

      "provided with a filled top level json" in {
        filledInputJson.validate[CalculationSummary] shouldBe JsSuccess(filledModel)
      }
    }
  }
}
