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

package v1.models.response.getCalculation.taxableIncome.detail.ukPropertyNonFhl

import play.api.libs.functional.syntax._
import play.api.libs.json._
import utils.NestedJsonReads
import v1.models.response.getCalculation.taxableIncome.detail.Bsas

case class UkPropertyNonFhl(totalIncome: Option[BigDecimal],
                            totalExpenses: Option[BigDecimal],
                            netProfit: Option[BigDecimal],
                            netLoss: Option[BigDecimal],
                            totalAdditions: Option[BigDecimal],
                            totalDeductions: Option[BigDecimal],
                            accountingAdjustments: Option[BigDecimal],
                            adjustedIncomeTaxLoss: Option[BigInt],
                            taxableProfit: Option[BigInt],
                            taxableProfitAfterIncomeTaxLossesDeduction: Option[BigInt],
                            lossClaimsSummary: Option[UkPropertyNonFhlLossClaimsSummary],
                            lossClaimsDetail: Option[UkPropertyNonFhlLossClaimsDetail],
                            bsas: Option[Bsas])

object UkPropertyNonFhl extends NestedJsonReads {

  private implicit val topLevelReads: Reads[UkPropertyNonFhl] = (
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
      JsPath.readNullable[UkPropertyNonFhlLossClaimsSummary].mapEmptyModelToNone(UkPropertyNonFhlLossClaimsSummary.empty) and
      Reads.pure(None) and Reads.pure(None)
  )(UkPropertyNonFhl.apply _)

  val empty: UkPropertyNonFhl = UkPropertyNonFhl(None, None, None, None, None, None, None, None, None, None, None, None, None)

  private def readsApply(topLevelElementsOpt: Option[UkPropertyNonFhl],
                         lossClaimsDetail: Option[UkPropertyNonFhlLossClaimsDetail],
                         bsas: Option[Bsas]): UkPropertyNonFhl =
    topLevelElementsOpt
      .getOrElse(UkPropertyNonFhl.empty)
      .copy(lossClaimsDetail = lossClaimsDetail, bsas = bsas)

  implicit val reads: Reads[UkPropertyNonFhl] = (
    (JsPath \ "calculation" \ "businessProfitAndLoss").readIncomeSourceTypeItem[UkPropertyNonFhl](incomeSourceType = "02")(topLevelReads) and
      JsPath.readNullable[UkPropertyNonFhlLossClaimsDetail].mapEmptyModelToNone(UkPropertyNonFhlLossClaimsDetail.empty) and
      (JsPath \ "inputs" \ "annualAdjustments").readIncomeSourceTypeItem[Bsas](incomeSourceType = "02")
  )(UkPropertyNonFhl.readsApply _)

  implicit val writes: OWrites[UkPropertyNonFhl] = Json.writes[UkPropertyNonFhl]
}
