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

package v1.models.response.getCalculation.taxableIncome.detail.selfEmployment.detail

import play.api.libs.functional.syntax._
import play.api.libs.json._
import utils.NestedJsonReads

case class LossClaimsDetail(lossesBroughtForward: Option[Seq[LossBroughtForward]],
                            resultOfClaimsApplied: Option[Seq[ResultOfClaimApplied]],
                            unclaimedLosses: Option[Seq[UnclaimedLoss]],
                            carriedForwardLosses: Option[Seq[CarriedForwardLoss]],
                            claimsNotApplied: Option[Seq[ClaimNotApplied]]) {

  val isEmpty: Boolean = this == LossClaimsDetail.emptyLossClaimsDetail

  def filterById(id: String): Option[LossClaimsDetail] = {

    val lossesBroughtForward  = this.lossesBroughtForward.getOrElse(Seq()).filter(arr => arr.incomeSourceId == id)
    val resultOfClaimsApplied = this.resultOfClaimsApplied.getOrElse(Seq()).filter(arr => arr.incomeSourceId == id)
    val unclaimedLosses       = this.unclaimedLosses.getOrElse(Seq()).filter(arr => arr.incomeSourceId == id)
    val carriedForwardLosses  = this.carriedForwardLosses.getOrElse(Seq()).filter(arr => arr.incomeSourceId == id)
    val claimsNotApplied      = this.claimsNotApplied.getOrElse(Seq()).filter(arr => arr.incomeSourceId == id)

    val tempDetail: LossClaimsDetail = LossClaimsDetail(
      LossClaimsDetail.toNoneIfEmpty(lossesBroughtForward),
      LossClaimsDetail.toNoneIfEmpty(resultOfClaimsApplied),
      LossClaimsDetail.toNoneIfEmpty(unclaimedLosses),
      LossClaimsDetail.toNoneIfEmpty(carriedForwardLosses),
      LossClaimsDetail.toNoneIfEmpty(claimsNotApplied)
    )

    if (!tempDetail.isEmpty) Some(tempDetail) else None
  }

}

object LossClaimsDetail extends NestedJsonReads {
  val emptyLossClaimsDetail: LossClaimsDetail = LossClaimsDetail(None, None, None, None, None)

  def toNoneIfEmpty[A](seq: Seq[A]): Option[Seq[A]] = {
    if (seq.nonEmpty) Some(seq) else None
  }

  implicit val writes: OWrites[LossClaimsDetail] = Json.writes[LossClaimsDetail]

  def conditionalReads[A](path: JsPath)(implicit reads: Reads[A]): Reads[Option[Seq[A]]] = {
    path.readNestedNullable[Seq[JsValue]].map(self => toType[A](self))
  }

  def toType[A](seq: Option[Seq[JsValue]])(implicit reads: Reads[A]): Option[Seq[A]] = {
    val mappedSequence = seq.getOrElse(Seq.empty).flatMap(js => filterByIncomeSourceType(js).asOpt[A])
    if (mappedSequence.isEmpty) None else Some(mappedSequence)
  }

  def filterByIncomeSourceType(js: JsValue): JsValue = js.asOpt[FilterWrapper] match {
    case Some(FilterWrapper(incomeSourceType, _)) if incomeSourceType == "01" => js
    case _                                                                    => Json.toJson(None)
  }

  case class FilterWrapper(incomeSourceType: String, incomeSourceId: String)

  object FilterWrapper {
    implicit val formats: OFormat[FilterWrapper] = Json.format[FilterWrapper]
  }

  implicit val reads: Reads[LossClaimsDetail] = (conditionalReads[LossBroughtForward](JsPath \ "inputs" \ "lossesBroughtForward") and
    conditionalReads[ResultOfClaimApplied](JsPath \ "calculation" \ "lossesAndClaims" \ "resultOfClaimsApplied") and
    conditionalReads[UnclaimedLoss](JsPath \ "calculation" \ "lossesAndClaims" \ "unclaimedLosses") and
    conditionalReads[CarriedForwardLoss](JsPath \ "calculation" \ "lossesAndClaims" \ "carriedForwardLosses") and
    conditionalReads[ClaimNotApplied](JsPath \ "calculation" \ "lossesAndClaims" \ "claimsNotApplied"))(LossClaimsDetail.apply _)

}
