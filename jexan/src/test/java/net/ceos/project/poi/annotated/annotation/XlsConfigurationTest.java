/**
 * Copyright 2016 Carlos CRISTO ABREU
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
package net.ceos.project.poi.annotated.annotation;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import net.ceos.project.poi.annotated.bean.XMenFactory;
import net.ceos.project.poi.annotated.definition.ExtensionFileType;

/**
 * Test the annotation {@link XlsConfiguration}
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public class XlsConfigurationTest {

	/**
	 * Test default configuration.
	 */
	@Test
	public void testDefaultConfiguration() {
		Class<XMenFactory.DefaultConfig> o = XMenFactory.DefaultConfig.class;

		// Process @XlsConfiguration
		if (o.isAnnotationPresent(XlsConfiguration.class)) {

			XlsConfiguration xlsConfig = (XlsConfiguration) o.getAnnotation(XlsConfiguration.class);

			// add here the annotations attributes
			assertEquals(xlsConfig.extensionFile(), ExtensionFileType.XLS);
		}
	}

	/**
	 * Test name file attribute.
	 */
	@Test
	public void testNameFileAttribute() {
		Class<XMenFactory.DefaultConfig> o = XMenFactory.DefaultConfig.class;

		// Process @XlsConfiguration
		if (o.isAnnotationPresent(XlsConfiguration.class)) {

			XlsConfiguration xlsConfig = (XlsConfiguration) o.getAnnotation(XlsConfiguration.class);

			// add here the annotations attributes
			assertEquals(xlsConfig.nameFile(), "DefaultConfigurationSample");
		}
	}

	/**
	 * Test name file attribute.
	 */
	@Test
	public void testExtensionFileAttribute() {
		Class<XMenFactory.Cyclops> o = XMenFactory.Cyclops.class;

		// Process @XlsConfiguration
		if (o.isAnnotationPresent(XlsConfiguration.class)) {

			XlsConfiguration xlsConfig = (XlsConfiguration) o.getAnnotation(XlsConfiguration.class);

			// add here the annotations attributes
			assertEquals(xlsConfig.extensionFile(), ExtensionFileType.XLSX);
		}
	}
}
