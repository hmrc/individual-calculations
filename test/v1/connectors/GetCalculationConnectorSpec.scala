/*
 * Copyright 2020 HM Revenue & Customs
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
import v1.fixtures.common.MessageFixtures._
import v1.mocks.{MockAppConfig, MockHttpClient}
import v1.models.domain.{CalculationReason, CalculationRequestor, CalculationType}
import v1.models.outcomes.ResponseWrapper
import v1.models.request.getCalculation.GetCalculationRequest
import v1.models.response.common.{Messages, Metadata}
import v1.models.response.getCalculation.GetCalculationResponse

import scala.concurrent.Future

class GetCalculationConnectorSpec extends ConnectorSpec {

  val nino = Nino("AA123456A")
  val calcId = "041f7e4d-87b9-4d4a-a296-3cfbdf92f7e2"

  val metadataResponse = Metadata(
    id = "041f7e4d-87b9-4d4a-a296-3cfbdf92f7e2",
    taxYear = "2018-19",
    requestedBy = CalculationRequestor.customer,
    requestedTimestamp = Some("2019-11-15T09:25:15.094Z"),
    calculationReason = CalculationReason.customerRequest,
    calculationTimestamp = Some("2019-11-15T09:35:15.094Z"),
    calculationType = CalculationType.inYear,
    intentToCrystallise = false,
    crystallised = false,
    totalIncomeTaxAndNicsDue = None,
    calculationErrorCount = Some(1)
  )

  val messagesResponse = Messages(Some(Seq(info1,info2)), Some(Seq(warn1,warn2)), Some(Seq(err1,err2)))

  val getCalculationResponse = GetCalculationResponse(metadataResponse, messages = Some(messagesResponse))

  class Test extends MockHttpClient with MockAppConfig {
    val connector: TaxCalcConnector = new TaxCalcConnector(http = mockHttpClient, appConfig = mockAppConfig)

    val desRequestHeaders: Seq[(String, String)] = Seq("Environment" -> "des-environment", "Authorization" -> s"Bearer des-token")
    MockedAppConfig.desToken returns "des-token"
    MockedAppConfig.desEnvironment returns "des-environment"
    MockedAppConfig.desBaseUrl returns baseUrl
  }

  "get calculation" when {
    val request = GetCalculationRequest(nino, calcId)

    "a valid request is supplied" should {
      "return a successful response with the correct correlationId" in new Test {

        val expected = Right(ResponseWrapper(correlationId, getCalculationResponse))

        MockedHttpClient
          .get(s"$baseUrl/income-tax/03.00.00/calculation-data/$nino/calcId/$calcId", desRequestHeaders: _*)
          .returns(Future.successful(expected))

        await(connector.getCalculation(request)) shouldBe expected

      }
    }
  }
}
