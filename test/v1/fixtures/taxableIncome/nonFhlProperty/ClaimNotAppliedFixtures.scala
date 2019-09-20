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
import v1.models.domain.TypeOfClaim
import v1.models.response.getCalculation.taxableIncome.detail.UkPropertyNonFhl.detail.ClaimNotApplied

object ClaimNotAppliedFixtures {

  val claimNotAppliedDesJson: JsValue = Json.parse(
    """
      |{
      | "claimId" : "EzluDU2ObK02SdA",
      | "taxYearClaimMade" : 2019,
      | "claimType" : "CSGI"
      |}
    """.stripMargin)

  val claimNotAppliedMtdJson: JsValue = Json.parse(
    """
      |{
      | "claimId" : "EzluDU2ObK02SdA",
      | "taxYearClaimMade" : "2018-19",
      | "claimType" : "carry-sideways"
      |}
    """.stripMargin)

  val claimNotAppliedModel = ClaimNotApplied("EzluDU2ObK02SdA", "2018-19", TypeOfClaim.`carry-sideways`)
}
