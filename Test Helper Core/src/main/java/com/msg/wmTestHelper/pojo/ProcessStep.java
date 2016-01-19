package com.msg.wmTestHelper.pojo;

import lombok.Data;

/**
 * ProcessStep
 *
 * @author Dennis Rippinger
 */
@Data
public class ProcessStep {

	/**
	 * The model type steps use different class names for each type.
	 * We map the full qualified name.
	 */
	private String typeOfClass;

	/**
	 * The label of the processes step 'as is'.
	 */
	private String stepLabel;

	/**
	 * Some processes steps can receive a message, is not always given.
	 */
	private Publishable message;

	/**
	 * @return true if a publishable message is given.
	 */
	public boolean isPublishable() {
		return message != null;
	}
}
