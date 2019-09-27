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

package v1.models.response.getCalculation.endOfYearEstimate.detail

import play.api.libs.json.{ JsError, JsObject, JsSuccess, Json }
import support.UnitSpec
import v1.fixtures.endOfYearEstimate.detail.EoyEstimateUkPropertyFHLFixtures._

class EoyEstimateUkPropertyFHLSpec extends UnitSpec {

  "EoyEstimateUkPropertyFHL" when {
    "read from valid Json" should {
      "return a JsSuccess" in {
        eoyEstimateUkPropertyFHLDesJson.validate[EoyEstimateUkPropertyFHL] shouldBe a[JsSuccess[_]]
      }
      "with the expected EoyEstimateUkPropertyFHL object" in {
        eoyEstimateUkPropertyFHLDesJson.as[EoyEstimateUkPropertyFHL] shouldBe eoyEstimateUkPropertyFHLResponse
      }
    }

    "read from Json with missing optional fields" should {
      "return the expected EoyEstimateUkPropertyFHL object" in {
        eoyEstimateUkPropertyFHLDesJsonMissingFields.as[EoyEstimateUkPropertyFHL] shouldBe eoyEstimateUkPropertyFHLResponseFactory(
          finalised = None)
      }
    }

    "read from invalid Json" should {
      "return a JsError" in {
        JsObject.empty.validate[EoyEstimateUkPropertyFHL] shouldBe a[JsError]
      }
    }

    "written to Json" should {
      "return the expected JsObject" in {
        Json.toJson(eoyEstimateUkPropertyFHLResponse) shouldBe eoyEstimateUkPropertyFHLWrittenJson
      }
    }

    "written to Json with missing optional fields" should {
      "return the expected JsObject" in {
        Json.toJson(eoyEstimateUkPropertyFHLResponseFactory(finalised = None)) shouldBe eoyEstimateUkPropertyFHLWrittenJsonMissingFields
      }
    }

  }
}
