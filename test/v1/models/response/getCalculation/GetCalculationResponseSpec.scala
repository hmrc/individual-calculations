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
import v1.models.response.common.{Message, Messages, Metadata}
import v1.models.response.getCalculation.allowancesAndDeductions.AllowancesDeductionsAndReliefs
import v1.models.response.getCalculation.incomeTaxAndNics.IncomeTax
import v1.models.response.getCalculation.incomeTaxAndNics.detail.{CalculationDetail, IncomeTaxDetail, IncomeTypeBreakdown}
import v1.models.response.getCalculation.incomeTaxAndNics.summary.{CalculationSummary, IncomeTaxSummary}
import v1.models.response.getCalculation.taxableIncome.TaxableIncome
import v1.models.response.getCalculation.taxableIncome.detail.PayPensionsProfit
import v1.models.response.getCalculation.allowancesAndDeductions.summary.{CalculationSummary => ADRCalculationSummary}
import v1.models.response.getCalculation.allowancesAndDeductions.detail.{CalculationDetail => ADRCalculationDetail}

class GetCalculationResponseSpec extends UnitSpec {

  val desJsonWithoutOptionalParts: JsValue = Json.parse("""{
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
      |     "messages": {
      |        "errors":[
      |        {"id":"id1", "text":"text1"}
      |        ]
      |     }
      |}""".stripMargin)

  val desJsonWithAllParts: JsValue = desJsonWithoutOptionalParts.as[JsObject] ++
    Json.parse("""
                 |{
                 | "calculation" : {
                 |   "taxCalculation" : {
                 |     "incomeTax" : {
                 |       "incomeTaxCharged" : 100.25,
                 |       "totalIncomeReceivedFromAllSources": 123,
                 |       "totalTaxableIncome": 234,
                 |       "payPensionsProfit" : {
                 |           "allowancesAllocated" : 300.25,
                 |           "incomeTaxAmount": 400.25,
                 |           "incomeReceived" : 500,
                 |           "taxableIncome": 600
                 |        }
                 |     },
                 |     "totalIncomeTaxAndNicsDue" : 200.25
                 |   }
                 |  },
                 | "inputs" : {
                 |  "personalInformation" : {
                 |    "taxRegime" : "UK"
                 |  }
                 | }
                 |}
      """.stripMargin).as[JsObject]

  val writtenJson: JsValue = Json.parse("""
      |{
      |  "metadata": {
      |    "id": "f2fb30e5-4ab6-4a29-b3c1-c7264259ff1c",
      |    "taxYear": "2018-19",
      |    "requestedBy": "customer",
      |    "requestedTimestamp": "2019-11-15T09:25:15.094Z",
      |    "calculationReason": "customerRequest",
      |    "calculationTimestamp": "2019-11-15T09:35:15.094Z",
      |    "calculationType": "inYear",
      |    "intentToCrystallise": false,
      |    "crystallised": false,
      |    "totalIncomeTaxAndNicsDue": 200.25,
      |    "calculationErrorCount": 1
      |  },
      |  "incomeTaxAndNicsCalculated": {
      |    "summary": {
      |      "incomeTax": {
      |        "incomeTaxCharged": 100.25
      |      },
      |      "totalIncomeTaxAndNicsDue": 200.25,
      |      "taxRegime": "UK"
      |    },
      |    "detail": {
      |      "incomeTax": {
      |        "payPensionsProfit": {
      |          "allowancesAllocated": 300.25,
      |          "incomeTaxAmount": 400.25
      |        }
      |      }
      |    }
      |  },
      |  "messages": {
      |    "errors": [
      |      {
      |        "id": "id1",
      |        "text": "text1"
      |      }
      |    ]
      |  },
      |  "taxableIncome": {
      |    "summary": {
      |      "totalIncomeReceivedFromAllSources": 123,
      |      "totalTaxableIncome": 234
      |    },
      |    "detail": {
      |     "payPensionsProfit": {
      |       "incomeReceived":500,
      |       "taxableIncome":600
      |       }
      |    }
      |  }
      |}
      |""".stripMargin)

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
    totalIncomeTaxAndNicsDue = None,
    calculationErrorCount = Some(1)
  )

  val messages           = Messages(None, None, Some(Seq(Message("id1", "text1"))))
  val calculationSummary = CalculationSummary(IncomeTaxSummary(100.25, None, None), None, None, None, 200.25, "UK")
  val calculationDetail  = CalculationDetail(IncomeTaxDetail(Some(IncomeTypeBreakdown(300.25, 400.25, None)), None, None, None), None, None)
  val incomeTax          = IncomeTax(calculationSummary, calculationDetail)

  val taxableIncomeModel = TaxableIncome(
    taxableIncome.summary.CalculationSummary(123, 234),
    taxableIncome.detail.CalculationDetail(Some(PayPensionsProfit(500, 600, None, None, None, None, None)), None, None)
  )
  val calculationResponse         = GetCalculationResponse(metadata, messages = Some(messages))
  val calculationResponseAllParts = GetCalculationResponse(metadata.copy(totalIncomeTaxAndNicsDue = Some(200.25)), Some(incomeTax), Some(messages), Some(taxableIncomeModel),
  Some(AllowancesDeductionsAndReliefs(ADRCalculationSummary(None,None),ADRCalculationDetail(None,None))))

  "GetCalculationResponse" should {

    "successfully read from json" when {
      "provided with valid json with only metadata" in {
        desJsonWithoutOptionalParts.as[GetCalculationResponse] shouldBe calculationResponse
      }

      "provided with valid json with all optional top-level parts" in {
        desJsonWithAllParts.as[GetCalculationResponse] shouldBe calculationResponseAllParts
      }
    }

    "fail to read from json" when {
      "invalid metadata JSON" in {
        // Mandatory value at /metadata/requestedBy is missing...
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

        invalidDesJson.validate[GetCalculationResponse] shouldBe a[JsError]
      }

      "read invalid income tax and NICs JSON" in {
        // Mandatory value at /calculation/taxCalculation/totalIncomeTaxAndNicsDue is missing...
        val invalidDesJson: JsValue = desJsonWithoutOptionalParts.as[JsObject] ++
          Json.parse("""
                       |{
                       | "calculation" : {
                       |   "taxCalculation" : {
                       |     "incomeTax" : {
                       |       "incomeTaxCharged" : 100.25,
                       |       "totalIncomeReceivedFromAllSources": 123,
                       |       "totalTaxableIncome": 234,
                       |       "payPensionsProfit" : {
                       |           "allowancesAllocated" : 300.25,
                       |           "incomeTaxAmount": 400.25
                       |        }
                       |     }
                       |   }
                       |  },
                       | "inputs" : {
                       |  "personalInformation" : {
                       |    "taxRegime" : "UK"
                       |  }
                       | }
                       |}
                      """.stripMargin).as[JsObject]

        invalidDesJson.validate[GetCalculationResponse] shouldBe a[JsError]
      }

      "read invalid taxable income JSON" in {
        // Mandatory value at /calculation/taxCalculation/incomeTax/totalTaxableIncome is missing...
        val invalidDesJson: JsValue = desJsonWithoutOptionalParts.as[JsObject] ++
          Json.parse("""
                       |{
                       | "calculation" : {
                       |   "taxCalculation" : {
                       |     "incomeTax" : {
                       |       "incomeTaxCharged" : 100.25,
                       |       "totalIncomeReceivedFromAllSources": 123,
                       |       "payPensionsProfit" : {
                       |           "allowancesAllocated" : 300.25,
                       |           "incomeTaxAmount": 400.25
                       |        }
                       |     },
                       |     "totalIncomeTaxAndNicsDue" : 200.25
                       |   }
                       |  },
                       | "inputs" : {
                       |  "personalInformation" : {
                       |    "taxRegime" : "UK"
                       |  }
                       | }
                       |}
                      """.stripMargin).as[JsObject]

        invalidDesJson.validate[GetCalculationResponse] shouldBe a[JsError]
      }
    }

    "write correctly to json" when {
      "using a model with only metadata" in {
        Json.toJson(calculationResponseAllParts) shouldBe writtenJson
      }
    }
  }
}
