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

package v1.models.response.getCalculation.taxableIncome.detail.ukPropertyFhl

import play.api.libs.functional.syntax._
import play.api.libs.json.Reads._
import play.api.libs.json._
import utils.NestedJsonReads
import v1.models.response.getCalculation.taxableIncome.detail.ukPropertyFhl.detail.LossClaimsDetail
import v1.models.response.getCalculation.taxableIncome.detail.ukPropertyFhl.summary.LossClaimsSummary

case class UkPropertyFhl(totalIncome: Option[BigDecimal],
                         totalExpenses: Option[BigDecimal],
                         netProfit: Option[BigDecimal],
                         netLoss: Option[BigDecimal],
                         totalAdditions: Option[BigDecimal],
                         totalDeductions: Option[BigDecimal],
                         accountingAdjustments: Option[BigDecimal],
                         adjustedIncomeTaxLoss: Option[BigDecimal],
                         taxableProfit: Option[BigDecimal],
                         taxableProfitAfterIncomeTaxLossesDeduction: Option[BigDecimal],
                         lossClaimsSummary: Option[LossClaimsSummary],
                         lossClaimsDetail: Option[LossClaimsDetail]
                        )

object UkPropertyFhl extends NestedJsonReads{

  case class TopLevelElements(incomeSourceType: String,
                              totalIncome: Option[BigDecimal],
                              totalExpenses: Option[BigDecimal],
                              netProfit: Option[BigDecimal],
                              netLoss: Option[BigDecimal],
                              totalAdditions: Option[BigDecimal],
                              totalDeductions: Option[BigDecimal],
                              accountingAdjustments: Option[BigDecimal],
                              adjustedIncomeTaxLoss: Option[BigDecimal],
                              taxableProfit: Option[BigDecimal],
                              taxableProfitAfterIncomeTaxLossesDeduction: Option[BigDecimal],
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
        (JsPath \ "adjustedIncomeTaxLoss").readNullable[BigDecimal] and
        (JsPath \ "taxableProfit").readNullable[BigDecimal] and
        (JsPath \ "taxableProfitAfterIncomeTaxLossesDeduction").readNullable[BigDecimal] and
        __.readNullable[LossClaimsSummary]
      )(TopLevelElements.apply _)
  }

  implicit val writes: OWrites[UkPropertyFhl] = Json.writes[UkPropertyFhl]

  def readsApply(topLevelElementsOpt: Option[TopLevelElements], lossClaimsDetail: Option[LossClaimsDetail]):UkPropertyFhl =
    topLevelElementsOpt match {
      case None => UkPropertyFhl(None, None, None, None, None, None, None, None, None, None, None, lossClaimsDetail)
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
        lossClaimsDetail)
    }

  implicit val reads: Reads[UkPropertyFhl] = (
    (JsPath \ "calculation" \ "businessProfitAndLoss")
      .readNestedNullableOpt[TopLevelElements](filteredArrayValueReads(None, "incomeSourceType", "04")) and
     __.readNullable[LossClaimsDetail].map(_.flatMap {
       case LossClaimsDetail(None,None,None) => None
       case x => Some(x)
     })
    )(UkPropertyFhl.readsApply _)


}
