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

package v1.fixtures.getCalculation.incomeTaxAndNics.summary

import play.api.libs.json.{JsValue, Json}
import v1.models.response.getCalculation.incomeTaxAndNics.summary.{CalculationSummary, IncomeTaxSummary, NicSummary}

object CalculationSummaryFixtures {

  val minInputJson: JsValue = Json.parse("""
      |{
      | "calculation" : {
      |   "taxCalculation" : {
      |     "incomeTax" : {
      |       "incomeTaxCharged" : 10.25
      |     },
      |     "totalIncomeTaxAndNicsDue" : 100.25
      |   }
      | },
      | "inputs" : {
      |  "personalInformation" : {
      |    "taxRegime" : "UK"
      |  }
      | }
      |}
    """.stripMargin)

  val inputJsonWithEmptyModels: JsValue = Json.parse("""
      |{
      | "calculation" : {
      |   "taxCalculation" : {
      |     "incomeTax" : {
      |       "incomeTaxCharged" : 10.25
      |     },
      |     "nics" : {
      |
      |     },
      |     "totalIncomeTaxAndNicsDue" : 100.25
      |   }
      | },
      | "inputs" : {
      |  "personalInformation" : {
      |    "taxRegime" : "UK"
      |  }
      | }
      |}
    """.stripMargin)

  val filledInputJson: JsValue = Json.parse("""
      |{
      | "calculation" : {
      |   "taxCalculation" : {
      |     "incomeTax" : {
      |       "incomeTaxCharged" : 10.25
      |     },
      |     "nics" : {
      |       "class2Nics" : {
      |         "amount" : 200.25
      |       }
      |     },
      |     "totalIncomeTaxNicsCharged" : 300.25,
      |     "totalTaxDeducted" : 400.25,
      |     "totalIncomeTaxAndNicsDue" : 100.25
      |   }
      | },
      | "inputs" : {
      |   "personalInformation" : {
      |     "taxRegime" : "UK"
      |   }
      | }
      |}
    """.stripMargin)

  val minOutputJson: JsValue = Json.parse("""
      |{
      | "incomeTax" : {
      |   "incomeTaxCharged" : 10.25
      | },
      | "totalIncomeTaxAndNicsDue" : 100.25,
      | "taxRegime" : "UK"
      |}
    """.stripMargin)

  val filledOutputJson: JsValue = Json.parse("""
      |{
      | "incomeTax" : {
      |   "incomeTaxCharged" : 10.25
      | },
      | "nics" : {
      |   "class2NicsAmount" : 200.25
      | },
      | "totalIncomeTaxNicsCharged" : 300.25,
      | "totalTaxDeducted" : 400.25,
      | "totalIncomeTaxAndNicsDue" : 100.25,
      | "taxRegime" : "UK"
      |}
    """.stripMargin)

  val minModel = CalculationSummary(IncomeTaxSummary(10.25, None, None), None, None, None, 100.25, "UK")

  val filledModel = CalculationSummary(
    IncomeTaxSummary(10.25, None, None),
    Some(NicSummary(Some(200.25), None, None)),
    Some(300.25),
    Some(400.25),
    100.25,
    "UK"
  )

}
