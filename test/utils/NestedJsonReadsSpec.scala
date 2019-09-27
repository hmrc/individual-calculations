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

package utils

import play.api.libs.functional.syntax._
import play.api.libs.json._
import support.UnitSpec

class NestedJsonReadsSpec extends UnitSpec {

  case class Foo(foo1: String, foo2: String)

  object Foo {
    implicit val reads: Reads[Foo] =
      ((__ \ "baz" \ "foo" \ "foo1").read[String] and
        (__ \ "foo2").read[String])(Foo.apply _)
  }

  case class Bar(p1: String, foo: Option[Foo])

  object Bar extends NestedJsonReads {
    implicit val reads: Reads[Bar] =
      ((__ \ "b1").read[String] and
        emptyIfNotPresent[Foo](__ \ "baz" \ "foo"))(Bar.apply _)

  }

  "emptyIfNotPresent" when {
    "required path not present" must {
      "read as None" in {
        Json.parse("""
            |{
            |  "b1": "B1",
            |  "foo2": "F2"
            |}
            |""".stripMargin).as[Bar] shouldBe Bar("B1", None)
      }
    }

    "part of required path not present" must {
      "read as None" in {
        Json.parse("""
            |{
            |  "b1": "B1",
            |  "baz": {
            |  },
            |  "foo2": "F2"
            |}
            |""".stripMargin).as[Bar] shouldBe Bar("B1", None)
      }
    }

    "no fields of the optional object are present" must {
      "read as None" in {
        Json.parse("""
                     |{
                     |  "b1": "B1"
                     |}
                     |""".stripMargin).as[Bar] shouldBe Bar("B1", None)
      }
    }

    "required path is present" when {
      "the schema is correct for the target object" must {
        "read it" in {
          Json.parse("""
                       |{
                       |  "b1": "B1",
                       |  "baz": {
                       |    "foo": {
                       |      "foo1": "F1"
                       |    }
                       |  },
                       |  "foo2": "F2"
                       |}
                       |""".stripMargin).as[Bar] shouldBe Bar("B1", Some(Foo("F1", "F2")))
        }
      }

      "the schema is incorrect for the target object under the parent" must {
        "validate with an error" in {
          // Invalid because required field missing...
          Json.parse("""
                       |{
                       |  "b1": "B1",
                       |  "baz": {
                       |    "foo": {
                       |    }
                       |  },
                       |  "foo2": "F2"
                       |}
                       |""".stripMargin).validate[Bar] shouldBe a[JsError]
        }
      }

      "the schema is incorrect for the target object under a field not under the parent" must {
        "validate with an error" in {
          // Invalid because required field missing...
          Json.parse("""
                       |{
                       |  "b1": "B1",
                       |  "baz": {
                       |    "foo": {
                       |      "foo1": "F1"
                       |    }
                       |  }
                       |}
                       |""".stripMargin).validate[Bar] shouldBe a[JsError]
        }
      }
    }
  }
}
