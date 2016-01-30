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
package com.msg.wmTestHelper;

import com.msg.wmTestHelper.pojo.GeneratorParameter;
import com.msg.wmTestHelper.util.ProprietaryHelper;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

/**
 * Goal to use test helper classes
 */
@Mojo(name = "createFactories")
public class CreateFactoriesMojo extends AbstractMojo {

	@Parameter(property = "createFactories.wmprtPath", defaultValue = "", required = true)
	private String wmprtPath;

	@Parameter(property = "createFactories.outputPath", defaultValue = "")
	private String outputPath;

	@Parameter(property = "createFactories.baseNamespace", defaultValue = "", required = true)
	private String baseNamespace;

	@Parameter(property = "createFactories.filterString", defaultValue = "", required = false)
	private String filterStrings;

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {

		Banner.printBanner();

		ProprietaryHelper.setAdditionalFilterString(filterStrings);

		GeneratorParameter parameter = new GeneratorParameter()
			.wmprtPath(wmprtPath)
			.outputPath(outputPath)
			.baseNamespace(baseNamespace);

		TestHelper testHelper = new TestHelper();
		testHelper.generateTestHelper(parameter);

	}
}
