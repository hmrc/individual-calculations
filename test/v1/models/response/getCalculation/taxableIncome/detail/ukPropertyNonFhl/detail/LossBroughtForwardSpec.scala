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

package v1.models.response.getCalculation.taxableIncome.detail.ukPropertyNonFhl.detail

import play.api.libs.json.{JsSuccess, Json}
import support.UnitSpec
import v1.fixtures.getCalculation.taxableIncome.detail.ukPropertyNonFhl.LossBroughtForwardFixtures

class LossBroughtForwardSpec extends UnitSpec {

  "Loss Brought Forward" should {

    "read correctly from json" when {

      "provided with valid json" in {
        val result = LossBroughtForwardFixtures.lossBroughtForwardDesJson.validate[LossBroughtForward]
        result shouldBe a[JsSuccess[_]]
        result.get shouldBe LossBroughtForwardFixtures.lossBroughtForwardModel
      }
    }

    "write correctly to json" when {

      "provided with a valid model" in {
        Json.toJson(LossBroughtForwardFixtures.lossBroughtForwardModel) shouldBe
          LossBroughtForwardFixtures.lossBroughtForwardMtdJson
      }
    }
  }
}
