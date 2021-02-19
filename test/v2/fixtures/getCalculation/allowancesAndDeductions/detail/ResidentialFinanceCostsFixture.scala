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
import v2.models.response.getCalculation.allowancesAndDeductions.detail.ResidentialFinanceCosts
import v2.fixtures.getCalculation.allowancesAndDeductions.detail.UkPropertyFixture._
import v2.fixtures.getCalculation.allowancesAndDeductions.detail.ForeignPropertyFixture._
import v2.fixtures.getCalculation.allowancesAndDeductions.detail.AllOtherIncomeReceivedWhilstAbroadFixture._

object ResidentialFinanceCostsFixture {

  val adjustedTotalIncome: BigDecimal = 49820.34
  val totalAllowableAmount: Option[BigDecimal] = Some(49821.34)
  val relievableAmount: BigDecimal = 49822.34
  val rate: BigDecimal = 45.34
  val totalResidentialFinanceCostsRelief: BigDecimal = 49823.34

  val residentialFinanceCostsModel: ResidentialFinanceCosts =
    ResidentialFinanceCosts(
      adjustedTotalIncome = adjustedTotalIncome,
      totalAllowableAmount = totalAllowableAmount,
      relievableAmount = relievableAmount,
      rate = rate,
      totalResidentialFinanceCostsRelief = totalResidentialFinanceCostsRelief,
      ukProperty = Some(ukPropertyModel),
      foreignProperty = Some(foreignPropertyModel),
      allOtherIncomeReceivedWhilstAbroad = Some(allOtherIncomeReceivedWhilstAbroadModel)
    )

  val residentialFinanceCostsJson: JsValue = Json.parse(
    s"""
      |{
      |  "adjustedTotalIncome": $adjustedTotalIncome,
      |  "totalAllowableAmount": ${totalAllowableAmount.get},
      |  "relievableAmount": $relievableAmount,
      |  "rate": $rate,
      |  "totalResidentialFinanceCostsRelief": $totalResidentialFinanceCostsRelief,
      |  "ukProperty": $ukPropertyJson,
      |  "foreignProperty": $foreignPropertyJson,
      |  "allOtherIncomeReceivedWhilstAbroad": $allOtherIncomeReceivedWhilstAbroadJson
      |}
    """.stripMargin
  )
}
