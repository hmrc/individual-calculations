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

package v1.controllers.requestParsers

import support.UnitSpec
import uk.gov.hmrc.domain.Nino
import v1.mocks.validators.MockGetCalculationValidator
import v1.models.errors.{BadRequestError, CalculationIdFormatError, ErrorWrapper, NinoFormatError}
import v1.models.requestData.selfAssessment.{GetCalculationRawData, GetCalculationRequest}

class GetCalculationParserSpec extends UnitSpec {
  val nino   = "AA123456B"
  val calcId = "12345678"

  trait Test extends MockGetCalculationValidator {
    lazy val parser = new GetCalculationParser(mockValidator)
  }

  "parse" when {
    "valid input" should {
      "parse the request" in new Test {
        val data = GetCalculationRawData(nino, calcId)
        MockValidator.validate(data).returns(Nil)

        parser.parseRequest(data) shouldBe Right(GetCalculationRequest(Nino(nino), calcId))
      }
    }

    "single validation error" should {
      "return the error" in new Test {
        val data = GetCalculationRawData(nino, calcId)
        MockValidator.validate(data).returns(List(NinoFormatError))

        parser.parseRequest(data) shouldBe Left(ErrorWrapper(None, NinoFormatError))
      }
    }

    "multiple validation errors" should {
      "return the errors" in new Test {
        val data = GetCalculationRawData(nino, calcId)
        MockValidator.validate(data).returns(List(NinoFormatError, CalculationIdFormatError))

        parser.parseRequest(data) shouldBe Left(ErrorWrapper(None, BadRequestError, Some(Seq(NinoFormatError, CalculationIdFormatError))))
      }
    }
  }

}
