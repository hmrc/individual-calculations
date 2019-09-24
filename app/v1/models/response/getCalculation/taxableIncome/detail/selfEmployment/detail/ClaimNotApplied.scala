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
import v1.models.des.ReliefClaimed
import v1.models.domain.TypeOfClaim
import v1.models.request.DesTaxYear

case class ClaimNotApplied(
    claimId: String,
    taxYearClaimMade: String,
    claimType: TypeOfClaim,
    incomeSourceId: String
)

object ClaimNotApplied {

  implicit val writes: OWrites[ClaimNotApplied] = new OWrites[ClaimNotApplied] {

    def writes(claimNotApplied: ClaimNotApplied): JsObject = Json.obj(
      "claimId"          -> claimNotApplied.claimId,
      "taxYearClaimMade" -> claimNotApplied.taxYearClaimMade,
      "claimType"        -> claimNotApplied.claimType
    )
  }

  implicit val reads: Reads[ClaimNotApplied] = (
    (JsPath \ "claimId").read[String] and
      (JsPath \ "taxYearClaimMade").read[Int].map(DesTaxYear.fromDesIntToString) and
      (JsPath \ "claimType").read[ReliefClaimed].map(des => des.toTypeOfClaim) and
      (JsPath \ "incomeSourceId").read[String]
  )(ClaimNotApplied.apply _)

}