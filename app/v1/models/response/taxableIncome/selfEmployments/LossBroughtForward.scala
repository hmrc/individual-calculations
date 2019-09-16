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
package v1.models.response.taxableIncome.selfEmployments

import play.api.libs.functional.syntax._
import play.api.libs.json._
import v1.models.des.{ LossType, ReliefClaimed }
import v1.models.request.DesTaxYear

case class LossBroughtForward(
    lossType: LossType,
    taxYearLossIncurred: String,
    currentLossValue: BigDecimal,
    mtdLoss: Option[Boolean] // FIXME only when false; why not just mandatory Boolean - easier for vendors???
)

object LossBroughtForward {

  implicit val writes: OWrites[LossBroughtForward] = Json.writes[LossBroughtForward]

  implicit val reads: Reads[LossBroughtForward] = (
    (__ \ "lossType").read[LossType] and
      (__ \ "taxYearLossIncurred").read[Int].map(DesTaxYear.fromDesIntToString) and
      (__ \ "currentLossValue").read[BigDecimal] and
      (__ \ "mtdLoss").readNullable[Boolean]
  )(LossBroughtForward.apply _)

}
