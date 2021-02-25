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

package v2.fixtures.getCalculation.incomeTaxAndNics.detail

import play.api.libs.json.{JsObject, JsValue, Json}
import v2.fixtures.getCalculation.incomeTaxAndNics.detail.Class2NicDetailFixtures._
import v2.fixtures.getCalculation.incomeTaxAndNics.detail.Class4NicDetailFixtures._
import v2.models.response.getCalculation.incomeTaxAndNics.detail._

object NicDetailFixtures {

  val nicDetailDesJson: JsValue = Json.parse(
    """
      |{
      |   "inputs": {
      |      "personalInformation": {
      |         "class2VoluntaryContributions": false
      |      }
      |   },
      |   "calculation": {
      |      "taxCalculation": {
      |         "nics": {
      |            "class2Nics": {
      |               "weeklyRate": 100.25,
      |               "weeks": 200.25,
      |               "limit": 300.25,
      |               "apportionedLimit": 400.25,
      |               "underSmallProfitThreshold": true,
      |               "actualClass2Nic": false
      |            },
      |            "class4Nics": {
      |               "totalClass4LossesAvailable": 3001,
      |               "totalClass4LossesUsed": 3002,
      |               "totalClass4LossesCarriedForward": 3003,
      |               "totalIncomeLiableToClass4Charge": 3003,
      |               "totalIncomeChargeableToClass4": 3004,
      |               "nic4Bands":[
      |                  {
      |                     "name":"name",
      |                     "rate":100.25,
      |                     "threshold":200,
      |                     "apportionedThreshold":300,
      |                     "income":400,
      |                     "amount":500.25
      |                  }
      |               ]
      |            }
      |         }
      |      }
      |   }
      |}
    """.stripMargin
  )

  val nicDetailEmptyJson: JsObject = JsObject.empty

  val nicDetailDesJsonWithEmptyObject: JsValue = Json.parse(
    """
      |{
      |   "calculation": {
      |      "taxCalculation": {
      |         "nics": {
      |            "class4Nics": { }
      |         }
      |      }
      |   }
      |}
    """.stripMargin
  )

  val nicDetailMtdJson: JsValue = Json.parse(
    s"""
       |{
       |  "class2Nics": $class2NicDetailMtdJson,
       |  "class4Nics": $class4NicDetailMtdJson
       |}
    """.stripMargin
  )

  val nicDetailModel: NicDetail = NicDetail(
    class2Nics = Some(class2NicDetailModel),
    class4Nics = Some(class4NicDetailModel)
  )
}