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
import v2.fixtures.getCalculation.incomeTaxAndNics.detail.PensionTypeBreakdownFixtures.pensionTypeBreakdownModel
import v2.models.response.getCalculation.incomeTaxAndNics.detail.PensionSchemeUnauthorisedPayments


object PensionSchemeUnauthorisedPaymentsFixtures {
  val pensionSchemeUnauthorisedPaymentsJson: JsValue = Json.parse(
    """
      |{
      |	  "totalChargeableAmount": 100,
      |	  "totalTaxPaid": 50.25,
      |	  "pensionSchemeUnauthorisedPaymentsNonSurcharge": {
      |	  	"amount": 120.50,
      |	  	"chargeableAmount": 160.50,
      |	  	"rate": 10.40,
      |	  	"taxPaid": 160.50
      |	  },
      |	  "pensionSchemeUnauthorisedPaymentsSurcharge": {
      |	  	"amount": 120.50,
      |	  	"chargeableAmount": 160.50,
      |	  	"rate": 10.40,
      |	  	"taxPaid": 160.50
      |	  }
      |}
    """.stripMargin)

  val pensionSchemeUnauthorisedPaymentsModel =
    PensionSchemeUnauthorisedPayments(Some(100), Some(50.25), Some(pensionTypeBreakdownModel), Some(pensionTypeBreakdownModel))

}
