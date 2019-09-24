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

import play.api.libs.json.{JsError, JsSuccess, Json}
import support.UnitSpec
import v1.fixtures.taxableIncome.detail.PayPensionsProfitFixtures._

class PayPensionsProfitSpec extends UnitSpec {

  "PayPensionsProfit" when {
    "read from valid Json" should {
      "return a JsSuccess" in {
        desJson("04", "02").validate[PayPensionsProfit] shouldBe a[JsSuccess[_]]
      }
      "with the expected BusinessProfitAndLoss object" in {
        desJson("04", "02").as[PayPensionsProfit] shouldBe payPensionsProfitResponse
      }
    }

    "read from valid Json with missing optional fields" should {
      "return a JsSuccess" in {
        payPensionsProfitDesJsonWithoutOptionalFields.validate[PayPensionsProfit] shouldBe a[JsSuccess[_]]
      }
      "with the expected BusinessProfitAndLoss object" in {
        payPensionsProfitDesJsonWithoutOptionalFields.as[PayPensionsProfit] shouldBe payPensionsProfitResponseWithoutOptionalFields
      }
    }

    "read from invalid Json" should {
      "return a JsError" in {
        payPensionsProfitInvalidJson.validate[PayPensionsProfit] shouldBe a[JsError]
      }
    }

    "written to Json with all fields present" should {
      "return the expected JsObject" in {
        Json.toJson(payPensionsProfitResponse) shouldBe payPensionsProfitWrittenJson
      }
    }

    "written to Json with missing optional fields" should {
      "return the expected JsObject" in {
        Json.toJson(payPensionsProfitResponseWithoutOptionalFields) shouldBe payPensionsProfitWrittenJsonWithoutOptionalFields
      }
    }
  }

}
