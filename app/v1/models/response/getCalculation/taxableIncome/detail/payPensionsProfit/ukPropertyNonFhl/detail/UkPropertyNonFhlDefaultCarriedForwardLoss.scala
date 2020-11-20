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

package v1.models.response.getCalculation.taxableIncome.detail.payPensionsProfit.ukPropertyNonFhl.detail

import play.api.libs.functional.syntax._
import play.api.libs.json.{JsPath, Json, OWrites, Reads}
import v1.models.request.DesTaxYear
import sangria.macros.derive._
import sangria.schema.ObjectType

case class UkPropertyNonFhlDefaultCarriedForwardLoss(taxYearLossIncurred: String, currentLossValue: BigInt)

object UkPropertyNonFhlDefaultCarriedForwardLoss {
  implicit val reads: Reads[UkPropertyNonFhlDefaultCarriedForwardLoss] = (
    (JsPath \ "taxYearLossIncurred").read[Int].map(DesTaxYear.fromDesIntToString) and
      (JsPath \ "currentLossValue").read[BigInt]
  )(UkPropertyNonFhlDefaultCarriedForwardLoss.apply _)

  implicit val writes: OWrites[UkPropertyNonFhlDefaultCarriedForwardLoss] = Json.writes[UkPropertyNonFhlDefaultCarriedForwardLoss]

  implicit def gqlType: ObjectType[Unit, UkPropertyNonFhlDefaultCarriedForwardLoss] =
    deriveObjectType[Unit, UkPropertyNonFhlDefaultCarriedForwardLoss]()
}
