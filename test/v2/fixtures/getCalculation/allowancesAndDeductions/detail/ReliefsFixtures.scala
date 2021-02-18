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

package v2.fixtures.getCalculation.allowancesAndDeductions.detail

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
      |	"calculation": {
      |		"allowancesAndDeductions": {
      |			"personalAllowance": 1000,
      |			"reducedPersonalAllowance": 1000,
      |			"giftOfInvestmentsAndPropertyToCharity": 1000,
      |			"blindPersonsAllowance": 1000,
      |			"lossesAppliedToGeneralIncome": 1000,
      |			"qualifyingLoanInterestFromInvestments": 1000,
      |			"post-cessationTradeReceipts": 1000,
      |			"paymentsToTradeUnionsForDeathBenefits": 1000,
      |			"grossAnnuityPayments": 1000,
      |			"annuityPayments": {
      |				"reliefClaimed": 1000,
      |				"rate": 2
      |			},
      |			"pensionContributions": 1000,
      |			"pensionContributionsDetail": {
      |				"retirementAnnuityPayments": 1000,
      |				"paymentToEmployersSchemeNoTaxRelief": 1000,
      |				"overseasPensionSchemeContributions": 1000
      |			}
      |		},
      |		"reliefs": {
      |			"residentialFinanceCosts": {
      |				"adjustedTotalIncome": 49820.34,
      |				"totalAllowableAmount": 49821.34,
      |				"relievableAmount": 49822.34,
      |				"rate": 45.34,
      |				"totalResidentialFinanceCostsRelief": 49823.34,
      |				"ukProperty": {
      |					"amountClaimed": 49824.34,
      |					"allowableAmount": 49825.34,
      |					"carryForwardAmount": 49826.34
      |				},
      |				"foreignProperty": {
      |					"totalForeignPropertyAllowableAmount": 49827.34,
      |					"foreignPropertyRfcDetail": [{
      |						"countryCode": "IND",
      |						"amountClaimed": 49829.34,
      |						"allowableAmount": 49828.34,
      |						"carryForwardAmount": 49830.34
      |					}]
      |				},
      |				"allOtherIncomeReceivedWhilstAbroad": {
      |					"totalOtherIncomeAllowableAmount": 49831.34,
      |					"otherIncomeRfcDetail": [{
      |						"countryCode": "IND",
      |						"residentialFinancialCostAmount": 49833.34,
      |						"broughtFwdResidentialFinancialCostAmount": 49832.34
      |					}]
      |				}
      |			},
      |			"foreignTaxCreditRelief": {
      |				"customerCalculatedRelief": true,
      |				"totalForeignTaxCreditRelief": 8019.25,
      |				"foreignTaxCreditReliefOnProperty": 8020.25,
      |				"foreignTaxCreditReliefOnDividends": 8021.25,
      |				"foreignTaxCreditReliefOnSavings": 8022.25,
      |				"foreignTaxCreditReliefOnForeignIncome": 8023.25
      |			},
      |			"reliefsClaimed": [{
      |				"type": "nonDeductableLoanInterest",
      |				"amountClaimed": 1000,
      |				"allowableAmount": 1000,
      |				"amountUsed": 1000,
      |				"rate": 2
      |			}]
      |		},
      |		"pensionContributionReliefs": {
      |			"totalPensionContributionReliefs": 1000,
      |			"pensionContributionDetail": {
      |				"regularPensionContributions": 1000,
      |				"oneOffPensionContributionsPaid": 1000
      |			}
      |		}
      |	}
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
      |	"pensionContributionReliefs": {
      |		"totalPensionContributionReliefs": 1000,
      |		"regularPensionContributions": 1000,
      |		"oneOffPensionContributionsPaid": 1000
      |	},
      |	"reliefsClaimed": [{
      |		"type": "nonDeductibleLoanInterest",
      |		"amountClaimed": 1000,
      |		"allowableAmount": 1000,
      |		"amountUsed": 1000,
      |		"rate": 2
      |	}],
      |	"residentialFinanceCosts": {
      |		"adjustedTotalIncome": 49820.34,
      |		"totalAllowableAmount": 49821.34,
      |		"relievableAmount": 49822.34,
      |		"rate": 45.34,
      |		"totalResidentialFinanceCostsRelief": 49823.34,
      |		"ukProperty": {
      |			"amountClaimed": 49824.34,
      |			"allowableAmount": 49825.34,
      |			"carryForwardAmount": 49826.34
      |		},
      |		"foreignProperty": {
      |			"totalForeignPropertyAllowableAmount": 49827.34,
      |			"foreignPropertyRfcDetail": [{
      |				"countryCode": "IND",
      |				"amountClaimed": 49829.34,
      |				"allowableAmount": 49828.34,
      |				"carryForwardAmount": 49830.34
      |			}]
      |		},
      |		"allOtherIncomeReceivedWhilstAbroad": {
      |			"totalOtherIncomeAllowableAmount": 49831.34,
      |			"otherIncomeRfcDetail": [{
      |				"countryCode": "IND",
      |				"residentialFinancialCostAmount": 49833.34,
      |				"broughtFwdResidentialFinancialCostAmount": 49832.34
      |			}]
      |		}
      |	},
      |	"foreignTaxCreditRelief": {
      |		"customerCalculatedRelief": true,
      |		"totalForeignTaxCreditRelief": 8019.25,
      |		"foreignTaxCreditReliefOnProperty": 8020.25,
      |		"foreignTaxCreditReliefOnDividends": 8021.25,
      |		"foreignTaxCreditReliefOnSavings": 8022.25,
      |		"foreignTaxCreditReliefOnForeignIncome": 8023.25
      |	}
      |}
    """.stripMargin
  )
}
