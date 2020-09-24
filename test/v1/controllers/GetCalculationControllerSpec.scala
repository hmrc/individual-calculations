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

package v1.controllers

import play.api.libs.json.Json
import play.api.mvc.Result
import uk.gov.hmrc.domain.Nino
import uk.gov.hmrc.http.HeaderCarrier
import v1.fixtures.getCalculation.GetCalculationResponseFixtures
import v1.mocks.requestParsers.MockGetCalculationParser
import v1.mocks.services.{MockEnrolmentsAuthService, MockGetCalculationService, MockMtdIdLookupService}
import v1.models.errors._
import v1.models.outcomes.ResponseWrapper
import v1.models.request.getCalculation.{GetCalculationRawData, GetCalculationRequest}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class GetCalculationControllerSpec
    extends ControllerBaseSpec
    with MockEnrolmentsAuthService
    with MockMtdIdLookupService
    with MockGetCalculationParser
    with MockGetCalculationService {

  trait Test {
    val hc = HeaderCarrier()

    val controller = new GetCalculationController(
      authService = mockEnrolmentsAuthService,
      lookupService = mockMtdIdLookupService,
      getCalculationParser = mockGetCalculationParser,
      getCalculationService = mockGetCalculationService,
      cc = cc
    )

    MockedMtdIdLookupService.lookup(nino).returns(Future.successful(Right("test-mtd-id")))
    MockedEnrolmentsAuthService.authoriseUser()
  }

  private val nino          = "AA123456A"
  private val calcId        = "f2fb30e5-4ab6-4a29-b3c1-c7264259ff1c"
  private val correlationId = "X-123"
  private val query =
    """
      |{
      |  metadata {
      |    id
      |    taxYear
      |    requestedBy
      |    requestedTimestamp
      |    calculationReason
      |    calculationTimestamp
      |    calculationType
      |    intentToCrystallise
      |    crystallised
      |    totalIncomeTaxAndNicsDue
      |    calculationErrorCount
      |  }
      |  incomeTaxAndNicsCalculated {
      |    summary {
      |      incomeTax {
      |        incomeTaxCharged
      |        incomeTaxDueAfterReliefs
      |        incomeTaxDueAfterGiftAid
      |      }
      |      nics {
      |        class2NicsAmount
      |        class4NicsAmount
      |        totalNic
      |      }
      |      totalIncomeTaxNicsCharged
      |      totalTaxDeducted
      |      totalIncomeTaxAndNicsDue
      |      taxRegime
      |    }
      |    detail {
      |      incomeTax {
      |        payPensionsProfit {
      |          allowancesAllocated
      |          incomeTaxAmount
      |          taxBands {
      |            name
      |            rate
      |            bandLimit
      |            apportionedBandLimit
      |            income
      |            taxAmount
      |          }
      |        }
      |        savingsAndGains {
      |          allowancesAllocated
      |          incomeTaxAmount
      |          taxBands {
      |            name
      |            rate
      |            bandLimit
      |            apportionedBandLimit
      |            income
      |            taxAmount
      |          }
      |        }
      |        dividends {
      |          allowancesAllocated
      |          incomeTaxAmount
      |          taxBands {
      |            name
      |            rate
      |            bandLimit
      |            apportionedBandLimit
      |            income
      |            taxAmount
      |          }
      |        }
      |        giftAid {
      |          grossGiftAidPayments
      |          rate
      |          giftAidTax
      |        }
      |      }
      |      nics {
      |        class2Nics {
      |          weeklyRate
      |          weeks
      |          limit
      |          apportionedLimit
      |          underSmallProfitThreshold
      |          actualClass2Nic
      |        }
      |        class4Nics {
      |          class4Losses {
      |            totalClass4LossesAvailable
      |            totalClass4LossesUsed
      |            totalClass4LossesCarriedForward
      |          }
      |          totalIncomeLiableToClass4Charge
      |          totalIncomeChargeableToClass4
      |          class4NicBands {
      |            name
      |            rate
      |            threshold
      |            apportionedThreshold
      |            income
      |            amount
      |          }
      |        }
      |      }
      |      taxDeductedAtSource {
      |        ukLandAndProperty
      |        savings
      |        cis
      |      }
      |    }
      |  }
      |  messages {
      |    info {
      |      id
      |      text
      |    }
      |    warnings {
      |      id
      |      text
      |    }
      |    errors {
      |      id
      |      text
      |    }
      |  }
      |  taxableIncome {
      |    summary {
      |      totalIncomeReceivedFromAllSources
      |      totalTaxableIncome
      |    }
      |    detail {
      |      payPensionsProfit {
      |        incomeReceived
      |        taxableIncome
      |        totalSelfEmploymentProfit
      |        totalPropertyProfit
      |        totalFHLPropertyProfit
      |        totalUKOtherPropertyProfit
      |        businessProfitAndLoss {
      |          selfEmployments {
      |            selfEmploymentId
      |            totalIncome
      |            totalExpenses
      |            netProfit
      |            netLoss
      |            class4Loss
      |            totalAdditions
      |            totalDeductions
      |            accountingAdjustments
      |            adjustedIncomeTaxLoss
      |            taxableProfit
      |            taxableProfitAfterIncomeTaxLossesDeduction
      |            lossClaimsSummary {
      |              totalBroughtForwardIncomeTaxLosses
      |              broughtForwardIncomeTaxLossesUsed
      |              carrySidewaysIncomeTaxLossesUsed
      |              totalIncomeTaxLossesCarriedForward
      |              totalBroughtForwardClass4Losses
      |              broughtForwardClass4LossesUsed
      |              carrySidewaysClass4LossesUsed
      |              totalClass4LossesCarriedForward
      |            }
      |            lossClaimsDetail {
      |              lossesBroughtForward {
      |                lossType
      |                taxYearLossIncurred
      |                currentLossValue
      |                mtdLoss
      |                incomeSourceId
      |              }
      |              resultOfClaimsApplied {
      |                claimId
      |                taxYearClaimMade
      |                claimType
      |                mtdLoss
      |                taxYearLossIncurred
      |                lossAmountUsed
      |                remainingLossValue
      |                lossType
      |                incomeSourceId
      |              }
      |              unclaimedLosses {
      |                taxYearLossIncurred
      |                currentLossValue
      |                lossType
      |                incomeSourceId
      |              }
      |              carriedForwardLosses {
      |                claimId
      |                claimType
      |                taxYearClaimMade
      |                taxYearLossIncurred
      |                currentLossValue
      |                lossType
      |                incomeSourceId
      |              }
      |              claimsNotApplied {
      |                claimId
      |                taxYearClaimMade
      |                claimType
      |                incomeSourceId
      |              }
      |            }
      |            bsas {
      |              bsasId
      |              applied
      |              incomeSourceId
      |            }
      |          }
      |          ukPropertyFhl {
      |            totalIncome
      |            totalExpenses
      |            netProfit
      |            netLoss
      |            totalAdditions
      |            totalDeductions
      |            accountingAdjustments
      |            adjustedIncomeTaxLoss
      |            taxableProfit
      |            taxableProfitAfterIncomeTaxLossesDeduction
      |            lossClaimsSummary {
      |              lossForCSFHL
      |              totalBroughtForwardIncomeTaxLosses
      |              broughtForwardIncomeTaxLossesUsed
      |              totalIncomeTaxLossesCarriedForward
      |            }
      |            lossClaimsDetail {
      |              lossesBroughtForward {
      |                taxYearLossIncurred
      |                currentLossValue
      |                mtdLoss
      |              }
      |              resultOfClaimsApplied {
      |                claimId
      |                taxYearClaimMade
      |                claimType
      |                mtdLoss
      |                taxYearLossIncurred
      |                lossAmountUsed
      |                remainingLossValue
      |              }
      |              defaultCarriedForwardLosses {
      |                taxYearLossIncurred
      |                currentLossValue
      |              }
      |            }
      |            bsas {
      |              bsasId
      |              applied
      |            }
      |          }
      |          ukPropertyNonFhl {
      |            totalIncome
      |            totalExpenses
      |            netProfit
      |            netLoss
      |            totalAdditions
      |            totalDeductions
      |            accountingAdjustments
      |            adjustedIncomeTaxLoss
      |            taxableProfit
      |            taxableProfitAfterIncomeTaxLossesDeduction
      |            lossClaimsSummary {
      |              totalBroughtForwardIncomeTaxLosses
      |              broughtForwardIncomeTaxLossesUsed
      |              carrySidewaysIncomeTaxLossesUsed
      |              totalIncomeTaxLossesCarriedForward
      |              broughtForwardCarrySidewaysIncomeTaxLossesUsed
      |            }
      |            lossClaimsDetail {
      |              lossesBroughtForward {
      |                taxYearLossIncurred
      |                currentLossValue
      |                mtdLoss
      |              }
      |              resultOfClaimsApplied {
      |                claimId
      |                originatingClaimId
      |                taxYearClaimMade
      |                claimType
      |                mtdLoss
      |                taxYearLossIncurred
      |                lossAmountUsed
      |                remainingLossValue
      |              }
      |              defaultCarriedForwardLosses {
      |                taxYearLossIncurred
      |                currentLossValue
      |              }
      |              claimsNotApplied {
      |                claimId
      |                taxYearClaimMade
      |                claimType
      |              }
      |            }
      |            bsas {
      |              bsasId
      |              applied
      |            }
      |          }
      |        }
      |      }
      |      savingsAndGains {
      |        incomeReceived
      |        taxableIncome
      |        ukSavings {
      |          savingsAccountId
      |          savingsAccountName
      |          grossIncome
      |          netIncome
      |          taxDeducted
      |        }
      |      }
      |      dividends {
      |        incomeReceived
      |        taxableIncome
      |      }
      |    }
      |  }
      |  endOfYearEstimate {
      |    summary {
      |      totalEstimatedIncome
      |      totalTaxableIncome
      |      incomeTaxAmount
      |      nic2
      |      nic4
      |      totalNicAmount
      |      totalStudentLoansRepaymentAmount
      |      totalAnnualPaymentsTaxCharged
      |      totalRoyaltyPaymentsTaxCharged
      |      totalTaxDeducted
      |      incomeTaxNicAmount
      |    }
      |    detail {
      |      selfEmployments {
      |        selfEmploymentId
      |        taxableIncome
      |        finalised
      |      }
      |      ukPropertyFhl {
      |        taxableIncome
      |        finalised
      |      }
      |      ukPropertyNonFhl {
      |        taxableIncome
      |        finalised
      |      }
      |      ukSavings {
      |        savingsAccountId
      |        savingsAccountName
      |        taxableIncome
      |      }
      |      ukDividends {
      |        taxableIncome
      |      }
      |      otherDividends {
      |        taxableIncome
      |      }
      |      stateBenefits {
      |        taxableIncome
      |      }
      |      ukSecurities {
      |        taxableIncome
      |      }
      |      foreignProperty {
      |        taxableIncome
      |        finalised
      |      }
      |      foreignInterest {
      |        taxableIncome
      |      }
      |    }
      |  }
      |  allowancesDeductionsAndReliefs {
      |    summary {
      |      totalAllowancesAndDeductions
      |      totalReliefs
      |    }
      |    detail {
      |      allowancesAndDeductions {
      |        personalAllowance
      |        reducedPersonalAllowance
      |        giftOfInvestmentsAndPropertyToCharity
      |        blindPersonsAllowance
      |        lossesAppliedToGeneralIncome
      |        qualifyingLoanInterestFromInvestments
      |        postCessationTradeReceipts
      |        paymentsToTradeUnionsForDeathBenefits
      |        annualPayments {
      |          grossAnnualPayments
      |          reliefClaimed
      |          rate
      |        }
      |        pensionContributions {
      |          totalPensionContributions
      |          retirementAnnuityPayments
      |          paymentToEmployersSchemeNoTaxRelief
      |          overseasPensionSchemeContributions
      |        }
      |      }
      |      reliefs {
      |        residentialFinanceCosts {
      |          amountClaimed
      |          allowableAmount
      |          rate
      |          propertyFinanceRelief
      |        }
      |        foreignTaxCreditRelief {
      |          incomeSourceType
      |          incomeSourceId
      |          countryCode
      |          allowableAmount
      |          rate
      |          amountUsed
      |        }
      |        pensionContributionReliefs {
      |          totalPensionContributionReliefs
      |          regularPensionContributions
      |          oneOffPensionContributionsPaid
      |        }
      |        reliefsClaimed {
      |          type
      |          amountClaimed
      |          allowableAmount
      |          amountUsed
      |          rate
      |        }
      |      }
      |    }
      |  }
      |}
      |""".stripMargin

  val rawData                = GetCalculationRawData(nino, calcId)
  val requestData            = GetCalculationRequest(Nino(nino), calcId)

  "handleRequest" should {
    "return OK with all fields returned" when {
      "happy path" in new Test {

        MockGetCalculationParser
          .parse(rawData)
          .returns(Right(requestData))

        MockGetCalculationService
          .getCalculation(requestData)
          .returns(Future.successful(Right(ResponseWrapper(correlationId, GetCalculationResponseFixtures.calculationResponseAllParts))))

        val result: Future[Result] = controller.getCalculation(nino, calcId, query)(fakeGetRequest)

        status(result) shouldBe OK
        contentAsJson(result) shouldBe Json.obj("data" -> GetCalculationResponseFixtures.writtenJson)
        header("X-CorrelationId", result) shouldBe Some(correlationId)
      }
    }

    "return the error as per spec" when {
      "parser errors occur" must {
        def errorsFromParserTester(error: MtdError, expectedStatus: Int): Unit = {
          s"a ${error.code} error is returned from the parser" in new Test {

            MockGetCalculationParser
              .parse(rawData)
              .returns(Left(ErrorWrapper(Some(correlationId), error, None)))

            val result: Future[Result] = controller.getCalculation(nino, calcId, query)(fakeGetRequest)

            status(result) shouldBe expectedStatus
            contentAsJson(result) shouldBe Json.toJson(error)
            header("X-CorrelationId", result) shouldBe Some(correlationId)
          }
        }

        val input = Seq(
          (BadRequestError, BAD_REQUEST),
          (NinoFormatError, BAD_REQUEST),
          (CalculationIdFormatError, BAD_REQUEST),
          (NotFoundError, NOT_FOUND),
          (DownstreamError, INTERNAL_SERVER_ERROR)
        )

        input.foreach(args => (errorsFromParserTester _).tupled(args))
      }

      "service errors occur" must {
        def serviceErrors(mtdError: MtdError, expectedStatus: Int): Unit = {
          s"a $mtdError error is returned from the service" in new Test {

            MockGetCalculationParser
              .parse(rawData)
              .returns(Right(requestData))

            MockGetCalculationService
              .getCalculation(requestData)
              .returns(Future.successful(Left(ErrorWrapper(Some(correlationId), mtdError))))

            val result: Future[Result] = controller.getCalculation(nino, calcId, query)(fakeGetRequest)

            status(result) shouldBe expectedStatus
            contentAsJson(result) shouldBe Json.toJson(mtdError)
            header("X-CorrelationId", result) shouldBe Some(correlationId)
          }
        }

        val input = Seq(
          (NinoFormatError, BAD_REQUEST),
          (CalculationIdFormatError, BAD_REQUEST),
          (NotFoundError, NOT_FOUND),
          (DownstreamError, INTERNAL_SERVER_ERROR)
        )

        input.foreach(args => (serviceErrors _).tupled(args))
      }
    }

  }
}
