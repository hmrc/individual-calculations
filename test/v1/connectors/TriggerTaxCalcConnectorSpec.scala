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
import v1.models.des.selfAssessment.CalculationIdResponse
import v1.models.domain.EmptyJsonBody
import v1.models.domain.selfAssessment.TriggerTaxCalculationBody
import v1.models.outcomes.ResponseWrapper
import v1.models.requestData.DesTaxYear
import v1.models.requestData.selfAssessment.TriggerTaxCalculationRequest

import scala.concurrent.Future

class TriggerTaxCalcConnectorSpec extends ConnectorSpec {

  val taxYear = "2017-18"
  val desTaxYear = DesTaxYear.fromMtd(taxYear).toString
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

    val request = TriggerTaxCalculationRequest(Nino(nino), TriggerTaxCalculationBody(taxYear))
    "a valid request is supplied" should {
      "return a successful response with the correct correlationId" in new Test {

        val expected = Right(ResponseWrapper(correlationId, CalculationIdResponse(calcId)))

        MockedHttpClient
          .post(s"$baseUrl/income-tax/nino/$nino/taxYear/$desTaxYear/tax-calculation", EmptyJsonBody, desRequestHeaders: _*)
          .returns(Future.successful(expected))

        await(connector.triggerTaxCalculation(request)) shouldBe expected
      }
    }
  }

}
