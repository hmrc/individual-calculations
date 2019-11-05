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

package v1.models.response.getCalculation.allowancesAndDeductions.detail

import play.api.libs.json.{JsObject, Json}
import support.UnitSpec
import v1.fixtures.allowancesAndDeductions.AllowancesAndDeductionsFixture._
import v1.models.utils.JsonErrorValidators

class AllowancesAndDeductionsSpec extends UnitSpec with JsonErrorValidators{

  "reads" should {
    "return a valid AllowancesAndDeductions object" when {

      testPropertyType[AllowancesAndDeductions](allowancesAndDeductionsDesJson)(
        path = "/calculation/allowancesAndDeductions/personalAllowance",
        replacement = "TEST".toJson,
        expectedError = JsonError.NUMBER_FORMAT_EXCEPTION)

      testPropertyType[AllowancesAndDeductions](allowancesAndDeductionsDesJson)(
        path = "/calculation/allowancesAndDeductions/reducedPersonalAllowance",
        replacement = "TEST".toJson,
        expectedError = JsonError.NUMBER_FORMAT_EXCEPTION)

      testPropertyType[AllowancesAndDeductions](allowancesAndDeductionsDesJson)(
        path = "/calculation/allowancesAndDeductions/giftOfInvestmentsAndPropertyToCharity",
        replacement = "TEST".toJson,
        expectedError = JsonError.NUMBER_FORMAT_EXCEPTION)

      testPropertyType[AllowancesAndDeductions](allowancesAndDeductionsDesJson)(
        path = "/calculation/allowancesAndDeductions/blindPersonsAllowance",
        replacement = "TEST".toJson,
        expectedError = JsonError.NUMBER_FORMAT_EXCEPTION)

      testPropertyType[AllowancesAndDeductions](allowancesAndDeductionsDesJson)(
        path = "/calculation/allowancesAndDeductions/lossesAppliedToGeneralIncome",
        replacement = "TEST".toJson,
        expectedError = JsonError.NUMBER_FORMAT_EXCEPTION)

      "a valid json is received" in {
        allowancesAndDeductionsDesJson.as[AllowancesAndDeductions] shouldBe allowancesAndDeductionsModel
      }
    }

    "return an empty summary object" when {
      "json has no AllowancesAndDeductions details" in {
        allowancesAndDeductionsNoDataDesJson.as[AllowancesAndDeductions] shouldBe AllowancesAndDeductions.empty
      }
    }
  }

  "writes" should {
    "return a valid json" when {
      "AllowancesAndDeductions object has data" in {
        Json.toJson(allowancesAndDeductionsModel) shouldBe allowancesAndDeductionsMtdJson
      }
    }

    "return an empty json" when {
      "AllowancesAndDeductions object has no data" in {
        Json.toJson(AllowancesAndDeductions.empty) shouldBe JsObject.empty
      }
    }
  }
}


