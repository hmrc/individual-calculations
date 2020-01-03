/*
 * Copyright 2020 HM Revenue & Customs
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

import play.api.libs.json.{JsError, JsObject, JsSuccess, Json}
import support.UnitSpec
import v1.fixtures.getCalculation.endOfYearEstimate.detail.EoyEstimateDetailFixtures._

class EoyEstimateDetailSpec extends UnitSpec {

  "EoyEstimateDetail" when {
    "read from valid Json" should {
      "return a JsSuccess" in {
        eoyEstimateDetailDesJson.validate[EoyEstimateDetail] shouldBe a[JsSuccess[_]]
      }
      "containing the expected EoyEstimateDetail object" in {
        eoyEstimateDetailDesJson.as[EoyEstimateDetail] shouldBe eoyEstimateDetailResponse
      }
    }

    "read from Json with some missing optional fields" should {
      "return the expected EoyEstimateDetail object" in {
        eoyEstimateDetailDesJsonSomeMissingOptionals.as[EoyEstimateDetail] shouldBe eoyEstimateDetailResponseReduced
      }
    }

    "read from Json with all missing optional fields" should {
      "return the expected EoyEstimateDetail object" in {
        eoyEstimateDetailDesJsonAllMissingOptionals.as[EoyEstimateDetail] shouldBe EoyEstimateDetail.empty
      }
    }

    "read from empty Json" should {
      "return an empty EoyEstimateDetail object" in {
        JsObject.empty.as[EoyEstimateDetail] shouldBe EoyEstimateDetail.empty
      }
    }

    "read from Json where some objects have an invalid income source type" should {
      "not read those objects" in {
        eoyEstimateDetailDesJsonSomeWrongIncomeSourceTypes.as[EoyEstimateDetail] shouldBe eoyEstimateDetailResponseReduced
      }
    }

    "read from Json where all objects have an invalid income source type" should {
      "return an empty EoyEstimateDetail object" in {
        eoyEstimateDetailDesJsonAllWrongIncomeSourceTypes.as[EoyEstimateDetail] shouldBe EoyEstimateDetail.empty
      }
    }

    "read from invalid Json" should {
      "return a JsError" in {
        eoyEstimateDetailInvalidJson.validate[EoyEstimateDetail] shouldBe a[JsError]
      }
    }

    "written to Json" should {
      "return the expected JsObject" in {
        Json.toJson(eoyEstimateDetailResponse) shouldBe eoyEstimateDetailWrittenJson
      }
    }

    "written from an EoyEstimateDetail object with missing optional fields" should {
      "return an empty JsObject" in {
        Json.toJson(eoyEstimateDetailResponseReduced) shouldBe eoyEstimateDetailWrittenJsonMissingOptionals
      }
    }

    "written from an empty EoyEstimateDetail object" should {
      "return an empty JsObject" in {
        Json.toJson(EoyEstimateDetail.empty) shouldBe JsObject.empty
      }
    }

  }
}
