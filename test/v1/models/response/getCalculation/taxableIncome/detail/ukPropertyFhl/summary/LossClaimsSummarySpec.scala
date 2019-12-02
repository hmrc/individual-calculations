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

package v1.models.response.getCalculation.taxableIncome.detail.ukPropertyFhl.summary

import play.api.libs.json.{JsSuccess, Json}
import support.UnitSpec
import v1.fixtures.getCalculation.taxableIncome.detail.ukPropertyFhl.LossClaimSummaryFixtures._
import v1.models.utils.JsonErrorValidators

class LossClaimsSummarySpec extends UnitSpec with JsonErrorValidators {


  "LossClaimSummary" when {
    "read from valid Json" should {

      testPropertyType[LossClaimsSummary](lossClaimSummaryDesJson)(
        path = "/lossForCSFHL",
        replacement = "TEST".toJson,
        expectedError = JsonError.NUMBER_FORMAT_EXCEPTION)

      testPropertyType[LossClaimsSummary](lossClaimSummaryDesJson)(
        path = "/totalBroughtForwardIncomeTaxLosses",
        replacement = "TEST".toJson,
        expectedError = JsonError.NUMBER_FORMAT_EXCEPTION)

      testPropertyType[LossClaimsSummary](lossClaimSummaryDesJson)(
        path = "/broughtForwardIncomeTaxLossesUsed",
        replacement = "TEST".toJson,
        expectedError = JsonError.NUMBER_FORMAT_EXCEPTION)

      testPropertyType[LossClaimsSummary](lossClaimSummaryDesJson)(
        path = "/totalIncomeTaxLossesCarriedForward",
        replacement = "TEST".toJson,
        expectedError = JsonError.NUMBER_FORMAT_EXCEPTION)

      "return a JsSuccess" in {
        lossClaimSummaryDesJson.validate[LossClaimsSummary] shouldBe a[JsSuccess[_]]
      }

      "with the expected LossClaimSummary object" in {
        lossClaimSummaryDesJson.as[LossClaimsSummary] shouldBe lossClaimsSummaryResponse
      }
    }

    "read from empty Json" should {
      "return a JsSuccess" in {
        emptyJson.as[LossClaimsSummary] shouldBe LossClaimsSummary.empty
      }
      "with the expected LossClaimSummary object" in {
        lossClaimSummaryDesJson.as[LossClaimsSummary] shouldBe lossClaimsSummaryResponse
      }
    }

    "written to JSON" should {
      "return the expected JsObject" in {
        Json.toJson(lossClaimsSummaryResponse) shouldBe lossClaimSummaryWrittenJson
      }
    }

    "written from empty JSON" should {
      "return an empty JsObject" in {
        Json.toJson(LossClaimsSummary.empty) shouldBe emptyJson
      }
    }
  }

}
