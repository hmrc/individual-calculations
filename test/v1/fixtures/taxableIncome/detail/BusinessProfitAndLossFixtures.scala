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

package v1.fixtures.taxableIncome.detail

import play.api.libs.json.{JsValue, Json}
import v1.models.response.getCalculation.taxableIncome.detail.BusinessProfitAndLoss

object BusinessProfitAndLossFixtures {

  val selfEmployments: String  = "1"
  val ukPropertyFhl: String    = "2"
  val ukPropertyNonFhl: String = "3"

  val businessProfitAndLossDesJson: JsValue = Json.parse(s"""{
      |    "selfEmployments" : "$selfEmployments",
      |    "ukPropertyFhl" : "$ukPropertyFhl",
      |    "ukPropertyNonFhl" : "$ukPropertyNonFhl"
      |}""".stripMargin)

  val selfEmploymentsOnlyDesJson: JsValue = Json.parse(s"""{
      |    "selfEmployments" : "$selfEmployments"
      |}""".stripMargin)

  val businessProfitAndLossWrittenJson: JsValue = Json.parse(s"""{
      |    "selfEmployments" : "$selfEmployments",
      |    "ukPropertyFhl" : "$ukPropertyFhl",
      |    "ukPropertyNonFhl" : "$ukPropertyNonFhl"
      |}""".stripMargin)

  val selfEmploymentsOnlyWrittenJson: JsValue = Json.parse(s"""{
      |    "selfEmployments" : "$selfEmployments"
      |}""".stripMargin)

  val businessProfitAndLossInvalidJson: JsValue = Json.parse(s"""{
      |    "selfEmployments" : 1
      |}""".stripMargin)

  val businessProfitAndLossResponse: BusinessProfitAndLoss      = BusinessProfitAndLoss(Some(selfEmployments), Some(ukPropertyFhl), Some(ukPropertyNonFhl))
  val selfEmploymentsOnlyResponse: BusinessProfitAndLoss        = businessProfitAndLossResponse.copy(ukPropertyFhl = None, ukPropertyNonFhl = None)
  val emptyBusinessProfitAndLossResponse: BusinessProfitAndLoss = BusinessProfitAndLoss.emptyBPAL

}
