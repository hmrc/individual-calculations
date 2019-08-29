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

package v1.models.response.listCalculations

import play.api.libs.functional.syntax._
import play.api.libs.json.{JsPath, Json, Reads, Writes}
import v1.models.domain.{CalculationRequestor, CalculationType}

case class CalculationListItem(
                                id: String,
                                calculationTimestamp: String,
                                `type`: CalculationType,
                                requestedBy: Option[CalculationRequestor]
                              )

  object CalculationListItem {
    implicit val writes: Writes[CalculationListItem] = Json.writes[CalculationListItem]
    implicit val reads: Reads[CalculationListItem] =
      ((JsPath \ "calculationId").read[String] and
        (JsPath \ "calculationTimestamp").read[String] and
        (JsPath \ "calculationType").read[CalculationType] and
        (JsPath \ "requestedBy").readNullable[CalculationRequestor]
        ) (CalculationListItem.apply _)

}

case class ListCalculationsResponse(calculations: Seq[CalculationListItem])

object ListCalculationsResponse {
  implicit val writes: Writes[ListCalculationsResponse] = Json.writes[ListCalculationsResponse]
  implicit val reads: Reads[ListCalculationsResponse] =
    implicitly[Reads[Seq[CalculationListItem]]].map(ListCalculationsResponse(_))

}


