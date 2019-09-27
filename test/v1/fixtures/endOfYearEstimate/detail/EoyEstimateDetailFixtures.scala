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

import play.api.libs.json.{ JsValue, Json }
import v1.fixtures.endOfYearEstimate.detail.EoyEstimateSelfEmploymentFixtures._
import v1.fixtures.endOfYearEstimate.detail.EoyEstimateUkDividendsFixtures._
import v1.fixtures.endOfYearEstimate.detail.EoyEstimateUkPropertyFHLFixtures._
import v1.fixtures.endOfYearEstimate.detail.EoyEstimateUkPropertyNonFHLFixtures._
import v1.fixtures.endOfYearEstimate.detail.EoyEstimateUkSavingFixtures._
import v1.models.response.getCalculation.endOfYearEstimate.detail.EoyEstimateDetail

object EoyEstimateDetailFixtures {

  val EoyEstimateDetailResponse: EoyEstimateDetail = EoyEstimateDetail(
    Some(Seq(eoyEstimateSelfEmploymentResponse)),
    Some(eoyEstimateUkPropertyFHLResponse),
    Some(eoyEstimateUkPropertyNonFHLResponse),
    Some(Seq(eoyEstimateUkSavingResponse)),
    Some(eoyEstimateUkDividendsResponse)
  )

  val EoyEstimateDetailDesJson: JsValue =
    Json.obj(
      "incomeSource" ->
        Seq(
          eoyEstimateSelfEmploymentDesJson,
          eoyEstimateUkPropertyFHLDesJson,
          eoyEstimateUkPropertyNonFHLDesJson,
          eoyEstimateUkSavingDesJson,
          eoyEstimateUkDividendsDesJson
        ))

}
