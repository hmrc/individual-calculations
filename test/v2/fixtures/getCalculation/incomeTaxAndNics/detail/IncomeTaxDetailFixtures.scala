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

package v2.fixtures.getCalculation.incomeTaxAndNics.detail

import play.api.libs.json.{JsValue, Json}
import v2.models.response.getCalculation.incomeTaxAndNics.detail
import v2.models.response.getCalculation.incomeTaxAndNics.detail.{GiftAid, IncomeTypeBreakdown}

object IncomeTaxDetailFixtures {

  val incomeTaxDetailEmptyJson: JsValue = Json.obj()

  val incomeTaxDetailEmptyModelJson: JsValue = Json.parse(
    """
      |{
      | "taxCalculation" : {
      |   "incomeTax" : {
      |
      |   }
      | }
      |}
    """.stripMargin)

  val incomeTaxDetailFilledModelJson: JsValue = Json.parse(
    """
      |{
      |   "taxCalculation":{
      |      "incomeTax":{
      |         "payPensionsProfit":{
      |            "allowancesAllocated":100,
      |            "incomeTaxAmount":100.50
      |         },
      |         "savingsAndGains":{
      |            "allowancesAllocated":200,
      |            "incomeTaxAmount":200.50
      |         },
      |         "lumpSums":{
      |            "allowancesAllocated":300,
      |            "incomeTaxAmount":300.50
      |         },
      |         "dividends":{
      |            "allowancesAllocated":400,
      |            "incomeTaxAmount":400.50
      |         },
      |         "gainsOnLifePolicies":{
      |            "allowancesAllocated":500,
      |            "incomeTaxAmount":500.50
      |         }
      |      }
      |   },
      |   "giftAid":{
      |      "grossGiftAidPayments":400,
      |      "rate":50.50,
      |      "giftAidTax":400.75,
      |      "giftAidTaxReductions": 75.50,
      |      "incomeTaxChargedAfterGiftAidTaxReductions" : 50.25,
      |      "giftAidCharge": 100
      |   }
      |}
    """.stripMargin)

  val incomeTaxDetailOutputJson: JsValue = Json.parse(
    """
      |{
      |   "payPensionsProfit":{
      |      "allowancesAllocated":100,
      |      "incomeTaxAmount":100.50
      |   },
      |   "savingsAndGains":{
      |      "allowancesAllocated":200,
      |      "incomeTaxAmount":200.50
      |   },
      |   "lumpSums":{
      |      "allowancesAllocated":300,
      |      "incomeTaxAmount":300.50
      |   },
      |   "dividends":{
      |      "allowancesAllocated":400,
      |      "incomeTaxAmount":400.50
      |   },
      |   "gainsOnLifePolicies":{
      |      "allowancesAllocated":500,
      |      "incomeTaxAmount":500.50
      |   },
      |   "giftAid":{
      |      "grossGiftAidPayments":400,
      |      "rate":50.50,
      |      "giftAidTax":400.75,
      |      "giftAidTaxReductions": 75.50,
      |      "incomeTaxChargedAfterGiftAidTaxReductions" : 50.25,
      |      "giftAidCharge": 100
      |   }
      |}
    """.stripMargin)

  def incomeTypeBreakdown(input: BigDecimal): IncomeTypeBreakdown = IncomeTypeBreakdown(input.toBigInt, input + 0.50, None)

  val incomeTaxDetailFilledModel = detail.IncomeTaxDetail(
    Some(incomeTypeBreakdown(100)),
    Some(incomeTypeBreakdown(200)),
    Some(incomeTypeBreakdown(300)),
    Some(incomeTypeBreakdown(400)),
    Some(incomeTypeBreakdown(500)),
    Some(GiftAid(400, 50.50, 400.75, Some(75.50), Some(50.25), Some(100))
    )
  )
}