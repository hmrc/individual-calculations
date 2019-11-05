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

class NestedJsonReadsSpec extends UnitSpec with NestedJsonReads {

  case class Foo(foo1: String, foo2: String)

  object Foo {
    implicit val reads: Reads[Foo] =
      ((__ \ "baz" \ "foo" \ "foo1").read[String] and
        (__ \ "foo2").read[String]) (Foo.apply _)
  }

  case class Bar(p1: String, foo: Option[Foo])

  object Bar extends NestedJsonReads {
    implicit val reads: Reads[Bar] =
      ((__ \ "b1").read[String] and
        emptyIfNotPresent[Foo](__ \ "baz" \ "foo")) (Bar.apply _)

  }

  case class MatchClass(id: String, name: NameClass)

  object MatchClass {
    implicit val formats: OFormat[MatchClass] = Json.format[MatchClass]
  }

  case class NameClass(name: String, firstName: String, lastName: String)

  object NameClass {
    implicit val formats: OFormat[NameClass] = Json.format[NameClass]
  }

  case class NestedDataClass(data: String, matchClass: Option[MatchClass])

  "emptyIfNotPresent" when {
    "required path not present" must {
      "read as None" in {
        Json.parse(
          """
            |{
            |  "b1": "B1",
            |  "foo2": "F2"
            |}
            |""".stripMargin).as[Bar] shouldBe Bar("B1", None)
      }
    }

    "part of required path not present" must {
      "read as None" in {
        Json.parse(
          """
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
        Json.parse(
          """
            |{
            |  "b1": "B1"
            |}
            |""".stripMargin).as[Bar] shouldBe Bar("B1", None)
      }
    }

    "required path is present" when {
      "the schema is correct for the target object" must {
        "read it" in {
          Json.parse(
            """
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
          Json.parse(
            """
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
          Json.parse(
            """
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

  "filteredArrayValueReads" should {
    val json = Json.parse(
      """
        |[
        |   {
        |     "id" : "1",
        |     "name" : {
        |       "name": "firstName",
        |       "firstName" : "first",
        |       "lastName": "name"
        |     }
        |   },
        |   {
        |     "id" : "2",
        |     "name" : {
        |       "name": "secondName",
        |       "firstName" : "second",
        |       "lastName": "name"
        |     }
        |   },
        |   {
        |     "id" : "3",
        |     "name" : {
        |       "name": "secondName",
        |       "firstName" : "second",
        |       "lastName": "name"
        |     }
        |   },
        |   {
        |     "id" : "4",
        |     "invalidName" : "fourthName"
        |   },
        |   {
        |     "invalidId" : 5,
        |     "name" : "fifthName"
        |   }
        |]
      """.stripMargin)

    "return a JsSuccess" which {

      "has all the data" when {

        "provided with a matching parameter without a field name" in {
          NestedJsonReads.filteredArrayValueReads[MatchClass](None, "id", "2")
            .reads(json).get shouldBe Some(MatchClass("2", NameClass("secondName", "second", "name")))
        }
      }

      "has a defined subset of the data" when {

        "provided with a matching parameter with a field name" in {
          NestedJsonReads.filteredArrayValueReads[NameClass](Some("name"), "id", "2")
            .reads(json).get shouldBe Some(NameClass("secondName", "second", "name"))
        }
      }

      "has no data" when {

        "provided with a non-matching parameter without a field name" in {
          NestedJsonReads.filteredArrayValueReads[MatchClass](None, "id", "6")
            .reads(json).get shouldBe None
        }

        "provided with a non-matching parameter with a field name" in {
          NestedJsonReads.filteredArrayValueReads[NameClass](Some("name"), "badId", "2")
            .reads(json).get shouldBe None
        }
      }
    }

    "return a JsError" when {

      "provided with invalid json with a matching parameter" in {
        val result = NestedJsonReads.filteredArrayValueReads[NameClass](None, "id", "4").reads(json)

        result shouldBe a[JsError]
      }

      "provided with a matching parameter but with an invalid field name" in {
        val result = NestedJsonReads.filteredArrayValueReads[NameClass](Some("invalidField"), "id", "2").reads(json)

        result shouldBe a[JsError]
      }
    }
  }

  "filteredArrayReads" should {
    val json = Json.parse(
      """
        |[
        |   {
        |     "id" : "1",
        |     "name" : {
        |       "name": "firstName",
        |       "firstName" : "first",
        |       "lastName": "name"
        |     }
        |   },
        |   {
        |     "id" : "1",
        |     "name" : {
        |       "name": "secondName",
        |       "firstName" : "second",
        |       "lastName": "name"
        |     }
        |   },
        |   {
        |     "id" : "3",
        |     "name" : {
        |       "name": "secondName",
        |       "firstName" : "second",
        |       "lastName": "name"
        |     }
        |   },
        |   {
        |     "id" : "4",
        |     "invalidName" : "fourthName"
        |   },
        |   {
        |     "invalidId" : 5,
        |     "name" : "fifthName"
        |   }
        |]
      """.stripMargin)

    "return a sequence of filtered values" when {

      "provided with a matching parameter for multiple values" in {
        NestedJsonReads.filteredArrayReads[MatchClass]("id", "1")
          .reads(json).get shouldBe Seq(
          MatchClass("1", NameClass("firstName", "first", "name")),
          MatchClass("1", NameClass("secondName", "second", "name"))
        )
      }

      "provided with a non-matching parameter value" in {
        NestedJsonReads.filteredArrayReads[MatchClass]("id", "2")
          .reads(json).get shouldBe Seq()
      }

      "provided with a non-matching parameter" in {
        NestedJsonReads.filteredArrayReads[MatchClass]("badId", "1")
          .reads(json).get shouldBe Seq()
      }
    }

    "return a JsError" when {

      "provided with a matching parameter for an invalid json object" in {
        val result = NestedJsonReads.filteredArrayReads[MatchClass]("id", "4").reads(json)

        result shouldBe a[JsError]
      }
    }
  }

  "applyTillLastNested" should {
    val json = Json.parse(
      """
        |{
        | "topLevel" : {
        |   "midLevel" : {
        |     "bottomLevel": {
        |       "data" : "data"
        |     }
        |   }
        | }
        |}
      """.stripMargin)

    val jsPath = JsPath(List(
      KeyPathNode("topLevel"),
      KeyPathNode("midLevel"),
      KeyPathNode("bottomLevel")
    ))

    "return the correct JsError" when {

      "provided with json with a missing topLevel" in {
        NestedJsonReads.JsPathOps(jsPath).applyTillLastNested(Json.obj()) shouldBe Right(JsError(Seq(jsPath -> Seq(JsonValidationError("error.path.missing")))))
      }

      "provided with json with a missing bottomLevel" in {
        val missingJson = Json.parse(
          """
            |{
            | "topLevel" : {
            |   "midLevel" : {
            |
            |   }
            | }
            |}
          """.stripMargin)

        NestedJsonReads.JsPathOps(jsPath).applyTillLastNested(missingJson) shouldBe
          Right(JsError(Seq(jsPath -> Seq(JsonValidationError("error.path.missing")))))
      }

      "provided with an empty JsPath" in {
        NestedJsonReads.JsPathOps(JsPath(List())).applyTillLastNested(json) shouldBe
          Left(JsError(Seq(JsPath(List()) -> Seq(JsonValidationError("error.path.empty")))))
      }
    }

    "return a JsSuccess" when {

      "provided with a path to valid data" in {
        NestedJsonReads.JsPathOps(jsPath).applyTillLastNested(json) shouldBe Right(JsSuccess(Json.parse("""{"data" : "data"}""")))
      }
    }
  }

  "readNestedNullableOpt" should {

    implicit val reads: Reads[NestedDataClass] = (
      (JsPath \ "data").read[String] and
        (JsPath \ "topLevel" \ "midLevel" \ "bottomLevel").readNestedNullableOpt[MatchClass](filteredArrayValueReads(None, "id", "1"))
      ) (NestedDataClass.apply _)

    "return a None" when {

      "provided with data missing" in {
        Json.parse("""{"data" : "data"}""").as[NestedDataClass] shouldBe NestedDataClass("data", None)
      }

      "provided with invalid data" in {
        val json = Json.parse(
          """
            |{
            | "data" : "data",
            | "topLevel" : {
            |   "midLevel" : {
            |     "bottomLevel": [
            |
            |     ]
            |   }
            | }
            |}
          """.stripMargin)

        json.as[NestedDataClass] shouldBe NestedDataClass("data", None)
      }
    }

    "return Some data" when {

      "provided with Json with valid data" in {
        val json = Json.parse(
          """
            |{
            | "data" : "data",
            | "topLevel" : {
            |   "midLevel" : {
            |     "bottomLevel": [
            |       {
            |         "id" : "1",
            |         "name" : {
            |           "name": "firstName",
            |           "firstName" : "first",
            |           "lastName": "name"
            |         }
            |       }
            |     ]
            |   }
            | }
            |}
          """.stripMargin)

        json.as[NestedDataClass] shouldBe NestedDataClass("data", Some(MatchClass("1", NameClass("firstName", "first", "name"))))
      }
    }

    "return a JsError" when {

      "provided with invalid data" in {
        val json = Json.parse(
          """
            |{
            | "data" : "data",
            | "topLevel" : {
            |   "midLevel" : {
            |     "bottomLevel": [
            |       {
            |         "id" : "1",
            |         "name" : {
            |           "firstName" : "first",
            |           "lastName": "name"
            |         }
            |       }
            |     ]
            |   }
            | }
            |}
          """.stripMargin)

        json.validate[NestedDataClass] shouldBe a[JsError]
      }
    }
  }

  "readNestedNullable" should {

    implicit val reads: Reads[NestedDataClass] = (
      (JsPath \ "data").read[String] and
        (JsPath \ "topLevel" \ "midLevel" \ "bottomLevel").readNestedNullable[MatchClass]
      ) (NestedDataClass.apply _)

    "return Some data" when {

      "provided with Json with valid data" in {
        val json = Json.parse(
          """
            |{
            | "data" : "data",
            | "topLevel" : {
            |   "midLevel" : {
            |     "bottomLevel":
            |       {
            |         "id" : "1",
            |         "name" : {
            |           "name": "firstName",
            |           "firstName" : "first",
            |           "lastName": "name"
            |         }
            |       }
            |   }
            | }
            |}
          """.stripMargin)

        json.as[NestedDataClass] shouldBe NestedDataClass("data", Some(MatchClass("1", NameClass("firstName", "first", "name"))))
      }
    }

    "return a None" when {

      "provided with json with a missing path component" in {
        val json = Json.parse(
          """
            |{
            | "data" : "data",
            | "topLevel" : {
            |   "midLevel" : {

            |   }
            | }
            |}
          """.stripMargin)

        json.as[NestedDataClass] shouldBe NestedDataClass("data", None)
      }
    }

    "return a JsError" when {

      "provided with invalid data" in {
        val json = Json.parse(
          """
            |{
            | "data" : "data",
            | "topLevel" : {
            |   "midLevel" : {
            |     "bottomLevel":
            |       {
            |         "id" : "1",
            |         "name" : {
            |           "firstName" : "first",
            |           "lastName": "name"
            |         }
            |       }
            |   }
            | }
            |}
          """.stripMargin)

        json.validate[NestedDataClass] shouldBe a[JsError]
      }
    }
  }

  "mapEmptySeqToNone" must {
    val reads = (__).readNullable[Seq[String]].mapEmptySeqToNone

    "map non-empty sequence to Some(non-empty sequence)" in {
      JsArray(Seq(JsString("value0"), JsString("value1"))).as(reads) shouldBe Some(Seq("value0", "value1"))
    }

    "map empty sequence to None" in {
      JsArray.empty.as(reads) shouldBe None
    }

    "map None to None" in {
      JsNull.as(reads) shouldBe None
    }
  }

  "mapHeadOption" must {
    val reads = (__).readNullable[Seq[String]].mapHeadOption

    "map non-empty sequence to Some(1st element)" in {
      JsArray(Seq(JsString("value0"), JsString("value1"))).as(reads) shouldBe Some("value0")
    }

    "map empty sequence to None" in {
      JsArray.empty.as(reads) shouldBe None
    }

    "map None to None" in {
      JsNull.as(reads) shouldBe None
    }
  }
}
