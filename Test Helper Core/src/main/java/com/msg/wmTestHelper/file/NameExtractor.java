package com.msg.wmTestHelper.file;

import com.msg.wmTestHelper.pojo.ProcessFile;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Validate;

import java.io.File;

/**
 * NameExtractor
 *
 * @author Dennis Rippinger
 */
@Slf4j
public class NameExtractor {

	public static ProcessFile extractProcessFile(File processFile) {
		ProcessFile result = new ProcessFile();

		String[] varNameParts = processFile.getName().split("[.]");

		Validate.validIndex(varNameParts, 2);

		result
				.fileReference(processFile)
				.version(getVersion(varNameParts))
				.name(getName(varNameParts));

		return result;
	}

	private static String getName(String[] input) {
		return input[1];
	}

	private static int getVersion(String[] input) {
		String mainPart = input[2];

		int i = mainPart.length();
		while (i > 0 && Character.isDigit(mainPart.charAt(i - 1))) {
			i--;
		}
		return Integer.parseInt(mainPart.substring(i));
	}
}
