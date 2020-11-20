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

package v1.models.response.getCalculation.incomeTaxAndNics.summary

import play.api.libs.json.{JsError, JsSuccess, Json}
import support.UnitSpec
import v1.fixtures.getCalculation.incomeTaxAndNics.summary.CalculationSummaryFixtures._

class CalculationSummarySpec extends UnitSpec {

  "CalculationDetail" should {

    "write to json correctly" when {

      "provided with the minimum model" in {
        Json.toJson(calcSummaryMinModel) shouldBe calcSummaryMinOutputJson
      }
    }

    "read from json correctly" when {

      "provided with the minimum json" in {
        calcSummaryMinInputJson.validate[CalculationSummary] shouldBe JsSuccess(calcSummaryMinModel)
      }

      "provided with json with empty models" in {
        calcSummaryInputJsonWithEmptyModels.validate[CalculationSummary] shouldBe JsSuccess(calcSummaryMinModel)
      }

      "provided with a filled top level json" in {
        calcSummaryFilledInputJson.validate[CalculationSummary] shouldBe JsSuccess(calcSummaryFilledModel)
      }
    }

    "read from invalid JSON" should {
      "produce a JsError" in {
        val invalidJson = Json.parse(
          """
            |{
            |   "calculation":{
            |      "taxCalculation":{
            |         "incomeTax":{
            |            "incomeTaxCharged": true
            |         },
            |         "nics":{
            |            "class2Nics":{
            |               "amount": 200.25
            |            }
            |         },
            |         "totalStudentLoansRepaymentAmount": 100.25,
            |         "totalAnnualPaymentsTaxCharged": 200.25,
            |         "totalRoyaltyPaymentsTaxCharged": 300.25,
            |         "totalIncomeTaxNicsCharged": 400.25,
            |         "totalTaxDeducted": 500.25,
            |         "totalIncomeTaxAndNicsDue": 600.25
            |      }
            |   },
            |   "inputs":{
            |      "personalInformation":{
            |         "taxRegime": "UK"
            |      }
            |   }
            |}
          """.stripMargin
        )
        invalidJson.validate[CalculationSummary] shouldBe a[JsError]
      }
    }
  }
}