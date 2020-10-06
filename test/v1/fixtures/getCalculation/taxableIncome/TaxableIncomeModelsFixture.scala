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

package v1.fixtures.getCalculation.taxableIncome

import v1.models.des.LossType
import v1.models.domain.TypeOfClaim
import v1.models.response.getCalculation.taxableIncome.detail._
import v1.models.response.getCalculation.taxableIncome.detail.selfEmployment.detail._
import v1.models.response.getCalculation.taxableIncome.detail.selfEmployment.{SelfEmployment, SelfEmploymentBsas, SelfEmploymentLossClaimsDetail, SelfEmploymentLossClaimsSummary}
import v1.models.response.getCalculation.taxableIncome.detail.ukPropertyFhl.detail.{UkPropertyFhlDefaultCarriedForwardLoss, UkPropertyFhlLossBroughtForward, UkPropertyFhlResultOfClaimApplied}
import v1.models.response.getCalculation.taxableIncome.detail.ukPropertyFhl.{UkPropertyFhl, UkPropertyFhlLossClaimsDetail, UkPropertyFhlLossClaimsSummary}
import v1.models.response.getCalculation.taxableIncome.detail.ukPropertyNonFhl.detail.{UkPropertyNonFhlClaimNotApplied, UkPropertyNonFhlDefaultCarriedForwardLoss, UkPropertyNonFhlLossBroughtForward, UkPropertyNonFhlResultOfClaimApplied}
import v1.models.response.getCalculation.taxableIncome.detail.ukPropertyNonFhl.{UkPropertyNonFhl, UkPropertyNonFhlLossClaimsDetail, UkPropertyNonFhlLossClaimsSummary}
import v1.models.response.getCalculation.taxableIncome.{TaxableIncome, TaxableIncomeDetail, TaxableIncomeSummary}

object TaxableIncomeModelsFixture {
  val taxableIncomeSummaryModel: TaxableIncomeSummary = TaxableIncomeSummary(
    totalIncomeReceivedFromAllSources = 7001,
    totalTaxableIncome = 100
  )

  val ukSavingModel1: UkSaving = UkSaving(
    savingsAccountId = "SAVKB1UVwUTBQGJ",
    savingsAccountName = "UK Savings Account ONE",
    grossIncome = 90101.11,
    netIncome = Some(90201.11),
    taxDeducted = Some(90301.11)
  )

  val ukSavingModel2: UkSaving = UkSaving(
    savingsAccountId = "SAVKB2UVwUTBQGJ",
    savingsAccountName = "UK Savings Account TWO",
    grossIncome = 90102.11,
    netIncome = Some(90202.11),
    taxDeducted = Some(90302.11)
  )

  val dividendsModel: Dividends = Dividends(
    incomeReceived = 7020,
    taxableIncome = 7022
  )

  val savingsAndGainsModel: SavingsAndGains = SavingsAndGains(
    incomeReceived = 7012,
    taxableIncome = 7014,
    ukSavings = Some(
      List(
        ukSavingModel1,
        ukSavingModel2
      ))
  )

  val selfEmploymentSummaryModel1: SelfEmploymentLossClaimsSummary = SelfEmploymentLossClaimsSummary(
    totalBroughtForwardIncomeTaxLosses = Some(101001),
    broughtForwardIncomeTaxLossesUsed = Some(101201),
    carrySidewaysIncomeTaxLossesUsed = Some(101401),
    totalIncomeTaxLossesCarriedForward = Some(101601),
    totalBroughtForwardClass4Losses = Some(101701),
    broughtForwardClass4LossesUsed = Some(101801),
    carrySidewaysClass4LossesUsed = Some(101901),
    totalClass4LossesCarriedForward = Some(101119)
  )

  val selfEmploymentLossBroughtForwardModel1: SelfEmploymentLossBroughtForward = SelfEmploymentLossBroughtForward(
    lossType = LossType.INCOME,
    taxYearLossIncurred = "2017-18",
    currentLossValue = 10101,
    mtdLoss = true,
    incomeSourceId = "AaIS12345678910"
  )

  val selfEmploymentResultOfClaimAppliedModel1: SelfEmploymentResultOfClaimApplied = SelfEmploymentResultOfClaimApplied(
    claimId = Some("CCIS12345678901"),
    taxYearClaimMade = "2017-18",
    claimType = TypeOfClaim.`carry-forward`,
    mtdLoss = true,
    taxYearLossIncurred = "2017-18",
    lossAmountUsed = 10101,
    remainingLossValue = 10201,
    lossType = LossType.INCOME,
    incomeSourceId = "AaIS12345678910"
  )

  val selfEmploymentUnclaimedLossModel1: SelfEmploymentUnclaimedLoss = SelfEmploymentUnclaimedLoss(
    taxYearLossIncurred = "2017-18",
    currentLossValue = 1001,
    lossType = LossType.INCOME,
    incomeSourceId = "AaIS12345678910"
  )

  val selfEmploymentCarriedForwardLossModel1: SelfEmploymentCarriedForwardLoss = SelfEmploymentCarriedForwardLoss(
    claimId = Some("CCIS12345678901"),
    claimType = TypeOfClaim.`carry-forward`,
    taxYearClaimMade = Some("2018-19"),
    taxYearLossIncurred = "2017-18",
    currentLossValue = 1001,
    lossType = LossType.INCOME,
    incomeSourceId = "AaIS12345678910"
  )

  val selfEmploymentClaimNotAppliedModel1: SelfEmploymentClaimNotApplied = SelfEmploymentClaimNotApplied(
    claimId = "CCIS12345678921",
    taxYearClaimMade = "2017-18",
    claimType = TypeOfClaim.`carry-forward`,
    incomeSourceId = "AaIS12345678910"
  )

  val selfEmploymentDetailModel1: SelfEmploymentLossClaimsDetail = SelfEmploymentLossClaimsDetail(
    lossesBroughtForward = Some(List(selfEmploymentLossBroughtForwardModel1)),
    resultOfClaimsApplied = Some(List(selfEmploymentResultOfClaimAppliedModel1)),
    unclaimedLosses = Some(List(selfEmploymentUnclaimedLossModel1)),
    carriedForwardLosses = Some(List(selfEmploymentCarriedForwardLossModel1)),
    claimsNotApplied = Some(List(selfEmploymentClaimNotAppliedModel1))
  )

  val selfEmploymentBsasModel1: SelfEmploymentBsas = SelfEmploymentBsas(
    bsasId = "10000001",
    applied = true,
    incomeSourceId = "AaIS12345678910"
  )

  val selfEmploymentModel1: SelfEmployment = SelfEmployment(
    selfEmploymentId = "AaIS12345678910",
    totalIncome = Some(100101.11),
    totalExpenses = Some(100201.11),
    netProfit = Some(100301.11),
    netLoss = Some(100401.11),
    class4Loss = Some(101501),
    totalAdditions = Some(100501.11),
    totalDeductions = Some(100601.11),
    accountingAdjustments = Some(100701.11),
    adjustedIncomeTaxLoss = Some(100901),
    taxableProfit = Some(100801),
    taxableProfitAfterIncomeTaxLossesDeduction = Some(101301),
    lossClaimsSummary = Some(selfEmploymentSummaryModel1),
    lossClaimsDetail = Some(selfEmploymentDetailModel1),
    bsas = Some(selfEmploymentBsasModel1)
  )

  val selfEmploymentSummaryModel2: SelfEmploymentLossClaimsSummary = SelfEmploymentLossClaimsSummary(
    totalBroughtForwardIncomeTaxLosses = Some(101002),
    broughtForwardIncomeTaxLossesUsed = Some(101202),
    carrySidewaysIncomeTaxLossesUsed = Some(101402),
    totalIncomeTaxLossesCarriedForward = Some(101602),
    totalBroughtForwardClass4Losses = Some(101702),
    broughtForwardClass4LossesUsed = Some(101802),
    carrySidewaysClass4LossesUsed = Some(101902),
    totalClass4LossesCarriedForward = Some(101392)
  )

  val selfEmploymentLossBroughtForwardModel2: SelfEmploymentLossBroughtForward = SelfEmploymentLossBroughtForward(
    lossType = LossType.INCOME,
    taxYearLossIncurred = "2017-18",
    currentLossValue = 10102,
    mtdLoss = true,
    incomeSourceId = "AbIS12345678910"
  )

  val selfEmploymentResultOfClaimAppliedModel2: SelfEmploymentResultOfClaimApplied = SelfEmploymentResultOfClaimApplied(
    claimId = Some("CCIS12345678902"),
    taxYearClaimMade = "2017-18",
    claimType = TypeOfClaim.`carry-sideways`,
    mtdLoss = true,
    taxYearLossIncurred = "2017-18",
    lossAmountUsed = 10102,
    remainingLossValue = 10202,
    lossType = LossType.INCOME,
    incomeSourceId = "AbIS12345678910"
  )

  val selfEmploymentUnclaimedLossModel2: SelfEmploymentUnclaimedLoss = SelfEmploymentUnclaimedLoss(
    taxYearLossIncurred = "2017-18",
    currentLossValue = 1002,
    lossType = LossType.INCOME,
    incomeSourceId = "AbIS12345678910"
  )

  val selfEmploymentCarriedForwardLossModel2: SelfEmploymentCarriedForwardLoss = SelfEmploymentCarriedForwardLoss(
    claimId = Some("CCIS12345678902"),
    claimType = TypeOfClaim.`carry-forward`,
    taxYearClaimMade = Some("2018-19"),
    taxYearLossIncurred = "2017-18",
    currentLossValue = 1002,
    lossType = LossType.INCOME,
    incomeSourceId = "AbIS12345678910"
  )

  val selfEmploymentClaimNotAppliedModel2: SelfEmploymentClaimNotApplied = SelfEmploymentClaimNotApplied(
    claimId = "CCIS12345678922",
    taxYearClaimMade = "2017-18",
    claimType = TypeOfClaim.`carry-sideways`,
    incomeSourceId = "AbIS12345678910"
  )

  val selfEmploymentDetailModel2: SelfEmploymentLossClaimsDetail = SelfEmploymentLossClaimsDetail(
    lossesBroughtForward = Some(List(selfEmploymentLossBroughtForwardModel2)),
    resultOfClaimsApplied = Some(List(selfEmploymentResultOfClaimAppliedModel2)),
    unclaimedLosses = Some(List(selfEmploymentUnclaimedLossModel2)),
    carriedForwardLosses = Some(List(selfEmploymentCarriedForwardLossModel2)),
    claimsNotApplied = Some(List(selfEmploymentClaimNotAppliedModel2))
  )

  val selfEmploymentBsasModel2: SelfEmploymentBsas = SelfEmploymentBsas(
    bsasId = "10000002",
    applied = true,
    incomeSourceId = "AbIS12345678910"
  )

  val selfEmploymentModel2: SelfEmployment = SelfEmployment(
    selfEmploymentId = "AbIS12345678910",
    totalIncome = Some(100102.22),
    totalExpenses = Some(100202.22),
    netProfit = Some(100302.22),
    netLoss = Some(100402.22),
    class4Loss = Some(101502),
    totalAdditions = Some(100502.22),
    totalDeductions = Some(100602.22),
    accountingAdjustments = Some(100702.22),
    adjustedIncomeTaxLoss = Some(100902),
    taxableProfit = Some(100802),
    taxableProfitAfterIncomeTaxLossesDeduction = Some(101302),
    lossClaimsSummary = Some(selfEmploymentSummaryModel2),
    lossClaimsDetail = Some(selfEmploymentDetailModel2),
    bsas = Some(selfEmploymentBsasModel2)
  )

  val ukPropertyFhlSummaryModel: UkPropertyFhlLossClaimsSummary = UkPropertyFhlLossClaimsSummary(
    lossForCSFHL = Some(4011),
    totalBroughtForwardIncomeTaxLosses = Some(4010),
    broughtForwardIncomeTaxLossesUsed = Some(4012),
    totalIncomeTaxLossesCarriedForward = Some(4014)
  )

  val ukPropertyFhlLossBroughtForwardModel: UkPropertyFhlLossBroughtForward = UkPropertyFhlLossBroughtForward(
    taxYearLossIncurred = "2017-18",
    currentLossValue = 40101,
    mtdLoss = true
  )

  val ukPropertyFhlResultOfClaimAppliedModel: UkPropertyFhlResultOfClaimApplied = UkPropertyFhlResultOfClaimApplied(
    claimId = Some("CCIS12345678904"),
    taxYearClaimMade = "2017-18",
    claimType = TypeOfClaim.`carry-forward-to-carry-sideways`,
    mtdLoss = true,
    taxYearLossIncurred = "2017-18",
    lossAmountUsed = 40101,
    remainingLossValue = 40201
  )

  val ukPropertyFhlDefaultCarriedForwardLossModel: UkPropertyFhlDefaultCarriedForwardLoss = UkPropertyFhlDefaultCarriedForwardLoss(
    taxYearLossIncurred = "2017-18",
    currentLossValue = 401
  )

  val ukPropertyFhlDetailModel: UkPropertyFhlLossClaimsDetail = UkPropertyFhlLossClaimsDetail(
    lossesBroughtForward = Some(List(ukPropertyFhlLossBroughtForwardModel)),
    resultOfClaimsApplied = Some(List(ukPropertyFhlResultOfClaimAppliedModel)),
    defaultCarriedForwardLosses = Some(List(ukPropertyFhlDefaultCarriedForwardLossModel))
  )

  val fhlBsasModel: Bsas = Bsas(
    bsasId = "12345678",
    applied = true
  )

  val ukPropertyFhlModel: UkPropertyFhl = UkPropertyFhl(
    totalIncome = Some(4001.11),
    totalExpenses = Some(4002.11),
    netProfit = Some(4003.11),
    netLoss = Some(4004.11),
    totalAdditions = Some(4005.11),
    totalDeductions = Some(4006.11),
    accountingAdjustments = Some(4007.11),
    adjustedIncomeTaxLoss = Some(4009),
    taxableProfit = Some(4008),
    taxableProfitAfterIncomeTaxLossesDeduction = Some(4013),
    lossClaimsSummary = Some(ukPropertyFhlSummaryModel),
    lossClaimsDetail = Some(ukPropertyFhlDetailModel),
    bsas = Some(fhlBsasModel)
  )

  val ukPropertyNonFhlSummaryModel: UkPropertyNonFhlLossClaimsSummary = UkPropertyNonFhlLossClaimsSummary(
    totalBroughtForwardIncomeTaxLosses = Some(2010),
    broughtForwardIncomeTaxLossesUsed = Some(2012),
    carrySidewaysIncomeTaxLossesUsed = Some(2014),
    totalIncomeTaxLossesCarriedForward = Some(2011),
    broughtForwardCarrySidewaysIncomeTaxLossesUsed = Some(2020)
  )

  val ukPropertyNonFhlLossBroughtForwardModel: UkPropertyNonFhlLossBroughtForward = UkPropertyNonFhlLossBroughtForward(
    taxYearLossIncurred = "2017-18",
    currentLossValue = 20101,
    mtdLoss = true
  )

  val ukPropertyNonFhlResultOfClaimAppliedModel: UkPropertyNonFhlResultOfClaimApplied = UkPropertyNonFhlResultOfClaimApplied(
    claimId = Some("CCIS12345678903"),
    originatingClaimId = Some("000000000000213"),
    taxYearClaimMade = "2017-18",
    claimType = TypeOfClaim.`carry-sideways-fhl`,
    mtdLoss = true,
    taxYearLossIncurred = "2017-18",
    lossAmountUsed = 20101,
    remainingLossValue = 20201
  )

  val ukPropertyNonFhlDefaultCarriedForwardLossModel: UkPropertyNonFhlDefaultCarriedForwardLoss = UkPropertyNonFhlDefaultCarriedForwardLoss(
    taxYearLossIncurred = "2017-18",
    currentLossValue = 201
  )

  val ukPropertyNonFhlClaimNotAppliedModel: UkPropertyNonFhlClaimNotApplied = UkPropertyNonFhlClaimNotApplied(
    claimId = "CCIS12345678923",
    taxYearClaimMade = "2017-18",
    claimType = TypeOfClaim.`carry-sideways-fhl`
  )

  val ukPropertyNonFhlDetailModel: UkPropertyNonFhlLossClaimsDetail = UkPropertyNonFhlLossClaimsDetail(
    lossesBroughtForward = Some(List(ukPropertyNonFhlLossBroughtForwardModel)),
    resultOfClaimsApplied = Some(List(ukPropertyNonFhlResultOfClaimAppliedModel)),
    defaultCarriedForwardLosses = Some(List(ukPropertyNonFhlDefaultCarriedForwardLossModel)),
    claimsNotApplied = Some(List(ukPropertyNonFhlClaimNotAppliedModel))
  )

  val nonFhlBsasModel: Bsas = Bsas(
    bsasId = "10000003",
    applied = false
  )

  val ukPropertyNonFhlModel: UkPropertyNonFhl = UkPropertyNonFhl(
    totalIncome = Some(2001.11),
    totalExpenses = Some(2002.11),
    netProfit = Some(2003.11),
    netLoss = Some(2004.11),
    totalAdditions = Some(2005.11),
    totalDeductions = Some(2006.11),
    accountingAdjustments = Some(2007.11),
    adjustedIncomeTaxLoss = Some(2009),
    taxableProfit = Some(2008),
    taxableProfitAfterIncomeTaxLossesDeduction = Some(2013),
    lossClaimsSummary = Some(ukPropertyNonFhlSummaryModel),
    lossClaimsDetail = Some(ukPropertyNonFhlDetailModel),
    bsas = Some(nonFhlBsasModel)
  )

  val businessProfitAndLossModel: BusinessProfitAndLoss = BusinessProfitAndLoss(
    selfEmployments = Some(
      List(
        selfEmploymentModel1,
        selfEmploymentModel2
      )),
    ukPropertyFhl = Some(ukPropertyFhlModel),
    ukPropertyNonFhl = Some(ukPropertyNonFhlModel)
  )

  val payPensionsProfitModel = PayPensionsProfit(
    incomeReceived = 7004,
    taxableIncome = 7006,
    totalSelfEmploymentProfit = Some(6001),
    totalPropertyProfit = Some(6002),
    totalFHLPropertyProfit = Some(6003),
    totalUKOtherPropertyProfit = Some(6004),
    businessProfitAndLoss = Some(businessProfitAndLossModel)
  )

  val taxableIncomeDetailModel: TaxableIncomeDetail = TaxableIncomeDetail(
    payPensionsProfit = Some(payPensionsProfitModel),
    savingsAndGains = Some(savingsAndGainsModel),
    dividends = Some(dividendsModel)
  )

  val taxableIncomeModel: TaxableIncome = TaxableIncome(
    summary = taxableIncomeSummaryModel,
    detail = taxableIncomeDetailModel
  )
}
