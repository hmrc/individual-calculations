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

package v1r2.fixtures.getCalculation.incomeTaxAndNics.detail

import play.api.libs.json.{JsValue, Json}
import v1r2.fixtures.getCalculation.incomeTaxAndNics.detail.OverseasPensionContributionsFixtures.overseasPensionContributionsModel
import v1r2.fixtures.getCalculation.incomeTaxAndNics.detail.PensionContributionsInExcessOfTheAnnualAllowanceFixtures.pensionContributionsInExcessOfTheAnnualAllowanceModel
import v1r2.fixtures.getCalculation.incomeTaxAndNics.detail.PensionSchemeOverseasTransfersFixtures.pensionSchemeOverseasTransfersModel
import v1r2.fixtures.getCalculation.incomeTaxAndNics.detail.PensionTypeBreakdownFixtures.pensionTypeBreakdownModel
import v1r2.models.response.getCalculation.incomeTaxAndNics.detail._

object CalculationDetailFixtures {

  val calculationDetailMinJson: JsValue = Json.parse(
    """
      |{
      | "calculation" : {
      |   "taxCalculation" : {
      |     "incomeTax" : {
      |       "payPensionsProfit" : {
      |           "allowancesAllocated" : 200,
      |           "incomeTaxAmount": 200.50
      |        }
      |     }
      |   }
      | }
      |}
    """.stripMargin)

  val calculationDetailInputJsonWithEmptyModels: JsValue = Json.parse(
    """
      |{
      | "calculation" : {
      |   "taxCalculation" : {
      |     "incomeTax" : {
      |       "payPensionsProfit" : {
      |           "allowancesAllocated" : 200,
      |           "incomeTaxAmount": 200.50
      |        }
      |     },
      |     "nics" : {
      |
      |     }
      |   },
      |   "taxDeductedAtSource" : {
      |
      |   }
      | }
      |}
    """.stripMargin)

  val calculationDetailFilledJson: JsValue = Json.parse(
    """
      |{
      |	"calculation": {
      |		"taxCalculation": {
      |			"incomeTax": {
      |				"payPensionsProfit": {
      |					"allowancesAllocated": 200,
      |					"incomeTaxAmount": 200.50
      |				}
      |			},
      |			"nics": {
      |				"class2Nics": {
      |					"underSmallProfitThreshold": true
      |				}
      |			}
      |		},
      |		"pensionSavingsTaxCharges": {
      |			"pensionSavingsTaxChargesDetail": {
      |				"excessOfLifeTimeAllowance": {
      |					"benefitInExcessOfLifetimeAllowance": {
      |						"amount": 120.50,
      |						"chargeableAmount": 160.50,
      |						"rate": 10.40,
      |						"taxPaid": 160.50
      |					},
      |					"lumpSumBenefitTakenInExcessOfLifetimeAllowance": {
      |						"amount": 120.50,
      |						"chargeableAmount": 160.50,
      |						"rate": 10.40,
      |						"taxPaid": 160.50
      |					}
      |				},
      |				"overseasPensionContributions": {
      |					"shortServiceRefundBands": [{
      |						"apportionedBandLimit": 2000,
      |						"bandLimit": 2000,
      |						"name": "name",
      |						"rate": 20.10,
      |						"shortServiceRefundAmount": 500.50,
      |						"shortServiceRefundCharge": 750.99
      |					}],
      |					"shortServiceRefundTaxPaid": 160.25,
      |					"totalShortServiceRefund": 100.50,
      |					"totalShortServiceRefundCharge": 200.50,
      |					"totalShortServiceRefundChargeDue": 160.99
      |				},
      |				"pensionContributionsInExcessOfTheAnnualAllowance": {
      |					"annualAllowanceTaxPaid": 180.25,
      |					"pensionBands": [{
      |						"apportionedBandLimit": 2000,
      |						"bandLimit": 2000,
      |						"contributionAmount": 160.89,
      |						"name": "Name",
      |						"pensionCharge": 180.99,
      |						"rate": 50.10
      |					}],
      |					"totalContributions": 70.25,
      |					"totalPensionCharge": 160.50,
      |					"totalPensionChargeDue": 120.99
      |				},
      |				"pensionSchemeOverseasTransfers": {
      |					"chargeableAmount": 140.25,
      |					"rate": 60.25,
      |					"transferCharge": 120.25,
      |					"transferChargeTaxPaid": 130.25
      |				},
      |				"pensionSchemeUnauthorisedPayments": {
      |					"pensionSchemeUnauthorisedPaymentsNonSurcharge": {
      |						"amount": 120.50,
      |						"chargeableAmount": 160.50,
      |						"rate": 10.40,
      |						"taxPaid": 160.5
      |					},
      |					"pensionSchemeUnauthorisedPaymentsSurcharge": {
      |						"amount": 120.50,
      |						"chargeableAmount": 160.50,
      |						"rate": 10.40,
      |						"taxPaid": 160.5
      |					}
      |				}
      |			}
      |		},
      |		"studentLoans": [{
      |			"planType": "name",
      |			"studentLoanApportionedIncomeThreshold": 300,
      |			"studentLoanChargeableIncomeAmount": 300.50,
      |			"studentLoanDeductionsFromEmployment": 300.50,
      |			"studentLoanRate": 300.50,
      |			"studentLoanRepaymentAmount": 300.50,
      |			"studentLoanRepaymentAmountNetOfDeductions": 300.50,
      |			"studentLoanTotalIncomeAmount": 300.50
      |		}],
      |		"taxDeductedAtSource": {
      |			"bbsi": 300,
      |			"cis": 500.75,
      |			"occupationalPensions": 500.75,
      |			"payeEmployments": 500.75,
      |			"securities": 500.75,
      |			"stateBenefits": 500.75,
      |			"ukLandAndProperty": 300,
      |			"voidedIsa": 500.75
      |		},
      |		"giftAid": {
      |			"giftAidTax": 400.75,
      |			"grossGiftAidPayments": 400,
      |			"rate": 50.50
      |		}
      |	}
      |}
    """.stripMargin)

  val calculationDetailMinOutputJson: JsValue = Json.parse(
    """
      |{
      | "incomeTax" : {
      |   "payPensionsProfit" : {
      |     "allowancesAllocated" : 200,
      |     "incomeTaxAmount" : 200.50
      |   }
      | }
      |}
    """.stripMargin)

  val calculationDetailOutputJson: JsValue = Json.parse(
    """
      |{
      |   "incomeTax":{
      |      "giftAid":{
      |         "giftAidTax":400.75,
      |         "grossGiftAidPayments":400,
      |         "rate":50.50
      |      },
      |      "payPensionsProfit":{
      |         "allowancesAllocated":200,
      |         "incomeTaxAmount":200.50
      |      }
      |   },
      |   "nics":{
      |      "class2Nics":{
      |         "underSmallProfitThreshold":true
      |      }
      |   },
      |   "pensionSavingsTaxCharges":{
      |      "pensionSavingsTaxChargesDetail":{
      |         "benefitInExcessOfLifetimeAllowance":{
      |            "amount":120.50,
      |            "chargeableAmount":160.50,
      |            "rate":10.40,
      |            "taxPaid":160.50
      |         },
      |         "lumpSumBenefitTakenInExcessOfLifetimeAllowance":{
      |            "amount":120.50,
      |            "chargeableAmount":160.50,
      |            "rate":10.40,
      |            "taxPaid":160.50
      |         },
      |         "overseasPensionContributions":{
      |            "shortServiceRefundBands":[
      |               {
      |                  "apportionedBandLimit":2000,
      |                  "bandLimit":2000,
      |                  "name":"name",
      |                  "rate":20.10,
      |                  "shortServiceRefundAmount":500.50,
      |                  "shortServiceRefundCharge":750.99
      |               }
      |            ],
      |            "shortServiceRefundTaxPaid":160.25,
      |            "totalShortServiceRefund":100.50,
      |            "totalShortServiceRefundCharge":200.50,
      |            "totalShortServiceRefundChargeDue":160.99
      |         },
      |         "pensionContributionsInExcessOfTheAnnualAllowance":{
      |            "annualAllowanceTaxPaid":180.25,
      |            "pensionBands":[
      |               {
      |                  "apportionedBandLimit":2000,
      |                  "bandLimit":2000,
      |                  "contributionAmount":160.89,
      |                  "name":"Name",
      |                  "pensionCharge":180.99,
      |                  "rate":50.10
      |               }
      |            ],
      |            "totalContributions":70.25,
      |            "totalPensionCharge":160.50,
      |            "totalPensionChargeDue":120.99
      |         },
      |         "pensionSchemeOverseasTransfers":{
      |            "chargeableAmount":140.25,
      |            "rate":60.25,
      |            "transferCharge":120.25,
      |            "transferChargeTaxPaid":130.25
      |         },
      |         "pensionSchemeUnauthorisedPaymentsNonSurcharge":{
      |            "amount":120.50,
      |            "chargeableAmount":160.50,
      |            "rate":10.40,
      |            "taxPaid":160.50
      |         },
      |         "pensionSchemeUnauthorisedPaymentsSurcharge":{
      |            "amount":120.50,
      |            "chargeableAmount":160.50,
      |            "rate":10.40,
      |            "taxPaid":160.50
      |         }
      |      }
      |   },
      |   "studentLoans":[
      |      {
      |         "planType":"name",
      |         "studentLoanApportionedIncomeThreshold":300,
      |         "studentLoanChargeableIncomeAmount":300.50,
      |         "studentLoanDeductionsFromEmployment":300.50,
      |         "studentLoanRate":300.50,
      |         "studentLoanRepaymentAmount":300.50,
      |         "studentLoanRepaymentAmountNetOfDeductions":300.50,
      |         "studentLoanTotalIncomeAmount":300.50
      |      }
      |   ],
      |   "taxDeductedAtSource":{
      |      "savings":300,
      |      "cis":500.75,
      |      "occupationalPensions":500.75,
      |      "payeEmployments":500.75,
      |      "securities":500.75,
      |      "stateBenefits":500.75,
      |      "ukLandAndProperty":300,
      |      "voidedIsa":500.75
      |   }
      |}
    """.stripMargin)

  val calculationDetailMinModel =
    CalculationDetail(
      IncomeTaxDetail(
        Some(
          IncomeTypeBreakdown(200, 200.50, None)
        ),
        None, None, None, None, None),
      None, None, None, None)

  val calculationDetailFilledModel =
    CalculationDetail(
      IncomeTaxDetail(
        Some(
          IncomeTypeBreakdown(200, 200.50, None)
        ), None, None, None, None,
        Some(
          GiftAid(
            400, 50.50, 400.75)
        )
      ),
      Some(
        Seq(
          StudentLoans(
            "name", 300.50, 300.50, 300.50,
            Some(300.50), 300.50, 300, 300.50)
        )
      ),
      Some(
        PensionSavingsTaxCharges(
          None, None, None, Some(
            PensionSavingsTaxChargesDetail(
              Some(pensionTypeBreakdownModel),
              Some(pensionTypeBreakdownModel),
              Some(pensionTypeBreakdownModel),
              Some(pensionTypeBreakdownModel),
              Some(pensionSchemeOverseasTransfersModel),
              Some(pensionContributionsInExcessOfTheAnnualAllowanceModel),
              Some(overseasPensionContributionsModel)
            )
          )
        )
      ),
      Some(
        NicDetail(
          Some(
            Class2NicDetail(
              None, None, None, None, underSmallProfitThreshold = true, None)), None)
      ),
      Some(
        TaxDeductedAtSource(
          Some(300), Some(300), Some(500.75), Some(500.75), Some(500.75), Some(500.75), Some(500.75), Some(500.75)
        )
      )
    )
}
