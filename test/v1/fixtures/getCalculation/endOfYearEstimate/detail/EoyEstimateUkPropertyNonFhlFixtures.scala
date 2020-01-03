/*
 * Copyright 2020 HM Revenue & Customs
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

import play.api.libs.json.{ JsObject, JsValue, Json }
import v1.models.response.getCalculation.endOfYearEstimate.detail.EoyEstimateUkPropertyNonFhl

object EoyEstimateUkPropertyNonFhlFixtures {

  val taxableIncome: BigInt      = 1041
  val finalised: Option[Boolean] = Some(false)

  val eoyEstimateUkPropertyNonFhlResponse: EoyEstimateUkPropertyNonFhl =
    EoyEstimateUkPropertyNonFhl(taxableIncome, finalised)

  val eoyEstimateUkPropertyNonFhlDesJson: JsValue = Json.parse(s"""{
      |  "taxableIncome": $taxableIncome,
      |  "finalised": ${finalised.get},
      |  "incomeSourceType" : "02"
      |}""".stripMargin)

  val eoyEstimateUkPropertyNonFhlDesJsonWrongIncomeSourceType: JsValue = Json.parse(s"""{
      |  "taxableIncome": $taxableIncome,
      |  "finalised": ${finalised.get},
      |  "incomeSourceType" : "wrong"
      |}""".stripMargin)

  val eoyEstimateUkPropertyNonFhlDesJsonMissingFields: JsValue = Json.parse(s"""{
      |  "taxableIncome": $taxableIncome,
      |  "incomeSourceType" : "02"
      |}""".stripMargin)

  val eoyEstimateUkPropertyNonFhlWrittenJson: JsValue = Json.parse(s"""{
      |  "taxableIncome": $taxableIncome,
      |  "finalised": ${finalised.get}
      |}""".stripMargin)

  val eoyEstimateUkPropertyNonFhlWrittenJsonMissingFields: JsValue = Json.parse(s"""{
      |  "taxableIncome": $taxableIncome
      |}""".stripMargin)

  val eoyEstimateUkPropertyNonFhlWrittenJsonObject: JsObject = Json.obj("ukPropertyNonFhl" -> eoyEstimateUkPropertyNonFhlWrittenJson)

  val eoyEstimateUkPropertyNonFhlInvalidJson: JsValue = Json.parse(s"""{
      |  "taxableIncome": $taxableIncome,
      |  "finalised": "notABoolean"
      |}""".stripMargin)
}
