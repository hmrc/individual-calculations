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

package v1.models.response.getCalculation.taxableIncome.detail.ukPropertyNonFhl

import play.api.libs.json.{JsSuccess, Json}
import support.UnitSpec
import v1.fixtures.taxableIncome.detail.ukPropertyNonFhl.UkPropertyNonFhlFixtures

class UkPropertyNonFhlSpec extends UnitSpec {

  "UK Property Non-FHL" should {

    "read from json correctly" when {

      "provided with valid json with all optional values and a mix of valid and invalid types" in {
        val result = UkPropertyNonFhlFixtures.ukPropertyNonFhlDesJson.validate[UkPropertyNonFhl]
        result shouldBe a[JsSuccess[_]]
        result.get shouldBe UkPropertyNonFhlFixtures.ukPropertyNonFhlModel
      }

      "provided with valid json with all optional values and only invalid types" in {
        val result = UkPropertyNonFhlFixtures.ukPropertyNonFhlDesJsonWithoutValidTypes.validate[UkPropertyNonFhl]
        result shouldBe a[JsSuccess[_]]
        result.get shouldBe UkPropertyNonFhl.empty
      }

      "provided with valid json without optional values" in {
        val result = UkPropertyNonFhlFixtures.ukPropertyNonFhlDesJsonWithoutOptionals.validate[UkPropertyNonFhl]
        result shouldBe a[JsSuccess[_]]
        result.get shouldBe UkPropertyNonFhl.empty
      }

      "provided with empty json" in {
        val result = UkPropertyNonFhlFixtures.emptyJson.validate[UkPropertyNonFhl]
        result shouldBe a[JsSuccess[_]]
        result.get shouldBe UkPropertyNonFhl.empty
      }
    }

    "write to json correctly" when {

      "provided with a valid model with all optional values" in {
        Json.toJson(UkPropertyNonFhlFixtures.ukPropertyNonFhlModel) shouldBe UkPropertyNonFhlFixtures.ukPropertyNonFhlMtdJson
      }

      "provided with a valid model with no optional values" in {
        Json.toJson(UkPropertyNonFhl.empty) shouldBe UkPropertyNonFhlFixtures.emptyJson
      }
    }
  }
}
