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

package v2.models.response.common

import play.api.libs.json.{JsError, JsObject, Json}
import support.UnitSpec
import v2.fixtures.common.MetadataFixtures._

class MetadataSpec extends UnitSpec {

  "Metadata" when {
    "read from a valid JSON" should {
      "read the expected Metadata object" in {
        desJson.as[Metadata] shouldBe metadataResponse
      }

      "read optional fields as None with optional booleans false" in {
        desJsonWithoutOptionals.as[Metadata] shouldBe desJsonWithoutOptionalsAsModel
      }
    }

    "read from an invalid JSON" should {
      "return a JsError" in {
        invalidDesJson.validate[Metadata] shouldBe a[JsError]
      }
    }

    "written to JSON" should {
      "return the expected JsObject as per spec" in {
        Json.toJson(metadataResponse) shouldBe writtenJson
      }
    }

    "errors are not present" should {
      "read the calculationErrorCount as None" in {
        (desJson ++ Json.parse("""{
            |  "messages": {
            |  }
            |}""".stripMargin).as[JsObject]).as[Metadata] shouldBe metadataResponse
      }
    }

    "errors are present, but empty" should {
      "read the calculationErrorCount as None" in {
        (desJson ++ Json.parse("""{
            |  "messages": {
            |     "errors": [
            |     ]
            |  }
            |}""".stripMargin).as[JsObject]).as[Metadata] shouldBe metadataResponse
      }
    }

    "errors are present, and non empty" should {
      "read the calculationErrorCount as some the number of errors" in {
        (desJson ++ Json.parse("""{
            | "messages": {
            |   "errors": [
            |    {"id": "id1", "text": "text1"},
            |    {"id": "id2", "text": "text2"}
            |    ]
            |  }
            |}""".stripMargin).as[JsObject]).as[Metadata] shouldBe
          metadataResponse.copy(calculationErrorCount = Some(2))
      }
    }
  }
}
