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

import play.api.libs.json.{JsObject, JsValue, Json}
import support.UnitSpec
import v1.models.utils.JsonErrorValidators

class AllowancesAndDeductionsSpec extends UnitSpec with JsonErrorValidators{

  val desJson: JsValue = Json.parse(
    """{
      |    "calculation": {
      |        "allowancesAndDeductions": {
      |            "personalAllowance": 1000,
      |            "reducedPersonalAllowance": 1000,
      |            "giftOfInvestmentsAndPropertyToCharity": 1000,
      |            "blindPersonsAllowance": 1000,
      |            "lossesAppliedToGeneralIncome": 1000
      |        },
      |        "reliefs": {
      |            "residentialFinanceCosts": {
      |                "amountClaimed": 6282356308,
      |                "allowableAmount": 56668463807,
      |                "rate": 2,
      |                "propertyFinanceRelief": 67923591034
      |            }
      |        }
      |    }
      |}""".stripMargin)

  val desJsonWithNoAllowancesAndDeductionsDetails: JsValue = Json.parse(
    """{
      |    "calculation": {
      |        "reliefs": {
      |            "residentialFinanceCosts": {
      |                "amountClaimed": 6282356308,
      |                "allowableAmount": 56668463807,
      |                "rate": 2,
      |                "propertyFinanceRelief": 67923591034
      |            }
      |        }
      |    }
      |}""".stripMargin)

  val mtdJson: JsValue = Json.parse(
    """{
      |            "personalAllowance": 1000,
      |            "reducedPersonalAllowance": 1000,
      |            "giftOfInvestmentsAndPropertyToCharity": 1000,
      |            "blindPersonsAllowance": 1000,
      |            "lossesAppliedToGeneralIncome": 1000
      |}""".stripMargin)


  "reads" should {
    "return a valid AllowancesAndDeductions object" when {

      testPropertyType[AllowancesAndDeductions](desJson)(
        path = "/calculation/allowancesAndDeductions/personalAllowance",
        replacement = "TEST".toJson,
        expectedError = JsonError.NUMBER_FORMAT_EXCEPTION)

      testPropertyType[AllowancesAndDeductions](desJson)(
        path = "/calculation/allowancesAndDeductions/reducedPersonalAllowance",
        replacement = "TEST".toJson,
        expectedError = JsonError.NUMBER_FORMAT_EXCEPTION)

      testPropertyType[AllowancesAndDeductions](desJson)(
        path = "/calculation/allowancesAndDeductions/giftOfInvestmentsAndPropertyToCharity",
        replacement = "TEST".toJson,
        expectedError = JsonError.NUMBER_FORMAT_EXCEPTION)

      testPropertyType[AllowancesAndDeductions](desJson)(
        path = "/calculation/allowancesAndDeductions/blindPersonsAllowance",
        replacement = "TEST".toJson,
        expectedError = JsonError.NUMBER_FORMAT_EXCEPTION)

      testPropertyType[AllowancesAndDeductions](desJson)(
        path = "/calculation/allowancesAndDeductions/lossesAppliedToGeneralIncome",
        replacement = "TEST".toJson,
        expectedError = JsonError.NUMBER_FORMAT_EXCEPTION)

      "a valid json is received" in {
        desJson.as[AllowancesAndDeductions] shouldBe AllowancesAndDeductions(Some(1000), Some(1000), Some(1000), Some(1000), Some(1000))
      }
    }

    "return an empty summary object" when {
      "json has no AllowancesAndDeductions details" in {
        desJsonWithNoAllowancesAndDeductionsDetails.as[AllowancesAndDeductions] shouldBe AllowancesAndDeductions(None, None, None, None, None)
      }
    }
  }

  "writes" should {
    "return a valid json" when {
      "AllowancesAndDeductions object has data" in {
        Json.toJson(AllowancesAndDeductions(Some(1000), Some(1000), Some(1000), Some(1000), Some(1000))) shouldBe mtdJson
      }
    }

    "return an empty json" when {
      "AllowancesAndDeductions object has no data" in {
        Json.toJson(AllowancesAndDeductions(None, None, None, None, None)) shouldBe JsObject.empty
      }
    }
  }
}


