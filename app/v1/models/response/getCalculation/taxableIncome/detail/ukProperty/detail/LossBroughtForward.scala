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

package v1.models.response.getCalculation.taxableIncome.detail.ukProperty.detail

import play.api.libs.functional.syntax._
import play.api.libs.json._
import v1.models.request.DesTaxYear

case class LossBroughtForward(
                              taxYearLossIncurred: String,
                              currentLossValue: BigDecimal,
                              mtdLoss: Boolean
                             )


object LossBroughtForward {
  implicit val writes: Writes[LossBroughtForward] = Json.writes[LossBroughtForward]

  implicit val reads: Reads[LossBroughtForward] = (
    (__ \ "taxYearLossIncurred").read[Int].map(DesTaxYear.fromDesIntToString) and
      (__ \ "currentLossValue").read[BigDecimal] and
      (__ \ "mtdLoss").read[Boolean] and
      (__ \ "incomeSourceType").read[String]
    )((taxYearLossIncurred, currentLossValue, mtdLoss, incomeSourceType) => {
      incomeSourceType match {
        case "04" => LossBroughtForward(taxYearLossIncurred, currentLossValue, mtdLoss)
        case _ => null
      }
    })
}