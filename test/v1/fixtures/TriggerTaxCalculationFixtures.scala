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

package v1.fixtures

import play.api.libs.json.{JsValue, Json}
import v1.models.response.triggerCalculation.TriggerCalculationResponse

object TriggerTaxCalculationFixtures {

  val inputJson: JsValue = Json.parse("""
      |{
      |   "taxYear": "2017-18"
      |}""".stripMargin)

  val responseJson: JsValue = Json.parse(
    """
      |{
      |  "id": "00000000-0000-1000-8000-000000000000"
      |}""".stripMargin)

  val responseModel = TriggerCalculationResponse("00000000-0000-1000-8000-000000000000")
}