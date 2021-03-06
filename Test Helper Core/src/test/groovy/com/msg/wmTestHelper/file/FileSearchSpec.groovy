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

import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Specification

/**
 * FileSearchSpec
 *
 * @author Dennis Rippinger
 */
class FileSearchSpec extends Specification {

	@Rule
	TemporaryFolder temporaryFolder

	def "FileSearch is provided with folder"() {
		setup: "Folder with two demo files is given"
		temporaryFolder.newFile("A.B.C2.Default.xml")
		temporaryFolder.newFile("A.Hello.B2.Default.xml")

		def FileSearch search = new FileSearch();

		when: "FileSearch is provided with the folder"
		def files = search.findLatestFiles(temporaryFolder.root)

		then: "Returned collection should contain two entries"
		files.size() == 2

		and: "Version should be 2"
		files.getAt(1).version() == 2

	}
}
