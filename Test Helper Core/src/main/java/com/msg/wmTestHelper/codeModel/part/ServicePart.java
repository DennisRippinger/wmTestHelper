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
package com.msg.wmTestHelper.codeModel.part;

import com.msg.wmTestHelper.codeModel.AbstractPartCreator;
import com.msg.wmTestHelper.pojo.ProcessModel;
import com.msg.wmTestHelper.pojo.ProcessStep;
import com.msg.wmTestHelper.pojo.StepType;
import com.msg.wmTestHelper.util.ProprietaryHelper;
import com.sun.codemodel.internal.*;
import lombok.extern.slf4j.Slf4j;

/**
 * ServicePart
 *
 * @author Dennis Rippinger
 */
@Slf4j
public class ServicePart extends AbstractPartCreator {

	@Override
	public void buildPart(JCodeModel codeModel, JDefinedClass currentClass, ProcessModel processModel) {

		processModel
			.processSteps()
			.stream()
			.filter(processStep -> processStep.typeOfStep().equals(StepType.SERVICE))
			.forEach(processStep -> createServiceFactory(codeModel, currentClass, processStep, processModel));
	}

	private void createServiceFactory(JCodeModel codeModel, JDefinedClass currentClass, ProcessStep processStep, ProcessModel processModel) {
		JClass processStepBuilder = codeModel.ref(ProprietaryHelper.getConfig("class.processStepBuilder"));

		JMethod method = currentClass.method(JMod.PUBLIC | JMod.STATIC, processStepBuilder, "createService" + processStep.cropStepLabel());
		method.body()._return(JExpr._new(processStepBuilder).arg(processStep.stepLabel()).invoke("processModelName").arg(processModel.modelName()));
	}
}
