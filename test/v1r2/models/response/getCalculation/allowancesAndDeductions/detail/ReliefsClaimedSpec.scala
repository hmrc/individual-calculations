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

package v1r2.models.response.getCalculation.allowancesAndDeductions.detail

import play.api.libs.json.Json
import support.UnitSpec
import v1r2.fixtures.getCalculation.allowancesAndDeductions.detail.ReliefsClaimedFixtures._
import v1r2.models.utils.JsonErrorValidators

class ReliefsClaimedSpec extends UnitSpec with JsonErrorValidators {

  val reliefsClaimed = ReliefsClaimed("nonDeductibleLoanInterest",Some(1000),Some(1000), Some(1000),Some(2))

  "reads" should {
    "return a valid object" when {
      "valid json is passed" in {
        desJson.as[ReliefsClaimed] shouldBe reliefsClaimed
      }

      "any other value for 'type' is passed" in {
        desJsonDeficiencyRelief.as[ReliefsClaimed] shouldBe reliefsClaimed.copy(`type` = "deficiencyRelief")
      }
    }
  }

  "writes" should {
    "return a valid json" when {
      "ResidentialFinanceCosts object has data" in {
        Json.toJson(reliefsClaimed) shouldBe mtdJson
      }
    }
  }
}
