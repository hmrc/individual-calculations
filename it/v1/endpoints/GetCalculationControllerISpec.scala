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
import play.api.libs.json.{JsValue, Json}
import play.api.libs.ws.{WSRequest, WSResponse}
import support.IntegrationBaseSpec
import v1.models.errors._
import v1.stubs.{AuditStub, AuthStub, DesStub, MtdIdLookupStub}

class GetCalculationControllerISpec extends IntegrationBaseSpec {
  private trait Test {

    val nino          = "AA123456A"
    val calcId        = "041f7e4d-87b9-4d4a-a296-3cfbdf92f7e2"
    val query =
      """
        |{
        |  metadata {
        |    id
        |    taxYear
        |    requestedBy
        |    requestedTimestamp
        |    calculationReason
        |    calculationTimestamp
        |    calculationType
        |    intentToCrystallise
        |    crystallised
        |    calculationErrorCount
        |  }
        |  messages {
        |    errors {
        |      id
        |      text
        |    }
        |  }
        |}
        |""".stripMargin

    def desUrl: String = s"/income-tax/03.00.00/calculation-data/$nino/calcId/$calcId"

    def setupStubs(): StubMapping

    def request: WSRequest = {
      setupStubs()
      buildRequest(uri)
        .withHttpHeaders((ACCEPT, "application/vnd.hmrc.1.0+json"))
    }

    def uri: String = s"/$nino/self-assessment/$calcId"
  }

  def errorBody(code: String): String =
    s"""{
       |  "code": "$code",
       |  "reason": "des message"
       |}""".stripMargin

  "Calling the sample endpoint" should {

    "return a 200 status code" when {

      val desResponse: JsValue = Json.parse("""{
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
      |     "messages" :{
      |        "errors":[
      |        {"id":"id1", "text":"text1"}
      |        ]
      |     }
      |}""".stripMargin)

      val readJson: JsValue = Json.parse("""{
      |  "data": {
      |    "metadata":{
      |      "id": "f2fb30e5-4ab6-4a29-b3c1-c7264259ff1c",
      |      "taxYear": "2018-19",
      |      "requestedBy": "customer",
      |      "requestedTimestamp": "2019-11-15T09:25:15.094Z",
      |      "calculationReason": "customerRequest",
      |      "calculationTimestamp": "2019-11-15T09:35:15.094Z",
      |      "calculationType": "inYear",
      |      "intentToCrystallise": false,
      |      "crystallised": false,
      |      "calculationErrorCount": 1
      |    },
      |    "messages" :{
      |      "errors":[
      |        {
      |          "id":"id1",
      |          "text":"text1"
      |        }
      |      ]
      |    }
      |  }
      |}""".stripMargin)


      "valid request is made" in new Test {
        override def setupStubs(): StubMapping = {
          AuditStub.audit()
          AuthStub.authorised()
          MtdIdLookupStub.ninoFound(nino)
          DesStub.onSuccess(DesStub.GET, desUrl, OK, desResponse)
        }

        val response: WSResponse = await(request.post(Json.obj("query" -> query)))

        response.status shouldBe OK
        response.header("Content-Type") shouldBe Some("application/json")
        response.json shouldBe readJson
      }
    }

    "return a 404 not found" when {
      "the response contains an unwanted calc type" in new Test {
        val desResponse: JsValue = Json.parse("""{
          |    "metadata":{
          |       "calculationId": "f2fb30e5-4ab6-4a29-b3c1-c7264259ff1c",
          |       "taxYear": 2019,
          |       "requestedBy": "customer",
          |       "requestedTimestamp": "2019-11-15T09:25:15.094Z",
          |       "calculationReason": "customerRequest",
          |       "calculationTimestamp": "2019-11-15T09:35:15.094Z",
          |       "calculationType": "biss",
          |       "periodFrom": "1-2018",
          |       "periodTo": "1-2019"
          |     }
          |}""".stripMargin)

        override def setupStubs(): StubMapping = {
          AuditStub.audit()
          AuthStub.authorised()
          MtdIdLookupStub.ninoFound(nino)
          DesStub.onSuccess(DesStub.GET, desUrl, OK, desResponse)
        }

        val response: WSResponse = await(request.post(Json.obj("query" -> query)))
        response.status shouldBe  NOT_FOUND
        response.json shouldBe Json.toJson(NotFoundError)
        response.header("Content-Type") shouldBe Some("application/json")
      }
    }

    "return error according to spec" when {

      "validation error" when {
        def validationErrorTest(requestNino: String, requestCalcId: String, expectedStatus: Int, expectedBody: MtdError): Unit = {
          s"validation fails with ${expectedBody.code} error" in new Test {

            override val nino: String   = requestNino
            override val calcId: String = requestCalcId

            override def setupStubs(): StubMapping = {
              AuditStub.audit()
              AuthStub.authorised()
              MtdIdLookupStub.ninoFound(nino)
            }

            val response: WSResponse = await(request.post(Json.obj("query" -> query)))
            response.status shouldBe expectedStatus
            response.json shouldBe Json.toJson(expectedBody)
            response.header("Content-Type") shouldBe Some("application/json")
          }
        }

        val input = Seq(
          ("AA1123A", "f2fb30e5-4ab6-4a29-b3c1-c7264259ff1c", BAD_REQUEST, NinoFormatError),
          ("AA123456A", "asd", BAD_REQUEST, CalculationIdFormatError)
        )

        input.foreach(args => (validationErrorTest _).tupled(args))
      }

      "des service error" when {

        def serviceErrorTest(desStatus: Int, desCode: String, expectedStatus: Int, expectedBody: MtdError): Unit = {
          s"des returns an $desCode error and status $desStatus" in new Test {

            override def setupStubs(): StubMapping = {
              AuditStub.audit()
              AuthStub.authorised()
              MtdIdLookupStub.ninoFound(nino)
              DesStub.onError(DesStub.GET, desUrl, desStatus, errorBody(desCode))
            }

            val response: WSResponse = await(request.post(Json.obj("query" -> query)))
            response.status shouldBe expectedStatus
            response.json shouldBe Json.toJson(expectedBody)
            response.header("Content-Type") shouldBe Some("application/json")
          }
        }

        val input = Seq(
          (BAD_REQUEST, "INVALID_TAXABLE_ENTITY_ID", BAD_REQUEST, NinoFormatError),
          (BAD_REQUEST, "INVALID_CALCULATION_ID", BAD_REQUEST, CalculationIdFormatError),
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
