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
package com.msg.wmTestHelper.file;

import com.msg.wmTestHelper.pojo.ProcessFile;
import com.msg.wmTestHelper.util.ProprietaryHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * NameExtractor
 *
 * @author Dennis Rippinger
 */
@Slf4j
public class NameExtractor {

	private static List<String> filterStrings;

	static {
		filterStrings = Arrays.asList(ProprietaryHelper.getConfig("files.filter").split(","));
		if (StringUtils.isNotEmpty(ProprietaryHelper.getAdditionalFilterString())) {
			filterStrings.addAll(Arrays.asList(ProprietaryHelper.getAdditionalFilterString().split(",")));
		}
	}

	public static ProcessFile extractProcessFile(File processFile) {
		ProcessFile result = new ProcessFile();

		String[] varNameParts = processFile.getName().split("[.]");

		Validate.validIndex(varNameParts, 2);

		if (isFiltered(varNameParts[2])) {
			return null;
		}

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

	private static boolean isFiltered(String mainPart) {
		for (String filter : filterStrings) {
			if (mainPart.contains(filter)) {
				return true;
			}
		}
		return false;
	}
}
