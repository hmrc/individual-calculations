/*
 * Copyright 2021 HM Revenue & Customs
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

package v2.models.response.getCalculation.endOfYearEstimate.detail

import play.api.libs.json.{JsError, Json}
import support.UnitSpec
import v2.fixtures.getCalculation.endOfYearEstimate.detail.EoyEstimateStateBenefitsFixtures._

class EoyEstimateStateBenefitsSpec extends UnitSpec {

  "EoyEstimateStateBenefits" when {
    "read from valid Json" should {
      "return the expected EoyEstimateStateBenefits object" in {
        eoyEstimateStateBenefitsDesJson.as[EoyEstimateStateBenefits] shouldBe eoyEstimateStateBenefitsResponse
      }
    }

    "read from invalid Json" should {
      "return a JsError" in {
        eoyEstimateStateBenefitsInvalidJson.validate[EoyEstimateStateBenefits] shouldBe a[JsError]
      }
    }

    "written to Json" should {
      "return the expected JsObject" in {
        Json.toJson(eoyEstimateStateBenefitsResponse) shouldBe eoyEstimateStateBenefitsWrittenJson
      }
    }
  }
}