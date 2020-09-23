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
import v1.fixtures.getCalculation.incomeTaxAndNics.detail.PensionContributionsInExcessOfTheAnnualAllowanceFixtures._

class PensionContributionsInExcessOfTheAnnualAllowanceSpec extends UnitSpec {

  "PensionContributionsInExcessOfTheAnnualAllowance" should {

    "read from json correctly" when {
      "provided with valid json" in {
        pensionContributionsInExcessOfTheAnnualAllowanceJson.as[PensionContributionsInExcessOfTheAnnualAllowance] shouldBe pensionContributionsInExcessOfTheAnnualAllowanceModel
      }
    }

    "write to json correctly" when {
      "a valid model is provided" in {
        Json.toJson(pensionContributionsInExcessOfTheAnnualAllowanceModel) shouldBe pensionContributionsInExcessOfTheAnnualAllowanceJson
      }
    }

    "read from invalid JSON" should {
      "produce a JsError" in {
        val invalidJson = Json.parse(
          """
            |{
            |   "totalContributions":"70.25",
            |   "totalPensionCharge":300.25,
            |   "annualAllowanceTaxPaid":300.25,
            |   "totalPensionChargeDue":300.25,
            |   "pensionBands":[
            |      {
            |         "name": true,
            |         "rate":20.10,
            |         "bandLimit":2000,
            |         "apportionedBandLimit":2000,
            |         "contributionAmount":500.50,
            |         "pensionCharge":750.99
            |      }
            |   ]
            |}
          """.stripMargin
        )
        invalidJson.validate[PensionContributionsInExcessOfTheAnnualAllowance] shouldBe a[JsError]
      }
    }
  }
}
