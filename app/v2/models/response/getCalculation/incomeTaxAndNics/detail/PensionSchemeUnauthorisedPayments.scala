/*
 * Copyright 2021 HM Revenue & Customs
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

package v2.models.response.getCalculation.incomeTaxAndNics.detail

import play.api.libs.functional.syntax._
import utils.NestedJsonReads
import play.api.libs.json._

case class PensionSchemeUnauthorisedPayments(totalChargeableAmount: Option[BigDecimal],
                                             totalTaxPaid: Option[BigDecimal],
                                             pensionSchemeUnauthorisedPaymentsSurcharge: Option[PensionTypeBreakdown],
                                             pensionSchemeUnauthorisedPaymentsNonSurcharge: Option[PensionTypeBreakdown])


object PensionSchemeUnauthorisedPayments extends NestedJsonReads {

  implicit val writes: OWrites[PensionSchemeUnauthorisedPayments] = Json.writes[PensionSchemeUnauthorisedPayments]


  implicit val reads: Reads[PensionSchemeUnauthorisedPayments] = (
    (JsPath \ "totalChargeableAmount").readNullable[BigDecimal] and
      (JsPath \ "totalTaxPaid").readNullable[BigDecimal] and
      (JsPath \ "pensionSchemeUnauthorisedPaymentsSurcharge").readNestedNullable[PensionTypeBreakdown] and
      (JsPath \ "pensionSchemeUnauthorisedPaymentsNonSurcharge").readNestedNullable[PensionTypeBreakdown]
    ) (PensionSchemeUnauthorisedPayments.apply _)
}

