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

package v1.models.response.getCalculation.taxableIncome.detail.payPensionsProfit.selfEmployment

import play.api.libs.functional.syntax._
import play.api.libs.json._
import utils.NestedJsonReads
import v1.models.response.getCalculation.taxableIncome.detail.payPensionsProfit.selfEmployment.detail._

case class SelfEmploymentLossClaimsDetail(lossesBroughtForward: Option[Seq[SelfEmploymentLossBroughtForward]],
                                          resultOfClaimsApplied: Option[Seq[SelfEmploymentResultOfClaimApplied]],
                                          unclaimedLosses: Option[Seq[SelfEmploymentUnclaimedLoss]],
                                          carriedForwardLosses: Option[Seq[SelfEmploymentCarriedForwardLoss]],
                                          claimsNotApplied: Option[Seq[SelfEmploymentClaimNotApplied]]) {

  def filterById(id: String): Option[SelfEmploymentLossClaimsDetail] = {

    def doFilter[Item <: SelfEmploymentLossClaimsDetailItem](optionalItemSeq: Option[Seq[Item]]): Option[Seq[Item]] =
      optionalItemSeq.map(
        _.filter(item => item.incomeSourceId == id)
      ) match {
        case Some(vals) if vals.nonEmpty => Some(vals)
        case _                           => None
      }

    val filteredDetail: SelfEmploymentLossClaimsDetail = SelfEmploymentLossClaimsDetail(
      lossesBroughtForward = doFilter(lossesBroughtForward),
      resultOfClaimsApplied = doFilter(resultOfClaimsApplied),
      unclaimedLosses = doFilter(unclaimedLosses),
      carriedForwardLosses = doFilter(carriedForwardLosses),
      claimsNotApplied = doFilter(claimsNotApplied)
    )

    if (filteredDetail == SelfEmploymentLossClaimsDetail.empty) None else Some(filteredDetail)
  }
}

object SelfEmploymentLossClaimsDetail extends NestedJsonReads {
  val empty: SelfEmploymentLossClaimsDetail = SelfEmploymentLossClaimsDetail(None, None, None, None, None)

  implicit val reads: Reads[SelfEmploymentLossClaimsDetail] = {
    val commonJsPath: JsPath = JsPath \ "calculation" \ "lossesAndClaims"
    (
      (JsPath \ "inputs" \ "lossesBroughtForward").readIncomeSourceTypeSeq[SelfEmploymentLossBroughtForward](incomeSourceType = "01") and
        (commonJsPath \ "resultOfClaimsApplied").readIncomeSourceTypeSeq[SelfEmploymentResultOfClaimApplied](incomeSourceType = "01") and
        (commonJsPath \ "unclaimedLosses").readIncomeSourceTypeSeq[SelfEmploymentUnclaimedLoss](incomeSourceType = "01") and
        (commonJsPath \ "carriedForwardLosses").readIncomeSourceTypeSeq[SelfEmploymentCarriedForwardLoss](incomeSourceType = "01") and
        (commonJsPath \ "claimsNotApplied").readIncomeSourceTypeSeq[SelfEmploymentClaimNotApplied](incomeSourceType = "01")
    )(SelfEmploymentLossClaimsDetail.apply _)
  }

  implicit val writes: OWrites[SelfEmploymentLossClaimsDetail] = Json.writes[SelfEmploymentLossClaimsDetail]
}
