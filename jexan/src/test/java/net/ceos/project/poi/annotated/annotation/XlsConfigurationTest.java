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
