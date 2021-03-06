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

package v2.models.response.getCalculation.allowancesAndDeductions.detail

import play.api.libs.json.{JsObject, Json}
import support.UnitSpec
import v2.fixtures.getCalculation.allowancesAndDeductions.detail.ReliefsFixtures._
import v2.fixtures.getCalculation.allowancesAndDeductions.detail.ForeignTaxCreditReliefFixture._
import v2.fixtures.getCalculation.allowancesAndDeductions.detail.ResidentialFinanceCostsFixture._
import v2.models.utils.JsonErrorValidators

class ReliefsSpec extends UnitSpec with JsonErrorValidators {

  val reliefs = Reliefs(Some(PensionContributionReliefs(1000, Some(1000),
    Some(1000))), Some(Seq(ReliefsClaimed("nonDeductibleLoanInterest",Some(1000),Some(1000),
    Some(1000),Some(2)))), Some(residentialFinanceCostsModel), Some(foreignTaxCreditReliefModel))

  "reads" should {
    "return a valid object" when {
      "valid json is passed" in {
        desJson.as[Reliefs] shouldBe reliefs
      }

      "json has no fields" in {
        JsObject.empty.as[Reliefs] shouldBe Reliefs.empty
      }

      "json has empty nested fields and no sequences" in {
        desJsonWithNoDataAndEmptyNestedFields.as[Reliefs] shouldBe Reliefs.empty
      }
    }
  }

  "writes" should {
    "return a valid json" when {
      "Reliefs object has data" in {
        Json.toJson(reliefs) shouldBe mtdJson
      }
    }
  }
}
