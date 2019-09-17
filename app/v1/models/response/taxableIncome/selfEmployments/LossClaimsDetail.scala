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
import play.api.libs.json.{JsPath, JsValue, Json, OWrites, Reads, Writes, __}
import utils.NestedJsonReads

case class LossClaimsDetail(
                             lossesBroughtForward: Option[Seq[LossBroughtForward]],
                             resultOfClaimsApplied: Option[Seq[ResultOfClaimApplied]],
                             unclaimedLosses: Option[Seq[UnclaimedLoss]],
                             carriedForwardLosses: Option[Seq[CarriedForwardLoss]],
                             claimsNotApplied: Option[Seq[ClaimNotApplied]]
)

object LossClaimsDetail extends NestedJsonReads {

  implicit val writes: OWrites[LossClaimsDetail] = Json.writes[LossClaimsDetail]

  // TODO either extend each of the array objects with incomeSourceId and incomeSourceType and filter in here
  // (can refactor out the filtering function to a single place) or
  // get the reads to peak at each array item ???

  implicit val reads: Reads[LossClaimsDetail] = (
    (JsPath \ "inputs" \ "lossesBroughtForward").readNestedNullable[Seq[JsValue]].map(vv => vv.get.map(v=>v.as[LossBroughtForward])).orElse(Reads.pure(None)) and
      (JsPath \ "calculation" \ "lossesAndClaims" \ "resultOfClaimsApplied").readNestedNullable[Seq[ResultOfClaimApplied]] and
      (JsPath \ "calculation" \ "lossesAndClaims" \ "unclaimedLosses").readNestedNullable[Seq[UnclaimedLoss]] and
      (JsPath \ "calculation" \ "lossesAndClaims" \ "carriedForwardLosses").readNestedNullable[Seq[CarriedForwardLoss]] and
      (JsPath \ "calculation" \ "lossesAndClaims" \ "claimsNotApplied").readNestedNullable[Seq[ClaimNotApplied]])(LossClaimsDetail.apply _)

//  implicit def reads(selfEmploymentId: String): Reads[LossClaimsDetail] = (
//    Reads.pure(Option.empty[Seq[LossBroughtForward]]) and
//      Reads.pure(Option.empty[Seq[ResultOfClaimApplied]]) and
//      Reads.pure(Option.empty[Seq[UnclaimedLoss]]) and
//      (__ \ "calculation" \ "lossesAndClaims" \ "carriedForwardLosses").readNestedNullable[Seq[CarriedForwardLoss]]
//        .filter{
//        xs => xs.map(ys=> ys.filter( ??? ))
//      } and
//      Reads.pure(Option.empty[Seq[ClaimNotApplied]])
//  )(LossClaimsDetail.apply _)
}
