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

package v1.models.domain


import support.UnitSpec
import v1.models.request.triggerCalculation.TriggerTaxCalculation
import v1.models.utils.JsonErrorValidators
import v1.fixtures.TriggerTaxCalculationFixtures.inputJson

class TriggerTaxCalculationSpec extends UnitSpec with JsonErrorValidators {
  "reads" when {
    "passed valid JSON" should {
      "return a valid model" in {
        TriggerTaxCalculation("2017-18") shouldBe inputJson.as[TriggerTaxCalculation]
      }

      testMandatoryProperty[TriggerTaxCalculation](inputJson)("/taxYear")

      testPropertyType[TriggerTaxCalculation](inputJson)(
        path = "/taxYear",
        replacement = 12344.toJson,
        expectedError = JsonError.STRING_FORMAT_EXCEPTION
      )
    }
  }
}
