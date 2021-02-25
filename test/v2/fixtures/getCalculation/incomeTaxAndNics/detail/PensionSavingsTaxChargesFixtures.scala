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
import v2.models.response.getCalculation.incomeTaxAndNics.detail.{PensionSavingsTaxCharges, PensionSavingsTaxChargesDetail}

object PensionSavingsTaxChargesFixtures {

  val pensionSavingsTaxChargesJson: JsValue = Json.parse(
    """
      |{
      |  "totalPensionCharges": 3000.10,
      |  "totalTaxPaid": 4000.25,
      |  "totalPensionChargesDue": 5000.70,
      |  "pensionSavingsTaxChargesDetail": {}
      |}
    """.stripMargin
  )

  val pensionSavingsTaxChargesModel: PensionSavingsTaxCharges = PensionSavingsTaxCharges(
    totalPensionCharges = Some(3000.10),
    totalTaxPaid = Some(4000.25),
    totalPensionChargesDue = Some(5000.70),
    pensionSavingsTaxChargesDetail = Some(PensionSavingsTaxChargesDetail(
      excessOfLifetimeAllowance = None,
      pensionSchemeUnauthorisedPayments = None,
      pensionSchemeOverseasTransfers = None,
      pensionContributionsInExcessOfTheAnnualAllowance = None,
      overseasPensionContributions = None
    ))
  )
}