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

package v1.models.response.getCalculation.incomeTaxAndNics.detail

import play.api.libs.json.{JsError, JsObject, Json}
import support.UnitSpec
import v1.fixtures.getCalculation.incomeTaxAndNics.detail.PensionSavingsTaxChargesFixtures._

class PensionSavingsTaxChargesSpec extends UnitSpec {

  "PensionSavingsTaxCharges" should {

    "read from json correctly" when {
      "provided with valid json" in {
        pensionSavingsTaxChargesJson.as[PensionSavingsTaxCharges] shouldBe pensionSavingsTaxChargesModel
      }
    }

    "write to json correctly" when {
      "a valid model is provided" in {
        Json.toJson(pensionSavingsTaxChargesModel) shouldBe pensionSavingsTaxChargesJson
      }
    }

    "read from empty JSON" should {
      "produce an empty PensionSavingsTaxCharges object" in {
        val emptyJson = JsObject.empty

        emptyJson.as[PensionSavingsTaxCharges] shouldBe PensionSavingsTaxCharges.empty
      }
    }

    "read from invalid JSON" should {
      "produce a JsError" in {
        val invalidJson = Json.parse(
          """
            |{
            |   "totalPensionCharges": true,
            |   "totalTaxPaid": 4000.25,
            |   "totalPensionChargesDue": 5000.70,
            |   "pensionSavingsTaxChargesDetail" : {}
            |}
          """.stripMargin
        )
        invalidJson.validate[PensionSavingsTaxCharges] shouldBe a[JsError]
      }
    }
  }
}
