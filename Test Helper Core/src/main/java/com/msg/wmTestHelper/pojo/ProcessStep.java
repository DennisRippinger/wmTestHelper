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
package com.msg.wmTestHelper.pojo;

import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

/**
 * ProcessStep
 *
 * @author Dennis Rippinger
 */
@Data
@Accessors(fluent = true, chain = true)
public class ProcessStep {

	/**
	 * The model type steps.
	 */
	private StepType typeOfStep;

	/**
	 * The label of the processes step 'as is'.
	 */
	private String stepLabel;

	/**
	 * Some processes steps can receive a message, is not always given.
	 */
	private Publishable message;

	/**
	 * @return true if a publishable message is given.
	 */
	public boolean isPublishable() {
		return message != null;
	}

	/**
	 * @return the first literal of the step name, per definition the technical name.
	 */
	public String cropStepLabel() {
		if (StringUtils.isNotEmpty(stepLabel)) {
			return stepLabel.split(" ")[0];
		}

		return StringUtils.EMPTY;
	}
}
