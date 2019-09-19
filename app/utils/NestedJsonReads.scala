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

import play.api.Logger
import play.api.libs.json._

import scala.annotation.tailrec
import scala.concurrent.Future

trait NestedJsonReads {

  type ServiceResponse[T, E] = Future[Either[T, E]]

  implicit class JsPathOps(jsPath: JsPath) {
    def readNestedNullable[T](implicit rds: Reads[T]): Reads[Option[T]] = Reads[Option[T]] { json =>
      applyTillLastNested(json).fold(
        jsErr => jsErr,
        jsRes => jsRes.fold(
          invalid = _ => JsSuccess(None),
          valid = {
            case JsNull => JsSuccess(None)
            case js => rds.reads(js).repath(jsPath).map(Some(_))
          }
        )
      )
    }

    def readNestedNullableOpt[T](implicit rds: Reads[Option[T]]): Reads[Option[T]] = Reads[Option[T]] { json =>
      applyTillLastNested(json).fold(
        jsErr => jsErr,
        jsRes => jsRes.fold(
          invalid = _ => JsSuccess(None),
          valid = {
            case JsNull => JsSuccess(None)
            case js => rds.reads(js).repath(jsPath)
          }
        )
      )
    }

    def applyTillLastNested(json: JsValue): Either[JsError, JsResult[JsValue]] = {
      def singleJsError(msg: String) = JsError(Seq(jsPath -> Seq(JsonValidationError(msg))))
      @tailrec
      def step(pathNodes: List[PathNode], json: JsValue): Either[JsError, JsResult[JsValue]] = pathNodes match {
        case Nil => Left(singleJsError("error.path.empty"))
        case node :: Nil => node(json) match {
          case Nil => Right(singleJsError("error.path.missing"))
          case js :: Nil => Right(JsSuccess(js))
          case _ :: _ => Right(singleJsError("error.path.result.multiple"))
        }
        case head :: tail => head(json) match {
          case Nil => Right(singleJsError("error.path.missing"))
          case js :: Nil => step(tail, js)
          case _ :: _ => Left(singleJsError("error.path.result.multiple"))
        }
      }

      step(jsPath.path, json)
    }
  }

  def filteredArrayReads[T](filterName: String, matching: String)(implicit rds: Reads[Seq[T]]): Reads[Seq[T]] = new Reads[Seq[T]] {
    override def reads(json: JsValue): JsResult[Seq[T]] = {
      json.validate[Seq[JsValue]].flatMap(readJson => Json.toJson(readJson.filter { element =>
        element.\(filterName).asOpt[String].contains(matching)
      }).validate[Seq[T]])
    }
  }

  def filteredArrayValueReads[T](fieldName: Option[String],
                                 filterName: String, matching: String)(implicit rds: Reads[T]): Reads[Option[T]] = new Reads[Option[T]] {
    override def reads(json: JsValue): JsResult[Option[T]] = {
      json.validate[Seq[JsValue]].flatMap(readJson => Json.toJson(readJson.find { element =>
        element.\(filterName).asOpt[String].contains(matching)
      }.map { jsValue =>
        fieldName match {
          case Some(name) => jsValue.\(name).getOrElse(Json.obj())
          case None => jsValue
        }
      }).validateOpt[T])
    }
  }

  def emptySeqToNone[T](seq: Option[Seq[T]]): Option[Seq[T]] = {
    seq match {
      case Some(Nil) => None
      case _ => seq
    }
  }
}

object NestedJsonReads extends NestedJsonReads
