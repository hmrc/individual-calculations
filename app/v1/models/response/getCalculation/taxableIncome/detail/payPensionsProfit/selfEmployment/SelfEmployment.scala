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

package v1.models.response.getCalculation.taxableIncome.detail.payPensionsProfit.selfEmployment

import play.api.libs.functional.syntax._
import play.api.libs.json._
import utils.NestedJsonReads

case class SelfEmployment(selfEmploymentId: String,
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
                          lossClaimsSummary: Option[SelfEmploymentLossClaimsSummary],
                          lossClaimsDetail: Option[SelfEmploymentLossClaimsDetail],
                          bsas: Option[SelfEmploymentBsas])

object SelfEmployment extends NestedJsonReads {

  private implicit val topLevelReads: Reads[SelfEmployment] = (
    (JsPath \ "incomeSourceId").read[String] and
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
      JsPath.readNullable[SelfEmploymentLossClaimsSummary].mapEmptyModelToNone(SelfEmploymentLossClaimsSummary.empty) and
      Reads.pure(None) and Reads.pure(None)
    ) (SelfEmployment.apply _)

  private def readsApply(selfEmploymentSeqOpt: Option[Seq[SelfEmployment]],
                         detailsOpt: Option[SelfEmploymentLossClaimsDetail],
                         bsasSeqOpt: Option[Seq[SelfEmploymentBsas]]): Seq[SelfEmployment] =
    selfEmploymentSeqOpt.getOrElse(Seq.empty[SelfEmployment])
      .map(selfEmployment => selfEmployment.copy(
        lossClaimsDetail = detailsOpt.getOrElse(SelfEmploymentLossClaimsDetail.empty).filterById(selfEmployment.selfEmploymentId),
        bsas = bsasSeqOpt.getOrElse(Seq.empty).find(_.incomeSourceId == selfEmployment.selfEmploymentId)
      ))

  implicit val seqReads: Reads[Seq[SelfEmployment]] = (
    (JsPath \ "calculation" \ "businessProfitAndLoss").readIncomeSourceTypeSeq[SelfEmployment](incomeSourceType = "01")(topLevelReads) and
      JsPath.readNullable[SelfEmploymentLossClaimsDetail].mapEmptyModelToNone(SelfEmploymentLossClaimsDetail.empty) and
      (JsPath \ "inputs" \ "annualAdjustments").readIncomeSourceTypeSeq[SelfEmploymentBsas](incomeSourceType = "01")
    ) (SelfEmployment.readsApply _)

  implicit val writes: OWrites[SelfEmployment] = Json.writes[SelfEmployment]
}
