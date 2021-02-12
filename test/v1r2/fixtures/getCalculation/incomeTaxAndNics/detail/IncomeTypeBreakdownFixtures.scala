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

package v1r2.fixtures.getCalculation.incomeTaxAndNics.detail

import play.api.libs.json.{JsValue, Json}
import v1r2.models.response.getCalculation.incomeTaxAndNics.detail.{IncomeTypeBreakdown, TaxBand}

object IncomeTypeBreakdownFixtures {

  val incomeTypeBreakdownJson: JsValue = Json.parse(
    """
      |{
      | "allowancesAllocated" : 100,
      | "incomeTaxAmount" : 200.25,
      | "taxBands" : [
      |   {
      |     "name" : "name",
      |     "rate" : 50.50,
      |     "bandLimit" : 400,
      |     "apportionedBandLimit" : 500,
      |     "income" : 600,
      |     "taxAmount" : 700.25
      |   }
      | ]
      |}
    """.stripMargin)

  val incomeTypeBreakdownModel =
    IncomeTypeBreakdown(100, 200.25,
      Some(
        Seq(
          TaxBand(
            "name", 50.50, 400, 500, 600, 700.25
          )
        )
      )
    )
}