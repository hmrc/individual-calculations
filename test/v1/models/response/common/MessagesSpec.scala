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

package v1.models.response.common

import play.api.libs.json.{JsError, JsSuccess, JsValue, Json}
import support.UnitSpec
import v1.fixtures.Fixtures._

class MessagesSpec extends UnitSpec {

  val desJson: JsValue = Json.parse("""{
	      |     "messages" :{
	      |        "errors":[
	      |          {"id":"err1", "text":"text1"},
	      |          {"id":"err2", "text":"text2"}
	      |        ],
	      |        "info":[
	      |          {"id":"info1", "text":"text1"},
	      |          {"id":"info2", "text":"text2"}
	      |        ],
	      |        "warnings":[
	      |          {"id":"warn1", "text":"text1"},
	      |          {"id":"warn2", "text":"text2"}
	      |        ]
	      |     }
	      |}""".stripMargin)

  val writtenJson: JsValue = Json.parse("""{
	      |        "info":[
	      |          {"id":"info1", "text":"text1"},
	      |          {"id":"info2", "text":"text2"}
	      |        ],
	      |        "warnings":[
	      |          {"id":"warn1", "text":"text1"},
	      |          {"id":"warn2", "text":"text2"}
	      |        ],
	      |        "error":[
	      |          {"id":"err1", "text":"text1"},
	      |          {"id":"err2", "text":"text2"}
	      |        ]
	      |}""".stripMargin)

  val desJsonWithEmptyErrors: JsValue = Json.parse("""{
	      |     "messages" :{
	      |        "errors":[
	      |        ],
	      |        "info":[
	      |          {"id":"info1", "text":"text1"},
	      |          {"id":"info2", "text":"text2"}
	      |        ],
	      |        "warnings":[
	      |          {"id":"warn1", "text":"text1"},
	      |          {"id":"warn2", "text":"text2"}
	      |        ]
	      |     }
	      |}""".stripMargin)

  val desJsonWithoutErrors: JsValue = Json.parse("""{
	      |     "messages" :{
	      |        "info":[
	      |          {"id":"info1", "text":"text1"},
	      |          {"id":"info2", "text":"text2"}
	      |        ],
	      |        "warnings":[
	      |          {"id":"warn1", "text":"text1"},
	      |          {"id":"warn2", "text":"text2"}
	      |        ]
	      |     }
	      |}""".stripMargin)

  val writtenJsonWithoutErrors: JsValue = Json.parse("""{
	      |        "info":[
	      |          {"id":"info1", "text":"text1"},
	      |          {"id":"info2", "text":"text2"}
	      |        ],
	      |        "warnings":[
	      |          {"id":"warn1", "text":"text1"},
	      |          {"id":"warn2", "text":"text2"}
	      |        ]
	      |}""".stripMargin)

  val desJsonWithoutMessages: JsValue = Json.parse("""{
	      |     "filler" :"json"
	      |}""".stripMargin)

  val invalidDesJson: JsValue = Json.parse("""{
	      |     "messages" :{
	      |        "info":[
	      |          {"id":"info1"}
	      |        ],
	      |        "warnings":[
	      |          {"id":"warn1", "text":"text1"},
	      |          {"id":"warn2", "text":"text2"}
	      |        ]
	      |     }
	      |}""".stripMargin)

  val validMessageJson: JsValue = Json.parse("""{
	      |        "id":"err1",
	      |        "text": "text1"
	      |}""".stripMargin)

  val invalidMessageJson: JsValue = Json.parse("""{
	      |        "id":1,
	      |        "text": "text1"
	      |}""".stripMargin)


  val messagesResponse = Messages(Some(Seq(info1,info2)), Some(Seq(warn1,warn2)), Some(Seq(err1,err2)))
  val messagesResponseWithoutErrors = Messages(Some(Seq(info1,info2)), Some(Seq(warn1,warn2)), None)
  val emptyMessageResponse = Messages(None, None, None)

  "Messages" when {
    "read from a valid JSON" should {
      "return a JsSuccess" in {
        desJson.validate[Messages] shouldBe a[JsSuccess[_]]
      }
      "with the expected Messages object" in {
        desJson.as[Messages] shouldBe messagesResponse
      }
    }

    "read from an invalid JSON" should {
      "return a JsError" in {
        invalidDesJson.validate[Messages] shouldBe a[JsError]
      }
    }

    "written to JSON" should {
      "return the expected JsObject" in {
        Json.toJson(messagesResponse) shouldBe writtenJson
      }
    }

    "errors are not present" should {
      "not write the errors field" in {
        Json.toJson(messagesResponseWithoutErrors) shouldBe writtenJsonWithoutErrors
      }
      "read the calculationErrorCount as None" in {
        desJsonWithoutErrors.as[Messages] shouldBe messagesResponseWithoutErrors
      }
    }

    "errors is present, but is empty" should {
      "read the calculationErrorCount as None" in{
        desJsonWithEmptyErrors.as[Messages] shouldBe messagesResponseWithoutErrors
      }
    }

    "messages are not present" should {
      "read the calculationErrorCount as None" in {
        desJsonWithoutMessages.as[Messages] shouldBe emptyMessageResponse
      }
    }
  }

  "Message" when {
    "read from valid JSON" should{
      "return a JsSuccess" in{
        validMessageJson.validate[Message] shouldBe a[JsSuccess[_]]
      }
      "with the expected Message object" in{
        validMessageJson.as[Message] shouldBe err1
      }
    }
    "read from invalid JSON" should{
      "return a JsError" in{
        invalidMessageJson.validate[Message] shouldBe a[JsError]
      }
    }
    "written to JSON" should{
      "return the expected JsObject" in{
        Json.toJson(err1) shouldBe validMessageJson
      }
    }
  }
}