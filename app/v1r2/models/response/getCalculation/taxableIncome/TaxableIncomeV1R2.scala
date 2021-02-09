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

package v1r2.models.response.getCalculation.taxableIncome

import play.api.libs.functional.syntax._
import play.api.libs.json.{JsPath, Json, OWrites, Reads}
import v1.models.response.getCalculation.taxableIncome.TaxableIncomeSummary

case class TaxableIncomeV1R2(summary: TaxableIncomeSummary, detail: TaxableIncomeDetailV1R2)

object TaxableIncomeV1R2 {
  implicit val reads: Reads[TaxableIncomeV1R2] = (
    (JsPath \ "calculation" \ "taxCalculation" \ "incomeTax").read[TaxableIncomeSummary] and
      JsPath.read[TaxableIncomeDetailV1R2]
    )(TaxableIncomeV1R2.apply _)

  implicit val writes: OWrites[TaxableIncomeV1R2] = Json.writes[TaxableIncomeV1R2]
}
