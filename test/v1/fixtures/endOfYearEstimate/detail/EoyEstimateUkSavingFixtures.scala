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

package v1.fixtures.endOfYearEstimate.detail

import play.api.libs.json.{ JsObject, JsValue, Json }
import v1.models.response.getCalculation.endOfYearEstimate.detail.EoyEstimateUkSaving

object EoyEstimateUkSavingFixtures {

  val savingsAccountId: String   = "AA123456789"
  val savingsAccountName: String = "An Account Name"
  val taxableIncome: BigInt      = 1051

  val eoyEstimateUkSavingResponse: EoyEstimateUkSaving =
    EoyEstimateUkSaving(savingsAccountId, savingsAccountName, taxableIncome)

  val eoyEstimateUkSavingDesJson: JsValue = Json.parse(s"""{
      |  "savingsAccountId": "$savingsAccountId",
      |  "savingsAccountName": "$savingsAccountName",
      |  "taxableIncome" : $taxableIncome,
      |  "incomeSourceType" : "09"
      |}""".stripMargin)

  val eoyEstimateUkSavingDesJsonWrongIncomeSourceType: JsValue = Json.parse(s"""{
      |  "savingsAccountId": "$savingsAccountId",
      |  "savingsAccountName": "$savingsAccountName",
      |  "taxableIncome" : $taxableIncome,
      |  "incomeSourceType" : "wrong"
      |}""".stripMargin)

  val eoyEstimateUkSavingDesJsonMissingFields: JsValue = Json.parse(s"""{
      |  "savingsAccountId": "$savingsAccountId",
      |  "savingsAccountName": "$savingsAccountName",
      |  "taxableIncome" : $taxableIncome,
      |  "incomeSourceType" : "09"
      |}""".stripMargin)

  val eoyEstimateUkSavingWrittenJson: JsValue = Json.parse(s"""{
      |  "savingsAccountId": "$savingsAccountId",
      |  "savingsAccountName": "$savingsAccountName",
      |  "taxableIncome" : $taxableIncome
      |}""".stripMargin)

  val eoyEstimateUkSavingWrittenJsonObject: JsObject = Json.obj("ukSavings" -> Seq(eoyEstimateUkSavingWrittenJson,eoyEstimateUkSavingWrittenJson))

  val eoyEstimateUkSavingInvalidJson: JsValue = Json.parse(s"""{
      |  "savingsAccountId": "$savingsAccountId",
      |  "savingsAccountName": "$savingsAccountName",
      |  "taxableIncome" : "notANumericValue"
      |}""".stripMargin)

  def eoyEstimateUkSavingResponseFactory(savingsAccountId: String = savingsAccountId,
                                         savingsAccountName: String = savingsAccountName,
                                         taxableIncome: BigInt = taxableIncome): EoyEstimateUkSaving =
    EoyEstimateUkSaving(savingsAccountId, savingsAccountName, taxableIncome)

}
