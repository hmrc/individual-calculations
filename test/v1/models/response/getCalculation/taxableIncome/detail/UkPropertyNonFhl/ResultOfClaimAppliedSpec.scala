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

package v1.models.response.getCalculation.taxableIncome.detail.UkPropertyNonFhl

import play.api.libs.json.{JsSuccess, Json}
import support.UnitSpec
import v1.fixtures.taxableIncome.detail.nonFhlProperty.ResultOfClaimAppliedFixtures
import v1.models.response.getCalculation.taxableIncome.detail.UkPropertyNonFhl.detail.ResultOfClaimApplied

class ResultOfClaimAppliedSpec extends UnitSpec {

  "Result of Claim Applied" should {

    "read from json correctly" when {

      "provided with json with all optional fields" in {
        val result = ResultOfClaimAppliedFixtures.resultOfClaimAppliedDesJson.validate[ResultOfClaimApplied]
        result shouldBe a[JsSuccess[_]]
        result.get shouldBe ResultOfClaimAppliedFixtures.resultOfClaimAppliedModel
      }

      "provided with json without optional fields" in {
        val result = ResultOfClaimAppliedFixtures.resultOfClaimAppliedDesJsonWithoutOptionals.validate[ResultOfClaimApplied]
        result shouldBe a[JsSuccess[_]]
        result.get shouldBe ResultOfClaimAppliedFixtures.resultOfClaimAppliedModelWithoutOptionals
      }
    }

    "write to json correctly" when {

      "provided with a model with all optional fields" in {
        Json.toJson(ResultOfClaimAppliedFixtures.resultOfClaimAppliedModel) shouldBe ResultOfClaimAppliedFixtures.resultOfClaimAppliedMtdJson
      }

      "provided with a model without optional fields" in {
        Json.toJson(ResultOfClaimAppliedFixtures.resultOfClaimAppliedModelWithoutOptionals) shouldBe
          ResultOfClaimAppliedFixtures.resultOfClaimAppliedMtdJsonWithoutOptionals
      }
    }
  }
}
