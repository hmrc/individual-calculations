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

package v1.models.response.getCalculation.endOfYearEstimate.detail

import play.api.libs.functional.syntax._
import play.api.libs.json.{ JsPath, Json, OWrites, Reads }
import utils.NestedJsonReads

case class EoyEstimateDetail(selfEmployments: Option[Seq[EoyEstimateSelfEmployment]],
                             ukPropertyFhl: Option[EoyEstimateUkPropertyFhl],
                             ukPropertyNonFhl: Option[EoyEstimateUkPropertyNonFhl],
                             ukSavings: Option[Seq[EoyEstimateUkSaving]],
                             ukDividends: Option[EoyEstimateUkDividends],
                             otherDividends: Option[EoyEstimateOtherDividends],
                             stateBenefits: Option[EoyEstimateStateBenefits],
                             ukSecurities: Option[EoyEstimateUkSecurities],
                             foreignProperty: Option[EoyEstimateForeignProperty],
                             foreignInterest: Option[EoyEstimateForeignInterest])

object EoyEstimateDetail extends NestedJsonReads {
  val empty = EoyEstimateDetail(None, None, None, None, None, None, None, None, None, None)

  implicit val writes: OWrites[EoyEstimateDetail] = Json.format[EoyEstimateDetail]

  implicit val reads: Reads[EoyEstimateDetail] = (
    (JsPath \ "incomeSource").readNullable(filteredArrayReads[EoyEstimateSelfEmployment]("incomeSourceType", "01")).mapEmptySeqToNone and
      (JsPath \ "incomeSource").readNullable(filteredArrayReads[EoyEstimateUkPropertyFhl]("incomeSourceType", "04")).mapHeadOption and
      (JsPath \ "incomeSource").readNullable(filteredArrayReads[EoyEstimateUkPropertyNonFhl]("incomeSourceType", "02")).mapHeadOption and
      (JsPath \ "incomeSource").readNullable(filteredArrayReads[EoyEstimateUkSaving]("incomeSourceType", "09")).mapEmptySeqToNone and
      (JsPath \ "incomeSource").readNullable(filteredArrayReads[EoyEstimateUkDividends]("incomeSourceType", "10")).mapHeadOption and
      (JsPath \ "incomeSource").readNullable(filteredArrayReads[EoyEstimateOtherDividends]("incomeSourceType", "17")).mapHeadOption and
      (JsPath \ "incomeSource").readNullable(filteredArrayReads[EoyEstimateStateBenefits]("incomeSourceType", "11")).mapHeadOption and
      (JsPath \ "incomeSource").readNullable(filteredArrayReads[EoyEstimateUkSecurities]("incomeSourceType", "18")).mapHeadOption and
      (JsPath \ "incomeSource").readNullable(filteredArrayReads[EoyEstimateForeignProperty]("incomeSourceType", "15")).mapHeadOption and
      (JsPath \ "incomeSource").readNullable(filteredArrayReads[EoyEstimateForeignInterest]("incomeSourceType", "16")).mapHeadOption
    )(EoyEstimateDetail.apply _)
}