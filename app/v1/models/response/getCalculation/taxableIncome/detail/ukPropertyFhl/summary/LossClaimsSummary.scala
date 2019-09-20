/*
 * Copyright 2019 HM Revenue & Customs
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

package v1.models.response.getCalculation.taxableIncome.detail.ukPropertyFhl.summary

import play.api.libs.functional.syntax._
import play.api.libs.json._

case class LossClaimsSummary(
                              lossForCSFHL: Option[BigDecimal],
                              totalBroughtForwardIncomeTaxLosses: Option[BigDecimal],
                              broughtForwardIncomeTaxLossesUsed: Option[BigDecimal],
                              totalIncomeTaxLossesCarriedForward: Option[BigDecimal]
                            )

object LossClaimsSummary {
  implicit val writes: OWrites[LossClaimsSummary] = Json.writes[LossClaimsSummary]

  implicit val reads: Reads[LossClaimsSummary] = (
    (JsPath \ "lossForCSFHL").readNullable[BigDecimal] and
      (JsPath \ "totalBroughtForwardIncomeTaxLosses").readNullable[BigDecimal] and
      (JsPath \ "broughtForwardIncomeTaxLossesUsed").readNullable[BigDecimal] and
      (JsPath \ "totalIncomeTaxLossesCarriedForward").readNullable[BigDecimal]
  )(LossClaimsSummary.apply _)
}
