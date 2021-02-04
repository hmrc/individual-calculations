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

package v2.fixtures.getCalculation.endOfYearEstimate

import play.api.libs.json.{JsObject, JsValue, Json}
import v2.fixtures.getCalculation.endOfYearEstimate.detail.EoyEstimateDetailFixtures._
import v2.fixtures.getCalculation.endOfYearEstimate.summary.EoyEstimateSummaryFixtures._
import v2.models.response.getCalculation.endOfYearEstimate.EoyEstimate

object EoyEstimateFixtures {

  val eoyEstimateResponse: EoyEstimate           = EoyEstimate(eoyEstimateSummaryResponse, eoyEstimateDetailResponse)

  val eoyEstimateDesJson: JsValue                = eoyEstimateDetailDesJson.deepMerge(eoyEstimateSummaryDesJson.as[JsObject])

  val eoyEstimateSummaryDesJsonTopLevel: JsValue = Json.obj("calculation" -> Json.obj("endOfYearEstimate" -> eoyEstimateDesJson))

  val eoyEstimateWrittenJson: JsObject           = eoyEstimateSummaryWrittenJsonObject.deepMerge(eoyEstimateDetailWrittenJsonObject)

  val eoyEstimateWrittenJsonEmpty: JsObject      = Json.obj("summary" -> JsObject.empty).deepMerge(Json.obj("detail" -> JsObject.empty))

  val eoyEstimateInvalidJson: JsValue            = eoyEstimateDetailInvalidJson.as[JsObject].deepMerge(eoyEstimateSummaryInvalidJson.as[JsObject])
}
