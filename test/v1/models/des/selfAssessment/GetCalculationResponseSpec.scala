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

package v1.models.des.selfAssessment

import play.api.libs.json.{JsError, JsSuccess, JsValue, Json}
import support.UnitSpec
import v1.models.des.selfAssessment.componentObjects.Metadata
import v1.models.domain.selfAssessment.{CalculationReason, CalculationRequestor, CalculationType}

class GetCalculationResponseSpec extends UnitSpec {

  val desJson: JsValue = Json.parse("""{
      |    "metadata":{
      |       "calculationId": "f2fb30e5-4ab6-4a29-b3c1-c7264259ff1c",
      |       "taxYear": 2019,
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
      |       "calculationId": "f2fb30e5-4ab6-4a29-b3c1-c7264259ff1c",
      |       "taxYear": 2019,
      |       "requestedBy": "me",
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

  val writtenJson: JsValue = Json.parse("""{
      |    "metadata":{
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
      |       }
      |}""".stripMargin)

  val metadata = Metadata(
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
  val calculationResponse = GetCalculationResponse(metadata)

  "GetCalculationResponse" when {
    "read from valid JSON" should {
      "return a JsSuccess" in {
        desJson.validate[GetCalculationResponse] shouldBe a[JsSuccess[GetCalculationResponse]]
      }
      "return the expected GetCalculationResponse object" in {
        desJson.as[GetCalculationResponse] shouldBe calculationResponse
      }
    }

    "read from invalid JSON" should {
      "return a JsError" in {
        invalidDesJson.validate[GetCalculationResponse] shouldBe a[JsError]
      }
    }

    "written to JSON" should {
      "return the expected JsObject" in {
        Json.toJson(calculationResponse) shouldBe writtenJson
      }
    }
  }
}
