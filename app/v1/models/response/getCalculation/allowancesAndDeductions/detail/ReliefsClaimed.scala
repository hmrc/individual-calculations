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

case class ReliefsClaimed(
                           `type`: String,
                           amountClaimed: Option[BigDecimal],
                           allowableAmount: Option[BigDecimal],
                           amountUsed: Option[BigDecimal],
                           rate: Option[Double]
                         )

object ReliefsClaimed extends NestedJsonReads{

  implicit val writes: OWrites[ReliefsClaimed] = Json.writes[ReliefsClaimed]

  implicit val reads: Reads[ReliefsClaimed] = (
    (JsPath \ "type").read[String].map {
      case "nonDeductableLoanInterest" => "nonDeductibleLoanInterest"
      case other => other
    } and
      (JsPath \ "amountClaimed").readNullable[BigDecimal] and
      (JsPath \ "allowableAmount").readNullable[BigDecimal] and
      (JsPath \ "amountUsed").readNullable[BigDecimal] and
      (JsPath \ "rate").readNullable[Double]
    )(ReliefsClaimed.apply _)
}
