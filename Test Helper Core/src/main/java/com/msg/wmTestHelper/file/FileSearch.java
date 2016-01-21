/**
 * Copyright (C) 2016 Dennis Rippinger (dennis.rippinger@msg-systems.com)
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.msg.wmTestHelper.file;

import com.msg.wmTestHelper.exception.TestHelperException;
import com.msg.wmTestHelper.pojo.ProcessFile;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class FileSearch {


	public Collection<ProcessFile> findLatestFiles(@NonNull File directory) {

		if (directory.isDirectory()) {
			return search(directory);
		} else {
			throw new TestHelperException(directory.getAbsoluteFile() + " is not a directory!");
		}
	}

	public Collection<ProcessFile> findLatestFiles(@NonNull String directory) {
		File directoryFile = new File(directory);

		return findLatestFiles(directoryFile);
	}

	private Collection<ProcessFile> search(@NonNull File file) {

		Map<String, ProcessFile> tempResult = new HashMap<>();

		for (File configFile : file.listFiles()) {
			ProcessFile processFile = NameExtractor.extractProcessFile(configFile);
			evaluateOldestVersion(processFile, tempResult);
		}

		return tempResult.values();
	}

	private void evaluateOldestVersion(ProcessFile processFile, Map<String, ProcessFile> tempResult) {
		ProcessFile result = tempResult.get(processFile.name());
		if (result == null) {
			tempResult.put(processFile.name(), processFile);
		} else if (result.version() < processFile.version()) {
			tempResult.put(processFile.name(), processFile);
		}
	}

}