package net.ceos.project.poi.annotated.annotation;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import net.ceos.project.poi.annotated.bean.ObjectsBuilderTest;
import net.ceos.project.poi.annotated.definition.ExtensionFileType;

public class XlsConfigurationTest {

	/**
	 * Test default configuration.
	 */
	@Test
	public void testDefaultConfiguration() {
		Class<ObjectsBuilderTest.ObjectWithDefaultConfig> o = ObjectsBuilderTest.ObjectWithDefaultConfig.class;

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
		Class<ObjectsBuilderTest.ObjectWithDefaultConfig> o = ObjectsBuilderTest.ObjectWithDefaultConfig.class;

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
		Class<ObjectsBuilderTest.Cyclops> o = ObjectsBuilderTest.Cyclops.class;

		// Process @XlsConfiguration
		if (o.isAnnotationPresent(XlsConfiguration.class)) {

			XlsConfiguration xlsConfig = (XlsConfiguration) o.getAnnotation(XlsConfiguration.class);

			// add here the annotations attributes
			assertEquals(xlsConfig.extensionFile(), ExtensionFileType.XLSX);
		}
	}
}
