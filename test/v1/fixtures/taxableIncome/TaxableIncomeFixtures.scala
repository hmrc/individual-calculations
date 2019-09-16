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

package v1.fixtures.taxableIncome

import play.api.libs.json
import play.api.libs.json.{ JsObject, Json }
import v1.fixtures.taxableIncome.detail.CalculationDetailFixtures._
import v1.fixtures.taxableIncome.summary.CalculationSummaryFixtures._
import v1.models.response.getCalculation.taxableIncome.TaxableIncome

object TaxableIncomeFixtures {

  val taxableIncomeDesJson: JsObject = detailDesJson
    .as[json.JsObject]
    .deepMerge(Json.obj("calculation" -> Json.obj("taxCalculation" -> summaryDesJson)))

  val taxableIncomeResponse: TaxableIncome = TaxableIncome(summaryResponse, detailResponse)

  val taxableIncomeWrittenJson: JsObject = Json
    .obj("detail" -> detailWrittenJson)
    .deepMerge(Json.obj("summary" -> summaryWrittenJson))

  val emptyJson: JsObject = JsObject.empty
}
