package com.msg.wmTestHelper

import com.msg.wmTestHelper.file.NameExtractor
import spock.lang.Specification

/**
 * NameExtractorSpec
 *
 * @author Dennis Rippinger
 */
class NameExtractorSpec extends Specification {

    def "Name Extraction"() {

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

}
