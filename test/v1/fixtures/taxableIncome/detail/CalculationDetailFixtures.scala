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

package v1.fixtures.taxableIncome.detail

import play.api.libs.json.{JsObject, JsValue, Json}
import v1.fixtures.taxableIncome.detail.DividendsFixtures._
import v1.fixtures.taxableIncome.detail.PayPensionsProfitFixtures._
import v1.fixtures.taxableIncome.detail.SavingsAndGainsFixtures._
import v1.models.response.getCalculation.taxableIncome.detail.{CalculationDetail, PayPensionsProfit}

object CalculationDetailFixtures {

  val payPensionsProfitObject: PayPensionsProfit = PayPensionsProfit(
    incomeReceivedPPP,
    taxableIncomePPP,
    totalSelfEmploymentProfit,
    totalPropertyProfit,
    totalFHLPropertyProfit,
    totalUKOtherPropertyProfit,
    None)

  val payPensionsProfitMtdJson: JsValue = Json.parse(s"""{
                                                            |    "incomeReceived" : $incomeReceivedPPP,
                                                            |    "taxableIncome" : $taxableIncomePPP,
                                                            |    "totalSelfEmploymentProfit" : ${totalSelfEmploymentProfit.get},
                                                            |    "totalPropertyProfit" : ${totalPropertyProfit.get},
                                                            |    "totalFHLPropertyProfit" : ${totalFHLPropertyProfit.get},
                                                            |    "totalUKOtherPropertyProfit" : ${totalUKOtherPropertyProfit.get}
                                                            |}""".stripMargin)

  val detailResponse           = CalculationDetail(Some(payPensionsProfitObject), Some(savingsAndGainsResponse), Some(dividendsResponse))
  val detailResponseWithoutPPP = CalculationDetail(None, Some(savingsAndGainsResponse), Some(dividendsResponse))
  val detailResponseWithoutSAG = CalculationDetail(Some(payPensionsProfitObject), None, Some(dividendsResponse))
  val detailResponseWithoutDiv = CalculationDetail(Some(payPensionsProfitObject), Some(savingsAndGainsResponse), None)

  val dividendsDesJsonWithFullPath: JsObject =
    Json.obj("calculation" -> Json.obj("taxCalculation" -> Json.obj("incomeTax" -> Json.obj("dividends" -> dividendsDesJson))))

  val detailDesJson: JsValue =
    payPensionsProfitDesJson.as[JsObject].deepMerge(Json.obj("calculation" -> savingsAndGainsDesJson).deepMerge(dividendsDesJsonWithFullPath))

  val detailDesJsonWithoutPPP: JsValue =
    Json.obj("calculation" -> savingsAndGainsDesJson).deepMerge(dividendsDesJsonWithFullPath)

  val detailDesJsonWithoutSAG: JsValue =
    payPensionsProfitDesJson.as[JsObject].deepMerge(dividendsDesJsonWithFullPath)

  val detailDesJsonWithoutDiv: JsValue =
    payPensionsProfitDesJson.as[JsObject].deepMerge(Json.obj("calculation" -> savingsAndGainsDesJson))

  val dividendsJsonComponent: JsObject         = Json.obj("dividends"         -> dividendsWrittenJson)
  val savingsAndGainsJsonComponent: JsObject   = Json.obj("savingsAndGains"   -> savingsAndGainsWrittenJson)
  val payPensionsProfitJsonComponent: JsObject = Json.obj("payPensionsProfit" -> payPensionsProfitMtdJson)

  val detailWrittenJson: JsValue           = dividendsJsonComponent.deepMerge(savingsAndGainsJsonComponent).deepMerge(payPensionsProfitJsonComponent)
  val detailWrittenJsonWithoutPPP: JsValue = dividendsJsonComponent.deepMerge(savingsAndGainsJsonComponent)
  val detailWrittenJsonWithoutSAG: JsValue = dividendsJsonComponent.deepMerge(payPensionsProfitJsonComponent)
  val detailWrittenJsonWithoutDiv: JsValue = savingsAndGainsJsonComponent.deepMerge(payPensionsProfitJsonComponent)

  val emptyDetailResponse = CalculationDetail(None, None, None)

}
