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

package v1.fixtures.taxableIncome.ukProperty

import play.api.libs.json.{JsValue, Json}
import v1.models.request.DesTaxYear
import v1.models.response.getCalculation.taxableIncome.detail.ukPropertyFhl.detail.DefaultCarriedForwardLoss

object DefaultCarriedForwardLossFixtures {


  val incomeSourceId: String        = "AAIS12345678904"
  val incomeSourceType: String      = "04"
  val taxYearLossIncurred: Int      = 2045
  val currentLossValue: BigDecimal     = 49177

  val carriedForwardLossResponse: DefaultCarriedForwardLoss = DefaultCarriedForwardLoss(
    DesTaxYear.fromDesIntToString(taxYearLossIncurred),
    currentLossValue
  )

  val carriedForwardLossDesJson: JsValue = Json.parse(f"""{
      |    "incomeSourceId": "$incomeSourceId",
      |    "incomeSourceType": "$incomeSourceType",
      |    "taxYearLossIncurred": $taxYearLossIncurred,
      |    "currentLossValue": $currentLossValue
      |}""".stripMargin)

  val carriedForwardLossDesJsonWithoutOptionals: JsValue = Json.parse(f"""{
      |    "incomeSourceId": "$incomeSourceId",
      |    "incomeSourceType": "$incomeSourceType",
      |    "taxYearLossIncurred": $taxYearLossIncurred,
      |    "currentLossValue": $currentLossValue
      |}""".stripMargin)

  val carriedForwardLossWrittenJson: JsValue = Json.parse(f"""{
      |    "taxYearLossIncurred": "${DesTaxYear.fromDesIntToString(taxYearLossIncurred)}",
      |    "currentLossValue": $currentLossValue
      |}""".stripMargin)

  val desJsonInvalidSourceType: JsValue =
    Json.parse(f"""{
                   |    "incomeSourceId": "$incomeSourceId",
                   |    "incomeSourceType": "01",
                   |    "taxYearLossIncurred": $taxYearLossIncurred,
                   |    "currentLossValue": $currentLossValue
                   |}""".stripMargin)
}
