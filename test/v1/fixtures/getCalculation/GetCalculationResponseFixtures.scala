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

package v1.fixtures.getCalculation

import play.api.libs.json.{JsObject, JsValue, Json}
import v1.models.des.LossType
import v1.models.domain.{CalculationReason, CalculationRequestor, CalculationType, TypeOfClaim}
import v1.models.response.common.{Messages, Metadata}
import v1.models.response.getCalculation.allowancesAndDeductions.AllowancesDeductionsAndReliefs
import v1.models.response.getCalculation.endOfYearEstimate.EoyEstimate
import v1.models.response.getCalculation.incomeTaxAndNics.IncomeTax
import v1.models.response.getCalculation.taxableIncome.TaxableIncome
import v1.models.response.getCalculation.{GetCalculationResponse, MetadataExistence}

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
        |  "calculation":{
        |    "taxCalculation": {
        |      "incomeTax": {
        |        "incomeTaxCharged": 100.25,
        |        "totalIncomeReceivedFromAllSources": 100,
        |        "totalTaxableIncome": 100,
        |        "totalAllowancesAndDeductions": 100
        |      },
        |      "totalIncomeTaxAndNicsDue": 100.25
        |    },
        |    "endOfYearEstimate": {
        |      "totalEstimatedIncome": 100
        |    }
        |  },
        |  "inputs": {
        |    "personalInformation": {
        |      "taxRegime": "UK"
        |    }
        |  }
        |}
        """.stripMargin).as[JsObject]

  val writtenJson: JsValue = Json.parse(
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
      |    "totalIncomeTaxAndNicsDue": 100.25,
      |    "calculationErrorCount": 1,
      |    "metadataExistence": {
      |      "incomeTaxAndNicsCalculated": true,
      |      "messages": true,
      |      "taxableIncome": true,
      |      "endOfYearEstimate": true,
      |      "allowancesDeductionsAndReliefs": true
      |    }
      |  },
      |  "incomeTaxAndNicsCalculated": {
      |    "summary": {
      |      "incomeTax": {
      |        "incomeTaxCharged": 100.25,
      |        "incomeTaxDueAfterReliefs": 100.25,
      |        "incomeTaxDueAfterGiftAid": 100.25,
      |        "totalNotionalTax": 100.25,
      |        "totalPensionSavingsTaxCharges": 100.25,
      |        "statePensionLumpSumCharges": 100.25,
      |        "incomeTaxDueAfterTaxReductions": 100.25,
      |        "totalIncomeTaxDue": 100.25
      |      },
      |      "nics": {
      |        "class2NicsAmount": 100.25,
      |        "class4NicsAmount": 100.25,
      |        "totalNic": 100.25
      |      },
      |      "totalStudentLoansRepaymentAmount": 100.25,
      |      "totalAnnualPaymentsTaxCharged": 100.25,
      |      "totalRoyaltyPaymentsTaxCharged": 100.25,
      |      "totalIncomeTaxNicsCharged": 100.25,
      |      "totalTaxDeducted": 100.25,
      |      "totalIncomeTaxAndNicsDue": 100.25,
      |      "taxRegime": "Regime A"
      |    },
      |    "detail": {
      |      "incomeTax": {
      |        "payPensionsProfit": {
      |          "allowancesAllocated": 100,
      |          "incomeTaxAmount": 100.25,
      |          "taxBands": [
      |            {
      |              "name": "Band A",
      |              "rate": 100.25,
      |              "bandLimit": 100,
      |              "apportionedBandLimit": 100,
      |              "income": 100,
      |              "taxAmount": 100.25
      |            }
      |          ]
      |        },
      |        "savingsAndGains": {
      |          "allowancesAllocated": 100,
      |          "incomeTaxAmount": 100.25,
      |          "taxBands": [
      |            {
      |              "name": "Band A",
      |              "rate": 100.25,
      |              "bandLimit": 100,
      |              "apportionedBandLimit": 100,
      |              "income": 100,
      |              "taxAmount": 100.25
      |            }
      |          ]
      |        },
      |        "lumpSums": {
      |          "allowancesAllocated": 100,
      |          "incomeTaxAmount": 100.25,
      |          "taxBands": [
      |            {
      |              "name": "Band A",
      |              "rate": 100.25,
      |              "bandLimit": 100,
      |              "apportionedBandLimit": 100,
      |              "income": 100,
      |              "taxAmount": 100.25
      |            }
      |          ]
      |        },
      |        "dividends": {
      |          "allowancesAllocated": 100,
      |          "incomeTaxAmount": 100.25,
      |          "taxBands": [
      |            {
      |              "name": "Band A",
      |              "rate": 100.25,
      |              "bandLimit": 100,
      |              "apportionedBandLimit": 100,
      |              "income": 100,
      |              "taxAmount": 100.25
      |            }
      |          ]
      |        },
      |        "gainsOnLifePolicies": {
      |          "allowancesAllocated": 100,
      |          "incomeTaxAmount": 100.25,
      |          "taxBands": [
      |            {
      |              "name": "Band A",
      |              "rate": 100.25,
      |              "bandLimit": 100,
      |              "apportionedBandLimit": 100,
      |              "income": 100,
      |              "taxAmount": 100.25
      |            }
      |          ]
      |        },
      |        "giftAid": {
      |          "grossGiftAidPayments": 100,
      |          "rate": 100.25,
      |          "giftAidTax": 100.25
      |        }
      |      },
      |      "studentLoans": [
      |        {
      |          "planType": "Plan A",
      |          "studentLoanTotalIncomeAmount": 100.25,
      |          "studentLoanChargeableIncomeAmount": 100.25,
      |          "studentLoanRepaymentAmount": 100.25,
      |          "studentLoanDeductionsFromEmployment": 100.25,
      |          "studentLoanRepaymentAmountNetOfDeductions": 100.25,
      |          "studentLoanApportionedIncomeThreshold": 100,
      |          "studentLoanRate": 100.25
      |        }
      |      ],
      |      "pensionSavingsTaxCharges": {
      |        "totalPensionCharges": 100.25,
      |        "totalTaxPaid": 100.25,
      |        "totalPensionChargesDue": 100.25,
      |        "pensionSavingsTaxChargesDetail": {
      |          "lumpSumBenefitTakenInExcessOfLifetimeAllowance": {
      |            "amount": 100.25,
      |            "taxPaid": 100.25,
      |            "rate": 100.25,
      |            "chargeableAmount": 100.25
      |          },
      |          "benefitInExcessOfLifetimeAllowance": {
      |            "amount": 100.25,
      |            "taxPaid": 100.25,
      |            "rate": 100.25,
      |            "chargeableAmount": 100.25
      |          },
      |          "pensionSchemeUnauthorisedPaymentsSurcharge": {
      |            "amount": 100.25,
      |            "taxPaid": 100.25,
      |            "rate": 100.25,
      |            "chargeableAmount": 100.25
      |          },
      |          "pensionSchemeUnauthorisedPaymentsNonSurcharge": {
      |            "amount": 100.25,
      |            "taxPaid": 100.25,
      |            "rate": 100.25,
      |            "chargeableAmount": 100.25
      |          },
      |          "pensionSchemeOverseasTransfers": {
      |            "transferCharge": 100.25,
      |            "transferChargeTaxPaid": 100.25,
      |            "rate": 100.25,
      |            "chargeableAmount": 100.25
      |          },
      |          "pensionContributionsInExcessOfTheAnnualAllowance": {
      |            "totalContributions": 100.25,
      |            "totalPensionCharge": 100.25,
      |            "annualAllowanceTaxPaid": 100.25,
      |            "totalPensionChargeDue": 100.25,
      |            "pensionBands": [
      |              {
      |                "name": "Band A",
      |                "rate": 100.25,
      |                "bandLimit": 100,
      |                "apportionedBandLimit": 100,
      |                "contributionAmount": 100.25,
      |                "pensionCharge": 100.25
      |              }
      |            ]
      |          },
      |          "overseasPensionContributions": {
      |            "totalShortServiceRefund": 100.25,
      |            "totalShortServiceRefundCharge": 100.25,
      |            "shortServiceTaxPaid": 100.25,
      |            "totalShortServiceRefundChargeDue": 100.25,
      |            "shortServiceRefundBands": [
      |              {
      |                "name": "Band A",
      |                "rate": 100.25,
      |                "bandLimit": 100,
      |                "apportionedBandLimit": 100,
      |                "shortServiceRefundAmount": 100.25,
      |                "shortServiceRefundCharge": 100.25
      |              }
      |            ]
      |          }
      |        }
      |      },
      |      "nics": {
      |        "class2Nics": {
      |          "weeklyRate": 100.25,
      |          "weeks": 100.25,
      |          "limit": 100.25,
      |          "apportionedLimit": 100.25,
      |          "underSmallProfitThreshold": true,
      |          "actualClass2Nic": true
      |        },
      |        "class4Nics": {
      |          "class4Losses": {
      |            "totalClass4LossesAvailable": 100,
      |            "totalClass4LossesUsed": 100,
      |            "totalClass4LossesCarriedForward": 100
      |          },
      |          "totalIncomeLiableToClass4Charge": 100,
      |          "totalIncomeChargeableToClass4": 100,
      |          "class4NicBands": [
      |            {
      |              "name": "Band A",
      |              "rate": 100.25,
      |              "threshold": 100,
      |              "apportionedThreshold": 100,
      |              "income": 100,
      |              "amount": 100.25
      |            }
      |          ]
      |        }
      |      },
      |      "taxDeductedAtSource": {
      |        "ukLandAndProperty": 100,
      |        "savings": 100,
      |        "cis": 100.25,
      |        "securities": 100.25,
      |        "voidedIsa": 100.25,
      |        "payeEmployments": 100.25,
      |        "occupationalPensions": 100.25,
      |        "stateBenefits": 100.25
      |      }
      |    }
      |  },
      |  "messages": {
      |    "info": [
      |      {
      |        "id": "id",
      |        "text": "text"
      |      }
      |    ],
      |    "warnings": [
      |      {
      |        "id": "id",
      |        "text": "text"
      |      }
      |    ],
      |    "errors": [
      |      {
      |        "id": "id",
      |        "text": "text"
      |      }
      |    ]
      |  },
      |  "taxableIncome": {
      |    "summary": {
      |      "totalIncomeReceivedFromAllSources": 100,
      |      "totalTaxableIncome": 100
      |    },
      |    "detail": {
      |      "payPensionsProfit": {
      |        "incomeReceived": 100,
      |        "taxableIncome": 100,
      |        "totalSelfEmploymentProfit": 100,
      |        "totalPropertyProfit": 100,
      |        "totalFHLPropertyProfit": 100,
      |        "totalUKOtherPropertyProfit": 100,
      |        "totalForeignPropertyProfit": 100,
      |        "totalEeaFhlProfit": 100,
      |        "totalOccupationalPensionIncome": 100.25,
      |        "totalStateBenefitsIncome": 100.25,
      |        "totalBenefitsInKind": 100.25,
      |        "totalPayeEmploymentAndLumpSumIncome": 100.25,
      |        "totalEmploymentExpenses": 100.25,
      |        "totalEmploymentIncome": 100,
      |        "businessProfitAndLoss": {
      |          "selfEmployments": [
      |            {
      |              "selfEmploymentId": "id",
      |              "totalIncome": 100.25,
      |              "totalExpenses": 100.25,
      |              "netProfit": 100.25,
      |              "netLoss": 100.25,
      |              "class4Loss": 100,
      |              "totalAdditions": 100.25,
      |              "totalDeductions": 100.25,
      |              "accountingAdjustments": 100.25,
      |              "adjustedIncomeTaxLoss": 100,
      |              "taxableProfit": 100.25,
      |              "taxableProfitAfterIncomeTaxLossesDeduction": 100,
      |              "lossClaimsSummary": {
      |                "totalBroughtForwardIncomeTaxLosses": 100,
      |                "broughtForwardIncomeTaxLossesUsed": 100,
      |                "carrySidewaysIncomeTaxLossesUsed": 100,
      |                "totalIncomeTaxLossesCarriedForward": 100,
      |                "totalBroughtForwardClass4Losses": 100,
      |                "broughtForwardClass4LossesUsed": 100,
      |                "carrySidewaysClass4LossesUsed": 100,
      |                "totalClass4LossesCarriedForward": 100
      |              },
      |              "lossClaimsDetail": {
      |                "lossesBroughtForward": [
      |                  {
      |                    "lossType": "income",
      |                    "currentLossValue": 100,
      |                    "taxYearLossIncurred": "this year",
      |                    "mtdLoss": true
      |                  }
      |                ],
      |                "resultOfClaimsApplied": [
      |                  {
      |                    "claimType": "carry-forward",
      |                    "lossType": "income",
      |                    "lossAmountUsed": 100,
      |                    "claimId": "id",
      |                    "remainingLossValue": 100,
      |                    "taxYearClaimMade": "this year",
      |                    "mtdLoss": true,
      |                    "taxYearLossIncurred": "this year"
      |                  }
      |                ],
      |                "unclaimedLosses": [
      |                  {
      |                    "lossType": "income",
      |                    "currentLossValue": 100,
      |                    "taxYearLossIncurred": "this year"
      |                  }
      |                ],
      |                "carriedForwardLosses": [
      |                  {
      |                    "claimType": "carry-forward",
      |                    "lossType": "income",
      |                    "currentLossValue": 100,
      |                    "claimId": "id",
      |                    "taxYearClaimMade": "this year",
      |                    "taxYearLossIncurred": "this year"
      |                  }
      |                ],
      |                "claimsNotApplied": [
      |                  {
      |                    "claimType": "carry-forward",
      |                    "claimId": "id",
      |                    "taxYearClaimMade": "this year"
      |                  }
      |                ]
      |              },
      |              "bsas": {
      |                "bsasId": "id",
      |                "applied": true
      |              }
      |            }
      |          ],
      |          "ukPropertyFhl": {
      |            "totalIncome": 100.25,
      |            "totalExpenses": 100.25,
      |            "netProfit": 100.25,
      |            "netLoss": 100.25,
      |            "totalAdditions": 100.25,
      |            "totalDeductions": 100.25,
      |            "adjustedIncomeTaxLoss": 100,
      |            "taxableProfit": 100,
      |            "taxableProfitAfterIncomeTaxLossesDeduction": 100,
      |            "lossClaimsSummary": {
      |              "lossForCSFHL": 100,
      |              "totalBroughtForwardIncomeTaxLosses": 100,
      |              "broughtForwardIncomeTaxLossesUsed": 100,
      |              "totalIncomeTaxLossesCarriedForward": 100
      |            },
      |            "lossClaimsDetail": {
      |              "lossesBroughtForward": [
      |                {
      |                  "taxYearLossIncurred": "this year",
      |                  "currentLossValue": 100,
      |                  "mtdLoss": true
      |                }
      |              ],
      |              "resultOfClaimsApplied": [
      |                {
      |                  "claimId": "id",
      |                  "taxYearClaimMade": "this year",
      |                  "claimType": "carry-forward",
      |                  "mtdLoss": true,
      |                  "taxYearLossIncurred": "this year",
      |                  "lossAmountUsed": 100,
      |                  "remainingLossValue": 100
      |                }
      |              ],
      |              "defaultCarriedForwardLosses": [
      |                {
      |                  "taxYearLossIncurred": "this year",
      |                  "currentLossValue": 100
      |                }
      |              ]
      |            },
      |            "bsas": {
      |              "bsasId": "id",
      |              "applied": true
      |            }
      |          },
      |          "ukPropertyNonFhl": {
      |            "totalIncome": 100.25,
      |            "totalExpenses": 100.25,
      |            "netProfit": 100.25,
      |            "netLoss": 100.25,
      |            "totalAdditions": 100.25,
      |            "totalDeductions": 100.25,
      |            "accountingAdjustments": 100.25,
      |            "adjustedIncomeTaxLoss": 100,
      |            "taxableProfit": 100,
      |            "taxableProfitAfterIncomeTaxLossesDeduction": 100,
      |            "lossClaimsSummary": {
      |              "totalBroughtForwardIncomeTaxLosses": 100,
      |              "broughtForwardIncomeTaxLossesUsed": 100,
      |              "carrySidewaysIncomeTaxLossesUsed": 100,
      |              "totalIncomeTaxLossesCarriedForward": 100,
      |              "broughtForwardCarrySidewaysIncomeTaxLossesUsed": 100
      |            },
      |            "lossClaimsDetail": {
      |              "lossesBroughtForward": [
      |                {
      |                  "taxYearLossIncurred": "this year",
      |                  "currentLossValue": 100,
      |                  "mtdLoss": true
      |                }
      |              ],
      |              "resultOfClaimsApplied": [
      |                {
      |                  "claimId": "id",
      |                  "originatingClaimId": "id",
      |                  "taxYearClaimMade": "this year",
      |                  "claimType": "carry-forward",
      |                  "mtdLoss": true,
      |                  "taxYearLossIncurred": "this year",
      |                  "lossAmountUsed": 100,
      |                  "remainingLossValue": 100
      |                }
      |              ],
      |              "defaultCarriedForwardLosses": [
      |                {
      |                  "taxYearLossIncurred": "this year",
      |                  "currentLossValue": 100
      |                }
      |              ],
      |              "claimsNotApplied": [
      |                {
      |                  "claimId": "id",
      |                  "taxYearClaimMade": "this year",
      |                  "claimType": "carry-forward"
      |                }
      |              ]
      |            },
      |            "bsas": {
      |              "bsasId": "id",
      |              "applied": true
      |            }
      |          },
      |          "eeaPropertyFhl": {
      |            "totalIncome": 100.25,
      |            "totalExpenses": 100.25,
      |            "netProfit": 100.25,
      |            "netLoss": 100.25,
      |            "totalAdditions": 100.25,
      |            "totalDeductions": 100.25,
      |            "adjustedIncomeTaxLoss": 100,
      |            "taxableProfit": 100,
      |            "taxableProfitAfterIncomeTaxLossesDeduction": 100,
      |            "lossClaimsSummary": {
      |              "lossForCSFHL": 100,
      |              "totalBroughtForwardIncomeTaxLosses": 100,
      |              "broughtForwardIncomeTaxLossesUsed": 100,
      |              "totalIncomeTaxLossesCarriedForward": 100
      |            },
      |            "lossClaimsDetail": {
      |              "lossesBroughtForward": [
      |                {
      |                  "taxYearLossIncurred": "this year",
      |                  "currentLossValue": 100,
      |                  "mtdLoss": true
      |                }
      |              ],
      |              "resultOfClaimsApplied": [
      |                {
      |                  "claimId": "id",
      |                  "taxYearClaimMade": "this year",
      |                  "claimType": "carry-forward",
      |                  "mtdLoss": true,
      |                  "taxYearLossIncurred": "this year",
      |                  "lossAmountUsed": 100,
      |                  "remainingLossValue": 100
      |                }
      |              ],
      |              "defaultCarriedForwardLosses": [
      |                {
      |                  "taxYearLossIncurred": "this year",
      |                  "currentLossValue": 100
      |                }
      |              ]
      |            },
      |            "bsas": {
      |              "bsasId": "id",
      |              "applied": true
      |            }
      |          },
      |          "foreignProperty": {
      |            "totalIncome": 100.25,
      |            "totalExpenses": 100.25,
      |            "netProfit": 100.25,
      |            "netLoss": 100.25,
      |            "totalAdditions": 100.25,
      |            "totalDeductions": 100.25,
      |            "accountingAdjustments": 100.25,
      |            "adjustedIncomeTaxLoss": 100,
      |            "taxableProfit": 100,
      |            "taxableProfitAfterIncomeTaxLossesDeduction": 100,
      |            "lossClaimsSummary": {
      |              "totalBroughtForwardIncomeTaxLosses": 100,
      |              "broughtForwardIncomeTaxLossesUsed": 100,
      |              "carrySidewaysIncomeTaxLossesUsed": 100,
      |              "totalIncomeTaxLossesCarriedForward": 100,
      |              "broughtForwardCarrySidewaysIncomeTaxLossesUsed": 100
      |            },
      |            "lossClaimsDetail": {
      |              "lossesBroughtForward": [
      |                {
      |                  "taxYearLossIncurred": "this year",
      |                  "currentLossValue": 100,
      |                  "mtdLoss": true
      |                }
      |              ],
      |              "resultOfClaimsApplied": [
      |                {
      |                  "claimId": "id",
      |                  "originatingClaimId": "id",
      |                  "taxYearClaimMade": "this year",
      |                  "claimType": "carry-forward",
      |                  "mtdLoss": true,
      |                  "taxYearLossIncurred": "this year",
      |                  "lossAmountUsed": 100,
      |                  "remainingLossValue": 100
      |                }
      |              ],
      |              "defaultCarriedForwardLosses": [
      |                {
      |                  "taxYearLossIncurred": "this year",
      |                  "currentLossValue": 100
      |                }
      |              ],
      |              "claimsNotApplied": [
      |                {
      |                  "claimId": "id",
      |                  "taxYearClaimMade": "this year",
      |                  "claimType": "carry-forward"
      |                }
      |              ]
      |            },
      |            "bsas": {
      |              "bsasId": "id",
      |              "applied": true
      |            }
      |          }
      |        }
      |      },
      |      "savingsAndGains": {
      |        "incomeReceived": 100,
      |        "taxableIncome": 100,
      |        "ukSavings": [
      |          {
      |            "savingsAccountId": "id",
      |            "savingsAccountName": "name",
      |            "grossIncome": 100.25,
      |            "netIncome": 100.25,
      |            "taxDeducted": 100.25
      |          }
      |        ],
      |        "ukSecurities": [
      |          {
      |            "ukSecuritiesAccountId": "id",
      |            "ukSecuritiesAccountName": "name",
      |            "grossIncome": 100.25,
      |            "netIncome": 100.25,
      |            "taxDeducted": 100.25
      |          }
      |        ]
      |      },
      |      "dividends": {
      |        "incomeReceived": 100,
      |        "taxableIncome": 100
      |      },
      |      "lumpSums": {
      |        "incomeReceived": 100,
      |        "taxableIncome": 100
      |      },
      |      "gainsOnLifePolicies": {
      |        "incomeReceived": 100,
      |        "taxableIncome": 100
      |      }
      |    }
      |  },
      |  "endOfYearEstimate": {
      |    "summary": {
      |      "totalEstimatedIncome": 100,
      |      "totalTaxableIncome": 100,
      |      "incomeTaxAmount": 100.25,
      |      "nic2": 100.25,
      |      "nic4": 100.25,
      |      "totalNicAmount": 100.25,
      |      "totalStudentLoansRepaymentAmount": 100.25,
      |      "totalAnnualPaymentsTaxCharged": 100.25,
      |      "totalRoyaltyPaymentsTaxCharged": 100.25,
      |      "totalTaxDeducted": 100.25,
      |      "incomeTaxNicAmount": 100.25
      |    },
      |    "detail": {
      |      "selfEmployments": [
      |        {
      |          "selfEmploymentId": "id",
      |          "taxableIncome": 100,
      |          "finalised": true
      |        }
      |      ],
      |      "ukPropertyFhl": {
      |        "taxableIncome": 100,
      |        "finalised": true
      |      },
      |      "ukPropertyNonFhl": {
      |        "taxableIncome": 100,
      |        "finalised": true
      |      },
      |      "ukSavings": [
      |        {
      |          "savingsAccountId": "id",
      |          "savingsAccountName": "name",
      |          "taxableIncome": 100
      |        }
      |      ],
      |      "ukDividends": {
      |        "taxableIncome": 100
      |      },
      |      "otherDividends": {
      |        "taxableIncome": 100
      |      },
      |      "stateBenefits": {
      |        "taxableIncome": 100
      |      },
      |      "ukSecurities": {
      |        "taxableIncome": 100
      |      },
      |      "foreignProperty": {
      |        "taxableIncome": 100,
      |        "finalised": true
      |      },
      |      "foreignInterest": {
      |        "taxableIncome": 100
      |      }
      |    }
      |  },
      |  "allowancesDeductionsAndReliefs": {
      |    "summary": {
      |      "totalAllowancesAndDeductions": 100,
      |      "totalReliefs": 100.25
      |    },
      |    "detail": {
      |      "allowancesAndDeductions": {
      |        "personalAllowance": 100,
      |        "reducedPersonalAllowance": 100,
      |        "giftOfInvestmentsAndPropertyToCharity": 100,
      |        "blindPersonsAllowance": 100,
      |        "lossesAppliedToGeneralIncome": 100,
      |        "qualifyingLoanInterestFromInvestments": 100.25,
      |        "postCessationTradeReceipts": 100.25,
      |        "paymentsToTradeUnionsForDeathBenefits": 100.25,
      |        "annualPayments": {
      |          "grossAnnualPayments": 100.25,
      |          "reliefClaimed": 100.25,
      |          "rate": 100.25
      |        },
      |        "pensionContributions": {
      |          "totalPensionContributions": 100.25,
      |          "retirementAnnuityPayments": 100.25,
      |          "paymentToEmployersSchemeNoTaxRelief": 100.25,
      |          "overseasPensionSchemeContributions": 100.25
      |        }
      |      },
      |      "reliefs": {
      |        "residentialFinanceCosts": {
      |          "amountClaimed": 100.25,
      |          "allowableAmount": 100.25,
      |          "rate": 100.25,
      |          "propertyFinanceRelief": 100.25
      |        },
      |        "foreignTaxCreditRelief": [
      |          {
      |            "incomeSourceType": "dividendsFromForeignCompanies",
      |            "incomeSourceId": "id",
      |            "countryCode": "GB",
      |            "allowableAmount": 100.25,
      |            "rate": 100.25,
      |            "amountUsed": 100.25
      |          }
      |        ],
      |        "pensionContributionReliefs": {
      |          "totalPensionContributionReliefs": 100.25,
      |          "regularPensionContributions": 100.25,
      |          "oneOffPensionContributionsPaid": 100.25
      |        },
      |        "reliefsClaimed": [
      |          {
      |            "type": "this type",
      |            "amountClaimed": 100.25,
      |            "allowableAmount": 100.25,
      |            "amountUsed": 100.25,
      |            "rate": 100.25
      |          }
      |        ]
      |      }
      |    }
      |  }
      |}
      |""".stripMargin)


  val metadata: Metadata = Metadata(
    id = "f2fb30e5-4ab6-4a29-b3c1-c7264259ff1c",
    taxYear = "2018-19",
    requestedBy = CalculationRequestor.customer,
    requestedTimestamp = Some("2019-11-15T09:25:15.094Z"),
    calculationReason = CalculationReason.customerRequest,
    calculationTimestamp = Some("2019-11-15T09:35:15.094Z"),
    calculationType = CalculationType.inYear,
    intentToCrystallise = false,
    crystallised = false,
    totalIncomeTaxAndNicsDue = Some(100.25),
    calculationErrorCount = Some(1),
    metadataExistence = Some(MetadataExistence(true, true, true, true, true))
  )

  val incomeTaxAndNicsCalculated: IncomeTax = {
    import v1.models.response.getCalculation.incomeTaxAndNics.detail._
    import v1.models.response.getCalculation.incomeTaxAndNics.summary._

    val incomeTypeBreakdown = IncomeTypeBreakdown(100, 100.25, Some(Seq(TaxBand(
      name = "Band A",
      rate = 100.25,
      bandLimit = 100,
      apportionedBandLimit = 100,
      income = 100,
      taxAmount = 100.25
    ))))

    val pensionTypeBreakdown = PensionTypeBreakdown(
      Some(100.25),
      Some(100.25),
      Some(100.25),
      Some(100.25)
    )

    IncomeTax(
      summary = CalculationSummary(
        incomeTax = IncomeTaxSummary(
          incomeTaxCharged = 100.25,
          incomeTaxDueAfterReliefs = Some(100.25),
          incomeTaxDueAfterGiftAid = Some(100.25),
          totalNotionalTax = Some(100.25),
          totalPensionSavingsTaxCharges = Some(100.25),
          statePensionLumpSumCharges = Some(100.25),
          incomeTaxDueAfterTaxReductions = Some(100.25),
          totalIncomeTaxDue = Some(100.25)
        ),
        nics = Some(NicSummary(
          class2NicsAmount = Some(100.25),
          class4NicsAmount = Some(100.25),
          totalNic = Some(100.25)
        )),
        totalStudentLoansRepaymentAmount = Some(100.25),
        totalAnnualPaymentsTaxCharged = Some(100.25),
        totalRoyaltyPaymentsTaxCharged = Some(100.25),
        totalIncomeTaxNicsCharged = Some(100.25),
        totalTaxDeducted = Some(100.25),
        totalIncomeTaxAndNicsDue = 100.25,
        taxRegime = "Regime A"
      ),
      detail = CalculationDetail(
        incomeTax = IncomeTaxDetail(
          payPensionsProfit = Some(incomeTypeBreakdown),
          savingsAndGains = Some(incomeTypeBreakdown),
          lumpSums = Some(incomeTypeBreakdown),
          dividends = Some(incomeTypeBreakdown),
          gainsOnLifePolicies = Some(incomeTypeBreakdown),
          giftAid = Some(GiftAid(100, 100.25, 100.25))
        ),
        studentLoans = Some(Seq(StudentLoans(
          planType = "Plan A",
          studentLoanTotalIncomeAmount = 100.25,
          studentLoanChargeableIncomeAmount = 100.25,
          studentLoanRepaymentAmount = 100.25,
          studentLoanDeductionsFromEmployment = Some(100.25),
          studentLoanRepaymentAmountNetOfDeductions = 100.25,
          studentLoanApportionedIncomeThreshold = 100,
          studentLoanRate = 100.25
        ))),
        pensionSavingsTaxCharges = Some(PensionSavingsTaxCharges(
          totalPensionCharges = Some(100.25),
          totalTaxPaid = Some(100.25),
          totalPensionChargesDue = Some(100.25),
          pensionSavingsTaxChargesDetail = Some(PensionSavingsTaxChargesDetail(
            Some(pensionTypeBreakdown),
            Some(pensionTypeBreakdown),
            Some(pensionTypeBreakdown),
            Some(pensionTypeBreakdown),
            Some(PensionSchemeOverseasTransfers(
              transferCharge = Some(100.25),
              transferChargeTaxPaid = Some(100.25),
              rate = Some(100.25),
              chargeableAmount = Some(100.25)
            )),
            Some(PensionContributionsInExcessOfTheAnnualAllowance(
              totalContributions = 100.25,
              totalPensionCharge = 100.25,
              annualAllowanceTaxPaid = Some(100.25),
              totalPensionChargeDue = 100.25,
              pensionBands = Some(Seq(PensionBands(
                name = "Band A",
                rate = 100.25,
                bandLimit = 100,
                apportionedBandLimit = 100,
                contributionAmount = 100.25,
                pensionCharge = 100.25
              )))
            )
            ),
            Some(OverseasPensionContributions(
              totalShortServiceRefund = 100.25,
              totalShortServiceRefundCharge = 100.25,
              shortServiceTaxPaid = Some(100.25),
              totalShortServiceRefundChargeDue = 100.25,
              shortServiceRefundBands = Some(Seq(ShortServiceRefundBands(
                name = "Band A",
                rate = 100.25,
                bandLimit = 100,
                apportionedBandLimit = 100,
                shortServiceRefundAmount = 100.25,
                shortServiceRefundCharge = 100.25
              )))
            ))
          ))
        )),
        nics = Some(NicDetail(
          Some(Class2NicDetail(
            weeklyRate = Some(100.25),
            weeks = Some(100.25),
            limit = Some(100.25),
            apportionedLimit = Some(100.25),
            underSmallProfitThreshold = true,
            actualClass2Nic = Some(true)
          )),
          Some(Class4NicDetail(
            class4Losses = Some(Class4Losses(
              totalClass4LossesAvailable = Some(100),
              totalClass4LossesUsed = Some(100),
              totalClass4LossesCarriedForward = Some(100)
            )),
            totalIncomeLiableToClass4Charge = Some(100),
            totalIncomeChargeableToClass4 = Some(100),
            class4NicBands = Some(Seq(NicBand(
              name = "Band A",
              rate = 100.25,
              threshold = Some(100),
              apportionedThreshold = Some(100),
              income = 100,
              amount = 100.25
            )))
          ))
        )),
        taxDeductedAtSource = Some(TaxDeductedAtSource(
          ukLandAndProperty = Some(100),
          savings = Some(100),
          cis = Some(100.25),
          securities = Some(100.25),
          voidedIsa = Some(100.25),
          payeEmployments = Some(100.25),
          occupationalPensions = Some(100.25),
          stateBenefits = Some(100.25)
        ))
      )
    )
  }

  val messages: Messages = {
    import v1.models.response.common._

    Messages(
      info = Some(Seq(Message(id = "id", text = "text"))),
      warnings = Some(Seq(Message(id = "id", text = "text"))),
      errors = Some(Seq(Message(id = "id", text = "text")))
    )
  }

  val taxableIncome: TaxableIncome = {
    import v1.models.response.getCalculation.taxableIncome._
    import v1.models.response.getCalculation.taxableIncome.detail._
    import v1.models.response.getCalculation.taxableIncome.detail.payPensionsProfit._
    import v1.models.response.getCalculation.taxableIncome.detail.payPensionsProfit.eeaPropertyFhl._
    import v1.models.response.getCalculation.taxableIncome.detail.payPensionsProfit.eeaPropertyFhl.detail._
    import v1.models.response.getCalculation.taxableIncome.detail.payPensionsProfit.foreignProperty._
    import v1.models.response.getCalculation.taxableIncome.detail.payPensionsProfit.foreignProperty.detail._
    import v1.models.response.getCalculation.taxableIncome.detail.payPensionsProfit.selfEmployment._
    import v1.models.response.getCalculation.taxableIncome.detail.payPensionsProfit.selfEmployment.detail._
    import v1.models.response.getCalculation.taxableIncome.detail.payPensionsProfit.ukPropertyFhl._
    import v1.models.response.getCalculation.taxableIncome.detail.payPensionsProfit.ukPropertyFhl.detail._
    import v1.models.response.getCalculation.taxableIncome.detail.payPensionsProfit.ukPropertyNonFhl._
    import v1.models.response.getCalculation.taxableIncome.detail.payPensionsProfit.ukPropertyNonFhl.detail._

    TaxableIncome(
      TaxableIncomeSummary(totalIncomeReceivedFromAllSources = 100, totalTaxableIncome = 100),
      TaxableIncomeDetail(
        payPensionsProfit = Some(PayPensionsProfit(
          incomeReceived = 100,
          taxableIncome = 100,
          totalSelfEmploymentProfit = Some(100),
          totalPropertyProfit = Some(100),
          totalFHLPropertyProfit = Some(100),
          totalUKOtherPropertyProfit = Some(100),
          totalForeignPropertyProfit = Some(100),
          totalEeaFhlProfit = Some(100),
          totalOccupationalPensionIncome = Some(100.25),
          totalStateBenefitsIncome = Some(100.25),
          totalBenefitsInKind = Some(100.25),
          totalPayeEmploymentAndLumpSumIncome = Some(100.25),
          totalEmploymentExpenses = Some(100.25),
          totalEmploymentIncome = Some(100),
          businessProfitAndLoss = Some(BusinessProfitAndLoss(
            selfEmployments = Some(Seq(SelfEmployment(
              selfEmploymentId = "id",
              totalIncome = Some(100.25),
              totalExpenses = Some(100.25),
              netProfit = Some(100.25),
              netLoss = Some(100.25),
              class4Loss = Some(100),
              totalAdditions = Some(100.25),
              totalDeductions = Some(100.25),
              accountingAdjustments = Some(100.25),
              adjustedIncomeTaxLoss = Some(100),
              taxableProfit = Some(100.25),
              taxableProfitAfterIncomeTaxLossesDeduction = Some(100),
              lossClaimsSummary = Some(SelfEmploymentLossClaimsSummary(
                totalBroughtForwardIncomeTaxLosses = Some(100),
                broughtForwardIncomeTaxLossesUsed = Some(100),
                carrySidewaysIncomeTaxLossesUsed = Some(100),
                totalIncomeTaxLossesCarriedForward = Some(100),
                totalBroughtForwardClass4Losses = Some(100),
                broughtForwardClass4LossesUsed = Some(100),
                carrySidewaysClass4LossesUsed = Some(100),
                totalClass4LossesCarriedForward = Some(100)
              )),
              lossClaimsDetail = Some(SelfEmploymentLossClaimsDetail(
                lossesBroughtForward = Some(Seq(SelfEmploymentLossBroughtForward(
                  lossType = LossType.INCOME,
                  taxYearLossIncurred = "this year",
                  currentLossValue = 100,
                  mtdLoss = true,
                  incomeSourceId = "id"
                ))),
                resultOfClaimsApplied = Some(Seq(SelfEmploymentResultOfClaimApplied(
                  claimId = Some("id"),
                  taxYearClaimMade = "this year",
                  claimType = TypeOfClaim.`carry-forward`,
                  mtdLoss = true,
                  taxYearLossIncurred = "this year",
                  lossAmountUsed = 100,
                  remainingLossValue = 100,
                  lossType = LossType.INCOME,
                  incomeSourceId = "id"
                ))),
                unclaimedLosses = Some(Seq(SelfEmploymentUnclaimedLoss(
                  taxYearLossIncurred = "this year",
                  currentLossValue = 100,
                  lossType = LossType.INCOME,
                  incomeSourceId = "id"
                ))),
                carriedForwardLosses = Some(Seq(SelfEmploymentCarriedForwardLoss(
                  claimId = Some("id"),
                  claimType = TypeOfClaim.`carry-forward`,
                  taxYearClaimMade = Some("this year"),
                  taxYearLossIncurred = "this year",
                  currentLossValue = 100,
                  lossType = LossType.INCOME,
                  incomeSourceId = "id"
                ))),
                claimsNotApplied = Some(Seq(SelfEmploymentClaimNotApplied(
                  claimId = "id",
                  taxYearClaimMade = "this year",
                  claimType = TypeOfClaim.`carry-forward`,
                  incomeSourceId = "id"
                )))
              )),
              bsas = Some(SelfEmploymentBsas(
                bsasId = "id",
                applied = true,
                incomeSourceId = "id"
              ))
            ))),
            ukPropertyFhl = Some(UkPropertyFhl(
              totalIncome = Some(100.25),
              totalExpenses = Some(100.25),
              netProfit = Some(100.25),
              netLoss = Some(100.25),
              totalAdditions = Some(100.25),
              totalDeductions = Some(100.25),
              adjustedIncomeTaxLoss = Some(100),
              taxableProfit = Some(100),
              taxableProfitAfterIncomeTaxLossesDeduction = Some(100),
              lossClaimsSummary = Some(UkPropertyFhlLossClaimsSummary(
                lossForCSFHL = Some(100),
                totalBroughtForwardIncomeTaxLosses = Some(100),
                broughtForwardIncomeTaxLossesUsed = Some(100),
                totalIncomeTaxLossesCarriedForward = Some(100)
              )),
              lossClaimsDetail = Some(UkPropertyFhlLossClaimsDetail(
                lossesBroughtForward = Some(Seq(UkPropertyFhlLossBroughtForward(
                  taxYearLossIncurred = "this year",
                  currentLossValue = 100,
                  mtdLoss = true
                ))),
                resultOfClaimsApplied = Some(Seq(UkPropertyFhlResultOfClaimApplied(
                  claimId = Some("id"),
                  taxYearClaimMade = "this year",
                  claimType = TypeOfClaim.`carry-forward`,
                  mtdLoss = true,
                  taxYearLossIncurred = "this year",
                  lossAmountUsed = 100,
                  remainingLossValue = 100
                ))),
                defaultCarriedForwardLosses = Some(Seq(UkPropertyFhlDefaultCarriedForwardLoss(taxYearLossIncurred = "this year", currentLossValue = 100)))
              )),
              bsas = Some(PropertyBsas(bsasId = "id", applied = true))
            )),
            ukPropertyNonFhl = Some(UkPropertyNonFhl(
              totalIncome = Some(100.25),
              totalExpenses = Some(100.25),
              netProfit = Some(100.25),
              netLoss = Some(100.25),
              totalAdditions = Some(100.25),
              totalDeductions = Some(100.25),
              accountingAdjustments = Some(100.25),
              adjustedIncomeTaxLoss = Some(100),
              taxableProfit = Some(100),
              taxableProfitAfterIncomeTaxLossesDeduction = Some(100),
              lossClaimsSummary = Some(UkPropertyNonFhlLossClaimsSummary(
                totalBroughtForwardIncomeTaxLosses = Some(100),
                broughtForwardIncomeTaxLossesUsed = Some(100),
                carrySidewaysIncomeTaxLossesUsed = Some(100),
                totalIncomeTaxLossesCarriedForward = Some(100),
                broughtForwardCarrySidewaysIncomeTaxLossesUsed = Some(100)
              )),
              lossClaimsDetail = Some(UkPropertyNonFhlLossClaimsDetail(
                lossesBroughtForward = Some(Seq(UkPropertyNonFhlLossBroughtForward(
                  taxYearLossIncurred = "this year",
                  currentLossValue = 100,
                  mtdLoss = true
                ))),
                resultOfClaimsApplied = Some(Seq(UkPropertyNonFhlResultOfClaimApplied(
                  claimId = Some("id"),
                  originatingClaimId = Some("id"),
                  taxYearClaimMade = "this year",
                  claimType = TypeOfClaim.`carry-forward`,
                  mtdLoss = true,
                  taxYearLossIncurred = "this year",
                  lossAmountUsed = 100,
                  remainingLossValue = 100
                ))),
                defaultCarriedForwardLosses = Some(Seq(UkPropertyNonFhlDefaultCarriedForwardLoss(taxYearLossIncurred = "this year", currentLossValue = 100))),
                claimsNotApplied = Some(Seq(UkPropertyNonFhlClaimNotApplied(claimId = "id", taxYearClaimMade = "this year", claimType = TypeOfClaim.`carry-forward`)))
              )),
              bsas = Some(PropertyBsas(bsasId = "id", applied = true))
            )),
            eeaPropertyFhl = Some(EeaPropertyFhl(
              totalIncome = Some(100.25),
              totalExpenses = Some(100.25),
              netProfit = Some(100.25),
              netLoss = Some(100.25),
              totalAdditions = Some(100.25),
              totalDeductions = Some(100.25),
              adjustedIncomeTaxLoss = Some(100),
              taxableProfit = Some(100),
              taxableProfitAfterIncomeTaxLossesDeduction = Some(100),
              lossClaimsSummary = Some(EeaPropertyFhlLossClaimsSummary(
                lossForCSFHL = Some(100),
                totalBroughtForwardIncomeTaxLosses = Some(100),
                broughtForwardIncomeTaxLossesUsed = Some(100),
                totalIncomeTaxLossesCarriedForward = Some(100)
              )),
              lossClaimsDetail = Some(EeaPropertyFhlLossClaimsDetail(
                lossesBroughtForward = Some(Seq(EeaPropertyFhlLossBroughtForward(taxYearLossIncurred = "this year", currentLossValue = 100, mtdLoss = true))),
                resultOfClaimsApplied = Some(Seq(EeaPropertyFhlResultOfClaimApplied(
                  claimId = Some("id"),
                  taxYearClaimMade = "this year",
                  claimType = TypeOfClaim.`carry-forward`,
                  mtdLoss = true,
                  taxYearLossIncurred = "this year",
                  lossAmountUsed = 100,
                  remainingLossValue = 100
                ))),
                defaultCarriedForwardLosses = Some(Seq(EeaPropertyFhlDefaultCarriedForwardLoss(taxYearLossIncurred = "this year", currentLossValue = 100)))
              )),
              bsas = Some(PropertyBsas(bsasId = "id", applied = true))
            )),
            foreignProperty = Some(ForeignProperty(
              totalIncome = Some(100.25),
              totalExpenses = Some(100.25),
              netProfit = Some(100.25),
              netLoss = Some(100.25),
              totalAdditions = Some(100.25),
              totalDeductions = Some(100.25),
              accountingAdjustments = Some(100.25),
              adjustedIncomeTaxLoss = Some(100),
              taxableProfit = Some(100),
              taxableProfitAfterIncomeTaxLossesDeduction = Some(100),
              lossClaimsSummary = Some(ForeignPropertyLossClaimsSummary(
                totalBroughtForwardIncomeTaxLosses = Some(100),
                broughtForwardIncomeTaxLossesUsed = Some(100),
                carrySidewaysIncomeTaxLossesUsed = Some(100),
                totalIncomeTaxLossesCarriedForward = Some(100),
                broughtForwardCarrySidewaysIncomeTaxLossesUsed = Some(100)
              )),
              lossClaimsDetail = Some(ForeignPropertyLossClaimsDetail(
                lossesBroughtForward = Some(Seq(ForeignPropertyLossBroughtForward(taxYearLossIncurred = "this year", currentLossValue = 100, mtdLoss = true))),
                resultOfClaimsApplied = Some(Seq(ForeignPropertyResultOfClaimApplied(
                  claimId = Some("id"),
                  originatingClaimId = Some("id"),
                  taxYearClaimMade = "this year",
                  claimType = TypeOfClaim.`carry-forward`,
                  mtdLoss = true,
                  taxYearLossIncurred = "this year",
                  lossAmountUsed = 100,
                  remainingLossValue = 100
                ))),
                defaultCarriedForwardLosses = Some(Seq(ForeignPropertyDefaultCarriedForwardLoss(taxYearLossIncurred = "this year", currentLossValue = 100))),
                claimsNotApplied = Some(Seq(ForeignPropertyClaimNotApplied(claimId = "id", taxYearClaimMade = "this year", claimType = TypeOfClaim.`carry-forward`)))
              )),
              bsas = Some(PropertyBsas(bsasId = "id", applied = true))
            ))
          ))
        )),
        savingsAndGains = Some(SavingsAndGains(
          incomeReceived = 100,
          taxableIncome = 100,
          ukSavings = Some(Seq(UkSaving(
            savingsAccountId = Some("id"),
            savingsAccountName = Some("name"),
            grossIncome = 100.25,
            netIncome = Some(100.25),
            taxDeducted = Some(100.25)
          ))),
          ukSecurities = Some(Seq(UkSecurity(
            ukSecuritiesAccountId = Some("id"),
            ukSecuritiesAccountName = Some("name"),
            grossIncome = 100.25,
            netIncome = Some(100.25),
            taxDeducted = Some(100.25)
          )))
        )),
        dividends = Some(Dividends(incomeReceived = 100, taxableIncome = 100)),
        lumpSums = Some(LumpSums(incomeReceived = 100, taxableIncome = 100)),
        gainsOnLifePolicies = Some(GainsOnLifePolicies(incomeReceived = 100, taxableIncome = 100))
      )
    )
  }

  val eoyEstimate: EoyEstimate = {
    import v1.models.response.getCalculation.endOfYearEstimate.detail._
    import v1.models.response.getCalculation.endOfYearEstimate.summary._

    EoyEstimate(
      summary = EoyEstimateSummary(
        totalEstimatedIncome = Some(100),
        totalTaxableIncome = Some(100),
        incomeTaxAmount = Some(100.25),
        nic2 = Some(100.25),
        nic4 = Some(100.25),
        totalNicAmount = Some(100.25),
        totalStudentLoansRepaymentAmount = Some(100.25),
        totalAnnualPaymentsTaxCharged = Some(100.25),
        totalRoyaltyPaymentsTaxCharged = Some(100.25),
        totalTaxDeducted = Some(100.25),
        incomeTaxNicAmount = Some(100.25)
      ),
      detail = EoyEstimateDetail(
        selfEmployments = Some(Seq(EoyEstimateSelfEmployment(
          selfEmploymentId = "id",
          taxableIncome = 100,
          finalised = Some(true)
        ))),
        ukPropertyFhl = Some(EoyEstimateUkPropertyFhl(taxableIncome = 100, finalised = Some(true))),
        ukPropertyNonFhl = Some(EoyEstimateUkPropertyNonFhl(taxableIncome = 100, finalised = Some(true))),
        ukSavings = Some(Seq(EoyEstimateUkSaving(savingsAccountId = "id", savingsAccountName = Some("name"), taxableIncome = 100))),
        ukDividends = Some(EoyEstimateUkDividends(taxableIncome = 100)),
        otherDividends = Some(EoyEstimateOtherDividends(taxableIncome = 100)),
        stateBenefits = Some(EoyEstimateStateBenefits(taxableIncome = 100)),
        ukSecurities = Some(EoyEstimateUkSecurities(taxableIncome = 100)),
        foreignProperty = Some(EoyEstimateForeignProperty(taxableIncome = 100, finalised = Some(true))),
        foreignInterest = Some(EoyEstimateForeignInterest(taxableIncome = 100))
      )
    )
  }

  val allowancesDeductionsAndReliefs: AllowancesDeductionsAndReliefs = {
    import v1.models.response.getCalculation.allowancesAndDeductions.detail._
    import v1.models.response.getCalculation.allowancesAndDeductions.summary._

    AllowancesDeductionsAndReliefs(
      summary = CalculationSummary(totalAllowancesAndDeductions = Some(100), totalReliefs = Some(100.25)),
      detail = CalculationDetail(
        allowancesAndDeductions = Some(AllowancesAndDeductions(
          personalAllowance = Some(100),
          reducedPersonalAllowance = Some(100),
          giftOfInvestmentsAndPropertyToCharity = Some(100),
          blindPersonsAllowance = Some(100),
          lossesAppliedToGeneralIncome = Some(100),
          qualifyingLoanInterestFromInvestments = Some(100.25),
          postCessationTradeReceipts = Some(100.25),
          paymentsToTradeUnionsForDeathBenefits = Some(100.25),
          annualPayments = Some(AnnualPayments(grossAnnualPayments = Some(100.25), reliefClaimed = Some(100.25), rate = Some(100.25))),
          pensionContributions = Some(PensionContributions(
            totalPensionContributions = Some(100.25),
            retirementAnnuityPayments = Some(100.25),
            paymentToEmployersSchemeNoTaxRelief = Some(100.25),
            overseasPensionSchemeContributions = Some(100.25)
          ))
        )),
        reliefs = Some(Reliefs(
          residentialFinanceCosts = Some(ResidentialFinanceCosts(
            amountClaimed = 100.25,
            allowableAmount = Some(100.25),
            rate = 100.25,
            propertyFinanceRelief = 100.25
          )),
          foreignTaxCreditRelief = Some(Seq(ForeignTaxCreditRelief(
            incomeSourceType = IncomeSourceType.dividendsFromForeignCompanies,
            incomeSourceId = Some("id"),
            countryCode = "GB",
            allowableAmount = Some(100.25),
            rate = Some(100.25),
            amountUsed = Some(100.25)
          ))),
          pensionContributionReliefs = Some(PensionContributionReliefs(
            totalPensionContributionReliefs = 100.25,
            regularPensionContributions = Some(100.25),
            oneOffPensionContributionsPaid = Some(100.25)
          )),
          reliefsClaimed = Some(Seq(ReliefsClaimed(
            `type` = "this type",
            amountClaimed = Some(100.25),
            allowableAmount = Some(100.25),
            amountUsed = Some(100.25),
            rate = Some(100.25)
          )))
        ))
      )
    )
  }

  val calculationResponseAllParts = GetCalculationResponse(metadata, Some(incomeTaxAndNicsCalculated), Some(messages), Some(taxableIncome), Some(eoyEstimate), Some(allowancesDeductionsAndReliefs))

}