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

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Element;
import org.dom4j.Node;

/**
 * AbstractExtractor
 *
 * @author Dennis Rippinger
 */
@Slf4j
public abstract class AbstractExtractor {

	protected String extractStepLabel(Element element) {
		Node nodeStepLabel = element.selectSingleNode(".//value[name='stepLabel']");

		if (nodeStepLabel != null && StringUtils.isNotEmpty(nodeStepLabel.getText())) {
			return nodeStepLabel.getText();
		}

		log.warn("Step label not given for element {}", element.getPath());
		return StringUtils.EMPTY;
	}


}
