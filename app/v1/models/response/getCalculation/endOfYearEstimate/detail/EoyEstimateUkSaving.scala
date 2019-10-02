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

package v1.models.response.getCalculation.endOfYearEstimate.detail

import play.api.libs.functional.syntax._
import play.api.libs.json.{ JsPath, Json, OWrites, Reads }

case class EoyEstimateUkSaving(savingsAccountId: String, savingsAccountName: String, taxableIncome: BigInt)

object EoyEstimateUkSaving {
  implicit val writes: OWrites[EoyEstimateUkSaving] = Json.writes[EoyEstimateUkSaving]
  implicit val reads: Reads[EoyEstimateUkSaving] = (
    (JsPath \ "incomeSourceId").read[String] and
      (JsPath \ "incomeSourceName").read[String] and
      (JsPath \ "taxableIncome").read[BigInt]
  )(EoyEstimateUkSaving.apply _)
}
