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

package v1.models.response.getCalculation.taxableIncome.detail.ukPropertyNonFhl.summary

import play.api.libs.json.{JsObject, JsSuccess, Json}
import support.UnitSpec
import v1.fixtures.taxableIncome.detail.ukPropertyNonFhl.LossClaimsSummaryFixtures._
import v1.models.utils.JsonErrorValidators

class LossClaimsSummarySpec extends UnitSpec with JsonErrorValidators {


  "LossClaimSummary" when {

    val emptyLossClaimSummary = LossClaimsSummary(None,None,None)

    "read from valid Json" should {

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
        lossClaimSummaryDesJson.as[LossClaimsSummary] shouldBe lossClaimsSummaryModel
      }
    }

    "read from empty Json" should {
      "return a JsSuccess" in {
        JsObject.empty.as[LossClaimsSummary] shouldBe emptyLossClaimSummary
      }
      "with the expected LossClaimSummary object" in {
        lossClaimSummaryDesJson.as[LossClaimsSummary] shouldBe lossClaimsSummaryModel
      }
    }

    "written to JSON" should {
      "return the expected JsObject" in {
        Json.toJson(lossClaimsSummaryModel) shouldBe lossClaimsSummaryJson
      }
    }

    "written from empty JSON" should {
      "return an empty JsObject" in {
        Json.toJson(emptyLossClaimSummary) shouldBe JsObject.empty
      }
    }
  }

}
