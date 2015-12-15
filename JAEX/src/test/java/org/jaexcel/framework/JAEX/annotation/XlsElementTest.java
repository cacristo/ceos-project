package org.jaexcel.framework.JAEX.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jaexcel.framework.JAEX.bean.ObjectsBuilderTest;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class XlsElementTest extends TestCase {
	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public XlsElementTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(XlsElementTest.class);
	}

	/**
	 * Test default configuration.
	 */
	public void testDefaultConfiguration() {
		Class<ObjectsBuilderTest.ObjectWithDefaultConfig> oC = ObjectsBuilderTest.ObjectWithDefaultConfig.class;

		List<Field> fL = Arrays.asList(oC.getDeclaredFields());
		for (Field f : fL) {
			// Process @XlsElement
			if (f.isAnnotationPresent(XlsElement.class)) {

				Annotation annotation = f.getAnnotation(XlsElement.class);
				XlsElement xlsElement = (XlsElement) annotation;

				assertEquals(xlsElement.comment(), "");
				assertEquals(xlsElement.decorator(), "");
			}
		}
	}

	/**
	 * Test title attribute.
	 */
	public void testTitleAttribute() {
		Class<ObjectsBuilderTest.Cyclops> oC = ObjectsBuilderTest.Cyclops.class;

		List<Field> fL = Arrays.asList(oC.getDeclaredFields());
		for (Field f : fL) {
			// Process @XlsElement
			if (f.isAnnotationPresent(XlsElement.class)) {

				Annotation annotation = f.getAnnotation(XlsElement.class);
				XlsElement xlsElement = (XlsElement) annotation;

				if(f.getName().equals("dateAttribute")){
					assertEquals(xlsElement.title(), "Date value");
				}
				if(f.getName().equals("stringAttribute")){
					assertEquals(xlsElement.title(), "String value");
				}
				if(f.getName().equals("integerAttribute")){
					assertEquals(xlsElement.title(), "Integer value");
				}

			}
		}
	}

	/**
	 * Test comment attribute.
	 */
	public void testCommentAttribute() {
		Class<ObjectsBuilderTest.Cyclops> oC = ObjectsBuilderTest.Cyclops.class;

		List<Field> fL = Arrays.asList(oC.getDeclaredFields());
		for (Field f : fL) {
			// Process @XlsElement
			if (f.isAnnotationPresent(XlsElement.class)) {

				Annotation annotation = f.getAnnotation(XlsElement.class);
				XlsElement xlsElement = (XlsElement) annotation;

				if(StringUtils.isNotBlank(xlsElement.comment())){
					assertEquals(xlsElement.comment(), "This is a comment");
				}
			}
		}
	}

	/**
	 * Test decorator attribute.
	 */
	public void testDecoratorAttribute() {
		Class<ObjectsBuilderTest.Cyclops> oC = ObjectsBuilderTest.Cyclops.class;

		List<Field> fL = Arrays.asList(oC.getDeclaredFields());
		for (Field f : fL) {
			// Process @XlsElement
			if (f.isAnnotationPresent(XlsElement.class)) {

				Annotation annotation = f.getAnnotation(XlsElement.class);
				XlsElement xlsElement = (XlsElement) annotation;

				if(StringUtils.isNotBlank(xlsElement.decorator())){
					assertEquals(xlsElement.decorator(), "0.0");
				}
			}
		}
	}
}
