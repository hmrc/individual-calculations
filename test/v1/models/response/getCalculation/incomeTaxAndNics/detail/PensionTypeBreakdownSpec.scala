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

package v1.models.response.getCalculation.incomeTaxAndNics.detail

import play.api.libs.json.{JsError, JsObject, Json}
import support.UnitSpec
import v1.fixtures.getCalculation.incomeTaxAndNics.detail.PensionTypeBreakdownFixtures._

class PensionTypeBreakdownSpec extends UnitSpec {

  "PensionTypeBreakdown" should {
    "read from json correctly" when {
      "provided with valid json" in {
        pensionTypeBreakdownJson.as[PensionTypeBreakdown] shouldBe pensionTypeBreakdownModel
      }
    }

    "write to json correctly" when {
      "a valid model is provided" in {
        Json.toJson(pensionTypeBreakdownModel) shouldBe pensionTypeBreakdownJson
      }
    }

    "read from empty JSON" should {
      "produce an empty PensionTypeBreakdown object" in {
        val emptyJson = JsObject.empty

        emptyJson.as[PensionTypeBreakdown] shouldBe PensionTypeBreakdown.empty
      }
    }

    "read from invalid JSON" should {
      "produce a JsError" in {
        val invalidJson = Json.parse(
          """
            |{
            |   "amount" : true,
            |   "taxPaid" : 160.50,
            |   "rate" : 10.40,
            |   "chargeableAmount" : 160.50
            |}
          """.stripMargin
        )
        invalidJson.validate[PensionTypeBreakdown] shouldBe a[JsError]
      }
    }
  }
}
