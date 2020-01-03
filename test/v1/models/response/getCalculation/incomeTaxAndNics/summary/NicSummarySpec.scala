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

package v1.models.response.getCalculation.incomeTaxAndNics.summary

import play.api.libs.json.Json
import support.UnitSpec
import v1.fixtures.getCalculation.incomeTaxAndNics.summary.NicSummaryFixtures._

class NicSummarySpec extends UnitSpec {

  "NicSummary" should {

    "read correctly from json" when {

      "provided with empty json" in {
        emptyJson.as[NicSummary] shouldBe NicSummary.empty
      }

      "provided with json with empty models" in {
        emptyModelJson.as[NicSummary] shouldBe NicSummary.empty
      }

      "provided with filled json" in {
        filledJson.as[NicSummary] shouldBe filledModel
      }
    }

    "write correctly to json" when {

      "provided with an empty model" in {
        Json.toJson(NicSummary.empty) shouldBe emptyJson
      }

      "provided with a filled model" in {
        Json.toJson(filledModel) shouldBe outputJson
      }
    }
  }
}
