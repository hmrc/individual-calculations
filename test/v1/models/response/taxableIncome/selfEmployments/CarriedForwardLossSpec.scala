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
import CarriedForwardLossSpec._

object CarriedForwardLossSpec {

  def desJson(claimId: String = "CCIS12345678911", incomeSourceId: String = "AAIS12345678904", incomeSourceType: String = "01") =
    Json.parse(s"""
                           |{
                           |    "claimId": "$claimId",
                           |    "originatingClaimId": "OCIS12345678901",
                           |    "incomeSourceId": "$incomeSourceId",
                           |    "incomeSourceType": "$incomeSourceType",
                           |    "claimType": "CF",
                           |    "taxYearClaimMade": 2047,
                           |    "taxYearLossIncurred": 2045,
                           |    "currentLossValue": 49177438626,
                           |    "lossType": "income"
                           |}
                           |""".stripMargin)

  def model(claimId: String = "CCIS12345678911") = CarriedForwardLoss(
    claimId = Some(claimId),
    claimType = TypeOfClaim.`carry-forward`,
    taxYearClaimMade = Some("2046-47"),
    taxYearLossIncurred = "2044-45",
    currentLossValue = 49177438626L,
    lossType = LossType.income
  )
}

class CarriedForwardLossSpec extends UnitSpec {
  "reads" should {
    "convert tax years and claim types" in {
      desJson().as[CarriedForwardLoss] shouldBe
        model()
    }
  }

  "writes" should {
    "work" in {
      Json.toJson(model()) shouldBe Json.parse("""
          |{
          |    "claimId": "CCIS12345678911",
          |    "claimType": "carry-forward",
          |    "taxYearClaimMade": "2046-47",
          |    "taxYearLossIncurred": "2044-45",
          |    "currentLossValue": 49177438626,
          |    "lossType": "income"
          |}
          |""".stripMargin)
    }
  }
}
