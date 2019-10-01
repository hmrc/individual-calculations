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

package v1.fixtures.endOfYearEstimate.detail

import play.api.libs.json.{JsObject, JsValue, Json}
import v1.fixtures.endOfYearEstimate.detail.EoyEstimateSelfEmploymentFixtures._
import v1.fixtures.endOfYearEstimate.detail.EoyEstimateUkDividendsFixtures._
import v1.fixtures.endOfYearEstimate.detail.EoyEstimateUkPropertyFHLFixtures._
import v1.fixtures.endOfYearEstimate.detail.EoyEstimateUkPropertyNonFHLFixtures._
import v1.fixtures.endOfYearEstimate.detail.EoyEstimateUkSavingFixtures._
import v1.models.response.getCalculation.endOfYearEstimate.detail.EoyEstimateDetail

object EoyEstimateDetailFixtures {

  val eoyEstimateDetailResponse: EoyEstimateDetail = EoyEstimateDetail(
    Some(Seq(eoyEstimateSelfEmploymentResponse)),
    Some(eoyEstimateUkPropertyFHLResponse),
    Some(eoyEstimateUkPropertyNonFHLResponse),
    Some(Seq(eoyEstimateUkSavingResponse, eoyEstimateUkSavingResponse)),
    Some(eoyEstimateUkDividendsResponse)
  )

  val eoyEstimateDetailResponseReduced: EoyEstimateDetail = EoyEstimateDetail(
    selfEmployments = Some(Seq(eoyEstimateSelfEmploymentResponse)),
    ukSavings = Some(Seq(eoyEstimateUkSavingResponse, eoyEstimateUkSavingResponse))
  )

  val eoyEstimateDetailDesJson: JsObject =
    Json.obj(
      "incomeSource" ->
        Seq(
          eoyEstimateSelfEmploymentDesJson,
          eoyEstimateUkPropertyFHLDesJson,
          eoyEstimateUkPropertyNonFHLDesJson,
          eoyEstimateUkSavingDesJson,
          eoyEstimateUkSavingDesJson,
          eoyEstimateUkDividendsDesJson
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
          eoyEstimateUkPropertyFHLDesJsonWrongIncomeSourceType,
          eoyEstimateUkPropertyNonFHLDesJsonWrongIncomeSourceType,
          eoyEstimateUkSavingDesJson,
          eoyEstimateUkSavingDesJson,
          eoyEstimateUkDividendsDesJsonWrongIncomeSourceType
        ))

  val eoyEstimateDetailDesJsonAllWrongIncomeSourceTypes: JsValue =
    Json.obj(
      "incomeSource" ->
        Seq(
          eoyEstimateSelfEmploymentDesJsonWrongIncomeSourceType,
          eoyEstimateUkPropertyFHLDesJsonWrongIncomeSourceType,
          eoyEstimateUkPropertyNonFHLDesJsonWrongIncomeSourceType,
          eoyEstimateUkSavingDesJsonWrongIncomeSourceType,
          eoyEstimateUkDividendsDesJsonWrongIncomeSourceType
        ))

  val eoyEstimateDetailWrittenJson: JsValue =
    eoyEstimateSelfEmploymentWrittenJsonObject
      .deepMerge(eoyEstimateUkPropertyFHLWrittenJsonObject)
      .deepMerge(eoyEstimateUkPropertyNonFHLWrittenJsonObject)
      .deepMerge(eoyEstimateUkSavingWrittenJsonObject)
      .deepMerge(eoyEstimateUkDividendsWrittenJsonObject)

  val eoyEstimateDetailWrittenJsonObject: JsObject = Json.obj("detail" -> eoyEstimateDetailWrittenJson)

  val eoyEstimateDetailWrittenJsonMissingOptionals: JsValue =
    eoyEstimateSelfEmploymentWrittenJsonObject
      .deepMerge(eoyEstimateUkSavingWrittenJsonObject)

  val eoyEstimateDetailInvalidJson: JsValue =
    Json.obj(
      "incomeSource" ->
        Seq(
          eoyEstimateSelfEmploymentInvalidJson,
          eoyEstimateUkPropertyFHLDesJson,
          eoyEstimateUkPropertyNonFHLDesJson,
          eoyEstimateUkSavingDesJson,
          eoyEstimateUkDividendsDesJson
        ))
}
