package com.msg.wmTestHelper.file;

import com.msg.wmTestHelper.exception.TestHelperException;
import com.msg.wmTestHelper.pojo.ProcessFile;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class FileSearch {


	public Collection<ProcessFile> findLatestFiles(File directory) {

		if (directory.isDirectory()) {
			return search(directory);
		} else {
			throw new TestHelperException(directory.getAbsoluteFile() + " is not a directory!");
		}
	}

	public Collection<ProcessFile> findLatestFiles(String directory) {
		File directoryFile = new File(directory);

		return findLatestFiles(directoryFile);
	}

	private Collection<ProcessFile> search(File file) {

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