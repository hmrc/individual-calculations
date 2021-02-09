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

package v1r2.fixtures.getCalculation.incomeTaxAndNics.summary

import play.api.libs.json.{JsValue, Json}
import v1r2.models.response.getCalculation.incomeTaxAndNics.summary.IncomeTaxSummary

object IncomeTaxSummaryFixtures {

  val incomeTaxSummaryJson: JsValue = Json.parse(
    """
      |{
      |   "incomeTaxCharged": 2000.00,
      |   "incomeTaxDueAfterReliefs": 1525.22,
      |   "incomeTaxDueAfterGiftAid": 120.10,
      |   "totalNotionalTax": 1900.58,
      |   "totalPensionSavingsTaxCharges": 2000.58,
      |   "statePensionLumpSumCharges": 4300.99,
      |   "incomeTaxDueAfterTaxReductions": 1300.58,
      |   "totalIncomeTaxDue": 1000.58
      |}
    """.stripMargin)

  val incomeTaxSummaryModel =
    IncomeTaxSummary(
      2000.00,
      Some(1525.22),
      Some(120.10),
      Some(1900.58),
      Some(2000.58),
      Some(4300.99),
      Some(1300.58),
      Some(1000.58)
    )
}