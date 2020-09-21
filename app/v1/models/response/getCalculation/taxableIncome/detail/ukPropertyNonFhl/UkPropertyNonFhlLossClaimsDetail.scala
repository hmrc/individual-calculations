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

package v1.models.response.getCalculation.taxableIncome.detail.ukPropertyNonFhl

import play.api.libs.functional.syntax._
import play.api.libs.json._
import utils.NestedJsonReads
import v1.models.response.getCalculation.taxableIncome.detail.ukPropertyNonFhl.detail._

case class UkPropertyNonFhlLossClaimsDetail(lossesBroughtForward: Option[Seq[UkPropertyNonFhlLossBroughtForward]],
                                            resultOfClaimsApplied: Option[Seq[UkPropertyNonFhlResultOfClaimApplied]],
                                            defaultCarriedForwardLosses: Option[Seq[UkPropertyNonFhlDefaultCarriedForwardLoss]],
                                            claimsNotApplied: Option[Seq[UkPropertyNonFhlClaimNotApplied]])

object UkPropertyNonFhlLossClaimsDetail extends NestedJsonReads {
  val empty: UkPropertyNonFhlLossClaimsDetail = UkPropertyNonFhlLossClaimsDetail(None, None, None, None)

  implicit val reads: Reads[UkPropertyNonFhlLossClaimsDetail] = {
    val commonJsPath: JsPath = JsPath \ "calculation" \ "lossesAndClaims"
    (
      (JsPath \ "inputs" \ "lossesBroughtForward").readIncomeSourceTypeSeq[UkPropertyNonFhlLossBroughtForward](incomeSourceType = "02") and
        (commonJsPath \ "resultOfClaimsApplied").readIncomeSourceTypeSeq[UkPropertyNonFhlResultOfClaimApplied](incomeSourceType = "02") and
        (commonJsPath \ "defaultCarriedForwardLosses").readIncomeSourceTypeSeq[UkPropertyNonFhlDefaultCarriedForwardLoss](incomeSourceType = "02") and
        (commonJsPath \ "claimsNotApplied").readIncomeSourceTypeSeq[UkPropertyNonFhlClaimNotApplied](incomeSourceType = "02")
    )(UkPropertyNonFhlLossClaimsDetail.apply _)
  }

  implicit val writes: OWrites[UkPropertyNonFhlLossClaimsDetail] = Json.writes[UkPropertyNonFhlLossClaimsDetail]
}
