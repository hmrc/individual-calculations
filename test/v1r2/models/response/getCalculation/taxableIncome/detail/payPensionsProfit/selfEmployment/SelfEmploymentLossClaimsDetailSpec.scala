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

package v1r2.models.response.getCalculation.taxableIncome.detail.payPensionsProfit.selfEmployment

import play.api.libs.json.{JsObject, JsValue, Json}
import support.UnitSpec
import v1r2.fixtures.getCalculation.taxableIncome.{TaxableIncomeJsonFixture, TaxableIncomeModelsFixture}

class SelfEmploymentLossClaimsDetailSpec extends UnitSpec {

  "SelfEmploymentLossClaimsDetail" when {
    "read from valid JSON" should {
      "produce the expected SelfEmploymentLossClaimsDetail object" in {
        TaxableIncomeJsonFixture.oneSelfEmploymentOnlyDesJson.as[SelfEmploymentLossClaimsDetail] shouldBe
          TaxableIncomeModelsFixture.selfEmploymentDetailModel1
      }
    }

    "read from empty JSON" should {
      "produce the expected SelfEmploymentLossClaimsDetail object" in {
        val emptyJson: JsValue = JsObject.empty
        emptyJson.as[SelfEmploymentLossClaimsDetail] shouldBe SelfEmploymentLossClaimsDetail.empty
      }
    }

    "read from non-empty JSON without incomeSourceType '01'" should {
      "produce the expected SelfEmploymentLossClaimsDetail object" in {
        TaxableIncomeJsonFixture.noValidIncomeSourcesDesJson.as[SelfEmploymentLossClaimsDetail] shouldBe
          SelfEmploymentLossClaimsDetail.empty
      }
    }

    "written to Json" should {
      "return the expected JsObject" in {
        val mtdJson: JsValue = (TaxableIncomeJsonFixture.mtdJson\ "detail" \ "payPensionsProfit" \
          "businessProfitAndLoss" \ "selfEmployments" \ 0 \ "lossClaimsDetail").get
        Json.toJson(TaxableIncomeModelsFixture.selfEmploymentDetailModel1) shouldBe mtdJson
      }
    }

    "filterById" when {
      "all items present have the desired incomeSourceId" should {
        "produce the expected result" in {
          TaxableIncomeModelsFixture.selfEmploymentDetailModel1.filterById(id = "AaIS12345678910") shouldBe
            Some(TaxableIncomeModelsFixture.selfEmploymentDetailModel1)
        }
      }

      "no items present have the desired incomeSourceId" should {
        "return None" in {
          TaxableIncomeModelsFixture.selfEmploymentDetailModel1.filterById(id = "BA112DDWQ2SV41D") shouldBe None
        }
      }
    }
  }
}
