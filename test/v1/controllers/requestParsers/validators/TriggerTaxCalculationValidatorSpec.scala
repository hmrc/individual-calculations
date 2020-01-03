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

package v1.controllers.requestParsers.validators

import play.api.libs.json.{JsValue, Json}
import play.api.mvc.AnyContentAsJson
import support.UnitSpec
import v1.models.errors._
import v1.models.request.triggerCalculation.TriggerTaxCalculationRawData

class TriggerTaxCalculationValidatorSpec extends UnitSpec {
  private val validNino = "AA123456A"
  private val validTaxYear = "2017-18"

  val validator = new TriggerTaxCalculationValidator

  def createTriggerTaxCalcBody(taxYear: String): JsValue = Json.parse(
    s"""{
       |  "taxYear": "$taxYear"
       |}
    """.stripMargin)

  "running a validation" should {
    "return no errors" when {
      "a valid request is supplied" in {
        validator.validate(TriggerTaxCalculationRawData(validNino, AnyContentAsJson(createTriggerTaxCalcBody(validTaxYear)))) shouldBe
          List()
      }
    }

    "return NinoFormatError error" when {
      "an invalid nino is supplied" in {
        validator.validate(TriggerTaxCalculationRawData("A12344A", AnyContentAsJson(createTriggerTaxCalcBody(validTaxYear)))) shouldBe
          List(NinoFormatError)
      }
    }

    "return TaxYearFormatError error" when {
      "an invalid tax year is supplied" in {
        validator.validate(TriggerTaxCalculationRawData(validNino, AnyContentAsJson(createTriggerTaxCalcBody("badTaxYear")))) shouldBe
          List(TaxYearFormatError)
      }
    }

    "return RuleTaxYearNotSupportedError error" when {
      "an out of range tax year is supplied" in {
        validator.validate(TriggerTaxCalculationRawData(validNino, AnyContentAsJson(createTriggerTaxCalcBody("2016-17")))) shouldBe
          List(RuleTaxYearNotSupportedError)
      }
    }

    "return RuleTaxYearRangeExceededError error" when {
      "an out of range tax year is supplied" in {
        validator.validate(TriggerTaxCalculationRawData(validNino, AnyContentAsJson(createTriggerTaxCalcBody("2018-20")))) shouldBe
          List(RuleTaxYearRangeExceededError)
      }
    }

    "return RuleIncorrectOrEmptyBodyError error" when {
      "body validation fails" in {
        validator.validate(TriggerTaxCalculationRawData(validNino, AnyContentAsJson(Json.obj()))) shouldBe
          List(RuleIncorrectOrEmptyBodyError)
      }

      "return multiple errors" when {
        "request supplied has multiple errors" in {
          validator.validate(TriggerTaxCalculationRawData("A12344A", AnyContentAsJson(Json.obj()))) shouldBe
            List(NinoFormatError, RuleIncorrectOrEmptyBodyError)
        }
      }
    }
  }
}
