/*
 * Copyright 2020 HM Revenue & Customs
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

import play.api.libs.json.{JsValue, Json}
import v1.models.response.getCalculation.allowancesAndDeductions.detail._
import v1.models.response.getCalculation.allowancesAndDeductions.summary.CalculationSummary

object AllowancesDeductionsAndReliefsFixtures {

  val desJson: JsValue = Json.parse("""{
      |"calculation": {
      |    "allowancesAndDeductions": {
      |            "personalAllowance": 1000,
      |            "reducedPersonalAllowance": 1000,
      |            "giftOfInvestmentsAndPropertyToCharity": 1000,
      |            "blindPersonsAllowance": 1000,
      |            "lossesAppliedToGeneralIncome": 1000,
      |            "qualifyingLoanInterestFromInvestments": 1000,
      |            "post-cessationTradeReceipts": 1000,
      |            "paymentsToTradeUnionsForDeathBenefits": 1000,
      |            "grossAnnuityPayments": 1000,
      |            "annuityPayments": {
      |               "reliefClaimed": 1000,
      |               "rate": 2
      |            },
      |            "pensionContributions": 1000,
      |            "pensionContributionsDetail": {
      |               "retirementAnnuityPayments": 1000,
      |               "paymentToEmployersSchemeNoTaxRelief": 1000,
      |               "overseasPensionSchemeContributions": 1000
      |            }
      |        },
      |        "reliefs": {
      |            "residentialFinanceCosts": {
      |                "amountClaimed": 1000.25,
      |                "allowableAmount": 1000.25,
      |                 "rate": 2,
      |                 "propertyFinanceRelief": 1000.25
      |            },
      |            "foreignTaxCreditRelief": [{
      |                  "incomeSourceType": "16",
      |                  "incomeSourceId": "ABC647261934212",
      |                  "countryCode": "FRA",
      |                  "allowableAmount": 1000,
      |                  "rate": 2,
      |                  "amountUsed": 1000
      |            }],
      |            "reliefsClaimed": [{
      |                  "type": "nonDeductableLoanInterest",
      |                  "amountClaimed": 1000,
      |                   "allowableAmount": 1000,
      |                  "amountUsed": 1000,
      |                  "rate": 2
      |            }]
      |        },
      |           "pensionContributionReliefs": {
      |       "totalPensionContributionReliefs": 1000,
      |       "pensionContributionDetail": {
      |          "regularPensionContributions": 1000,
      |          "oneOffPensionContributionsPaid": 1000
      |       }
      |      },
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
    Some(AllowancesAndDeductions(Some(1000), Some(1000), Some(1000), Some(1000), Some(1000), Some(1000),
      Some(1000), Some(1000), Some(AnnualPayments(Some(1000), Some(1000), Some(2))),
    Some(PensionContributions(Some(1000), Some(1000), Some(1000), Some(1000))))),
    Some(Reliefs(Some(ResidentialFinanceCosts(1000.25, Some(1000.25), 2, 1000.25)), Some(Seq(ForeignTaxCreditRelief(
      Some(IncomeSourceType.foreignInterest), Some("ABC647261934212"), Some("FRA"), Some(1000), Some(2), Some(1000)))), Some(PensionContributionReliefs(
      Some(1000), Some(1000), Some(1000))), Some(Seq(ReliefsClaimed(Some("nonDeductibleLoanInterest"), Some(1000), Some(1000), Some(1000),
      Some(2)))))))

  val calculationSummary = CalculationSummary(Some(1000), Some(1000))

  val mtdJson: JsValue = Json.parse(
    """
      |{
      |		"summary": {
      |			"totalAllowancesAndDeductions": 1000,
      |			"totalReliefs": 1000
      |		},
      |		"detail": {
      |			"allowancesAndDeductions": {
      |            "personalAllowance": 1000,
      |            "reducedPersonalAllowance": 1000,
      |            "giftOfInvestmentsAndPropertyToCharity": 1000,
      |            "blindPersonsAllowance": 1000,
      |            "lossesAppliedToGeneralIncome": 1000,
      |            "qualifyingLoanInterestFromInvestments": 1000,
      |            "postCessationTradeReceipts": 1000,
      |            "paymentsToTradeUnionsForDeathBenefits": 1000,
      |            "annualPayments": {
      |               "grossAnnualPayments": 1000,
      |               "reliefClaimed": 1000,
      |               "rate": 2
      |            },
      |            "pensionContributions": {
      |               "totalPensionContributions": 1000,
      |               "retirementAnnuityPayments": 1000,
      |               "paymentToEmployersSchemeNoTaxRelief": 1000,
      |               "overseasPensionSchemeContributions": 1000
      |            }
      |     },
      |			"reliefs": {
      |				"residentialFinanceCosts": {
      |					"amountClaimed": 1000.25,
      |					"allowableAmount": 1000.25,
      |					"rate": 2,
      |					"propertyFinanceRelief": 1000.25
      |				},
      |     "foreignTaxCreditRelief": [{
      |       "incomeSourceType": "foreignInterest",
      |       "incomeSourceId": "ABC647261934212",
      |       "countryCode": "FRA",
      |       "allowableAmount": 1000,
      |       "rate": 2,
      |       "amountUsed": 1000
      |       }],
      |     "pensionContributionReliefs": {
      |       "totalPensionContributionReliefs": 1000,
      |       "regularPensionContributions": 1000,
      |       "oneOffPensionContributionsPaid": 1000
      |       },
      |     "reliefsClaimed": [{
      |         "type": "nonDeductibleLoanInterest",
      |         "amountClaimed": 1000,
      |         "allowableAmount": 1000,
      |         "amountUsed": 1000,
      |         "rate": 2
      |       }]
      |			}
      |		}
      |  }
      |""".stripMargin)

}
