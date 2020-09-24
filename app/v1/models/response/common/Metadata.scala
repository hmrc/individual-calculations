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
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package v1.models.response.common

import play.api.libs.functional.syntax._
import play.api.libs.json._
import sangria.macros.derive._
import sangria.schema._
import utils.NestedJsonReads
import v1.models.domain.{CalculationReason, CalculationRequestor, CalculationType}
import v1.models.request.DesTaxYear
case class Metadata(id: String,
                    taxYear: String,
                    requestedBy: CalculationRequestor,
                    requestedTimestamp: Option[String],
                    calculationReason: CalculationReason,
                    calculationTimestamp: Option[String],
                    calculationType: CalculationType,
                    intentToCrystallise: Boolean,
                    crystallised: Boolean,
                    totalIncomeTaxAndNicsDue: Option[BigDecimal],
                    calculationErrorCount: Option[Int])


object Metadata extends NestedJsonReads {
  implicit val writes: OWrites[Metadata] = Json.writes[Metadata]
  implicit val reads: Reads[Metadata] = (
    (JsPath \ "metadata" \ "calculationId").read[String] and
      (JsPath \ "metadata" \ "taxYear").read[Int].map(DesTaxYear.fromDesIntToString) and
      (JsPath \ "metadata" \ "requestedBy").read[CalculationRequestor] and
      (JsPath \ "metadata" \ "requestedTimestamp").readNullable[String] and
      (JsPath \ "metadata" \ "calculationReason").read[CalculationReason] and
      (JsPath \ "metadata" \ "calculationTimestamp").readNullable[String] and
      (JsPath \ "metadata" \ "calculationType").read[CalculationType] and
      (JsPath \ "metadata" \ "intentToCrystallise").readWithDefault[Boolean](false) and
      (JsPath \ "metadata" \ "crystallised").readWithDefault[Boolean](false) and
      (JsPath \ "calculation" \ "taxCalculation" \ "totalIncomeTaxAndNicsDue").readNestedNullable[BigDecimal] and
      (__ \ "messages" \ "errors").readNestedNullable[Seq[Message]].map {
        case Some(errs) if errs.nonEmpty => Some(errs.length)
        case _                           => None
      }
    ) (Metadata.apply _)



  implicit def gqlType =
    deriveObjectType[Unit, Metadata](
      ReplaceField("requestedBy", Field("requestedBy", StringType, resolve = _.value.requestedBy.toString)),
      ReplaceField("calculationReason", Field("calculationReason", StringType, resolve = _.value.calculationReason.toString)),
      ReplaceField("calculationType", Field("calculationType", StringType, resolve = _.value.calculationType.toString))
    )
}
