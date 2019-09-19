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
import v1.models.domain.{CalculationReason, CalculationRequestor, CalculationType}
import v1.models.response.common.{Message, Messages, Metadata, _}
import v1.models.response.getCalculation.incomeTaxAndNics.IncomeTax
import v1.models.response.getCalculation.incomeTaxAndNics.detail.{CalculationDetail, IncomeTaxDetail, IncomeTypeBreakdown}
import v1.models.response.getCalculation.incomeTaxAndNics.summary.{CalculationSummary, IncomeTaxSummary}

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
      |       },
      |    "messages" :{
      |        "errors":[
      |        {"id":"id1", "text":"text1"}
      |        ]
      |     }
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

  val messages = Messages(None, None, Some(Seq(Message("id1", "text1"))))
  val calculationSummary = CalculationSummary(IncomeTaxSummary(100.25, None, None), None, None, None, 200.25, "UK")
  val calculationDetail = CalculationDetail(IncomeTaxDetail(Some(IncomeTypeBreakdown(300.25, 400.25, None)), None, None, None), None, None)
  val incomeTax = IncomeTax(calculationSummary, calculationDetail)
  val calculationResponse = GetCalculationResponse(metadata, messages = Some(messages))
  val calculationResponseFull = GetCalculationResponse(metadata, Some(incomeTax), Some(messages))

  val desJsonWithIncomeTax: JsValue = desJson.as[JsObject] ++
    Json.parse(
      """
        |{
        | "calculation" : {
        |   "taxCalculation" : {
        |     "incomeTax" : {
        |       "incomeTaxCharged" : 100.25,
        |       "payPensionsProfit" : {
        |           "allowancesAllocated" : 300.25,
        |           "incomeTaxAmount": 400.25
        |        }
        |     },
        |     "totalIncomeTaxAndNicsDue" : 200.25
        |   }
        | },
        | "inputs" : {
        |  "personalInformation" : {
        |    "taxRegime" : "UK"
        |  }
        | }
        |}
      """.stripMargin).as[JsObject]

  "GetCalculationResponse" should {

    "successfully read from json" when {

      "provided with valid json with only metadata" in {
        desJson.validate[GetCalculationResponse] shouldBe a[JsSuccess[_]]
        desJson.as[GetCalculationResponse] shouldBe calculationResponse
      }

      "provided with valid json with income tax and messages" in {
        desJsonWithIncomeTax.validate[GetCalculationResponse] shouldBe a[JsSuccess[_]]
        desJsonWithIncomeTax.as[GetCalculationResponse] shouldBe calculationResponseFull
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
