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

package v1.models.response.getCalculation.endOfYearEstimate

import play.api.libs.json.{JsError, JsObject, JsSuccess, Json}
import support.UnitSpec
import v1.fixtures.getCalculation.endOfYearEstimate.EoyEstimateFixtures._

class EoyEstimateSpec extends UnitSpec {

  "EoyEstimate" when {
    "read from valid Json" should {
      "return a JsSuccess" in {
        eoyEstimateDesJson.validate[EoyEstimate] shouldBe a[JsSuccess[_]]
      }
      "with the expected EoyEstimate object" in {
        eoyEstimateDesJson.as[EoyEstimate] shouldBe eoyEstimateResponse
      }
    }

    "read from empty Json" should {
      "return an empty EoyEstimate object" in {
        JsObject.empty.as[EoyEstimate] shouldBe EoyEstimate()
      }
    }

    "read from invalid Json" should {
      "return a JsError" in {
        eoyEstimateInvalidJson.validate[EoyEstimate] shouldBe a[JsError]
      }
    }

    "written to Json" should {
      "return the expected JsObject" in {
        Json.toJson(eoyEstimateResponse) shouldBe eoyEstimateWrittenJson
      }
    }

    "written to Json with empty detail and summary objects" should {
      "return the expected JsObject" in {
        Json.toJson(EoyEstimate()) shouldBe eoyEstimateWrittenJsonEmpty
      }
    }
  }

}
