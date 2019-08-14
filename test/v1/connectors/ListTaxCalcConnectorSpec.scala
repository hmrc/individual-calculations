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

package v1.connectors

import uk.gov.hmrc.domain.Nino
import v1.mocks.{MockAppConfig, MockHttpClient}
import v1.models.des.selfAssessment
import v1.models.des.selfAssessment.{CalculationListItem, ListCalculationsResponse}
import v1.models.domain.selfAssessment.{CalculationRequestor, CalculationType}
import v1.models.outcomes.ResponseWrapper
import v1.models.requestData.DesTaxYear
import v1.models.requestData.selfAssessment.ListCalculationsRequest

import scala.concurrent.Future

class ListTaxCalcConnectorSpec extends ConnectorSpec {

  val taxYear = DesTaxYear("2019")
  val nino = Nino("AA123456A")
  val calcId = "041f7e4d-87b9-4d4a-a296-3cfbdf92f7e2"

  val listCalcResponse = ListCalculationsResponse(
    Seq(
      CalculationListItem(
        id = "f2fb30e5-4ab6-4a29-b3c1-c7264259ff1c",
        calculationTimestamp = "2019-03-17T09:22:59Z",
        `type` = CalculationType.inYear,
        requestedBy = Some(CalculationRequestor.hmrc)
      ),
      selfAssessment.CalculationListItem(
        id = "cf63c46a-1a4f-3c56-b9ea-9a82551d27bb",
        calculationTimestamp = "2019-06-17T18:45:59Z",
        `type` = CalculationType.crystallisation,
        requestedBy = None
      )
    ))

  class Test extends MockHttpClient with MockAppConfig {
    val connector: TaxCalcConnector = new TaxCalcConnector(http = mockHttpClient, appConfig = mockAppConfig)

    val desRequestHeaders = Seq("Environment" -> "des-environment", "Authorization" -> s"Bearer des-token")
    MockedAppConfig.desBaseUrl returns baseUrl
    MockedAppConfig.desToken returns "des-token"
    MockedAppConfig.desEnvironment returns "des-environment"
  }

  "List tax calculations" when {

    val request = ListCalculationsRequest(nino, None)
    val taxYearRequest = ListCalculationsRequest(nino, Some(taxYear))

    "a valid request is supplied with no query parameters" should {
      "return a successful response with the correct correlationId" in new Test {

        val expected = Right(ResponseWrapper(correlationId, listCalcResponse))

        MockedHttpClient
          .parameterGet(s"$baseUrl/income-tax/list-of-calculation-results/$nino", Seq(), desRequestHeaders: _*)
          .returns(Future.successful(expected))

        await(connector.listCalculations(request)) shouldBe expected

      }
    }

    "a valid request is supplied with a tax year parameter" should {
      "return a successful response with the correct correlationId" in new Test {
        val expected = Right(ResponseWrapper(correlationId, listCalcResponse))

        MockedHttpClient
          .parameterGet(s"$baseUrl/income-tax/list-of-calculation-results/$nino", Seq(("taxYear", "2019")), desRequestHeaders: _*)
          .returns(Future.successful(expected))

        await(connector.listCalculations(taxYearRequest)) shouldBe expected
      }
    }
  }
}
