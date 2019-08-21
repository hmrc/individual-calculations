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

package v1.models.des.selfAssessment.componentObjects

import play.api.libs.json.{ JsSuccess, JsValue, Json }
import support.UnitSpec
import v1.models.des.selfAssessment.GetCalculationMessagesResponse

class MessagesSpec extends UnitSpec {

  val desJson: JsValue = Json.parse("""{
      |    "info" : [
      |               {"id" : "1","text" : "text"},
      |               {"id" : "2","text" : "text2"}
      |               ],
      |    "warnings" :[{"id" : "1","text" : "text"}],
      |    "errors" :[{"id" : "1","text" : "text"}]
      |}""".stripMargin)

  val infoObj     = new Info("1", "text")
  val infoObj2    = new Info("2", "text2")
  val warningsObj = new Warnings("1", "text")
  val errorsObj   = new Errors("1", "text")
  val messagesObj = new Messages(Some(Array(infoObj, infoObj2)), Some(Array(warningsObj)), Some(Array(errorsObj)))

  "Messages" when {
    "read from JSON" should {
      "return a JsSuccess" in {
        desJson.validate[Messages] shouldBe a[JsSuccess[Messages]]
      }

      val readMessages = desJson.as[Messages]

      "containing the expected info messages" in {
        readMessages.info.getOrElse(Array.empty).headOption shouldBe Some(messagesObj.info.get.head)
        readMessages.info.getOrElse(Array.empty).tail shouldBe messagesObj.info.get.tail
      }
      "containing the expected warnings message" in {
        readMessages.warnings.getOrElse(Array.empty).headOption shouldBe Some(messagesObj.warnings.get.head)
      }
      "containing the expected errors message" in {
        readMessages.errors.getOrElse(Array.empty).headOption shouldBe messagesObj.errors.get.headOption
      }
    }

    "written to JSON" should {
      "return the expected JsObject" in {
        Json.toJson(messagesObj) shouldBe desJson
      }
    }
  }
}
