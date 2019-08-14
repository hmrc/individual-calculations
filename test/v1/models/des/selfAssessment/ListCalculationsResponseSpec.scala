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

import play.api.libs.json.Json
import support.UnitSpec
import v1.models.domain.selfAssessment.{CalculationRequestor, CalculationType}

class ListCalculationsResponseSpec extends UnitSpec {

  "JSON writes" must {
    val mtdJson = Json.parse(
      """{
        |  "calculations": [
        |    {
        |      "id": "f2fb30e5-4ab6-4a29-b3c1-c7264259ff1c",
        |      "calculationTimestamp": "2019-03-17T09:22:59Z",
        |      "type": "inYear",
        |      "requestedBy": "hmrc"
        |    },
        |    {
        |      "id": "cf63c46a-1a4f-3c56-b9ea-9a82551d27bb",
        |      "calculationTimestamp": "2019-06-17T18:45:59Z",
        |      "type": "crystallisation"
        |    }
        |  ]
        |}""".stripMargin)

    val response = ListCalculationsResponse(
      Seq(
        CalculationListItem(
          id = "f2fb30e5-4ab6-4a29-b3c1-c7264259ff1c",
          calculationTimestamp = "2019-03-17T09:22:59Z",
          `type` = CalculationType.inYear,
          requestedBy = Some(CalculationRequestor.hmrc)
        ),
        CalculationListItem(
          id = "cf63c46a-1a4f-3c56-b9ea-9a82551d27bb",
          calculationTimestamp = "2019-06-17T18:45:59Z",
          `type` = CalculationType.crystallisation,
          requestedBy = None
        )
      ))

    "align with response to front end" in {
      Json.toJson(response) shouldBe mtdJson
    }
  }

  "JSON reads" must {
    // Note: with and without optional requestedBy...
    val desJson = Json.parse(
      """[
        |	{
        |		"calculationId": "f2fb30e5-4ab6-4a29-b3c1-c7264259ff1c",
        |		"calculationTimestamp": "2019-03-17T09:22:59Z",
        |		"calculationType": "inYear",
        |		"requestedBy": "hmrc",
        |		"year": 2016,
        |		"fromDate": "2018-01-01",
        |		"toDate": "2019-01-01",
        |		"totalIncomeTaxAndNicsDue": 99999999999.99,
        |		"intentToCrystallise": true,
        |		"crystallised": true,
        |		"crystallisationTimestamp": "2019-07-13T07:51:43Z"
        |	},
        |	{
        |		"calculationId": "cf63c46a-1a4f-3c56-b9ea-9a82551d27bb",
        |		"calculationTimestamp": "2019-06-17T18:45:59Z",
        |		"calculationType": "crystallisation",
        |		"year": 2016,
        |		"fromDate": "2019-01-01",
        |		"toDate": "2020-01-01",
        |		"totalIncomeTaxAndNicsDue": -99999999999.99,
        |		"intentToCrystallise": true,
        |		"crystallised": true,
        |		"crystallisationTimestamp": "2019-07-13T07:51:43Z"
        |	}
        |]
        |""".stripMargin)

    val response = ListCalculationsResponse(
      Seq(
        CalculationListItem(
          id = "f2fb30e5-4ab6-4a29-b3c1-c7264259ff1c",
          calculationTimestamp = "2019-03-17T09:22:59Z",
          `type` = CalculationType.inYear,
          requestedBy = Some(CalculationRequestor.hmrc)
        ),
        CalculationListItem(
          id = "cf63c46a-1a4f-3c56-b9ea-9a82551d27bb",
          calculationTimestamp = "2019-06-17T18:45:59Z",
          `type` = CalculationType.crystallisation,
          requestedBy = None
        )
      ))


    "align with des spec" in {
      desJson.as[ListCalculationsResponse] shouldBe response
    }
  }
}
