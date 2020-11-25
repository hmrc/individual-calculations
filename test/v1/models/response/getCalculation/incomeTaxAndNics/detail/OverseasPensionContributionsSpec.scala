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

import play.api.libs.json.{JsError, Json}
import support.UnitSpec
import v1.fixtures.getCalculation.incomeTaxAndNics.detail.OverseasPensionContributionsFixtures._

class OverseasPensionContributionsSpec extends UnitSpec {

  "OverseasPensionContributions" should {
    "read from json correctly" when {
      "provided with valid json" in {
        overseasPensionContributionsJson.as[OverseasPensionContributions] shouldBe overseasPensionContributionsModel
      }
    }

    "write to json correctly" when {
      "a valid model is provided" in {
        Json.toJson(overseasPensionContributionsModel) shouldBe overseasPensionContributionsJson
      }
    }

    "read from invalid JSON" should {
      "produce a JsError" in {
        val invalidJson = Json.parse(
          """
            |{
            |   "totalShortServiceRefund":100.50,
            |   "totalShortServiceRefundCharge":"200.50",
            |   "shortServiceTaxPaid":160.25,
            |   "totalShortServiceRefundChargeDue":160.99,
            |   "shortServiceRefundBands":[
            |      {
            |         "name": true,
            |         "rate":20.10,
            |         "bandLimit":2000,
            |         "apportionedBandLimit":2000,
            |         "shortServiceRefundAmount":500.50,
            |         "shortServiceRefundCharge":750.99
            |      }
            |   ]
            |}
          """.stripMargin
        )
        invalidJson.validate[OverseasPensionContributions] shouldBe a[JsError]
      }
    }
  }
}