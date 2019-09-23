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

package v1.models.response.getCalculation.taxableIncome.detail.selfEmployment

import play.api.libs.json.{ JsSuccess, Json }
import support.UnitSpec
import v1.fixtures.taxableIncome.detail.selfEmployments.LossClaimSummaryFixtures._
import v1.fixtures.taxableIncome.detail.selfEmployments.SelfEmploymentBusinessFixtures._

class SelfEmploymentBusinessSpec extends UnitSpec {

  "SelfEmploymentBusiness: singular" when {
    "read from valid Json" should {
      "return a JsSuccess" in {
        selfEmploymentBusinessDefaultDesJsonSingular.validate[SelfEmploymentBusiness] shouldBe a[JsSuccess[_]]
      }
      "containing the expected SelfEmploymentBusiness object" in {
        selfEmploymentBusinessDefaultDesJsonSingular.as[SelfEmploymentBusiness] shouldBe selfEmploymentBusinessDefaultResponseWithoutDetail
      }
    }
    //Failure case
    //Missing optionals

    "written to Json" should {
      "return the expected JsObject" in {
        Json.toJson(selfEmploymentBusinessDefaultResponse) shouldBe selfEmploymentDetailDefaultWrittenJsonSingular
      }
    }

    //Missing optionals
  }

  "SelfEmploymentBusiness: sequence" when {
    "read from valid Json" should {
      "return a JsSuccess" in {
        selfEmploymentBusinessDefaultDesJsonSequence.validate[Seq[SelfEmploymentBusiness]] shouldBe a[JsSuccess[_]]
      }
      "containing the expected SelfEmploymentBusiness object" in {
        selfEmploymentBusinessDefaultDesJsonSequence.as[Seq[SelfEmploymentBusiness]] shouldBe Seq(selfEmploymentBusinessDefaultResponse)
      }
    }

    //Empty Json/ invalid Json
    //Prove filter
    //Missing optionals

    "written to Json" should {
      "return the expected JsObject" in {
        //Json.toJson(Seq(selfEmploymentBusinessDefaultResponse)) shouldBe selfEmploymentDetailDefaultWrittenJsonSequence
      }
    }
  }
}
