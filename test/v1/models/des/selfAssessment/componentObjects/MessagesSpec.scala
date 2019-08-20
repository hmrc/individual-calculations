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

import play.api.libs.json.{JsSuccess, JsValue, Json}
import support.UnitSpec

class MessagesSpec extends UnitSpec {

  val desJson: JsValue = Json.parse("""{
      |    "info" : [{"id" : "1","text" : "text"}],
      |    "warnings" :[{"id" : "1","text" : "text"}],
      |    "errors" :[{"id" : "1","text" : "text"}]
      |}""".stripMargin)

  val infoObj: Info = new Info("1", "text")
  val warningsObj: Warnings = new Warnings("1", "text")
  val errorObj: Errors = new Errors("1", "text")
  val messagesObj: Messages = new Messages(Some(Array(infoObj)),Some(Array(warningsObj)),Some(Array(errorObj)))

  "Messages" when {
    "read from JSON" should {
      "return a JsSuccess" in {
        desJson.validate[Messages] shouldBe a[JsSuccess[Messages]]
      }
      "containing an info message" in {
        desJson.as[Messages].info.get.head shouldBe messagesObj.info.get.head
      }
      "containing a warnings message" in {
        desJson.as[Messages].warnings.get.head shouldBe messagesObj.warnings.get.head
      }
      "containing an errors message" in {
        desJson.as[Messages].errors.get.head shouldBe messagesObj.errors.get.head
      }
    }

    "written to JSON" should {
      "return a JsObject" in {
        Json.toJson(messagesObj) shouldBe desJson
      }
    }
  }
}
