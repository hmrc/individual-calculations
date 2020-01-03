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

package v1.fixtures.getCalculation.taxableIncome.detail.ukPropertyFhl

import play.api.libs.json.{JsValue, Json}
import v1.models.domain.TypeOfClaim
import v1.models.request.DesTaxYear
import v1.models.response.getCalculation.taxableIncome.detail.ukPropertyFhl.detail.ResultOfClaimApplied

object ResultOfClaimAppliedFixtures {

  val claimId: Option[String]    = Some("CCIS12345678901")
  val originatingClaimId: String = "000000000000210"
  val incomeSourceId: String     = "AAIS12345678904"
  val incomeSourceType: String   = "04"
  val taxYearClaimMade: Int      = 2020
  val claimType: String          = "CF"
  val mtdLoss: Boolean           = true
  val taxYearLossIncurred: Int   = 2019
  val lossAmountUsed: BigInt = 1000
  val remainingLossValue: BigInt   = 4000

  val resultOfClaimAppliedResponse: ResultOfClaimApplied = ResultOfClaimApplied(
    claimId,
    DesTaxYear.fromDesIntToString(taxYearClaimMade),
    TypeOfClaim.`carry-forward`,
    mtdLoss,
    DesTaxYear.fromDesIntToString(taxYearLossIncurred),
    lossAmountUsed,
    remainingLossValue
  )

  val resultOfClaimAppliedDesJson: JsValue = Json.parse(s"""{
      |    "claimId": "${claimId.get}",
      |    "originatingClaimId": "$originatingClaimId",
      |    "incomeSourceId": "$incomeSourceId",
      |    "incomeSourceType": "$incomeSourceType",
      |    "taxYearClaimMade": $taxYearClaimMade,
      |    "claimType": "$claimType",
      |    "mtdLoss": $mtdLoss,
      |    "taxYearLossIncurred": $taxYearLossIncurred,
      |    "lossAmountUsed": $lossAmountUsed,
      |    "remainingLossValue": $remainingLossValue
      |}""".stripMargin)

  val resultOfClaimAppliedWrittenJson: JsValue = Json.parse(f"""{
      |    "claimId": "${claimId.get}",
      |    "taxYearLossIncurred": "${DesTaxYear.fromDesIntToString(taxYearLossIncurred)}",
      |    "taxYearClaimMade": "${DesTaxYear.fromDesIntToString(taxYearClaimMade)}",
      |    "claimType": "carry-forward",
      |    "mtdLoss": $mtdLoss,
      |    "lossAmountUsed": $lossAmountUsed,
      |    "remainingLossValue": $remainingLossValue
      |}""".stripMargin)

  val resultOfClaimAppliedInvalidJson: JsValue = Json.parse(f"""{
      |    "taxYearLossIncurred": "$taxYearLossIncurred",
      |    "mtdLoss": $mtdLoss
      |}""".stripMargin)

  val desJsonWithInvalidSourceType: JsValue =
    Json.parse(s"""{
                   |    "claimId": "${claimId.get}",
                   |    "originatingClaimId": "$originatingClaimId",
                   |    "incomeSourceId": "$incomeSourceId",
                   |    "incomeSourceType": "01",
                   |    "taxYearClaimMade": $taxYearClaimMade,
                   |    "claimType": "$claimType",
                   |    "mtdLoss": $mtdLoss,
                   |    "taxYearLossIncurred": $taxYearLossIncurred,
                   |    "lossAmountUsed": $lossAmountUsed,
                   |    "remainingLossValue": $remainingLossValue
                   |}""".stripMargin)

}
