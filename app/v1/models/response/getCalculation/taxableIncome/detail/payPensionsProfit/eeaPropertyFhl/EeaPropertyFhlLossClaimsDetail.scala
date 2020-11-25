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

package v1.models.response.getCalculation.taxableIncome.detail.payPensionsProfit.eeaPropertyFhl

import play.api.libs.functional.syntax._
import play.api.libs.json._
import utils.NestedJsonReads
import v1.models.response.getCalculation.taxableIncome.detail.payPensionsProfit.eeaPropertyFhl.detail._

case class EeaPropertyFhlLossClaimsDetail(lossesBroughtForward: Option[Seq[EeaPropertyFhlLossBroughtForward]],
                                          resultOfClaimsApplied: Option[Seq[EeaPropertyFhlResultOfClaimApplied]],
                                          defaultCarriedForwardLosses: Option[Seq[EeaPropertyFhlDefaultCarriedForwardLoss]])

object EeaPropertyFhlLossClaimsDetail extends NestedJsonReads {
  val empty: EeaPropertyFhlLossClaimsDetail = EeaPropertyFhlLossClaimsDetail(None, None, None)

  implicit val reads: Reads[EeaPropertyFhlLossClaimsDetail] = {
    val commonJsPath: JsPath = JsPath \ "calculation" \ "lossesAndClaims"
    (
      (JsPath \ "inputs" \ "lossesBroughtForward").readIncomeSourceTypeSeq[EeaPropertyFhlLossBroughtForward](incomeSourceType = "03") and
        (commonJsPath \ "resultOfClaimsApplied").readIncomeSourceTypeSeq[EeaPropertyFhlResultOfClaimApplied](incomeSourceType = "03") and
        (commonJsPath \ "defaultCarriedForwardLosses").readIncomeSourceTypeSeq[EeaPropertyFhlDefaultCarriedForwardLoss](incomeSourceType = "03")
    )(EeaPropertyFhlLossClaimsDetail.apply _)
  }

  implicit val writes: OWrites[EeaPropertyFhlLossClaimsDetail] = Json.writes[EeaPropertyFhlLossClaimsDetail]
}
