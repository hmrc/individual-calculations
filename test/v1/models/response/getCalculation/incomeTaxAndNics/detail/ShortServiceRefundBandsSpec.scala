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

import play.api.libs.json.{JsError, Json}
import support.UnitSpec
import v1.fixtures.getCalculation.incomeTaxAndNics.detail.ShortServiceRefundBandsFixtures._

class ShortServiceRefundBandsSpec extends UnitSpec {

  "ShortServiceRefundBands" should {
    "read from json correctly" when {
      "provided with valid json" in {
        shortServiceRefundBandsJson.as[ShortServiceRefundBands] shouldBe shortServiceRefundBandsModel
      }
    }

    "write to json correctly" when {
      "a valid model is provided" in {
        Json.toJson(shortServiceRefundBandsModel) shouldBe shortServiceRefundBandsJson
      }
    }

    "read from invalid JSON" should {
      "produce a JsError" in {
        val invalidJson = Json.parse(
          """
            |{
            | "name": "name",
            | "rate": 10.25,
            | "bandLimit" : false,
            | "apportionedBandLimit" : 2000,
            | "shortServiceRefundAmount" : 600.25,
            | "shortServiceRefundCharge" : 700.25
            |}
          """.stripMargin
        )
        invalidJson.validate[ShortServiceRefundBands] shouldBe a[JsError]
      }
    }
  }
}