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

package v1.models.response.getCalculation.taxableIncome.detail.payPensionsProfit.selfEmployment.detail

import play.api.libs.functional.syntax._
import play.api.libs.json._
import sangria.macros.derive.{ExcludeFields, ReplaceField, deriveObjectType}
import sangria.schema.{Field, ObjectType, StringType}
import v1.models.des.{LossType, ReliefClaimed}
import v1.models.domain.TypeOfClaim
import v1.models.request.DesTaxYear

case class SelfEmploymentResultOfClaimApplied(claimId: Option[String],
                                              taxYearClaimMade: String,
                                              claimType: TypeOfClaim,
                                              mtdLoss: Boolean,
                                              taxYearLossIncurred: String,
                                              lossAmountUsed: BigInt,
                                              remainingLossValue: BigInt,
                                              lossType: LossType,
                                              incomeSourceId: String) extends SelfEmploymentLossClaimsDetailItem

object SelfEmploymentResultOfClaimApplied {
  implicit val reads: Reads[SelfEmploymentResultOfClaimApplied] = (
    (JsPath \ "claimId").readNullable[String] and
      (JsPath \ "taxYearClaimMade").read[Int].map(DesTaxYear.fromDesIntToString) and
      (JsPath \ "claimType").read[ReliefClaimed].map(des => des.toTypeOfClaim) and
      (JsPath \ "mtdLoss").readWithDefault(defaultValue = true) and
      (JsPath \ "taxYearLossIncurred").read[Int].map(DesTaxYear.fromDesIntToString) and
      (JsPath \ "lossAmountUsed").read[BigInt] and
      (JsPath \ "remainingLossValue").read[BigInt] and
      (JsPath \ "lossType").read[LossType] and
      (JsPath \ "incomeSourceId").read[String]
    )(SelfEmploymentResultOfClaimApplied.apply _)

  implicit val writes: OWrites[SelfEmploymentResultOfClaimApplied] = (resultOfClaimApplied: SelfEmploymentResultOfClaimApplied) =>
    Json.toJsObject(resultOfClaimApplied)(Json.writes[SelfEmploymentResultOfClaimApplied]) - "incomeSourceId"

  implicit def gqlType: ObjectType[Unit, SelfEmploymentResultOfClaimApplied] =
    deriveObjectType[Unit, SelfEmploymentResultOfClaimApplied](
      ReplaceField("claimType", Field("claimType", StringType, resolve = _.value.claimType.toString)),
      ReplaceField("lossType", Field("lossType", StringType, resolve = _.value.lossType.toString.toLowerCase)),
      ExcludeFields("incomeSourceId")
    )
}
