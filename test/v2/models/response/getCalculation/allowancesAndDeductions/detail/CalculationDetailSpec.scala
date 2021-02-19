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

package v2.models.response.getCalculation.allowancesAndDeductions.detail

import play.api.libs.json.{JsObject, Json}
import support.UnitSpec
import v2.fixtures.getCalculation.allowancesAndDeductions.detail.CalculationDetailFixtures._
import v2.models.utils.JsonErrorValidators

class CalculationDetailSpec extends UnitSpec with JsonErrorValidators {
  "reads" should {
    "return a valid object" when {
      "valid json is passed" in {
        desJson.as[CalculationDetail] shouldBe calculationDetail
      }

      "json has no fields" in {
        JsObject.empty.as[CalculationDetail] shouldBe CalculationDetail.empty
      }
    }
  }

  "writes" should {
    "return a valid json" when {
      "CalculationDetail object has data" in {
        Json.toJson(calculationDetail) shouldBe mtdJson
      }
    }
  }
}
