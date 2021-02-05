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

package v2.fixtures

import play.api.libs.json.{JsValue, Json}
import v2.models.errors._

object ErrorFixtures {

  object DesError {

    val json: JsValue = Json.parse(
      """
        |{
        |   "code": "CODE",
        |   "reason": "ignored"
        |}
      """.stripMargin
    )

    val model = DesErrorCode("CODE")
  }

  implicit val correlationId: String = "a1e8057e-fbbc-47a8-a8b4-78d9f015c253"

  object SingleError {
    val jsonAsString: String =
      """
        |{
        |   "code": "FORMAT_NINO",
        |   "message": "The provided NINO is invalid"
        |}
      """.stripMargin

    val json: JsValue = Json.parse(jsonAsString)
    val model = ErrorWrapper(correlationId, NinoFormatError, None)
    val modelWithEmptySeq = ErrorWrapper(correlationId, NinoFormatError, Some(Seq.empty))
  }

  object MultipleErrors {
    val json: JsValue = Json.parse(
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

    val model = ErrorWrapper(correlationId, BadRequestError, Some(Seq(NinoFormatError, TaxYearFormatError)))
  }
}
