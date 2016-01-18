package com.msg.wmTestHelper.pojo;

import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

/**
 * Contains parameter for the code generator.
 *
 * @author Dennis Rippinger
 */
@Data
@Accessors(fluent = true, chain = true)
public class GeneratorParameter {

	private String wmprtPath;

	private String outputPath;

	private String baseNamespace;

	public boolean hasSufficientData() {
		return StringUtils.isNotEmpty(wmprtPath) //
				&& StringUtils.isNotEmpty(outputPath) //
				&& StringUtils.isNotEmpty(baseNamespace);
	}

}
