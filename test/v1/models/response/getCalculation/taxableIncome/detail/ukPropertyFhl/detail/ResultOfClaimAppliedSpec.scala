/*
 * Copyright 2019 HM Revenue & Customs
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

package v1.models.response.getCalculation.taxableIncome.detail.ukPropertyFhl.detail

import play.api.libs.json.{JsSuccess, Json}
import support.UnitSpec
import v1.fixtures.taxableIncome.ukPropertyFhl.ResultOfClaimAppliedFixtures._
import v1.models.utils.JsonErrorValidators

class ResultOfClaimAppliedSpec extends UnitSpec with JsonErrorValidators {

  "ResultOfClaimApplied" when {
    "read from valid Json" should {

      testMandatoryProperty[ResultOfClaimApplied](resultOfClaimAppliedDesJson)("/taxYearClaimMade")
      testMandatoryProperty[ResultOfClaimApplied](resultOfClaimAppliedDesJson)("/claimType")
      testMandatoryProperty[ResultOfClaimApplied](resultOfClaimAppliedDesJson)("/mtdLoss")
      testMandatoryProperty[ResultOfClaimApplied](resultOfClaimAppliedDesJson)("/taxYearLossIncurred")
      testMandatoryProperty[ResultOfClaimApplied](resultOfClaimAppliedDesJson)("/lossAmountUsed")
      testMandatoryProperty[ResultOfClaimApplied](resultOfClaimAppliedDesJson)("/remainingLossValue")

      testPropertyType[ResultOfClaimApplied](resultOfClaimAppliedDesJson)(
        path = "/claimId",
        replacement = 12344.toJson,
        expectedError = JsonError.STRING_FORMAT_EXCEPTION)

      "return a JsSuccess" in {
        resultOfClaimAppliedDesJson.validate[ResultOfClaimApplied] shouldBe a[JsSuccess[_]]
      }

      "with the expected ResultOfClaimApplied object" in {
        resultOfClaimAppliedDesJson.as[ResultOfClaimApplied] shouldBe resultOfClaimAppliedResponse
      }
    }

    "writes to Json" should {
      "return the expected JsObject" in {
        Json.toJson(resultOfClaimAppliedResponse) shouldBe resultOfClaimAppliedWrittenJson
      }
    }
  }

}
