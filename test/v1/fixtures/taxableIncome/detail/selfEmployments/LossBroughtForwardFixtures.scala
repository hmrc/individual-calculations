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

package v1.fixtures.taxableIncome.detail.selfEmployments

import play.api.libs.json.{JsValue, Json}
import v1.models.domain.TypeOfLoss
import v1.models.request.DesTaxYear
import v1.models.response.getCalculation.taxableIncome.detail.selfEmployment.detail.LossBroughtForward

object LossBroughtForwardFixtures {
  val lossId: String               = "0yriP9QrW2jTa6n"
  val incomeSourceId: String       = "AAIS12345678904"
  val incomeSourceType: String     = "01"
  val submissionTimestamp: String  = "2019-07-13T07:51:43Z"
  val lossType                     = "income"
  val mtdLossType                  = "self-employment"
  val taxYearLossIncurred: Int     = 2055
  val currentLossValue: BigDecimal = 673350334
  val mtdLoss: Boolean             = false

  val lossBroughtForwardResponse: LossBroughtForward =
    LossBroughtForward(TypeOfLoss.`self-employment`, DesTaxYear.fromDesIntToString(taxYearLossIncurred), currentLossValue, mtdLoss, incomeSourceId)

  val lossBroughtForwardResponseWithDifferentId: LossBroughtForward = lossBroughtForwardResponse.copy(incomeSourceId = "1LS40C6DM10SM45")

  val lossBroughtForwardResponseWithoutMtdLoss: LossBroughtForward = lossBroughtForwardResponse.copy(mtdLoss = true)

  val lossBroughtForwardDesJson: JsValue = Json.parse(s"""{
      |    "lossId": "$lossId",
      |    "incomeSourceId": "$incomeSourceId",
      |    "incomeSourceType": "$incomeSourceType",
      |    "submissionTimestamp": "$submissionTimestamp",
      |    "lossType": "$lossType",
      |    "taxYearLossIncurred": $taxYearLossIncurred,
      |    "currentLossValue": $currentLossValue,
      |    "mtdLoss": $mtdLoss
      |}""".stripMargin)

  val lossBroughtForwardDesJsonWithWrongIncomeSourceType: JsValue = Json.parse(s"""{
      |    "lossId": "$lossId",
      |    "incomeSourceId": "$incomeSourceId",
      |    "incomeSourceType": "02",
      |    "submissionTimestamp": "$submissionTimestamp",
      |    "lossType": "$lossType",
      |    "taxYearLossIncurred": $taxYearLossIncurred,
      |    "currentLossValue": $currentLossValue,
      |    "mtdLoss": $mtdLoss
      |}""".stripMargin)

  val lossBroughtForwardDesJsonWithoutMtdLoss: JsValue = Json.parse(s"""{
      |    "lossId": "$lossId",
      |    "incomeSourceId": "$incomeSourceId",
      |    "incomeSourceType": "$incomeSourceType",
      |    "submissionTimestamp": "$submissionTimestamp",
      |    "lossType": "$lossType",
      |    "taxYearLossIncurred": $taxYearLossIncurred,
      |    "currentLossValue": $currentLossValue
      |}""".stripMargin)

  val lossBroughtForwardWrittenJson: JsValue = Json.parse(f"""{
      |    "lossType": "$mtdLossType",
      |    "taxYearLossIncurred": "${DesTaxYear.fromDesIntToString(taxYearLossIncurred)}",
      |    "currentLossValue": $currentLossValue,
      |    "mtdLoss": $mtdLoss
      |}""".stripMargin)

  val lossBroughtForwardWrittenJsonWithoutMtdLoss: JsValue = Json.parse(f"""{
      |    "lossType": "$mtdLossType",
      |    "taxYearLossIncurred": "${DesTaxYear.fromDesIntToString(taxYearLossIncurred)}",
      |    "currentLossValue": $currentLossValue,
      |    "mtdLoss": true
      |}""".stripMargin)

  val lossBroughtForwardInvalidJson: JsValue = Json.parse(f"""{
      |    "lossType": "$mtdLossType",
      |    "taxYearLossIncurred": "$taxYearLossIncurred}",
      |    "currentLossValue": $currentLossValue,
      |    "mtdLoss": $mtdLoss
      |}""".stripMargin)

}
