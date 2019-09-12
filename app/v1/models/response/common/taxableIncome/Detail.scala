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

package v1.models.response.common.taxableIncome

import play.api.libs.functional.syntax._
import play.api.libs.json.{JsPath, Json, Reads, Writes}
import utils.NestedJsonReads
import v1.models.response.common.taxableIncome.detail.{Dividends, PayPensionsProfit, SavingsAndGains}

case class Detail(payPensionsProfit: Option[PayPensionsProfit], savingsAndGains: Option[SavingsAndGains], dividends: Option[Dividends])

object Detail extends NestedJsonReads {
  implicit val writes: Writes[Detail] = Json.writes[Detail]
  implicit val reads: Reads[Detail] = (
    JsPath.readNullable[PayPensionsProfit].orElse(Reads.pure(None)) and
      (JsPath \ "calculation").readNullable[SavingsAndGains].orElse(Reads.pure(None)) and
      (JsPath \ "calculation" \ "taxCalculation" \ "incomeTax" \ "dividends").readNestedNullable[Dividends].orElse(Reads.pure(None))
  )(Detail.apply _)
}
