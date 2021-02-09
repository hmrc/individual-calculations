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

package v1r2.fixtures.getCalculation.incomeTaxAndNics.summary

import play.api.libs.json.{JsValue, Json}
import v1r2.models.response.getCalculation.incomeTaxAndNics.summary.NicSummary

object NicSummaryFixtures {

  val nicSummaryFilledJson: JsValue = Json.parse(
    """
      |{
      |   "class2Nics":{
      |      "amount": 100.25
      |   },
      |   "class4Nics":{
      |      "totalAmount": 200.25
      |   },
      |   "totalNic": 300.25
      |}
    """.stripMargin)

  val nicSummaryEmptyModelJson: JsValue = Json.parse(
    """
      |{
      |   "class2Nics":{
      |
      |   },
      |   "class4Nics":{
      |
      |   }
      |}
    """.stripMargin)

  val nicSummaryOutputJson: JsValue = Json.parse(
    """
      |{
      |   "class2NicsAmount": 100.25,
      |   "class4NicsAmount": 200.25,
      |   "totalNic": 300.25
      |}
    """.stripMargin)

  val nicSummaryEmptyJson: JsValue = Json.obj()

  val nicSummaryFilledModel = NicSummary(
    Some(100.25), Some(200.25), Some(300.25)
  )
}
