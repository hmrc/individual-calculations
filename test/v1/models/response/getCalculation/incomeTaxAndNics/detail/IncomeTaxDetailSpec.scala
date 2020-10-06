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

package v1.models.response.getCalculation.incomeTaxAndNics.detail

import play.api.libs.json.{JsError, JsSuccess, Json}
import support.UnitSpec
import v1.fixtures.getCalculation.incomeTaxAndNics.detail.IncomeTaxDetailFixtures._

class IncomeTaxDetailSpec extends UnitSpec {

  "IncomeTaxDetail" should {

    "read correctly from Json" when {

      "provided with empty json" in {
        incomeTaxDetailEmptyJson.validate[IncomeTaxDetail] shouldBe JsSuccess(IncomeTaxDetail.empty)
      }

      "provided with json containing empty models" in {
        incomeTaxDetailEmptyModelJson.validate[IncomeTaxDetail] shouldBe JsSuccess(IncomeTaxDetail.empty)
      }

      "provided with filled json" in {
        incomeTaxDetailFilledModelJson.validate[IncomeTaxDetail] shouldBe JsSuccess(incomeTaxDetailFilledModel)
      }
    }

    "read from invalid JSON" should {
      "produce a JsError" in {
        val invalidJson = Json.parse(
          """
            |{
            |   "taxCalculation":{
            |      "incomeTax":{
            |         "payPensionsProfit":{
            |            "allowancesAllocated": true,
            |            "incomeTaxAmount": 100.50
            |         },
            |         "savingsAndGains":{
            |            "allowancesAllocated": 200,
            |            "incomeTaxAmount": 200.50
            |         },
            |         "lumpSums":{
            |            "allowancesAllocated": 300,
            |            "incomeTaxAmount": 300.50
            |         },
            |         "dividends":{
            |            "allowancesAllocated": 400,
            |            "incomeTaxAmount": 400.50
            |         },
            |         "gainsOnLifePolicies":{
            |            "allowancesAllocated": 500,
            |            "incomeTaxAmount": 500.50
            |         }
            |      }
            |   },
            |   "giftAid":{
            |      "grossGiftAidPayments": 400.25,
            |      "rate": 50.50,
            |      "giftAidTax": 400.75
            |   }
            |}
          """.stripMargin
        )
        invalidJson.validate[GiftAid] shouldBe a[JsError]
      }
    }

    "write to json correctly" when {

      "provided with an empty model" in {
        Json.toJson(IncomeTaxDetail.empty) shouldBe incomeTaxDetailEmptyJson
      }

      "provided with a filled model" in {
        Json.toJson(incomeTaxDetailFilledModel) shouldBe incomeTaxDetailOutputJson
      }
    }
  }
}
