package net.ceos.project.poi.annotated.annotation;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import net.ceos.project.poi.annotated.bean.ObjectsBuilderTest;

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

				XlsElement xlsElement = (XlsElement) f.getAnnotation(XlsElement.class);

				assertEquals(xlsElement.comment(), "");
				assertEquals(xlsElement.commentRules(), "");
				assertEquals(xlsElement.decorator(), "");
				assertEquals(xlsElement.formatMask(), "");
				assertEquals(xlsElement.transformMask(), "");
				assertEquals(xlsElement.isFormula(), false);
				assertEquals(xlsElement.formula(), "");
				assertEquals(xlsElement.customizedRules(), "");
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

				XlsElement xlsElement = (XlsElement) f.getAnnotation(XlsElement.class);

				if (f.getName().equals("dateAttribute")) {
					assertEquals(xlsElement.title(), "Date value");

				} else if (f.getName().equals("stringAttribute")) {
					assertEquals(xlsElement.title(), "String value");

				} else if (f.getName().equals("integerAttribute")) {
					assertEquals(xlsElement.title(), "Integer value");

				} else if (f.getName().equals("doubleAttribute1")) {
					assertEquals(xlsElement.title(), "Double value 1");

				} else if (f.getName().equals("doubleAttribute2")) {
					assertEquals(xlsElement.title(), "Double value 2");

				} else if (f.getName().equals("sum")) {
					assertEquals(xlsElement.title(), "Sum double 1 & double 2");

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

				XlsElement xlsElement = (XlsElement) f.getAnnotation(XlsElement.class);

				if (StringUtils.isNotBlank(xlsElement.comment())) {
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

				XlsElement xlsElement = (XlsElement) f.getAnnotation(XlsElement.class);

				if (StringUtils.isNotBlank(xlsElement.decorator())) {
					assertEquals(xlsElement.decorator(), "extendedIntDecorator");
				}
			}
		}
	}

	/**
	 * Test formatMask attribute.
	 */
	public void testFormatMaskAttribute() {
		Class<ObjectsBuilderTest.Cyclops> oC = ObjectsBuilderTest.Cyclops.class;

		List<Field> fL = Arrays.asList(oC.getDeclaredFields());
		for (Field f : fL) {
			// Process @XlsElement
			if (f.isAnnotationPresent(XlsElement.class)) {

				XlsElement xlsElement = (XlsElement) f.getAnnotation(XlsElement.class);

				if (StringUtils.isNotBlank(xlsElement.formatMask())) {
					assertEquals(xlsElement.formatMask(), "0.000");
				}
			}
		}
	}

	/**
	 * Test transformMask attribute.
	 */
	public void testTransformMaskAttribute() {
		Class<ObjectsBuilderTest.Cyclops> oC = ObjectsBuilderTest.Cyclops.class;

		List<Field> fL = Arrays.asList(oC.getDeclaredFields());
		for (Field f : fL) {
			// Process @XlsElement
			if (f.isAnnotationPresent(XlsElement.class)) {

				XlsElement xlsElement = (XlsElement) f.getAnnotation(XlsElement.class);

				if (StringUtils.isNotBlank(xlsElement.transformMask())) {
					assertEquals(xlsElement.transformMask(), "0.0");
				}
			}
		}
	}

	/**
	 * Test isFormula & formula attribute.
	 */
	public void testFormulaAttribute() {
		Class<ObjectsBuilderTest.Cyclops> oC = ObjectsBuilderTest.Cyclops.class;

		List<Field> fL = Arrays.asList(oC.getDeclaredFields());
		for (Field f : fL) {
			// Process @XlsElement
			if (f.isAnnotationPresent(XlsElement.class)) {

				XlsElement xlsElement = (XlsElement) f.getAnnotation(XlsElement.class);

				if (xlsElement.isFormula()) {
					assertEquals(xlsElement.formula(), "SUM(E3,F3)");
				}
			}
		}
	}
}
