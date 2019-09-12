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
import v1.models.des.LossType
import v1.models.domain.TypeOfClaim
import v1.models.response.taxableIncome.selfEmployments.ResultOfClaimAppliedSpec._

object ResultOfClaimAppliedSpec {

  def desJson(claimId: String = "CCIS12345678911", incomeSourceId: String = "AAIS12345678904", incomeSourceType: String = "01") = Json.parse(s"""
                             |  {
                             |    "claimId": "$claimId",
                             |    "originatingClaimId": "000000000000210",
                             |    "incomeSourceId": "$incomeSourceId",
                             |    "incomeSourceType": "$incomeSourceType",
                             |    "taxYearClaimMade": 2039,
                             |    "claimType": "CF",
                             |    "mtdLoss": true,
                             |    "taxYearLossIncurred": 2051,
                             |    "lossAmountUsed": 64613077921,
                             |    "remainingLossValue": 72548288090,
                             |    "lossType": "income"
                             |  }
                             |""".stripMargin)

  val model = ResultOfClaimApplied(
    claimId = Some("CCIS12345678901"),
    taxYearClaimMade = "2038-39",
    claimType = TypeOfClaim.`carry-forward`,
    mtdLoss = Some(true),
    taxYearLossIncurred = "2050-51",
    lossAmountUsed = 64613077921L,
    remainingLossValue = 72548288090L,
    lossType = LossType.income
  )
}

class ResultOfClaimAppliedSpec extends UnitSpec {

  "reads" should {
    "convert tax years and claim types" in {
      desJson().as[ResultOfClaimApplied] shouldBe model
    }
  }

  "writes" should {
    "work" in {
      Json.toJson(model) shouldBe Json.parse("""
                                               |  {
                                               |    "claimId": "CCIS12345678901",
                                               |    "taxYearClaimMade": "2038-39",
                                               |    "claimType": "carry-forward",
                                               |    "mtdLoss": true,
                                               |    "taxYearLossIncurred": "2050-51",
                                               |    "lossAmountUsed": 64613077921,
                                               |    "remainingLossValue": 72548288090,
                                               |    "lossType": "income"
                                               |  }
                                               |""".stripMargin)
    }
  }
}
