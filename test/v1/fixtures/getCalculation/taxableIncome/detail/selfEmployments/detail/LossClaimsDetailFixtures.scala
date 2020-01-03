/*
 * Copyright 2020 HM Revenue & Customs
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

package v1.fixtures.getCalculation.taxableIncome.detail.selfEmployments.detail

import play.api.libs.json.{JsObject, JsValue, Json}
import v1.fixtures.getCalculation.taxableIncome.detail.selfEmployments.detail.CarriedForwardLossFixtures._
import v1.fixtures.getCalculation.taxableIncome.detail.selfEmployments.detail.ClaimNotAppliedFixtures._
import v1.fixtures.getCalculation.taxableIncome.detail.selfEmployments.detail.LossBroughtForwardFixtures._
import v1.fixtures.getCalculation.taxableIncome.detail.selfEmployments.detail.ResultOfClaimAppliedFixtures._
import v1.fixtures.getCalculation.taxableIncome.detail.selfEmployments.detail.UnclaimedLossFixtures._
import v1.models.response.getCalculation.taxableIncome.detail.selfEmployment.detail
import v1.models.response.getCalculation.taxableIncome.detail.selfEmployment.detail._

object LossClaimsDetailFixtures {

  val incomeSourceId: String   = "AAIS12345678904"
  val incomeSourceType: String = "01"
  val taxYearLossIncurred: Int = 2051
  val lossType: String         = "income"
  val currentLossValue: BigInt = 71438847594L
  val expires: Int             = 2079

  val lossClaimsDetailDefaultResponse: LossClaimsDetail = LossClaimsDetail(
    Some(Seq(lossBroughtForwardResponse)),
    Some(Seq(resultOfClaimAppliedResponse)),
    Some(Seq(unclaimedLossResponse)),
    Some(Seq(carriedForwardLossResponse)),
    Some(Seq(claimNotAppliedResponse))
  )

  val lossClaimsDetailResponseNotAllIdsMatch: LossClaimsDetail =
    lossClaimsDetailDefaultResponse.copy(lossesBroughtForward = Some(Seq(lossBroughtForwardResponseWithDifferentId)))

  val lossClaimsDetailResponseNoLossesBroughtForward: LossClaimsDetail =
    lossClaimsDetailDefaultResponse.copy(lossesBroughtForward = None)

  val lossClaimsDetailDefaultDesJson: JsValue = Json
    .obj("inputs" -> Json.obj("lossesBroughtForward" -> Seq(lossBroughtForwardDesJson)))
    .deepMerge(Json.obj("calculation" -> Json.obj("lossesAndClaims" -> Json.obj("resultOfClaimsApplied" -> Seq(resultOfClaimAppliedDesJson)))))
    .deepMerge(Json.obj("calculation" -> Json.obj("lossesAndClaims" -> Json.obj("unclaimedLosses" -> Seq(unclaimedLossDesJson)))))
    .deepMerge(Json.obj("calculation" -> Json.obj("lossesAndClaims" -> Json.obj("carriedForwardLosses" -> Seq(carriedForwardLossDesJson)))))
    .deepMerge(Json.obj("calculation" -> Json.obj("lossesAndClaims" -> Json.obj("claimsNotApplied" -> Seq(claimNotAppliedDesJson)))))

  val lossClaimsDetailDefaultWrittenJson: JsValue =
    Json
      .obj("lossesBroughtForward" -> Seq(lossBroughtForwardWrittenJson))
      .deepMerge(Json.obj("resultOfClaimsApplied" -> Seq(resultOfClaimAppliedWrittenJson)))
      .deepMerge(Json.obj("unclaimedLosses" -> Seq(unclaimedLossWrittenJson)))
      .deepMerge(Json.obj("carriedForwardLosses" -> Seq(carriedForwardLossWrittenJson)))
      .deepMerge(Json.obj("claimsNotApplied" -> Seq(claimNotAppliedWrittenJson)))

  val emptyJson: JsObject = JsObject.empty

  def lossClaimsDetailDesJsonFactory(resultOfClaimsApplied: Seq[JsValue] = Seq.empty,
                                     unclaimedLosses: Seq[JsValue] = Seq.empty,
                                     carriedForwardLosses: Seq[JsValue] = Seq.empty,
                                     claimsNotApplied: Seq[JsValue] = Seq.empty,
                                     lossesBroughtForward: Seq[JsValue] = Seq.empty): JsValue =
    Json
      .obj("inputs" -> Json.obj("lossesBroughtForward" -> lossesBroughtForward))
      .deepMerge(Json.obj("calculation" -> Json.obj("lossesAndClaims" -> Json.obj("resultOfClaimsApplied" -> resultOfClaimsApplied))))
      .deepMerge(Json.obj("calculation" -> Json.obj("lossesAndClaims" -> Json.obj("unclaimedLosses" -> unclaimedLosses))))
      .deepMerge(Json.obj("calculation" -> Json.obj("lossesAndClaims" -> Json.obj("carriedForwardLosses" -> carriedForwardLosses))))
      .deepMerge(Json.obj("calculation" -> Json.obj("lossesAndClaims" -> Json.obj("claimsNotApplied" -> claimsNotApplied))))

  def lossClaimsDetailDesJsonIdFactory(resultOfClaimsApplied: Seq[JsValue] = Seq.empty,
                                     unclaimedLosses: Seq[JsValue] = Seq.empty,
                                     carriedForwardLosses: Seq[JsValue] = Seq.empty,
                                     claimsNotApplied: Seq[JsValue] = Seq.empty,
                                     lossesBroughtForward: Seq[JsValue] = Seq.empty): JsValue =
    Json
      .obj("inputs" -> Json.obj("lossesBroughtForward" -> lossesBroughtForward))
      .deepMerge(Json.obj("calculation" -> Json.obj("lossesAndClaims" -> Json.obj("resultOfClaimsApplied" -> resultOfClaimsApplied))))
      .deepMerge(Json.obj("calculation" -> Json.obj("lossesAndClaims" -> Json.obj("unclaimedLosses" -> unclaimedLosses))))
      .deepMerge(Json.obj("calculation" -> Json.obj("lossesAndClaims" -> Json.obj("carriedForwardLosses" -> carriedForwardLosses))))
      .deepMerge(Json.obj("calculation" -> Json.obj("lossesAndClaims" -> Json.obj("claimsNotApplied" -> claimsNotApplied))))

  def lossClaimsDetailWrittenJsonFactory(resultOfClaimsApplied: Seq[JsValue] = Seq.empty,
                                         unclaimedLosses: Seq[JsValue] = Seq.empty,
                                         carriedForwardLosses: Seq[JsValue] = Seq.empty,
                                         claimsNotApplied: Seq[JsValue] = Seq.empty,
                                         lossesBroughtForward: Seq[JsValue] = Seq.empty): JsValue =
    Json
      .obj("lossesBroughtForward" -> lossesBroughtForward)
      .deepMerge(Json.obj("resultOfClaimsApplied" -> resultOfClaimsApplied))
      .deepMerge(Json.obj("unclaimedLosses" -> unclaimedLosses))
      .deepMerge(Json.obj("carriedForwardLosses" -> carriedForwardLosses))
      .deepMerge(Json.obj("claimsNotApplied" -> claimsNotApplied))

  def lossClaimsDetailResponseFactory(resultOfClaimsApplied: Option[Seq[ResultOfClaimApplied]] = None,
                                      unclaimedLosses: Option[Seq[UnclaimedLoss]] = None,
                                      carriedForwardLosses: Option[Seq[CarriedForwardLoss]] = None,
                                      claimsNotApplied: Option[Seq[ClaimNotApplied]] = None,
                                      lossesBroughtForward: Option[Seq[LossBroughtForward]] = None): LossClaimsDetail =
    detail.LossClaimsDetail(lossesBroughtForward, resultOfClaimsApplied, unclaimedLosses, carriedForwardLosses, claimsNotApplied)
}
