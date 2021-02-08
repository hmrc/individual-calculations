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

package v1r2.models.response.getCalculation.taxableIncome.detail.payPensionsProfit.ukPropertyNonFhl.detail

import play.api.libs.json.{JsError, JsObject, JsValue, Json}
import support.UnitSpec
import v1r2.fixtures.getCalculation.taxableIncome.{TaxableIncomeJsonFixture, TaxableIncomeModelsFixture}

class UkPropertyNonFhlResultOfClaimAppliedSpec extends UnitSpec {

  "UkPropertyNonFhlResultOfClaimApplied" when {
    "read from valid JSON" should {
      "produce the expected UkPropertyNonFhlResultOfClaimApplied object" in {
        val desJson: JsValue = (TaxableIncomeJsonFixture.desJson \ "calculation" \ "lossesAndClaims" \
          "resultOfClaimsApplied" \ 2).get
        desJson.as[UkPropertyNonFhlResultOfClaimApplied] shouldBe
          TaxableIncomeModelsFixture.ukPropertyNonFhlResultOfClaimAppliedModel
      }
    }

    "read from invalid JSON" should {
      "produce a JsError" in {
        val invalidDesJson: JsValue = JsObject.empty
        invalidDesJson.validate[UkPropertyNonFhlResultOfClaimApplied] shouldBe a[JsError]
      }
    }

    "written to JSON" should {
      "produce the expected JsObject" in {
        val mtdJson: JsValue = (TaxableIncomeJsonFixture.mtdJson \ "detail" \ "payPensionsProfit" \
          "businessProfitAndLoss" \ "ukPropertyNonFhl" \ "lossClaimsDetail" \ "resultOfClaimsApplied" \ 0).get
        Json.toJson(TaxableIncomeModelsFixture.ukPropertyNonFhlResultOfClaimAppliedModel) shouldBe mtdJson
      }
    }
  }
}
