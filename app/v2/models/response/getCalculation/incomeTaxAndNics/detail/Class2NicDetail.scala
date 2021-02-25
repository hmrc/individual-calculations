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

package v2.models.response.getCalculation.incomeTaxAndNics.detail

import play.api.libs.functional.syntax._
import play.api.libs.json.{JsPath, Json, OWrites, Reads}
import utils.NestedJsonReads

case class Class2NicDetail(weeklyRate: Option[BigDecimal],
                           weeks: Option[BigDecimal],
                           limit: Option[BigDecimal],
                           apportionedLimit: Option[BigDecimal],
                           underSmallProfitThreshold: Boolean,
                           actualClass2Nic: Option[Boolean],
                           class2VoluntaryContributions: Option[Boolean])

object Class2NicDetail extends NestedJsonReads {
  implicit val writes: OWrites[Class2NicDetail] = Json.writes[Class2NicDetail]

  implicit val reads: Reads[Class2NicDetail] = {
    val commonJsPath: JsPath = JsPath \ "calculation" \ "taxCalculation" \ "nics" \ "class2Nics"
    (
      (commonJsPath \ "weeklyRate").readNestedNullable[BigDecimal] and
        (commonJsPath \ "weeks").readNestedNullable[BigDecimal] and
        (commonJsPath \ "limit").readNestedNullable[BigDecimal] and
        (commonJsPath \ "apportionedLimit").readNestedNullable[BigDecimal] and
        (commonJsPath \ "underSmallProfitThreshold").read[Boolean] and
        (commonJsPath \ "actualClass2Nic").readNestedNullable[Boolean] and
        (JsPath \ "inputs" \ "personalInformation" \ "class2VoluntaryContributions").readNestedNullable[Boolean]
    )(Class2NicDetail.apply _)
  }
}