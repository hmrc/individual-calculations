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

package v1.models.response.getCalculation.incomeTaxAndNics.detail

import play.api.libs.json.{JsError, JsSuccess, Json}
import support.UnitSpec
import v1.fixtures.getCalculation.incomeTaxAndNics.detail.TaxBandFixtures._

class TaxBandSpec extends UnitSpec {

  "TaxBand" should {

    "write correctly to json" in {
      Json.toJson(taxBandModel) shouldBe taxBandJson
    }

    "read correctly from json" in {
      taxBandJson.validate[TaxBand] shouldBe JsSuccess(taxBandModel)
    }

    "read from invalid JSON" should {
      "produce a JsError" in {
        val invalidJson = Json.parse(
          """
            |{
            |   "name":true,
            |   "rate":100.25,
            |   "bandLimit":400,
            |   "apportionedBandLimit":500,
            |   "income":600,
            |   "taxAmount":700.25
            |}
          """.stripMargin
        )
        invalidJson.validate[TaxBand] shouldBe a[JsError]
      }
    }
  }
}
