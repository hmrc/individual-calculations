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

package v1.models.response.getCalculation

import play.api.libs.json._
import support.UnitSpec
import v1.models.des.selfAssessment.componentObjects.{CalculationDetail, CalculationSummary, IncomeTax}
import v1.models.domain.{CalculationReason, CalculationRequestor, CalculationType}
import v1.models.response.common.Metadata

class GetCalculationResponseSpec extends UnitSpec {

  val desJson: JsValue = Json.parse(
    """{
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

  val invalidDesJson: JsValue = Json.parse(
    """{
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

  val writtenJson: JsValue = Json.parse(
    """{
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

  val calculationSummary = CalculationSummary("test")
  val calculationDetail = CalculationDetail("test")
  val incomeTax = IncomeTax(calculationSummary, calculationDetail)

  val desJsonWithIncomeTax: JsValue = desJson.as[JsObject] ++ Json.parse(
    s"""
       |{
       | "summary" : ${Json.toJson(calculationSummary).toString()},
       | "detail" : ${Json.toJson(calculationDetail).toString()}
       |}
    """.stripMargin).as[JsObject]

  val calculationResponse = GetCalculationResponse(metadata)
  val calculationResponseWithIncome = GetCalculationResponse(metadata, Some(incomeTax))

  "GetCalculationResponse" should {

    "successfully read from json" when {

      "provided with valid json with only metadata" in {
        desJson.validate[GetCalculationResponse] shouldBe a[JsSuccess[_]]
        desJson.as[GetCalculationResponse] shouldBe calculationResponse
      }

      "provided with valid json with income tax" in {
        desJsonWithIncomeTax.validate[GetCalculationResponse] shouldBe a[JsSuccess[_]]
        desJsonWithIncomeTax.as[GetCalculationResponse] shouldBe calculationResponseWithIncome
      }
    }

    "fail to read from json" when {

      "read from invalid JSON" in {
        invalidDesJson.validate[GetCalculationResponse] shouldBe a[JsError]
      }
    }

    "write correctly to json" when {

      "using a model with only metadata" in {

        Json.toJson(calculationResponse) shouldBe writtenJson
      }
    }
  }
}
