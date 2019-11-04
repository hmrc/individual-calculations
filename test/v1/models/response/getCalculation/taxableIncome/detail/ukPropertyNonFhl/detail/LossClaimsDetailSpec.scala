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
import v1.fixtures.taxableIncome.detail.ukPropertyNonFhl.LossClaimsDetailFixtures

class LossClaimsDetailSpec extends UnitSpec {

  "Loss Claims Detail" should {

    "read correctly from json" when {

      "provided with json with all optional values and a mixture of valid and invalid income sources" in {
        val result = LossClaimsDetailFixtures.lossClaimsDetailDesJson.validate[LossClaimsDetail]
        result shouldBe a[JsSuccess[_]]
        result.get shouldBe LossClaimsDetailFixtures.lossClaimsDetailModel
      }

      "provided with json with all optional values and only invalid income sources" in {
        val result = LossClaimsDetailFixtures.lossClaimsDetailDesJsonWithoutValidTypes.validate[LossClaimsDetail]
        result shouldBe a[JsSuccess[_]]
        result.get shouldBe LossClaimsDetail.empty
      }

      "provided with json without optional values" in {
        val result = LossClaimsDetailFixtures.lossClaimsDetailDesJsonWithoutOptionals.validate[LossClaimsDetail]
        result shouldBe a[JsSuccess[_]]
        result.get shouldBe LossClaimsDetail.empty
      }

      "provided with empty json" in {
        val result = LossClaimsDetailFixtures.emptyJson.validate[LossClaimsDetail]
        result shouldBe a[JsSuccess[_]]
        result.get shouldBe LossClaimsDetail.empty
      }
    }

    "write correctly to json" when {

      "provided with a model with all optional fields" in {
        Json.toJson(LossClaimsDetailFixtures.lossClaimsDetailModel) shouldBe LossClaimsDetailFixtures.lossClaimsDetailMtdJson
      }

      "provided with a model with no optional fields" in {
        Json.toJson(LossClaimsDetail.empty) shouldBe LossClaimsDetailFixtures.emptyJson
      }
    }
  }
}
