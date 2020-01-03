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

package v1.fixtures.getCalculation.taxableIncome.detail

import play.api.libs.json.{JsValue, Json}
import v1.fixtures.getCalculation.taxableIncome.detail.SavingsFixtures._
import v1.models.response.getCalculation.taxableIncome.detail.{Savings, SavingsAndGains}

object SavingsAndGainsFixtures {
  val incomeReceivedSAG: BigInt                              = 392
  val taxableIncomeSAG: BigInt                               = 3920
  val savingsResponse2: Savings                              = Savings("anId2", "aName2", 400.1, Some(112.3), Some(556.3))
  val savingsSequence = Some(Seq(savingsResponse, savingsResponse2))
  val savingsAndGainsResponse: SavingsAndGains               = SavingsAndGains(incomeReceivedSAG, taxableIncomeSAG,savingsSequence)
  val savingsAndGainsResponseWithoutSavings: SavingsAndGains = savingsAndGainsResponse.copy(ukSavings = None)

  val savingsAndGainsDesJson: JsValue = Json.parse(s"""{
       |"calculation": {
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
       |     "incomeSourceType": "09",
       |     "incomeSourceName":"$incomeSourceName",
       |     "grossIncome":$grossIncome,
       |     "netIncome": ${netIncome.get},
       |     "taxDeducted": ${taxDeducted.get}
       |     },
       |    {
       |     "incomeSourceId":"${incomeSourceId concat "2"}",
       |     "incomeSourceType": "09",
       |     "incomeSourceName":"${incomeSourceName concat "2"}",
       |     "grossIncome":${grossIncome + 100},
       |     "netIncome": ${netIncome.get + 100},
       |     "taxDeducted": ${taxDeducted.get + 100}
       |     },
       |    {
       |     "incomeSourceId":"${incomeSourceId concat "3"}",
       |     "incomeSourceType": "04",
       |     "incomeSourceName":"${incomeSourceName concat "3"}",
       |     "grossIncome":${grossIncome + 100},
       |     "netIncome": ${netIncome.get + 100},
       |     "taxDeducted": ${taxDeducted.get + 100}
       |     }
       |     ]
       |  }
       |}""".stripMargin)

  val savingsAndGainsDesJsonWithoutSavings: JsValue = Json.parse(s"""{
       |"calculation": {
       |   "taxCalculation" : {
       |     "incomeTax" : {
       |       "savingsAndGains" :{
       |        "incomeReceived" : $incomeReceivedSAG,
       |        "taxableIncome": $taxableIncomeSAG
       |       }
       |     }
       |   }
       |  }
       |}""".stripMargin)

  val savingsAndGainsWrittenJson: JsValue = Json.parse(s"""{
       |   "incomeReceived" : $incomeReceivedSAG,
       |   "taxableIncome" : $taxableIncomeSAG,
       |   "ukSavings" : [
       |   {
       |     "savingsAccountId":"$incomeSourceId",
       |     "savingsAccountName":"$incomeSourceName",
       |     "grossIncome":$grossIncome,
       |     "netIncome": ${netIncome.get},
       |     "taxDeducted": ${taxDeducted.get}
       |     },
       |   {
       |     "savingsAccountId":"${incomeSourceId concat "2"}",
       |     "savingsAccountName":"${incomeSourceName concat "2"}",
       |     "grossIncome":${grossIncome + 100},
       |     "netIncome": ${netIncome.get + 100},
       |     "taxDeducted": ${taxDeducted.get + 100}
       |     }
       |     ]
       |}""".stripMargin)

  val savingsAndGainsWrittenJsonWithoutSavings: JsValue = Json.parse(s"""{
       |   "incomeReceived" : $incomeReceivedSAG,
       |   "taxableIncome" : $taxableIncomeSAG
       |}""".stripMargin)

  val savingsAndGainsInvalidJson: JsValue = Json.parse(s"""{
       |"calculation": {
       |   "taxCalculation" : {
       |     "incomeTax" : {
       |       "savingsAndGains" : {
       |        "taxableIncome": $taxableIncomeSAG
       |       }
       |     }
       |   }
       | }
       |}""".stripMargin)

}
