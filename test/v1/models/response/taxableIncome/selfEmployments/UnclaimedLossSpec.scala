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
import v1.models.response.taxableIncome.selfEmployments.UnclaimedLossSpec._

object UnclaimedLossSpec {

  def desJson(incomeSourceId: String = "AAIS12345678904", incomeSourceType: String = "01") = Json.parse(s"""
                             |  {
                             |    "incomeSourceId": "$incomeSourceId",
                             |    "incomeSourceType": "$incomeSourceType",
                             |    "taxYearLossIncurred": 2024,
                             |    "currentLossValue": 71438847594,
                             |    "expires": 2079,
                             |    "lossType": "income"
                             |  }
                             |""".stripMargin)

  val model = UnclaimedLoss(
    taxYearLossIncurred = "2023-24",
    currentLossValue = 71438847594L,
    expires = "2078-79",
    lossType = LossType.income
  )
}

class UnclaimedLossSpec extends UnitSpec {

  "reads" should {
    "convert tax years" in {
      desJson().as[UnclaimedLoss] shouldBe model
    }
  }

  "writes" should {
    "work" in {
      Json.toJson(model) shouldBe Json.parse("""
                                               |  {
                                               |    "taxYearLossIncurred": "2023-24",
                                               |    "currentLossValue": 71438847594,
                                               |    "expires": "2078-79",
                                               |    "lossType": "income"
                                               |  }
                                               |""".stripMargin)
    }
  }

}
