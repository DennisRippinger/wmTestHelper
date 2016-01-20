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
package com.msg.wmTestHelper.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * ProcessModel
 *
 * @author Dennis Rippinger
 */
@Data
@NoArgsConstructor
public class ProcessModel {

	/**
	 * Creates a new ProcessModel instance with the model name and version from the ProcessFile.
	 *
	 * @param processFile
	 */
	public ProcessModel(ProcessFile processFile) {
		modelName = processFile.name();
		modelVersion = processFile.version();
	}

	private String modelName;

	private int modelVersion;

	private List<ProcessStep> processSteps = new ArrayList<>();

	/**
	 * Fluent add method for process steps.
	 *
	 * @return the current instance of this.
	 */
	public ProcessModel addProcessSteps(List<ProcessStep> processSteps) {
		processSteps.addAll(processSteps);

		return this;
	}

}
