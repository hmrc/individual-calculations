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

package v2.models.response.getCalculation.taxableIncome.detail

import play.api.libs.functional.syntax._
import play.api.libs.json.{JsPath, Json, Reads, Writes}
import utils.NestedJsonReads

case class Dividends(incomeReceived: BigInt,
                     taxableIncome: BigInt,
                     totalUkDividends: Option[BigInt],
                     totalForeignDividends: Option[BigInt])

object Dividends extends NestedJsonReads {
  implicit val reads: Reads[Dividends] = {
    val dividendsJsPath: JsPath = JsPath \ "calculation" \ "taxCalculation" \ "incomeTax" \ "dividends"
    val dividendsIncomeJsPath: JsPath = JsPath \ "calculation" \ "dividendsIncome"
    (
      (dividendsJsPath \ "incomeReceived").read[BigInt] and
        (dividendsJsPath \ "taxableIncome").read[BigInt] and
        (dividendsIncomeJsPath \ "totalUkDividends").readNestedNullable[BigInt] and
        (dividendsIncomeJsPath \ "chargeableForeignDividends").readNestedNullable[BigInt]
    )(Dividends.apply _)
  }

  implicit val writes: Writes[Dividends] = Json.writes[Dividends]
}