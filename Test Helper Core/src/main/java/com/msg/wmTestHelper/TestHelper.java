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
package com.msg.wmTestHelper;

import com.msg.wmTestHelper.codeModel.CodeModelCreator;
import com.msg.wmTestHelper.file.FileSearch;
import com.msg.wmTestHelper.metaModel.MetaModelCreator;
import com.msg.wmTestHelper.pojo.GeneratorParameter;
import com.msg.wmTestHelper.pojo.ProcessFile;
import com.msg.wmTestHelper.pojo.ProcessModel;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.List;

/**
 * TestHelper
 *
 * @author Dennis Rippinger
 */
@Slf4j
public class TestHelper {

	private FileSearch fileSearch = new FileSearch();

	private MetaModelCreator metaModelCreator = new MetaModelCreator();

	private CodeModelCreator codeModelCreator = new CodeModelCreator();

	/**
	 * Generates a set of classes depending on the WM input files.
	 *
	 * @param parameter the parameter form CLI or the Maven POJO
	 */
	public void generateTestHelper(GeneratorParameter parameter) {
		Collection<ProcessFile> processFiles = fileSearch.findLatestFiles(parameter.wmprtPath());

		log.info("Found {} relevant process files", processFiles.size());

		List<ProcessModel> metaModels = metaModelCreator.createMetaModel(processFiles);

		log.info("Created {} meta model entries", metaModels.size());

		codeModelCreator.createCodeMode(metaModels, parameter);
	}
}
