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

package v1r2.fixtures.getCalculation.incomeTaxAndNics.detail

import play.api.libs.json.{JsValue, Json}
import v1r2.models.response.getCalculation.incomeTaxAndNics.detail.StudentLoans

object StudentLoanFixtures {

  val studentLoansJson: JsValue = Json.parse(
    """
      |{
      |  "planType": "name",
      |  "studentLoanTotalIncomeAmount": 70.25,
      |  "studentLoanChargeableIncomeAmount": 300.25,
      |  "studentLoanRepaymentAmount": 300.25,
      |  "studentLoanDeductionsFromEmployment": 300.25,
      |  "studentLoanRepaymentAmountNetOfDeductions": 300.25,
      |  "studentLoanApportionedIncomeThreshold": 300,
      |  "studentLoanRate": 50.60
      |}
    """.stripMargin)

  val studentLoansModel =
    StudentLoans(
      "name", 70.25, 300.25, 300.25, Some(300.25), 300.25, 300, 50.60
    )
}