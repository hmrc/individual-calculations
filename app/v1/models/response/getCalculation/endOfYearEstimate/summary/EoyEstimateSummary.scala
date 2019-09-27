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

package v1.models.response.getCalculation.endOfYearEstimate.summary

import play.api.libs.json.{ Json, OFormat }

case class EoyEstimateSummary(totalEstimatedIncome: Option[BigInt] = None,
                              totalTaxableIncome: Option[BigInt] = None,
                              incomeTaxAmount: Option[BigDecimal] = None,
                              nic2: Option[BigDecimal] = None,
                              nic4: Option[BigDecimal] = None,
                              totalNicAmount: Option[BigDecimal] = None,
                              incomeTaxNicAmount: Option[BigDecimal] = None)

object EoyEstimateSummary {
  implicit val formats: OFormat[EoyEstimateSummary] = Json.format[EoyEstimateSummary]
}
