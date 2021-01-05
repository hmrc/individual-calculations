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

package v1.fixtures.getCalculation.endOfYearEstimate.detail

import play.api.libs.json.{JsObject, JsValue, Json}
import v1.models.response.getCalculation.endOfYearEstimate.detail.EoyEstimateUkSecurities

object EoyEstimateUkSecuritiesFixtures {

  val taxableIncome: BigInt = 1400

  val eoyEstimateUkSecuritiesResponse: EoyEstimateUkSecurities = EoyEstimateUkSecurities(taxableIncome)

  val eoyEstimateUkSecuritiesDesJson: JsValue = Json.parse(s"""{
      |  "taxableIncome" : $taxableIncome,
      |  "incomeSourceType" : "18"
      |}""".stripMargin)

  val eoyEstimateUkSecuritiesDesJsonWrongIncomeSourceType: JsValue = Json.parse(s"""{
      |  "taxableIncome" : $taxableIncome,
      |  "incomeSourceType" : "source"
      |}""".stripMargin)

  val eoyEstimateUkSecuritiesWrittenJson: JsValue = Json.parse(s"""{
      |  "taxableIncome" : $taxableIncome
      |}""".stripMargin)

  val eoyEstimateUkSecuritiesWrittenJsonObject: JsObject = Json.obj("ukSecurities" -> eoyEstimateUkSecuritiesWrittenJson)

  val eoyEstimateUkSecuritiesInvalidJson: JsValue = Json.parse(s"""{
      |  "taxableIncome" : "notANumericValue"
      |}""".stripMargin)
}