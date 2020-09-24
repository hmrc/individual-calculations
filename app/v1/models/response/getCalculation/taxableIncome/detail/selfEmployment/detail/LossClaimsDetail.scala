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

package v1.models.response.getCalculation.taxableIncome.detail.selfEmployment.detail

import play.api.libs.functional.syntax._
import play.api.libs.json._
import sangria.macros.derive.{ObjectTypeName, deriveObjectType}
import sangria.schema.ObjectType
import utils.NestedJsonReads
case class LossClaimsDetail(lossesBroughtForward: Option[Seq[LossBroughtForward]],
                            resultOfClaimsApplied: Option[Seq[ResultOfClaimApplied]],
                            unclaimedLosses: Option[Seq[UnclaimedLoss]],
                            carriedForwardLosses: Option[Seq[CarriedForwardLoss]],
                            claimsNotApplied: Option[Seq[ClaimNotApplied]]) {

  def filterById(id: String): Option[LossClaimsDetail] = {

    val lossesBroughtForward = this.lossesBroughtForward.getOrElse(Seq.empty).filter(lbf => lbf.incomeSourceId == id)
    val resultOfClaimsApplied = this.resultOfClaimsApplied.getOrElse(Seq.empty).filter(rca => rca.incomeSourceId == id)
    val unclaimedLosses = this.unclaimedLosses.getOrElse(Seq.empty).filter(ucl => ucl.incomeSourceId == id)
    val carriedForwardLosses = this.carriedForwardLosses.getOrElse(Seq.empty).filter(cfl => cfl.incomeSourceId == id)
    val claimsNotApplied = this.claimsNotApplied.getOrElse(Seq.empty).filter(cna => cna.incomeSourceId == id)

    val filteredDetail: LossClaimsDetail = LossClaimsDetail(
      LossClaimsDetail.toNoneIfEmpty(lossesBroughtForward),
      LossClaimsDetail.toNoneIfEmpty(resultOfClaimsApplied),
      LossClaimsDetail.toNoneIfEmpty(unclaimedLosses),
      LossClaimsDetail.toNoneIfEmpty(carriedForwardLosses),
      LossClaimsDetail.toNoneIfEmpty(claimsNotApplied)
    )

    if (filteredDetail == LossClaimsDetail.empty) None else Some(filteredDetail)
  }

}

object LossClaimsDetail extends NestedJsonReads {
  val empty = LossClaimsDetail(None, None, None, None, None)

  def toNoneIfEmpty[A](seq: Seq[A]): Option[Seq[A]] = if (seq.nonEmpty) Some(seq) else None

  implicit val writes: OWrites[LossClaimsDetail] = Json.writes[LossClaimsDetail]

  implicit val reads: Reads[LossClaimsDetail] = ((JsPath \ "inputs" \ "lossesBroughtForward").readNestedNullable(filteredArrayReads[LossBroughtForward]("incomeSourceType", "01")).mapEmptySeqToNone and
    (JsPath \ "calculation" \ "lossesAndClaims" \ "resultOfClaimsApplied").readNestedNullable(filteredArrayReads[ResultOfClaimApplied]("incomeSourceType", "01")).mapEmptySeqToNone and
    (JsPath \ "calculation" \ "lossesAndClaims" \ "unclaimedLosses").readNestedNullable(filteredArrayReads[UnclaimedLoss]("incomeSourceType", "01")).mapEmptySeqToNone and
    (JsPath \ "calculation" \ "lossesAndClaims" \ "carriedForwardLosses").readNestedNullable(filteredArrayReads[CarriedForwardLoss]("incomeSourceType", "01")).mapEmptySeqToNone and
    (JsPath \ "calculation" \ "lossesAndClaims" \ "claimsNotApplied").readNestedNullable(filteredArrayReads[ClaimNotApplied]("incomeSourceType", "01")).mapEmptySeqToNone) (LossClaimsDetail.apply _)

  implicit def gqlType: ObjectType[Unit, LossClaimsDetail] =
    deriveObjectType[Unit, LossClaimsDetail](ObjectTypeName("SelfEmploymentLossClaimsDetail"))
}
