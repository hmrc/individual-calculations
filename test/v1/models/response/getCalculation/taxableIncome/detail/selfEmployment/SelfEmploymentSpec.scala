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

  package v1.models.response.getCalculation.taxableIncome.detail.selfEmployment

import play.api.libs.json.{JsObject, JsSuccess, Json}
import support.UnitSpec
import v1.fixtures.getCalculation.taxableIncome.detail.selfEmployments.SelfEmploymentBusinessFixtures._
import v1.fixtures.getCalculation.taxableIncome.detail.selfEmployments.SelfEmploymentJson._
import v1.models.response.getCalculation.taxableIncome.detail.selfEmployment.SelfEmployment._

class SelfEmploymentSpec extends UnitSpec {

  "SelfEmploymentBusiness: singular" when {

    val selfEmploymentResponseWithoutOptionals: SelfEmployment =
      SelfEmployment("anId", None, None, None, None, None, None, None, None, None, None, None, None, None, None)

    "read from valid JSON without LossClaimsSummary fields" should {
      "produce the expected SelfEmployment object" in {
        Json.parse("""{"incomeSourceId" : "anId"}""").as[SelfEmployment] shouldBe selfEmploymentResponseWithoutOptionals
      }
    }

    "written to Json" should {
      "return the expected JsObject" in {
        Json.toJson(selfEmploymentBusinessDefaultResponse) shouldBe selfEmploymentDetailDefaultWrittenJsonSingular
      }
    }

    "written to JSON with an empty LossClaimsSummary" should {
      "not write the LossClaimsSummary field" in {
        Json.toJson(selfEmploymentBusinessDefaultResponse.copy(lossClaimsSummary = None)) shouldBe selfEmploymentDetailDefaultWrittenJsonSingularNoSummary
      }
    }
  }

  "SelfEmploymentBusiness: sequence" when {
    "read from valid Json" should {
      "return a JsSuccess" in {
        selfEmploymentBusinessDefaultDesJsonSequence.validate[Seq[SelfEmployment]] shouldBe a[JsSuccess[_]]
      }
      "containing the expected SelfEmploymentBusiness object" in {
        selfEmploymentBusinessDefaultDesJsonSequence.as[Seq[SelfEmployment]] shouldBe Seq(selfEmploymentBusinessDefaultResponse)
      }
    }

    "des returns a valid json with different incomeSourceId in annualAdjustments" should {
      "return SelfEmploymentBusiness object without BSAS object" in {
        selfEmploymentBusinessDefaultDesJsonSequenceWithDifferentIncomeSourceId.as[Seq[SelfEmployment]] shouldBe
          Seq(selfEmploymentBusinessDefaultResponse.copy(bsas = None))
      }
    }

    "read from empty Json" should {
      "return an empty sequence" in {
        JsObject.empty.as[Seq[SelfEmployment]].isEmpty shouldBe true
      }
    }

    "read from Json with multiple selfEmployments" should {
      "return the expected sequence of SelfEmployments" in {
        summariesDesJson.as[Seq[SelfEmployment]] shouldBe selfEmployments
      }
    }

    "read from a complex Json scenario" should {
      "return the expected sequence of SelfEmployments" in {
        complexSelfEmploymentCaseDesJson.as[Seq[SelfEmployment]] shouldBe complexSelfEmploymentCaseResponse
      }
    }

    "written to Json" should {
      "return the expected JsObject" in {
        Json.toJson(Seq(selfEmploymentBusinessDefaultResponse)) shouldBe selfEmploymentDetailDefaultWrittenJsonSequence
      }
    }

    "written from a complex selfEmployment object" should {
      "return the expected JsObject" in {
        Json.toJson(complexSelfEmploymentCaseResponse) shouldBe complexSelfEmploymentCaseWrittenJson
      }
    }
  }

}
