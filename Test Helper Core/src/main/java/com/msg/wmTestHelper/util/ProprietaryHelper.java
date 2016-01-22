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
package com.msg.wmTestHelper.util;

import com.msg.wmTestHelper.exception.TestHelperException;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang3.StringUtils;

/**
 * ProprietaryHelper
 *
 * @author Dennis Rippinger
 */
@Slf4j
public final class ProprietaryHelper {

	private static CompositeConfiguration config = new CompositeConfiguration();

	static {
		try {
			config.addConfiguration(new PropertiesConfiguration("config.properties"));

			log.info("Loaded {} config entries", config.getNumberOfConfigurations());
		} catch (ConfigurationException e) {
			throw new TestHelperException("Could not find config file", e);
		}
	}

	public static String getConfig(@NonNull String configName) {
		return config.getString(configName, StringUtils.EMPTY);
	}

}
