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

package v1r2.models.response.getCalculation.endOfYearEstimate.summary

import play.api.libs.functional.syntax._
import play.api.libs.json.{JsPath, Json, OWrites, Reads}

case class EoyEstimateSummary(totalEstimatedIncome: Option[BigInt],
                              totalTaxableIncome: Option[BigInt],
                              incomeTaxAmount: Option[BigDecimal],
                              nic2: Option[BigDecimal],
                              nic4: Option[BigDecimal],
                              totalNicAmount: Option[BigDecimal],
                              totalStudentLoansRepaymentAmount: Option[BigDecimal],
                              totalAnnualPaymentsTaxCharged: Option[BigDecimal],
                              totalRoyaltyPaymentsTaxCharged: Option[BigDecimal],
                              totalTaxDeducted: Option[BigDecimal],
                              incomeTaxNicAmount: Option[BigDecimal])

object EoyEstimateSummary {
  val empty = EoyEstimateSummary(None, None, None, None, None, None, None, None, None, None, None)

  implicit val writes: OWrites[EoyEstimateSummary] = Json.writes[EoyEstimateSummary]

  implicit val reads: Reads[EoyEstimateSummary] = (
    (JsPath \ "totalEstimatedIncome").readNullable[BigInt] and
      (JsPath \ "totalTaxableIncome").readNullable[BigInt] and
      (JsPath \ "incomeTaxAmount").readNullable[BigDecimal] and
      (JsPath \ "nic2").readNullable[BigDecimal] and
      (JsPath \ "nic4").readNullable[BigDecimal] and
      (JsPath \ "totalNicAmount").readNullable[BigDecimal] and
      (JsPath \ "totalStudentLoansRepaymentAmount").readNullable[BigDecimal] and
      (JsPath \ "totalAnnuityPaymentsTaxCharged").readNullable[BigDecimal] and
      (JsPath \ "totalRoyaltyPaymentsTaxCharged").readNullable[BigDecimal] and
      (JsPath \ "totalTaxDeducted").readNullable[BigDecimal] and
      (JsPath \ "incomeTaxNicAmount").readNullable[BigDecimal]
    ) (EoyEstimateSummary.apply _)
}