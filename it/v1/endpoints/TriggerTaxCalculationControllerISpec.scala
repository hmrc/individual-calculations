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

package v1.endpoints

import com.github.tomakehurst.wiremock.stubbing.StubMapping
import play.api.http.HeaderNames.ACCEPT
import play.api.http.Status
import play.api.http.Status.OK
import play.api.libs.json.{JsValue, Json}
import play.api.libs.ws.{WSRequest, WSResponse}
import support.IntegrationBaseSpec
import v1.models.errors._
import v1.models.request.DesTaxYear
import v1.stubs.{AuditStub, AuthStub, DesStub, MtdIdLookupStub}

class TriggerTaxCalculationControllerISpec extends IntegrationBaseSpec {

  private trait Test {

    val nino = "AA123456A"
    val taxYear = "2017-18"
    val calcId = "041f7e4d-87b9-4d4a-a296-3cfbdf92f7e2"

    val requestJson: JsValue = Json.parse(
      s"""
         |{
         |"taxYear": "$taxYear"
         |}
    """.stripMargin)

    val responseBody: JsValue = Json.parse(
      s"""
         | {
         | "id": "$calcId"
         | }
      """.stripMargin)

    def uri: String = s"/$nino/self-assessment"

    val desTaxYear: DesTaxYear = DesTaxYear.fromMtd(taxYear)

    lazy val desUrl = s"/income-tax/nino/$nino/taxYear/$desTaxYear/tax-calculation"

    def setupStubs(): StubMapping

    def request(): WSRequest = {
      setupStubs()
      buildRequest(uri)
        .withHttpHeaders((ACCEPT, "application/vnd.hmrc.1.0+json"))
    }
  }


  val emptyBody: JsValue = Json.parse(
    s"""
       | {
       | }
      """.stripMargin)

  def createTriggerTaxCalcBody(taxYear: String): JsValue = Json.parse(
    s"""{
       |  "taxYear": "$taxYear"
       |}
    """.stripMargin)

  def errorBody(code: String): String =
    s"""
       |      {
       |        "code": "$code",
       |        "reason": "backend message"
       |      }
      """.stripMargin

  "Calling the trigger a self assessment tax calculation endpoint" should {

    "return a 202 status code" when {

      "any valid request is made" in new Test {

        override def setupStubs(): StubMapping = {
          AuditStub.audit()
          AuthStub.authorised()
          MtdIdLookupStub.ninoFound(nino)

          DesStub.onSuccess(DesStub.POST, desUrl, OK, responseBody)
        }

        val response: WSResponse = await(request().post(requestJson))
        response.status shouldBe Status.ACCEPTED
        response.header("Content-Type") shouldBe Some("application/json")
      }
    }

    "return a 202 status code with same correlation id" when {

      "a correlation id is received in the request" in new Test {

        override def setupStubs(): StubMapping = {
          AuditStub.audit()
          AuthStub.authorised()
          MtdIdLookupStub.ninoFound(nino)

          DesStub.onSuccess(DesStub.POST, desUrl, OK, responseBody, Map("CorrelationId" -> "same-id"))
        }

        val response: WSResponse = await(request().addHttpHeaders("CorrelationId" -> "same-id").post(requestJson))
        response.status shouldBe Status.ACCEPTED
        response.header("Content-Type") shouldBe Some("application/json")
        response.header("X-CorrelationId") shouldBe Some("same-id")
      }
    }

    "return error according to spec" when {

      "validation error" when {
        def validationErrorTest(requestNino: String, body: JsValue, expectedStatus: Int, expectedBody: MtdError): Unit = {
          s"validation fails with ${expectedBody.code} error" in new Test {

            override val nino: String = requestNino

            override def setupStubs(): StubMapping = {
              AuditStub.audit()
              AuthStub.authorised()
              MtdIdLookupStub.ninoFound(nino)
            }

            val response: WSResponse = await(request().post(body))

            response.status shouldBe expectedStatus
            response.json shouldBe Json.toJson(expectedBody)
            response.header("Content-Type") shouldBe Some("application/json")
          }
        }

        val input = Seq(
          ("AA1123A", createTriggerTaxCalcBody("2018-19"), Status.BAD_REQUEST, NinoFormatError),
          ("AA123456A", createTriggerTaxCalcBody("badTaxYear"), Status.BAD_REQUEST, TaxYearFormatError),
          ("AA123456A", createTriggerTaxCalcBody("2016-17"), Status.BAD_REQUEST, RuleTaxYearNotSupportedError),
          ("AA123456A", createTriggerTaxCalcBody("2018-20"), Status.BAD_REQUEST, RuleTaxYearRangeExceededError),
          ("AA123456A", emptyBody, Status.BAD_REQUEST, RuleIncorrectOrEmptyBodyError)
        )

        input.foreach(args => (validationErrorTest _).tupled(args))
      }

      "backend service error" when {
        def serviceErrorTest(backendStatus: Int, backendCode: String, expectedStatus: Int, expectedBody: MtdError): Unit = {
          s"backend returns an $backendCode error and status $backendStatus" in new Test {

            override def setupStubs(): StubMapping = {
              AuditStub.audit()
              AuthStub.authorised()
              MtdIdLookupStub.ninoFound(nino)
              DesStub.onError(DesStub.POST, desUrl, backendStatus, errorBody(backendCode))
            }

            val response: WSResponse = await(request().post(requestJson))
            response.status shouldBe expectedStatus
            response.json shouldBe Json.toJson(expectedBody)
            response.header("Content-Type") shouldBe Some("application/json")
          }
        }

        val input = Seq(
          (Status.BAD_REQUEST, "INVALID_NINO", Status.BAD_REQUEST, NinoFormatError),
          (Status.BAD_REQUEST, "INVALID_TAX_YEAR", Status.BAD_REQUEST, TaxYearFormatError),
          (Status.FORBIDDEN, "NO_SUBMISSION_EXIST", Status.FORBIDDEN, RuleNoIncomeSubmissionsExistError),
          (Status.BAD_REQUEST, "INVALID_REQUEST", Status.INTERNAL_SERVER_ERROR, DownstreamError),
          (Status.CONFLICT, "CONFLICT", Status.INTERNAL_SERVER_ERROR, DownstreamError),
          (Status.BAD_REQUEST, "INVALID_TAX_CRYSTALLISE", Status.INTERNAL_SERVER_ERROR, DownstreamError),
          (Status.INTERNAL_SERVER_ERROR, "SERVER_ERROR", Status.INTERNAL_SERVER_ERROR, DownstreamError),
          (Status.SERVICE_UNAVAILABLE, "SERVICE_UNAVAILABLE", Status.INTERNAL_SERVER_ERROR, DownstreamError))

        input.foreach(args => (serviceErrorTest _).tupled(args))
      }
    }
  }
}
