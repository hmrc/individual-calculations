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

package v1.models.response.selfAssessment

import play.api.libs.functional.syntax._
import play.api.libs.json._
import utils.NestedJsonReads
import v1.models.domain.selfAssessment.{CalculationReason, CalculationRequestor, CalculationType}
import v1.models.request.DesTaxYear

case class Metadata(id: String,
                    taxYear: String,
                    requestedBy: CalculationRequestor,
                    requestedTimestamp: Option[String],
                    calculationReason: CalculationReason,
                    calculationTimestamp: String,
                    calculationType: CalculationType,
                    intentToCrystallise: Boolean,
                    crystallised: Boolean,
                    calculationErrorCount: Option[Int])

case class Error(id: String, text:String)
object Error{
  implicit val format: OFormat[Error] = Json.format[Error]
}

object Metadata extends NestedJsonReads {
  implicit val writes: Writes[Metadata] = Json.writes[Metadata]
  implicit val reads: Reads[Metadata]=(
  (JsPath \"metadata" \ "calculationId").read[String] and
    (JsPath \"metadata" \ "taxYear").read[Int].map(taxYear => taxYear.toString).map(DesTaxYear.fromDes).map(_.toString) and
    (JsPath \"metadata" \ "requestedBy").read[CalculationRequestor] and
    (JsPath \"metadata" \ "requestedTimestamp").readNullable[String] and
    (JsPath \"metadata" \ "calculationReason").read[CalculationReason] and
    (JsPath \"metadata" \ "calculationTimestamp").read[String] and
    (JsPath \"metadata" \ "calculationType").read[CalculationType] and
    (JsPath \"metadata" \ "intentToCrystallise").readWithDefault[Boolean](false) and
    (JsPath \"metadata" \ "crystallised").readWithDefault[Boolean](false) and
    (__ \"messages" \ "errors").readNestedNullable[Seq[Error]].map {
      case Some(errs) if errs.nonEmpty => Some(errs.length)
      case _ => None
    }
  )(Metadata.apply _)
}
