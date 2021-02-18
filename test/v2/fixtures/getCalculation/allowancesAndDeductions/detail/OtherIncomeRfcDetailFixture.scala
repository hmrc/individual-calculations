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

package v2.fixtures.getCalculation.allowancesAndDeductions.detail

import play.api.libs.json.{JsValue, Json}
import v2.models.response.getCalculation.allowancesAndDeductions.detail.OtherIncomeRfcDetail

object OtherIncomeRfcDetailFixture {

  val countryCode: String = "IND"
  val residentialFinancialCostAmount: Option[BigDecimal] = Some(49833.34)
  val broughtFwdResidentialFinancialCostAmount: Option[BigDecimal] = Some(49832.34)

  val otherIncomeRfcDetailModel: OtherIncomeRfcDetail =
    OtherIncomeRfcDetail(
      countryCode = countryCode,
      residentialFinancialCostAmount = residentialFinancialCostAmount,
      broughtFwdResidentialFinancialCostAmount = broughtFwdResidentialFinancialCostAmount
    )

  val otherIncomeRfcDetailJson: JsValue = Json.parse(
    s"""
      |{
      |  "countryCode": "$countryCode",
      |  "residentialFinancialCostAmount": ${residentialFinancialCostAmount.get},
      |  "broughtFwdResidentialFinancialCostAmount": ${broughtFwdResidentialFinancialCostAmount.get}
      |}
    """.stripMargin
  )
}
