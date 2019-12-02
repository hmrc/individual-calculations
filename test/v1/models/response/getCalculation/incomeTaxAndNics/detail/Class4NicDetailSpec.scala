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

package v1.models.response.getCalculation.incomeTaxAndNics.detail

import play.api.libs.json.{JsObject, Json}
import support.UnitSpec
import v1.fixtures.getCalculation.incomeTaxAndNics.detail.Class4NicDetailFixtures._

class Class4NicDetailSpec extends UnitSpec {

  "Class4NicDetail" should {

    "write correctly to json" in {
      Json.toJson(model) shouldBe mtdJson
    }

    "read correctly from json" in {
      desJson.as[Class4NicDetail] shouldBe model
    }

    "read empty json to an empty object" in {
      JsObject.empty.as[Class4NicDetail] shouldBe Class4NicDetail.empty
    }
  }
}
