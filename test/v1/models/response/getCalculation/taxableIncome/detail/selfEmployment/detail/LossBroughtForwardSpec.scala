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

package v1.models.response.getCalculation.taxableIncome.detail.selfEmployment.detail

import play.api.libs.json.{JsError, JsSuccess, Json}
import support.UnitSpec
import v1.fixtures.taxableIncome.detail.selfEmployments.LossBroughtForwardFixtures._

class LossBroughtForwardSpec extends UnitSpec {

  "LossesBroughtForward" when {
    "read from valid Json" should {
      "return a JsSuccess" in {
        lossBroughtForwardDesJson.validate[LossBroughtForward] shouldBe a[JsSuccess[_]]
      }
      "containing the expected LossesBroughtForward object" in {
        lossBroughtForwardDesJson.as[LossBroughtForward] shouldBe lossBroughtForwardResponse
      }
    }

    "read from Json with the MtdLoss field not present" should {
      "map the MtdLoss field to 'true' and return the expected LossesBroughtForward object" in {
        lossBroughtForwardDesJsonWithoutMtdLoss.as[LossBroughtForward] shouldBe lossBroughtForwardResponseWithoutMtdLoss
      }
    }

    "read from invalid Json" should {
      "return a JsError" in {
        lossBroughtForwardInvalidJson.validate[LossBroughtForward] shouldBe a[JsError]
      }
    }

    "written to Json" should {
      "return the expected JsObject" in {
        Json.toJson(lossBroughtForwardResponse) shouldBe lossBroughtForwardWrittenJson
      }
    }
  }

}
