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
import v1.mocks.{MockAppConfig, MockHttpClient}
import v1.models.outcomes.ResponseWrapper
import v1.models.request.triggerCalculation.{TriggerTaxCalculation, TriggerTaxCalculationRequest}
import v1.models.request.{DesTaxYear, EmptyJsonBody}
import v1.models.response.triggerCalculation.TriggerCalculationResponse

import scala.concurrent.Future

class TriggerTaxCalcConnectorSpec extends ConnectorSpec {

  val taxYear = "2017-18"
  val desTaxYear: String = DesTaxYear.fromMtd(taxYear).toString
  val nino = "AA123456A"
  val calcId = "041f7e4d-87b9-4d4a-a296-3cfbdf92f7e2"


  class Test extends MockHttpClient with MockAppConfig {
    val connector: TaxCalcConnector = new TaxCalcConnector(http = mockHttpClient, appConfig = mockAppConfig)

    val desRequestHeaders = Seq("Environment" -> "des-environment", "Authorization" -> s"Bearer des-token")
    MockedAppConfig.desBaseUrl returns baseUrl
    MockedAppConfig.desToken returns "des-token"
    MockedAppConfig.desEnvironment returns "des-environment"
  }

  "trigger a tax calculation" when {

    val request = TriggerTaxCalculationRequest(Nino(nino), TriggerTaxCalculation(taxYear))
    "a valid request is supplied" should {
      "return a successful response with the correct correlationId" in new Test {

        val expected = Right(ResponseWrapper(correlationId, TriggerCalculationResponse(calcId)))

        MockedHttpClient
          .post(s"$baseUrl/income-tax/nino/$nino/taxYear/$desTaxYear/tax-calculation", EmptyJsonBody, desRequestHeaders: _*)
          .returns(Future.successful(expected))

        await(connector.triggerTaxCalculation(request)(hc, ec, correlationId)) shouldBe expected
      }
    }

    "a valid request with header carrier contains correlation id is supplied" should {
      "send request to des with single correlationId in the header and return a successful response" in new Test {

        val expected = Right(ResponseWrapper(correlationId, TriggerCalculationResponse(calcId)))

        MockedHttpClient
          .post(s"$baseUrl/income-tax/nino/$nino/taxYear/$desTaxYear/tax-calculation", EmptyJsonBody, desRequestHeaders: _*)
          .returns(Future.successful(expected))

        await(connector.triggerTaxCalculation(request)(hc.withExtraHeaders("CorrelationId"-> "X-123"), ec, correlationId)) shouldBe expected
      }
    }
  }

}
