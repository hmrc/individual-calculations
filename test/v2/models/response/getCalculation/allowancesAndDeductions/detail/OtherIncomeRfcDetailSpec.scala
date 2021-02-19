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

package v2.models.response.getCalculation.allowancesAndDeductions.detail

import play.api.libs.json.Json
import support.UnitSpec
import v2.models.utils.JsonErrorValidators
import v2.fixtures.getCalculation.allowancesAndDeductions.detail.OtherIncomeRfcDetailFixture._

class OtherIncomeRfcDetailSpec extends UnitSpec with JsonErrorValidators {

  testJsonProperties[OtherIncomeRfcDetail](otherIncomeRfcDetailJson)(
    mandatoryProperties = Seq(
      "countryCode"
    ),
    optionalProperties = Seq(
      "residentialFinancialCostAmount",
      "broughtFwdResidentialFinancialCostAmount"
    )
  )

  "reads" should {
    "return a valid object" when {
      "valid json is passed" in {
        otherIncomeRfcDetailJson.as[OtherIncomeRfcDetail] shouldBe otherIncomeRfcDetailModel
      }
    }
  }

  "writes" should {
    "return a valid json" when {
      "OtherIncomeRfcDetail object has data" in {
        Json.toJson(otherIncomeRfcDetailModel) shouldBe otherIncomeRfcDetailJson
      }
    }
  }
}
