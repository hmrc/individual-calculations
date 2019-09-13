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
package v1.fixtures

import play.api.libs.json.{JsValue, Json}
import v1.models.response.getCalculation.taxableIncome.detail.{Dividends, Savings, SavingsAndGains}
import v1.models.response.getCalculation.taxableIncome.summary.CalculationSummary

object TaxableIncomeFixtures {

  val totalIncomeReceivedFromAllSources: BigInt = 100
  val totalTaxableIncome: BigInt = 200
  val summaryResponse: CalculationSummary = CalculationSummary(totalIncomeReceivedFromAllSources,totalTaxableIncome)

  val summaryDesJson: JsValue = Json.parse(s"""{
      |    "totalIncomeReceivedFromAllSources":$totalIncomeReceivedFromAllSources,
      |    "totalTaxableIncome":$totalTaxableIncome
      |}""".stripMargin)

  val summaryWrittenJson: JsValue = Json.parse(s"""{
      |    "totalIncomeReceivedFromAllSources":$totalIncomeReceivedFromAllSources ,
      |    "totalTaxableIncome":$totalTaxableIncome
      |}""".stripMargin)

  val summaryInvalidJson: JsValue = Json.parse(s"""{
      |    "totalIncomeReceivedFromAllSources":$totalIncomeReceivedFromAllSources
      |}""".stripMargin)


  val incomeReceived: BigInt = 100
  val taxableIncome: BigInt = 200
  val dividendsResponse: Dividends = Dividends(incomeReceived,taxableIncome)

  val dividendsDesJson: JsValue = Json.parse(s"""{
      |    "incomeReceived":$incomeReceived,
      |    "taxableIncome":$taxableIncome
      |}""".stripMargin)

  val dividendsWrittenJson: JsValue = Json.parse(s"""{
      |    "incomeReceived":$incomeReceived,
      |    "taxableIncome":$taxableIncome
      |}""".stripMargin)

  val dividendsInvalidJson: JsValue = Json.parse(s"""{
      |    "incomeReceived":$incomeReceived
      |}""".stripMargin)


  val incomeSourceId: String = "anId"
  val incomeSourceName: String = "aName"
  val grossIncome: BigDecimal = 300.1
  val netIncome: Option[BigDecimal] = Some(12.3)
  val taxDeducted: Option[BigDecimal] = Some(456.3)
  val savingsResponse: Savings = Savings(incomeSourceId, incomeSourceName, grossIncome, netIncome, taxDeducted)
  val savingsResponseWithoutOptionals: Savings = savingsResponse.copy(netIncome = None, taxDeducted = None)

  val savingsDesJson: JsValue = Json.parse(s"""{
      |    "incomeSourceId":"$incomeSourceId",
      |    "incomeSourceName":"$incomeSourceName",
      |    "grossIncome":$grossIncome,
      |    "netIncome": ${netIncome.get},
      |    "taxDeducted": ${taxDeducted.get}
      |}""".stripMargin)

  val savingsDesJsonWithoutOptionals: JsValue = Json.parse(s"""{
      |    "incomeSourceId":"$incomeSourceId",
      |    "incomeSourceName":"$incomeSourceName",
      |    "grossIncome":$grossIncome
      |}""".stripMargin)

  val savingsWrittenJson: JsValue = Json.parse(s"""{
      |    "savingsAccountId":"$incomeSourceId",
      |    "savingsAccountName":"$incomeSourceName",
      |    "grossIncome":$grossIncome,
      |    "netIncome": ${netIncome.get},
      |    "taxDeducted": ${taxDeducted.get}
      |}""".stripMargin)

  val savingsWrittenJsonWithoutOptionals: JsValue = Json.parse(s"""{
      |    "savingsAccountId":"$incomeSourceId",
      |    "savingsAccountName":"$incomeSourceName",
      |    "grossIncome":$grossIncome
      |}""".stripMargin)

  val savingsInvalidJson: JsValue = Json.parse(s"""{
      |    "incomeSourceName":"$incomeSourceName",
      |    "grossIncome":$grossIncome,
      |    "netIncome": ${netIncome.get},
      |    "taxDeducted": ${taxDeducted.get}
      |}""".stripMargin)


  val incomeReceivedSAG: BigInt = 392
  val taxableIncomeSAG: BigInt = 3920
  val savingsResponse2: Savings = Savings("anId2", "aName2", 400.1, Some(112.3), Some(556.3))
  val savingsAndGainsResponse: SavingsAndGains = SavingsAndGains(incomeReceivedSAG, taxableIncomeSAG, Some(Seq(savingsResponse, savingsResponse2)))
  val savingsAndGainsDesJson: JsValue = Json.parse(s"""{
      |   "taxCalculation" : {
      |     "incomeTax" : {
      |       "savingsAndGains": {
      |        "incomeReceived" : $incomeReceivedSAG,
      |        "taxableIncome": $taxableIncomeSAG
      |       }
      |     }
      |   },
      |   "savingsAndGainsIncome" : [
      |   {
      |     "incomeSourceId":"$incomeSourceId",
      |     "incomeSourceName":"$incomeSourceName",
      |     "grossIncome":$grossIncome,
      |     "netIncome": ${netIncome.get},
      |     "taxDeducted": ${taxDeducted.get}
      |     },
      |    {
      |     "incomeSourceId":"${incomeSourceId concat "2"}",
      |     "incomeSourceName":"${incomeSourceName concat "2"}",
      |     "grossIncome":${grossIncome + 100},
      |     "netIncome": ${netIncome.get + 100},
      |     "taxDeducted": ${taxDeducted.get + 100}
      |     }
      |     ]
      |}""".stripMargin)
}
