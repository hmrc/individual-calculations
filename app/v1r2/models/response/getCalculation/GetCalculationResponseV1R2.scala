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

package v1r2.models.response.getCalculation

import play.api.libs.functional.syntax._
import play.api.libs.json._
import utils.NestedJsonReads
import v1.models.response.common.{Messages, Metadata}
import v1.models.response.getCalculation.allowancesAndDeductions.AllowancesDeductionsAndReliefs
import v1.models.response.getCalculation.endOfYearEstimate.EoyEstimate
import v1.models.response.getCalculation.incomeTaxAndNics.IncomeTax
import v1r2.models.response.getCalculation.taxableIncome.TaxableIncomeV1R2

case class GetCalculationResponseV1R2(metadata: Metadata,
                                      incomeTaxAndNicsCalculated: Option[IncomeTax] = None,
                                      messages: Option[Messages] = None,
                                      taxableIncome: Option[TaxableIncomeV1R2] = None,
                                      endOfYearEstimate: Option[EoyEstimate] = None,
                                      allowancesDeductionsAndReliefs: Option[AllowancesDeductionsAndReliefs] = None)

object GetCalculationResponseV1R2 extends NestedJsonReads {
  implicit val writes: OWrites[GetCalculationResponseV1R2] = Json.writes[GetCalculationResponseV1R2]

  implicit val reads: Reads[GetCalculationResponseV1R2] =
    (
      JsPath.read[Metadata] and
        emptyIfNotPresent[IncomeTax](__ \ "calculation") and
        JsPath.readNullable[Messages].map {
          case Some(Messages.empty) => None
          case other => other
        } and
        emptyIfNotPresent[TaxableIncomeV1R2](__ \ "calculation") and
        (__ \ "calculation" \ "endOfYearEstimate").readNestedNullable[EoyEstimate] and
        emptyIfNotPresent[AllowancesDeductionsAndReliefs](__ \ "calculation")
      ) (GetCalculationResponseV1R2.apply _)
}

