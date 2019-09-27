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

package v1.fixtures.endOfYearEstimate

import play.api.libs.json.{JsValue, Json, OFormat}
import v1.models.response.getCalculation.endOfYearEstimate.summary.EoyEstimateSummary

object FixtureTemplate {

  case class Placeholder(totalEstimatedIncome: Option[BigInt] = None,
                         totalTaxableIncome: Option[BigInt] = None,
                         incomeTaxAmount: Option[BigDecimal] = None,
                         nic2: Option[BigDecimal] = None,
                         nic4: Option[BigDecimal] = None,
                         totalNicAmount: Option[BigDecimal] = None,
                         incomeTaxNicAmount: Option[BigDecimal] = None)

  object Placeholder {
    implicit val formats: OFormat[Placeholder] = Json.format[Placeholder]
  }

  val totalEstimatedIncome: Option[BigInt]   = Some(1001)
  val totalTaxableIncome: Option[BigInt]     = Some(1002)
  val incomeTaxAmount: Option[BigDecimal]    = Some(1003.1)
  val nic2: Option[BigDecimal]               = Some(1004.1)
  val nic4: Option[BigDecimal]               = Some(1005.1)
  val totalNicAmount: Option[BigDecimal]     = Some(1005.1)
  val incomeTaxNicAmount: Option[BigDecimal] = Some(1006.1)

  val placeholderResponse: Placeholder =
    Placeholder(totalEstimatedIncome, totalTaxableIncome, incomeTaxAmount, nic2, nic4, totalNicAmount, incomeTaxNicAmount)

  val placeholderDesJson: JsValue = Json.parse(s"""{
      |  "totalEstimatedIncome": ${totalEstimatedIncome.get},
      |  "totalTaxableIncome": ${totalTaxableIncome.get},
      |  "incomeTaxAmount": ${incomeTaxAmount.get},
      |  "nic2": ${nic2.get},
      |  "nic4": ${nic4.get},
      |  "totalNicAmount" : ${totalNicAmount.get},
      |  "incomeTaxNicAmount" :  ${incomeTaxNicAmount.get}
      |}""".stripMargin)

  val placeholderDesJsonMissingFields: JsValue = Json.parse(s"""{
      |  "totalEstimatedIncome": ${totalEstimatedIncome.get},
      |  "totalTaxableIncome": ${totalTaxableIncome.get},
      |  "incomeTaxAmount": ${incomeTaxAmount.get},
      |  "nic2": ${nic2.get},
      |  "nic4": ${nic4.get}
      |}""".stripMargin)

  val placeholderDesJsonTopLevel: JsValue = Json.obj("calculation" -> Json.obj("endOfYearEstimate" -> placeholderDesJson))

  val placeholderWrittenJson: JsValue = Json.parse(s"""{
      |  "totalEstimatedIncome": ${totalEstimatedIncome.get},
      |  "totalTaxableIncome": ${totalTaxableIncome.get},
      |  "incomeTaxAmount": ${incomeTaxAmount.get},
      |  "nic2": ${nic2.get},
      |  "nic4": ${nic4.get},
      |  "totalNicAmount" : ${totalNicAmount.get} ,
      |  "incomeTaxNicAmount" :  ${incomeTaxNicAmount.get}
      |}""".stripMargin)

  val placeholderWrittenJsonMissingFields: JsValue = Json.parse(s"""{
      |  "totalEstimatedIncome": ${totalEstimatedIncome.get},
      |  "totalTaxableIncome": ${totalTaxableIncome.get},
      |  "incomeTaxAmount": ${incomeTaxAmount.get},
      |  "nic2": ${nic2.get},
      |  "nic4": ${nic4.get}
      |}""".stripMargin)

  val placeholderWrittenJsonObject: JsValue = Json.obj("summary" -> placeholderWrittenJson)

  val placeholderInvalidJson: JsValue = Json.parse(s"""{
      |  "totalEstimatedIncome": "notANumericValue",
      |  "totalTaxableIncome": ${totalTaxableIncome.get},
      |  "incomeTaxAmount": ${incomeTaxAmount.get},
      |  "nic2": ${nic2.get},
      |  "nic4": ${nic4.get},
      |  "totalNicAmount" : ${totalNicAmount.get} ,
      |  "incomeTaxNicAmount" :  ${incomeTaxNicAmount.get}
      |}""".stripMargin)

  def placeholderResponseFactory(totalEstimatedIncome: Option[BigInt] = totalEstimatedIncome,
                                        totalTaxableIncome: Option[BigInt] = totalTaxableIncome,
                                        incomeTaxAmount: Option[BigDecimal] = incomeTaxAmount,
                                        nic2: Option[BigDecimal] = nic2,
                                        nic4: Option[BigDecimal] = nic4,
                                        totalNicAmount: Option[BigDecimal] = totalNicAmount,
                                        incomeTaxNicAmount: Option[BigDecimal] = incomeTaxNicAmount): Placeholder =
    Placeholder(totalEstimatedIncome, totalTaxableIncome, incomeTaxAmount, nic2, nic4, totalNicAmount, incomeTaxNicAmount)

}
