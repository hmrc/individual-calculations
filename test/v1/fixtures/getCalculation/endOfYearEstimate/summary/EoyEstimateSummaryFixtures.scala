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

package v1.fixtures.getCalculation.endOfYearEstimate.summary

import play.api.libs.json.{JsObject, JsValue, Json}
import v1.models.response.getCalculation.endOfYearEstimate.summary.EoyEstimateSummary

object EoyEstimateSummaryFixtures {

  val totalEstimatedIncome: Option[BigInt]   = Some(1001)
  val totalTaxableIncome: Option[BigInt]     = Some(1002)
  val incomeTaxAmount: Option[BigDecimal]    = Some(1003.1)
  val nic2: Option[BigDecimal]               = Some(1004.1)
  val nic4: Option[BigDecimal]               = Some(1005.1)
  val totalNicAmount: Option[BigDecimal]     = Some(1006.1)
  val totalStudentLoansRepaymentAmount: Option[BigDecimal] = Some(1007.1)
  val totalAnnualPaymentsTaxCharged: Option[BigDecimal] = Some(1008.1)
  val totalRoyaltyPaymentsTaxCharged: Option[BigDecimal] = Some(1009.1)
  val totalTaxDeducted: Option[BigDecimal] = Some(1010.1)
  val incomeTaxNicAmount: Option[BigDecimal] = Some(1011.1)

  val eoyEstimateSummaryResponse: EoyEstimateSummary =
    EoyEstimateSummary(
      totalEstimatedIncome, totalTaxableIncome, incomeTaxAmount, nic2, nic4, totalNicAmount, totalStudentLoansRepaymentAmount,
      totalAnnualPaymentsTaxCharged, totalRoyaltyPaymentsTaxCharged, totalTaxDeducted, incomeTaxNicAmount
    )

  val eoyEstimateSummaryDesJson: JsValue = Json.parse(s"""{
      |  "totalEstimatedIncome": ${totalEstimatedIncome.get},
      |  "totalTaxableIncome": ${totalTaxableIncome.get},
      |  "incomeTaxAmount": ${incomeTaxAmount.get},
      |  "nic2": ${nic2.get},
      |  "nic4": ${nic4.get},
      |  "totalNicAmount": ${totalNicAmount.get},
      |  "totalStudentLoansRepaymentAmount": ${totalStudentLoansRepaymentAmount.get},
      |  "totalAnnuityPaymentsTaxCharged": ${totalAnnualPaymentsTaxCharged.get},
      |  "totalRoyaltyPaymentsTaxCharged": ${totalRoyaltyPaymentsTaxCharged.get},
      |  "totalTaxDeducted": ${totalTaxDeducted.get},
      |  "incomeTaxNicAmount":  ${incomeTaxNicAmount.get}
      |}""".stripMargin)

  val eoyEstimateSummaryDesJsonMissingFields: JsValue = Json.parse(s"""{
      |  "totalEstimatedIncome": ${totalEstimatedIncome.get},
      |  "totalTaxableIncome": ${totalTaxableIncome.get},
      |  "incomeTaxAmount": ${incomeTaxAmount.get},
      |  "nic2": ${nic2.get},
      |  "nic4": ${nic4.get}
      |}""".stripMargin)

  val eoyEstimateSummaryWrittenJson: JsValue = Json.parse(s"""{
      |  "totalEstimatedIncome": ${totalEstimatedIncome.get},
      |  "totalTaxableIncome": ${totalTaxableIncome.get},
      |  "incomeTaxAmount": ${incomeTaxAmount.get},
      |  "nic2": ${nic2.get},
      |  "nic4": ${nic4.get},
      |  "totalNicAmount": ${totalNicAmount.get},
      |  "totalStudentLoansRepaymentAmount": ${totalStudentLoansRepaymentAmount.get},
      |  "totalAnnualPaymentsTaxCharged": ${totalAnnualPaymentsTaxCharged.get},
      |  "totalRoyaltyPaymentsTaxCharged": ${totalRoyaltyPaymentsTaxCharged.get},
      |  "totalTaxDeducted": ${totalTaxDeducted.get},
      |  "incomeTaxNicAmount":  ${incomeTaxNicAmount.get}
      |}""".stripMargin)

  val eoyEstimateSummaryWrittenJsonMissingFields: JsValue = Json.parse(s"""{
      |  "totalEstimatedIncome": ${totalEstimatedIncome.get},
      |  "totalTaxableIncome": ${totalTaxableIncome.get},
      |  "incomeTaxAmount": ${incomeTaxAmount.get},
      |  "nic2": ${nic2.get},
      |  "nic4": ${nic4.get}
      |}""".stripMargin)

  val eoyEstimateSummaryWrittenJsonObject: JsObject = Json.obj("summary" -> eoyEstimateSummaryWrittenJson)

  val eoyEstimateSummaryInvalidJson: JsValue = Json.parse(s"""{
      |  "totalEstimatedIncome": "notANumericValue",
      |  "totalTaxableIncome": ${totalTaxableIncome.get},
      |  "incomeTaxAmount": ${incomeTaxAmount.get},
      |  "nic2": ${nic2.get},
      |  "nic4": ${nic4.get},
      |  "totalNicAmount": ${totalNicAmount.get},
      |  "totalStudentLoansRepaymentAmount": ${totalStudentLoansRepaymentAmount.get},
      |  "totalAnnuityPaymentsTaxCharged": ${totalAnnualPaymentsTaxCharged.get},
      |  "totalRoyaltyPaymentsTaxCharged": ${totalRoyaltyPaymentsTaxCharged.get},
      |  "totalTaxDeducted": ${totalTaxDeducted.get},
      |  "incomeTaxNicAmount":  ${incomeTaxNicAmount.get}
      |}""".stripMargin)

  def eoyEstimateSummaryResponseFactory(totalEstimatedIncome: Option[BigInt] = totalEstimatedIncome,
                                        totalTaxableIncome: Option[BigInt] = totalTaxableIncome,
                                        incomeTaxAmount: Option[BigDecimal] = incomeTaxAmount,
                                        nic2: Option[BigDecimal] = nic2,
                                        nic4: Option[BigDecimal] = nic4,
                                        totalNicAmount: Option[BigDecimal] = totalNicAmount,
                                        totalStudentLoansRepaymentAmount: Option[BigDecimal] = totalStudentLoansRepaymentAmount,
                                        totalAnnualPaymentsTaxCharged: Option[BigDecimal] = totalAnnualPaymentsTaxCharged,
                                        totalRoyaltyPaymentsTaxCharged: Option[BigDecimal] = totalRoyaltyPaymentsTaxCharged,
                                        totalTaxDeducted: Option[BigDecimal] = totalTaxDeducted,
                                        incomeTaxNicAmount: Option[BigDecimal] = incomeTaxNicAmount): EoyEstimateSummary =
    EoyEstimateSummary(totalEstimatedIncome, totalTaxableIncome, incomeTaxAmount, nic2, nic4, totalNicAmount,
      totalStudentLoansRepaymentAmount, totalAnnualPaymentsTaxCharged, totalRoyaltyPaymentsTaxCharged, totalTaxDeducted, incomeTaxNicAmount)

}