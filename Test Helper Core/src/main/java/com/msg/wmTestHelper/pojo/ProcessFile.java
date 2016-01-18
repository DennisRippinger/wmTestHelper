package com.msg.wmTestHelper.pojo;

/**
 * ProcessFile
 *
 * @author Dennis Rippinger
 * @since 18.01.16
 */

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.File;

@Data
@Accessors(fluent = true, chain = true)
public class ProcessFile {

	private String name;

	private File fileReference;

	private int version;
}
