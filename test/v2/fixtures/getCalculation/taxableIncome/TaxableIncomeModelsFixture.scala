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

package v2.fixtures.getCalculation.taxableIncome

import v2.models.des.LossType
import v2.models.domain.TypeOfClaim
import v2.models.response.getCalculation.taxableIncome._
import v2.models.response.getCalculation.taxableIncome.detail._
import v2.models.response.getCalculation.taxableIncome.detail.payPensionsProfit.eeaPropertyFhl._
import v2.models.response.getCalculation.taxableIncome.detail.payPensionsProfit.eeaPropertyFhl.detail._
import v2.models.response.getCalculation.taxableIncome.detail.payPensionsProfit.foreignProperty._
import v2.models.response.getCalculation.taxableIncome.detail.payPensionsProfit.foreignProperty.detail._
import v2.models.response.getCalculation.taxableIncome.detail.payPensionsProfit.selfEmployment._
import v2.models.response.getCalculation.taxableIncome.detail.payPensionsProfit.selfEmployment.detail._
import v2.models.response.getCalculation.taxableIncome.detail.payPensionsProfit.ukPropertyFhl._
import v2.models.response.getCalculation.taxableIncome.detail.payPensionsProfit.ukPropertyFhl.detail._
import v2.models.response.getCalculation.taxableIncome.detail.payPensionsProfit.ukPropertyNonFhl._
import v2.models.response.getCalculation.taxableIncome.detail.payPensionsProfit.ukPropertyNonFhl.detail._
import v2.models.response.getCalculation.taxableIncome.detail.payPensionsProfit.{BusinessProfitAndLoss, PropertyBsas}

object TaxableIncomeModelsFixture {
  
  // Self Employment Model 1:
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

  // Self Employment Model 2:
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
  
  // UK Property FHL model:
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

  val ukPropertyFhlBsasModel: PropertyBsas = PropertyBsas(
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
    adjustedIncomeTaxLoss = Some(4009),
    taxableProfit = Some(4008),
    taxableProfitAfterIncomeTaxLossesDeduction = Some(4013),
    lossClaimsSummary = Some(ukPropertyFhlSummaryModel),
    lossClaimsDetail = Some(ukPropertyFhlDetailModel),
    bsas = Some(ukPropertyFhlBsasModel)
  )
  
  // EEA Property FHL model:
  val eeaPropertyFhlSummaryModel: EeaPropertyFhlLossClaimsSummary = EeaPropertyFhlLossClaimsSummary(
    lossForCSFHL = Some(5011),
    totalBroughtForwardIncomeTaxLosses = Some(5010),
    broughtForwardIncomeTaxLossesUsed = Some(5012),
    totalIncomeTaxLossesCarriedForward = Some(5014)
  )

  val eeaPropertyFhlLossBroughtForwardModel: EeaPropertyFhlLossBroughtForward = EeaPropertyFhlLossBroughtForward(
    taxYearLossIncurred = "2017-18",
    currentLossValue = 50101,
    mtdLoss = false
  )

  val eeaPropertyFhlResultOfClaimAppliedModel: EeaPropertyFhlResultOfClaimApplied = EeaPropertyFhlResultOfClaimApplied(
    claimId = Some("CCIS12345678905"),
    taxYearClaimMade = "2017-18",
    claimType = TypeOfClaim.`carry-forward`,
    mtdLoss = false,
    taxYearLossIncurred = "2017-18",
    lossAmountUsed = 50101,
    remainingLossValue = 50201
  )

  val eeaPropertyFhlDefaultCarriedForwardLossModel: EeaPropertyFhlDefaultCarriedForwardLoss = EeaPropertyFhlDefaultCarriedForwardLoss(
    taxYearLossIncurred = "2017-18",
    currentLossValue = 501
  )

  val eeaPropertyFhlDetailModel: EeaPropertyFhlLossClaimsDetail = EeaPropertyFhlLossClaimsDetail(
    lossesBroughtForward = Some(List(eeaPropertyFhlLossBroughtForwardModel)),
    resultOfClaimsApplied = Some(List(eeaPropertyFhlResultOfClaimAppliedModel)),
    defaultCarriedForwardLosses = Some(List(eeaPropertyFhlDefaultCarriedForwardLossModel))
  )

  val eeaPropertyFhlBsasModel: PropertyBsas = PropertyBsas(
    bsasId = "87654321",
    applied = false
  )

  val eeaPropertyFhlModel: EeaPropertyFhl = EeaPropertyFhl(
    totalIncome = Some(5001.11),
    totalExpenses = Some(5002.11),
    netProfit = Some(5003.11),
    netLoss = Some(5004.11),
    totalAdditions = Some(5005.11),
    totalDeductions = Some(5006.11),
    adjustedIncomeTaxLoss = Some(5009),
    taxableProfit = Some(5008),
    taxableProfitAfterIncomeTaxLossesDeduction = Some(5013),
    lossClaimsSummary = Some(eeaPropertyFhlSummaryModel),
    lossClaimsDetail = Some(eeaPropertyFhlDetailModel),
    bsas = Some(eeaPropertyFhlBsasModel)
  )

  // UK Property Non-FHL model:
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

  val ukPropertyNonFhlBsasModel: PropertyBsas = PropertyBsas(
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
    bsas = Some(ukPropertyNonFhlBsasModel)
  )
  
  // Foreign Property model:
  val foreignPropertySummaryModel: ForeignPropertyLossClaimsSummary = ForeignPropertyLossClaimsSummary(
    totalBroughtForwardIncomeTaxLosses = Some(6010),
    broughtForwardIncomeTaxLossesUsed = Some(6012),
    carrySidewaysIncomeTaxLossesUsed = Some(6014),
    totalIncomeTaxLossesCarriedForward = Some(6011),
    broughtForwardCarrySidewaysIncomeTaxLossesUsed = Some(6020)
  )

  val foreignPropertyLossBroughtForwardModel: ForeignPropertyLossBroughtForward = ForeignPropertyLossBroughtForward(
    taxYearLossIncurred = "2017-18",
    currentLossValue = 60101,
    mtdLoss = false
  )

  val foreignPropertyResultOfClaimAppliedModel: ForeignPropertyResultOfClaimApplied = ForeignPropertyResultOfClaimApplied(
    claimId = Some("CCIS12345678906"),
    originatingClaimId = Some("000000000000216"),
    taxYearClaimMade = "2017-18",
    claimType = TypeOfClaim.`carry-sideways-fhl`,
    mtdLoss = false,
    taxYearLossIncurred = "2017-18",
    lossAmountUsed = 60101,
    remainingLossValue = 60201
  )

  val foreignPropertyDefaultCarriedForwardLossModel: ForeignPropertyDefaultCarriedForwardLoss = ForeignPropertyDefaultCarriedForwardLoss(
    taxYearLossIncurred = "2017-18",
    currentLossValue = 601
  )

  val foreignPropertyClaimNotAppliedModel: ForeignPropertyClaimNotApplied = ForeignPropertyClaimNotApplied(
    claimId = "CCIS12345678926",
    taxYearClaimMade = "2017-18",
    claimType = TypeOfClaim.`carry-sideways-fhl`
  )

  val foreignPropertyDetailModel: ForeignPropertyLossClaimsDetail = ForeignPropertyLossClaimsDetail(
    lossesBroughtForward = Some(List(foreignPropertyLossBroughtForwardModel)),
    resultOfClaimsApplied = Some(List(foreignPropertyResultOfClaimAppliedModel)),
    defaultCarriedForwardLosses = Some(List(foreignPropertyDefaultCarriedForwardLossModel)),
    claimsNotApplied = Some(List(foreignPropertyClaimNotAppliedModel))
  )

  val foreignPropertyBsasModel: PropertyBsas = PropertyBsas(
    bsasId = "10000006",
    applied = false
  )

  val foreignPropertyModel: ForeignProperty = ForeignProperty(
    totalIncome = Some(6001.11),
    totalExpenses = Some(6002.11),
    netProfit = Some(6003.11),
    netLoss = Some(6004.11),
    totalAdditions = Some(6005.11),
    totalDeductions = Some(6006.11),
    accountingAdjustments = Some(6007.11),
    adjustedIncomeTaxLoss = Some(6009),
    taxableProfit = Some(6008),
    taxableProfitAfterIncomeTaxLossesDeduction = Some(6013),
    lossClaimsSummary = Some(foreignPropertySummaryModel),
    lossClaimsDetail = Some(foreignPropertyDetailModel),
    bsas = Some(foreignPropertyBsasModel)
  )
  
  // Top level objects
  val businessProfitAndLossModel: BusinessProfitAndLoss = BusinessProfitAndLoss(
    selfEmployments = Some(List(
      selfEmploymentModel1,
      selfEmploymentModel2
    )),
    ukPropertyFhl = Some(ukPropertyFhlModel),
    ukPropertyNonFhl = Some(ukPropertyNonFhlModel),
    eeaPropertyFhl = Some(eeaPropertyFhlModel),
    foreignProperty = Some(foreignPropertyModel)
  )

  val payPensionsProfitModel: PayPensionsProfit = PayPensionsProfit(
    incomeReceived = 7004,
    taxableIncome = 7006,
    totalSelfEmploymentProfit = Some(6001),
    totalPropertyProfit = Some(6002),
    totalFHLPropertyProfit = Some(6003),
    totalUKOtherPropertyProfit = Some(6004),
    totalForeignPropertyProfit = Some(6005),
    totalEeaFhlProfit = Some(6006),
    totalOccupationalPensionIncome = Some(6007.77),
    totalStateBenefitsIncome = Some(6008.88),
    totalBenefitsInKind = Some(6009.99),
    totalPayeEmploymentAndLumpSumIncome = Some(6010.00),
    totalEmploymentExpenses = Some(6011.11),
    totalSeafarersDeduction = Some(6013.12),
    totalForeignTaxOnForeignEmployment = Some(6014.15),
    totalEmploymentIncome = Some(6012),
    totalShareSchemesIncome = Some(6015.20),
    totalOverseasPensionsStateBenefitsRoyalties = Some(6016.25),
    totalAllOtherIncomeReceivedWhilstAbroad = Some(6017.30),
    totalOverseasIncomeAndGains = Some(6018.35),
    totalForeignBenefitsAndGifts = Some(6019.40),
    businessProfitAndLoss = Some(businessProfitAndLossModel)
  )

  val ukSavingModel1: UkSaving = UkSaving(
    savingsAccountId = Some("SAVKB1UVwUTBQGJ"),
    savingsAccountName = Some("UK Savings Account ONE"),
    grossIncome = 90101.11,
    netIncome = Some(90201.11),
    taxDeducted = Some(90301.11)
  )

  val ukSavingModel2: UkSaving = UkSaving(
    savingsAccountId = Some("SAVKB2UVwUTBQGJ"),
    savingsAccountName = Some("UK Savings Account TWO"),
    grossIncome = 90102.11,
    netIncome = Some(90202.11),
    taxDeducted = Some(90302.11)
  )

  val ukSecurityModel: UkSecurity = UkSecurity(
    ukSecuritiesAccountId = Some("SAVKB3UVwUTBQGJ"),
    ukSecuritiesAccountName = Some("UK Securities Account ONE"),
    grossIncome = 11101.11,
    netIncome = Some(11201.11),
    taxDeducted = Some(11301.11)
  )

  val savingsAndGainsModel: SavingsAndGains = SavingsAndGains(
    incomeReceived = 7012,
    taxableIncome = 7014,
    totalOfAllGains = 7015,
    totalUkSavingsAndSecurities = Some(7016),
    ukSavings = Some(Seq(
      ukSavingModel1,
      ukSavingModel2
    )),
    ukSecurities = Some(Seq(
      ukSecurityModel
    )),
    totalGainsWithNoTaxPaidAndVoidedIsa = Some(7017),
    totalForeignGainsOnLifePoliciesNoTaxPaid = Some(7018),
    totalForeignSavingsAndGainsIncome = Some(7019)
  )

  val dividendsModel: Dividends = Dividends(
    incomeReceived = 7020,
    taxableIncome = 7022,
    totalUkDividends = Some(7024),
    totalForeignDividends = Some(7026)
  )

  val lumpSumsModel: LumpSums = LumpSums(
    incomeReceived = 8010,
    taxableIncome = 8020
  )

  val gainsOnLifePoliciesModel: GainsOnLifePolicies = GainsOnLifePolicies(
    incomeReceived = 9010,
    taxableIncome = 9020,
    totalUkGainsWithTaxPaid = Some(9030),
    totalForeignGainsOnLifePoliciesWithTaxPaid = Some(9040)
  )

  val taxableIncomeDetailModel: TaxableIncomeDetail = TaxableIncomeDetail(
    payPensionsProfit = Some(payPensionsProfitModel),
    savingsAndGains = Some(savingsAndGainsModel),
    dividends = Some(dividendsModel),
    lumpSums = Some(lumpSumsModel),
    gainsOnLifePolicies = Some(gainsOnLifePoliciesModel)
  )

  val taxableIncomeSummaryModel: TaxableIncomeSummary = TaxableIncomeSummary(
    totalIncomeReceivedFromAllSources = 7001,
    totalTaxableIncome = 100
  )

  val taxableIncomeModel: TaxableIncome = TaxableIncome(
    summary = taxableIncomeSummaryModel,
    detail = taxableIncomeDetailModel
  )
}