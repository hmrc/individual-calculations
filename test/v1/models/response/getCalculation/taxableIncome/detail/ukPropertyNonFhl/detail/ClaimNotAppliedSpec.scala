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
import v1.fixtures.taxableIncome.detail.ukPropertyNonFhl.ClaimNotAppliedFixtures

class ClaimNotAppliedSpec extends UnitSpec {

  "Claim Not Applied" should {

    "read correctly from json" when {

      "provided with valid json" in {
        val result = ClaimNotAppliedFixtures.claimNotAppliedDesJson.validate[ClaimNotApplied]
        result shouldBe a[JsSuccess[_]]
        result.get shouldBe ClaimNotAppliedFixtures.claimNotAppliedModel
      }
    }

    "write correctly to json" when {

      "provided with a valid model" in {
        Json.toJson(ClaimNotAppliedFixtures.claimNotAppliedModel) shouldBe ClaimNotAppliedFixtures.claimNotAppliedMtdJson
      }
    }
  }
}
