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
