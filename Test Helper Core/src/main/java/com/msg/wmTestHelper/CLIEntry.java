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
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.cli.*;

/**
 * CLIEntry
 *
 * @author Dennis Rippinger
 */
@Slf4j
public class CLIEntry {

	public static void main(String... args) {

		try {
			CommandLineParser parser = new DefaultParser();
			CommandLine cmd = parser.parse(createOptions(), args);

			if (cmd.hasOption("h")) {
				printHelp();
			} else {
				GeneratorParameter parameter = extractParameter(cmd);
				if (parameter.hasSufficientData()) {
					TestHelper testHelper = new TestHelper();
					testHelper.generateTestHelper(parameter);
				} else {
					log.error("Parameters are not sufficient, exiting. " +
						"WMPRT folder, output folder and namespace must be set.");
				}
			}

		} catch (ParseException e) {
			log.error(e.toString());
		}
	}

	private static GeneratorParameter extractParameter(CommandLine cmd) {
		GeneratorParameter parameter = new GeneratorParameter();
		parameter.wmprtPath(cmd.getOptionValue("w"));
		parameter.outputPath(cmd.getOptionValue("o", "")); // Default is current folder.
		parameter.baseNamespace(cmd.getOptionValue("n", "com.msg"));
		ProprietaryHelper.setAdditionalFilterString(cmd.getOptionValue("f", ""));

		return parameter;
	}

	private static void printHelp() {
		HelpFormatter helpFormatter = new HelpFormatter();
		helpFormatter.printHelp("testHelper", createOptions());
	}

	private static Options createOptions() {
		Options options = new Options();
		options.addOption("h", "help", false, "Prints this message");
		options.addOption("w", "wmprt", true, "Path to WMPRT folder");
		options.addOption("o", "output", true, "Output Folder for generated code");
		options.addOption("n", "namespace", true, "The desired namespace for the generated code");
		options.addOption("f", "filter", true, "Additional filter criteria on Process Definition files, separated by comma");

		return options;
	}
}
