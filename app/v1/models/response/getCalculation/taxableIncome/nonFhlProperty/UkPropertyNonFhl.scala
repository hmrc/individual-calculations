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

package v1.models.response.getCalculation.taxableIncome.nonFhlProperty

import play.api.libs.json.{JsPath, Json, OWrites, Reads}
import play.api.libs.functional.syntax._
import utils.NestedJsonReads

case class UkPropertyNonFhl(totalIncome: Option[BigDecimal],
                            totalExpenses: Option[BigDecimal],
                            netProfit: Option[BigDecimal],
                            netLoss: Option[BigDecimal],
                            totalAdditions: Option[BigDecimal],
                            totalDeductions: Option[BigDecimal],
                            accountingAdjustments: Option[BigDecimal],
                            adjustedIncomeTaxLoss: Option[BigInt],
                            taxableProfit: Option[BigInt],
                            taxableProfitAfterLossesDeduction: Option[BigInt],
                            lossClaimsSummary: Option[LossClaimsSummary],
                            lossClaimsDetail: Option[LossClaimsDetail])

object UkPropertyNonFhl extends NestedJsonReads {
  implicit val writes: OWrites[UkPropertyNonFhl] = Json.writes[UkPropertyNonFhl]

  implicit val reads: Reads[UkPropertyNonFhl] = (
    (JsPath \ "calculation" \ "businessProfitAndLoss")
      .readNestedNullableOpt[BigDecimal](filteredArrayValueReads(Some("totalIncome"), "incomeSourceType", "02")) and
      (JsPath \ "calculation" \ "businessProfitAndLoss")
        .readNestedNullableOpt[BigDecimal](filteredArrayValueReads(Some("totalExpenses"), "incomeSourceType", "02")) and
      (JsPath \ "calculation" \ "businessProfitAndLoss")
        .readNestedNullableOpt[BigDecimal](filteredArrayValueReads(Some("netProfit"), "incomeSourceType", "02")) and
      (JsPath \ "calculation" \ "businessProfitAndLoss")
        .readNestedNullableOpt[BigDecimal](filteredArrayValueReads(Some("netLoss"), "incomeSourceType", "02")) and
      (JsPath \ "calculation" \ "businessProfitAndLoss")
        .readNestedNullableOpt[BigDecimal](filteredArrayValueReads(Some("totalAdditions"), "incomeSourceType", "02")) and
      (JsPath \ "calculation" \ "businessProfitAndLoss")
        .readNestedNullableOpt[BigDecimal](filteredArrayValueReads(Some("totalDeductions"), "incomeSourceType", "02")) and
      (JsPath \ "calculation" \ "businessProfitAndLoss")
        .readNestedNullableOpt[BigDecimal](filteredArrayValueReads(Some("accountingAdjustments"), "incomeSourceType", "02")) and
      (JsPath \ "calculation" \ "businessProfitAndLoss")
        .readNestedNullableOpt[BigInt](filteredArrayValueReads(Some("taxableProfit"), "incomeSourceType", "02")) and
      (JsPath \ "calculation" \ "businessProfitAndLoss")
        .readNestedNullableOpt[BigInt](filteredArrayValueReads(Some("adjustedIncomeTaxLoss"), "incomeSourceType", "02")) and
      (JsPath \ "calculation" \ "businessProfitAndLoss")
        .readNestedNullableOpt[BigInt](filteredArrayValueReads(Some("taxableProfitAfterLossesDeduction"), "incomeSourceType", "02")) and
      (JsPath \ "calculation" \ "businessProfitAndLoss")
        .readNestedNullableOpt[LossClaimsSummary](filteredArrayValueReads(None, "incomeSourceType", "02")) and
      JsPath.readNullable[LossClaimsDetail].map {
        case Some(LossClaimsDetail(None, None, None, None)) => None
        case other => other
      }
  )(UkPropertyNonFhl.apply _)
}
