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

package v1.models.response.getCalculation.incomeTaxAndNics.detail

import play.api.libs.json.{JsPath, Json, OWrites, Reads}
import play.api.libs.functional.syntax._
import v1.models.response.getCalculation.endOfYearEstimate.detail.EoyEstimateDetail.emptySeqToNone

case class Class4NicDetail(class4Losses: Option[Class4Losses],
                           totalIncomeLiableToClass4Charge: Option[BigInt],
                           totalIncomeChargeableToClass4: Option[BigInt],
                           class4NicBands: Option[Seq[NicBand]]){
  val isEmpty: Boolean = this == Class4NicDetail.emptyClass4NicDetail
}

object Class4NicDetail{
  val emptyClass4NicDetail: Class4NicDetail = Class4NicDetail(None, None, None, None)
  implicit val writes: OWrites[Class4NicDetail] = Json.writes[Class4NicDetail]
  implicit val reads: Reads[Class4NicDetail] = (
    JsPath.readNullable[Class4Losses].map{
      case Some(Class4Losses.emptyClass4Losses) => None
      case notEmpty => notEmpty
    } and
      (JsPath \ "totalIncomeLiableToClass4Charge").readNullable[BigInt] and
      (JsPath \ "totalIncomeChargeableToClass4").readNullable[BigInt] and
      (JsPath \ "nic4Bands").readNullable[Seq[NicBand]].map(emptySeqToNone)
    )(Class4NicDetail.apply _)
}
