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

package v2.fixtures.getCalculation.incomeTaxAndNics.detail

import play.api.libs.json.{JsValue, Json}
import v2.fixtures.getCalculation.incomeTaxAndNics.detail.ExcessOfLifetimeAllowanceFixtures._
import v2.fixtures.getCalculation.incomeTaxAndNics.detail.OverseasPensionContributionsFixtures._
import v2.fixtures.getCalculation.incomeTaxAndNics.detail.PensionContributionsInExcessOfTheAnnualAllowanceFixtures._
import v2.fixtures.getCalculation.incomeTaxAndNics.detail.PensionSchemeOverseasTransfersFixtures._
import v2.fixtures.getCalculation.incomeTaxAndNics.detail.PensionSchemeUnauthorisedPaymentsFixtures._
import v2.models.response.getCalculation.incomeTaxAndNics.detail._

object CalculationDetailFixtures {

  val calculationDetailMinDesJson: JsValue = Json.parse(
    """
      |{
      |   "calculation": {
      |      "taxCalculation": {
      |         "incomeTax": {
      |            "payPensionsProfit": {
      |               "allowancesAllocated": 200,
      |               "incomeTaxAmount": 200.50
      |            }
      |         }
      |      }
      |   }
      |}
    """.stripMargin
  )

  val calculationDetailDesJsonWithEmptyObjects: JsValue = Json.parse(
    """
      |{
      |   "calculation": {
      |      "taxCalculation": {
      |         "incomeTax": {
      |            "payPensionsProfit": {
      |               "allowancesAllocated": 200,
      |               "incomeTaxAmount": 200.50
      |            }
      |         },
      |         "nics":{ }
      |      },
      |      "taxDeductedAtSource":{ }
      |   }
      |}
    """.stripMargin
  )

  val calculationDetailFilledDesJson: JsValue = Json.parse(
    """
      |{
      |   "calculation": {
      |      "taxCalculation": {
      |         "incomeTax": {
      |            "payPensionsProfit": {
      |               "allowancesAllocated": 200,
      |               "incomeTaxAmount": 200.50
      |            }
      |         },
      |         "nics": {
      |            "class2Nics": {
      |               "underSmallProfitThreshold": true
      |            }
      |         }
      |      },
      |      "studentLoans": [
      |         {
      |            "planType": "name",
      |            "studentLoanApportionedIncomeThreshold": 300,
      |            "studentLoanChargeableIncomeAmount": 300.50,
      |            "studentLoanDeductionsFromEmployment": 300.50,
      |            "studentLoanRate": 300.50,
      |            "studentLoanRepaymentAmount": 300.50,
      |            "studentLoanRepaymentAmountNetOfDeductions": 300.50,
      |            "studentLoanTotalIncomeAmount": 300.50
      |         }
      |      ],
      |      "pensionSavingsTaxCharges": {
      |         "pensionSavingsTaxChargesDetail": {
      |            "excessOfLifeTimeAllowance": {
      |               "totalChargeableAmount": 100.99,
      |               "totalTaxPaid": 200.25,
      |               "lumpSumBenefitTakenInExcessOfLifetimeAllowance": {
      |                  "amount": 120.50,
      |                  "chargeableAmount": 160.50,
      |                  "rate": 10.40,
      |                  "taxPaid": 160.50
      |               },
      |               "benefitInExcessOfLifetimeAllowance": {
      |                  "amount": 120.50,
      |                  "chargeableAmount": 160.50,
      |                  "rate": 10.40,
      |                  "taxPaid": 160.50
      |               }
      |            },
      |            "pensionSchemeUnauthorisedPayments": {
      |               "totalChargeableAmount": 150.99,
      |               "totalTaxPaid": 250.25,
      |               "pensionSchemeUnauthorisedPaymentsSurcharge": {
      |                  "amount": 120.50,
      |                  "chargeableAmount": 160.50,
      |                  "rate": 10.40,
      |                  "taxPaid": 160.5
      |               },
      |               "pensionSchemeUnauthorisedPaymentsNonSurcharge": {
      |                  "amount": 120.50,
      |                  "chargeableAmount": 160.50,
      |                  "rate": 10.40,
      |                  "taxPaid": 160.5
      |               }
      |            },
      |            "pensionSchemeOverseasTransfers": {
      |               "chargeableAmount": 140.25,
      |               "rate": 60.25,
      |               "transferCharge": 120.25,
      |               "transferChargeTaxPaid": 130.25
      |            },
      |            "pensionContributionsInExcessOfTheAnnualAllowance": {
      |               "annualAllowanceTaxPaid": 180.25,
      |               "pensionBands": [
      |                  {
      |                     "apportionedBandLimit": 2000,
      |                     "bandLimit": 2000,
      |                     "contributionAmount": 160.89,
      |                     "name": "Name",
      |                     "pensionCharge": 180.99,
      |                     "rate": 50.10
      |                  }
      |               ],
      |               "totalContributions": 70.25,
      |               "totalPensionCharge": 160.50,
      |               "totalPensionChargeDue": 120.99
      |            },
      |            "overseasPensionContributions": {
      |               "shortServiceRefundBands": [
      |                  {
      |                     "apportionedBandLimit": 2000,
      |                     "bandLimit": 2000,
      |                     "name": "name",
      |                     "rate": 20.10,
      |                     "shortServiceRefundAmount": 500.50,
      |                     "shortServiceRefundCharge": 750.99
      |                  }
      |               ],
      |               "shortServiceRefundTaxPaid": 160.25,
      |               "totalShortServiceRefund": 100.50,
      |               "totalShortServiceRefundCharge": 200.50,
      |               "totalShortServiceRefundChargeDue": 160.99
      |            }
      |         }
      |      },
      |      "taxDeductedAtSource": {
      |         "bbsi": 300,
      |         "cis": 500.75,
      |         "occupationalPensions": 500.75,
      |         "payeEmployments": 500.75,
      |         "securities": 500.75,
      |         "stateBenefits": 500.75,
      |         "ukLandAndProperty": 300,
      |         "voidedIsa": 500.75,
      |         "specialWithholdingTaxOrUkTaxPaid": 170.5
      |      },
      |      "giftAid": {
      |         "giftAidTax": 400.75,
      |         "grossGiftAidPayments": 400,
      |         "rate": 50.50,
      |         "giftAidTaxReductions": 75.50,
      |         "incomeTaxChargedAfterGiftAidTaxReductions": 50.25,
      |         "giftAidCharge": 100.99
      |      }
      |   }
      |}
    """.stripMargin
  )

  val calculationDetailMinMtdJson: JsValue = Json.parse(
    """
      |{
      |   "incomeTax": {
      |      "payPensionsProfit": {
      |         "allowancesAllocated": 200,
      |         "incomeTaxAmount": 200.50
      |      }
      |   }
      |}
    """.stripMargin
  )

  val calculationDetailMtdJson: JsValue = Json.parse(
    """
      |{
      |   "incomeTax": {
      |      "giftAid": {
      |         "giftAidTax": 400.75,
      |         "grossGiftAidPayments": 400,
      |         "rate": 50.50,
      |         "giftAidTaxReductions": 75.50,
      |         "incomeTaxChargedAfterGiftAidTaxReductions": 50.25,
      |         "giftAidCharge": 100.99
      |      },
      |      "payPensionsProfit": {
      |         "allowancesAllocated": 200,
      |         "incomeTaxAmount": 200.50
      |      }
      |   },
      |   "studentLoans": [
      |      {
      |         "planType": "name",
      |         "studentLoanApportionedIncomeThreshold": 300,
      |         "studentLoanChargeableIncomeAmount": 300.50,
      |         "studentLoanDeductionsFromEmployment": 300.50,
      |         "studentLoanRate": 300.50,
      |         "studentLoanRepaymentAmount": 300.50,
      |         "studentLoanRepaymentAmountNetOfDeductions": 300.50,
      |         "studentLoanTotalIncomeAmount": 300.50
      |      }
      |   ],
      |   "nics": {
      |      "class2Nics": {
      |         "underSmallProfitThreshold": true
      |      }
      |   },
      |   "pensionSavingsTaxCharges": {
      |      "pensionSavingsTaxChargesDetail": {
      |         "excessOfLifetimeAllowance": {
      |            "totalChargeableAmount": 100.99,
      |            "totalTaxPaid": 200.25,
      |            "lumpSumBenefitTakenInExcessOfLifetimeAllowance": {
      |               "amount": 120.50,
      |               "chargeableAmount": 160.50,
      |               "rate": 10.40,
      |               "taxPaid": 160.50
      |            },
      |            "benefitInExcessOfLifetimeAllowance": {
      |               "amount": 120.50,
      |               "chargeableAmount": 160.50,
      |               "rate": 10.40,
      |               "taxPaid": 160.50
      |            }
      |         },
      |         "pensionSchemeUnauthorisedPayments": {
      |            "totalChargeableAmount": 150.99,
      |            "totalTaxPaid": 250.25,
      |            "pensionSchemeUnauthorisedPaymentsSurcharge": {
      |               "amount": 120.50,
      |               "chargeableAmount": 160.50,
      |               "rate": 10.40,
      |               "taxPaid": 160.50
      |            },
      |            "pensionSchemeUnauthorisedPaymentsNonSurcharge": {
      |               "amount": 120.50,
      |               "chargeableAmount": 160.50,
      |               "rate": 10.40,
      |               "taxPaid": 160.50
      |            }
      |         },
      |         "pensionSchemeOverseasTransfers": {
      |            "chargeableAmount": 140.25,
      |            "rate": 60.25,
      |            "transferCharge": 120.25,
      |            "transferChargeTaxPaid": 130.25
      |         },
      |         "pensionContributionsInExcessOfTheAnnualAllowance": {
      |            "annualAllowanceTaxPaid": 180.25,
      |            "pensionBands": [
      |               {
      |                  "apportionedBandLimit": 2000,
      |                  "bandLimit": 2000,
      |                  "contributionAmount": 160.89,
      |                  "name": "Name",
      |                  "pensionCharge": 180.99,
      |                  "rate": 50.10
      |               }
      |            ],
      |            "totalContributions": 70.25,
      |            "totalPensionCharge": 160.50,
      |            "totalPensionChargeDue": 120.99
      |         },
      |         "overseasPensionContributions": {
      |            "shortServiceRefundBands": [
      |               {
      |                  "apportionedBandLimit": 2000,
      |                  "bandLimit": 2000,
      |                  "name": "name",
      |                  "rate": 20.10,
      |                  "shortServiceRefundAmount": 500.50,
      |                  "shortServiceRefundCharge": 750.99
      |               }
      |            ],
      |            "shortServiceRefundTaxPaid": 160.25,
      |            "totalShortServiceRefund": 100.50,
      |            "totalShortServiceRefundCharge": 200.50,
      |            "totalShortServiceRefundChargeDue": 160.99
      |         }
      |      }
      |   },
      |   "taxDeductedAtSource": {
      |      "savings": 300,
      |      "cis": 500.75,
      |      "occupationalPensions": 500.75,
      |      "payeEmployments": 500.75,
      |      "securities": 500.75,
      |      "stateBenefits": 500.75,
      |      "ukLandAndProperty": 300,
      |      "voidedIsa": 500.75,
      |      "specialWithholdingTaxOrUkTaxPaid": 170.5
      |   }
      |}
    """.stripMargin
  )

  val calculationDetailMinModel: CalculationDetail = CalculationDetail(
    IncomeTaxDetail(
      payPensionsProfit = Some(IncomeTypeBreakdown(
        allowancesAllocated = 200,
        incomeTaxAmount = 200.50,
        taxBands = None
      )),
      savingsAndGains = None,
      lumpSums = None,
      dividends = None,
      gainsOnLifePolicies = None,
      giftAid = None
    ),
    studentLoans = None,
    pensionSavingsTaxCharges = None,
    nics = None,
    taxDeductedAtSource = None
  )

  val calculationDetailFilledModel: CalculationDetail = CalculationDetail(
    IncomeTaxDetail(
      payPensionsProfit = Some(IncomeTypeBreakdown(
        allowancesAllocated = 200,
        incomeTaxAmount = 200.50,
        taxBands = None
      )),
      savingsAndGains = None,
      lumpSums = None,
      dividends = None,
      gainsOnLifePolicies = None,
      giftAid = Some(GiftAid(
        grossGiftAidPayments = 400,
        rate = 50.50,
        giftAidTax = 400.75,
        giftAidTaxReductions = Some(75.50),
        incomeTaxChargedAfterGiftAidTaxReductions = Some(50.25),
        giftAidCharge = Some(100.99)
      ))
    ),
    studentLoans = Some(Seq(StudentLoans(
      planType = "name",
      studentLoanTotalIncomeAmount = 300.50,
      studentLoanChargeableIncomeAmount = 300.50,
      studentLoanRepaymentAmount = 300.50,
      studentLoanDeductionsFromEmployment = Some(300.50),
      studentLoanRepaymentAmountNetOfDeductions = 300.50,
      studentLoanApportionedIncomeThreshold = 300,
      studentLoanRate = 300.50
    ))),
    pensionSavingsTaxCharges = Some(PensionSavingsTaxCharges(
      totalPensionCharges = None,
      totalTaxPaid = None,
      totalPensionChargesDue = None,
      pensionSavingsTaxChargesDetail = Some(PensionSavingsTaxChargesDetail(
        excessOfLifetimeAllowance = Some(excessOfLifetimeAllowanceModel),
        pensionSchemeUnauthorisedPayments = Some(pensionSchemeUnauthorisedPaymentsModel),
        pensionSchemeOverseasTransfers = Some(pensionSchemeOverseasTransfersModel),
        pensionContributionsInExcessOfTheAnnualAllowance = Some(pensionContributionsInExcessOfTheAnnualAllowanceModel),
        overseasPensionContributions = Some(overseasPensionContributionsModel)
      )))
    ),
    nics = Some(NicDetail(
      class2Nics = Some(Class2NicDetail(
        weeklyRate = None,
        weeks = None,
        limit = None,
        apportionedLimit = None,
        underSmallProfitThreshold = true,
        actualClass2Nic = None,
        class2VoluntaryContributions = None
      )),
      class4Nics = None
    )),
    taxDeductedAtSource = Some(TaxDeductedAtSource(
      ukLandAndProperty = Some(300),
      savings = Some(300),
      cis = Some(500.75),
      securities = Some(500.75),
      voidedIsa = Some(500.75),
      payeEmployments = Some(500.75),
      occupationalPensions = Some(500.75),
      stateBenefits = Some(500.75),
      specialWithholdingTaxOrUkTaxPaid = Some(170.50)
    ))
  )
}