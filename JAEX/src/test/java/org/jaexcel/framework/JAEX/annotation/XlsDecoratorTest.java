package org.jaexcel.framework.JAEX.annotation;

import java.lang.annotation.Annotation;

import org.jaexcel.framework.JAEX.bean.ObjectsBuilderTest;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class XlsDecoratorTest extends TestCase {

	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public XlsDecoratorTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(XlsDecoratorTest.class);
	}

	/**
	 * Test default configuration.
	 */
	public void testDefaultConfiguration() {
		Class<ObjectsBuilderTest.ObjectWithDefaultConfig> o = ObjectsBuilderTest.ObjectWithDefaultConfig.class;

		// Process @XlsDecorator
		if (o.isAnnotationPresent(XlsDecorator.class)) {

			Annotation annotation = o.getAnnotation(XlsDecorator.class);
			XlsDecorator xlsDecorator = (XlsDecorator) annotation;

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
