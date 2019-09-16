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
import play.api.libs.json.{ Json, Reads, Writes, __ }
import utils.NestedJsonReads

case class LossClaimsDetail(
                             lossesBroughtForward: Option[Seq[LossBroughtForward]] = None,
                             resultOfClaimsApplied: Option[Seq[ResultOfClaimApplied]] = None,
                             unclaimedLosses: Option[Seq[UnclaimedLoss]] = None,
                             carriedForwardLosses: Option[Seq[CarriedForwardLoss]] = None,
                             claimsNotApplied: Option[Seq[ClaimNotApplied]] = None
)

object LossClaimsDetail extends NestedJsonReads {

  implicit val writes: Writes[LossClaimsDetail] = Json.writes[LossClaimsDetail]

  // TODO either extend each of the array objects with incomeSourceId and incomeSourceType and filter in here
  // (can refactor out the filtering function to a single place) or
  // get the reads to peak at each array item ???

  implicit val reads: Reads[LossClaimsDetail] = ???
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
