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
import sangria.macros.derive.deriveObjectType
import sangria.schema.ObjectType
import utils.NestedJsonReads
import v1.models.response.getCalculation.taxableIncome.detail.Bsas
import v1.models.response.getCalculation.taxableIncome.detail.ukPropertyNonFhl.detail.LossClaimsDetail
import v1.models.response.getCalculation.taxableIncome.detail.ukPropertyNonFhl.summary.LossClaimsSummary

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
                            lossClaimsSummary: Option[LossClaimsSummary],
                            lossClaimsDetail: Option[LossClaimsDetail],
                            bsas: Option[Bsas])

object UkPropertyNonFhl extends NestedJsonReads {
  val empty = UkPropertyNonFhl(None, None, None, None, None, None, None, None, None, None, None, None, None)

  case class TopLevelElements(totalIncome: Option[BigDecimal],
                              totalExpenses: Option[BigDecimal],
                              netProfit: Option[BigDecimal],
                              netLoss: Option[BigDecimal],
                              totalAdditions: Option[BigDecimal],
                              totalDeductions: Option[BigDecimal],
                              accountingAdjustments: Option[BigDecimal],
                              adjustedIncomeTaxLoss: Option[BigInt],
                              taxableProfit: Option[BigInt],
                              taxableProfitAfterIncomeTaxLossesDeduction: Option[BigInt],
                              lossClaimsSummary: Option[LossClaimsSummary])

  implicit val topLevelReads: Reads[TopLevelElements] = (
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
      JsPath.readNullable[LossClaimsSummary].map {
        case Some(LossClaimsSummary.empty) => None
        case other                         => other
      }
    ) (TopLevelElements.apply _)

  def componentApply(topLevelElementsOpt: Option[TopLevelElements], lossClaimsDetail: Option[LossClaimsDetail], bsas: Option[Bsas]): UkPropertyNonFhl = {
    topLevelElementsOpt match {
      case Some(topLevelElements) => UkPropertyNonFhl(topLevelElements.totalIncome, topLevelElements.totalExpenses,
        topLevelElements.netProfit, topLevelElements.netLoss, topLevelElements.totalAdditions, topLevelElements.totalDeductions,
        topLevelElements.accountingAdjustments, topLevelElements.adjustedIncomeTaxLoss, topLevelElements.taxableProfit,
        topLevelElements.taxableProfitAfterIncomeTaxLossesDeduction, topLevelElements.lossClaimsSummary, lossClaimsDetail, bsas)
      case None                   => UkPropertyNonFhl(None, None, None, None, None, None, None, None, None, None, None, lossClaimsDetail, bsas)
    }
  }

  implicit val writes: OWrites[UkPropertyNonFhl] = Json.writes[UkPropertyNonFhl]

  implicit val reads: Reads[UkPropertyNonFhl] = (
    (JsPath \ "calculation" \ "businessProfitAndLoss").readNestedNullableOpt[TopLevelElements](filteredArrayValueReads(None, "incomeSourceType", "02")) and
      JsPath.readNullable[LossClaimsDetail].map {
        case Some(LossClaimsDetail.empty) => None
        case other                        => other
      } and
      (JsPath \ "inputs" \ "annualAdjustments").readNestedNullableOpt[Bsas](filteredArrayValueReads(None, "incomeSourceType", "02"))
    ) (UkPropertyNonFhl.componentApply _)

  implicit def gqlType: ObjectType[Unit, UkPropertyNonFhl] = deriveObjectType[Unit, UkPropertyNonFhl]()
}
