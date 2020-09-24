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

package v1.models.response.getCalculation.taxableIncome.detail.selfEmployment

import play.api.libs.functional.syntax._
import play.api.libs.json._
import sangria.macros.derive.deriveObjectType
import sangria.schema.ObjectType
import utils.NestedJsonReads
import v1.models.response.getCalculation.taxableIncome.detail.selfEmployment.detail.{BusinessSourceAdjustableSummary, LossClaimsDetail}
import v1.models.response.getCalculation.taxableIncome.detail.selfEmployment.summary.LossClaimsSummary

case class SelfEmployment(
    selfEmploymentId: String,
    totalIncome: Option[BigDecimal],
    totalExpenses: Option[BigDecimal],
    netProfit: Option[BigDecimal],
    netLoss: Option[BigDecimal],
    class4Loss: Option[BigInt],
    totalAdditions: Option[BigDecimal],
    totalDeductions: Option[BigDecimal],
    accountingAdjustments: Option[BigDecimal],
    adjustedIncomeTaxLoss: Option[BigInt],
    taxableProfit: Option[BigDecimal],
    taxableProfitAfterIncomeTaxLossesDeduction: Option[BigInt],
    lossClaimsSummary: Option[LossClaimsSummary],
    lossClaimsDetail: Option[LossClaimsDetail],
    bsas: Option[BusinessSourceAdjustableSummary]
)

object SelfEmployment extends NestedJsonReads {

  implicit val writes: OWrites[SelfEmployment] = Json.writes[SelfEmployment]

  implicit val singleReads: Reads[SelfEmployment] = ((JsPath \ "incomeSourceId").read[String] and
    (JsPath \ "totalIncome").readNullable[BigDecimal] and
    (JsPath \ "totalExpenses").readNullable[BigDecimal] and
    (JsPath \ "netProfit").readNullable[BigDecimal] and
    (JsPath \ "netLoss").readNullable[BigDecimal] and
    (JsPath \ "class4Loss").readNullable[BigInt] and
    (JsPath \ "totalAdditions").readNullable[BigDecimal] and
    (JsPath \ "totalDeductions").readNullable[BigDecimal] and
    (JsPath \ "accountingAdjustments").readNullable[BigDecimal] and
    (JsPath \ "adjustedIncomeTaxLoss").readNullable[BigInt] and
    (JsPath \ "taxableProfit").readNullable[BigDecimal] and
    (JsPath \ "taxableProfitAfterIncomeTaxLossesDeduction").readNullable[BigInt] and
    JsPath.readNullable[LossClaimsSummary].map{
      case Some(LossClaimsSummary.empty) => None
      case other => other
    } and Reads.pure(None) and Reads.pure(None))(SelfEmployment.apply _)

  implicit val seqReads: Reads[Seq[SelfEmployment]] =((JsPath \ "calculation" \ "businessProfitAndLoss")
    .readNestedNullable[Seq[JsValue]].mapEmptySeqToNone
    .map(_.getOrElse(Seq.empty).flatMap(js => filterByIncomeSourceType(js)))
    .map(x => Some(x)) and
    JsPath.readNullable[LossClaimsDetail] and
    (JsPath \ "inputs" \ "annualAdjustments")
      .readNestedNullable[Seq[BusinessSourceAdjustableSummary]](filteredArrayReads("incomeSourceType", "01")).mapEmptySeqToNone)(buildSelfEmployments(_, _, _))

  def buildSelfEmployments(seqSelfEmploysOpt: Option[Seq[SelfEmployment]],
                           detailsOpt: Option[LossClaimsDetail], bsasOpt: Option[Seq[BusinessSourceAdjustableSummary]]): Seq[SelfEmployment] = {
    val seqSelfEmploys    = seqSelfEmploysOpt.getOrElse(Seq.empty)
    val details = detailsOpt.getOrElse(LossClaimsDetail.empty)
    val bsas = bsasOpt
    seqSelfEmploys.map(selfEmploy => selfEmploy.copy(lossClaimsDetail = details.filterById(selfEmploy.selfEmploymentId),
      bsas = bsas.getOrElse(Seq.empty).find(x => selfEmploy.selfEmploymentId == x.incomeSourceId)))
  }

  def filterByIncomeSourceType(js: JsValue, sourceType: String = "01"): Option[SelfEmployment] = js.as[FilterWrapper] match {
    case FilterWrapper(incomeSourceType) if incomeSourceType == sourceType => Some(js.as[SelfEmployment])
    case _ => None
  }

  case class FilterWrapper(incomeSourceType: String)

  object FilterWrapper {
    implicit val formats: OFormat[FilterWrapper] = Json.format[FilterWrapper]
  }

  implicit def gqlType: ObjectType[Unit, SelfEmployment] = deriveObjectType[Unit, SelfEmployment]()

}
