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
import v1.models.response.getCalculation.endOfYearEstimate.detail.EoyEstimateUkDividends

object EoyEstimateUkDividendsFixtures {

  val taxableIncome: BigInt = 1021

  val eoyEstimateUkDividendsResponse: EoyEstimateUkDividends =
    EoyEstimateUkDividends(taxableIncome)

  val eoyEstimateUkDividendsDesJson: JsValue = Json.parse(s"""{
      |  "taxableIncome": $taxableIncome,
      |  "incomeSourceType" : "10"
      |}""".stripMargin)

  val eoyEstimateUkDividendsDesJsonWrongIncomeSourceType: JsValue = Json.parse(s"""{
      |  "taxableIncome": $taxableIncome,
      |  "incomeSourceType" : "wrong"
      |}""".stripMargin)


  val eoyEstimateUkDividendsDesJsonMissingFields: JsValue = Json.parse(s"""{
      |  "taxableIncome": $taxableIncome,
      |  "incomeSourceType" : "10"
      |}""".stripMargin)

  val eoyEstimateUkDividendsWrittenJson: JsValue = Json.parse(s"""{
      |  "taxableIncome": $taxableIncome
      |}""".stripMargin)

  val eoyEstimateUkDividendsWrittenJsonObject: JsObject = Json.obj("ukDividends" -> eoyEstimateUkDividendsWrittenJson)

  val eoyEstimateUkDividendsInvalidJson: JsValue = Json.parse(s"""{
      |  "taxableIncome": "notANumericValue"
      |}""".stripMargin)

  def eoyEstimateUkDividendsResponseFactory(totalEstimatedIncome: BigInt = taxableIncome): EoyEstimateUkDividends =
    EoyEstimateUkDividends(totalEstimatedIncome)

}
