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

package v1.models.response.getCalculation.taxableIncome.detail.payPensionsProfit.ukPropertyFhl

import play.api.libs.functional.syntax._
import play.api.libs.json._
import sangria.macros.derive._
import sangria.schema.ObjectType

case class UkPropertyFhlLossClaimsSummary(lossForCSFHL: Option[BigInt],
                                          totalBroughtForwardIncomeTaxLosses: Option[BigInt],
                                          broughtForwardIncomeTaxLossesUsed: Option[BigInt],
                                          totalIncomeTaxLossesCarriedForward: Option[BigInt])

object UkPropertyFhlLossClaimsSummary {
  val empty: UkPropertyFhlLossClaimsSummary = UkPropertyFhlLossClaimsSummary(None, None, None, None)

  implicit val reads: Reads[UkPropertyFhlLossClaimsSummary] = (
    (JsPath \ "lossForCSFHL").readNullable[BigInt] and
      (JsPath \ "totalBroughtForwardIncomeTaxLosses").readNullable[BigInt] and
      (JsPath \ "broughtForwardIncomeTaxLossesUsed").readNullable[BigInt] and
      (JsPath \ "totalIncomeTaxLossesCarriedForward").readNullable[BigInt]
    )(UkPropertyFhlLossClaimsSummary.apply _)

  implicit val writes: OWrites[UkPropertyFhlLossClaimsSummary] = Json.writes[UkPropertyFhlLossClaimsSummary]

  implicit def gqlType: ObjectType[Unit, UkPropertyFhlLossClaimsSummary] =
    deriveObjectType[Unit, UkPropertyFhlLossClaimsSummary]()
}
