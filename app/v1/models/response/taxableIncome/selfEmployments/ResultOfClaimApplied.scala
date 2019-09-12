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
import play.api.libs.json.{Json, Reads, Writes, __}
import v1.models.des.{LossType, ReliefClaimed}
import v1.models.domain.TypeOfClaim
import v1.models.request.DesTaxYear

case class ResultOfClaimApplied(
    claimId: Option[String],
    taxYearClaimMade: String,
    claimType: TypeOfClaim,
    mtdLoss: Option[Boolean], // FIXME really?
    taxYearLossIncurred: String,
    lossAmountUsed: BigDecimal,
    remainingLossValue: BigDecimal,
    lossType: LossType
)


object ResultOfClaimApplied {

  implicit val writes: Writes[ResultOfClaimApplied] = Json.writes[ResultOfClaimApplied]

  implicit val reads: Reads[ResultOfClaimApplied] = (
    (__ \ "claimId").readNullable[String] and
      (__ \ "taxYearClaimMade").read[Int].map(DesTaxYear.fromDesIntToString) and
      (__ \ "claimType").read[ReliefClaimed].map(des => des.toTypeOfClaim) and
      (__ \ "mtdLoss").readNullable[Boolean] and
      (__ \ "taxYearLossIncurred").read[Int].map(DesTaxYear.fromDesIntToString) and
      (__ \ "lossAmountUsed").read[BigDecimal] and
      (__ \ "remainingLossValue").read[BigDecimal] and
      (__ \ "lossType").read[LossType]
    )(ResultOfClaimApplied.apply _)

}