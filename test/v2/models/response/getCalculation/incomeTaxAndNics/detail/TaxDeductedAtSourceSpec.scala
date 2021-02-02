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

package v2.models.response.getCalculation.incomeTaxAndNics.detail

import play.api.libs.json.{JsError, JsObject, Json}
import support.UnitSpec
import v2.fixtures.getCalculation.incomeTaxAndNics.detail.TaxDeductedAtSourceFixtures._

class TaxDeductedAtSourceSpec extends UnitSpec {

  "TaxDeductedAtSource" should {

    "read correctly from json" in {
      taxDeductedAtSourceJson.as[TaxDeductedAtSource] shouldBe taxDeductedAtSourceModel
    }

    "read empty json as empty object" in {
      JsObject.empty.as[TaxDeductedAtSource] shouldBe TaxDeductedAtSource.empty
    }

    "write correctly to json" in {
      Json.toJson(taxDeductedAtSourceModel) shouldBe taxDeductedAtSourceOutputJson
    }

    "read from invalid JSON" should {
      "produce a JsError" in {
        val invalidJson = Json.parse(
          """
            |{
            |   "ukLandAndProperty":true,
            |   "bbsi":200,
            |   "cis":110.25,
            |   "securities":120.35,
            |   "voidedIsa":130.45,
            |   "payeEmployments":140.55,
            |   "occupationalPensions":150.65,
            |   "stateBenefits":160.75
            |}
          """.stripMargin
        )
        invalidJson.validate[TaxDeductedAtSource] shouldBe a[JsError]
      }
    }
  }
}
