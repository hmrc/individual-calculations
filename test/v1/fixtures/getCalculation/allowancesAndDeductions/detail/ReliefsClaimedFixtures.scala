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

package v1.fixtures.getCalculation.allowancesAndDeductions.detail

import play.api.libs.json.{JsValue, Json}

object ReliefsClaimedFixtures {

  val desJson: JsValue = Json.parse(
      s"""
         |{
         |       "type": "nonDeductableLoanInterest",
         |       "amountClaimed": 1000,
         |        "allowableAmount": 1000,
         |       "amountUsed": 1000,
         |       "rate": 2
         |}
         |""".stripMargin)

  val desJsonDeficiencyRelief: JsValue = Json.parse(
    s"""
       |{
       |       "type": "deficiencyRelief",
       |       "amountClaimed": 1000,
       |        "allowableAmount": 1000,
       |       "amountUsed": 1000,
       |       "rate": 2
       |}
       |""".stripMargin)

  val mtdJson: JsValue = Json.parse(
    """
      |{
      |		"type": "nonDeductibleLoanInterest",
      |		"amountClaimed": 1000,
      |		"allowableAmount": 1000,
      |		"amountUsed": 1000,
      |		"rate": 2
      |}
      |""".stripMargin)

}
