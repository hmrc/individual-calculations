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

import play.api.libs.functional.syntax._
import play.api.libs.json.{JsPath, Json, OWrites, Reads}
import sangria.macros.derive.{ReplaceField, deriveObjectType}
import sangria.schema._
import utils.NestedJsonReads

case class ForeignTaxCreditRelief(
                                   incomeSourceType: IncomeSourceType,
                                   incomeSourceId: Option[String],
                                   countryCode: String,
                                   allowableAmount: Option[BigDecimal],
                                   rate: Option[Double],
                                   amountUsed: Option[BigDecimal]
                                 )

object ForeignTaxCreditRelief extends NestedJsonReads {

  implicit val writes: OWrites[ForeignTaxCreditRelief] = Json.writes[ForeignTaxCreditRelief]

  implicit val reads: Reads[ForeignTaxCreditRelief] = (
    (JsPath \ "incomeSourceType").read[DesIncomeSourceType].map(_.toIncomeSourceType) and
      (JsPath \ "incomeSourceId").readNullable[String] and
      (JsPath \ "countryCode").read[String] and
      (JsPath \ "allowableAmount").readNullable[BigDecimal] and
      (JsPath \ "rate").readNullable[Double] and
      (JsPath \ "amountUsed").readNullable[BigDecimal]
    ) (ForeignTaxCreditRelief.apply _)

  implicit def gqlType: ObjectType[Unit, ForeignTaxCreditRelief] = deriveObjectType[Unit, ForeignTaxCreditRelief](
    ReplaceField("incomeSourceType", Field("incomeSourceType", StringType, resolve = _.value.incomeSourceType.toString))
  )
}
