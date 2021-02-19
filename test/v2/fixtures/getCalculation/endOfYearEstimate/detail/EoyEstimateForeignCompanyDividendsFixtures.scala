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
import v2.models.response.getCalculation.endOfYearEstimate.detail.EoyEstimateForeignCompanyDividends

object EoyEstimateForeignCompanyDividendsFixtures {

  val taxableIncome: BigInt = 1750

  val eoyEstimateForeignCompanyDividendsResponse: EoyEstimateForeignCompanyDividends = EoyEstimateForeignCompanyDividends(taxableIncome)

  val eoyEstimateForeignCompanyDividendsDesJson: JsValue = Json.parse(s"""{
      |  "taxableIncome" : $taxableIncome,
      |  "incomeSourceType" : "07"
      |}""".stripMargin)

  val eoyEstimateForeignCompanyDividendsDesJsonWrongIncomeSourceType: JsValue = Json.parse(s"""{
      |  "taxableIncome" : $taxableIncome,
      |  "incomeSourceType" : "source"
      |}""".stripMargin)

  val eoyEstimateForeignCompanyDividendsWrittenJson: JsValue = Json.parse(s"""{
      |  "taxableIncome" : $taxableIncome
      |}""".stripMargin)

  val eoyEstimateForeignCompanyDividendsWrittenJsonObject: JsObject = Json.obj("foreignCompanyDividends" -> eoyEstimateForeignCompanyDividendsWrittenJson)

  val eoyEstimateForeignCompanyDividendsInvalidJson: JsValue = Json.parse("""{
      |  "taxableIncome" : "notANumericValue"
      |}""".stripMargin)
}