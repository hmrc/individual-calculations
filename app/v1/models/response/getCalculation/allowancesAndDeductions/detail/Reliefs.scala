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

package v1.models.response.getCalculation.allowancesAndDeductions.detail

import play.api.libs.json.{JsPath, Json, OWrites, Reads}
import utils.NestedJsonReads
import play.api.libs.functional.syntax._

case class Reliefs (
                     residentialFinanceCosts: Option[ResidentialFinanceCosts],
                     foreignTaxCreditRelief: Option[Seq[ForeignTaxCreditRelief]],
                     pensionContributionReliefs: Option[PensionContributionReliefs],
                     reliefsClaimed: Option[Seq[ReliefsClaimed]]
                   )

object Reliefs extends NestedJsonReads{
  val empty = Reliefs(None, None, None, None)

  implicit val writes: OWrites[Reliefs] = Json.writes[Reliefs]

  implicit val reads: Reads[Reliefs] = (
    (JsPath \ "calculation" \ "reliefs" \ "residentialFinanceCosts").readNestedNullable[ResidentialFinanceCosts] and
      (JsPath \ "calculation" \ "reliefs" \ "foreignTaxCreditRelief").readNestedNullable[Seq[ForeignTaxCreditRelief]].map {
        case Some(ForeignTaxCreditRelief.empty) => None
        case other => other
      } and
      (JsPath \ "calculation" \ "pensionContributionReliefs").readNestedNullable[PensionContributionReliefs].map {
        case Some(PensionContributionReliefs.empty) => None
        case other => other
      } and
      (JsPath \ "calculation" \ "reliefs" \ "reliefsClaimed").readNestedNullable[Seq[ReliefsClaimed]].map {
        case Some(ReliefsClaimed.empty) => None
        case other => other
      }
    )(Reliefs.apply _)
}
