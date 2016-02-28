package net.ceos.project.poi.annotated.annotation;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import net.ceos.project.poi.annotated.bean.ObjectsBuilderTest;

public class XlsMasterHeaderTest extends TestCase {
	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public XlsMasterHeaderTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(XlsMasterHeaderTest.class);
	}

	/**
	 * Test default configuration.
	 */
	public void testDefaultConfiguration() {
		Class<ObjectsBuilderTest.ObjectWithDefaultConfig> oC = ObjectsBuilderTest.ObjectWithDefaultConfig.class;

		List<Field> fL = Arrays.asList(oC.getDeclaredFields());
		for (Field f : fL) {
			// Process @XlsNestedHeader
			if (f.isAnnotationPresent(XlsNestedHeader.class)) {

				XlsNestedHeader xlsNestedHeader = (XlsNestedHeader) f.getAnnotation(XlsNestedHeader.class);

				// add here the annotations attributes
				assertEquals(xlsNestedHeader.startX(), 0);
				assertEquals(xlsNestedHeader.startY(), 0);
				assertEquals(xlsNestedHeader.endX(), 0);
				assertEquals(xlsNestedHeader.endY(), 0);
			}
		}
	}

	/**
	 * Test cell configuration.
	 */
	public void testCellConfiguration() {
		Class<ObjectsBuilderTest.Cyclops> oC = ObjectsBuilderTest.Cyclops.class;

		List<Field> fL = Arrays.asList(oC.getDeclaredFields());
		for (Field f : fL) {
			// Process @XlsNestedHeader
			if (f.isAnnotationPresent(XlsNestedHeader.class)) {

				XlsNestedHeader xlsNestedHeader = (XlsNestedHeader) f.getAnnotation(XlsNestedHeader.class);

				// add here the annotations attributes
				assertEquals(xlsNestedHeader.startX(), 1);
				assertEquals(xlsNestedHeader.endX(), 3);
			}
		}
	}

	/**
	 * Test row configuration.
	 */
	public void testRowConfiguration() {
		Class<ObjectsBuilderTest.IronMan> oC = ObjectsBuilderTest.IronMan.class;

		List<Field> fL = Arrays.asList(oC.getDeclaredFields());
		for (Field f : fL) {
			// Process @XlsNestedHeader
			if (f.isAnnotationPresent(XlsNestedHeader.class)) {

				XlsNestedHeader xlsNestedHeader = (XlsNestedHeader) f.getAnnotation(XlsNestedHeader.class);

				// add here the annotations attributes
				assertEquals(xlsNestedHeader.startY(), 1);
				assertEquals(xlsNestedHeader.endY(), 2);
			}
		}
	}
}
