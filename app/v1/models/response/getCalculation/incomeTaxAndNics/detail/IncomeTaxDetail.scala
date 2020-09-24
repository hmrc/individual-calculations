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

package v1.models.response.getCalculation.incomeTaxAndNics.detail

import play.api.libs.functional.syntax._
import play.api.libs.json._
import sangria.macros.derive.deriveObjectType
import sangria.schema.ObjectType
import utils.NestedJsonReads
case class IncomeTaxDetail(payPensionsProfit: Option[IncomeTypeBreakdown],
                           savingsAndGains: Option[IncomeTypeBreakdown],
                           dividends: Option[IncomeTypeBreakdown],
                           giftAid: Option[GiftAid])

object IncomeTaxDetail extends NestedJsonReads {
  val empty = IncomeTaxDetail(None, None, None, None)

  implicit val writes: OWrites[IncomeTaxDetail] = Json.writes[IncomeTaxDetail]

  implicit val reads: Reads[IncomeTaxDetail] = (
    (JsPath \ "taxCalculation" \ "incomeTax" \ "payPensionsProfit").readNestedNullable[IncomeTypeBreakdown] and
      (JsPath \ "taxCalculation" \ "incomeTax" \ "savingsAndGains").readNestedNullable[IncomeTypeBreakdown] and
      (JsPath \ "taxCalculation" \ "incomeTax" \ "dividends").readNestedNullable[IncomeTypeBreakdown] and
      (JsPath \ "giftAid").readNullable[GiftAid]
    ) (IncomeTaxDetail.apply _)

  implicit def gqlType: ObjectType[Unit, IncomeTaxDetail] = deriveObjectType[Unit, IncomeTaxDetail]()
}
