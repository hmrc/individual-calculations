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
import v2.fixtures.getCalculation.incomeTaxAndNics.detail.OverseasPensionContributionsFixtures._
import v2.fixtures.getCalculation.incomeTaxAndNics.detail.PensionContributionsInExcessOfTheAnnualAllowanceFixtures._
import v2.fixtures.getCalculation.incomeTaxAndNics.detail.PensionSchemeOverseasTransfersFixtures._
import v2.fixtures.getCalculation.incomeTaxAndNics.detail.PensionTypeBreakdownFixtures._
import v2.models.response.getCalculation.incomeTaxAndNics.detail._

object PensionSavingsTaxChargesDetailFixtures {

  val pensionSavingsTaxChargesDetailJson: JsValue = Json.parse(
    """
      |{
      |   "lumpSumBenefitTakenInExcessOfLifetimeAllowance":{
      |      "amount":120.50,
      |      "taxPaid":160.50,
      |      "rate":10.40,
      |      "chargeableAmount":160.50
      |   },
      |   "benefitInExcessOfLifetimeAllowance":{
      |      "amount":120.50,
      |      "taxPaid":160.50,
      |      "rate":10.40,
      |      "chargeableAmount":160.50
      |   },
      |   "pensionSchemeUnauthorisedPaymentsSurcharge":{
      |      "amount":120.50,
      |      "taxPaid":160.50,
      |      "rate":10.40,
      |      "chargeableAmount":160.50
      |   },
      |   "pensionSchemeUnauthorisedPaymentsNonSurcharge":{
      |      "amount":120.50,
      |      "taxPaid":160.50,
      |      "rate":10.40,
      |      "chargeableAmount":160.50
      |   },
      |   "pensionSchemeOverseasTransfers":{
      |      "transferCharge":120.25,
      |      "transferChargeTaxPaid":130.25,
      |      "rate":60.25,
      |      "chargeableAmount":140.25
      |   },
      |   "pensionContributionsInExcessOfTheAnnualAllowance":{
      |      "totalContributions":70.25,
      |   "totalPensionCharge":160.50,
      |   "annualAllowanceTaxPaid":180.25,
      |   "totalPensionChargeDue":120.99,
      |   "pensionBands":[
      |      {
      |         "name":"Name",
      |         "rate":50.10,
      |         "bandLimit":2000,
      |         "apportionedBandLimit":2000,
      |         "contributionAmount":160.89,
      |         "pensionCharge":180.99
      |      }
      |   ]
      |   },
      |   "overseasPensionContributions":{
      |      "totalShortServiceRefund":100.50,
      |      "totalShortServiceRefundCharge":200.50,
      |      "shortServiceRefundTaxPaid":160.25,
      |      "totalShortServiceRefundChargeDue":160.99,
      |      "shortServiceRefundBands":[
      |         {
      |            "name":"name",
      |            "rate":20.10,
      |            "bandLimit":2000,
      |            "apportionedBandLimit":2000,
      |            "shortServiceRefundAmount":500.50,
      |            "shortServiceRefundCharge":750.99
      |         }
      |      ]
      |   }
      |}
    """.stripMargin)

  val pensionSavingsTaxChargesDetailModel =
    PensionSavingsTaxChargesDetail(
      Some(pensionTypeBreakdownModel),
      Some(pensionTypeBreakdownModel),
      Some(pensionTypeBreakdownModel),
      Some(pensionTypeBreakdownModel),
      Some(pensionSchemeOverseasTransfersModel),
      Some(pensionContributionsInExcessOfTheAnnualAllowanceModel),
      Some(overseasPensionContributionsModel)
    )
}
