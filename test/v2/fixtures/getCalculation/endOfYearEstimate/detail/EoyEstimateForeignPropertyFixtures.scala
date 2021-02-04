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
import v2.models.response.getCalculation.endOfYearEstimate.detail.EoyEstimateForeignProperty

object EoyEstimateForeignPropertyFixtures {

  val taxableIncome: BigInt = 1500
  val finalised: Option[Boolean] = Some(true)

  val eoyEstimateForeignPropertyResponse: EoyEstimateForeignProperty = EoyEstimateForeignProperty(taxableIncome, finalised)

  val eoyEstimateForeignPropertyDesJson: JsValue = Json.parse(s"""{
      |  "taxableIncome" : $taxableIncome,
      |  "finalised" : ${finalised.get},
      |  "incomeSourceType" : "15"
      |}""".stripMargin)

  val eoyEstimateForeignPropertyDesJsonWrongIncomeSourceType: JsValue = Json.parse(s"""{
      |  "taxableIncome" : $taxableIncome,
      |  "finalised" : ${finalised.get},
      |  "incomeSourceType" : "source"
      |}""".stripMargin)

  val eoyEstimateForeignPropertyWrittenJson: JsValue = Json.parse(s"""{
      |  "taxableIncome" : $taxableIncome,
      |  "finalised" : ${finalised.get}
      |}""".stripMargin)

  val eoyEstimateForeignPropertyWrittenJsonObject: JsObject = Json.obj("foreignProperty" -> eoyEstimateForeignPropertyWrittenJson)

  val eoyEstimateForeignPropertyInvalidJson: JsValue = Json.parse(s"""{
      |  "taxableIncome" : $taxableIncome,
      |  "finalised" : "notABoolean"
      |}""".stripMargin)
}