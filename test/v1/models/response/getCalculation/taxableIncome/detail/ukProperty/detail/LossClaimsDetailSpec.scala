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

package v1.models.response.getCalculation.taxableIncome.detail.ukProperty.detail

import play.api.libs.json.JsSuccess
import support.UnitSpec
import v1.fixtures.taxableIncome.ukProperty.LossClaimsDetailFixtures._

class LossClaimsDetailSpec extends UnitSpec {

  "LossClaimsDetail" when {
    "reads a valid json" should {

      "return a JsSuccess" in {
        desJson.validate[LossClaimsDetail] shouldBe a[JsSuccess[LossClaimsDetail]]
      }

      "return a LossClaimsDetail object with all fields" in {
        desJson.validate[LossClaimsDetail].get shouldBe lossClaimsDetail
      }

      "return a LossClaimsDetail object without LossBroughtForward" in {
        desJsonWithoutLossesBroughtForward.validate[LossClaimsDetail].get shouldBe lossClaimsDetailWithoutLossBroughtForward
      }

      "return a LossClaimsDetail object without ResultOfClaimApplied" in {
        desJsonWithoutResultOfClaims.validate[LossClaimsDetail].get shouldBe lossClaimsDetailResultOfClaimApplied
      }

      "return a LossClaimsDetail object without DefaultCarriedForwardLoss" in {
        desJsonWithoutDefaultCarriedForwardLosses.validate[LossClaimsDetail].get shouldBe lossClaimsDetailDefaultCarriedForwardLoss
      }
    }
  }
}
