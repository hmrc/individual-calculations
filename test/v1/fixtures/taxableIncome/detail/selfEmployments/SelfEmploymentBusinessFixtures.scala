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

import play.api.libs.json.{JsArray, JsObject, JsValue, Json}
import v1.fixtures.taxableIncome.detail.selfEmployments.detail.LossClaimsDetailFixtures._
import v1.fixtures.taxableIncome.detail.selfEmployments.summary.LossClaimSummaryFixtures._
import v1.models.response.getCalculation.taxableIncome.detail.selfEmployment.SelfEmploymentBusiness
import v1.models.response.getCalculation.taxableIncome.detail.selfEmployment.detail.LossClaimsDetail
import v1.models.response.getCalculation.taxableIncome.detail.selfEmployment.summary.LossClaimsSummary

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
  val taxableProfitAfterLossesDeduction: Option[BigInt] = Some(2)

  val selfEmploymentBusinessDefaultResponse: SelfEmploymentBusiness = SelfEmploymentBusiness(
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
    taxableProfitAfterLossesDeduction,
    Some(lossClaimsSummaryResponse),
    Some(lossClaimsDetailDefaultResponse)
  )

  val selfEmploymentBusinessDefaultResponseWithoutDetail: SelfEmploymentBusiness = selfEmploymentBusinessDefaultResponse.copy(lossClaimsDetail = None)

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
      |    "taxableProfitAfterLossesDeduction": ${taxableProfitAfterLossesDeduction.get}
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
   Json.arr(additionalWrittenFieldsJson.as[JsObject].deepMerge(
      Json.obj("lossClaimsSummary" -> lossClaimSummaryWrittenJson)
        .deepMerge(
          Json.obj("lossClaimsDetail" -> lossClaimsDetailDefaultWrittenJson)
        )))

  def selfEmploymentBusinessResponseFactory(
      selfEmploymentId: String = "a1s2d3f4g5h6j7k"
  ): SelfEmploymentBusiness = selfEmploymentBusinessDefaultResponse.copy(selfEmploymentId = selfEmploymentId)


  val emptyJson: JsObject = JsObject.empty

}
