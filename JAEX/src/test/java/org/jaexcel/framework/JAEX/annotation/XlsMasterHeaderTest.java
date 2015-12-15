package org.jaexcel.framework.JAEX.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import org.jaexcel.framework.JAEX.bean.ObjectsBuilderTest;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

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
			// Process @XlsMasterHeader
			if (f.isAnnotationPresent(XlsMasterHeader.class)) {

				Annotation annotation = f.getAnnotation(XlsMasterHeader.class);
				XlsMasterHeader xlsMasterHeader = (XlsMasterHeader) annotation;

				// add here the annotations attributes
				assertEquals(xlsMasterHeader.startX(), 0);
				assertEquals(xlsMasterHeader.startY(), 0);
				assertEquals(xlsMasterHeader.endX(), 0);
				assertEquals(xlsMasterHeader.endY(), 0);
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
			// Process @XlsMasterHeader
			if (f.isAnnotationPresent(XlsMasterHeader.class)) {

				Annotation annotation = f.getAnnotation(XlsMasterHeader.class);
				XlsMasterHeader xlsMasterHeader = (XlsMasterHeader) annotation;

				// add here the annotations attributes
				assertEquals(xlsMasterHeader.startX(), 1);
				assertEquals(xlsMasterHeader.endX(), 3);
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
			// Process @XlsMasterHeader
			if (f.isAnnotationPresent(XlsMasterHeader.class)) {

				Annotation annotation = f.getAnnotation(XlsMasterHeader.class);
				XlsMasterHeader xlsMasterHeader = (XlsMasterHeader) annotation;

				// add here the annotations attributes
				assertEquals(xlsMasterHeader.startY(), 1);
				assertEquals(xlsMasterHeader.endY(), 2);
			}
		}
	}
}
