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

import play.api.libs.json.{ JsValue, Json }
import v1.models.des.LossType
import v1.models.request.DesTaxYear
import v1.models.response.taxableIncome.selfEmployments.UnclaimedLoss

object UnclaimedLossFixtures {

  val incomeSourceId: String   = "AAIS12345678904"
  val incomeSourceType: String = "01"
  val taxYearLossIncurred: Int = 2051
  val lossType: String         = "income"
  val currentLossValue: BigInt = 71438847594L
  val expires: Int             = 2079

  val unclaimedLossResponse: UnclaimedLoss = UnclaimedLoss(
    DesTaxYear.fromDesIntToString(taxYearLossIncurred),
    currentLossValue,
    DesTaxYear.fromDesIntToString(expires),
    LossType.income
  )

  val unclaimedLossDesJson: JsValue = Json.parse(s"""{
      |    "incomeSourceId": "$incomeSourceId",
      |    "incomeSourceType": "$incomeSourceType",
      |    "taxYearLossIncurred": $taxYearLossIncurred,
      |    "currentLossValue": $currentLossValue,
      |    "expires": $expires,
      |    "lossType": "$lossType"
      |}""".stripMargin)

  val unclaimedLossDesJsonWithWrongIncomeSourceType: JsValue = Json.parse(s"""{
      |    "incomeSourceId": "$incomeSourceId",
      |    "incomeSourceType": "02",
      |    "taxYearLossIncurred": $taxYearLossIncurred,
      |    "currentLossValue": $currentLossValue,
      |    "expires": $expires,
      |    "lossType": "$lossType"
      |}""".stripMargin)

  val unclaimedLossWrittenJson: JsValue = Json.parse(f"""
      |  {
      |    "taxYearLossIncurred": "${DesTaxYear.fromDesIntToString(taxYearLossIncurred)}",
      |    "currentLossValue": $currentLossValue,
      |    "expires": "${DesTaxYear.fromDesIntToString(expires)}",
      |    "lossType": "$lossType"
      |}""".stripMargin)

  val unclaimedLossInvalidJson: JsValue = Json.parse(s"""{
      |    "incomeSourceId": "$incomeSourceId",
      |    "incomeSourceType": "$incomeSourceType",
      |    "taxYearLossIncurred": $taxYearLossIncurred,
      |    "currentLossValue": $currentLossValue,
      |    "expires": $expires
      |}""".stripMargin)

}
