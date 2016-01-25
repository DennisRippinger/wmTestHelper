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
package com.msg.wmTestHelper.file

import spock.lang.Specification

/**
 * NameExtractorSpec
 *
 * @author Dennis Rippinger
 */
class NameExtractorSpec extends Specification {

	def "Name Extraction normal examples"() {

		expect:
		def processFile = NameExtractor.extractProcessFile(file);
		processFile.name().equals(name)
		processFile.version() == version
		processFile.fileReference().equals(file)

		where:
		file                                | version | name
		new File("/A.B.C2.Default.xml")     | 2       | "B"
		new File("/A.Hello.C3.Default.xml") | 3       | "Hello"

	}

	def "Wrong filename provided"() {

		setup: "Read in a wrong file"
		def wrongFile = new File("/A.xml")

		when: "Name extractor is called"
		NameExtractor.extractProcessFile(wrongFile)

		then: "Exception is thrown"
		thrown(IndexOutOfBoundsException)

	}

}
