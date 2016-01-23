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

import com.msg.wmTestHelper.pojo.ProcessStep;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;

import java.util.List;

/**
 * AbstractExtractor
 *
 * @author Dennis Rippinger
 */
@Slf4j
public abstract class AbstractExtractor {

	public abstract List<ProcessStep> extractSteps(Document document);

	/**
	 * Extracts a <code><value></code> element where the attribute name is like elementName.
	 *
	 * @param element     the XML element.
	 * @param elementName the name attribute value.
	 * @return
	 */
	protected String extractPlainValue(Element element, String elementName) {
		Node nodeStepLabel = element.selectSingleNode(".//value[@name='" + elementName + "']");

		if (nodeStepLabel != null && StringUtils.isNotEmpty(nodeStepLabel.getText())) {
			return nodeStepLabel.getText();
		}

		log.warn("Value '{}' not given for element {} {}", elementName, element.getUniquePath(), element.getDocument().getName());
		return StringUtils.EMPTY;
	}

	/**
	 * Extract the Step Label value.
	 *
	 * @param element the XML element contaning a <code>stepLabel</code>
	 * @return the string value.
	 */
	protected String extractStepLabel(Element element) {
		return extractPlainValue(element, "stepLabel");
	}


}
