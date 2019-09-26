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

package v1.models.response.getCalculation.taxableIncome.detail

import play.api.libs.functional.syntax._
import play.api.libs.json._
import utils.NestedJsonReads

case class CalculationDetail(payPensionsProfit: Option[PayPensionsProfit], savingsAndGains: Option[SavingsAndGains], dividends: Option[Dividends])

object CalculationDetail extends NestedJsonReads {
  implicit val writes: OWrites[CalculationDetail] = Json.writes[CalculationDetail]
  implicit val reads: Reads[CalculationDetail] = (
    emptyIfNotPresent[PayPensionsProfit](__ \ "calculation" \ "taxCalculation" \ "incomeTax" \ "payPensionsProfit") and
      emptyIfNotPresent[SavingsAndGains](__ \ "calculation" \ "taxCalculation" \ "incomeTax" \ "savingsAndGains") and
      (JsPath \ "calculation" \ "taxCalculation" \ "incomeTax" \ "dividends").readNestedNullable[Dividends].orElse(Reads.pure(None))
  )(CalculationDetail.apply _)
}
