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

package v1.fixtures

import v1.models.response.common.Message

object Fixtures {
  val err1 = Message("err1", "text1")
  val err2 = Message("err2", "text2")
  val info1 = Message("info1", "text1")
  val info2 = Message("info2", "text2")
  val warn1 = Message("warn1", "text1")
  val warn2 = Message("warn2", "text2")

}
