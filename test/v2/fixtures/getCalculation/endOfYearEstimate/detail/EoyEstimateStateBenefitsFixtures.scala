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
import v2.models.response.getCalculation.endOfYearEstimate.detail.EoyEstimateStateBenefits

object EoyEstimateStateBenefitsFixtures {

  val taxableIncome: BigInt = 1300

  val eoyEstimateStateBenefitsResponse: EoyEstimateStateBenefits = EoyEstimateStateBenefits(taxableIncome)

  val eoyEstimateStateBenefitsDesJson: JsValue = Json.parse(s"""{
      |  "taxableIncome" : $taxableIncome,
      |  "incomeSourceType" : "11"
      |}""".stripMargin)

  val eoyEstimateStateBenefitsDesJsonWrongIncomeSourceType: JsValue = Json.parse(s"""{
      |  "taxableIncome" : $taxableIncome,
      |  "incomeSourceType" : "source"
      |}""".stripMargin)

  val eoyEstimateStateBenefitsWrittenJson: JsValue = Json.parse(s"""{
      |  "taxableIncome" : $taxableIncome
      |}""".stripMargin)

  val eoyEstimateStateBenefitsWrittenJsonObject: JsObject = Json.obj("stateBenefits" -> eoyEstimateStateBenefitsWrittenJson)

  val eoyEstimateStateBenefitsInvalidJson: JsValue = Json.parse(s"""{
      |  "taxableIncome" : "notANumericValue"
      |}""".stripMargin)
}