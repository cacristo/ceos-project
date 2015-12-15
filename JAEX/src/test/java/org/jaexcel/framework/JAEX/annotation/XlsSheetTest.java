package org.jaexcel.framework.JAEX.annotation;

import java.lang.annotation.Annotation;

import org.jaexcel.framework.JAEX.bean.ObjectsBuilderTest;
import org.jaexcel.framework.JAEX.definition.CascadeType;
import org.jaexcel.framework.JAEX.definition.PropagationType;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class XlsSheetTest extends TestCase {
	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public XlsSheetTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(XlsSheetTest.class);
	}

	/**
	 * Test default configuration.
	 */
	public void testDefaultConfigurationAttibutes() {
		Class<ObjectsBuilderTest.ObjectWithDefaultConfig> oC = ObjectsBuilderTest.ObjectWithDefaultConfig.class;

		// Process @XlsSheet
		if (oC.isAnnotationPresent(XlsSheet.class)) {

			Annotation annotation = oC.getAnnotation(XlsSheet.class);
			XlsSheet xlsSheet = (XlsSheet) annotation;

			// add here the annotations attributes
			assertEquals(xlsSheet.startRow(), 1);
			assertEquals(xlsSheet.startCell(), 1);
			assertEquals(xlsSheet.propagation(), PropagationType.PROPAGATION_HORIZONTAL);
			assertEquals(xlsSheet.cascadeLevel(), CascadeType.CASCADE_BASE);
		}
	}

	/**
	 * Test default configuration.
	 */
	public void testTitleAttibute() {
		Class<ObjectsBuilderTest.ObjectWithDefaultConfig> oC = ObjectsBuilderTest.ObjectWithDefaultConfig.class;

		// Process @XlsSheet
		if (oC.isAnnotationPresent(XlsSheet.class)) {

			Annotation annotation = oC.getAnnotation(XlsSheet.class);
			XlsSheet xlsSheet = (XlsSheet) annotation;

			// add here the annotations attributes
			assertEquals(xlsSheet.title(), "Default configuration sample");
		}
	}

	/**
	 * Test propagation attribute with type PROPAGATION_VERTICAL.
	 */
	public void testPropagationVerticalAttibute() {
		Class<ObjectsBuilderTest.IronMan> oC = ObjectsBuilderTest.IronMan.class;

		// Process @XlsSheet
		if (oC.isAnnotationPresent(XlsSheet.class)) {

			Annotation annotation = oC.getAnnotation(XlsSheet.class);
			XlsSheet xlsSheet = (XlsSheet) annotation;

			// add here the annotations attributes
			assertEquals(xlsSheet.propagation(), PropagationType.PROPAGATION_VERTICAL);
		}
	}

	/**
	 * Test propagation attribute with type PROPAGATION_HORIZONTAL.
	 */
	public void testPropagationHorizontalAttibute() {
		Class<ObjectsBuilderTest.Thor> oC = ObjectsBuilderTest.Thor.class;

		// Process @XlsSheet
		if (oC.isAnnotationPresent(XlsSheet.class)) {

			Annotation annotation = oC.getAnnotation(XlsSheet.class);
			XlsSheet xlsSheet = (XlsSheet) annotation;

			// add here the annotations attributes
			assertEquals(xlsSheet.propagation(), PropagationType.PROPAGATION_HORIZONTAL);
		}
	}

	/**
	 * Test cascade level attribute with type CASCADE_BASE.
	 */
	public void testCascadeLevelBaseAttibute() {
		Class<ObjectsBuilderTest.IronMan> oC = ObjectsBuilderTest.IronMan.class;

		// Process @XlsSheet
		if (oC.isAnnotationPresent(XlsSheet.class)) {

			Annotation annotation = oC.getAnnotation(XlsSheet.class);
			XlsSheet xlsSheet = (XlsSheet) annotation;

			// add here the annotations attributes
			assertEquals(xlsSheet.cascadeLevel(), CascadeType.CASCADE_BASE);
		}
	}

	/**
	 * Test cascade level attribute with type CASCADE_LEVEL_ONE.
	 */
	public void testCascadeLevelOneAttibute() {
		Class<ObjectsBuilderTest.Wolverine> oC = ObjectsBuilderTest.Wolverine.class;

		// Process @XlsSheet
		if (oC.isAnnotationPresent(XlsSheet.class)) {

			Annotation annotation = oC.getAnnotation(XlsSheet.class);
			XlsSheet xlsSheet = (XlsSheet) annotation;

			// add here the annotations attributes
			assertEquals(xlsSheet.cascadeLevel(), CascadeType.CASCADE_LEVEL_ONE);
		}
	}

	/**
	 * Test cascade level attribute with type CASCADE_LEVEL_TWO.
	 */
	public void testCascadeLevelTwoAttibute() {
		Class<ObjectsBuilderTest.SpiderMan> oC = ObjectsBuilderTest.SpiderMan.class;

		// Process @XlsSheet
		if (oC.isAnnotationPresent(XlsSheet.class)) {

			Annotation annotation = oC.getAnnotation(XlsSheet.class);
			XlsSheet xlsSheet = (XlsSheet) annotation;

			// add here the annotations attributes
			assertEquals(xlsSheet.cascadeLevel(), CascadeType.CASCADE_LEVEL_TWO);
		}
	}

	/**
	 * Test cascade level attribute with type CASCADE_FULL.
	 */
	public void testCascadeLevelFullAttibute() {
		Class<ObjectsBuilderTest.DoctorX> oC = ObjectsBuilderTest.DoctorX.class;

		// Process @XlsSheet
		if (oC.isAnnotationPresent(XlsSheet.class)) {

			Annotation annotation = oC.getAnnotation(XlsSheet.class);
			XlsSheet xlsSheet = (XlsSheet) annotation;

			// add here the annotations attributes
			assertEquals(xlsSheet.cascadeLevel(), CascadeType.CASCADE_FULL);
		}
	}
}
