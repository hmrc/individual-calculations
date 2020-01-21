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

package v1.models.response.getCalculation.taxableIncome.detail.ukPropertyFhl

import play.api.libs.functional.syntax._
import play.api.libs.json.Reads._
import play.api.libs.json._
import utils.NestedJsonReads
import v1.models.response.getCalculation.taxableIncome.detail.Bsas
import v1.models.response.getCalculation.taxableIncome.detail.ukPropertyFhl.detail.LossClaimsDetail
import v1.models.response.getCalculation.taxableIncome.detail.ukPropertyFhl.summary.LossClaimsSummary
import v1.models.response.getCalculation.taxableIncome.detail.ukPropertyNonFhl.UkPropertyNonFhl.filteredArrayValueReads

case class UkPropertyFhl(totalIncome: Option[BigDecimal],
                         totalExpenses: Option[BigDecimal],
                         netProfit: Option[BigDecimal],
                         netLoss: Option[BigDecimal],
                         totalAdditions: Option[BigDecimal],
                         totalDeductions: Option[BigDecimal],
                         accountingAdjustments: Option[BigDecimal],
                         adjustedIncomeTaxLoss: Option[BigInt],
                         taxableProfit: Option[BigInt],
                         taxableProfitAfterIncomeTaxLossesDeduction: Option[BigInt],
                         lossClaimsSummary: Option[LossClaimsSummary],
                         lossClaimsDetail: Option[LossClaimsDetail],
                         bsas: Option[Bsas])

object UkPropertyFhl extends NestedJsonReads{
  val empty = UkPropertyFhl(None, None, None, None, None, None, None, None, None, None, None, None, None)

  case class TopLevelElements(incomeSourceType: String,
                              totalIncome: Option[BigDecimal],
                              totalExpenses: Option[BigDecimal],
                              netProfit: Option[BigDecimal],
                              netLoss: Option[BigDecimal],
                              totalAdditions: Option[BigDecimal],
                              totalDeductions: Option[BigDecimal],
                              accountingAdjustments: Option[BigDecimal],
                              adjustedIncomeTaxLoss: Option[BigInt],
                              taxableProfit: Option[BigInt],
                              taxableProfitAfterIncomeTaxLossesDeduction: Option[BigInt],
                              lossClaimsSummary: Option[LossClaimsSummary]
                             )
  object TopLevelElements {

    implicit val writes: OWrites[TopLevelElements] = Json.writes[TopLevelElements]

    implicit val reads: Reads[TopLevelElements] = (
      (JsPath \ "incomeSourceType").read[String] and
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
        __.readNullable[LossClaimsSummary]
      )(TopLevelElements.apply _)
  }

  implicit val writes: OWrites[UkPropertyFhl] = Json.writes[UkPropertyFhl]

  def readsApply(topLevelElementsOpt: Option[TopLevelElements], lossClaimsDetail: Option[LossClaimsDetail], bsas: Option[Bsas]):UkPropertyFhl =
    topLevelElementsOpt match {
      case None => UkPropertyFhl(None, None, None, None, None, None, None, None, None, None, None, lossClaimsDetail, bsas)
      case Some(topLevelElements) => UkPropertyFhl(topLevelElements.totalIncome,
        topLevelElements.totalExpenses,
        topLevelElements. netProfit,
        topLevelElements.netLoss,
        topLevelElements.totalAdditions,
        topLevelElements.totalDeductions,
        topLevelElements.accountingAdjustments,
        topLevelElements.adjustedIncomeTaxLoss,
        topLevelElements.taxableProfit,
        topLevelElements.taxableProfitAfterIncomeTaxLossesDeduction,
        topLevelElements.lossClaimsSummary,
        lossClaimsDetail, bsas)
    }

  implicit val reads: Reads[UkPropertyFhl] = (
    (JsPath \ "calculation" \ "businessProfitAndLoss")
      .readNestedNullableOpt[TopLevelElements](filteredArrayValueReads(None, "incomeSourceType", "04")) and
     __.readNullable[LossClaimsDetail].map{
       case Some(LossClaimsDetail.empty) => None
       case other => other
     } and
      (JsPath \ "inputs" \ "annualAdjustments").readNestedNullableOpt[Bsas](filteredArrayValueReads(None, "incomeSourceType", "04"))
    )(UkPropertyFhl.readsApply _)


}
