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

package v2.fixtures.getCalculation.incomeTaxAndNics.detail

import play.api.libs.json.{JsValue, Json}
import v2.fixtures.getCalculation.incomeTaxAndNics.detail.PensionTypeBreakdownFixtures._
import v2.models.response.getCalculation.incomeTaxAndNics.detail.PensionSchemeUnauthorisedPayments

object PensionSchemeUnauthorisedPaymentsFixtures {

  val pensionSchemeUnauthorisedPaymentsJson: JsValue = Json.parse(
    s"""
       |{
       |  "totalChargeableAmount": 150.99,
       |  "totalTaxPaid": 250.25,
       |  "pensionSchemeUnauthorisedPaymentsSurcharge": $pensionTypeBreakdownJson,
       |  "pensionSchemeUnauthorisedPaymentsNonSurcharge": $pensionTypeBreakdownJson
       |}
    """.stripMargin
  )

  val pensionSchemeUnauthorisedPaymentsModel: PensionSchemeUnauthorisedPayments = PensionSchemeUnauthorisedPayments(
    totalChargeableAmount = Some(150.99),
    totalTaxPaid = Some(250.25),
    pensionSchemeUnauthorisedPaymentsSurcharge = Some(pensionTypeBreakdownModel),
    pensionSchemeUnauthorisedPaymentsNonSurcharge = Some(pensionTypeBreakdownModel)
  )
}