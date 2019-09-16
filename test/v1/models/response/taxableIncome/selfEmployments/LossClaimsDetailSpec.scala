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

import play.api.libs.json.{ JsArray, Json }
import support.UnitSpec
/*
class LossClaimsDetailSpec extends UnitSpec {
  def desJson(businessProfitAndLoss: JsArray = JsArray.empty,
              resultOfClaimsApplied: JsArray = JsArray.empty,
              unclaimedLosses: JsArray = JsArray.empty,
              carriedForwardLosses: JsArray = JsArray.empty,
              claimsNotApplied: JsArray = JsArray.empty,
              lossesBroughtForward: JsArray = JsArray.empty) = {
    val json =
      s"""
         |{
         |"inputs": {
         |  "lossesBroughtForward": $lossesBroughtForward
         |},
         |"calculation": {
         | "businessProfitAndLoss": $businessProfitAndLoss,
         | "lossesAndClaims": {
         |    "resultOfClaimsApplied": $resultOfClaimsApplied,
         |    "unclaimedLosses": $unclaimedLosses,
         |    "carriedForwardLosses": $carriedForwardLosses,
         |    "claimsNotApplied": $claimsNotApplied
         |  }
         |}
         |}
         |""".stripMargin

    Json.parse(json)
  }

  "reads" must {
    "not contain arrays when they are blank" in {
      fail
    }
  }

  "reads of lossesBroughtForward" must {

    "not include where incomeSourceType is not 01" in {
      fail
    }
    "not include where incomeSourceId does not match selfEmploymentId" in {
      fail
    }

    "include multiple matching items" in {
      fail
    }
  }

  "reads of carriedForwardLosses" must {
    "not include where incomeSourceType is not 01" in {
      desJson(
        businessProfitAndLoss = JsArray(Seq(LossClaimsSummarySpec.desJson("id1", incomeSourceType = "02"))),
        carriedForwardLosses = JsArray(Seq(CarriedForwardLossSpec.desJson(claimId = "claim1", "id1", incomeSourceType = "02")))
      ).as[LossClaimsDetail] shouldBe LossClaimsDetail()
    }

    "not include where incomeSourceId does not match selfEmploymentId" in {
      fail
    }

    "include multiple matching items" in {
      desJson(
        businessProfitAndLoss = JsArray(Seq(LossClaimsSummarySpec.desJson("id1"), LossClaimsSummarySpec.desJson("id2"))),
        carriedForwardLosses =
          JsArray(Seq(CarriedForwardLossSpec.desJson(claimId = "claim1", "id1"), CarriedForwardLossSpec.desJson(claimId = "claim2", "id2")))
      ).as[LossClaimsDetail] shouldBe LossClaimsDetail(
        carriedForwardLosses = Some(Seq(CarriedForwardLossSpec.model("claim1"), CarriedForwardLossSpec.model("claim2")))
      )
    }
  }

  "reads of resultOfClaimsApplied" must {
    "not include where incomeSourceType is not 01" in {
      fail
    }
    "not include where incomeSourceId does not match selfEmploymentId" in {
      fail
    }

    "include multiple matching items" in {
      fail
    }
  }

  "reads of unclaimedLosses" must {
    "not include where incomeSourceType is not 01" in {
      fail
    }
    "not include where incomeSourceId does not match selfEmploymentId" in {
      fail
    }

    "include multiple matching items" in {
      fail
    }
  }

  "reads of claimsNotApplied" must {
    "not include where incomeSourceType is not 01" in {
      fail
    }
    "not include where incomeSourceId does not match selfEmploymentId" in {
      fail
    }

    "include multiple matching items" in {
      fail
    }
  }
}
*/