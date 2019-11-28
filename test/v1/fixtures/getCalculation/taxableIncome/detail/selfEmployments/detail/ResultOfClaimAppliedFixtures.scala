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

package v1.fixtures.getCalculation.taxableIncome.detail.selfEmployments.detail

import play.api.libs.json.{JsValue, Json}
import v1.models.des.LossType
import v1.models.domain.TypeOfClaim
import v1.models.request.DesTaxYear
import v1.models.response.getCalculation.taxableIncome.detail.selfEmployment.detail.ResultOfClaimApplied

object ResultOfClaimAppliedFixtures {

  val claimId: Option[String]    = Some("CCIS12345678901")
  val originatingClaimId: String = "000000000000210"
  val incomeSourceId: String     = "AAIS12345678904"
  val incomeSourceType: String   = "01"
  val taxYearClaimMade: Int      = 2039
  val claimType: String          = "CF"
  val mtdLoss: Boolean           = true
  val taxYearLossIncurred: Int   = 2051
  val lossAmountUsed: Long       = 64613077921L
  val remainingLossValue: Long   = 72548288090L
  val lossType: String           = "income"
  val mtdLossType: String        = "income"

  val resultOfClaimAppliedResponse: ResultOfClaimApplied = ResultOfClaimApplied(
    claimId,
    DesTaxYear.fromDesIntToString(taxYearClaimMade),
    TypeOfClaim.`carry-forward`,
    mtdLoss,
    DesTaxYear.fromDesIntToString(taxYearLossIncurred),
    lossAmountUsed,
    remainingLossValue,
    LossType.INCOME,
    incomeSourceId
  )

  val resultOfClaimAppliedResponseWithoutMtdLoss: ResultOfClaimApplied = resultOfClaimAppliedResponse.copy(mtdLoss = true)

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
      |    "remainingLossValue": $remainingLossValue,
      |    "lossType": "$lossType"
      |}""".stripMargin)

  val resultOfClaimAppliedDesJsonWithWrongIncomeSourceType: JsValue = Json.parse(s"""{
      |    "claimId": "${claimId.get}",
      |    "originatingClaimId": "$originatingClaimId",
      |    "incomeSourceId": "$incomeSourceId",
      |    "incomeSourceType": "02",
      |    "taxYearClaimMade": $taxYearClaimMade,
      |    "claimType": "$claimType",
      |    "mtdLoss": $mtdLoss,
      |    "taxYearLossIncurred": $taxYearLossIncurred,
      |    "lossAmountUsed": $lossAmountUsed,
      |    "remainingLossValue": $remainingLossValue,
      |    "lossType": "$lossType"
      |}""".stripMargin)

  val resultOfClaimAppliedDesJsonWithoutMtdLoss: JsValue = Json.parse(s"""{
      |    "claimId": "${claimId.get}",
      |    "originatingClaimId": "$originatingClaimId",
      |    "incomeSourceId": "$incomeSourceId",
      |    "incomeSourceType": "$incomeSourceType",
      |    "taxYearClaimMade": $taxYearClaimMade,
      |    "claimType": "$claimType",
      |    "taxYearLossIncurred": $taxYearLossIncurred,
      |    "lossAmountUsed": $lossAmountUsed,
      |    "remainingLossValue": $remainingLossValue,
      |    "lossType": "$lossType"
      |}""".stripMargin)

  val resultOfClaimAppliedWrittenJson: JsValue = Json.parse(f"""{
      |    "claimId": "${claimId.get}",
      |    "taxYearClaimMade": "${DesTaxYear.fromDesIntToString(taxYearClaimMade)}",
      |    "claimType": "carry-forward",
      |    "mtdLoss": $mtdLoss,
      |    "taxYearLossIncurred": "${DesTaxYear.fromDesIntToString(taxYearLossIncurred)}",
      |    "lossAmountUsed": $lossAmountUsed,
      |    "remainingLossValue": $remainingLossValue,
      |    "lossType": "$mtdLossType"
      |}""".stripMargin)

  val resultOfClaimAppliedWrittenJsonWithoutMtdLoss: JsValue = Json.parse(f"""{
      |    "claimId": "${claimId.get}",
      |    "taxYearClaimMade": "${DesTaxYear.fromDesIntToString(taxYearClaimMade)}",
      |    "claimType": "carry-forward",
      |    "mtdLoss": true,
      |    "taxYearLossIncurred": "${DesTaxYear.fromDesIntToString(taxYearLossIncurred)}",
      |    "lossAmountUsed": $lossAmountUsed,
      |    "remainingLossValue": $remainingLossValue,
      |    "lossType": "$lossType"
      |}""".stripMargin)

  val resultOfClaimAppliedInvalidJson: JsValue = Json.parse(f"""{
      |    "lossType": "$lossType",
      |    "taxYearLossIncurred": "$taxYearLossIncurred",
      |    "mtdLoss": $mtdLoss
      |}""".stripMargin)

}
