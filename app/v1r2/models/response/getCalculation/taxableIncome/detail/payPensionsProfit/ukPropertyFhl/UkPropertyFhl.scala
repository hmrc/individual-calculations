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

package v1r2.models.response.getCalculation.taxableIncome.detail.payPensionsProfit.ukPropertyFhl

import play.api.libs.functional.syntax._
import play.api.libs.json.Reads._
import play.api.libs.json._
import utils.NestedJsonReads
import v1r2.models.response.getCalculation.taxableIncome.detail.payPensionsProfit.PropertyBsas

case class UkPropertyFhl(totalIncome: Option[BigDecimal],
                         totalExpenses: Option[BigDecimal],
                         netProfit: Option[BigDecimal],
                         netLoss: Option[BigDecimal],
                         totalAdditions: Option[BigDecimal],
                         totalDeductions: Option[BigDecimal],
                         adjustedIncomeTaxLoss: Option[BigInt],
                         taxableProfit: Option[BigInt],
                         taxableProfitAfterIncomeTaxLossesDeduction: Option[BigInt],
                         lossClaimsSummary: Option[UkPropertyFhlLossClaimsSummary],
                         lossClaimsDetail: Option[UkPropertyFhlLossClaimsDetail],
                         bsas: Option[PropertyBsas])

object UkPropertyFhl extends NestedJsonReads {

  private implicit val topLevelReads: Reads[UkPropertyFhl] = (
    (JsPath \ "totalIncome").readNullable[BigDecimal] and
      (JsPath \ "totalExpenses").readNullable[BigDecimal] and
      (JsPath \ "netProfit").readNullable[BigDecimal] and
      (JsPath \ "netLoss").readNullable[BigDecimal] and
      (JsPath \ "totalAdditions").readNullable[BigDecimal] and
      (JsPath \ "totalDeductions").readNullable[BigDecimal] and
      (JsPath \ "adjustedIncomeTaxLoss").readNullable[BigInt] and
      (JsPath \ "taxableProfit").readNullable[BigInt] and
      (JsPath \ "taxableProfitAfterIncomeTaxLossesDeduction").readNullable[BigInt] and
      JsPath.readNullable[UkPropertyFhlLossClaimsSummary].mapEmptyModelToNone(UkPropertyFhlLossClaimsSummary.empty) and
      Reads.pure(None) and Reads.pure(None)
  )(UkPropertyFhl.apply _)

  val empty: UkPropertyFhl = UkPropertyFhl(None, None, None, None, None, None, None, None, None, None, None, None)

  private def readsApply(topLevelElementsOpt: Option[UkPropertyFhl],
                         lossClaimsDetail: Option[UkPropertyFhlLossClaimsDetail],
                         bsas: Option[PropertyBsas]): UkPropertyFhl =
    topLevelElementsOpt
      .getOrElse(UkPropertyFhl.empty)
      .copy(lossClaimsDetail = lossClaimsDetail, bsas = bsas)

  implicit val reads: Reads[UkPropertyFhl] = (
    (JsPath \ "calculation" \ "businessProfitAndLoss").readIncomeSourceTypeItem[UkPropertyFhl](incomeSourceType = "04")(topLevelReads) and
      JsPath.readNullable[UkPropertyFhlLossClaimsDetail].mapEmptyModelToNone(UkPropertyFhlLossClaimsDetail.empty) and
      (JsPath \ "inputs" \ "annualAdjustments").readIncomeSourceTypeItem[PropertyBsas](incomeSourceType = "04")
  )(UkPropertyFhl.readsApply _)

  implicit val writes: OWrites[UkPropertyFhl] = Json.writes[UkPropertyFhl]
}
