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
import v1.models.response.getCalculation.taxableIncome.detail.payPensionsProfit.PropertyBsas

case class ForeignProperty(totalIncome: Option[BigDecimal],
                           totalExpenses: Option[BigDecimal],
                           netProfit: Option[BigDecimal],
                           netLoss: Option[BigDecimal],
                           totalAdditions: Option[BigDecimal],
                           totalDeductions: Option[BigDecimal],
                           accountingAdjustments: Option[BigDecimal],
                           adjustedIncomeTaxLoss: Option[BigInt],
                           taxableProfit: Option[BigInt],
                           taxableProfitAfterIncomeTaxLossesDeduction: Option[BigInt],
                           lossClaimsSummary: Option[ForeignPropertyLossClaimsSummary],
                           lossClaimsDetail: Option[ForeignPropertyLossClaimsDetail],
                           bsas: Option[PropertyBsas])

object ForeignProperty extends NestedJsonReads {

  private implicit val topLevelReads: Reads[ForeignProperty] = (
    (JsPath \ "totalIncome").readNullable[BigDecimal] and
      (JsPath \ "totalExpenses").readNullable[BigDecimal] and
      (JsPath \ "netProfit").readNullable[BigDecimal] and
      (JsPath \ "netLoss").readNullable[BigDecimal] and
      (JsPath \ "totalAdditions").readNullable[BigDecimal] and
      (JsPath \ "totalDeductions").readNullable[BigDecimal] and
      (JsPath \ "accountingAdjustments").readNullable[BigDecimal] and
      (JsPath \ "adjustedIncomeTaxLoss").readNullable[BigInt] and
      (JsPath \ "taxableProfit").readNullable[BigInt] and
      (JsPath \ "taxableProfitAfterIncomeTaxLossesDeduction").readNullable[BigInt] and
      JsPath.readNullable[ForeignPropertyLossClaimsSummary].mapEmptyModelToNone(ForeignPropertyLossClaimsSummary.empty) and
      Reads.pure(None) and Reads.pure(None)
  )(ForeignProperty.apply _)

  val empty: ForeignProperty = ForeignProperty(None, None, None, None, None, None, None, None, None, None, None, None, None)

  private def readsApply(topLevelElementsOpt: Option[ForeignProperty],
                         lossClaimsDetail: Option[ForeignPropertyLossClaimsDetail],
                         bsas: Option[PropertyBsas]): ForeignProperty =
    topLevelElementsOpt
      .getOrElse(ForeignProperty.empty)
      .copy(lossClaimsDetail = lossClaimsDetail, bsas = bsas)

  implicit val reads: Reads[ForeignProperty] = (
    (JsPath \ "calculation" \ "businessProfitAndLoss").readIncomeSourceTypeItem[ForeignProperty](incomeSourceType = "15")(topLevelReads) and
      JsPath.readNullable[ForeignPropertyLossClaimsDetail].mapEmptyModelToNone(ForeignPropertyLossClaimsDetail.empty) and
      (JsPath \ "inputs" \ "annualAdjustments").readIncomeSourceTypeItem[PropertyBsas](incomeSourceType = "15")
  )(ForeignProperty.readsApply _)

  implicit val writes: OWrites[ForeignProperty] = Json.writes[ForeignProperty]
}
