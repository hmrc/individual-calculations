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

package v1.fixtures.taxableIncome.ukProperty

import play.api.libs.json.{JsValue, Json}
import v1.models.domain.TypeOfClaim
import v1.models.response.getCalculation.taxableIncome.detail.ukPropertyFhl.detail._

object LossClaimsDetailFixtures {

  val lossClaimsDetail = LossClaimsDetail(
    Some(List(LossBroughtForward("2054-55",1000,mtdLoss = true))),
    Some(List(ResultOfClaimApplied(Some("CCIS12345678901"),"2038-39", TypeOfClaim.`carry-forward`, mtdLoss = true,
      "2050-51",1000,1000))),
    Some(List(DefaultCarriedForwardLoss("2026-27",1000))))

  val lossClaimsDetailWithoutLossBroughtForward = LossClaimsDetail(None,
    Some(List(ResultOfClaimApplied(Some("CCIS12345678901"),"2038-39", TypeOfClaim.`carry-forward`, mtdLoss = true,
      "2050-51",1000,1000))),
    Some(List(DefaultCarriedForwardLoss("2026-27",1000))))

  val lossClaimsDetailResultOfClaimApplied = LossClaimsDetail(
    Some(List(LossBroughtForward("2054-55",1000,mtdLoss = true))),
    None,
    Some(List(DefaultCarriedForwardLoss("2026-27",1000))))

  val lossClaimsDetailDefaultCarriedForwardLoss = LossClaimsDetail(
    Some(List(LossBroughtForward("2054-55",1000,mtdLoss = true))),
    Some(List(ResultOfClaimApplied(Some("CCIS12345678901"),"2038-39", TypeOfClaim.`carry-forward`, mtdLoss = true, "2050-51",1000,1000))), None)

  val lossClaimsDetailWithMultipleLBF = LossClaimsDetail(Some(List(LossBroughtForward("2054-55",1000,mtdLoss = true),
    LossBroughtForward("2056-57",1000,mtdLoss = true))),
    Some(List(ResultOfClaimApplied(Some("CCIS12345678901"),"2038-39", TypeOfClaim.`carry-forward`, mtdLoss = true,
      "2050-51",1000,1000))),
    Some(List(DefaultCarriedForwardLoss("2026-27",1000))))

  val desJson: JsValue = Json.parse(
    """
      |{
      |	"inputs": {
      |		"lossesBroughtForward": [{
      |			"lossId": "0yriP9QrW2jTa6n",
      |			"incomeSourceId": "AAIS12345678904",
      |			"incomeSourceType": "04",
      |			"submissionTimestamp": "2019-07-13T07:51:43Z",
      |			"lossType": "income",
      |			"taxYearLossIncurred": 2055,
      |			"currentLossValue": 1000.00,
      |			"mtdLoss": true
      |		},
      |  {
      |			"lossId": "123",
      |			"incomeSourceId": "AAIS12345678904",
      |			"incomeSourceType": "02",
      |			"submissionTimestamp": "2019-07-13T07:51:43Z",
      |			"lossType": "income",
      |			"taxYearLossIncurred": 2057,
      |			"currentLossValue": 1000.00,
      |			"mtdLoss": true
      |		}],
      |		"claims": [{
      |			"claimId": "CCIS12345678901",
      |			"originatingClaimId": "LLIS12345678905",
      |			"incomeSourceId": "LLIS12345678905",
      |			"incomeSourceType": "04",
      |			"submissionTimestamp": "2019-08-13T07:51:43Z",
      |			"taxYearClaimMade": 2038,
      |			"claimType": "CF"
      |		}]
      |	},
      |	"calculation": {
      |		"lossesAndClaims": {
      |			"resultOfClaimsApplied": [{
      |				"claimId": "CCIS12345678901",
      |				"originatingClaimId": "000000000000210",
      |				"incomeSourceId": "LLIS12345678911",
      |				"incomeSourceType": "04",
      |				"taxYearClaimMade": 2039,
      |				"claimType": "CF",
      |				"mtdLoss": true,
      |				"taxYearLossIncurred": 2051,
      |				"lossAmountUsed": 1000.00,
      |				"remainingLossValue": 1000.00,
      |				"lossType": "income"
      |			}],
      |			"unclaimedLosses": [{
      |				"incomeSourceId": "LLIS12345678913",
      |				"incomeSourceType": "04",
      |				"taxYearLossIncurred": 2024,
      |				"currentLossValue": 71438847594,
      |				"expires": 2079,
      |				"lossType": "income"
      |			}],
      |			"carriedForwardLosses": [{
      |				"claimId": "CCIS12345678911",
      |				"originatingClaimId": "OCIS12345678901",
      |				"incomeSourceId": "AAIS12345678901",
      |				"incomeSourceType": "04",
      |				"claimType": "CF",
      |				"taxYearClaimMade": 2047,
      |				"taxYearLossIncurred": 2045,
      |				"currentLossValue": 49177438626,
      |				"lossType": "income"
      |			}],
      |			"defaultCarriedForwardLosses": [{
      |				"incomeSourceId": "AAIS12345678912",
      |				"incomeSourceType": "04",
      |				"taxYearLossIncurred": 2027,
      |				"currentLossValue": 1000.00
      |			}],
      |			"claimsNotApplied": [{
      |				"claimId": "CCIS12345678912",
      |				"incomeSourceId": "AAIS12345678901",
      |				"incomeSourceType": "04",
      |				"taxYearClaimMade": 2046,
      |				"claimType": "CF"
      |			}]
      |		}
      |	}
      |}
    """.stripMargin)

  val desJsonWithMultipleLBF: JsValue = Json.parse(
    """
      |{
      |	"inputs": {
      |		"lossesBroughtForward": [{
      |			"lossId": "0yriP9QrW2jTa6n",
      |			"incomeSourceId": "AAIS12345678904",
      |			"incomeSourceType": "04",
      |			"submissionTimestamp": "2019-07-13T07:51:43Z",
      |			"lossType": "income",
      |			"taxYearLossIncurred": 2055,
      |			"currentLossValue": 1000.00,
      |			"mtdLoss": true
      |		},
      |  {
      |			"lossId": "123",
      |			"incomeSourceId": "AAIS12345678904",
      |			"incomeSourceType": "04",
      |			"submissionTimestamp": "2019-07-13T07:51:43Z",
      |			"lossType": "income",
      |			"taxYearLossIncurred": 2057,
      |			"currentLossValue": 1000.00,
      |			"mtdLoss": true
      |		}],
      |		"claims": [{
      |			"claimId": "CCIS12345678901",
      |			"originatingClaimId": "LLIS12345678905",
      |			"incomeSourceId": "LLIS12345678905",
      |			"incomeSourceType": "04",
      |			"submissionTimestamp": "2019-08-13T07:51:43Z",
      |			"taxYearClaimMade": 2038,
      |			"claimType": "CF"
      |		}]
      |	},
      |	"calculation": {
      |		"lossesAndClaims": {
      |			"resultOfClaimsApplied": [{
      |				"claimId": "CCIS12345678901",
      |				"originatingClaimId": "000000000000210",
      |				"incomeSourceId": "LLIS12345678911",
      |				"incomeSourceType": "04",
      |				"taxYearClaimMade": 2039,
      |				"claimType": "CF",
      |				"mtdLoss": true,
      |				"taxYearLossIncurred": 2051,
      |				"lossAmountUsed": 1000.00,
      |				"remainingLossValue": 1000.00,
      |				"lossType": "income"
      |			}],
      |			"unclaimedLosses": [{
      |				"incomeSourceId": "LLIS12345678913",
      |				"incomeSourceType": "04",
      |				"taxYearLossIncurred": 2024,
      |				"currentLossValue": 71438847594,
      |				"expires": 2079,
      |				"lossType": "income"
      |			}],
      |			"carriedForwardLosses": [{
      |				"claimId": "CCIS12345678911",
      |				"originatingClaimId": "OCIS12345678901",
      |				"incomeSourceId": "AAIS12345678901",
      |				"incomeSourceType": "04",
      |				"claimType": "CF",
      |				"taxYearClaimMade": 2047,
      |				"taxYearLossIncurred": 2045,
      |				"currentLossValue": 49177438626,
      |				"lossType": "income"
      |			}],
      |			"defaultCarriedForwardLosses": [{
      |				"incomeSourceId": "AAIS12345678912",
      |				"incomeSourceType": "04",
      |				"taxYearLossIncurred": 2027,
      |				"currentLossValue": 1000.00
      |			}],
      |			"claimsNotApplied": [{
      |				"claimId": "CCIS12345678912",
      |				"incomeSourceId": "AAIS12345678901",
      |				"incomeSourceType": "04",
      |				"taxYearClaimMade": 2046,
      |				"claimType": "CF"
      |			}]
      |		}
      |	}
      |}
    """.stripMargin)

  val desJsonWithoutLossesBroughtForward: JsValue = Json.parse(
    """
      |{
      |	"inputs": {
      |		"claims": [{
      |			"claimId": "CCIS12345678901",
      |			"originatingClaimId": "LLIS12345678905",
      |			"incomeSourceId": "LLIS12345678905",
      |			"incomeSourceType": "04",
      |			"submissionTimestamp": "2019-08-13T07:51:43Z",
      |			"taxYearClaimMade": 2038,
      |			"claimType": "CF"
      |		}]
      |	},
      |	"calculation": {
      |		"lossesAndClaims": {
      |			"resultOfClaimsApplied": [{
      |				"claimId": "CCIS12345678901",
      |				"originatingClaimId": "000000000000210",
      |				"incomeSourceId": "LLIS12345678911",
      |				"incomeSourceType": "04",
      |				"taxYearClaimMade": 2039,
      |				"claimType": "CF",
      |				"mtdLoss": true,
      |				"taxYearLossIncurred": 2051,
      |				"lossAmountUsed": 1000.00,
      |				"remainingLossValue": 1000.00,
      |				"lossType": "income"
      |			}],
      |			"unclaimedLosses": [{
      |				"incomeSourceId": "LLIS12345678913",
      |				"incomeSourceType": "04",
      |				"taxYearLossIncurred": 2024,
      |				"currentLossValue": 71438847594,
      |				"expires": 2079,
      |				"lossType": "income"
      |			}],
      |			"carriedForwardLosses": [{
      |				"claimId": "CCIS12345678911",
      |				"originatingClaimId": "OCIS12345678901",
      |				"incomeSourceId": "AAIS12345678901",
      |				"incomeSourceType": "04",
      |				"claimType": "CF",
      |				"taxYearClaimMade": 2047,
      |				"taxYearLossIncurred": 2045,
      |				"currentLossValue": 49177438626,
      |				"lossType": "income"
      |			}],
      |			"defaultCarriedForwardLosses": [{
      |				"incomeSourceId": "AAIS12345678912",
      |				"incomeSourceType": "04",
      |				"taxYearLossIncurred": 2027,
      |				"currentLossValue": 1000.00
      |			}],
      |			"claimsNotApplied": [{
      |				"claimId": "CCIS12345678912",
      |				"incomeSourceId": "AAIS12345678901",
      |				"incomeSourceType": "04",
      |				"taxYearClaimMade": 2046,
      |				"claimType": "CF"
      |			}]
      |		}
      |	}
      |}
    """.stripMargin)

  val desJsonWithoutResultOfClaims: JsValue = Json.parse(
    """
      |{
      |	"inputs": {
      |		"lossesBroughtForward": [{
      |			"lossId": "0yriP9QrW2jTa6n",
      |			"incomeSourceId": "AAIS12345678904",
      |			"incomeSourceType": "04",
      |			"submissionTimestamp": "2019-07-13T07:51:43Z",
      |			"lossType": "income",
      |			"taxYearLossIncurred": 2055,
      |			"currentLossValue": 1000.00,
      |			"mtdLoss": true
      |		}],
      |		"claims": [{
      |			"claimId": "CCIS12345678901",
      |			"originatingClaimId": "LLIS12345678905",
      |			"incomeSourceId": "LLIS12345678905",
      |			"incomeSourceType": "04",
      |			"submissionTimestamp": "2019-08-13T07:51:43Z",
      |			"taxYearClaimMade": 2038,
      |			"claimType": "CF"
      |		}]
      |	},
      |	"calculation": {
      |		"lossesAndClaims": {
      |			"unclaimedLosses": [{
      |				"incomeSourceId": "LLIS12345678913",
      |				"incomeSourceType": "04",
      |				"taxYearLossIncurred": 2024,
      |				"currentLossValue": 71438847594,
      |				"expires": 2079,
      |				"lossType": "income"
      |			}],
      |			"carriedForwardLosses": [{
      |				"claimId": "CCIS12345678911",
      |				"originatingClaimId": "OCIS12345678901",
      |				"incomeSourceId": "AAIS12345678901",
      |				"incomeSourceType": "04",
      |				"claimType": "CF",
      |				"taxYearClaimMade": 2047,
      |				"taxYearLossIncurred": 2045,
      |				"currentLossValue": 49177438626,
      |				"lossType": "income"
      |			}],
      |			"defaultCarriedForwardLosses": [{
      |				"incomeSourceId": "AAIS12345678912",
      |				"incomeSourceType": "04",
      |				"taxYearLossIncurred": 2027,
      |				"currentLossValue": 1000.00
      |			}],
      |			"claimsNotApplied": [{
      |				"claimId": "CCIS12345678912",
      |				"incomeSourceId": "AAIS12345678901",
      |				"incomeSourceType": "04",
      |				"taxYearClaimMade": 2046,
      |				"claimType": "CF"
      |			}]
      |		}
      |	}
      |}
    """.stripMargin)

  val desJsonWithoutDefaultCarriedForwardLosses: JsValue = Json.parse(
    """
      |{
      |	"inputs": {
      |		"lossesBroughtForward": [{
      |			"lossId": "0yriP9QrW2jTa6n",
      |			"incomeSourceId": "AAIS12345678904",
      |			"incomeSourceType": "04",
      |			"submissionTimestamp": "2019-07-13T07:51:43Z",
      |			"lossType": "income",
      |			"taxYearLossIncurred": 2055,
      |			"currentLossValue": 1000.00,
      |			"mtdLoss": true
      |		}],
      |		"claims": [{
      |			"claimId": "CCIS12345678901",
      |			"originatingClaimId": "LLIS12345678905",
      |			"incomeSourceId": "LLIS12345678905",
      |			"incomeSourceType": "04",
      |			"submissionTimestamp": "2019-08-13T07:51:43Z",
      |			"taxYearClaimMade": 2038,
      |			"claimType": "CF"
      |		}]
      |	},
      |	"calculation": {
      |		"lossesAndClaims": {
      |			"resultOfClaimsApplied": [{
      |				"claimId": "CCIS12345678901",
      |				"originatingClaimId": "000000000000210",
      |				"incomeSourceId": "LLIS12345678911",
      |				"incomeSourceType": "04",
      |				"taxYearClaimMade": 2039,
      |				"claimType": "CF",
      |				"mtdLoss": true,
      |				"taxYearLossIncurred": 2051,
      |				"lossAmountUsed": 1000.00,
      |				"remainingLossValue": 1000.00,
      |				"lossType": "income"
      |			}],
      |			"unclaimedLosses": [{
      |				"incomeSourceId": "LLIS12345678913",
      |				"incomeSourceType": "04",
      |				"taxYearLossIncurred": 2024,
      |				"currentLossValue": 71438847594,
      |				"expires": 2079,
      |				"lossType": "income"
      |			}],
      |			"carriedForwardLosses": [{
      |				"claimId": "CCIS12345678911",
      |				"originatingClaimId": "OCIS12345678901",
      |				"incomeSourceId": "AAIS12345678901",
      |				"incomeSourceType": "04",
      |				"claimType": "CF",
      |				"taxYearClaimMade": 2047,
      |				"taxYearLossIncurred": 2045,
      |				"currentLossValue": 49177438626,
      |				"lossType": "income"
      |			}],
      |			"claimsNotApplied": [{
      |				"claimId": "CCIS12345678912",
      |				"incomeSourceId": "AAIS12345678901",
      |				"incomeSourceType": "04",
      |				"taxYearClaimMade": 2046,
      |				"claimType": "CF"
      |			}]
      |		}
      |	}
      |}
    """.stripMargin)

  val desJsonWithNoFhlDetails: JsValue = Json.parse(
    """
      |{
      |	"inputs": {
      |		"lossesBroughtForward": [{
      |			"lossId": "0yriP9QrW2jTa6n",
      |			"incomeSourceId": "AAIS12345678904",
      |			"incomeSourceType": "01",
      |			"submissionTimestamp": "2019-07-13T07:51:43Z",
      |			"lossType": "income",
      |			"taxYearLossIncurred": 2055,
      |			"currentLossValue": 1000.00,
      |			"mtdLoss": true
      |		}],
      |		"claims": [{
      |			"claimId": "CCIS12345678901",
      |			"originatingClaimId": "LLIS12345678905",
      |			"incomeSourceId": "LLIS12345678905",
      |			"incomeSourceType": "01",
      |			"submissionTimestamp": "2019-08-13T07:51:43Z",
      |			"taxYearClaimMade": 2038,
      |			"claimType": "CF"
      |		}]
      |	},
      |	"calculation": {
      |		"lossesAndClaims": {
      |			"resultOfClaimsApplied": [{
      |				"claimId": "CCIS12345678901",
      |				"originatingClaimId": "000000000000210",
      |				"incomeSourceId": "LLIS12345678911",
      |				"incomeSourceType": "01",
      |				"taxYearClaimMade": 2039,
      |				"claimType": "CF",
      |				"mtdLoss": true,
      |				"taxYearLossIncurred": 2051,
      |				"lossAmountUsed": 1000.00,
      |				"remainingLossValue": 1000.00,
      |				"lossType": "income"
      |			}],
      |			"carriedForwardLosses": [{
      |				"claimId": "CCIS12345678911",
      |				"originatingClaimId": "OCIS12345678901",
      |				"incomeSourceId": "AAIS12345678901",
      |				"incomeSourceType": "01",
      |				"claimType": "CF",
      |				"taxYearClaimMade": 2047,
      |				"taxYearLossIncurred": 2045,
      |				"currentLossValue": 49177438626,
      |				"lossType": "income"
      |			}],
      |			"claimsNotApplied": [{
      |				"claimId": "CCIS12345678912",
      |				"incomeSourceId": "AAIS12345678901",
      |				"incomeSourceType": "01",
      |				"taxYearClaimMade": 2046,
      |				"claimType": "CF"
      |			}]
      |		}
      |	}
      |}
    """.stripMargin)

  val mtdJson: JsValue = Json.parse(
    """
      |{
      |	"lossesBroughtForward": [{
      |		"taxYearLossIncurred": "2054-55",
      |		"currentLossValue": 1000,
      |		"mtdLoss": true
      |	}],
      |	"resultOfClaimsApplied": [{
      |		"claimId": "CCIS12345678901",
      |		"taxYearClaimMade": "2038-39",
      |		"claimType": "carry-forward",
      |		"mtdLoss": true,
      |		"taxYearLossIncurred": "2050-51",
      |		"lossAmountUsed": 1000,
      |		"remainingLossValue": 1000
      |	}],
      |	"carriedForwardLosses": [{
      |		"taxYearLossIncurred": "2026-27",
      |		"currentLossValue": 1000
      |	}]
      |}
    """.stripMargin)
}
