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
import utils.NestedJsonReads
import play.api.libs.functional.syntax._

case class PensionContributions(
                                 totalPensionContributions: Option[BigDecimal],
                                 retirementAnnuityPayments: Option[BigDecimal],
                                 paymentToEmployersSchemeNoTaxRelief: Option[BigDecimal],
                                 overseasPensionSchemeContributions: Option[BigDecimal]
                               )

object PensionContributions extends NestedJsonReads{
  val empty = PensionContributions(None, None, None, None)

  implicit val writes: OWrites[PensionContributions] = Json.writes[PensionContributions]

  implicit val reads: Reads[PensionContributions] = (
    (JsPath \ "pensionContributions").readNullable[BigDecimal] and
      (JsPath \ "pensionContributionsDetail" \ "retirementAnnuityPayments").readNestedNullable[BigDecimal] and
      (JsPath \ "pensionContributionsDetail" \ "paymentToEmployersSchemeNoTaxRelief").readNestedNullable[BigDecimal] and
      (JsPath \ "pensionContributionsDetail" \ "overseasPensionSchemeContributions").readNestedNullable[BigDecimal]
    )(PensionContributions.apply _)
}
