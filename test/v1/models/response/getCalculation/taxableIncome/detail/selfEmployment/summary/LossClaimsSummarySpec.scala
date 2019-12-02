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

package v1.models.response.getCalculation.taxableIncome.detail.selfEmployment.summary

import play.api.libs.json.{JsError, JsSuccess, Json}
import support.UnitSpec
import v1.fixtures.getCalculation.taxableIncome.detail.selfEmployments.summary.LossClaimSummaryFixtures._

class LossClaimsSummarySpec extends UnitSpec {

  "LossClaimSummary" when {
    "read from valid Json" should {
      "return a JsSuccess" in {
        lossClaimSummaryDesJson.validate[LossClaimsSummary] shouldBe a[JsSuccess[_]]
      }
      "with the expected LossClaimsSummary object" in {
        lossClaimSummaryDesJson.as[LossClaimsSummary] shouldBe lossClaimsSummaryResponse
      }
    }

    "read from empty Json" should {
      "return an empty LossClaimsSummary object" in {
        emptyJson.as[LossClaimsSummary] shouldBe LossClaimsSummary.empty
      }
    }

    "read from invalid Json" should {
      "return a JsError" in {
        lossClaimSummaryInvalidJson.validate[LossClaimsSummary] shouldBe a[JsError]
      }
    }

    "written to Json" should {
      "return the expected JsObject" in {
        Json.toJson(lossClaimsSummaryResponse) shouldBe lossClaimSummaryWrittenJson
      }
    }

    "written from an empty LossClaimsSummary object" should {
      "return an empty JsObject" in {
        Json.toJson(LossClaimsSummary.empty) shouldBe emptyJson
      }
    }
  }

}
