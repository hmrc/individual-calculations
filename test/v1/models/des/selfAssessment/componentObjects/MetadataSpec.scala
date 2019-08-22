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

package v1.models.des.selfAssessment.componentObjects

import play.api.libs.json.{JsError, JsSuccess, JsValue, Json}
import support.UnitSpec
import v1.models.domain.selfAssessment.{CalculationReason, CalculationRequestor, CalculationType}

class MetadataSpec extends UnitSpec {

  val desJson: JsValue = Json.parse("""{
      |    "metadata":{
      |       "calculationId": "f2fb30e5-4ab6-4a29-b3c1-c7264259ff1c",
      |       "taxYear": "2019",
      |       "requestedBy": "customer",
      |       "requestedTimestamp": "2019-11-15T09:25:15.094Z",
      |       "calculationReason": "customerRequest",
      |       "calculationTimestamp": "2019-11-15T09:35:15.094Z",
      |       "calculationType": "inYear",
      |       "periodFrom": "1-2018",
      |       "periodTo": "1-2019"
      |     },
      |     "messages" :{
      |        "errors":[
      |        {"id":"id1", "text":"text1"}
      |        ]
      |     }
      |}""".stripMargin)

  val invalidDesJson: JsValue = Json.parse("""{
      |    "metadata":{
      |       "calcId": "f2fb30e5-4ab6-4a29-b3c1-c7264259ff1c",
      |       "taxYear": "2019",
      |       "requestedBy": "me",
      |       "requestedTimestamp": "2019-11-15T09:25:15.094Z",
      |       "calculationReason": "I wanted to",
      |       "calculationTimestamp": "2019-11-15T09:35:15.094Z",
      |       "calculationType": "inYear",
      |       "periodFrom": "Now",
      |       "periodTo": "Later"
      |     },
      |     "messages" :{
      |        "errors":[
      |        {"id":"id1"}
      |        ]
      |     }
      |}""".stripMargin)

  val desJsonWithoutErrors: JsValue = Json.parse("""{
      |    "metadata":{
      |       "calculationId": "f2fb30e5-4ab6-4a29-b3c1-c7264259ff1c",
      |       "taxYear": "2019",
      |       "requestedBy": "customer",
      |       "requestedTimestamp": "2019-11-15T09:25:15.094Z",
      |       "calculationReason": "customerRequest",
      |       "calculationTimestamp": "2019-11-15T09:35:15.094Z",
      |       "calculationType": "inYear",
      |       "periodFrom": "1-2018",
      |       "periodTo": "1-2019"
      |     },
      |          "messages" :{
      |     }
      |}""".stripMargin)

  val desJsonWithoutMessages: JsValue = Json.parse("""{
      |    "metadata":{
      |       "calculationId": "f2fb30e5-4ab6-4a29-b3c1-c7264259ff1c",
      |       "taxYear": "2019",
      |       "requestedBy": "customer",
      |       "requestedTimestamp": "2019-11-15T09:25:15.094Z",
      |       "calculationReason": "customerRequest",
      |       "calculationTimestamp": "2019-11-15T09:35:15.094Z",
      |       "calculationType": "inYear",
      |       "periodFrom": "1-2018",
      |       "periodTo": "1-2019"
      |     }
      |}""".stripMargin)

  val desJsonWithEmptyErrors: JsValue = Json.parse("""{
      |    "metadata":{
      |       "calculationId": "f2fb30e5-4ab6-4a29-b3c1-c7264259ff1c",
      |       "taxYear": "2019",
      |       "requestedBy": "customer",
      |       "requestedTimestamp": "2019-11-15T09:25:15.094Z",
      |       "calculationReason": "customerRequest",
      |       "calculationTimestamp": "2019-11-15T09:35:15.094Z",
      |       "calculationType": "inYear",
      |       "periodFrom": "1-2018",
      |       "periodTo": "1-2019"
      |     },
      |     "messages" :{
      |        "errors":[
      |        ]
      |     }
      |}""".stripMargin)

  val writtenJson: JsValue = Json.parse("""{
      |       "id": "f2fb30e5-4ab6-4a29-b3c1-c7264259ff1c",
      |       "taxYear": "2018-19",
      |       "requestedBy": "customer",
      |       "requestedTimestamp": "2019-11-15T09:25:15.094Z",
      |       "calculationReason": "customerRequest",
      |       "calculationTimestamp": "2019-11-15T09:35:15.094Z",
      |       "calculationType": "inYear",
      |       "intentToCrystallise": false,
      |       "crystallised": false,
      |       "calculationErrorCount": 1
      |}""".stripMargin)

  val writtenJsonWithoutErrors: JsValue = Json.parse("""{
      |       "id": "f2fb30e5-4ab6-4a29-b3c1-c7264259ff1c",
      |       "taxYear": "2018-19",
      |       "requestedBy": "customer",
      |       "requestedTimestamp": "2019-11-15T09:25:15.094Z",
      |       "calculationReason": "customerRequest",
      |       "calculationTimestamp": "2019-11-15T09:35:15.094Z",
      |       "calculationType": "inYear",
      |       "intentToCrystallise": false,
      |       "crystallised": false
      |}""".stripMargin)

  val validErrorJson: JsValue = Json.parse("""{
      |        "id":"id1",
      |        "text": "exampleText"
      |}""".stripMargin)

  val invalidErrorJson: JsValue = Json.parse("""{
      |        "id":1,
      |        "text": "exampleText"
      |}""".stripMargin)

  val metadataResponse = Metadata(
    id = "f2fb30e5-4ab6-4a29-b3c1-c7264259ff1c",
    taxYear = "2018-19",
    requestedBy = CalculationRequestor.customer,
    requestedTimestamp = Some("2019-11-15T09:25:15.094Z"),
    calculationReason = CalculationReason.customerRequest,
    calculationTimestamp = "2019-11-15T09:35:15.094Z",
    calculationType = CalculationType.inYear,
    intentToCrystallise = false,
    crystallised = false,
    calculationErrorCount = Some(1)
  )

  private val metadataResponseWithoutErrors = metadataResponse.copy(calculationErrorCount = None)

  val error = Error("id1", "exampleText")

  "Metadata" when {
    "read from a valid JSON" should {
      "return a JsSuccess" in {
        desJson.validate[Metadata] shouldBe a[JsSuccess[Metadata]]
      }
      "with the expected Metadata object" in {
        desJson.as[Metadata] shouldBe metadataResponse
      }
    }

    "read from an invalid JSON" should {
      "return a JsError" in {
        invalidDesJson.validate[Metadata] shouldBe a[JsError]
      }
    }

    "written to JSON" should {
      "return the expected JsObject" in {
        Json.toJson(metadataResponse) shouldBe writtenJson
      }
    }

    "errors are not present" should {
      "not write the calculationErrorCountField" in {
        Json.toJson(metadataResponseWithoutErrors) shouldBe writtenJsonWithoutErrors
      }
      "read the calculationErrorCount as None" in {
        desJsonWithoutErrors.as[Metadata] shouldBe metadataResponseWithoutErrors
      }
    }

    "errors is present, but is empty" should {
      "read the calculationErrorCount as None" in{
        desJsonWithEmptyErrors.as[Metadata] shouldBe metadataResponseWithoutErrors
      }
    }

    "messages are not present" should {
      "read the calculationErrorCount as None" in {
        desJsonWithoutMessages.as[Metadata] shouldBe metadataResponseWithoutErrors
      }
    }
  }

  "Error" when {
    "read from valid JSON" should{
      "return a JsSuccess" in{
        validErrorJson.validate[Error] shouldBe a[JsSuccess[Error]]
      }
      "with the expected Error object" in{
        validErrorJson.as[Error] shouldBe error
      }
    }
    "read from invalid JSON" should{
      "return a JsError" in{
        invalidErrorJson.validate[Error] shouldBe a[JsError]
      }
    }
    "written to JSON" should{
      "return the expected JsObject" in{
        Json.toJson(error) shouldBe validErrorJson
      }
    }
  }
}
