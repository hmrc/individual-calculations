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

import play.api.libs.json.{ JsSuccess, JsValue, Json }
import support.UnitSpec
import v1.models.des.selfAssessment.componentObjects.{ Errors, Info, Messages, Metadata, Warnings }
import v1.models.domain.selfAssessment.{ CalculationReason, CalculationRequestor, CalculationType }

class GetCalculationResponseSpec extends UnitSpec {

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
      |   "messages":{
      |       "info" : [{"id" : "1","text" : "text"},
      |       {"id" : "2","text" : "text2"}],
      |       "warnings" :[{"id" : "1","text" : "text"}],
      |       "errors" :[{"id" : "1","text" : "text"}]
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
      |   "messages":{
      |       "info" : [{"id" : "1","text" : "text"},
      |       {"id" : "2","text" : "text2"}],
      |       "warnings" :[{"id" : "1","text" : "text"}]
      |     }
      |}""".stripMargin)

  val writtenJson: JsValue = Json.parse("""{
      |   "metadata":{
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
      |     },
      |   "messages":{
      |       "info" : [{"id" : "1","text" : "text"},
      |       {"id" : "2","text" : "text2"}],
      |       "warnings" :[{"id" : "1","text" : "text"}],
      |       "errors" :[{"id" : "1","text" : "text"}]
      |    }
      |}""".stripMargin)

  val writtenJsonWithoutErrors: JsValue = Json.parse("""{
       |   "metadata":{
       |       "id": "f2fb30e5-4ab6-4a29-b3c1-c7264259ff1c",
       |       "taxYear": "2018-19",
       |       "requestedBy": "customer",
       |       "requestedTimestamp": "2019-11-15T09:25:15.094Z",
       |       "calculationReason": "customerRequest",
       |       "calculationTimestamp": "2019-11-15T09:35:15.094Z",
       |       "calculationType": "inYear",
       |       "intentToCrystallise": false,
       |       "crystallised": false
       |     },
       |   "messages":{
       |       "info" : [{"id" : "1","text" : "text"},
       |       {"id" : "2","text" : "text2"}],
       |       "warnings" :[{"id" : "1","text" : "text"}]
       |    }
       |}""".stripMargin)

  val metadata = new Metadata(
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

  val infoObj     = new Info("1", "text")
  val infoObj2    = new Info("2", "text2")
  val warningsObj = new Warnings("1", "text")
  val errorsObj   = new Errors("1", "text")
  val messagesObj = new Messages(Some(Array(infoObj, infoObj2)), Some(Array(warningsObj)), Some(Array(errorsObj)))

  val calculationResponseWrapper = GetCalculationResponse(metadata, messagesObj)

  "GetCalculationMetadata" when {
    "read from JSON" should {

      "return a JsSuccess" in {
        desJson.validate[GetCalculationResponse] shouldBe a[JsSuccess[GetCalculationResponse]]
      }
      "with a valid GetCalculationResponse" in {
        desJson.as[GetCalculationResponse] shouldBe a[GetCalculationResponse]
      }

      val readMetadata = desJson.as[GetCalculationResponse].metadata
      "containing a Metadata object" in {
        readMetadata shouldBe a[Metadata]
      }
      "which matches the expected metadata result" in {
        readMetadata shouldBe metadata
      }

      val readMessages = desJson.as[GetCalculationResponse].messages
      "containing a messages object" in {
        readMessages shouldBe a[Messages]
      }
      "which matches the expected messages result" in {
        readMessages.info.getOrElse(Array.empty).headOption shouldBe Some(messagesObj.info.get.head)
        readMessages.info.getOrElse(Array.empty).tail shouldBe messagesObj.info.get.tail
        readMessages.warnings.getOrElse(Array.empty).headOption shouldBe Some(messagesObj.warnings.get.head)
        readMessages.errors.getOrElse(Array.empty).headOption shouldBe Some(messagesObj.errors.get.head)
      }
    }

    "written to JSON" should {
      "return a JsObject" in {
        Json.toJson(calculationResponseWrapper) shouldBe writtenJson
      }
    }

    val metadataWithoutErrors                   = metadata.copy(calculationErrorCount = None)
    val messagesWithoutErrors                   = messagesObj.copy(errors = None)
    val calculationResponseWrapperWithoutErrors = GetCalculationResponse(metadataWithoutErrors, messagesWithoutErrors)

    "writing a response with no errors to JSON" should {
      "not return the calculationErrorCount field" in {
        Json.toJson(calculationResponseWrapperWithoutErrors) shouldBe writtenJsonWithoutErrors
      }
    }

    "reading a response with no errors from Json" should {
      "return None in the calculationErrorCountField" in {
        desJsonWithoutErrors.as[GetCalculationResponse].metadata.calculationErrorCount shouldBe None
      }
    }
  }
}
