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

import play.api.libs.json.{JsPath, JsValue, Json, OFormat, Reads}

object FilterTools extends NestedJsonReads {
  def readSequenceAndMapToType[A](path: JsPath, sourceType: String = "01")(implicit reads: Reads[A]): Reads[Option[Seq[A]]] = {
    path.readNestedNullable[Seq[JsValue]].map(optJsSeq => mapOptionalSequenceToType[A](optJsSeq, sourceType))
  }

  def mapOptionalSequenceToType[A](optJsSeq: Option[Seq[JsValue]], sourceType: String = "01")(implicit reads: Reads[A]): Option[Seq[A]] = {
    val mappedSequence = optJsSeq.getOrElse(Seq.empty).flatMap(js => filterByIncomeSourceType[A](js, sourceType))
    if (mappedSequence.isEmpty) None else Some(mappedSequence)
  }

  def filterByIncomeSourceType[A](js: JsValue, sourceType: String = "01")(implicit reads: Reads[A]): Option[A] = js.asOpt[FilterWrapper] match {
    case Some(FilterWrapper(incomeSourceType)) if incomeSourceType == sourceType => Some(js.as[A])
    case _ => None
  }

  case class FilterWrapper(incomeSourceType: String)

  object FilterWrapper {
    implicit val formats: OFormat[FilterWrapper] = Json.format[FilterWrapper]
  }

}
