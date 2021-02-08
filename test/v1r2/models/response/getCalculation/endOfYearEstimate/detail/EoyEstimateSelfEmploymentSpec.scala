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

package v1r2.models.response.getCalculation.endOfYearEstimate.detail

import play.api.libs.json.{JsError, JsObject, JsSuccess, Json}
import support.UnitSpec
import v1r2.fixtures.getCalculation.endOfYearEstimate.detail.EoyEstimateSelfEmploymentFixtures._

class EoyEstimateSelfEmploymentSpec extends UnitSpec {

  "EoyEstimateSelfEmployment" when {
    "read from valid Json" should {
      "return a JsSuccess" in {
        eoyEstimateSelfEmploymentDesJson.validate[EoyEstimateSelfEmployment] shouldBe a[JsSuccess[_]]
      }
      "with the expected EoyEstimateSelfEmployment object" in {
        eoyEstimateSelfEmploymentDesJson.as[EoyEstimateSelfEmployment] shouldBe eoyEstimateSelfEmploymentResponse
      }
    }

    "read from Json with missing optional fields" should {
      "return the expected EoyEstimateSelfEmployment object" in {
        eoyEstimateSelfEmploymentDesJsonMissingFields.as[EoyEstimateSelfEmployment] shouldBe eoyEstimateSelfEmploymentResponseFactory(finalised = None)
      }
    }

    "read from invalid Json" should {
      "return a JsError" in {
        JsObject.empty.validate[EoyEstimateSelfEmployment] shouldBe a[JsError]
      }
    }

    "written to Json" should {
      "return the expected JsObject" in {
        Json.toJson(eoyEstimateSelfEmploymentResponse) shouldBe eoyEstimateSelfEmploymentWrittenJson
      }
    }

    "written to Json with missing optional fields" should {
      "return the expected JsObject" in {
        Json.toJson(eoyEstimateSelfEmploymentResponseFactory(finalised = None)) shouldBe eoyEstimateSelfEmploymentWrittenJsonMissingFields
      }
    }

  }
}
