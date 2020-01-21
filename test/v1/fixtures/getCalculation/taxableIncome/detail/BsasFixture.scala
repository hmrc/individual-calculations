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

package v1.fixtures.getCalculation.taxableIncome.detail

import play.api.libs.json.{JsValue, Json}
import v1.models.response.getCalculation.taxableIncome.detail.Bsas

object BsasFixture {

  val desJson: JsValue = Json.parse(
    """
      |{
      |  "incomeSourceId" : "anId",
      |  "applied" : true
      |}
    """.stripMargin)

  val invalidDesJson: JsValue = Json.parse(
    """
      |{
      |  "incomeSourceId" : 100,
      |  "applied" : true
      |}
    """.stripMargin)

  val mtdJson: JsValue = Json.parse(
    """
      |{
      |  "bsasId" : "anId",
      |  "applied" : true
      |}
    """.stripMargin)

  val bsasResponse: Bsas = Bsas("anId", applied = true)
  val fhlBsasResponse: Bsas = Bsas("AcIS12345678910", applied = false)
  val nonFhlBsasResponse: Bsas = Bsas("anId", applied = true)

}
