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

package v1.models.response.getCalculation.taxableIncome.detail.payPensionsProfit.selfEmployment

import play.api.libs.functional.syntax._
import play.api.libs.json._

case class SelfEmploymentBsas(bsasId: String, applied: Boolean, incomeSourceId: String)

object SelfEmploymentBsas {
  implicit val reads: Reads[SelfEmploymentBsas] = (
    (JsPath \ "ascId").read[String] and
      (JsPath \ "applied").read[Boolean] and
      (JsPath \ "incomeSourceId").read[String]
  )(SelfEmploymentBsas.apply _)

  implicit val writes: OWrites[SelfEmploymentBsas] = (selfEmploymentBsas: SelfEmploymentBsas) =>
    Json.toJsObject(selfEmploymentBsas)(Json.writes[SelfEmploymentBsas]) - "incomeSourceId"
}
