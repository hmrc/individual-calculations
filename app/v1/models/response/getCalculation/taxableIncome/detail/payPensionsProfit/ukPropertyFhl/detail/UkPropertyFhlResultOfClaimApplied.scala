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

package v1.models.response.getCalculation.taxableIncome.detail.payPensionsProfit.ukPropertyFhl.detail

import play.api.libs.functional.syntax._
import play.api.libs.json.{JsPath, Json, OWrites, Reads}
import v1.models.des.ReliefClaimed
import v1.models.domain.TypeOfClaim
import v1.models.request.DesTaxYear
import sangria.macros.derive._
import sangria.schema.{Field, ObjectType, StringType}

case class UkPropertyFhlResultOfClaimApplied(claimId: Option[String],
                                             taxYearClaimMade: String,
                                             claimType: TypeOfClaim,
                                             mtdLoss: Boolean,
                                             taxYearLossIncurred: String,
                                             lossAmountUsed: BigInt,
                                             remainingLossValue: BigInt)

object UkPropertyFhlResultOfClaimApplied {
  implicit val reads: Reads[UkPropertyFhlResultOfClaimApplied] = (
    (JsPath \ "claimId").readNullable[String] and
      (JsPath \ "taxYearClaimMade").read[Int].map(DesTaxYear.fromDesIntToString) and
      (JsPath \ "claimType").read[ReliefClaimed].map(des => des.toTypeOfClaim) and
      (JsPath \ "mtdLoss").read[Boolean] and
      (JsPath \ "taxYearLossIncurred").read[Int].map(DesTaxYear.fromDesIntToString) and
      (JsPath \ "lossAmountUsed").read[BigInt] and
      (JsPath \ "remainingLossValue").read[BigInt]
  )(UkPropertyFhlResultOfClaimApplied.apply _)

  implicit val writes: OWrites[UkPropertyFhlResultOfClaimApplied] = Json.writes[UkPropertyFhlResultOfClaimApplied]

  implicit def gqlType: ObjectType[Unit, UkPropertyFhlResultOfClaimApplied] =
    deriveObjectType[Unit, UkPropertyFhlResultOfClaimApplied](
      ReplaceField("claimType", Field("claimType", StringType, resolve = _.value.claimType.toString))
    )
}
