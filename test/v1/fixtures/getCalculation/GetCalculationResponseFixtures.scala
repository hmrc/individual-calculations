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

package v1.fixtures.getCalculation

import play.api.libs.json.{JsObject, JsValue, Json}
import v1.fixtures.getCalculation.endOfYearEstimate.EoyEstimateFixtures.eoyEstimateResponse
import v1.models.domain.{CalculationReason, CalculationRequestor, CalculationType}
import v1.models.response.common.{Message, Messages, Metadata}
import v1.models.response.getCalculation.GetCalculationResponse
import v1.models.response.getCalculation.allowancesAndDeductions.AllowancesDeductionsAndReliefs
import v1.models.response.getCalculation.allowancesAndDeductions.detail.{AllowancesAndDeductions, AnnualPayments, ForeignTaxCreditRelief, IncomeSourceType, PensionContributionReliefs, PensionContributions, Reliefs, ReliefsClaimed, ResidentialFinanceCosts, CalculationDetail => ADRCalculationDetail}
import v1.models.response.getCalculation.allowancesAndDeductions.summary.{CalculationSummary => ADRCalculationSummary}
import v1.models.response.getCalculation.incomeTaxAndNics.IncomeTax
import v1.models.response.getCalculation.incomeTaxAndNics.detail.{CalculationDetail, IncomeTaxDetail, IncomeTypeBreakdown}
import v1.models.response.getCalculation.incomeTaxAndNics.summary.{CalculationSummary, IncomeTaxSummary}
import v1.models.response.getCalculation.taxableIncome._
import v1.models.response.getCalculation.taxableIncome.detail.PayPensionsProfit

object GetCalculationResponseFixtures {

  val desJsonWithoutOptionalParts: JsValue = Json.parse(
    """
      |{
      |   "metadata":{
      |      "calculationId":"f2fb30e5-4ab6-4a29-b3c1-c7264259ff1c",
      |      "taxYear":2019,
      |      "requestedBy":"customer",
      |      "requestedTimestamp":"2019-11-15T09:25:15.094Z",
      |      "calculationReason":"customerRequest",
      |      "calculationTimestamp":"2019-11-15T09:35:15.094Z",
      |      "calculationType":"inYear",
      |      "periodFrom":"1-2018",
      |      "periodTo":"1-2019"
      |   },
      |   "messages":{
      |      "errors":[
      |         {
      |            "id":"id1",
      |            "text":"text1"
      |         }
      |      ]
      |   }
      |}
     """.stripMargin)

  val desJsonWithAllParts: JsValue = desJsonWithoutOptionalParts.as[JsObject] ++
    Json.parse(
      """
        |{
        |   "calculation":{
        |      "endOfYearEstimate":{
        |         "incomeTaxAmount":1003.1,
        |         "totalNicAmount":1006.1,
        |         "totalEstimatedIncome":1001,
        |         "totalTaxableIncome":1002,
        |         "totalStudentLoansRepaymentAmount":1007.1,
        |         "totalAnnuityPaymentsTaxCharged":1008.1,
        |         "totalRoyaltyPaymentsTaxCharged":1009.1,
        |         "totalTaxDeducted":1010.1,
        |         "incomeTaxNicAmount":1011.1,
        |         "nic2":1004.1,
        |         "incomeSource":[
        |            {
        |               "incomeSourceId":"AB123456789",
        |               "taxableIncome":1011,
        |               "finalised":false,
        |               "incomeSourceType":"01"
        |            },
        |            {
        |               "taxableIncome":1031,
        |               "finalised":false,
        |               "incomeSourceType":"04"
        |            },
        |            {
        |               "taxableIncome":1041,
        |               "finalised":false,
        |               "incomeSourceType":"02"
        |            },
        |            {
        |               "incomeSourceId":"AA123456789",
        |               "incomeSourceName":"An Account Name",
        |               "taxableIncome":1051,
        |               "incomeSourceType":"09"
        |            },
        |            {
        |               "incomeSourceId":"AA123456789",
        |               "incomeSourceName":"An Account Name",
        |               "taxableIncome":1051,
        |               "incomeSourceType":"09"
        |            },
        |            {
        |               "taxableIncome":1021,
        |               "incomeSourceType":"10"
        |            },
        |            {
        |               "taxableIncome":1200,
        |               "incomeSourceType":"17"
        |            },
        |            {
        |               "taxableIncome":1300,
        |               "incomeSourceType":"11"
        |            },
        |            {
        |               "taxableIncome":1400,
        |               "incomeSourceType":"18"
        |            },
        |            {
        |               "taxableIncome":1500,
        |               "finalised":true,
        |               "incomeSourceType":"15"
        |            },
        |            {
        |               "taxableIncome":1600,
        |               "incomeSourceType":"16"
        |            }
        |         ],
        |         "nic4":1005.1
        |      },
        |      "allowancesAndDeductions":{
        |         "personalAllowance":1000,
        |         "reducedPersonalAllowance":1000,
        |         "giftOfInvestmentsAndPropertyToCharity":1000,
        |         "blindPersonsAllowance":1000,
        |         "lossesAppliedToGeneralIncome":1000,
        |         "qualifyingLoanInterestFromInvestments":1000,
        |         "post-cessationTradeReceipts":1000,
        |         "paymentsToTradeUnionsForDeathBenefits":1000,
        |         "grossAnnuityPayments":1000,
        |         "annuityPayments":{
        |            "reliefClaimed":1000,
        |            "rate":2
        |         },
        |         "pensionContributions":1000,
        |         "pensionContributionsDetail":{
        |            "retirementAnnuityPayments":1000,
        |            "paymentToEmployersSchemeNoTaxRelief":1000,
        |            "overseasPensionSchemeContributions":1000
        |         }
        |      },
        |      "reliefs":{
        |         "residentialFinanceCosts":{
        |            "amountClaimed":1000.25,
        |            "allowableAmount":1000.25,
        |            "rate":2,
        |            "propertyFinanceRelief":1000.25
        |         },
        |         "foreignTaxCreditRelief":[
        |            {
        |               "incomeSourceType":"16",
        |               "incomeSourceId":"ABC647261934212",
        |               "countryCode":"FRA",
        |               "allowableAmount":1000,
        |               "rate":2,
        |               "amountUsed":1000
        |            }
        |         ],
        |         "reliefsClaimed":[
        |            {
        |               "type":"nonDeductibleLoanInterest",
        |               "amountClaimed":1000,
        |               "allowableAmount":1000,
        |               "amountUsed":1000,
        |               "rate":2
        |            }
        |         ]
        |      },
        |      "pensionContributionReliefs":{
        |         "totalPensionContributionReliefs":1000,
        |         "pensionContributionDetail":{
        |            "regularPensionContributions":1000,
        |            "oneOffPensionContributionsPaid":1000
        |         }
        |      },
        |      "taxCalculation":{
        |         "incomeTax":{
        |            "incomeTaxCharged":100.25,
        |            "totalAllowancesAndDeductions":1000,
        |            "totalIncomeReceivedFromAllSources":123,
        |            "totalReliefs":1000,
        |            "totalTaxableIncome":234,
        |            "payPensionsProfit":{
        |               "allowancesAllocated":300,
        |               "incomeTaxAmount":400.25,
        |               "incomeReceived":500,
        |               "taxableIncome":600
        |            }
        |         },
        |         "totalIncomeTaxAndNicsDue":200.25
        |      }
        |   },
        |   "inputs":{
        |      "personalInformation":{
        |         "taxRegime":"UK"
        |      }
        |   }
        |}
        """.stripMargin).as[JsObject]

  val writtenJson: JsValue = Json.parse(
    """
      |{
      |   "metadata":{
      |      "id":"f2fb30e5-4ab6-4a29-b3c1-c7264259ff1c",
      |      "taxYear":"2018-19",
      |      "requestedBy":"customer",
      |      "requestedTimestamp":"2019-11-15T09:25:15.094Z",
      |      "calculationReason":"customerRequest",
      |      "calculationTimestamp":"2019-11-15T09:35:15.094Z",
      |      "calculationType":"inYear",
      |      "intentToCrystallise":false,
      |      "crystallised":false,
      |      "totalIncomeTaxAndNicsDue":200.25,
      |      "calculationErrorCount":1
      |   },
      |   "incomeTaxAndNicsCalculated":{
      |      "summary":{
      |         "incomeTax":{
      |            "incomeTaxCharged":100.25
      |         },
      |         "totalIncomeTaxAndNicsDue":200.25,
      |         "taxRegime":"UK"
      |      },
      |      "detail":{
      |         "incomeTax":{
      |            "payPensionsProfit":{
      |               "allowancesAllocated":300,
      |               "incomeTaxAmount":400.25
      |            }
      |         }
      |      }
      |   },
      |   "messages":{
      |      "errors":[
      |         {
      |            "id":"id1",
      |            "text":"text1"
      |         }
      |      ]
      |   },
      |   "taxableIncome":{
      |      "summary":{
      |         "totalIncomeReceivedFromAllSources":123,
      |         "totalTaxableIncome":234
      |      },
      |      "detail":{
      |         "payPensionsProfit":{
      |            "incomeReceived":500,
      |            "taxableIncome":600
      |         }
      |      }
      |   },
      |   "endOfYearEstimate":{
      |      "summary":{
      |         "totalEstimatedIncome":1001,
      |         "totalTaxableIncome":1002,
      |         "incomeTaxAmount":1003.1,
      |         "nic2":1004.1,
      |         "nic4":1005.1,
      |         "totalNicAmount":1006.1,
      |         "totalStudentLoansRepaymentAmount":1007.1,
      |         "totalAnnualPaymentsTaxCharged":1008.1,
      |         "totalRoyaltyPaymentsTaxCharged":1009.1,
      |         "totalTaxDeducted":1010.1,
      |         "incomeTaxNicAmount":1011.1
      |      },
      |      "detail":{
      |         "selfEmployments":[
      |            {
      |               "selfEmploymentId":"AB123456789",
      |               "taxableIncome":1011,
      |               "finalised":false
      |            }
      |         ],
      |         "ukPropertyFhl":{
      |            "taxableIncome":1031,
      |            "finalised":false
      |         },
      |         "ukPropertyNonFhl":{
      |            "taxableIncome":1041,
      |            "finalised":false
      |         },
      |         "ukSavings":[
      |            {
      |               "savingsAccountId":"AA123456789",
      |               "savingsAccountName":"An Account Name",
      |               "taxableIncome":1051
      |            },
      |            {
      |               "savingsAccountId":"AA123456789",
      |               "savingsAccountName":"An Account Name",
      |               "taxableIncome":1051
      |            }
      |         ],
      |         "ukDividends":{
      |            "taxableIncome":1021
      |         },
      |         "otherDividends":{
      |            "taxableIncome":1200
      |         },
      |         "stateBenefits":{
      |            "taxableIncome":1300
      |         },
      |         "ukSecurities":{
      |            "taxableIncome":1400
      |         },
      |         "foreignProperty":{
      |            "taxableIncome":1500,
      |            "finalised":true
      |         },
      |         "foreignInterest":{
      |            "taxableIncome":1600
      |         }
      |      }
      |   },
      |   "allowancesDeductionsAndReliefs":{
      |      "summary":{
      |         "totalAllowancesAndDeductions":1000,
      |         "totalReliefs":1000
      |      },
      |      "detail":{
      |         "allowancesAndDeductions":{
      |            "personalAllowance":1000,
      |            "reducedPersonalAllowance":1000,
      |            "giftOfInvestmentsAndPropertyToCharity":1000,
      |            "blindPersonsAllowance":1000,
      |            "lossesAppliedToGeneralIncome":1000,
      |            "qualifyingLoanInterestFromInvestments":1000,
      |            "postCessationTradeReceipts":1000,
      |            "paymentsToTradeUnionsForDeathBenefits":1000,
      |            "annualPayments":{
      |               "grossAnnualPayments":1000,
      |               "reliefClaimed":1000,
      |               "rate":2
      |            },
      |            "pensionContributions":{
      |               "totalPensionContributions":1000,
      |               "retirementAnnuityPayments":1000,
      |               "paymentToEmployersSchemeNoTaxRelief":1000,
      |               "overseasPensionSchemeContributions":1000
      |            }
      |         },
      |         "reliefs":{
      |            "residentialFinanceCosts":{
      |               "amountClaimed":1000.25,
      |               "allowableAmount":1000.25,
      |               "rate":2,
      |               "propertyFinanceRelief":1000.25
      |            },
      |            "foreignTaxCreditRelief":[
      |               {
      |                  "incomeSourceType":"foreignInterest",
      |                  "incomeSourceId":"ABC647261934212",
      |                  "countryCode":"FRA",
      |                  "allowableAmount":1000,
      |                  "rate":2,
      |                  "amountUsed":1000
      |               }
      |            ],
      |            "pensionContributionReliefs":{
      |               "totalPensionContributionReliefs":1000,
      |               "regularPensionContributions":1000,
      |               "oneOffPensionContributionsPaid":1000
      |            },
      |            "reliefsClaimed":[
      |               {
      |                  "type":"nonDeductibleLoanInterest",
      |                  "amountClaimed":1000,
      |                  "allowableAmount":1000,
      |                  "amountUsed":1000,
      |                  "rate":2
      |               }
      |            ]
      |         }
      |      }
      |   }
      |}
      |""".stripMargin)

  val writtenJsonWithoutOptionalParts: JsValue = Json.parse(
    """
      |{
      |  "metadata": {
      |    "id": "f2fb30e5-4ab6-4a29-b3c1-c7264259ff1c",
      |    "taxYear": "2018-19",
      |    "requestedBy": "customer",
      |    "requestedTimestamp": "2019-11-15T09:25:15.094Z",
      |    "calculationReason": "customerRequest",
      |    "calculationTimestamp": "2019-11-15T09:35:15.094Z",
      |    "calculationType": "inYear",
      |    "intentToCrystallise": false,
      |    "crystallised": false,
      |    "calculationErrorCount": 1
      |  },
      |  "messages": {
      |    "errors": [
      |      {
      |        "id": "id1",
      |        "text": "text1"
      |      }
      |    ]
      |  }
      |}""".stripMargin)

  val metadata = Metadata(
    id = "f2fb30e5-4ab6-4a29-b3c1-c7264259ff1c",
    taxYear = "2018-19",
    requestedBy = CalculationRequestor.customer,
    requestedTimestamp = Some("2019-11-15T09:25:15.094Z"),
    calculationReason = CalculationReason.customerRequest,
    calculationTimestamp = Some("2019-11-15T09:35:15.094Z"),
    calculationType = CalculationType.inYear,
    intentToCrystallise = false,
    crystallised = false,
    totalIncomeTaxAndNicsDue = Some(200.25),
    calculationErrorCount = Some(1)
  )

  val messages = Messages(None, None, Some(Seq(Message("id1", "text1"))))

  val calculationSummary = CalculationSummary(
    IncomeTaxSummary(100.25, None, None, None, None, None, None, None), None, None, None, None, None, None, 200.25, "UK")

  val calculationDetail = CalculationDetail(
    IncomeTaxDetail(
      Some(IncomeTypeBreakdown(300, 400.25, None)), None, None, None, None, None), None, None, None, None)

  val incomeTax = IncomeTax(calculationSummary, calculationDetail)

  val taxableIncomeModel = TaxableIncome(
    TaxableIncomeSummary(
      totalIncomeReceivedFromAllSources = 123,
      totalTaxableIncome = 234
    ),
    TaxableIncomeDetail(
      payPensionsProfit = Some(PayPensionsProfit(
        incomeReceived = 500,
        taxableIncome = 600,
        totalSelfEmploymentProfit = None,
        totalPropertyProfit = None,
        totalFHLPropertyProfit = None,
        totalUKOtherPropertyProfit = None,
        totalForeignPropertyProfit = None,
        totalEeaFhlProfit = None,
        totalOccupationalPensionIncome = None,
        totalStateBenefitsIncome = None,
        totalBenefitsInKind = None,
        totalPayeEmploymentAndLumpSumIncome = None,
        totalEmploymentExpenses = None,
        totalEmploymentIncome = None,
        businessProfitAndLoss = None
      )),
      savingsAndGains = None,
      dividends = None,
      lumpSums = None,
      gainsOnLifePolicies = None
    )
  )
  val calculationResponse = GetCalculationResponse(metadata.copy(totalIncomeTaxAndNicsDue = None), messages = Some(messages))

  val allowancesDeductionsAndReliefs = AllowancesDeductionsAndReliefs(
    ADRCalculationSummary(Some(1000), Some(1000)),
    ADRCalculationDetail(
      Some(AllowancesAndDeductions(Some(1000), Some(1000), Some(1000), Some(1000), Some(1000), Some(1000), Some(1000), Some(1000),
        Some(AnnualPayments(Some(1000), Some(1000), Some(2))),
          Some(
            PensionContributions(Some(1000),
              Some(1000), Some(1000), Some(1000)
            )
          )
        )
      ),
      Some(Reliefs(Some(ResidentialFinanceCosts(1000.25, Some(1000.25), 2, 1000.25)),
        Some(Seq(ForeignTaxCreditRelief(IncomeSourceType.foreignInterest,
          Some("ABC647261934212"), "FRA", Some(1000), Some(2), Some(1000)))),
        Some(PensionContributionReliefs(1000, Some(1000), Some(1000))),
          Some(
            Seq(
                ReliefsClaimed("nonDeductibleLoanInterest", Some(1000), Some(1000), Some(1000), Some(2)
              )
            )
          )
        )
      )
    )
  )

  val calculationResponseAllParts = GetCalculationResponse(
    metadata.copy(totalIncomeTaxAndNicsDue = Some(200.25)),
    Some(incomeTax),
    Some(messages),
    Some(taxableIncomeModel),
    Some(eoyEstimateResponse),
    Some(allowancesDeductionsAndReliefs)
  )
}