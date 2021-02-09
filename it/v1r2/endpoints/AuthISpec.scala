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

package v1r2.endpoints

import com.github.tomakehurst.wiremock.stubbing.StubMapping
import play.api.http.HeaderNames.ACCEPT
import play.api.http.Status
import play.api.http.Status._
import play.api.libs.json.Json
import play.api.libs.ws.{WSRequest, WSResponse}
import support.IntegrationBaseSpec
import v1r2.stubs.{AuditStub, AuthStub, DesStub, MtdIdLookupStub}

class AuthISpec extends IntegrationBaseSpec {

  private trait Test {

    val nino                    = "AA123456A"
    val taxYear: String         = "2018-19"
    val calcId                  = "041f7e4d-87b9-4d4a-a296-3cfbdf92f7e2"
    val correlationId           = "X-123"

    def uri: String = s"/$nino/self-assessment"

    def desUrl: String = s"/income-tax/list-of-calculation-results/$nino"

    def setupStubs(): StubMapping

    def request: WSRequest = {
      val queryParams: Seq[(String, String)] = Seq("taxYear" -> taxYear)

      setupStubs()
      buildRequest(uri)
        .addQueryStringParameters(queryParams: _*)
        .withHttpHeaders((ACCEPT, "application/vnd.hmrc.1.0+json"))
    }
  }

  "Calling the List Calculations endpoint" when {

    "the NINO cannot be converted to a MTD ID" should {

      "return 500" in new Test {
        override val nino: String = "AA123456A"

        override def setupStubs(): StubMapping = {
          AuditStub.audit()
          MtdIdLookupStub.internalServerError(nino)
        }

        val response: WSResponse = await(request.get)
        response.status shouldBe Status.INTERNAL_SERVER_ERROR
      }
    }

    "an MTD ID is successfully retrieve from the NINO and the user is authorised" should {

      val successBody = Json.parse(
        """{
          |  "calculations": [
          |    {
          |      "id": "f2fb30e5-4ab6-4a29-b3c1-c7264259ff1c",
          |      "calculationTimestamp": "2019-03-17T09:22:59Z",
          |      "type": "inYear",
          |      "requestedBy": "hmrc"
          |    }
          |  ]
          |}""".stripMargin)

      val desSuccessBody = Json.parse(
        """[
          |    {
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
          | {
          |		"calculationId": "f2fb30e5-4ab6-4a29-b3c1-c7264259ff1d",
          |		"calculationTimestamp": "2019-03-17T09:22:59Z",
          |		"calculationType": "biss",
          |		"requestedBy": "hmrc",
          |		"year": 2016,
          |		"fromDate": "2018-01-01",
          |		"toDate": "2019-01-01",
          |		"totalIncomeTaxAndNicsDue": 99999999999.99,
          |		"intentToCrystallise": true,
          |		"crystallised": true,
          |		"crystallisationTimestamp": "2019-07-13T07:51:43Z"
          |	}
          |  ]""".stripMargin)

      "return 200 " in new Test {

        override def setupStubs(): StubMapping = {
          AuditStub.audit()
          AuthStub.authorised()
          MtdIdLookupStub.ninoFound(nino)
          DesStub.onSuccess(DesStub.GET, desUrl, Map("taxYear" -> "2019"), OK, desSuccessBody)
        }

        val response: WSResponse = await(request.get)

        response.status shouldBe OK
        response.header("Content-Type") shouldBe Some("application/json")
        response.json shouldBe successBody
      }
    }

    "an MTD ID is successfully retrieve from the NINO and the user is NOT logged in" should {

      "return 403" in new Test {
        override val nino: String = "AA123456A"

        override def setupStubs(): StubMapping = {
          AuditStub.audit()
          MtdIdLookupStub.ninoFound(nino)
          AuthStub.unauthorisedNotLoggedIn()
        }

        val response: WSResponse = await(request.get)
        response.status shouldBe Status.FORBIDDEN
      }
    }

    "an MTD ID is successfully retrieve from the NINO and the user is NOT authorised" should {

      "return 403" in new Test {
        override val nino: String = "AA123456A"

        override def setupStubs(): StubMapping = {
          AuditStub.audit()
          MtdIdLookupStub.ninoFound(nino)
          AuthStub.unauthorisedOther()
        }

        val response: WSResponse = await(request.get)
        response.status shouldBe Status.FORBIDDEN
      }
    }

  }

}
