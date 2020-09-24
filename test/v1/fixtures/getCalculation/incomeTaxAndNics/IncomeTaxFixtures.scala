/*
 * Copyright 2020 HM Revenue & Customs
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

package v1.fixtures.getCalculation.incomeTaxAndNics

import play.api.libs.json.{JsValue, Json}
import v1.models.response.getCalculation.incomeTaxAndNics.IncomeTax
import v1.models.response.getCalculation.incomeTaxAndNics.detail._
import v1.models.response.getCalculation.incomeTaxAndNics.summary._

object IncomeTaxFixtures {

  val json: JsValue = Json.parse("""
      |{
      |  "calculation" : {
      |   "taxCalculation" : {
      |     "incomeTax" : {
      |       "incomeTaxCharged" : 100.25,
      |       "payPensionsProfit" : {
      |        "allowancesAllocated" : 300,
      |        "incomeTaxAmount" : 400.25
      |       }
      |     },
      |     "totalIncomeTaxAndNicsDue" : 200.25
      |   }
      | },
      | "inputs" : {
      |  "personalInformation" : {
      |    "taxRegime" : "UK"
      |  }
      | }
      |}
    """.stripMargin)

  val outputJson: JsValue = Json.parse("""
      |{
      | "summary" : {
      |   "incomeTax" : {
      |     "incomeTaxCharged" : 100.25
      |   },
      |   "totalIncomeTaxAndNicsDue" : 200.25,
      |   "taxRegime" : "UK"
      | },
      | "detail" : {
      |   "incomeTax" : {
      |     "payPensionsProfit" : {
      |        "allowancesAllocated" : 300,
      |        "incomeTaxAmount" : 400.25
      |     }
      |   }
      | }
      |}
    """.stripMargin)

  val calcSummary = CalculationSummary(IncomeTaxSummary(100.25, None, None), None, None, None, 200.25, "UK")
  val calcDetail  = CalculationDetail(IncomeTaxDetail(Some(IncomeTypeBreakdown(300, 400.25, None)), None, None, None), None, None)
  val model       = IncomeTax(calcSummary, calcDetail)

  val fullCalcSummary = CalculationSummary(
    incomeTax = IncomeTaxSummary(100.25, Some(100.25), Some(100.25)),
    nics = Some(NicSummary(Some(100.25), Some(100.25), Some(100.25))),
    totalIncomeTaxNicsCharged = Some(100.25),
    totalTaxDeducted = Some(100.25),
    totalIncomeTaxAndNicsDue = 200.25,
    taxRegime = "UK"
  )
  val fullCalcDetail  = CalculationDetail(
    incomeTax = IncomeTaxDetail(
      payPensionsProfit = Some(IncomeTypeBreakdown(1, 400.25, Some(Seq(TaxBand("BandA", 100.25, 1, 1, 1, 100.25))))),
      savingsAndGains = Some(IncomeTypeBreakdown(1, 400.25, Some(Seq(TaxBand("BandA", 100.25, 1, 1, 1, 100.25))))),
      dividends = Some(IncomeTypeBreakdown(1, 400.25, Some(Seq(TaxBand("BandA", 100.25, 1, 1, 1, 100.25))))),
      giftAid = Some(GiftAid(100.25, 100.25, 100.25))
    ),
    nics = Some(NicDetail(
      class2Nics = Some(Class2NicDetail(Some(100.25), Some(100.25), Some(100.25), Some(100.25), true, Some(true))),
      class4Nics = Some(Class4NicDetail(
        class4Losses = Some(Class4Losses(Some(1), Some(1), Some(1))),
        totalIncomeLiableToClass4Charge = Some(1),
        totalIncomeChargeableToClass4 = Some(1),
        class4NicBands = Some(Seq(NicBand("BandA", 100.25, Some(1), Some(1), 1, 100.25)))
      ))
    )),
    taxDeductedAtSource = Some(TaxDeductedAtSource(Some(100.25), Some(100.25), Some(100.25))))
  val fullModel = IncomeTax(fullCalcSummary, fullCalcDetail)

}
