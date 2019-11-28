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

package v1.models.response.getCalculation.taxableIncome.detail.ukPropertyFhl.detail

import play.api.libs.json.{JsObject, JsSuccess, Json}
import support.UnitSpec
import v1.fixtures.getCalculation.taxableIncome.detail.ukPropertyFhl.LossClaimsDetailFixtures._

class LossClaimsDetailSpec extends UnitSpec {

  "LossClaimsDetail" when {
    "reads a valid json" should {

      "return a JsSuccess" in {
        desJson.validate[LossClaimsDetail] shouldBe a[JsSuccess[_]]
      }

      "return a LossClaimsDetail object with all fields" in {
        desJson.as[LossClaimsDetail] shouldBe lossClaimsDetail
      }

      "return a LossClaimsDetail object with multiple LossBroughtForward fields" in {
        desJsonWithMultipleLBF.as[LossClaimsDetail] shouldBe lossClaimsDetailWithMultipleLBF
      }

      "return a LossClaimsDetail object without LossBroughtForward" in {
        desJsonWithoutLossesBroughtForward.as[LossClaimsDetail] shouldBe lossClaimsDetailWithoutLossBroughtForward
      }

      "return a LossClaimsDetail object without ResultOfClaimApplied" in {
        desJsonWithoutResultOfClaims.as[LossClaimsDetail] shouldBe lossClaimsDetailResultOfClaimApplied
      }

      "return a LossClaimsDetail object without DefaultCarriedForwardLoss" in {
        desJsonWithoutDefaultCarriedForwardLosses.as[LossClaimsDetail] shouldBe lossClaimsDetailDefaultCarriedForwardLoss
      }

      "return an empty LossClaimsDetail object" in {
        desJsonWithNoFhlDetails.as[LossClaimsDetail] shouldBe LossClaimsDetail(None,None,None)
      }
    }

    "writes to Json" should {
      "return the expected JsObject" in {
        Json.toJson(lossClaimsDetail) shouldBe mtdJson
      }

      "return an empty JsObject" in {
        Json.toJson(LossClaimsDetail(None,None,None)) shouldBe JsObject.empty
      }
    }
  }
}
