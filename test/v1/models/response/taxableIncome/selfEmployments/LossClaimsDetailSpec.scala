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

import play.api.libs.json.{ JsSuccess, Json }
import support.UnitSpec
import v1.fixtures.CarriedForwardLossFixtures._
import v1.fixtures.ClaimNotAppliedFixtures._
import v1.fixtures.LossBroughtForwardFixtures._
import v1.fixtures.LossClaimsDetailFixtures._
import v1.fixtures.ResultOfClaimAppliedFixtures._
import v1.fixtures.UnclaimedLossFixtures._

class LossClaimsDetailSpec extends UnitSpec {

  "LossClaimsDetail" when {
    "read from valid Json" should {
      "return a JsSuccess" in {
        lossClaimsDetailDefaultDesJson.validate[LossClaimsDetail] shouldBe a[JsSuccess[_]]
      }
      "containing the expected LossClaimsDetail object" in {
        lossClaimsDetailDefaultDesJson.as[LossClaimsDetail] shouldBe lossClaimsDetailDefaultResponse
      }
    }

    "read from Json with no provided field arrays" should {
      "return an empty LossClaimsDetail object" in {
        lossClaimsDetailDesJsonFactory().as[LossClaimsDetail].isEmpty shouldBe true
      }
    }

    "read from empty Json" should {
      "return an empty LossClaimsDetail object" in {
        emptyJson.as[LossClaimsDetail].isEmpty shouldBe true
      }
    }

    "written to Json" should {
      "return the expected JsObject" in {
        Json.toJson(lossClaimsDetailDefaultResponse) shouldBe lossClaimsDetailDefaultWrittenJson
      }
    }

    "written to Json from an empty lossClaimsDetail object" should {
      "return the expected JsObject" in {
        Json.toJson(LossClaimsDetail.emptyLossClaimsDetail) shouldBe emptyJson
      }
    }
  }

  "conditionalReads" when {
    "reading in a sequence of lossBroughtForward" must {
      "not include where incomeSourceType is not 01" in {
        lossClaimsDetailDesJsonFactory(lossesBroughtForward = Seq(lossBroughtForwardDesJsonWithWrongIncomeSourceType))
          .as[LossClaimsDetail]
          .isEmpty shouldBe true
      }
      "include multiple matching items" in {
        val lossesBroughtForwardDes =
          Seq(lossBroughtForwardDesJsonWithWrongIncomeSourceType, lossBroughtForwardDesJson, lossBroughtForwardDesJsonWithoutMtdLoss)
        val lossesBroughtForwardResponse = Seq(lossBroughtForwardResponse, lossBroughtForwardResponseWithoutMtdLoss)

        lossClaimsDetailDesJsonFactory(lossesBroughtForward = lossesBroughtForwardDes)
          .as[LossClaimsDetail] shouldBe lossClaimsDetailResponseFactory(lossesBroughtForward = Some(lossesBroughtForwardResponse))
      }
    }

    "reading in a sequence of resultOfClaimApplied" must {
      "not include where incomeSourceType is not 01" in {
        lossClaimsDetailDesJsonFactory(resultOfClaimsApplied = Seq(resultOfClaimAppliedDesJsonWithWrongIncomeSourceType))
          .as[LossClaimsDetail]
          .isEmpty shouldBe true
      }
      "include multiple matching items" in {
        val resultOfClaimsAppliedDes =
          Seq(resultOfClaimAppliedDesJsonWithWrongIncomeSourceType, resultOfClaimAppliedDesJson, resultOfClaimAppliedDesJsonWithoutMtdLoss)
        val resultOfClaimsAppliedResponse = Seq(resultOfClaimAppliedResponse, resultOfClaimAppliedResponseWithoutMtdLoss)

        lossClaimsDetailDesJsonFactory(resultOfClaimsApplied = resultOfClaimsAppliedDes)
          .as[LossClaimsDetail] shouldBe lossClaimsDetailResponseFactory(resultOfClaimsApplied = Some(resultOfClaimsAppliedResponse))
      }
    }

    "reading in a sequence of unclaimedLoss" must {
      "not include where incomeSourceType is not 01" in {
        lossClaimsDetailDesJsonFactory(unclaimedLosses = Seq(unclaimedLossDesJsonWithWrongIncomeSourceType))
          .as[LossClaimsDetail]
          .isEmpty shouldBe true
      }
      "include multiple matching items" in {
        val unclaimedLossesDes =
          Seq(unclaimedLossDesJsonWithWrongIncomeSourceType, unclaimedLossDesJson, unclaimedLossDesJson)
        val unclaimedLossesResponse = Seq(unclaimedLossResponse, unclaimedLossResponse)

        lossClaimsDetailDesJsonFactory(unclaimedLosses = unclaimedLossesDes)
          .as[LossClaimsDetail] shouldBe lossClaimsDetailResponseFactory(unclaimedLosses = Some(unclaimedLossesResponse))
      }
    }

    "reading in a sequence of carriedForwardLoss" must {
      "not include where incomeSourceType is not 01" in {
        lossClaimsDetailDesJsonFactory(carriedForwardLosses = Seq(carriedForwardLossDesJsonWithWrongIncomeSourceType))
          .as[LossClaimsDetail]
          .isEmpty shouldBe true
      }
      "include multiple matching items" in {
        val carriedForwardLossDes =
          Seq(carriedForwardLossDesJsonWithWrongIncomeSourceType, carriedForwardLossDesJson, carriedForwardLossDesJsonWithoutOptionals)
        val carriedForwardLossesResponse = Seq(carriedForwardLossResponse, carriedForwardLossResponseWithoutOptionals)

        lossClaimsDetailDesJsonFactory(carriedForwardLosses = carriedForwardLossDes)
          .as[LossClaimsDetail] shouldBe lossClaimsDetailResponseFactory(carriedForwardLosses = Some(carriedForwardLossesResponse))
      }
    }

    "reading in a sequence of claimNotApplied" must {
      "not include where incomeSourceType is not 01" in {
        lossClaimsDetailDesJsonFactory(claimsNotApplied = Seq(claimNotAppliedDesJsonWithWrongIncomeSourceType))
          .as[LossClaimsDetail]
          .isEmpty shouldBe true
      }
      "include multiple matching items" in {
        val claimNotAppliedDes =
          Seq(claimNotAppliedDesJsonWithWrongIncomeSourceType, claimNotAppliedDesJson, claimNotAppliedDesJson)
        val claimsNotAppliedResponse = Seq(claimNotAppliedResponse, claimNotAppliedResponse)

        lossClaimsDetailDesJsonFactory(claimsNotApplied = claimNotAppliedDes)
          .as[LossClaimsDetail] shouldBe lossClaimsDetailResponseFactory(claimsNotApplied = Some(claimsNotAppliedResponse))
      }
    }
  }
}
