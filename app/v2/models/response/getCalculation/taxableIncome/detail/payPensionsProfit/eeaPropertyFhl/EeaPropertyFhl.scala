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

package v2.models.response.getCalculation.taxableIncome.detail.payPensionsProfit.eeaPropertyFhl

import play.api.libs.functional.syntax._
import play.api.libs.json.Reads._
import play.api.libs.json._
import utils.NestedJsonReads
import v2.models.response.getCalculation.taxableIncome.detail.payPensionsProfit.PropertyBsas

case class EeaPropertyFhl(totalIncome: Option[BigDecimal],
                          totalExpenses: Option[BigDecimal],
                          netProfit: Option[BigDecimal],
                          netLoss: Option[BigDecimal],
                          totalAdditions: Option[BigDecimal],
                          totalDeductions: Option[BigDecimal],
                          adjustedIncomeTaxLoss: Option[BigInt],
                          taxableProfit: Option[BigInt],
                          taxableProfitAfterIncomeTaxLossesDeduction: Option[BigInt],
                          lossClaimsSummary: Option[EeaPropertyFhlLossClaimsSummary],
                          lossClaimsDetail: Option[EeaPropertyFhlLossClaimsDetail],
                          bsas: Option[PropertyBsas])

object EeaPropertyFhl extends NestedJsonReads {

  private implicit val topLevelReads: Reads[EeaPropertyFhl] = (
    (JsPath \ "totalIncome").readNullable[BigDecimal] and
      (JsPath \ "totalExpenses").readNullable[BigDecimal] and
      (JsPath \ "netProfit").readNullable[BigDecimal] and
      (JsPath \ "netLoss").readNullable[BigDecimal] and
      (JsPath \ "totalAdditions").readNullable[BigDecimal] and
      (JsPath \ "totalDeductions").readNullable[BigDecimal] and
      (JsPath \ "adjustedIncomeTaxLoss").readNullable[BigInt] and
      (JsPath \ "taxableProfit").readNullable[BigInt] and
      (JsPath \ "taxableProfitAfterIncomeTaxLossesDeduction").readNullable[BigInt] and
      JsPath.readNullable[EeaPropertyFhlLossClaimsSummary].mapEmptyModelToNone(EeaPropertyFhlLossClaimsSummary.empty) and
      Reads.pure(None) and Reads.pure(None)
  )(EeaPropertyFhl.apply _)

  val empty: EeaPropertyFhl = EeaPropertyFhl(None, None, None, None, None, None, None, None, None, None, None, None)

  private def readsApply(topLevelElementsOpt: Option[EeaPropertyFhl],
                         lossClaimsDetail: Option[EeaPropertyFhlLossClaimsDetail],
                         bsas: Option[PropertyBsas]): EeaPropertyFhl =
    topLevelElementsOpt
      .getOrElse(EeaPropertyFhl.empty)
      .copy(lossClaimsDetail = lossClaimsDetail, bsas = bsas)

  implicit val reads: Reads[EeaPropertyFhl] = (
    (JsPath \ "calculation" \ "businessProfitAndLoss").readIncomeSourceTypeItem[EeaPropertyFhl](incomeSourceType = "03")(topLevelReads) and
      JsPath.readNullable[EeaPropertyFhlLossClaimsDetail].mapEmptyModelToNone(EeaPropertyFhlLossClaimsDetail.empty) and
      (JsPath \ "inputs" \ "annualAdjustments").readIncomeSourceTypeItem[PropertyBsas](incomeSourceType = "03")
  )(EeaPropertyFhl.readsApply _)

  implicit val writes: OWrites[EeaPropertyFhl] = Json.writes[EeaPropertyFhl]
}
