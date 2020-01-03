/*
 * Copyright 2020 HM Revenue & Customs
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

package v1.models.response.getCalculation.incomeTaxAndNics.detail

import play.api.libs.json.{JsSuccess, Json}
import support.UnitSpec
import v1.fixtures.getCalculation.incomeTaxAndNics.detail.CalculationDetailFixtures._

class CalculationDetailSpec extends UnitSpec {

  "CalculationDetail" should {

    "write to json correctly" when {

      "provided with a minimal model" in {
        Json.toJson(minModel) shouldBe minOutputJson
      }

      "provided with a top level model" in {
        Json.toJson(filledModel) shouldBe outputJson
      }
    }

    "read from json correctly" when {

      "provided with empty json" in {
        minJson.validate[CalculationDetail] shouldBe JsSuccess(minModel)
      }

      "provided with json with empty models" in {
        inputJsonWithEmptyModels.validate[CalculationDetail] shouldBe JsSuccess(minModel)
      }

      "provided with json containing all top level models" in {
        filledJson.validate[CalculationDetail] shouldBe JsSuccess(filledModel)
      }
    }
  }
}
