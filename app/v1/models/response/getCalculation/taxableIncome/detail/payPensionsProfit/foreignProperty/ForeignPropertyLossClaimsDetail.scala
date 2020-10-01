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

package v1.models.response.getCalculation.taxableIncome.detail.payPensionsProfit.foreignProperty

import play.api.libs.functional.syntax._
import play.api.libs.json._
import utils.NestedJsonReads
import v1.models.response.getCalculation.taxableIncome.detail.payPensionsProfit.foreignProperty.detail._

case class ForeignPropertyLossClaimsDetail(lossesBroughtForward: Option[Seq[ForeignPropertyLossBroughtForward]],
                                           resultOfClaimsApplied: Option[Seq[ForeignPropertyResultOfClaimApplied]],
                                           defaultCarriedForwardLosses: Option[Seq[ForeignPropertyDefaultCarriedForwardLoss]],
                                           claimsNotApplied: Option[Seq[ForeignPropertyClaimNotApplied]])

object ForeignPropertyLossClaimsDetail extends NestedJsonReads {
  val empty: ForeignPropertyLossClaimsDetail = ForeignPropertyLossClaimsDetail(None, None, None, None)

  implicit val reads: Reads[ForeignPropertyLossClaimsDetail] = {
    val commonJsPath: JsPath = JsPath \ "calculation" \ "lossesAndClaims"
    (
      (JsPath \ "inputs" \ "lossesBroughtForward").readIncomeSourceTypeSeq[ForeignPropertyLossBroughtForward](incomeSourceType = "15") and
        (commonJsPath \ "resultOfClaimsApplied").readIncomeSourceTypeSeq[ForeignPropertyResultOfClaimApplied](incomeSourceType = "15") and
        (commonJsPath \ "defaultCarriedForwardLosses").readIncomeSourceTypeSeq[ForeignPropertyDefaultCarriedForwardLoss](incomeSourceType = "15") and
        (commonJsPath \ "claimsNotApplied").readIncomeSourceTypeSeq[ForeignPropertyClaimNotApplied](incomeSourceType = "15")
    )(ForeignPropertyLossClaimsDetail.apply _)
  }

  implicit val writes: OWrites[ForeignPropertyLossClaimsDetail] = Json.writes[ForeignPropertyLossClaimsDetail]
}
