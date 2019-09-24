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
import v1.models.domain.TypeOfClaim
import v1.models.request.DesTaxYear
import v1.models.response.getCalculation.taxableIncome.detail.selfEmployment.detail.ClaimNotApplied

object ClaimNotAppliedFixtures {

  val claimId                  = "CCIS12345678912"
  val incomeSourceId: String   = "AAIS12345678904"
  val incomeSourceType: String = "01"
  val taxYearClaimMade         = 2046
  val claimType                = "CF"

  val claimNotAppliedResponse: ClaimNotApplied = ClaimNotApplied(
    claimId,
    DesTaxYear.fromDesIntToString(taxYearClaimMade),
    claimType = TypeOfClaim.`carry-forward`,
    incomeSourceId
  )

  val claimNotAppliedDesJson: JsValue = Json.parse(s"""{
      |  "claimId": "$claimId",
      |  "incomeSourceId": "$incomeSourceId",
      |  "incomeSourceType": "$incomeSourceType",
      |  "taxYearClaimMade": $taxYearClaimMade,
      |  "claimType": "$claimType"
      |}""".stripMargin)

  val claimNotAppliedDesJsonWithWrongIncomeSourceType: JsValue = Json.parse(s"""{
      |  "claimId": "$claimId",
      |  "incomeSourceId": "$incomeSourceId",
      |  "incomeSourceType": "02",
      |  "taxYearClaimMade": $taxYearClaimMade,
      |  "claimType": "$claimType"
      |}""".stripMargin)

  val claimNotAppliedWrittenJson: JsValue = Json.parse(s"""{
      |  "claimId": "$claimId",
      |  "taxYearClaimMade": "${DesTaxYear.fromDesIntToString(taxYearClaimMade)}",
      |  "claimType": "carry-forward"
      |}""".stripMargin)

  val claimNotAppliedInvalidJson: JsValue = Json.parse(s"""{
      |  "claimId": "$claimId",
      |  "incomeSourceId": "$incomeSourceId",
      |  "incomeSourceType": "$incomeSourceType",
      |  "taxYearClaimMade": $taxYearClaimMade
      |}""".stripMargin)

}
