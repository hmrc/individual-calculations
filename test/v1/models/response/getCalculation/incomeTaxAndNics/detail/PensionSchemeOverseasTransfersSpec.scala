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
import v1.fixtures.getCalculation.incomeTaxAndNics.detail.PensionSchemeOverseasTransfersFixtures._

class PensionSchemeOverseasTransfersSpec extends UnitSpec {

  "PensionSchemeOverseasTransfers" should {

    "read from json correctly" when {
      "provided with valid json" in {
        pensionSchemeOverseasTransfersJson.as[PensionSchemeOverseasTransfers] shouldBe pensionSchemeOverseasTransfersModel
      }
    }

    "write to json correctly" when {
      "a valid model is provided" in {
        Json.toJson(pensionSchemeOverseasTransfersModel) shouldBe pensionSchemeOverseasTransfersJson
      }
    }

    "read from empty JSON" should {
      "produce an empty PensionSchemeOverseasTransfers object" in {
        val emptyJson = JsObject.empty

        emptyJson.as[PensionSchemeOverseasTransfers] shouldBe PensionSchemeOverseasTransfers.empty
      }
    }

    "read from invalid JSON" should {
      "produce a JsError" in {
        val invalidJson = Json.parse(
          """
            |{
            |   "transferCharge": true,
            |   "transferChargeTaxPaid": 130.25,
            |   "rate": 60.25,
            |   "chargeableAmount": 140.25
            |}
          """.stripMargin
        )
        invalidJson.validate[PensionSchemeOverseasTransfers] shouldBe a[JsError]
      }
    }

  }
}
