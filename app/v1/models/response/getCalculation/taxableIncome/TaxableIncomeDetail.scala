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

package v1.models.response.getCalculation.taxableIncome

import play.api.libs.functional.syntax._
import play.api.libs.json._
import utils.NestedJsonReads
import v1.models.response.getCalculation.taxableIncome.detail._

case class TaxableIncomeDetail(payPensionsProfit: Option[PayPensionsProfit],
                               savingsAndGains: Option[SavingsAndGains],
                               dividends: Option[Dividends],
                               lumpSums: Option[LumpSums],
                               gainsOnLifePolicies: Option[GainsOnLifePolicies])

object TaxableIncomeDetail extends NestedJsonReads {
  implicit val reads: Reads[TaxableIncomeDetail] = {
    val commonJsPath: JsPath = JsPath \ "calculation" \ "taxCalculation" \ "incomeTax"
    (
      emptyIfNotPresent[PayPensionsProfit](commonJsPath \ "payPensionsProfit") and
        emptyIfNotPresent[SavingsAndGains](commonJsPath \ "savingsAndGains") and
        (commonJsPath \ "dividends").readNestedNullable[Dividends] and
        (commonJsPath \ "lumpSums").readNestedNullable[LumpSums] and
        (commonJsPath \ "gainsOnLifePolicies").readNestedNullable[GainsOnLifePolicies]
      ) (TaxableIncomeDetail.apply _)
  }

  implicit val writes: OWrites[TaxableIncomeDetail] = Json.writes[TaxableIncomeDetail]
}
