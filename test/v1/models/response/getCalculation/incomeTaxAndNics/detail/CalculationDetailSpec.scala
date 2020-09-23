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
        Json.toJson(calculationDetailMinModel) shouldBe calculationDetailMinOutputJson
      }

      "provided with a top level model" in {
        Json.toJson(calculationDetailFilledModel) shouldBe calculationDetailOutputJson
      }
    }

    "read from json correctly" when {
      "provided with empty json" in {
        calculationDetailMinJson.validate[CalculationDetail] shouldBe JsSuccess(calculationDetailMinModel)
      }

      "provided with json with empty models" in {
        calculationDetailInputJsonWithEmptyModels.validate[CalculationDetail] shouldBe JsSuccess(calculationDetailMinModel)
      }

      "provided with json containing all top level models" in {
        calculationDetailFilledJson.validate[CalculationDetail] shouldBe JsSuccess(calculationDetailFilledModel)
      }
    }
  }
}
