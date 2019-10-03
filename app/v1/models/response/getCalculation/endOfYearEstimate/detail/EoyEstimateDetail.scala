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
import utils.NestedJsonReads

case class EoyEstimateDetail(selfEmployments: Option[Seq[EoyEstimateSelfEmployment]] = None,
                             ukPropertyFhl: Option[EoyEstimateUkPropertyFhl] = None,
                             ukPropertyNonFhl: Option[EoyEstimateUkPropertyNonFhl] = None,
                             ukSavings: Option[Seq[EoyEstimateUkSaving]] = None,
                             ukDividends: Option[EoyEstimateUkDividends] = None) {

  val isEmpty: Boolean = this == EoyEstimateDetail.emptyEoyEstimateDetail

}

object EoyEstimateDetail extends NestedJsonReads {

  val emptyEoyEstimateDetail: EoyEstimateDetail = EoyEstimateDetail()

  implicit val writes: OWrites[EoyEstimateDetail] = Json.format[EoyEstimateDetail]
  implicit val reads: Reads[EoyEstimateDetail] = (
    (JsPath \ "incomeSource").readNullable(filteredArrayReads[EoyEstimateSelfEmployment]("incomeSourceType", "01")).map(emptySeqToNone) and
      (JsPath \ "incomeSource").readNullable(filteredArrayReads[EoyEstimateUkPropertyFhl]("incomeSourceType", "04")).map(emptySeqToNone(_).getOrElse(Seq.empty).headOption) and
      (JsPath \ "incomeSource").readNullable(filteredArrayReads[EoyEstimateUkPropertyNonFhl]("incomeSourceType", "02")).map(emptySeqToNone(_).getOrElse(Seq.empty).headOption) and
      (JsPath \ "incomeSource").readNullable(filteredArrayReads[EoyEstimateUkSaving]("incomeSourceType", "09")).map(emptySeqToNone) and
      (JsPath \ "incomeSource").readNullable(filteredArrayReads[EoyEstimateUkDividends]("incomeSourceType", "10")).map(emptySeqToNone(_).getOrElse(Seq.empty).headOption)
  )(EoyEstimateDetail.apply _)
}
