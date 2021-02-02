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
import v2.models.response.getCalculation.incomeTaxAndNics.detail.{OverseasPensionContributions, ShortServiceRefundBands}

object OverseasPensionContributionsFixtures {

  val overseasPensionContributionsJson: JsValue = Json.parse(
    """
      |{
      |   "totalShortServiceRefund":100.50,
      |   "totalShortServiceRefundCharge":200.50,
      |   "shortServiceRefundTaxPaid":160.25,
      |   "totalShortServiceRefundChargeDue":160.99,
      |   "shortServiceRefundBands":[
      |      {
      |         "name":"name",
      |         "rate":20.10,
      |         "bandLimit":2000,
      |         "apportionedBandLimit":2000,
      |         "shortServiceRefundAmount":500.50,
      |         "shortServiceRefundCharge":750.99
      |      }
      |   ]
      |}
    """.stripMargin)

  val overseasPensionContributionsModel =
    OverseasPensionContributions(100.50, 200.50, Some(160.25), 160.99,
    Some(
      Seq(
        ShortServiceRefundBands(
          "name", 20.10, 2000, 2000, 500.50, 750.99
        )
      )
    )
  )
}