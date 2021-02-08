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

package v1r2.fixtures.getCalculation.allowancesAndDeductions.detail

import play.api.libs.json.{JsValue, Json}

object PensionContributionsFixtures {

  val desJson: JsValue = Json.parse("""{
   |            "pensionContributions": 1000,
   |            "pensionContributionsDetail": {
   |               "retirementAnnuityPayments": 1000,
   |               "paymentToEmployersSchemeNoTaxRelief": 1000,
   |               "overseasPensionSchemeContributions": 1000
   |            }
   |}""".stripMargin)

  val desJsonWithNoDataAndEmptyNestedFields: JsValue = Json.parse("""{
   |            "pensionContributionsDetail": {
   |
   |            }
   |}""".stripMargin)


  val mtdJson: JsValue = Json.parse(
    """
      |{
      |               "totalPensionContributions": 1000,
      |               "retirementAnnuityPayments": 1000,
      |               "paymentToEmployersSchemeNoTaxRelief": 1000,
      |               "overseasPensionSchemeContributions": 1000
      | }
      |""".stripMargin)

}
