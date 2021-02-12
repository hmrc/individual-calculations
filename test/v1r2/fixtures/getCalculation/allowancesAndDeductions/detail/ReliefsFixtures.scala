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

package v1r2.fixtures.getCalculation.allowancesAndDeductions.detail

import play.api.libs.json.{JsValue, Json}

object ReliefsFixtures {

  def desJsonIncomeSourceType(incomeSourceType: String): JsValue = {
    Json.parse(
      s"""
         |{
         |   "incomeSourceType": "$incomeSourceType",
         |   "incomeSourceId": "ABC647261934212",
         |   "countryCode": "FRA",
         |   "allowableAmount": 1000,
         |   "rate": 2,
         |   "amountUsed": 1000
         |}
       """.stripMargin
    )
  }

  val desJson: JsValue = Json.parse(
    """
      |{
      |   "calculation": {
      |      "allowancesAndDeductions": {
      |         "personalAllowance": 1000,
      |         "reducedPersonalAllowance": 1000,
      |         "giftOfInvestmentsAndPropertyToCharity": 1000,
      |         "blindPersonsAllowance": 1000,
      |         "lossesAppliedToGeneralIncome": 1000,
      |         "qualifyingLoanInterestFromInvestments": 1000,
      |         "post-cessationTradeReceipts": 1000,
      |         "paymentsToTradeUnionsForDeathBenefits": 1000,
      |         "grossAnnuityPayments": 1000,
      |         "annuityPayments": {
      |            "reliefClaimed": 1000,
      |            "rate": 2
      |         },
      |         "pensionContributions": 1000,
      |         "pensionContributionsDetail": {
      |            "retirementAnnuityPayments": 1000,
      |            "paymentToEmployersSchemeNoTaxRelief": 1000,
      |            "overseasPensionSchemeContributions": 1000
      |         }
      |      },
      |      "reliefs": {
      |         "reliefsClaimed": [
      |            {
      |               "type": "nonDeductableLoanInterest",
      |               "amountClaimed": 1000,
      |               "allowableAmount": 1000,
      |               "amountUsed": 1000,
      |               "rate": 2
      |            }
      |         ]
      |      },
      |      "pensionContributionReliefs": {
      |         "totalPensionContributionReliefs": 1000,
      |         "pensionContributionDetail": {
      |            "regularPensionContributions": 1000,
      |            "oneOffPensionContributionsPaid": 1000
      |         }
      |      }
      |   }
      |}
    """.stripMargin
  )

  val desJsonWithNoDataAndEmptyNestedFields: JsValue = Json.parse(
    """
      |{
      |    "calculation": {
      |        "allowancesAndDeductions": {
      |            "personalAllowance": 1000,
      |            "reducedPersonalAllowance": 1000,
      |            "giftOfInvestmentsAndPropertyToCharity": 1000,
      |            "blindPersonsAllowance": 1000,
      |            "lossesAppliedToGeneralIncome": 1000
      |        },
      |        "reliefs": { }
      |    }
      |}
    """.stripMargin
  )

  val desJsonWithNoData: JsValue = Json.parse(
    """
      |{
      |    "calculation": {
      |        "allowancesAndDeductions": {
      |            "personalAllowance": 1000,
      |            "reducedPersonalAllowance": 1000,
      |            "giftOfInvestmentsAndPropertyToCharity": 1000,
      |            "blindPersonsAllowance": 1000,
      |            "lossesAppliedToGeneralIncome": 1000
      |        },
      |        "reliefs": { }
      |    }
      |}
    """.stripMargin
  )

  val mtdJson: JsValue = Json.parse(
    """
      |{
      |   "pensionContributionReliefs": {
      |      "totalPensionContributionReliefs": 1000,
      |      "regularPensionContributions": 1000,
      |      "oneOffPensionContributionsPaid": 1000
      |   },
      |   "reliefsClaimed": [
      |      {
      |         "type": "nonDeductibleLoanInterest",
      |         "amountClaimed": 1000,
      |         "allowableAmount": 1000,
      |         "amountUsed": 1000,
      |         "rate": 2
      |      }
      |   ]
      |}
    """.stripMargin
  )

}
