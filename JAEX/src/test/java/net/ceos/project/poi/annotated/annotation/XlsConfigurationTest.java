package net.ceos.project.poi.annotated.annotation;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import net.ceos.project.poi.annotated.bean.ObjectsBuilderTest;
import net.ceos.project.poi.annotated.definition.ExtensionFileType;

public class XlsConfigurationTest extends TestCase {
	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public XlsConfigurationTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(XlsConfigurationTest.class);
	}

	/**
	 * Test default configuration.
	 */
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
