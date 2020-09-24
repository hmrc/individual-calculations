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
import sangria.macros.derive.{ObjectTypeName, ReplaceField, deriveObjectType}
import sangria.schema.{Field, ObjectType, StringType}
import v1.models.des.LossType
import v1.models.request.DesTaxYear
case class LossBroughtForward(
                               lossType: LossType,
                               taxYearLossIncurred: String,
                               currentLossValue: BigInt,
                               mtdLoss: Boolean,
                               incomeSourceId: String
                             )

object LossBroughtForward {

  implicit val writes: OWrites[LossBroughtForward] = (lossBroughtForward: LossBroughtForward) => Json.obj(
    "lossType" -> lossBroughtForward.lossType,
    "taxYearLossIncurred" -> lossBroughtForward.taxYearLossIncurred,
    "currentLossValue" -> lossBroughtForward.currentLossValue,
    "mtdLoss" -> lossBroughtForward.mtdLoss
  )

  implicit val reads: Reads[LossBroughtForward] = (
    (JsPath \ "lossType").read[LossType] and
      (JsPath \ "taxYearLossIncurred").read[Int].map(DesTaxYear.fromDesIntToString) and
      (JsPath \ "currentLossValue").read[BigInt] and
      (JsPath \ "mtdLoss").readWithDefault(true) and
      (JsPath \ "incomeSourceId").read[String]
    ) (LossBroughtForward.apply _)

  implicit def gqlType: ObjectType[Unit, LossBroughtForward] =
    deriveObjectType[Unit, LossBroughtForward](
      ObjectTypeName("SelfEmploymentLossBroughtForward"),
      ReplaceField("lossType", Field("lossType", StringType, resolve = _.value.lossType.toString))
    )
}
