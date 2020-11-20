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

package v1.models.response.getCalculation.taxableIncome.detail

import play.api.libs.functional.syntax._
import play.api.libs.json.{JsPath, Json, OWrites, Reads}
import sangria.macros.derive.deriveObjectType
import sangria.schema.ObjectType

case class UkSaving(savingsAccountId: Option[String],
                    savingsAccountName: Option[String],
                    grossIncome: BigDecimal,
                    netIncome: Option[BigDecimal],
                    taxDeducted: Option[BigDecimal])

object UkSaving {
  implicit val reads: Reads[UkSaving] = (
    (JsPath \ "incomeSourceId").readNullable[String] and
      (JsPath \ "incomeSourceName").readNullable[String] and
      (JsPath \ "grossIncome").read[BigDecimal] and
      (JsPath \ "netIncome").readNullable[BigDecimal] and
      (JsPath \ "taxDeducted").readNullable[BigDecimal]
    )(UkSaving.apply _)

  implicit val writes: OWrites[UkSaving] = Json.writes[UkSaving]

  implicit def gqlType: ObjectType[Unit, UkSaving] = deriveObjectType[Unit, UkSaving]()
}
