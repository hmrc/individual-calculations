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

package v1.fixtures.getCalculation.allowancesAndDeductions

import play.api.libs.json.{ JsValue, Json }
import v1.models.response.getCalculation.allowancesAndDeductions.detail.{
  AllowancesAndDeductions,
  CalculationDetail,
  Reliefs,
  ResidentialFinanceCosts
}
import v1.models.response.getCalculation.allowancesAndDeductions.summary.CalculationSummary

object AllowancesDeductionsAndReliefsFixtures {

  val desJson: JsValue = Json.parse("""{
      |"calculation": {
      |        "allowancesAndDeductions": {
      |            "personalAllowance": 1000,
      |            "reducedPersonalAllowance": 1000,
      |            "giftOfInvestmentsAndPropertyToCharity": 1000,
      |            "blindPersonsAllowance": 1000,
      |            "lossesAppliedToGeneralIncome": 1000
      |        },
      |        "reliefs": {
      |            "residentialFinanceCosts": {
      |                "amountClaimed": 1000,
      |                "allowableAmount": 1000,
      |                "rate": 2,
      |                "propertyFinanceRelief": 1000
      |            }
      |        },
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

  val calculationDetail = CalculationDetail(
    Some(AllowancesAndDeductions(Some(1000), Some(1000), Some(1000), Some(1000), Some(1000))),
    Some(Reliefs(Some(ResidentialFinanceCosts(1000, Some(1000), 2, 1000))))
  )

  val calculationSummary = CalculationSummary(Some(1000), Some(1000))

  val mtdJson: JsValue = Json.parse(
    """{
      |"summary":{"totalAllowancesAndDeductions":1000,"totalReliefs":1000},"detail":{"allowancesAndDeductions":{"personalAllowance":1000,"reducedPersonalAllowance":1000,"giftOfInvestmentsAndPropertyToCharity":1000,"blindPersonsAllowance":1000,"lossesAppliedToGeneralIncome":1000},"reliefs":{"residentialFinanceCosts":{"amountClaimed":1000,"allowableAmount":1000,"rate":2,"propertyFinanceRelief":1000}}}
      |}
    """.stripMargin)

}
