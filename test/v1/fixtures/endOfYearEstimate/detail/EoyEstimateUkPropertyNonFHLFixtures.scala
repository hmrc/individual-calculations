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
import v1.models.response.getCalculation.endOfYearEstimate.detail.EoyEstimateUkPropertyNonFHL

object EoyEstimateUkPropertyNonFHLFixtures {

  val taxableIncome: BigInt      = 1011
  val finalised: Option[Boolean] = Some(false)

  val eoyEstimateUkPropertyNonFHLResponse: EoyEstimateUkPropertyNonFHL =
    EoyEstimateUkPropertyNonFHL(taxableIncome, finalised)

  val eoyEstimateUkPropertyNonFHLDesJson: JsValue = Json.parse(s"""{
      |  "taxableIncome": $taxableIncome,
      |  "finalised": ${finalised.get},
      |  "incomeSourceType" : "02"
      |}""".stripMargin)

  val eoyEstimateUkPropertyNonFHLDesJsonMissingFields: JsValue = Json.parse(s"""{
      |  "taxableIncome": $taxableIncome,
      |  "incomeSourceType" : "02"
      |}""".stripMargin)

  val eoyEstimateUkPropertyNonFHLWrittenJson: JsValue = Json.parse(s"""{
      |  "taxableIncome": $taxableIncome,
      |  "finalised": ${finalised.get}
      |}""".stripMargin)

  val eoyEstimateUkPropertyNonFHLWrittenJsonMissingFields: JsValue = Json.parse(s"""{
      |  "taxableIncome": $taxableIncome
      |}""".stripMargin)

  val eoyEstimateUkPropertyNonFHLWrittenJsonObject: JsValue = Json.obj("ukPropertyNonFHL" -> Seq(eoyEstimateUkPropertyNonFHLWrittenJson))

  val eoyEstimateUkPropertyNonFHLInvalidJson: JsValue = Json.parse(s"""{
      |  "taxableIncome": $taxableIncome,
      |  "finalised": "notABoolean"
      |}""".stripMargin)

  def eoyEstimateUkPropertyNonFHLResponseFactory(taxableIncome: BigInt = taxableIncome,
                                                 finalised: Option[Boolean] = finalised): EoyEstimateUkPropertyNonFHL =
    EoyEstimateUkPropertyNonFHL(taxableIncome, finalised)

}
