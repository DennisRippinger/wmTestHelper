package com.msg.wmTestHelper.codeModel.part;

import com.msg.wmTestHelper.codeModel.AbstractPartCreator;
import com.msg.wmTestHelper.pojo.ProcessModel;
import com.msg.wmTestHelper.pojo.ProcessStep;
import com.msg.wmTestHelper.pojo.StepType;
import com.msg.wmTestHelper.util.ProprietaryHelper;
import com.sun.codemodel.internal.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

/**
 * StartMessagePart
 *
 * @author Dennis Rippinger
 */
@Slf4j
public class StartMessagePart extends AbstractPartCreator {

	@Override
	public void buildPart(JCodeModel codeModel, JDefinedClass currentClass, ProcessModel processModel) {

		Optional<ProcessStep> startMessageStep = processModel.getProcessSteps().stream().filter(processStep -> processStep.typeOfStep().equals(StepType.START_MESSAGE)).findFirst();
		if (startMessageStep.isPresent()) {
			ProcessStep startMessage = startMessageStep.get();

			JClass processMessageBuilder = codeModel.ref(ProprietaryHelper.getConfig("class.processMessageBuilder"));

			JMethod method = currentClass.method(JMod.PUBLIC | JMod.STATIC, processMessageBuilder, "createStartMessage");
			method.body()._return(JExpr._new(processMessageBuilder).arg(startMessage.message().documentType()));
		}
	}
}
