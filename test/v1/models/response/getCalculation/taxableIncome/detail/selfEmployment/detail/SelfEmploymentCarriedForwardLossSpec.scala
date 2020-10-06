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

package v1.models.response.getCalculation.taxableIncome.detail.selfEmployment.detail

import play.api.libs.json.{JsError, JsObject, JsValue, Json}
import support.UnitSpec
import v1.fixtures.getCalculation.taxableIncome.{TaxableIncomeJsonFixture, TaxableIncomeModelsFixture}

class SelfEmploymentCarriedForwardLossSpec extends UnitSpec {

  "SelfEmploymentCarriedForwardLoss" when {
    "read from valid JSON" should {
      "produce the expected SelfEmploymentCarriedForwardLoss object" in {
        val desJson: JsValue = (TaxableIncomeJsonFixture.desJson \ "calculation" \ "lossesAndClaims" \
          "carriedForwardLosses" \ 0).get
        desJson.as[SelfEmploymentCarriedForwardLoss] shouldBe
          TaxableIncomeModelsFixture.selfEmploymentCarriedForwardLossModel1
      }
    }

    "read from invalid JSON" should {
      "produce a JsError" in {
        val invalidDesJson: JsValue = JsObject.empty
        invalidDesJson.validate[SelfEmploymentCarriedForwardLoss] shouldBe a[JsError]
      }
    }

    "written to JSON" should {
      "produce the expected JsObject" in {
        val mtdJson: JsValue = (TaxableIncomeJsonFixture.mtdJson \ "detail" \ "payPensionsProfit" \
          "businessProfitAndLoss" \ "selfEmployments" \ 0 \ "lossClaimsDetail" \ "carriedForwardLosses" \ 0).get
        Json.toJson(TaxableIncomeModelsFixture.selfEmploymentCarriedForwardLossModel1) shouldBe mtdJson
      }
    }
  }
}
