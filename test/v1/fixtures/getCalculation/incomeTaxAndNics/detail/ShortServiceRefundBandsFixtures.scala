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

package v1.fixtures.getCalculation.incomeTaxAndNics.detail

import play.api.libs.json.{JsValue, Json}
import v1.models.response.getCalculation.incomeTaxAndNics.detail.ShortServiceRefundBands

object ShortServiceRefundBandsFixtures {

  val shortServiceRefundBandsJson: JsValue = Json.parse(
    """
      |{
      | "name": "name",
      | "rate": 10.25,
      | "bandLimit" : 2000,
      | "apportionedBandLimit" : 2000,
      | "shortServiceRefundAmount" : 600.25,
      | "shortServiceRefundCharge" : 700.25
      |}
    """.stripMargin)

  val shortServiceRefundBandsModel =
    ShortServiceRefundBands(
      name = "name",
      rate = 10.25,
      bandLimit = 2000,
      apportionedBandLimit = 2000,
      shortServiceRefundAmount = 600.25,
      shortServiceRefundCharge = 700.25
    )
}
