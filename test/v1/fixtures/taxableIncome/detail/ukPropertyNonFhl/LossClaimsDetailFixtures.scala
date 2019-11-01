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

package v1.fixtures.taxableIncome.detail.ukPropertyNonFhl

import play.api.libs.json.{JsObject, JsValue, Json}
import support.UnitSpec
import v1.models.response.getCalculation.taxableIncome.detail.ukPropertyNonFhl.detail.LossClaimsDetail

object LossClaimsDetailFixtures extends UnitSpec {

  def incomeSourceTypeField(incomeSourceType: String): JsObject = Json.parse(
    s"""
      |{
      | "incomeSourceType" : "$incomeSourceType"
      |}
    """.stripMargin).asInstanceOf[JsObject]

  val lossClaimsDetailDesJson: JsValue = Json.parse(
    s"""
      |{
      | "inputs" : {
      |   "lossesBroughtForward" : [
      |     ${LossBroughtForwardFixtures.lossBroughtForwardDesJson.asInstanceOf[JsObject].++(incomeSourceTypeField("02")).toString()},
      |     ${LossBroughtForwardFixtures.lossBroughtForwardDesJson.asInstanceOf[JsObject].++(incomeSourceTypeField("04")).toString()}
      |   ]
      | },
      | "calculation" : {
      |   "lossesAndClaims" : {
      |     "resultOfClaimsApplied" : [
      |       ${ResultOfClaimAppliedFixtures.resultOfClaimAppliedDesJson.asInstanceOf[JsObject].++(incomeSourceTypeField("02")).toString()},
      |       ${ResultOfClaimAppliedFixtures.resultOfClaimAppliedDesJson.asInstanceOf[JsObject].++(incomeSourceTypeField("04")).toString()}
      |     ],
      |     "defaultCarriedForwardLosses" : [
      |       ${DefaultCarriedForwardLossFixtures.defaultCarriedForwardLossDesJson.asInstanceOf[JsObject].++(incomeSourceTypeField("02")).toString()},
      |       ${DefaultCarriedForwardLossFixtures.defaultCarriedForwardLossDesJson.asInstanceOf[JsObject].++(incomeSourceTypeField("04")).toString()}
      |     ],
      |     "claimsNotApplied" : [
      |       ${ClaimNotAppliedFixtures.claimNotAppliedDesJson.asInstanceOf[JsObject].++(incomeSourceTypeField("02")).toString()},
      |       ${ClaimNotAppliedFixtures.claimNotAppliedDesJson.asInstanceOf[JsObject].++(incomeSourceTypeField("04")).toString()}
      |     ]
      |   }
      | }
      |}
    """.stripMargin)

  val lossClaimsDetailDesJsonWithoutValidTypes: JsValue = Json.parse(
    s"""
       |{
       | "inputs" : {
       |   "lossesBroughtForward" : [
       |     ${LossBroughtForwardFixtures.lossBroughtForwardDesJson.asInstanceOf[JsObject].++(incomeSourceTypeField("04")).toString()}
       |   ]
       | },
       | "calculation" : {
       |   "lossesAndClaims" : {
       |     "resultOfClaimsApplied" : [
       |       ${ResultOfClaimAppliedFixtures.resultOfClaimAppliedDesJson.asInstanceOf[JsObject].++(incomeSourceTypeField("04")).toString()}
       |     ],
       |     "defaultCarriedForwardLosses" : [
       |       ${DefaultCarriedForwardLossFixtures.defaultCarriedForwardLossDesJson.asInstanceOf[JsObject].++(incomeSourceTypeField("04")).toString()}
       |     ],
       |     "claimsNotApplied" : [
       |       ${ClaimNotAppliedFixtures.claimNotAppliedDesJson.asInstanceOf[JsObject].++(incomeSourceTypeField("04")).toString()}
       |     ]
       |   }
       | }
       |}
    """.stripMargin)

  val lossClaimsDetailDesJsonWithoutOptionals: JsValue = Json.parse(
    s"""
       |{
       | "inputs" : {
       |
       | },
       | "calculation" : {
       |
       | }
       |}
    """.stripMargin)

  val lossClaimsDetailMtdJson: JsValue = Json.parse(
    s"""
      |{
      | "lossesBroughtForward" : [
      |   ${LossBroughtForwardFixtures.lossBroughtForwardMtdJson.toString()}
      | ],
      | "resultOfClaimsApplied" : [
      |   ${ResultOfClaimAppliedFixtures.resultOfClaimAppliedMtdJson.toString()}
      | ],
      | "defaultCarriedForwardLosses" :[
      |   ${DefaultCarriedForwardLossFixtures.defaultCarriedForwardLossMtdJson.toString()}
      | ],
      | "claimsNotApplied" : [
      |   ${ClaimNotAppliedFixtures.claimNotAppliedMtdJson.toString()}
      | ]
      |}
    """.stripMargin)

  val emptyJson: JsValue = Json.obj()

  val lossClaimsDetailModel = LossClaimsDetail(
    Some(Seq(LossBroughtForwardFixtures.lossBroughtForwardModel)),
    Some(Seq(ResultOfClaimAppliedFixtures.resultOfClaimAppliedModel)),
    Some(Seq(DefaultCarriedForwardLossFixtures.defaultCarriedForwardLossModel)),
    Some(Seq(ClaimNotAppliedFixtures.claimNotAppliedModel))
  )
}
