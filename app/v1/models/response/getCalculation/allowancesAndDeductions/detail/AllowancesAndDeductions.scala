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

package v1.models.response.getCalculation.allowancesAndDeductions.detail

import play.api.libs.json.{JsPath, Json, OWrites, Reads}
import play.api.libs.functional.syntax._
import utils.NestedJsonReads

case class AllowancesAndDeductions(personalAllowance: Option[BigInt],
                                   reducedPersonalAllowance: Option[BigInt],
                                   giftOfInvestmentsAndPropertyToCharity: Option[BigInt],
                                   blindPersonsAllowance: Option[BigInt],
                                   lossesAppliedToGeneralIncome: Option[BigInt],
                                   qualifyingLoanInterestFromInvestments: Option[BigInt],
                                   `post-cessationTradeReceipts`: Option[BigInt],
                                   paymentsToTradeUnionsForDeathBenefits: Option[BigInt],
                                   annualPayments: Option[AnnualPayments],
                                   pensionContributions: Option[PensionContributions]
                                  )

object AllowancesAndDeductions extends NestedJsonReads{
  val empty = AllowancesAndDeductions(None, None, None, None, None, None, None, None, None, None)

  implicit val writes: OWrites[AllowancesAndDeductions] = Json.writes[AllowancesAndDeductions]

  implicit val reads: Reads[AllowancesAndDeductions] = (
    (JsPath \ "calculation" \ "allowancesAndDeductions" \ "personalAllowance").readNestedNullable[BigInt] and
      (JsPath \ "calculation" \ "allowancesAndDeductions" \ "reducedPersonalAllowance").readNestedNullable[BigInt] and
      (JsPath \ "calculation" \ "allowancesAndDeductions" \ "giftOfInvestmentsAndPropertyToCharity").readNestedNullable[BigInt] and
      (JsPath \ "calculation" \ "allowancesAndDeductions" \ "blindPersonsAllowance").readNestedNullable[BigInt] and
      (JsPath \ "calculation" \ "allowancesAndDeductions" \ "lossesAppliedToGeneralIncome").readNestedNullable[BigInt] and
      (JsPath \ "calculation" \ "allowancesAndDeductions" \ "qualifyingLoanInterestFromInvestments").readNestedNullable[BigInt] and
      (JsPath \ "calculation" \ "allowancesAndDeductions" \ "post-cessationTradeReceipts").readNestedNullable[BigInt] and
      (JsPath \ "calculation" \ "allowancesAndDeductions" \ "paymentsToTradeUnionsForDeathBenefits").readNestedNullable[BigInt] and
      (JsPath \ "calculation" \ "allowancesAndDeductions").readNestedNullable(AnnualPayments.reads) and
      (JsPath \ "calculation" \ "allowancesAndDeductions").readNestedNullable(PensionContributions.reads)
    )(AllowancesAndDeductions.apply _)
}
