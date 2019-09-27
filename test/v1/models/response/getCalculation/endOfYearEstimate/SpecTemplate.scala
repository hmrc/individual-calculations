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

package v1.models.response.getCalculation.endOfYearEstimate

import play.api.libs.json.{JsError, JsObject, JsSuccess, Json, OFormat}
import support.UnitSpec
import v1.fixtures.endOfYearEstimate.FixtureTemplate._

class SpecTemplate extends UnitSpec {

  "Placeholder" when {
    "read from valid Json" should {
      "return a JsSuccess" in {
        placeholderDesJson.validate[Placeholder] shouldBe a[JsSuccess[_]]
      }
      "with the expected Placeholder object" in {
        placeholderDesJson.as[Placeholder] shouldBe placeholderResponse
      }
    }

    "read from Json with missing optional fields" should {
      "return the expected Placeholder object" in {
        placeholderDesJsonMissingFields.as[Placeholder] shouldBe placeholderResponseFactory(totalNicAmount = None,
                                                                                                                 incomeTaxNicAmount = None)
      }
    }

    "read from empty Json" should {
      "return an empty Placeholder object" in {
        JsObject.empty.as[Placeholder] shouldBe Placeholder()
      }
    }

    "read from invalid Json" should {
      "return a JsError" in {
        placeholderInvalidJson.validate[Placeholder] shouldBe a[JsError]
      }
    }

    "written to Json" should {
      "return the expected JsObject" in {
        Json.toJson(placeholderResponse) shouldBe placeholderWrittenJson
      }
    }

    "written to Json with missing optional fields" should {
      "return the expected JsObject" in {
        Json.toJson(placeholderResponseFactory(totalNicAmount = None, incomeTaxNicAmount = None)) shouldBe placeholderWrittenJsonMissingFields
      }
    }
  }

}
