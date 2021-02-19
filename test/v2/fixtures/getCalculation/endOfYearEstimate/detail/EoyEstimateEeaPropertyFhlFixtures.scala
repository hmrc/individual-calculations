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

package v2.fixtures.getCalculation.endOfYearEstimate.detail

import play.api.libs.json.{JsObject, JsValue, Json}
import v2.models.response.getCalculation.endOfYearEstimate.detail.EoyEstimateEeaPropertyFhl

object EoyEstimateEeaPropertyFhlFixtures {

  val taxableIncome: BigInt = 1700

  val eoyEstimateEeaPropertyFhlResponse: EoyEstimateEeaPropertyFhl = EoyEstimateEeaPropertyFhl(taxableIncome)

  val eoyEstimateEeaPropertyFhlDesJson: JsValue = Json.parse(s"""{
      |  "taxableIncome" : $taxableIncome,
      |  "incomeSourceType" : "03"
      |}""".stripMargin)

  val eoyEstimateEeaPropertyFhlDesJsonWrongIncomeSourceType: JsValue = Json.parse(s"""{
      |  "taxableIncome" : $taxableIncome,
      |  "incomeSourceType" : "source"
      |}""".stripMargin)

  val eoyEstimateEeaPropertyFhlWrittenJson: JsValue = Json.parse(s"""{
      |  "taxableIncome" : $taxableIncome
      |}""".stripMargin)

  val eoyEstimateEeaPropertyFhlWrittenJsonObject: JsObject = Json.obj("eeaPropertyFhl" -> eoyEstimateEeaPropertyFhlWrittenJson)

  val eoyEstimateEeaPropertyFhlInvalidJson: JsValue = Json.parse("""{
      |  "taxableIncome" : "notANumericValue"
      |}""".stripMargin)
}