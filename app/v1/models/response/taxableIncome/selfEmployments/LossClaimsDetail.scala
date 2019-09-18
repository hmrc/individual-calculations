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
import utils.NestedJsonReads

case class LossClaimsDetail(
                             lossesBroughtForward: Option[Seq[LossBroughtForward]],
                             resultOfClaimsApplied: Option[Seq[ResultOfClaimApplied]],
                             unclaimedLosses: Option[Seq[UnclaimedLoss]],
                             carriedForwardLosses: Option[Seq[CarriedForwardLoss]],
                             claimsNotApplied: Option[Seq[ClaimNotApplied]]) {
                               val isEmpty: Boolean = this == LossClaimsDetail.emptyLossClaimsDetail
                             }

object LossClaimsDetail extends NestedJsonReads {

  val emptyLossClaimsDetail: LossClaimsDetail = LossClaimsDetail(None,None,None,None,None)

  implicit val writes: OWrites[LossClaimsDetail] = Json.writes[LossClaimsDetail]

  // TODO either extend each of the array objects with incomeSourceId and incomeSourceType and filter in here
  // (can refactor out the filtering function to a single place) or
  // get the reads to peak at each array item ???

    def conditionalReads[A](path: JsPath)(implicit reads: Reads[A]): Reads[Option[Seq[A]]] = {
      path.readNestedNullable[Seq[JsValue]].map(self => toType[A](self))
    }

    def toType[A](seq: Option[Seq[JsValue]])(implicit reads: Reads[A]): Option[Seq[A]] = {
      val mappedSequence = seq.getOrElse(Seq.empty).flatMap(js => filterByIncomeSourceType(js).asOpt[A])
      if (mappedSequence.isEmpty) None else Some(mappedSequence)
    }

    def filterByIncomeSourceType(js: JsValue): JsValue =  js.asOpt[FilterWrapper] match {
      case Some(FilterWrapper(incomeSourceType, _)) if incomeSourceType == "01" => js
      case _ => Json.toJson(None)
    }

  case class FilterWrapper(incomeSourceType: String, incomeSourceId: String)

  object FilterWrapper{
    implicit val formats: OFormat[FilterWrapper] = Json.format[FilterWrapper]
  }

  implicit val reads: Reads[LossClaimsDetail] = (
    conditionalReads[LossBroughtForward](JsPath \ "inputs" \ "lossesBroughtForward") and
      conditionalReads[ResultOfClaimApplied](JsPath \ "calculation" \ "lossesAndClaims" \ "resultOfClaimsApplied") and
      conditionalReads[UnclaimedLoss](JsPath \ "calculation" \ "lossesAndClaims" \ "unclaimedLosses") and
      conditionalReads[CarriedForwardLoss](JsPath \ "calculation" \ "lossesAndClaims" \ "carriedForwardLosses") and
      conditionalReads[ClaimNotApplied](JsPath \ "calculation" \ "lossesAndClaims" \ "claimsNotApplied"))(LossClaimsDetail.apply _)

}
