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
package v1.models.des.taxCalculation

import play.api.libs.json.{JsSuccess, Json}
import v1.models.des.taxCalculation.componentObjects.Metadata

object TempMain {
  def main(args: Array[String]): Unit = {
    val json = Json.parse(
      """
        |{
        |            "calculationId": "test1",
        |            "taxYear": "test1",
        |            "requestedBy": "hmrc",
        |            "requestedTimestamp": "test1",
        |            "calculationReason": "updatedLossEvent",
        |            "calculationTimestamp": "test1",
        |            "calculationType": "biss",
        |            "intentToCrystallise": true,
        |            "crystallised": true,
        |            "crystallisationTimestamp": "test1",
        |            "periodFrom": "test1",
        |            "periodTo": "test1"
        |}""".stripMargin)
    println(json.validate[Metadata])
  }
}
