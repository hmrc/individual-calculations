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

package v2.fixtures.getCalculation.allowancesAndDeductions.summary

import play.api.libs.json.{JsValue, Json}

object CalculationSummaryFixtures {

  val desJson: JsValue = Json.parse("""{
      |    "calculation": {
      |        "taxCalculation": {
      |            "incomeTax": {
      |                "totalIncomeReceivedFromAllSources": 2108191510,
      |                "totalAllowancesAndDeductions": 1000,
      |                "totalTaxableIncome": 68088189411,
      |                "incomeTaxCharged": 16364761452,
      |                "totalReliefs": 1000.25,
      |                "incomeTaxDueAfterReliefs": -99999999999.99,
      |                "incomeTaxDueAfterGiftAid": 69148904014
      |            },
      |            "totalIncomeTaxNicsCharged": 65062942055,
      |            "totalTaxDeducted": 49524197674,
      |            "totalIncomeTaxAndNicsDue": -99999999999.99
      |        }
      |    }
      |}""".stripMargin)

  val desJsonWithNoAllowancesAndDeductionsDetails: JsValue = Json.parse("""{
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

  val mtdJson: JsValue = Json.parse("""
      |{"totalAllowancesAndDeductions":1000,"totalReliefs":1000.25}
    """.stripMargin)

}
