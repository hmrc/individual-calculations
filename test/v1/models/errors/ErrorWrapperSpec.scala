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

package v1.models.errors

import play.api.libs.json.Json
import support.UnitSpec
import v1.fixtures.ErrorFixtures.{MultipleErrors, SingleError}

class ErrorWrapperSpec extends UnitSpec {

  "Rendering a error response with one error" should {
    "generate the correct JSON" in {
      Json.toJson(SingleError.model) shouldBe SingleError.json
    }
  }

  "Rendering a error response with one error and an empty sequence of errors" should {
    "generate the correct JSON" in {
      Json.toJson(SingleError.modelWithEmptySeq) shouldBe SingleError.json
    }
  }

  "Rendering a error response with two errors" should {
    "generate the correct JSON" in {
      Json.toJson(MultipleErrors.model) shouldBe MultipleErrors.json
    }
  }

}
