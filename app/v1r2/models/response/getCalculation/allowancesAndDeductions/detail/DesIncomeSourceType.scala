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

import play.api.libs.json.Format
import utils.enums.Enums

sealed trait DesIncomeSourceType {
  def toIncomeSourceType: IncomeSourceType
}

object DesIncomeSourceType {
  case object `03` extends DesIncomeSourceType {
    override def toIncomeSourceType: IncomeSourceType = IncomeSourceType.fhlPropertyEea
  }
  case object `06` extends DesIncomeSourceType {
    override def toIncomeSourceType: IncomeSourceType = IncomeSourceType.foreignIncome
  }
  case object `07` extends DesIncomeSourceType {
    override def toIncomeSourceType: IncomeSourceType = IncomeSourceType.dividendsFromForeignCompanies
  }
  case object `15` extends DesIncomeSourceType {
    override def toIncomeSourceType: IncomeSourceType = IncomeSourceType.foreignProperty
  }
  case object `16` extends DesIncomeSourceType {
    override def toIncomeSourceType: IncomeSourceType = IncomeSourceType.foreignInterest
  }

  implicit val format: Format[DesIncomeSourceType] = Enums.format[DesIncomeSourceType]
}
