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

package v1.models.response.taxableIncome.selfEmployments

import play.api.libs.json.Json
import support.UnitSpec
import v1.models.domain.TypeOfClaim
import ClaimNotAppliedSpec._

object ClaimNotAppliedSpec {
  def desJson(claimId: String = "CCIS12345678912", incomeSourceId: String = "AAIS12345678904", incomeSourceType: String = "01") = Json.parse(s"""
                             |{
                             |  "claimId": "$claimId",
                             |  "incomeSourceId": "$incomeSourceId",
                             |  "incomeSourceType": "$incomeSourceType",
                             |  "taxYearClaimMade": 2046,
                             |  "claimType": "CF"
                             |}
                             |""".stripMargin)

  val model = ClaimNotApplied(
    claimId = "CCIS12345678912",
    taxYearClaimMade = "2045-46",
    claimType = TypeOfClaim.`carry-forward`
  )
}

class ClaimNotAppliedSpec extends UnitSpec {

  "reads" should {
    "convert tax years and claim types" in {
      desJson().as[ClaimNotApplied] shouldBe model
    }
  }

  "writes" should {
    "work" in {
      Json.toJson(model) shouldBe Json.parse("""
          |{
          |  "claimId": "CCIS12345678912",
          |  "taxYearClaimMade": "2045-46",
          |  "claimType": "carry-forward"
          |}
          |""".stripMargin)
    }
  }
}
