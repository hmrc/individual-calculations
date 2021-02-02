/*
 * Copyright 2021 HM Revenue & Customs
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

package v2.models.response.getCalculation.taxableIncome.detail.payPensionsProfit.ukPropertyFhl

import play.api.libs.functional.syntax._
import play.api.libs.json._
import utils.NestedJsonReads
import v2.models.response.getCalculation.taxableIncome.detail.payPensionsProfit.ukPropertyFhl.detail._

case class UkPropertyFhlLossClaimsDetail(lossesBroughtForward: Option[Seq[UkPropertyFhlLossBroughtForward]],
                                         resultOfClaimsApplied: Option[Seq[UkPropertyFhlResultOfClaimApplied]],
                                         defaultCarriedForwardLosses: Option[Seq[UkPropertyFhlDefaultCarriedForwardLoss]])

object UkPropertyFhlLossClaimsDetail extends NestedJsonReads {
  val empty: UkPropertyFhlLossClaimsDetail = UkPropertyFhlLossClaimsDetail(None, None, None)

  implicit val reads: Reads[UkPropertyFhlLossClaimsDetail] = {
    val commonJsPath: JsPath = JsPath \ "calculation" \ "lossesAndClaims"
    (
      (JsPath \ "inputs" \ "lossesBroughtForward").readIncomeSourceTypeSeq[UkPropertyFhlLossBroughtForward](incomeSourceType = "04") and
        (commonJsPath \ "resultOfClaimsApplied").readIncomeSourceTypeSeq[UkPropertyFhlResultOfClaimApplied](incomeSourceType = "04") and
        (commonJsPath \ "defaultCarriedForwardLosses").readIncomeSourceTypeSeq[UkPropertyFhlDefaultCarriedForwardLoss](incomeSourceType = "04")
    )(UkPropertyFhlLossClaimsDetail.apply _)
  }

  implicit val writes: OWrites[UkPropertyFhlLossClaimsDetail] = Json.writes[UkPropertyFhlLossClaimsDetail]
}
