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

package v1r2.fixtures.getCalculation.incomeTaxAndNics.detail

import play.api.libs.json.{JsValue, Json}
import v1r2.models.response.getCalculation.incomeTaxAndNics.detail.{PensionBands, PensionContributionsInExcessOfTheAnnualAllowance}

object PensionContributionsInExcessOfTheAnnualAllowanceFixtures {

  val pensionContributionsInExcessOfTheAnnualAllowanceJson: JsValue = Json.parse(
    """
      |{
      |   "totalContributions":70.25,
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
      |}
    """.stripMargin)

  val pensionContributionsInExcessOfTheAnnualAllowanceModel =
    PensionContributionsInExcessOfTheAnnualAllowance(
      70.25, 160.50, Some(180.25), 120.99,
      Some(
        Seq(
          PensionBands(
            "Name", 50.10, 2000, 2000, 160.89, 180.99
          )
        )
      )
    )
}