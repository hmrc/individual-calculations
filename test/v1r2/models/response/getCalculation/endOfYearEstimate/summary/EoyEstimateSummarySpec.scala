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

package v1r2.models.response.getCalculation.endOfYearEstimate.summary

import play.api.libs.json.{ JsError, JsObject, JsSuccess, Json }
import support.UnitSpec
import v1r2.fixtures.getCalculation.endOfYearEstimate.summary.EoyEstimateSummaryFixtures._

class EoyEstimateSummarySpec extends UnitSpec {

  "EoyEstimateSummary" when {
    "read from valid Json" should {
      "return a JsSuccess" in {
        eoyEstimateSummaryDesJson.validate[EoyEstimateSummary] shouldBe a[JsSuccess[_]]
      }
      "with the expected EoyEstimateSummary object" in {
        eoyEstimateSummaryDesJson.as[EoyEstimateSummary] shouldBe eoyEstimateSummaryResponse
      }
    }

    "read from Json with missing optional fields" should {
      "return the expected EoyEstimateSummary object" in {
        eoyEstimateSummaryDesJsonMissingFields.as[EoyEstimateSummary] shouldBe
          eoyEstimateSummaryResponseFactory(totalNicAmount = None, totalStudentLoansRepaymentAmount = None, totalAnnualPaymentsTaxCharged = None,
            totalRoyaltyPaymentsTaxCharged = None, totalTaxDeducted = None, incomeTaxNicAmount = None)
      }
    }

    "read from empty Json" should {
      "return an empty EoyEstimateSummary object" in {
        JsObject.empty.as[EoyEstimateSummary] shouldBe EoyEstimateSummary.empty
      }
    }

    "read from invalid Json" should {
      "return a JsError" in {
        eoyEstimateSummaryInvalidJson.validate[EoyEstimateSummary] shouldBe a[JsError]
      }
    }

    "written to Json" should {
      "return the expected JsObject" in {
        Json.toJson(eoyEstimateSummaryResponse) shouldBe eoyEstimateSummaryWrittenJson
      }
    }

    "written to Json with missing optional fields" should {
      "return the expected JsObject" in {
        Json.toJson(eoyEstimateSummaryResponseFactory(totalNicAmount = None, totalStudentLoansRepaymentAmount = None, totalAnnualPaymentsTaxCharged = None,
          totalRoyaltyPaymentsTaxCharged = None, totalTaxDeducted = None, incomeTaxNicAmount = None)) shouldBe eoyEstimateSummaryWrittenJsonMissingFields
      }
    }
  }
}