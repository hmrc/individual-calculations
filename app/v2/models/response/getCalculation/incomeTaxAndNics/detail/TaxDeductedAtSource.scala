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

case class TaxDeductedAtSource(ukLandAndProperty: Option[BigInt],
                               savings: Option[BigInt],
                               cis: Option[BigDecimal],
                               securities: Option[BigDecimal],
                               voidedIsa: Option[BigDecimal],
                               payeEmployments: Option[BigDecimal],
                               occupationalPensions: Option[BigDecimal],
                               stateBenefits: Option[BigDecimal])

object TaxDeductedAtSource {
  val empty = TaxDeductedAtSource(None, None, None, None, None, None, None, None)

  implicit val writes: OWrites[TaxDeductedAtSource] = Json.writes[TaxDeductedAtSource]

  implicit val reads: Reads[TaxDeductedAtSource] = (
    (JsPath \ "ukLandAndProperty").readNullable[BigInt] and
      (JsPath \ "bbsi").readNullable[BigInt] and
      (JsPath \ "cis").readNullable[BigDecimal] and
      (JsPath \ "securities").readNullable[BigDecimal] and
      (JsPath \ "voidedIsa").readNullable[BigDecimal] and
      (JsPath \ "payeEmployments").readNullable[BigDecimal] and
      (JsPath \ "occupationalPensions").readNullable[BigDecimal] and
      (JsPath \ "stateBenefits").readNullable[BigDecimal]
    ) (TaxDeductedAtSource.apply _)
}