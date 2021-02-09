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

package v1r2.fixtures.getCalculation.endOfYearEstimate.detail

import play.api.libs.json.{JsObject, JsValue, Json}
import v1r2.fixtures.getCalculation.endOfYearEstimate.detail.EoyEstimateForeignInterestFixtures._
import v1r2.fixtures.getCalculation.endOfYearEstimate.detail.EoyEstimateForeignPropertyFixtures._
import v1r2.fixtures.getCalculation.endOfYearEstimate.detail.EoyEstimateOtherDividendsFixtures._
import v1r2.fixtures.getCalculation.endOfYearEstimate.detail.EoyEstimateSelfEmploymentFixtures._
import v1r2.fixtures.getCalculation.endOfYearEstimate.detail.EoyEstimateStateBenefitsFixtures._
import v1r2.fixtures.getCalculation.endOfYearEstimate.detail.EoyEstimateUkDividendsFixtures._
import v1r2.fixtures.getCalculation.endOfYearEstimate.detail.EoyEstimateUkPropertyFhlFixtures._
import v1r2.fixtures.getCalculation.endOfYearEstimate.detail.EoyEstimateUkPropertyNonFhlFixtures._
import v1r2.fixtures.getCalculation.endOfYearEstimate.detail.EoyEstimateUkSavingFixtures._
import v1r2.fixtures.getCalculation.endOfYearEstimate.detail.EoyEstimateUkSecuritiesFixtures._
import v1r2.models.response.getCalculation.endOfYearEstimate.detail.EoyEstimateDetail

object EoyEstimateDetailFixtures {

  val eoyEstimateDetailResponse: EoyEstimateDetail = EoyEstimateDetail(
    Some(Seq(eoyEstimateSelfEmploymentResponse)),
    Some(eoyEstimateUkPropertyFhlResponse),
    Some(eoyEstimateUkPropertyNonFhlResponse),
    Some(Seq(eoyEstimateUkSavingResponse, eoyEstimateUkSavingResponse)),
    Some(eoyEstimateUkDividendsResponse),
    Some(eoyEstimateOtherDividendsResponse),
    Some(eoyEstimateStateBenefitsResponse),
    Some(eoyEstimateUkSecuritiesResponse),
    Some(eoyEstimateForeignPropertyResponse),
    Some(eoyEstimateForeignInterestResponse)
  )

  val eoyEstimateDetailResponseReduced: EoyEstimateDetail = EoyEstimateDetail(
    selfEmployments = Some(Seq(eoyEstimateSelfEmploymentResponse)),
    ukPropertyFhl = None,
    ukPropertyNonFhl = None,
    ukSavings = Some(Seq(eoyEstimateUkSavingResponse, eoyEstimateUkSavingResponse)),
    ukDividends = None,
    otherDividends = None,
    stateBenefits = None,
    ukSecurities = None,
    foreignProperty = None,
    foreignInterest = None
  )

  val eoyEstimateDetailDesJson: JsObject =
    Json.obj(
      "incomeSource" ->
        Seq(
          eoyEstimateSelfEmploymentDesJson,
          eoyEstimateUkPropertyFhlDesJson,
          eoyEstimateUkPropertyNonFhlDesJson,
          eoyEstimateUkSavingDesJson,
          eoyEstimateUkSavingDesJson,
          eoyEstimateUkDividendsDesJson,
          eoyEstimateOtherDividendsDesJson,
          eoyEstimateStateBenefitsDesJson,
          eoyEstimateUkSecuritiesDesJson,
          eoyEstimateForeignPropertyDesJson,
          eoyEstimateForeignInterestDesJson
        ))

  val eoyEstimateDetailDesJsonSomeMissingOptionals: JsValue =
    Json.obj(
      "incomeSource" ->
        Seq(
          eoyEstimateSelfEmploymentDesJson,
          eoyEstimateUkSavingDesJson,
          eoyEstimateUkSavingDesJson
        ))

  val eoyEstimateDetailDesJsonAllMissingOptionals: JsValue =
    Json.obj(
      "incomeSource" ->
        Seq.empty[JsValue])

  val eoyEstimateDetailDesJsonSomeWrongIncomeSourceTypes: JsValue =
    Json.obj(
      "incomeSource" ->
        Seq(
          eoyEstimateSelfEmploymentDesJson,
          eoyEstimateUkPropertyFhlDesJsonWrongIncomeSourceType,
          eoyEstimateUkPropertyNonFhlDesJsonWrongIncomeSourceType,
          eoyEstimateUkSavingDesJson,
          eoyEstimateUkSavingDesJson,
          eoyEstimateUkDividendsDesJsonWrongIncomeSourceType,
          eoyEstimateOtherDividendsDesJsonWrongIncomeSourceType,
          eoyEstimateStateBenefitsDesJsonWrongIncomeSourceType,
          eoyEstimateUkSecuritiesDesJsonWrongIncomeSourceType,
          eoyEstimateForeignPropertyDesJsonWrongIncomeSourceType,
          eoyEstimateForeignInterestDesJsonWrongIncomeSourceType
        ))

  val eoyEstimateDetailDesJsonAllWrongIncomeSourceTypes: JsValue =
    Json.obj(
      "incomeSource" ->
        Seq(
          eoyEstimateSelfEmploymentDesJsonWrongIncomeSourceType,
          eoyEstimateUkPropertyFhlDesJsonWrongIncomeSourceType,
          eoyEstimateUkPropertyNonFhlDesJsonWrongIncomeSourceType,
          eoyEstimateUkSavingDesJsonWrongIncomeSourceType,
          eoyEstimateUkDividendsDesJsonWrongIncomeSourceType,
          eoyEstimateOtherDividendsDesJsonWrongIncomeSourceType,
          eoyEstimateStateBenefitsDesJsonWrongIncomeSourceType,
          eoyEstimateUkSecuritiesDesJsonWrongIncomeSourceType,
          eoyEstimateForeignPropertyDesJsonWrongIncomeSourceType,
          eoyEstimateForeignInterestDesJsonWrongIncomeSourceType
        ))

  val eoyEstimateDetailWrittenJson: JsValue =
    eoyEstimateSelfEmploymentWrittenJsonObject
      .deepMerge(eoyEstimateUkPropertyFhlWrittenJsonObject)
      .deepMerge(eoyEstimateUkPropertyNonFhlWrittenJsonObject)
      .deepMerge(eoyEstimateUkSavingWrittenJsonObject)
      .deepMerge(eoyEstimateUkDividendsWrittenJsonObject)
      .deepMerge(eoyEstimateOtherDividendsWrittenJsonObject)
      .deepMerge(eoyEstimateStateBenefitsWrittenJsonObject)
      .deepMerge(eoyEstimateUkSecuritiesWrittenJsonObject)
      .deepMerge(eoyEstimateForeignPropertyWrittenJsonObject)
      .deepMerge(eoyEstimateForeignInterestWrittenJsonObject)

  val eoyEstimateDetailWrittenJsonObject: JsObject = Json.obj("detail" -> eoyEstimateDetailWrittenJson)

  val eoyEstimateDetailWrittenJsonMissingOptionals: JsValue =
    eoyEstimateSelfEmploymentWrittenJsonObject
      .deepMerge(eoyEstimateUkSavingWrittenJsonObject)

  val eoyEstimateDetailInvalidJson: JsValue =
    Json.obj(
      "incomeSource" ->
        Seq(
          eoyEstimateSelfEmploymentInvalidJson,
          eoyEstimateUkPropertyFhlDesJson,
          eoyEstimateUkPropertyNonFhlDesJson,
          eoyEstimateUkSavingDesJson,
          eoyEstimateUkDividendsDesJson,
          eoyEstimateOtherDividendsDesJson,
          eoyEstimateStateBenefitsDesJson,
          eoyEstimateUkSecuritiesDesJson,
          eoyEstimateForeignPropertyDesJson,
          eoyEstimateForeignInterestDesJson
        ))
}
