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

package v2.models.response.getCalculation.allowancesAndDeductions.detail

import play.api.libs.json.Json
import support.UnitSpec
import v2.models.utils.JsonErrorValidators
import v2.fixtures.getCalculation.allowancesAndDeductions.detail.AllOtherIncomeReceivedWhilstAbroadFixture._

class AllOtherIncomeReceivedWhilstAbroadSpec extends UnitSpec with JsonErrorValidators {

  testJsonProperties[AllOtherIncomeReceivedWhilstAbroad](allOtherIncomeReceivedWhilstAbroadJson)(
    mandatoryProperties = Seq(
      "totalOtherIncomeAllowableAmount",
      "otherIncomeRfcDetail"
    ),
    optionalProperties = Seq()
  )

  "reads" should {
    "return a valid object" when {
      "valid json is passed" in {
        allOtherIncomeReceivedWhilstAbroadJson.as[AllOtherIncomeReceivedWhilstAbroad] shouldBe allOtherIncomeReceivedWhilstAbroadModel
      }
    }
  }

  "writes" should {
    "return a valid json" when {
      "AllOtherIncomeReceivedWhilstAbroad object has data" in {
        Json.toJson(allOtherIncomeReceivedWhilstAbroadModel) shouldBe allOtherIncomeReceivedWhilstAbroadJson
      }
    }
  }
}
