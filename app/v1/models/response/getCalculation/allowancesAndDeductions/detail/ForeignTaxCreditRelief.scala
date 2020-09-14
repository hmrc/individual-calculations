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

case class ForeignTaxCreditRelief(
                                   incomeSourceType: Option[BigInt],
                                   incomeSourceId: Option[BigInt],
                                   countryCode: Option[BigInt],
                                   allowableAmount: Option[BigInt],
                                   rate: Option[BigInt],
                                   amountUsed: Option[BigInt]
                                 )

object ForeignTaxCreditRelief extends NestedJsonReads{
  val empty = ForeignTaxCreditRelief(None, None, None, None, None, None)

  implicit val writes: OWrites[ForeignTaxCreditRelief] = Json.writes[ForeignTaxCreditRelief]

  implicit val reads: Reads[ForeignTaxCreditRelief] = (
    (JsPath \ "incomeSourceType").readNestedNullable[BigInt] and
      (JsPath \ "incomeSourceId").readNestedNullable[BigInt] and
      (JsPath \ "countryCode").readNestedNullable[BigInt] and
      (JsPath \ "allowableAmount").readNestedNullable[BigInt] and
      (JsPath \ "rate").readNestedNullable[BigInt] and
      (JsPath \ "amountUsed").readNestedNullable[BigInt]
    )(ForeignTaxCreditRelief.apply _)
}
