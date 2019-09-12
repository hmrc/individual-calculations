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

class LossBroughtForwardSpec extends UnitSpec {

  val model = LossBroughtForward(
    lossType = LossType.income,
    taxYearLossIncurred = "2054-55",
    currentLossValue = 67263350334L,
    mtdLoss = Some(true)
  )

  "reads" should {
    "convert tax years" in {

      val desJson = Json.parse("""
                                 |  {
                                 |    "lossId": "0yriP9QrW2jTa6n",
                                 |    "incomeSourceId": "AAIS12345678904",
                                 |    "incomeSourceType": "01",
                                 |    "submissionTimestamp": "2019-07-13T07:51:43Z",
                                 |    "lossType": "income",
                                 |    "taxYearLossIncurred": 2055,
                                 |    "currentLossValue": 67263350334,
                                 |    "mtdLoss": true
                                 |  }
                                 |""".stripMargin)

      desJson.as[LossBroughtForward] shouldBe
        model
    }
  }

  "writes" should {
    "work" in {
      Json.toJson(model) shouldBe Json.parse("""
                                               |  {
                                               |    "lossType": "income",
                                               |    "taxYearLossIncurred": "2054-55",
                                               |    "currentLossValue": 67263350334,
                                               |    "mtdLoss": true
                                               |  }
                                               |""".stripMargin)
    }
  }

}
