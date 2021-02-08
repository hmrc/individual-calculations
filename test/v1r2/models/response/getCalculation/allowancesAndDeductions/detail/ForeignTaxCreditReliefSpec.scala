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

package v1r2.models.response.getCalculation.allowancesAndDeductions.detail

import support.UnitSpec
import v1r2.fixtures.getCalculation.allowancesAndDeductions.detail.ReliefsFixtures._
import v1r2.models.utils.JsonErrorValidators

class ForeignTaxCreditReliefSpec extends UnitSpec with JsonErrorValidators {

  val foreignTaxCreditRelief = ForeignTaxCreditRelief(IncomeSourceType.foreignInterest, Some("ABC647261934212"),
    "FRA", Some(1000), Some(2), Some(1000))


  "reads" should {
    "convert a valid income source type" when {

      "passed '03' for fhlPropertyEea" in {
        desJsonIncomeSourceType("03").as[ForeignTaxCreditRelief] shouldBe foreignTaxCreditRelief
          .copy(incomeSourceType = IncomeSourceType.fhlPropertyEea)
      }

      "passed '06' for foreignIncome" in {
        desJsonIncomeSourceType("06").as[ForeignTaxCreditRelief] shouldBe foreignTaxCreditRelief
          .copy(incomeSourceType = IncomeSourceType.foreignIncome)
      }

      "passed '07' for dividendsFromForeignCompanies" in {
        desJsonIncomeSourceType("07").as[ForeignTaxCreditRelief] shouldBe foreignTaxCreditRelief
          .copy(incomeSourceType = IncomeSourceType.dividendsFromForeignCompanies)
      }

      "passed '15' for foreignProperty" in {
        desJsonIncomeSourceType("15").as[ForeignTaxCreditRelief] shouldBe foreignTaxCreditRelief
          .copy(incomeSourceType = IncomeSourceType.foreignProperty)
      }

      "passed '16' for foreignInterest" in {
        desJsonIncomeSourceType("16").as[ForeignTaxCreditRelief] shouldBe foreignTaxCreditRelief
      }
    }
  }
}
