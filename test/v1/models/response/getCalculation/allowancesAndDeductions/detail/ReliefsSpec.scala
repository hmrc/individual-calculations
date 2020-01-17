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

package v1.models.response.getCalculation.allowancesAndDeductions.detail

import play.api.libs.json.{JsObject, Json}
import support.UnitSpec
import v1.fixtures.getCalculation.allowancesAndDeductions.detail.ReliefFixtures._
import v1.models.utils.JsonErrorValidators

class ReliefsSpec extends UnitSpec with JsonErrorValidators {

  "reads" should {
    "return a valid object" when {
      "valid json is passed" in {
        desJson.as[Reliefs] shouldBe Reliefs(Some(ResidentialFinanceCosts(1000.25, Some(1000.25), 2, 1000.25)))
      }

      "json has no fields" in {
        JsObject.empty.as[Reliefs] shouldBe Reliefs.empty
      }
    }
  }

  "writes" should {
    "return a valid json" when {
      "ResidentialFinanceCosts object has data" in {
        Json.toJson(Reliefs(Some(ResidentialFinanceCosts(1000.25, Some(1000.25), 2, 1000.25)))) shouldBe mtdJson
      }
    }
  }
}
