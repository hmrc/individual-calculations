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

package v1.fixtures.taxableIncome.detail.selfEmployments

import play.api.libs.json.{ JsValue, Json }
import v1.models.domain.TypeOfLoss
import v1.models.response.getCalculation.taxableIncome.detail.selfEmployment.SelfEmployment
import v1.models.response.getCalculation.taxableIncome.detail.selfEmployment.detail.{ LossBroughtForward, LossClaimsDetail }

object SelfEmploymentJson {

  val complexSelfEmploymentCaseDesJson: JsValue = Json.parse("""{
      |     "calculation": {
      |       "businessProfitAndLoss" : [
      |           {
      |              "incomeSourceId" : "a",
      |              "incomeSourceType" : "01"
      |            },
      |            {
      |              "incomeSourceId" : "b",
      |              "incomeSourceType" : "01"
      |            },
      |            {
      |              "incomeSourceId" : "c",
      |              "incomeSourceType" : "01"
      |             },
      |             {
      |              "incomeSourceId" : "d",
      |              "incomeSourceType" : "01"
      |            }
      |          ]
      |     },
      |     "inputs" : {
      |         "lossesBroughtForward" : [
      |             {
      |              "lossType" : "income",
      |              "taxYearLossIncurred" : 2018,
      |              "currentLossValue" : 100,
      |              "mtdLoss": false,
      |              "incomeSourceId" : "a",
      |              "incomeSourceType" : "01"
      |              },
      |              {
      |              "lossType" : "income",
      |              "taxYearLossIncurred" : 2018,
      |              "currentLossValue" : 200,
      |              "mtdLoss": false,
      |              "incomeSourceId" : "a",
      |              "incomeSourceType" : "01"
      |              },
      |              {
      |              "lossType" : "income",
      |              "taxYearLossIncurred" : 2018,
      |              "currentLossValue" : 300,
      |              "mtdLoss": false,
      |              "incomeSourceId" : "b",
      |              "incomeSourceType" : "01"
      |              },
      |              {
      |              "lossType" : "income",
      |              "taxYearLossIncurred" : 2018,
      |              "currentLossValue" : 400,
      |              "mtdLoss": false,
      |              "incomeSourceId" : "c",
      |              "incomeSourceType" : "01"
      |              },
      |              {
      |              "lossType" : "income",
      |              "taxYearLossIncurred" : 2018,
      |              "currentLossValue" : 500,
      |              "mtdLoss": false,
      |              "incomeSourceId" : "e",
      |              "incomeSourceType" : "01"
      |              }
      |          ]
      |      }
      |}""".stripMargin)

  val complexSelfEmploymentCaseWrittenJson: JsValue = Json.parse("""[{
      |	      "selfEmploymentId": "a",
      |	      "lossClaimsDetail": {
      |	      	"lossesBroughtForward": [{
      |		      	"lossType": "self-employment",
      |		      	"taxYearLossIncurred": "2017-18",
      |		      	"currentLossValue": 100,
      |		      	"mtdLoss": false
      |	      	}, {
      |		      	"lossType": "self-employment",
      |		      	"taxYearLossIncurred": "2017-18",
      |		      	"currentLossValue": 200,
      |		      	"mtdLoss": false
      |		      }]
      |	      }
      |     }, {
      |	      "selfEmploymentId": "b",
      |	      "lossClaimsDetail": {
      |		      "lossesBroughtForward": [{
      |		      	"lossType": "self-employment",
      |		      	"taxYearLossIncurred": "2017-18",
      |		      	"currentLossValue": 300,
      |		      	"mtdLoss": false
      |		      }]
      |	      }
      |     }, {
      |	      "selfEmploymentId": "c",
      |	      "lossClaimsDetail": {
      |	      	"lossesBroughtForward": [{
      |		      	"lossType": "self-employment",
      |		      	"taxYearLossIncurred": "2017-18",
      |		      	"currentLossValue": 400,
      |		      	"mtdLoss": false
      |		      }]
      |	      }
      |     }, {
      |	      "selfEmploymentId": "d"
      |}]""".stripMargin)

  val lbf1 = LossBroughtForward(TypeOfLoss.`self-employment`, "2017-18", 100, false, "a")
  val lbf2 = LossBroughtForward(TypeOfLoss.`self-employment`, "2017-18", 200, false, "a")
  val lbf3 = LossBroughtForward(TypeOfLoss.`self-employment`, "2017-18", 300, false, "b")
  val lbf4 = LossBroughtForward(TypeOfLoss.`self-employment`, "2017-18", 400, false, "c")
  val lbf5 = LossBroughtForward(TypeOfLoss.`self-employment`, "2017-18", 500, false, "e")

  val lcd1 = LossClaimsDetail(Some(Seq(lbf1, lbf2)), None, None, None, None)
  val lcd2 = LossClaimsDetail(Some(Seq(lbf3)), None, None, None, None)
  val lcd3 = LossClaimsDetail(Some(Seq(lbf4)), None, None, None, None)

  val se1 = SelfEmployment("a", None, None, None, None, None, None, None, None, None, None, None, None, Some(lcd1))
  val se2 = SelfEmployment("b", None, None, None, None, None, None, None, None, None, None, None, None, Some(lcd2))
  val se3 = SelfEmployment("c", None, None, None, None, None, None, None, None, None, None, None, None, Some(lcd3))
  val se4 = SelfEmployment("d", None, None, None, None, None, None, None, None, None, None, None, None, None)

  val complexSelfEmploymentCaseResponse = Seq(se1, se2, se3, se4)
}
