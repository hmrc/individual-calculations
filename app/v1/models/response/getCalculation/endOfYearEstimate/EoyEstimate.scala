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

package v1.models.response.getCalculation.endOfYearEstimate

import play.api.libs.functional.syntax._
import play.api.libs.json.{JsPath, Json, OWrites, Reads}
import sangria.macros.derive.deriveObjectType
import sangria.schema.ObjectType
import v1.models.response.getCalculation.endOfYearEstimate.detail.EoyEstimateDetail
import v1.models.response.getCalculation.endOfYearEstimate.summary.EoyEstimateSummary

case class EoyEstimate(summary: EoyEstimateSummary = EoyEstimateSummary.empty,
                       detail: EoyEstimateDetail = EoyEstimateDetail.empty)

object EoyEstimate {
  implicit val writes: OWrites[EoyEstimate] = Json.writes[EoyEstimate]
  implicit val reads: Reads[EoyEstimate] = (
    JsPath.read[EoyEstimateSummary] and
      JsPath.read[EoyEstimateDetail]
    ) (EoyEstimate.apply _)

  implicit def gqlType: ObjectType[Unit, EoyEstimate] = deriveObjectType[Unit, EoyEstimate]()
}
