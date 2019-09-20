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

package v1.models.response.getCalculation.taxableIncome.detail

import play.api.libs.functional.syntax._
import play.api.libs.json.Reads._
import play.api.libs.json._
import utils.NestedJsonReads
import v1.models.response.getCalculation.taxableIncome.detail.selfEmployment.SelfEmployment
import v1.models.response.getCalculation.taxableIncome.detail.ukProperty.{UkPropertyFhl, UkPropertyNonFhl}

case class BusinessProfitAndLoss(selfEmployments: Option[SelfEmployment],
                                 ukPropertyFhl: Option[UkPropertyFhl],
                                 ukPropertyNonFhl: Option[UkPropertyNonFhl])

object BusinessProfitAndLoss extends NestedJsonReads {
  implicit val writes: Writes[BusinessProfitAndLoss] = Json.writes[BusinessProfitAndLoss]


  implicit val reads: Reads[BusinessProfitAndLoss] = (
    __.readNullable[SelfEmployment].map(_.flatMap {
      case SelfEmployment(None) => None
      case x => Some(x)
    }) and
    __.readNullable[UkPropertyFhl].map(_.flatMap {
        case UkPropertyFhl(None, None, None, None, None, None, None, None, None, None, None, None) => None
        case x => Some(x)
      }) and
      __.readNullable[UkPropertyNonFhl].map(_.flatMap {
        case UkPropertyNonFhl(None) => None
        case x => Some(x)
      })
    )(BusinessProfitAndLoss.apply _)

}
