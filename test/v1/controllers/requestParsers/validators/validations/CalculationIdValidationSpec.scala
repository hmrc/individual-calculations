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

package v1.controllers.requestParsers.validators.validations

import support.UnitSpec
import v1.models.errors.CalculationIdFormatError
import v1.models.utils.JsonErrorValidators

class CalculationIdValidationSpec extends UnitSpec with JsonErrorValidators {

  "CalculationIdValidation.validate" should {
    "return an empty list" when {
      "passed a valid short form calculationId" in {
        CalculationIdValidation.validate("01234569") shouldBe empty
      }
      "passed a valid long form calculationId" in {
        CalculationIdValidation.validate("f2fb30e5-4ab6-4a29-b3c1-c7264259ff1c") shouldBe empty
      }
    }

    "return a non-empty list" when {
      "passed an invalid calculationId" in {
        CalculationIdValidation.validate("ABC1234567890") shouldBe List(CalculationIdFormatError)
      }
    }
  }

}
