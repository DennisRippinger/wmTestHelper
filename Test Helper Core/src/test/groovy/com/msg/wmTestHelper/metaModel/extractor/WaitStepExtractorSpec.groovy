/**
 * Copyright (C) 2016 Dennis Rippinger (dennis.rippinger@msg-systems.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.msg.wmTestHelper.metaModel.extractor

import com.msg.wmTestHelper.util.XmlUtil
import spock.lang.Specification

/**
 * StartMessageExtractorSpec
 *
 * @author Dennis Rippinger
 */
class WaitStepExtractorSpec extends Specification {

    def "Task conversion to meta model"() {

        setup: "Init Document and mock ProprietaryHelper"
        def processTestDocument = XmlUtil.parse(this.getClass().getResourceAsStream("/testProcessFile.xml"))
        assert processTestDocument != null: "Process test file missing"

        WaitStepExtractor extractor = new WaitStepExtractor()

        when: "Create Meta Model"
        def processSteps = extractor.extractSteps(processTestDocument)

        then: "Wait SubProcesses are identified"
        assert processSteps.size().equals(1)
        assert processSteps.get(0).stepLabel().equals("Start Message")
    }
}
