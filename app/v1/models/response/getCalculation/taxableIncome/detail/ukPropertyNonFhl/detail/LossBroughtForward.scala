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

package v1.models.response.getCalculation.taxableIncome.detail.ukPropertyNonFhl.detail

import play.api.libs.functional.syntax._
import play.api.libs.json.{JsPath, Json, OWrites, Reads}
import sangria.macros.derive.{ObjectTypeName, deriveObjectType}
import sangria.schema.ObjectType
import v1.models.request.DesTaxYear
case class LossBroughtForward(taxYearLossIncurred: String,
                              currentLossValue: BigInt,
                              mtdLoss: Boolean)

object LossBroughtForward {
  implicit val writes: OWrites[LossBroughtForward] = Json.writes[LossBroughtForward]

  implicit val reads: Reads[LossBroughtForward] = (
    (JsPath \ "taxYearLossIncurred").read[Int].map(DesTaxYear.fromDesIntToString) and
      (JsPath \ "currentLossValue").read[BigInt] and
      (JsPath \ "mtdLoss").read[Boolean]
    ) (LossBroughtForward.apply _)

  implicit def gqlType: ObjectType[Unit, LossBroughtForward] =
    deriveObjectType[Unit, LossBroughtForward](ObjectTypeName("UkPropertyNonFhlLossBroughtForward"))
}
