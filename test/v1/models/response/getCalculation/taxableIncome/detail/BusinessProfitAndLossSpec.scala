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

package v1.models.response.getCalculation.taxableIncome.detail

import play.api.libs.json.Json
import support.UnitSpec
import v1.fixtures.getCalculation.taxableIncome.detail.BusinessProfitAndLossFixtures._

class BusinessProfitAndLossSpec extends UnitSpec {

  "BusinessProfitAndLoss" when {
    "reads with a valid json" should {
      "return JsSuccess" in {
        desJson("04", "02", "01").as[BusinessProfitAndLoss] shouldBe businessProfitAndLoss(selfEmployments, ukPropertyFhlObject, ukPropertyNonFhlObject)
      }
    }

    "reads with a valid json having only UkPropertyFhl details" should {
      "return JsSuccess" in {
        desJson("04").as[BusinessProfitAndLoss] shouldBe businessProfitAndLoss(None, ukPropertyFhlObject, None)
      }
    }

    "reads with a valid json having only UkPropertyNonFhl details" should {
      "return JsSuccess" in {
        desJson("02").as[BusinessProfitAndLoss] shouldBe businessProfitAndLoss(None, None, ukPropertyNonFhlObject)
      }
    }

    "reads with a valid json having only Self-Employments details" should {
      "return JsSuccess" in {
        desJson("01").as[BusinessProfitAndLoss] shouldBe businessProfitAndLoss(selfEmployments, None, None)
      }
    }

    "reads with a valid json with no details" should {
      "return JsSuccess" in {
        desJson("05", "06").as[BusinessProfitAndLoss] shouldBe businessProfitAndLoss(None, None, None)
      }
    }

    "writes a object with all fields" should {
      "return a valid json" in {
        Json.toJson(businessProfitAndLoss(selfEmployments, ukPropertyFhlObject, ukPropertyNonFhlObject)) shouldBe mtdJson
      }
    }
  }
}
