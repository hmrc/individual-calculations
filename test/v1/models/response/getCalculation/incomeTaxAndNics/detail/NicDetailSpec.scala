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

package v1.models.response.getCalculation.incomeTaxAndNics.detail

import play.api.libs.json.Json
import support.UnitSpec
import v1.fixtures.getCalculation.incomeTaxAndNics.detail.NicDetailFixtures._

class NicDetailSpec extends UnitSpec {

  "NicDetail" should {

    "read from json correctly" when {

      "provided with empty json" in {
        emptyJson.as[NicDetail] shouldBe NicDetail.empty
      }

      "provided with empty models in the json" in {
        inputJsonWithEmptyModels.as[NicDetail] shouldBe NicDetail.empty
      }

      "provided with filled json" in {
        filledJson.as[NicDetail] shouldBe filledModel
      }
    }

    "write to json correctly" when {

      "provided with an empty model" in {
        Json.toJson(NicDetail.empty) shouldBe emptyJson
      }

      "provided with a filled model" in {
        Json.toJson(filledModel) shouldBe outputJson
      }
    }
  }
}
