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
import com.sun.codemodel.internal.JCodeModel;
import com.sun.codemodel.internal.JDefinedClass;
import com.sun.codemodel.internal.JExpr;
import com.sun.codemodel.internal.JMod;
import lombok.extern.slf4j.Slf4j;

/**
 * VariablePart
 *
 * @author Dennis Rippinger
 */
@Slf4j
public class VariablePart extends AbstractPartCreator {

	@Override
	public void buildPart(JCodeModel codeModel, JDefinedClass currentClass, ProcessModel processModel) {

		currentClass.field(JMod.PUBLIC | JMod.STATIC | JMod.FINAL, String.class, "PROCESS_NAME", JExpr.lit(processModel.modelName()));
	}
}
