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
import v2.models.response.getCalculation.allowancesAndDeductions.detail.ForeignTaxCreditRelief

object ForeignTaxCreditReliefFixture {

  val customerCalculatedRelief =  true
  val totalForeignTaxCreditRelief: BigDecimal = 8019.25
  val foreignTaxCreditReliefOnProperty: Option[BigDecimal] = Some(8020.25)
  val foreignTaxCreditReliefOnDividends: Option[BigDecimal] = Some(8021.25)
  val foreignTaxCreditReliefOnSavings: Option[BigDecimal] = Some(8022.25)
  val foreignTaxCreditReliefOnForeignIncome: Option[BigDecimal] = Some(8023.25)

  val foreignTaxCreditReliefModel: ForeignTaxCreditRelief =
    ForeignTaxCreditRelief (
      customerCalculatedRelief = customerCalculatedRelief,
      totalForeignTaxCreditRelief = totalForeignTaxCreditRelief,
      foreignTaxCreditReliefOnProperty = foreignTaxCreditReliefOnProperty,
      foreignTaxCreditReliefOnDividends = foreignTaxCreditReliefOnDividends,
      foreignTaxCreditReliefOnSavings = foreignTaxCreditReliefOnSavings,
      foreignTaxCreditReliefOnForeignIncome = foreignTaxCreditReliefOnForeignIncome
    )

  val foreignTaxCreditReliefJson: JsValue = Json.parse(
    s"""
      |{
      |  "customerCalculatedRelief": $customerCalculatedRelief,
      |  "totalForeignTaxCreditRelief": $totalForeignTaxCreditRelief,
      |  "foreignTaxCreditReliefOnProperty": ${foreignTaxCreditReliefOnProperty.get},
      |  "foreignTaxCreditReliefOnDividends": ${foreignTaxCreditReliefOnDividends.get},
      |  "foreignTaxCreditReliefOnSavings": ${foreignTaxCreditReliefOnSavings.get},
      |  "foreignTaxCreditReliefOnForeignIncome": ${foreignTaxCreditReliefOnForeignIncome.get}
      |}
    """.stripMargin
  )
}
