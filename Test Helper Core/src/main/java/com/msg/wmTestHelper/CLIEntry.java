package com.msg.wmTestHelper;

import com.msg.wmTestHelper.pojo.GeneratorParameter;
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

		return options;
	}
}
