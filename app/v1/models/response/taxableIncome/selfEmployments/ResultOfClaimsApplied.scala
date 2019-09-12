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
package v1.models.response.taxableIncome.selfEmployments

import v1.models.domain.{ LossType, TypeOfClaim }

case class ResultOfClaimsApplied(
    claimId: Option[String],
    taxYearClaimMade: String,
    claimType: TypeOfClaim,
    mtdLoss: Option[Boolean], // FIXME really?
    taxYearLossIncurred: BigDecimal,
    lossAmountUsed: BigDecimal,
    remainingLossValue: BigDecimal,
    lossType: LossType
)