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

package v2.models.response.getCalculation.taxableIncome.detail.payPensionsProfit.eeaPropertyFhl

import play.api.libs.functional.syntax._
import play.api.libs.json._

case class EeaPropertyFhlLossClaimsSummary(lossForCSFHL: Option[BigInt],
                                           totalBroughtForwardIncomeTaxLosses: Option[BigInt],
                                           broughtForwardIncomeTaxLossesUsed: Option[BigInt],
                                           totalIncomeTaxLossesCarriedForward: Option[BigInt])

object EeaPropertyFhlLossClaimsSummary {
  val empty: EeaPropertyFhlLossClaimsSummary = EeaPropertyFhlLossClaimsSummary(None, None, None, None)

  implicit val reads: Reads[EeaPropertyFhlLossClaimsSummary] = (
    (JsPath \ "lossForCSFHL").readNullable[BigInt] and
      (JsPath \ "totalBroughtForwardIncomeTaxLosses").readNullable[BigInt] and
      (JsPath \ "broughtForwardIncomeTaxLossesUsed").readNullable[BigInt] and
      (JsPath \ "totalIncomeTaxLossesCarriedForward").readNullable[BigInt]
  )(EeaPropertyFhlLossClaimsSummary.apply _)

  implicit val writes: OWrites[EeaPropertyFhlLossClaimsSummary] = Json.writes[EeaPropertyFhlLossClaimsSummary]
}
