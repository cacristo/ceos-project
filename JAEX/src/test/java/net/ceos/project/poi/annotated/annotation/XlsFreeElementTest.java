package net.ceos.project.poi.annotated.annotation;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import net.ceos.project.poi.annotated.bean.ObjectsBuilderTest;
import net.ceos.project.poi.annotated.definition.TitleOrientationType;

public class XlsFreeElementTest extends TestCase {
	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public XlsFreeElementTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(XlsFreeElementTest.class);
	}

	/**
	 * Test default configuration.
	 */
	public void testDefaultConfiguration() {
		Class<ObjectsBuilderTest.ProfessorX> oC = ObjectsBuilderTest.ProfessorX.class;

		List<Field> fL = Arrays.asList(oC.getDeclaredFields());
		for (Field f : fL) {
			// Process @XlsFreeElement
			if (f.isAnnotationPresent(XlsFreeElement.class)) {

				XlsFreeElement xlsFreeElement = (XlsFreeElement) f.getAnnotation(XlsFreeElement.class);

				if (f.getName().equals("stringFreeAttribute1")) {
					assertEquals(xlsFreeElement.showTitle(), false);
					assertEquals(xlsFreeElement.titleOrientation(), TitleOrientationType.TOP);
					assertEquals(xlsFreeElement.row(), 1);
					assertEquals(xlsFreeElement.cell(), 1);
					assertEquals(xlsFreeElement.comment(), "");
					assertEquals(xlsFreeElement.decorator(), "");
					assertEquals(xlsFreeElement.formatMask(), "");
					assertEquals(xlsFreeElement.transformMask(), "");
					assertEquals(xlsFreeElement.isFormula(), false);
					assertEquals(xlsFreeElement.formula(), "");
					assertEquals(xlsFreeElement.customizedRules(), "");
				}
			}
		}
	}

	/**
	 * Test title attribute.
	 */
	public void testTitleAttribute() {
		Class<ObjectsBuilderTest.ProfessorX> oC = ObjectsBuilderTest.ProfessorX.class;

		List<Field> fL = Arrays.asList(oC.getDeclaredFields());
		for (Field f : fL) {
			// Process @XlsFreeElement
			if (f.isAnnotationPresent(XlsFreeElement.class)) {

				XlsFreeElement xlsFreeElement = (XlsFreeElement) f.getAnnotation(XlsFreeElement.class);

				if (f.getName().equals("stringFreeAttribute1")) {
					assertEquals(xlsFreeElement.showTitle(), false);
					assertEquals(xlsFreeElement.title(), "String free element 1");
					assertEquals(xlsFreeElement.row(), 1);
					assertEquals(xlsFreeElement.cell(), 1);

				} else if (f.getName().equals("stringFreeAttribute2")) {
					assertEquals(xlsFreeElement.showTitle(), true);
					assertEquals(xlsFreeElement.titleOrientation(), TitleOrientationType.BOTTOM);
					assertEquals(xlsFreeElement.title(), "String free element 2");
					assertEquals(xlsFreeElement.row(), 1);
					assertEquals(xlsFreeElement.cell(), 2);
					
				}else if (f.getName().equals("stringFreeAttribute3")) {
					assertEquals(xlsFreeElement.showTitle(), false);
					assertEquals(xlsFreeElement.title(), "String free element 3");
					assertEquals(xlsFreeElement.row(), 1);
					assertEquals(xlsFreeElement.cell(), 3);

				} else if (f.getName().equals("stringFreeAttribute4")) {
					assertEquals(xlsFreeElement.showTitle(), true);
					assertEquals(xlsFreeElement.titleOrientation(), TitleOrientationType.RIGHT);
					assertEquals(xlsFreeElement.title(), "String free element 4");
					assertEquals(xlsFreeElement.row(), 1);
					assertEquals(xlsFreeElement.cell(), 4);
				} 
			}
		}
	}

	/**
	 * Test comment attribute.
	 */
	public void testCommentAttribute() {
		Class<ObjectsBuilderTest.ProfessorX> oC = ObjectsBuilderTest.ProfessorX.class;

		List<Field> fL = Arrays.asList(oC.getDeclaredFields());
		for (Field f : fL) {
			// Process @XlsFreeElement
			if (f.isAnnotationPresent(XlsFreeElement.class)) {

				XlsFreeElement xlsFreeElement = (XlsFreeElement) f.getAnnotation(XlsFreeElement.class);

				if (f.getName().equals("stringFreeAttribute3")
						&& StringUtils.isNotBlank(xlsFreeElement.comment())) {
					assertEquals(xlsFreeElement.comment(), "Free element with sample comment");
				}
			}
		}
	}

	/**
	 * Test decorator attribute.
	 */
	public void testDecoratorAttribute() {
		Class<ObjectsBuilderTest.ProfessorX> oC = ObjectsBuilderTest.ProfessorX.class;

		List<Field> fL = Arrays.asList(oC.getDeclaredFields());
		for (Field f : fL) {
			// Process @XlsFreeElement
			if (f.isAnnotationPresent(XlsFreeElement.class)) {

				XlsFreeElement xlsFreeElement = (XlsFreeElement) f.getAnnotation(XlsFreeElement.class);

				if (f.getName().equals("integerFreeAttribute")
						&& StringUtils.isNotBlank(xlsFreeElement.decorator())) {
					assertEquals(xlsFreeElement.decorator(), "myDecorator");
				}
			}
		}
	}

	/**
	 * Test formatMask attribute.
	 */
	public void testFormatMaskAttribute() {
		Class<ObjectsBuilderTest.ProfessorX> oC = ObjectsBuilderTest.ProfessorX.class;

		List<Field> fL = Arrays.asList(oC.getDeclaredFields());
		for (Field f : fL) {
			// Process @XlsFreeElement
			if (f.isAnnotationPresent(XlsFreeElement.class)) {

				XlsFreeElement xlsFreeElement = (XlsFreeElement) f.getAnnotation(XlsFreeElement.class);

				if (f.getName().equals("integerFreeAttribute")
						&& StringUtils.isNotBlank(xlsFreeElement.formatMask())) {
					assertEquals(xlsFreeElement.formatMask(), "0.0");
				}
			}
		}
	}

	/**
	 * Test transformMask attribute.
	 */
	public void testTransformMaskAttribute() {
		Class<ObjectsBuilderTest.ProfessorX> oC = ObjectsBuilderTest.ProfessorX.class;

		List<Field> fL = Arrays.asList(oC.getDeclaredFields());
		for (Field f : fL) {
			// Process @XlsFreeElement
			if (f.isAnnotationPresent(XlsFreeElement.class)) {

				XlsFreeElement xlsFreeElement = (XlsFreeElement) f.getAnnotation(XlsFreeElement.class);

				if (f.getName().equals("doubleFreeAttribute")
						&& StringUtils.isNotBlank(xlsFreeElement.transformMask())) {
					assertEquals(xlsFreeElement.transformMask(), "0.00");
				}
			}
		}
	}
}
