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

package v2.models.response.getCalculation.incomeTaxAndNics.detail

import play.api.libs.json.{JsError, Json}
import support.UnitSpec
import v2.fixtures.getCalculation.incomeTaxAndNics.detail.IncomeTypeBreakdownFixtures._

class IncomeTypeBreakdownSpec extends UnitSpec {

  "IncomeTaxSummary" should {

    "read from json correctly" when {
      "provided with valid json" in {
        incomeTypeBreakdownJson.as[IncomeTypeBreakdown] shouldBe incomeTypeBreakdownModel
      }
    }

    "write to json correctly" when {
      "a valid model is provided" in {
        Json.toJson(incomeTypeBreakdownModel) shouldBe incomeTypeBreakdownJson
      }
    }

    "read from invalid JSON" should {
      "produce a JsError" in {
        val invalidJson = Json.parse(
          """
            |{
            | "allowancesAllocated" : true,
            | "incomeTaxAmount" : 200.25,
            | "taxBands" : [
            |   {
            |     "name" : "name",
            |     "rate" : 50.50,
            |     "bandLimit" : 400,
            |     "apportionedBandLimit" : 500,
            |     "income" : 600,
            |     "taxAmount" : 700.25
            |   }
            | ]
            |}
          """.stripMargin
        )
        invalidJson.validate[IncomeTypeBreakdown] shouldBe a[JsError]
      }
    }
  }
}
