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

package v1.fixtures.taxableIncome.detail.selfEmployments

import play.api.libs.json.{JsObject, JsValue, Json}
import v1.fixtures.taxableIncome.detail.selfEmployments.detail.LossClaimsDetailFixtures._
import v1.fixtures.taxableIncome.detail.selfEmployments.summary.LossClaimSummaryFixtures._
import v1.models.response.getCalculation.taxableIncome.detail.selfEmployment.SelfEmployment

object SelfEmploymentBusinessFixtures {

  val selfEmploymentId: String                          = "AAIS12345678904"
  val totalIncome: Option[BigDecimal]                   = Some(79291394)
  val totalExpenses: Option[BigDecimal]                 = Some(89005890)
  val netProfit: Option[BigDecimal]                     = Some(93480427)
  val netLoss: Option[BigDecimal]                       = Some(10017816)
  val class4Loss: Option[BigInt]                        = Some(2)
  val totalAdditions: Option[BigDecimal]                = Some(39901282)
  val totalDeductions: Option[BigDecimal]               = Some(80648172)
  val accountingAdjustments: Option[BigDecimal]         = Some(-8769926.99)
  val taxableProfit: Option[BigDecimal]                 = Some(92149284)
  val adjustedIncomeTaxLoss: Option[BigInt]             = Some(2)
  val taxableProfitAfterIncomeTaxLossesDeduction: Option[BigInt] = Some(2)

  val selfEmploymentBusinessDefaultResponse: SelfEmployment = SelfEmployment(
    selfEmploymentId,
    totalIncome,
    totalExpenses,
    netProfit,
    netLoss,
    class4Loss,
    totalAdditions,
    totalDeductions,
    accountingAdjustments,
    adjustedIncomeTaxLoss,
    taxableProfit,
    taxableProfitAfterIncomeTaxLossesDeduction,
    Some(lossClaimsSummaryResponse),
    Some(lossClaimsDetailDefaultResponse)
  )

  val selfEmploymentBusinessDefaultResponseWithoutDetail: SelfEmployment = selfEmploymentBusinessDefaultResponse.copy(lossClaimsDetail = None)

  val selfEmploymentBusinessDefaultDesJsonSingular: JsValue = lossClaimSummaryDesJson

  val selfEmploymentBusinessDefaultDesJsonSequence: JsValue = Json
    .obj("calculation" -> Json.obj("businessProfitAndLoss" -> Seq(lossClaimSummaryDesJson)))
    .deepMerge(lossClaimsDetailDefaultDesJson.as[JsObject])

  val additionalWrittenFieldsJson: JsValue = Json.parse(s"""{
      |    "selfEmploymentId": "$selfEmploymentId",
      |    "totalIncome": ${totalIncome.get},
      |    "totalExpenses": ${totalExpenses.get},
      |    "netProfit": ${netProfit.get},
      |    "netLoss": ${netLoss.get},
      |    "class4Loss": ${class4Loss.get},
      |    "totalAdditions": ${totalAdditions.get},
      |    "totalDeductions": ${totalDeductions.get},
      |    "accountingAdjustments": ${accountingAdjustments.get},
      |    "adjustedIncomeTaxLoss": ${adjustedIncomeTaxLoss.get},
      |    "taxableProfit": ${taxableProfit.get},
      |    "taxableProfitAfterIncomeTaxLossesDeduction": ${taxableProfitAfterIncomeTaxLossesDeduction.get}
      |}""".stripMargin)

  val selfEmploymentDetailDefaultWrittenJsonSingular: JsValue =
    additionalWrittenFieldsJson
      .as[JsObject]
      .deepMerge(
        Json
          .obj("lossClaimsSummary" -> lossClaimSummaryWrittenJson)
          .deepMerge(
            Json.obj("lossClaimsDetail" -> lossClaimsDetailDefaultWrittenJson)
          ))

  val selfEmploymentDetailDefaultWrittenJsonSequence: JsValue =
    Json.arr(
      additionalWrittenFieldsJson
        .as[JsObject]
        .deepMerge(
          Json
            .obj("lossClaimsSummary" -> lossClaimSummaryWrittenJson)
            .deepMerge(
              Json.obj("lossClaimsDetail" -> lossClaimsDetailDefaultWrittenJson)
            )))

  val summaryDesJson1: JsValue = lossClaimsSummaryDesJsonIdFactory("AA11WWLD30FKEKK")
  val summaryDesJson2: JsValue = lossClaimsSummaryDesJsonIdFactory("FW01LCMWAIKSAND")
  val summaryDesJson3: JsValue = lossClaimsSummaryDesJsonIdFactory("WIVJEJDJFJEHFI2")
  val summaryDesJson4: JsValue = lossClaimsSummaryDesJsonIdFactory("J3OC939CKEO2JFI")

  val summariesDesJson = Json.obj(
    "calculation" -> Json.obj(
      "businessProfitAndLoss" -> Seq(summaryDesJson1, summaryDesJson2, summaryDesJson3, summaryDesJson4)
    )
  )

  val selfEmployments: Seq[SelfEmployment] = Seq(
    selfEmploymentResponseIdFactory("AA11WWLD30FKEKK"),
    selfEmploymentResponseIdFactory("FW01LCMWAIKSAND"),
    selfEmploymentResponseIdFactory("WIVJEJDJFJEHFI2"),
    selfEmploymentResponseIdFactory("J3OC939CKEO2JFI")
  )
  val emptyJson: JsObject = JsObject.empty

  def selfEmploymentResponseIdFactory(selfEmploymentId: String = selfEmploymentId): SelfEmployment = SelfEmployment(
    selfEmploymentId,
    totalIncome,
    totalExpenses,
    netProfit,
    netLoss,
    class4Loss,
    totalAdditions,
    totalDeductions,
    accountingAdjustments,
    adjustedIncomeTaxLoss,
    taxableProfit,
    taxableProfitAfterIncomeTaxLossesDeduction,
    Some(lossClaimsSummaryResponse),
    None
  )

}
