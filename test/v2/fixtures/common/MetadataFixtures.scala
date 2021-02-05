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

package v2.fixtures.common

import play.api.libs.json.{JsObject, JsValue, Json}
import v2.models.domain.{CalculationReason, CalculationRequestor, CalculationType}
import v2.models.response.common.Metadata

object MetadataFixtures {

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

  val writtenJson: JsValue = Json.parse("""
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

  val desJsonWithoutOptionalsAsModel = Metadata(
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

  val invalidDesJson: JsValue = Json.parse("""{
      |    "metadata":{}
      |}""".stripMargin)

}
