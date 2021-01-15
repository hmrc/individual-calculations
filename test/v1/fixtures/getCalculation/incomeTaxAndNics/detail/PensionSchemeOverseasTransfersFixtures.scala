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
import v1.models.response.getCalculation.incomeTaxAndNics.detail.PensionSchemeOverseasTransfers

object PensionSchemeOverseasTransfersFixtures {

  val pensionSchemeOverseasTransfersJson: JsValue = Json.parse(
    """
      |{
      | "transferCharge": 120.25,
      | "transferChargeTaxPaid": 130.25,
      | "rate": 60.25,
      | "chargeableAmount": 140.25
      |}
    """.stripMargin)

  val pensionSchemeOverseasTransfersModel =
    PensionSchemeOverseasTransfers(
      Some(120.25), Some(130.25), Some(60.25), Some(140.25)
    )
}