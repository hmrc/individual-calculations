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

package v1.models.response.common

import play.api.libs.json.{ JsError, JsObject, JsValue, Json }
import support.UnitSpec
import v1.models.domain.{ CalculationReason, CalculationRequestor, CalculationType }

class MetadataSpec extends UnitSpec {

  val desJson: JsObject = Json.parse("""{
        |    "metadata":{
        |       "calculationId": "f2fb30e5-4ab6-4a29-b3c1-c7264259ff1c",
        |       "taxYear": 2019,
        |       "requestedBy": "customer",
        |       "requestedTimestamp": "2019-11-15T09:25:15.094Z",
        |       "calculationReason": "customerRequest",
        |       "calculationTimestamp": "2019-11-16T09:35:15.094Z",
        |       "calculationType": "inYear",
        |       "intentToCrystallise": true,
        |       "crystallised": true,
        |       "periodFrom": "1-2018",
        |       "periodTo": "1-2019"
        |    },
        |    "calculation": {
        |      "taxCalculation": {
        |        "totalIncomeTaxAndNicsDue": 123.45
        |      }
        |    }
        |}""".stripMargin).as[JsObject]

  val writtenJson = Json.parse("""
       |{
       |       "id": "f2fb30e5-4ab6-4a29-b3c1-c7264259ff1c",
       |       "taxYear": "2018-19",
       |       "requestedBy": "customer",
       |       "requestedTimestamp": "2019-11-15T09:25:15.094Z",
       |       "calculationReason": "customerRequest",
       |       "calculationTimestamp": "2019-11-16T09:35:15.094Z",
       |       "calculationType": "inYear",
       |       "intentToCrystallise": true,
       |       "crystallised": true,
       |       "totalIncomeTaxAndNicsDue": 123.45
       |}""".stripMargin)

  val metadataResponse = Metadata(
    id = "f2fb30e5-4ab6-4a29-b3c1-c7264259ff1c",
    taxYear = "2018-19",
    requestedBy = CalculationRequestor.customer,
    requestedTimestamp = Some("2019-11-15T09:25:15.094Z"),
    calculationReason = CalculationReason.customerRequest,
    calculationTimestamp = Some("2019-11-16T09:35:15.094Z"),
    calculationType = CalculationType.inYear,
    intentToCrystallise = true,
    crystallised = true,
    totalIncomeTaxAndNicsDue = Some(123.45),
    calculationErrorCount = None
  )

  "Metadata" when {
    "read from a valid JSON" should {
      "read the expected Metadata object" in {
        desJson.as[Metadata] shouldBe metadataResponse
      }

      "read optional fields as None with optional booleans false" in {
        val desJsonWithoutOptionals: JsValue = Json.parse("""{
              |    "metadata":{
              |       "calculationId": "f2fb30e5-4ab6-4a29-b3c1-c7264259ff1c",
              |       "taxYear": 2019,
              |       "requestedBy": "customer",
              |       "calculationReason": "customerRequest",
              |       "calculationType": "inYear",
              |       "periodFrom": "1-2018",
              |       "periodTo": "1-2019"
              |     }
              |}""".stripMargin)

        desJsonWithoutOptionals.as[Metadata] shouldBe
          Metadata(
            id = "f2fb30e5-4ab6-4a29-b3c1-c7264259ff1c",
            taxYear = "2018-19",
            requestedBy = CalculationRequestor.customer,
            requestedTimestamp = None,
            calculationReason = CalculationReason.customerRequest,
            calculationTimestamp = None,
            calculationType = CalculationType.inYear,
            intentToCrystallise = false,
            crystallised = false,
            totalIncomeTaxAndNicsDue = None,
            calculationErrorCount = None
          )
      }
    }

    "read from an invalid JSON" should {
      "return a JsError" in {
        val invalidDesJson: JsValue =
          Json.parse("""{
                         |    "metadata":{}
                         |}""".stripMargin)
        invalidDesJson.validate[Metadata] shouldBe a[JsError]
      }
    }

    "written to JSON" should {
      "return the expected JsObject as per spec" in {
        Json.toJson(metadataResponse) shouldBe writtenJson
      }
    }

    "messages are not present" should {
      "read the calculationErrorCount as None" in {
        desJson.as[Metadata] shouldBe metadataResponse
      }
    }

    "errors are not present" should {
      "read the calculationErrorCount as None" in {
        (desJson ++ Json.parse("""{
            |  "messages": {
            |  }
            |}""".stripMargin).as[JsObject]).as[Metadata] shouldBe metadataResponse
      }
    }

    "errors are present, but empty" should {
      "read the calculationErrorCount as None" in {
        (desJson ++ Json.parse("""{
            |  "messages": {
            |     "errors": [
            |     ]
            |  }
            |}""".stripMargin).as[JsObject]).as[Metadata] shouldBe metadataResponse
      }
    }

    "errors are present, and non empty" should {
      "read the calculationErrorCount as some the number of errors" in {
        (desJson ++ Json.parse("""{
            | "messages": {
            |   "errors": [
            |    {"id": "id1", "text": "text1"},
            |    {"id": "id2", "text": "text2"}
            |    ]
            |  }
            |}""".stripMargin).as[JsObject]).as[Metadata] shouldBe
          metadataResponse.copy(calculationErrorCount = Some(2))
      }
    }
  }
}
