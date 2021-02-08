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

package v1r2.models.response.getCalculation.endOfYearEstimate.detail

import play.api.libs.functional.syntax._
import play.api.libs.json.{ JsPath, Json, OWrites, Reads }

case class EoyEstimateSelfEmployment(selfEmploymentId: String, taxableIncome: BigInt, finalised: Option[Boolean])

object EoyEstimateSelfEmployment {
  implicit val writes: OWrites[EoyEstimateSelfEmployment] = Json.writes[EoyEstimateSelfEmployment]
  implicit val reads: Reads[EoyEstimateSelfEmployment] = (
    (JsPath \ "incomeSourceId").read[String] and
      (JsPath \ "taxableIncome").read[BigInt] and
      (JsPath \ "finalised").readNullable[Boolean]
  )(EoyEstimateSelfEmployment.apply _)
}
