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

package v1.models.response.common.taxableIncome

import play.api.libs.json.{Json, Reads, Writes, __}
import play.api.libs.functional.syntax._

case class Summary (totalIncomeReceivedFromAllSources: Int, totalTaxableIncome: Int)

object Summary{
  implicit val writes: Writes[Summary] = Json.writes[Summary]
  implicit val reads: Reads[Summary] = (
    (__ \ "calculation" \ "taxCalculation" \ "totalIncomeReceivedFromAllSources").read[Int] and
      (__ \ "calculation" \ "taxCalculation" \ "totalTaxableIncome").read[Int]
    )(Summary.apply _)
}
