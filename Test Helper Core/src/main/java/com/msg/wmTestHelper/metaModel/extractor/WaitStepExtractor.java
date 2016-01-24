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
import com.msg.wmTestHelper.util.ProprietaryHelper;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * WaitStepExtractor
 *
 * @author Dennis Rippinger
 */
@Slf4j
public class WaitStepExtractor extends AbstractExtractor {


	@Override
	public List<ProcessStep> extractSteps(Document document) {
		List<Element> possibleWaitSteps = document
				.selectNodes("//idatacodable[@javaclass='com.wm.app.prt.model.SubprocessStepDef']");
		List<ProcessStep> results = new ArrayList<>(possibleWaitSteps.size());

		for (Element possibleWaitStep : possibleWaitSteps) {

			Element publishedDoc = (Element) possibleWaitStep
					.selectSingleNode(".//array/idatacodable[@javaclass='com.wm.app.prt.model.PRTPublishedDocOutputDef']");

			if (isWaitStep(publishedDoc)) {
				ProcessStep processStep = new ProcessStep();

				processStep
						.stepLabel(extractWaitStepLabel(possibleWaitStep))
						.message(extractStartMessage(possibleWaitStep))
						.typeOfStep(StepType.WAIT_STEP);

				results.add(processStep);
			}

		}

		return results;
	}

	private Publishable extractStartMessage(Element startMessage) {
		Publishable result = new Publishable();

		Element elementPublishedDocument = (Element) startMessage.selectSingleNode(".//idatacodable[@javaclass='com.wm.app.prt.model.PRTPublishedDocOutputDef']");

		result.documentName(extractPlainValue(elementPublishedDocument, "docName"));
		result.documentType(extractPlainValue(elementPublishedDocument, "docType"));

		return result;
	}

	private String extractWaitStepLabel(@NonNull Element possibleWaitStep) {
		String stepLabel = extractStepLabel(possibleWaitStep);

		return stepLabel.replace("PRESUB_", "");
	}

	private boolean isWaitStep(Element publishedDoc) {

		if (publishedDoc == null) {
			return false;
		}

		String docName = extractPlainValue(publishedDoc, "docName");

		log.info(docName);

		return StringUtils.isNotEmpty(docName) && (
				StringUtils.equals(ProprietaryHelper.getConfig("wait.1.startmessage"), docName) ||
				StringUtils.equals(ProprietaryHelper.getConfig("wait.2.startmessage"), docName) ||
				StringUtils.equals(ProprietaryHelper.getConfig("wait.3.startmessage"), docName)
		);
	}
}
