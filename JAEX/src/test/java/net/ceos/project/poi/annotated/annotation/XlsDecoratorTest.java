package net.ceos.project.poi.annotated.annotation;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import net.ceos.project.poi.annotated.bean.ObjectsBuilderTest;

public class XlsDecoratorTest {

	/**
	 * Test default configuration.
	 */
	@Test
	public void testDefaultConfiguration() {
		Class<ObjectsBuilderTest.ObjectWithDefaultConfig> o = ObjectsBuilderTest.ObjectWithDefaultConfig.class;

		// Process @XlsDecorator
		if (o.isAnnotationPresent(XlsDecorator.class)) {

			XlsDecorator xlsDecorator = (XlsDecorator) o.getAnnotation(XlsDecorator.class);

			// add here the annotations attributes
			assertEquals(xlsDecorator.fontName(), "Arial");
			assertEquals(xlsDecorator.fontSize(), 10);
			assertEquals(xlsDecorator.fontColor(), 0);
			assertEquals(xlsDecorator.fontBold(), false);
			assertEquals(xlsDecorator.fontItalic(), false);
			assertEquals(xlsDecorator.fontUnderline(), 0);
			assertEquals(xlsDecorator.alignment(), 0);
			assertEquals(xlsDecorator.verticalAlignment(), 0);
			assertEquals(xlsDecorator.border(), 0);
			assertEquals(xlsDecorator.borderLeft(), 0);
			assertEquals(xlsDecorator.borderRight(), 0);
			assertEquals(xlsDecorator.borderTop(), 0);
			assertEquals(xlsDecorator.borderBottom(), 0);
			assertEquals(xlsDecorator.backgroundColor(), 0);
			assertEquals(xlsDecorator.foregroundColor(), 0);
			assertEquals(xlsDecorator.wrapText(), false);

		}
	}
}
