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
package com.msg.wmTestHelper.metaModel;

import com.msg.wmTestHelper.exception.TestHelperException;
import com.msg.wmTestHelper.pojo.ProcessFile;
import com.msg.wmTestHelper.pojo.ProcessModel;
import com.msg.wmTestHelper.util.XmlUtil;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * MetaModelCreator
 *
 * @author Dennis Rippinger
 */
@Slf4j
public class MetaModelCreator {

	private Reflections reflections = new Reflections(getClass().getPackage().getName());

	private List<AbstractExtractor> extractors;

	public MetaModelCreator() {
		initExtractors();
	}

	public List<ProcessModel> createMetaModel(Collection<ProcessFile> fileList) {

		List<ProcessModel> result = new ArrayList<>(fileList.size());

		for (ProcessFile processFile : fileList) {
			result.add(createSingleMetaModel(processFile));
		}

		return result;
	}

	private ProcessModel createSingleMetaModel(ProcessFile processFile) {
		ProcessModel processModel = new ProcessModel(processFile);
		Document document = XmlUtil.parse(processFile.fileReference());

		for (AbstractExtractor extractor : extractors) {
			processModel
					.addProcessSteps(extractor.extractSteps(document));
		}

		return processModel;
	}

	private void initExtractors() {
		Set<Class<? extends AbstractExtractor>> extractorClasses = reflections.getSubTypesOf(AbstractExtractor.class);
		extractors = new ArrayList<>(extractorClasses.size());

		try {
			for (Class<? extends AbstractExtractor> extractor : extractorClasses) {
				extractors.add(extractor.newInstance());
			}

		} catch (ReflectiveOperationException e) {
			new TestHelperException("Could not instantiate Extractor class", e);
		}

	}
}
