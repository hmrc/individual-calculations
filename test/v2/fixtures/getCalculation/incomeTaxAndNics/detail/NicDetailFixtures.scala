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

import play.api.libs.json.{JsValue, Json}
import v2.models.response.getCalculation.incomeTaxAndNics.detail._

object NicDetailFixtures {

  val class2 =
    Class2NicDetail(
      Some(100.25), Some(200.25), Some(300.25), Some(400.25), underSmallProfitThreshold = true, Some(false), Some(false)
    )

  val class4 = Class4NicDetail(
    Some(
      Class4Losses(
        Some(3001), Some(3002), Some(3003)
      )
    ),
    Some(3003),
    Some(3004),
    Some(
      Seq(
        NicBand(
          name = "name",
          rate = 100.25,
          threshold = Some(200),
          apportionedThreshold = Some(300),
          income = 400,
          amount = 500.25
        )
      )
    )
  )

  val nicDetailEmptyJson: JsValue = Json.obj()

  val nicDetailInputJsonWithEmptyModels: JsValue = Json.parse(
    """
      |{
      | "class4Nics" : {}
      |}
    """.stripMargin)

  val nicDetailFilledJson: JsValue = Json.parse(
    s"""
   {
       |      	"inputs": {
       |      		"personalInformation": {
       |      			"class2VoluntaryContributions": false
       |      		}
       |      	},
       |      	"calculation": {
       |      		"taxCalculation": {
       |      			"nics": {
       |      				"class2Nics": {
       |      					"weeklyRate": 100.25,
       |      					"weeks": 200.25,
       |      					"limit": 300.25,
       |      					"apportionedLimit": 400.25,
       |      					"underSmallProfitThreshold": true,
       |      					"actualClass2Nic": false
       |      				},
       |
       |
       |      				"class4Nics": {
       |      					"totalClass4LossesAvailable": 3001,
       |      					"totalClass4LossesUsed": 3002,
       |      					"totalClass4LossesCarriedForward": 3003,
       |      					"totalIncomeLiableToClass4Charge": 3003,
       |      					"totalIncomeChargeableToClass4": 3004,
       |      					"nic4Bands": [{
       |      						"name": "name",
       |      						"rate": 100.25,
       |      						"threshold": 200,
       |      						"apportionedThreshold": 300,
       |      						"income": 400,
       |      						"amount": 500.25
       |      					}]
       |      				}
       |      			}
       |      		}
       |      	}
       |      }
    """.stripMargin)

  val nicDetailOutputJson: JsValue = Json.parse(
    s"""
       |{
       | "class2Nics" : ${Json.toJson(class2).toString()},
       | "class4Nics" : ${Json.toJson(class4).toString()}
       |}
    """.stripMargin)

  val nicDetailFilledModel = NicDetail(Some(class2), Some(class4))

}