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
                           `type`: Option[String],
                           amountClaimed: Option[BigDecimal],
                           allowableAmount: Option[BigDecimal],
                           amountUsed: Option[BigDecimal],
                           rate: Option[Double]
                         )

object ReliefsClaimed extends NestedJsonReads{
  val empty = ReliefsClaimed(None, None, None, None, None)

  implicit val writes: OWrites[ReliefsClaimed] = Json.writes[ReliefsClaimed]

  implicit val reads: Reads[ReliefsClaimed] = (
    (JsPath \ "type").readNestedNullable[String] and
      (JsPath \ "amountClaimed").readNestedNullable[BigDecimal] and
      (JsPath \ "allowableAmount").readNestedNullable[BigDecimal] and
      (JsPath \ "amountUsed").readNestedNullable[BigDecimal] and
      (JsPath \ "rate").readNestedNullable[Double]
    )(ReliefsClaimed.apply _)
}
