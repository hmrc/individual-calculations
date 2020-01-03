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

package v1.models.response.getCalculation.taxableIncome.detail.ukPropertyNonFhl.detail

import play.api.libs.functional.syntax._
import play.api.libs.json._
import utils.NestedJsonReads

case class LossClaimsDetail(lossesBroughtForward: Option[Seq[LossBroughtForward]],
                            resultOfClaimsApplied: Option[Seq[ResultOfClaimApplied]],
                            defaultCarriedForwardLosses: Option[Seq[DefaultCarriedForwardLoss]],
                            claimsNotApplied: Option[Seq[ClaimNotApplied]])

object LossClaimsDetail extends NestedJsonReads {
  val empty = LossClaimsDetail(None, None, None, None)

  implicit val writes: OWrites[LossClaimsDetail] = Json.writes[LossClaimsDetail]

  implicit val reads: Reads[LossClaimsDetail] = (
    (JsPath \ "inputs" \ "lossesBroughtForward")
      .readNestedNullable[Seq[LossBroughtForward]](filteredArrayReads("incomeSourceType", "02")).mapEmptySeqToNone and
      (JsPath \ "calculation" \ "lossesAndClaims" \ "resultOfClaimsApplied")
        .readNestedNullable[Seq[ResultOfClaimApplied]](filteredArrayReads("incomeSourceType", "02")).mapEmptySeqToNone and
      (JsPath \ "calculation" \ "lossesAndClaims" \ "defaultCarriedForwardLosses")
        .readNestedNullable[Seq[DefaultCarriedForwardLoss]](filteredArrayReads("incomeSourceType", "02")).mapEmptySeqToNone and
      (JsPath \ "calculation" \ "lossesAndClaims" \ "claimsNotApplied")
        .readNestedNullable[Seq[ClaimNotApplied]](filteredArrayReads("incomeSourceType", "02")).mapEmptySeqToNone
  )(LossClaimsDetail.apply _)
}
