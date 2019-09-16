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

import play.api.libs.json._
import support.UnitSpec
import v1.fixtures.taxableIncome.TaxableIncomeFixtures._
import v1.fixtures.taxableIncome.detail.BusinessProfitAndLossFixtures._

class BusinessProfitAndLossSpec extends UnitSpec {

  "BusinessProfitAndLoss" when {
    "read from valid Json" should {
      "return a JsSuccess" in {
        businessProfitAndLossDesJson.validate[BusinessProfitAndLoss] shouldBe a[JsSuccess[_]]
      }
      "with the expected BusinessProfitAndLoss object" in {
        businessProfitAndLossDesJson.as[BusinessProfitAndLoss] shouldBe businessProfitAndLossResponse
      }
    }

    "read from valid Json with missing optional fields" should {
      "return a JsSuccess" in {
        selfEmploymentsOnlyDesJson.validate[BusinessProfitAndLoss] shouldBe a[JsSuccess[_]]
      }
      "with the expected BusinessProfitAndLoss object" in {
        selfEmploymentsOnlyDesJson.as[BusinessProfitAndLoss] shouldBe selfEmploymentsOnlyResponse
      }
    }

    "read from empty Json" should {
      "return a JsSuccess" in {
        selfEmploymentsOnlyDesJson.validate[BusinessProfitAndLoss] shouldBe a[JsSuccess[_]]
      }
      "with an empty BusinessProfitAndLoss object" in {
        emptyJson.as[BusinessProfitAndLoss].isEmpty shouldBe true
      }
    }

    "read from invalid Json" should {
      "return a JsError" in {
        businessProfitAndLossInvalidJson.validate[BusinessProfitAndLoss] shouldBe a[JsError]
      }
    }

    "written to Json with all fields present" should {
      "return the expected JsObject" in {
        Json.toJson(businessProfitAndLossResponse) shouldBe businessProfitAndLossWrittenJson
      }
    }

    "written to Json with missing optional fields" should {
      "return the expected JsObject" in {
        Json.toJson(selfEmploymentsOnlyResponse) shouldBe selfEmploymentsOnlyWrittenJson
      }
    }

    "written to Json from an empty BusinessProfitAndLoss object" should {
      "return an empty Json object" in {
        Json.toJson(emptyBusinessProfitAndLossResponse) shouldBe emptyJson
      }
    }
  }

}
