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

package v1.models.response.getCalculation.taxableIncome.detail.ukPropertyFhl

import play.api.libs.json.{JsObject, JsSuccess, Json}
import support.UnitSpec
import v1.fixtures.taxableIncome.detail.ukPropertyFhl.UkPropertyFhlFixtures._

class UkPropertyFhlSpec extends UnitSpec {

  "UkPropertyFhl" when {
    "reads with a valid json" should {
      "return JsSuccess" in {
        desJson.validate[UkPropertyFhl] shouldBe a[JsSuccess[_]]
      }

      "return the expected ukPropertyFhl object" in {
        desJson.as[UkPropertyFhl] shouldBe ukPropertyFhlObject
      }

      "return the ukPropertyFhl with out LossClaimsDetail object" in {
        desJsonWithOutLossClaimsDetail.as[UkPropertyFhl] shouldBe ukPropertyFhlWithOutLossClaimsDetailObject
      }
    }

    "writes with a valid object" should {
      "return a valid json" in {
        Json.toJson(ukPropertyFhlObject) shouldBe mtdUkPropertyFhlObj
      }

      "return a valid json with out LossClaimsDetail" in {
        Json.toJson(ukPropertyFhlWithOutLossClaimsDetailObject) shouldBe mtdUkPropertyFhlObjWithOutLossClaimsDetail
      }

      "return a empty json with empty UkPropertyFhl object" in {
        Json.toJson(emptyUkPropertyFhl) shouldBe
          JsObject.empty
      }
    }
  }
}
