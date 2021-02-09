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

package v2.models.response.getCalculation.incomeTaxAndNics.detail

import play.api.libs.functional.syntax._
import play.api.libs.json._
import utils.NestedJsonReads

case class PensionSavingsTaxChargesDetail(lumpSumBenefitTakenInExcessOfLifetimeAllowance: Option[PensionTypeBreakdown],
                                          benefitInExcessOfLifetimeAllowance: Option[PensionTypeBreakdown],
                                          pensionSchemeUnauthorisedPaymentsSurcharge: Option[PensionTypeBreakdown],
                                          pensionSchemeUnauthorisedPaymentsNonSurcharge: Option[PensionTypeBreakdown],
                                          pensionSchemeOverseasTransfers: Option[PensionSchemeOverseasTransfers],
                                          pensionContributionsInExcessOfTheAnnualAllowance: Option[PensionContributionsInExcessOfTheAnnualAllowance],
                                          overseasPensionContributions: Option[OverseasPensionContributions])

object PensionSavingsTaxChargesDetail extends NestedJsonReads {
  val empty = PensionSavingsTaxChargesDetail(None, None, None, None, None, None, None)

  implicit val writes: OWrites[PensionSavingsTaxChargesDetail] = Json.writes[PensionSavingsTaxChargesDetail]

  implicit val reads: Reads[PensionSavingsTaxChargesDetail] = (
    (JsPath \ "excessOfLifeTimeAllowance" \ "lumpSumBenefitTakenInExcessOfLifetimeAllowance").readNestedNullable[PensionTypeBreakdown] and
      (JsPath \ "excessOfLifeTimeAllowance" \ "benefitInExcessOfLifetimeAllowance").readNestedNullable[PensionTypeBreakdown] and
      (JsPath \ "pensionSchemeUnauthorisedPayments" \ "pensionSchemeUnauthorisedPaymentsSurcharge").readNestedNullable[PensionTypeBreakdown] and
      (JsPath \ "pensionSchemeUnauthorisedPayments" \ "pensionSchemeUnauthorisedPaymentsNonSurcharge").readNestedNullable[PensionTypeBreakdown] and
      (JsPath \ "pensionSchemeOverseasTransfers").readNestedNullable[PensionSchemeOverseasTransfers] and
      (JsPath \ "pensionContributionsInExcessOfTheAnnualAllowance").readNullable[PensionContributionsInExcessOfTheAnnualAllowance] and
      (JsPath \ "overseasPensionContributions").readNestedNullable[OverseasPensionContributions]
    ) (PensionSavingsTaxChargesDetail.apply _)
}
