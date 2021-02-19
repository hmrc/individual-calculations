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

package v2.fixtures.getCalculation.endOfYearEstimate.detail

import play.api.libs.json.{JsObject, JsValue, Json}
import v2.fixtures.getCalculation.endOfYearEstimate.detail.EoyEstimateEeaPropertyFhlFixtures._
import v2.fixtures.getCalculation.endOfYearEstimate.detail.EoyEstimateForeignCompanyDividendsFixtures._
import v2.fixtures.getCalculation.endOfYearEstimate.detail.EoyEstimateForeignInterestFixtures._
import v2.fixtures.getCalculation.endOfYearEstimate.detail.EoyEstimateForeignPensionFixtures._
import v2.fixtures.getCalculation.endOfYearEstimate.detail.EoyEstimateForeignPropertyFixtures._
import v2.fixtures.getCalculation.endOfYearEstimate.detail.EoyEstimateOtherDividendsFixtures._
import v2.fixtures.getCalculation.endOfYearEstimate.detail.EoyEstimateOtherIncomeFixtures._
import v2.fixtures.getCalculation.endOfYearEstimate.detail.EoyEstimateSelfEmploymentFixtures._
import v2.fixtures.getCalculation.endOfYearEstimate.detail.EoyEstimateStateBenefitsFixtures._
import v2.fixtures.getCalculation.endOfYearEstimate.detail.EoyEstimateUkDividendsFixtures._
import v2.fixtures.getCalculation.endOfYearEstimate.detail.EoyEstimateUkPropertyFhlFixtures._
import v2.fixtures.getCalculation.endOfYearEstimate.detail.EoyEstimateUkPropertyNonFhlFixtures._
import v2.fixtures.getCalculation.endOfYearEstimate.detail.EoyEstimateUkSavingFixtures._
import v2.fixtures.getCalculation.endOfYearEstimate.detail.EoyEstimateUkSecuritiesFixtures._
import v2.models.response.getCalculation.endOfYearEstimate.detail.EoyEstimateDetail

object EoyEstimateDetailFixtures {

  val eoyEstimateDetailResponse: EoyEstimateDetail = EoyEstimateDetail(
    Some(Seq(eoyEstimateSelfEmploymentResponse)),
    Some(eoyEstimateUkPropertyFhlResponse),
    Some(eoyEstimateUkPropertyNonFhlResponse),
    Some(Seq(eoyEstimateUkSavingResponse, eoyEstimateUkSavingResponse)),
    Some(eoyEstimateUkDividendsResponse),
    Some(eoyEstimateOtherDividendsResponse),
    Some(eoyEstimateForeignCompanyDividendsResponse),
    Some(eoyEstimateStateBenefitsResponse),
    Some(eoyEstimateUkSecuritiesResponse),
    Some(eoyEstimateForeignPropertyResponse),
    Some(eoyEstimateEeaPropertyFhlResponse),
    Some(eoyEstimateForeignInterestResponse),
    Some(eoyEstimateOtherIncomeResponse),
    Some(eoyEstimateForeignPensionResponse)
  )

  val eoyEstimateDetailResponseReduced: EoyEstimateDetail = EoyEstimateDetail(
    selfEmployments = Some(Seq(eoyEstimateSelfEmploymentResponse)),
    ukPropertyFhl = None,
    ukPropertyNonFhl = None,
    ukSavings = Some(Seq(eoyEstimateUkSavingResponse, eoyEstimateUkSavingResponse)),
    ukDividends = None,
    otherDividends = None,
    foreignCompanyDividends = None,
    stateBenefits = None,
    ukSecurities = None,
    foreignProperty = None,
    eeaPropertyFhl = None,
    foreignInterest = None,
    otherIncome = None,
    foreignPension = None
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
          eoyEstimateForeignCompanyDividendsDesJson,
          eoyEstimateStateBenefitsDesJson,
          eoyEstimateUkSecuritiesDesJson,
          eoyEstimateForeignPropertyDesJson,
          eoyEstimateEeaPropertyFhlDesJson,
          eoyEstimateForeignInterestDesJson,
          eoyEstimateOtherIncomeDesJson,
          eoyEstimateForeignPensionDesJson
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
          eoyEstimateForeignCompanyDividendsDesJsonWrongIncomeSourceType,
          eoyEstimateStateBenefitsDesJsonWrongIncomeSourceType,
          eoyEstimateUkSecuritiesDesJsonWrongIncomeSourceType,
          eoyEstimateForeignPropertyDesJsonWrongIncomeSourceType,
          eoyEstimateEeaPropertyFhlDesJsonWrongIncomeSourceType,
          eoyEstimateForeignInterestDesJsonWrongIncomeSourceType,
          eoyEstimateOtherIncomeDesJsonWrongIncomeSourceType,
          eoyEstimateForeignPensionDesJsonWrongIncomeSourceType
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
          eoyEstimateForeignCompanyDividendsDesJsonWrongIncomeSourceType,
          eoyEstimateStateBenefitsDesJsonWrongIncomeSourceType,
          eoyEstimateUkSecuritiesDesJsonWrongIncomeSourceType,
          eoyEstimateForeignPropertyDesJsonWrongIncomeSourceType,
          eoyEstimateEeaPropertyFhlDesJsonWrongIncomeSourceType,
          eoyEstimateForeignInterestDesJsonWrongIncomeSourceType,
          eoyEstimateOtherIncomeDesJsonWrongIncomeSourceType,
          eoyEstimateForeignPensionDesJsonWrongIncomeSourceType
        ))

  val eoyEstimateDetailWrittenJson: JsValue =
    eoyEstimateSelfEmploymentWrittenJsonObject
      .deepMerge(eoyEstimateUkPropertyFhlWrittenJsonObject)
      .deepMerge(eoyEstimateUkPropertyNonFhlWrittenJsonObject)
      .deepMerge(eoyEstimateUkSavingWrittenJsonObject)
      .deepMerge(eoyEstimateUkDividendsWrittenJsonObject)
      .deepMerge(eoyEstimateOtherDividendsWrittenJsonObject)
      .deepMerge(eoyEstimateForeignCompanyDividendsWrittenJsonObject)
      .deepMerge(eoyEstimateStateBenefitsWrittenJsonObject)
      .deepMerge(eoyEstimateUkSecuritiesWrittenJsonObject)
      .deepMerge(eoyEstimateForeignPropertyWrittenJsonObject)
      .deepMerge(eoyEstimateEeaPropertyFhlWrittenJsonObject)
      .deepMerge(eoyEstimateForeignInterestWrittenJsonObject)
      .deepMerge(eoyEstimateOtherIncomeWrittenJsonObject)
      .deepMerge(eoyEstimateForeignPensionWrittenJsonObject)

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
          eoyEstimateForeignCompanyDividendsDesJson,
          eoyEstimateStateBenefitsDesJson,
          eoyEstimateUkSecuritiesDesJson,
          eoyEstimateForeignPropertyDesJson,
          eoyEstimateEeaPropertyFhlDesJson,
          eoyEstimateForeignInterestDesJson,
          eoyEstimateOtherIncomeDesJson,
          eoyEstimateForeignPensionDesJson
        ))
}