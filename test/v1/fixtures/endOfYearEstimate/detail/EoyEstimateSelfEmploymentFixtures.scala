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

import play.api.libs.json.{ JsValue, Json }
import v1.models.response.getCalculation.endOfYearEstimate.detail.EoyEstimateSelfEmployment

object EoyEstimateSelfEmploymentFixtures {

  val selfEmploymentID: String   = "AA123456789"
  val taxableIncome: BigInt      = 1011
  val finalised: Option[Boolean] = Some(false)

  val eoyEstimateSelfEmploymentResponse: EoyEstimateSelfEmployment =
    EoyEstimateSelfEmployment(selfEmploymentID, taxableIncome, finalised)

  val eoyEstimateSelfEmploymentDesJson: JsValue = Json.parse(s"""{
      |  "incomeSourceId": "$selfEmploymentID",
      |  "taxableIncome": $taxableIncome,
      |  "finalised": ${finalised.get}
      |}""".stripMargin)

  val eoyEstimateSelfEmploymentDesJsonMissingFields: JsValue = Json.parse(s"""{
      |  "incomeSourceId": "$selfEmploymentID",
      |  "taxableIncome": $taxableIncome
      |}""".stripMargin)

  val eoyEstimateSelfEmploymentDesJsonTopLevel: JsValue =
    Json.obj("calculation" -> Json.obj("endOfYearEstimate" -> Json.obj("incomeSource" -> Seq(eoyEstimateSelfEmploymentDesJson))))

  val eoyEstimateSelfEmploymentWrittenJson: JsValue = Json.parse(s"""{
      |  "selfEmploymentID": "$selfEmploymentID",
      |  "taxableIncome": $taxableIncome,
      |  "finalised": ${finalised.get}
      |}""".stripMargin)

  val eoyEstimateSelfEmploymentWrittenJsonMissingFields: JsValue = Json.parse(s"""{
      |  "selfEmploymentID": "$selfEmploymentID",
      |  "taxableIncome": $taxableIncome
      |}""".stripMargin)

  val eoyEstimateSelfEmploymentWrittenJsonObject: JsValue = Json.obj("selfEmployment" -> Seq(eoyEstimateSelfEmploymentWrittenJson))

  val eoyEstimateSelfEmploymentInvalidJson: JsValue = Json.parse(s"""{
      |  "selfEmploymentID": "$selfEmploymentID",
      |  "taxableIncome": $taxableIncome,
      |  "finalised": "notABoolean"
      |}""".stripMargin)

  def eoyEstimateSelfEmploymentResponseFactory(selfEmploymentID: String = selfEmploymentID,
                                                taxableIncome: BigInt = taxableIncome,
                                                finalised: Option[Boolean] = finalised): EoyEstimateSelfEmployment =
    EoyEstimateSelfEmployment(selfEmploymentID, taxableIncome, finalised)

}
