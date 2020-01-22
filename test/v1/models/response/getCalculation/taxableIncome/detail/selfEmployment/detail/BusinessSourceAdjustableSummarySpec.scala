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

package v1.models.response.getCalculation.taxableIncome.detail.selfEmployment.detail

import play.api.libs.json.{JsError, JsSuccess, Json}
import support.UnitSpec
import v1.fixtures.getCalculation.taxableIncome.detail.selfEmployments.detail.CarriedForwardLossFixtures._

class BusinessSourceAdjustableSummarySpec extends UnitSpec {

  val desJson = Json.parse(
    """{
      |"ascId": "bsasId",
      |"applied": true
      |}""".stripMargin)

  val mtdJson = Json.parse(
    """{
      |"bsasId": "bsasId",
      |"applied": true
      |}""".stripMargin)

  val model = BusinessSourceAdjustableSummary("bsasId", true)

  "BusinessSourceAdjustableSummary" when {
    "read from valid Json" should {
      "return a valid model" in {
        desJson.as[BusinessSourceAdjustableSummary] shouldBe model
      }

    }


    "writes a valid model" should {
      "return a valid json" in {
        Json.toJson(model) shouldBe mtdJson
      }
    }
  }

}
