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
import play.api.libs.json._
import utils.NestedJsonReads

case class CalculationDetail(incomeTax: IncomeTaxDetail,
                             studentLoans: Option[Seq[StudentLoans]],
                             pensionSavingsTaxCharges: Option[PensionSavingsTaxCharges],
                             nics: Option[NicDetail],
                             taxDeductedAtSource: Option[TaxDeductedAtSource])

object CalculationDetail extends NestedJsonReads {
  implicit val writes: OWrites[CalculationDetail] = Json.writes[CalculationDetail]

  implicit val reads: Reads[CalculationDetail] = {
    val commonJsPath: JsPath = JsPath \ "calculation"
    (
      commonJsPath.read[IncomeTaxDetail] and
        (commonJsPath \ "studentLoans").readNestedNullable[Seq[StudentLoans]] and
        (commonJsPath \ "pensionSavingsTaxCharges").readNestedNullable[PensionSavingsTaxCharges] and
        emptyIfNotPresent[NicDetail](commonJsPath \ "taxCalculation" \ "nics").mapEmptyModelToNone(NicDetail.empty) and
        (commonJsPath \ "taxDeductedAtSource").readNestedNullable[TaxDeductedAtSource].mapEmptyModelToNone(TaxDeductedAtSource.empty)
    )(CalculationDetail.apply _)
  }
}