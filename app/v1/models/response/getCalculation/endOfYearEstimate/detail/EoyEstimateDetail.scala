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

import play.api.libs.json.{JsPath, Json, OWrites, Reads}
import play.api.libs.functional.syntax._
import utils.NestedJsonReads
import utils.FilterTools.readSequenceAndMapToType

case class EoyEstimateDetail(selfEmployments: Option[Seq[EoyEstimateSelfEmployment]],
                             ukPropertyFHL: Option[EoyEstimateUkPropertyFHL],
                             ukPropertyNonFHL: Option[EoyEstimateUkPropertyNonFHL],
                             ukSavings: Option[Seq[EoyEstimateUkSaving]],
                             ukDividends: Option[EoyEstimateUkDividends])

object EoyEstimateDetail extends NestedJsonReads{
  implicit val writes: OWrites[EoyEstimateDetail] = Json.format[EoyEstimateDetail]
  implicit val reads: Reads[EoyEstimateDetail] = (
    readSequenceAndMapToType[EoyEstimateSelfEmployment](JsPath \ "incomeSource") and
      readSequenceAndMapToType[EoyEstimateUkPropertyFHL](JsPath \ "incomeSource", "04").map(_.getOrElse(Seq.empty).headOption) and
      readSequenceAndMapToType[EoyEstimateUkPropertyNonFHL](JsPath \ "incomeSource", "02").map(_.getOrElse(Seq.empty).headOption) and
      readSequenceAndMapToType[EoyEstimateUkSaving] (JsPath \ "incomeSource", "09") and
      readSequenceAndMapToType[EoyEstimateUkDividends] (JsPath \ "incomeSource", "10").map(_.getOrElse(Seq.empty).headOption)
    )(EoyEstimateDetail.apply _)
}
