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

import play.api.libs.json.{JsError, JsObject, Json}
import support.UnitSpec
import v2.fixtures.getCalculation.incomeTaxAndNics.detail.PensionSavingsTaxChargesDetailFixtures._

class PensionSavingsTaxChargesDetailSpec extends UnitSpec {

  "PensionSavingsTaxChargesDetail" should {

    "read from json correctly" when {
      "provided with valid json" in {
        desPensionSavingsTaxChargesDetailJson.as[PensionSavingsTaxChargesDetail] shouldBe pensionSavingsTaxChargesDetailModel
      }
    }

    "write to json correctly" when {
      "a valid model is provided" in {
        Json.toJson(pensionSavingsTaxChargesDetailModel) shouldBe mtdPensionSavingsTaxChargesDetailJson
      }
    }

    "read from valid JSON with empty PensionSavingsTaxChargesDetail objects" should {
      "produce an empty PensionSavingsTaxChargesDetail object" in {
        val emptyJson = JsObject.empty

        emptyJson.as[PensionSavingsTaxChargesDetail] shouldBe PensionSavingsTaxChargesDetail.empty
      }
    }

    "read from invalid JSON" should {
      "produce a JsError" in {
        val invalidJson = Json.parse(
          """
            |{
            |	"excessOfLifeTimeAllowance": {
            |		"lumpSumBenefitTakenInExcessOfLifetimeAllowance": {
            |			"amount": true,
            |			"taxPaid": 250.50,
            |			"rate": 30.25,
            |			"chargeableAmount": 300.99
            |		},
            |		"benefitInExcessOfLifetimeAllowance": {
            |			"amount": 120.10,
            |			"taxPaid": 250.50,
            |			"rate": 30.25,
            |			"chargeableAmount": 300.99
            |		}
            |	},
            |	"pensionSchemeUnauthorisedPayments": {
            |		"pensionSchemeUnauthorisedPaymentsSurcharge": {
            |			"amount": 120.10,
            |			"taxPaid": 250.50,
            |			"rate": 30.25,
            |			"chargeableAmount": 300.99
            |		},
            |		"pensionSchemeOverseasTransfers": {
            |			"transferCharge": 120.10,
            |			"transferChargeTaxPaid": 250.50,
            |			"rate": 30.25,
            |			"chargeableAmount": 300.99
            |		}
            |	},
            |	"pensionContributionsInExcessOfTheAnnualAllowance": {
            |		"totalContributions": 70.25,
            |		"totalPensionCharge": 300.25,
            |		"annualAllowanceTaxPaid": 300.25,
            |		"totalPensionChargeDue": 300.25,
            |		"pensionBands": [{
            |			"name": "name",
            |			"rate": 20.10,
            |			"bandLimit": 2000,
            |			"apportionedBandLimit": 2000,
            |			"contributionAmount": 500.50,
            |			"pensionCharge": 750.99
            |		}]
            |	},
            |	"overseasPensionContributions": {
            |		"totalShortServiceRefund": 100.50,
            |		"totalShortServiceRefundCharge": 200.50,
            |		"shortServiceRefundTaxPaid": 160.25,
            |		"totalShortServiceRefundChargeDue": 160.99,
            |		"shortServiceRefundBands": [{
            |			"name": "name",
            |			"rate": 20.10,
            |			"bandLimit": 2000,
            |			"apportionedBandLimit": 2000,
            |			"shortServiceRefundAmount": 500.50,
            |			"shortServiceRefundCharge": 750.99
            |		}]
            |	}
            |}
          """.stripMargin
        )
        invalidJson.validate[PensionSavingsTaxChargesDetail] shouldBe a[JsError]
      }
    }

    "read from empty JSON" should {
      "produce an empty PensionSavingsTaxChargesDetail object" in {
        val emptyJson = JsObject.empty

        emptyJson.as[PensionSavingsTaxChargesDetail] shouldBe PensionSavingsTaxChargesDetail.empty
      }
    }
  }
}
