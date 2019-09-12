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

class LossClaimsSummarySpec extends UnitSpec {

  val model = LossClaimsSummary(
    totalBroughtForwardIncomeTaxLosses= Some(1),
    broughtForwardIncomeTaxLossesUsed = Some(2),
    totalIncomeTaxLossesCarriedForward= Some(3),
    totalBroughtForwardClass4Losses= Some(4),
    broughtForwardClass4LossesUsed = Some(5),
    carrySidewaysClass4LossesUsed= Some(6),
    totalClass4LossesCarriedForward= Some(7)
  )

  "reads" should {
    "work" in {

      val desJson = Json.parse("""
                                 |  {
                                 |    "incomeSourceId": "LLIS12345678908",
                                 |    "incomeSourceType": "01",
                                 |    "incomeSourceName": "abcdefghijklm",
                                 |    "totalIncome": 79291452394,
                                 |    "totalExpenses": 89009405890,
                                 |    "netProfit": 93480251427,
                                 |    "netLoss": 10017807116,
                                 |    "totalAdditions": 39146901282,
                                 |    "totalDeductions": 80659848172,
                                 |    "accountingAdjustments": -87697961926.99,
                                 |    "taxableProfit": 92672149284,
                                 |    "adjustedIncomeTaxLoss": 2,
                                 |    "totalBroughtForwardIncomeTaxLosses": 1,
                                 |    "lossForCSFHL": 2,
                                 |    "broughtForwardIncomeTaxLossesUsed": 2,
                                 |    "taxableProfitAfterIncomeTaxLossesDeduction": 2,
                                 |    "totalIncomeTaxLossesCarriedForward": 3,
                                 |    "class4Loss": 2,
                                 |    "totalBroughtForwardClass4Losses": 4,
                                 |    "broughtForwardClass4LossesUsed": 5,
                                 |    "carrySidewaysClass4LossesUsed": 6,
                                 |    "totalClass4LossesCarriedForward": 7
                                 |  }
                                 |""".stripMargin)

      desJson.as[LossClaimsSummary] shouldBe
        model
    }
  }

  "writes" should {
    "work" in {
      Json.toJson(model) shouldBe Json.parse("""
                                 |{
                                 |    "totalBroughtForwardIncomeTaxLosses": 1,
                                 |    "broughtForwardIncomeTaxLossesUsed": 2,
                                 |    "totalIncomeTaxLossesCarriedForward": 3,
                                 |    "totalBroughtForwardClass4Losses": 4,
                                 |    "broughtForwardClass4LossesUsed": 5,
                                 |    "carrySidewaysClass4LossesUsed": 6,
                                 |    "totalClass4LossesCarriedForward": 7
                                 |}
                                 |""".stripMargin)
    }
  }
}
