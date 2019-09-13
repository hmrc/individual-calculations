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

import play.api.libs.json.{ JsError, JsSuccess, Json }
import support.UnitSpec
import v1.fixtures.TaxableIncomeFixtures._

class PayPensionsProfitSpec extends UnitSpec {

  "PayPensionsProfit" when {
    "read from valid Json" should {
      "return a JsSuccess" in {
        payPensionsProfitDesJson.validate[PayPensionsProfit] shouldBe a[JsSuccess[_]]
      }
      "with the expected BusinessProfitAndLoss object" in {
        payPensionsProfitDesJson.as[PayPensionsProfit] shouldBe payPensionsProfitResponse
      }
    }
    "read from valid Json without income summary totals" should {
      "return a JsSuccess" in {
        payPensionsProfitDesJsonWithoutOptionals.validate[PayPensionsProfit] shouldBe a[JsSuccess[_]]
      }
      "with the expected BusinessProfitAndLoss object" in {
        payPensionsProfitDesJsonWithoutOptionals.as[PayPensionsProfit] shouldBe payPensionsProfitResponseWithoutSummaryTotals
      }
    }
    "read from invalid Json" should {
      "return a JsError" in {
        payPensionsProfitInvalidJson.validate[PayPensionsProfit] shouldBe a[JsError]
      }
    }

    "written to Json" should {
      "return the expected JsObject" in {
        Json.toJson(payPensionsProfitResponse) shouldBe payPensionsProfitWrittenJson
      }
    }
    "written to Json with empty fields" should {
      "return the expected JsObject" in {
        Json.toJson(payPensionsProfitResponseWithoutSummaryTotals) shouldBe payPensionsProfitWrittenJsonWithoutSummaryTotals
      }
    }

  }

}
