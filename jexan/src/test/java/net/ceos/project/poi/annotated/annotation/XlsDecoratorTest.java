package net.ceos.project.poi.annotated.annotation;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import net.ceos.project.poi.annotated.bean.XMenFactory;

/**
 * Test the annotation {@link XlsDecorator}
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public class XlsDecoratorTest {

	/**
	 * Test default configuration.
	 */
	@Test
	public void testDefaultConfiguration() {
		Class<XMenFactory.DefaultConfig> o = XMenFactory.DefaultConfig.class;

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
