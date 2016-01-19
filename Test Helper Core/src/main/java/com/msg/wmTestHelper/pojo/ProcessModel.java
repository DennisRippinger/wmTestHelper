package com.msg.wmTestHelper.pojo;

import lombok.Data;

import java.util.List;

/**
 * ProcessModel
 *
 * @author Dennis Rippinger
 */
@Data
public class ProcessModel {

	private String modelName;

	private String modelVersion;

	private List<ProcessStep> processSteps;

}
