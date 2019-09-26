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

package v1.fixtures.taxableIncome.detail.selfEmployments.detail

import play.api.libs.json.{JsValue, Json}
import v1.models.des.LossType
import v1.models.domain.{TypeOfClaim, TypeOfLoss}
import v1.models.request.DesTaxYear
import v1.models.response.getCalculation.taxableIncome.detail.selfEmployment.detail.CarriedForwardLoss

object CarriedForwardLossFixtures {

  val claimId: Option[String]       = Some("CCIS12345678911")
  val originatingClaimId: String    = "OCIS12345678901"
  val incomeSourceId: String        = "AAIS12345678904"
  val incomeSourceType: String      = "01"
  val claimType: String             = "CF"
  val taxYearClaimMade: Option[Int] = Some(2047)
  val taxYearLossIncurred: Int      = 2045
  val currentLossValue: BigInt      = 49177438626L
  val lossType: String              = "income"
  val typeOfLoss = TypeOfLoss.`self-employment`

  val carriedForwardLossResponse: CarriedForwardLoss = CarriedForwardLoss(
    claimId,
    TypeOfClaim.`carry-forward`,
    taxYearClaimMade.map(taxYear => DesTaxYear.fromDesIntToString(taxYear)),
    DesTaxYear.fromDesIntToString(taxYearLossIncurred),
    currentLossValue,
    typeOfLoss,
    incomeSourceId
  )

  val carriedForwardLossResponseWithoutOptionals: CarriedForwardLoss = carriedForwardLossResponse.copy(claimId = None, taxYearClaimMade = None)

  val carriedForwardLossDesJson: JsValue = Json.parse(f"""{
      |    "claimId": "${claimId.get}",
      |    "originatingClaimId": "$originatingClaimId",
      |    "incomeSourceId": "$incomeSourceId",
      |    "incomeSourceType": "$incomeSourceType",
      |    "claimType": "$claimType",
      |    "taxYearClaimMade": ${taxYearClaimMade.get},
      |    "taxYearLossIncurred": $taxYearLossIncurred,
      |    "currentLossValue": $currentLossValue,
      |    "lossType": "$lossType"
      |}""".stripMargin)

  val carriedForwardLossDesJsonWithWrongIncomeSourceType: JsValue = Json.parse(f"""{
      |    "claimId": "${claimId.get}",
      |    "originatingClaimId": "$originatingClaimId",
      |    "incomeSourceId": "$incomeSourceId",
      |    "incomeSourceType": "02",
      |    "claimType": "$claimType",
      |    "taxYearClaimMade": ${taxYearClaimMade.get},
      |    "taxYearLossIncurred": $taxYearLossIncurred,
      |    "currentLossValue": $currentLossValue,
      |    "lossType": "$lossType"
      |}""".stripMargin)

  val carriedForwardLossDesJsonWithoutOptionals: JsValue = Json.parse(f"""{
      |    "originatingClaimId": "$originatingClaimId",
      |    "incomeSourceId": "$incomeSourceId",
      |    "incomeSourceType": "$incomeSourceType",
      |    "claimType": "$claimType",
      |    "taxYearLossIncurred": $taxYearLossIncurred,
      |    "currentLossValue": $currentLossValue,
      |    "lossType": "$lossType"
      |}""".stripMargin)

  val carriedForwardLossWrittenJson: JsValue = Json.parse(f"""{
      |    "claimId": "${claimId.get}",
      |    "claimType": "carry-forward",
      |    "taxYearClaimMade": "${DesTaxYear.fromDesIntToString(taxYearClaimMade.get)}",
      |    "taxYearLossIncurred": "${DesTaxYear.fromDesIntToString(taxYearLossIncurred)}",
      |    "currentLossValue": $currentLossValue,
      |    "lossType": "$typeOfLoss"
      |}""".stripMargin)

  val carriedForwardLossWrittenJsonWithoutOptionals: JsValue = Json.parse(f"""{
      |    "claimType": "carry-forward",
      |    "taxYearLossIncurred": "${DesTaxYear.fromDesIntToString(taxYearLossIncurred)}",
      |    "currentLossValue": $currentLossValue,
      |    "lossType": "$typeOfLoss"
      |}""".stripMargin)

  val carriedForwardLossInvalidJson: JsValue = Json.parse(f"""{
      |    "claimId": "${claimId.get}",
      |    "originatingClaimId": "$originatingClaimId",
      |    "incomeSourceId": "$incomeSourceId",
      |    "incomeSourceType": "$incomeSourceType",
      |    "taxYearClaimMade": ${taxYearClaimMade.get},
      |    "taxYearLossIncurred": $taxYearLossIncurred,
      |    "currentLossValue": $currentLossValue,
      |    "lossType": "$lossType"
      |}""".stripMargin)

}
