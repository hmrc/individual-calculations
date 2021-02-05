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

object PensionContributionReliefsFixtures {

  val desJson: JsValue = Json.parse("""{
      |       "totalPensionContributionReliefs": 1000,
      |       "pensionContributionDetail": {
      |          "regularPensionContributions": 1000,
      |          "oneOffPensionContributionsPaid": 1000
      |       }
      |}""".stripMargin)

  val desJsonWithNoDataAndEmptyNestedFields: JsValue = Json.parse("""{
      |       "totalPensionContributionReliefs": 1000,
      |       "pensionContributionDetail": {
      |
      |       }
      |}""".stripMargin)


  val mtdJson: JsValue = Json.parse(
    """
      |{
      |		"totalPensionContributionReliefs": 1000,
      |		"regularPensionContributions": 1000,
      |		"oneOffPensionContributionsPaid": 1000
      | }
      |""".stripMargin)

}
