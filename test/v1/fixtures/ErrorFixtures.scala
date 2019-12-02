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

package v1.fixtures

import play.api.libs.json.Json
import v1.models.errors._

object ErrorFixtures {

  object DesError {

    val json = Json.parse(
      """
        |{
        |   "code": "CODE",
        |   "reason": "ignored"
        |}
      """.stripMargin
    )

    val model = DesErrorCode("CODE")
  }

  object SingleError {
    val jsonAsString = """{
                         |   "code": "FORMAT_NINO",
                         |   "message": "The provided NINO is invalid"
                         |}""".stripMargin

    val json = Json.parse(jsonAsString)

    val model             = ErrorWrapper(None, NinoFormatError, None)
    val modelWithEmptySeq = ErrorWrapper(None, NinoFormatError, Some(Seq.empty))
  }

  object MultipleErrors {

    val json = Json.parse(
      """
        |{
        |   "code": "INVALID_REQUEST",
        |   "message": "Invalid request",
        |   "errors": [
        |       {
        |         "code": "FORMAT_NINO",
        |         "message": "The provided NINO is invalid"
        |       },
        |       {
        |         "code": "FORMAT_TAX_YEAR",
        |         "message": "The provided tax year is invalid"
        |       }
        |   ]
        |}
      """.stripMargin
    )

    val model = ErrorWrapper(None, BadRequestError, Some(Seq(NinoFormatError, TaxYearFormatError)))
  }
}
