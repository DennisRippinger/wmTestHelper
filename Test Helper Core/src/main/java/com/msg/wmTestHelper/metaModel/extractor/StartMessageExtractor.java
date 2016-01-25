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
package com.msg.wmTestHelper.metaModel.extractor;

import com.msg.wmTestHelper.metaModel.AbstractExtractor;
import com.msg.wmTestHelper.pojo.ProcessStep;
import com.msg.wmTestHelper.pojo.Publishable;
import com.msg.wmTestHelper.pojo.StepType;
import lombok.NonNull;
import org.dom4j.Document;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * StartMessageExtractor
 *
 * @author Dennis Rippinger
 */
public class StartMessageExtractor extends AbstractExtractor {

	@Override
	public List<ProcessStep> extractSteps(@NonNull Document document) {

		List<Element> startMessages = document.selectNodes("//idatacodable[@javaclass='com.wm.app.prt.model.StartMessageEvent']");
		List<ProcessStep> results = new ArrayList<>(startMessages.size());

		for (Element startMessage : startMessages) {
			ProcessStep processStep = new ProcessStep();

			processStep
				.stepLabel(extractStepLabel(startMessage))
				.typeOfStep(StepType.START_MESSAGE)
				.message(extractStartMessage(startMessage));

			results.add(processStep);
		}


		return results;
	}

	private Publishable extractStartMessage(Element startMessage) {
		Publishable result = new Publishable();

		Element elementPublishedDocument = (Element) startMessage.selectSingleNode(".//idatacodable[@javaclass='com.wm.app.prt.model.PRTPublishedDocInputDef']");

		result.documentName(extractPlainValue(elementPublishedDocument, "docName"));
		result.documentType(extractPlainValue(elementPublishedDocument, "docType"));

		return result;
	}
}
