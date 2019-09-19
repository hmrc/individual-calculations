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

package v1.fixtures.taxableIncome.nonFhlProperty

import play.api.libs.json.{JsValue, Json}
import v1.models.response.getCalculation.taxableIncome.nonFhlProperty.LossClaimsSummary

object LossClaimsSummaryFixtures {

  val lossClaimsSummaryJson: JsValue = Json.parse(
    """
      |{
      | "totalBroughtForwardIncomeTaxLosses" : 2000,
      | "broughtForwardIncomeTaxLossesUsed" : 2000,
      | "totalIncomeTaxLossesCarriedForward" : 2000
      |}
    """.stripMargin)

  val lossClaimsSummaryModel = LossClaimsSummary(Some(2000), Some(2000), Some(2000))
}
