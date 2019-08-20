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

import play.api.libs.json.{JsSuccess, JsValue, Json}
import support.UnitSpec
import v1.models.des.selfAssessment.componentObjects.Metadata
import v1.models.domain.selfAssessment.{CalculationReason, CalculationRequestor, CalculationType}

class GetCalculationResponseMessagesSpec extends UnitSpec {
  val desJson: JsValue = Json.parse("""{
      |   "messages":{
      |       "info" : [{"id" : "1","text" : "text"}],
      |       "warnings" :[{"id" : "1","text" : "text"}],
      |       "errors" :[{"id" : "1","text" : "text"}]
      |     }
      |}""".stripMargin)

  val desJsonWithOptionals: JsValue = Json.parse("""{
      |   "messages":{
      |       "info" : [{"id" : "1","text" : "text"}],
      |       "warnings" :[{"id" : "1","text" : "text"}],
      |       "errors" :[{"id" : "1","text" : "text"}]
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
    calculationErrorCount = None
  )

  val metadataWrapper = new GetCalculationMetadataResponse(metadata)

  "GetCalculationMetadata" when {
    "read from JSON" should {
      "return a JsSuccess" in {
        desJson.validate[GetCalculationMetadataResponse] shouldBe a[JsSuccess[GetCalculationMetadataResponse]]
      }
      "with a valid GetCalculationMetadataResponse" in {
        desJson.as[GetCalculationMetadataResponse] shouldBe metadataWrapper
      }
      "containing a Metadata object" in {
        desJson.as[GetCalculationMetadataResponse].metadata shouldBe metadata
      }
    }
    "written to JSON" should {
      "return a JsObject" in {
        Json.toJson(metadataWrapper) shouldBe desJsonWithOptionals
      }
    }
  }
}
