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
 * WITHOUT WARRANTIED OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package v1.endpoints

import com.github.tomakehurst.wiremock.stubbing.StubMapping
import play.api.http.HeaderNames.ACCEPT
import play.api.http.Status._
import play.api.libs.json.Json
import play.api.libs.ws.{WSRequest, WSResponse}
import support.IntegrationBaseSpec
import v1.models.errors._
import v1.stubs.{AuditStub, AuthStub, DesStub, MtdIdLookupStub}

class ListCalculationsControllerISpec extends IntegrationBaseSpec {

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

  "Calling the sample endpoint" should {

    "return a 200 status code" when {

      val successBody = Json.parse("""{
      |  "calculations": [
      |    {
      |      "id": "f2fb30e5-4ab6-4a29-b3c1-c7264259ff1c",
      |      "calculationTimestamp": "2019-03-17T09:22:59Z",
      |      "type": "inYear",
      |      "requestedBy": "hmrc"
      |    }
      |  ]
      |}""".stripMargin)

      val desSuccessBody = Json.parse("""[
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

      "valid request is made with a tax year" in new Test {

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

    "return error according to spec" when {

      "validation error" when {
        def validationErrorTest(requestNino: String, requestTaxYear: String, expectedStatus: Int, expectedBody: MtdError): Unit = {
          s"validation fails with ${expectedBody.code} error" in new Test {

            override val nino: String            = requestNino
            override val taxYear: String         = requestTaxYear

            override def setupStubs(): StubMapping = {
              AuditStub.audit()
              AuthStub.authorised()
              MtdIdLookupStub.ninoFound(nino)
            }

            val response: WSResponse = await(request.get)
            response.status shouldBe expectedStatus
            response.json shouldBe Json.toJson(expectedBody)
            response.header("Content-Type") shouldBe Some("application/json")
          }
        }

        val input = Seq(
          ("AA1123A", "2019-20", BAD_REQUEST, NinoFormatError),
          ("AA123456A", "20177", BAD_REQUEST, TaxYearFormatError),
          ("AA123456A", "2015-16", BAD_REQUEST, RuleTaxYearNotSupportedError),
          ("AA123456A", "2020-22", BAD_REQUEST, RuleTaxYearRangeExceededError)
        )

        input.foreach(args => (validationErrorTest _).tupled(args))
      }

      "des service error" when {

        def errorBody(code: String): String =
          s"""{
             |  "code": "$code",
             |  "reason": "des message"
             |}""".stripMargin

        def serviceErrorTest(desStatus: Int, desCode: String, expectedStatus: Int, expectedBody: MtdError): Unit = {
          s"des returns an $desCode error and status $desStatus" in new Test {

            override def setupStubs(): StubMapping = {
              AuditStub.audit()
              AuthStub.authorised()
              MtdIdLookupStub.ninoFound(nino)
              DesStub.onError(DesStub.GET, desUrl, desStatus, errorBody(desCode))
            }

            val response: WSResponse = await(request.get)
            response.status shouldBe expectedStatus
            response.json shouldBe Json.toJson(expectedBody)
            response.header("Content-Type") shouldBe Some("application/json")
          }
        }

        val input = Seq(
          (BAD_REQUEST, "INVALID_TAXABLE_ENTITY_ID", BAD_REQUEST, NinoFormatError),
          (BAD_REQUEST, "INVALID_TAXYEAR", BAD_REQUEST, TaxYearFormatError),
          (BAD_REQUEST, "NOT_FOUND", NOT_FOUND, NotFoundError),
          (INTERNAL_SERVER_ERROR, "SERVER_ERROR", INTERNAL_SERVER_ERROR, DownstreamError),
          (SERVICE_UNAVAILABLE, "SERVICE_UNAVAILABLE", INTERNAL_SERVER_ERROR, DownstreamError),
          (BAD_REQUEST, "INVALID_REQUEST", INTERNAL_SERVER_ERROR, DownstreamError)
        )

        input.foreach(args => (serviceErrorTest _).tupled(args))
      }
    }
  }
}
