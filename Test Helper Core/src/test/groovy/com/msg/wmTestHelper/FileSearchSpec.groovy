package com.msg.wmTestHelper

import com.msg.wmTestHelper.file.FileSearch
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
