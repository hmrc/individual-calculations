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
import v2.models.response.getCalculation.allowancesAndDeductions.detail.ForeignProperty
import v2.fixtures.getCalculation.allowancesAndDeductions.detail.ForeignPropertyRfcDetailFixture._

object ForeignPropertyFixture {

  val totalForeignPropertyAllowableAmount: BigDecimal = 49827.34

  val foreignPropertyModel: ForeignProperty =
    ForeignProperty(
      totalForeignPropertyAllowableAmount = totalForeignPropertyAllowableAmount,
      foreignPropertyRfcDetail = Seq(foreignPropertyRfcDetailModel)
    )

  val desJson =
    """{
      |			"totalForeignPropertyAllowableAmount": 49827.34,
      |			"foreignPropertyRfcDetail": [{
      |				"countryCode": "ASM",
      |				"amountClaimed": 49829.34,
      |				"allowableAmount": 49828.34,
      |				"carryForwardAmount": 49830.34
      |			}]
      |		}
      |""".stripMargin
  val foreignPropertyJson: JsValue = Json.parse(
    s"""
      |{
      |  "totalForeignPropertyAllowableAmount": $totalForeignPropertyAllowableAmount,
      |  "foreignPropertyRfcDetail": [$foreignPropertyRfcDetailJson]
      |}
    """.stripMargin
  )
}
