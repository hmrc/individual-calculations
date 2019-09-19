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

package v1.models.response.getCalculation.taxableIncome.detail.ukProperty

import play.api.libs.functional.syntax._
import play.api.libs.json._
import play.api.libs.json.Reads._
import utils.NestedJsonReads
import v1.models.response.getCalculation.taxableIncome.detail.ukProperty.detail.LossClaimsDetail
import v1.models.response.getCalculation.taxableIncome.detail.ukProperty.summary.LossClaimsSummary

case class ComponentOne(incomeSourceType: String,
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
object ComponentOne {

  implicit val writes: OWrites[ComponentOne] = Json.writes[ComponentOne]

  implicit val reads: Reads[ComponentOne] = (
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
    )(ComponentOne.apply _)
}

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
  implicit val writes: OWrites[UkPropertyFhl] = Json.writes[UkPropertyFhl]

  def readsApply(componentOne: Option[ComponentOne], lossClaimsDetail: Option[LossClaimsDetail]):UkPropertyFhl =
    componentOne match {
      case None => UkPropertyFhl(None, None, None, None, None, None, None, None, None, None, None, lossClaimsDetail)
      case Some(componentOne) => UkPropertyFhl(componentOne.totalIncome,
        componentOne.totalExpenses,
        componentOne. netProfit,
        componentOne.netLoss,
        componentOne.totalAdditions,
        componentOne.totalDeductions,
        componentOne.accountingAdjustments,
        componentOne.adjustedIncomeTaxLoss,
        componentOne.taxableProfit,
        componentOne.taxableProfitAfterIncomeTaxLossesDeduction,
        componentOne.lossClaimsSummary,
        lossClaimsDetail)
    }

  implicit val reads: Reads[UkPropertyFhl] = (
    (__ \ "calculation" \ "businessProfitAndLoss").readNestedNullable[Seq[ComponentOne]]
      .map(_.flatMap {
        case Nil => None
        case x => x.find(x => x.incomeSourceType == "04")
      }) and
     __.readNullable[LossClaimsDetail].map(_.flatMap {
       case LossClaimsDetail(None,None,None) => None
       case x => Some(x)
     })
    )(UkPropertyFhl.readsApply _)


}
