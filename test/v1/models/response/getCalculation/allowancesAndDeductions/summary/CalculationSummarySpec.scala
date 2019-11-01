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

package v1.models.response.getCalculation.allowancesAndDeductions.summary

import play.api.libs.json.{JsObject, JsValue, Json}
import support.UnitSpec
import v1.models.utils.JsonErrorValidators

class CalculationSummarySpec extends UnitSpec with JsonErrorValidators {

  val desJson: JsValue = Json.parse(
    """{
      |    "calculation": {
      |        "taxCalculation": {
      |            "incomeTax": {
      |                "totalIncomeReceivedFromAllSources": 2108191510,
      |                "totalAllowancesAndDeductions": 1000,
      |                "totalTaxableIncome": 68088189411,
      |                "incomeTaxCharged": 16364761452,
      |                "totalReliefs": 1000,
      |                "incomeTaxDueAfterReliefs": -99999999999.99,
      |                "incomeTaxDueAfterGiftAid": 69148904014
      |            },
      |            "totalIncomeTaxNicsCharged": 65062942055,
      |            "totalTaxDeducted": 49524197674,
      |            "totalIncomeTaxAndNicsDue": -99999999999.99
      |        }
      |    }
      |}""".stripMargin)

  val desJsonWithNoAllowancesAndDeductionsDetails: JsValue = Json.parse(
    """{
      |    "calculation": {
      |        "taxCalculation": {
      |            "incomeTax": {
      |                "totalIncomeReceivedFromAllSources": 2108191510,
      |                "totalTaxableIncome": 68088189411,
      |                "incomeTaxCharged": 16364761452,
      |                "incomeTaxDueAfterReliefs": -99999999999.99,
      |                "incomeTaxDueAfterGiftAid": 69148904014
      |            },
      |            "totalIncomeTaxNicsCharged": 65062942055,
      |            "totalTaxDeducted": 49524197674,
      |            "totalIncomeTaxAndNicsDue": -99999999999.99
      |        }
      |    }
      |}""".stripMargin)

  val mtdJson: JsValue = Json.parse(
    """
      |{"totalAllowancesAndDeductions":1000,"totalReliefs":1000}
    """.stripMargin)

  "reads" should {
    "return a valid Calculation Summary" when {

      testPropertyType[CalculationSummary](desJson)(
        path = "/calculation/taxCalculation/incomeTax/totalAllowancesAndDeductions",
        replacement = "TEST".toJson,
        expectedError = JsonError.NUMBER_FORMAT_EXCEPTION)

      testPropertyType[CalculationSummary](desJson)(
        path = "/calculation/taxCalculation/incomeTax/totalReliefs",
        replacement = "TEST".toJson,
        expectedError = JsonError.NUMBER_FORMAT_EXCEPTION)

      "a valid json is received" in {
        desJson.as[CalculationSummary] shouldBe CalculationSummary(Some(1000), Some(1000))
      }
    }

    "return an empty summary object" when {
      "json has no summary details" in {
        desJsonWithNoAllowancesAndDeductionsDetails.as[CalculationSummary] shouldBe CalculationSummary(None, None)
      }
    }
  }

  "writes" should {
    "return a valid json" when {
      "summary object has allowances and deductions" in {
        Json.toJson(CalculationSummary(Some(1000), Some(1000))) shouldBe mtdJson
      }
    }

    "return an empty json" when {
      "summary object has no data" in {
        Json.toJson(CalculationSummary.empty) shouldBe JsObject.empty
      }
    }
  }
}
