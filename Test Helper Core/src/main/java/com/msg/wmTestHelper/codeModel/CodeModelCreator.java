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
package com.msg.wmTestHelper.codeModel;

import com.msg.wmTestHelper.exception.TestHelperException;
import com.msg.wmTestHelper.pojo.GeneratorParameter;
import com.msg.wmTestHelper.pojo.ProcessModel;
import com.sun.codemodel.internal.JClassAlreadyExistsException;
import com.sun.codemodel.internal.JCodeModel;
import com.sun.codemodel.internal.JDefinedClass;
import com.sun.codemodel.internal.JPackage;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * CodeModelCreator
 *
 * @author Dennis Rippinger
 */
@Slf4j
public class CodeModelCreator {

	private Reflections reflections = new Reflections(getClass().getPackage().getName());

	private List<AbstractPartCreator> partCreators;

	public CodeModelCreator() {
		initExtractors();
	}

	public void createCodeMode(List<ProcessModel> processModels, GeneratorParameter parameter) {
		log.info("Creating code model with namespace '{}", parameter.baseNamespace());
		JCodeModel codeModel = new JCodeModel();

		for (ProcessModel processModel : processModels) {

			createSingleCodeFile(codeModel, processModel, parameter);
		}

		generateClasses(codeModel, parameter);
	}

	private void createSingleCodeFile(JCodeModel codeModel, ProcessModel processModel, GeneratorParameter parameter) {

		try {
			JPackage jPackage = codeModel._package(parameter.baseNamespace());
			JDefinedClass currentClass = jPackage._class(processModel.cropModelName());

			for (AbstractPartCreator partCreator : partCreators) {
				partCreator.buildPart(codeModel, currentClass, processModel);
			}
		} catch (JClassAlreadyExistsException e) {
			log.info("Could not create model for {}, it already exist", processModel.modelName());
		}
	}


	private void generateClasses(JCodeModel codeModel, GeneratorParameter parameter) {
		log.info("Created code model, storing classes to '{}'", parameter.outputPath());

		try {
			File file = new File(parameter.outputPath());
			file.mkdirs();
			codeModel.build(file);
		} catch (IOException e) {
			throw new TestHelperException("Could not write code mode", e);
		}
	}

	private void initExtractors() {
		Set<Class<? extends AbstractPartCreator>> partCreatorClasses = reflections.getSubTypesOf(AbstractPartCreator.class);
		partCreators = new ArrayList<>(partCreatorClasses.size());

		try {
			for (Class<? extends AbstractPartCreator> extractor : partCreatorClasses) {
				partCreators.add(extractor.newInstance());
			}

		} catch (ReflectiveOperationException e) {
			new TestHelperException("Could not instantiate Part creator class", e);
		}

	}
}
