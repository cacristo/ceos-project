package net.ceos.project.poi.annotated.annotation;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import net.ceos.project.poi.annotated.bean.XMenFactory;
import net.ceos.project.poi.annotated.definition.TitleOrientationType;

/**
 * Test the annotation {@link XlsFreeElement}
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public class XlsFreeElementTest {

	/**
	 * Test default configuration.
	 */
	@Test
	public void testDefaultConfiguration() {
		Class<XMenFactory.ProfessorX> oC = XMenFactory.ProfessorX.class;

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
	@Test
	public void testTitleAttribute() {
		Class<XMenFactory.ProfessorX> oC = XMenFactory.ProfessorX.class;

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

				} else if (f.getName().equals("stringFreeAttribute3")) {
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
	@Test
	public void testCommentAttribute() {
		Class<XMenFactory.ProfessorX> oC = XMenFactory.ProfessorX.class;

		List<Field> fL = Arrays.asList(oC.getDeclaredFields());
		for (Field f : fL) {
			// Process @XlsFreeElement
			if (f.isAnnotationPresent(XlsFreeElement.class)) {

				XlsFreeElement xlsFreeElement = (XlsFreeElement) f.getAnnotation(XlsFreeElement.class);

				if (f.getName().equals("stringFreeAttribute3") && StringUtils.isNotBlank(xlsFreeElement.comment())) {
					assertEquals(xlsFreeElement.comment(), "Free element with sample comment");
				}
			}
		}
	}

	/**
	 * Test decorator attribute.
	 */
	@Test
	public void testDecoratorAttribute() {
		Class<XMenFactory.ProfessorX> oC = XMenFactory.ProfessorX.class;

		List<Field> fL = Arrays.asList(oC.getDeclaredFields());
		for (Field f : fL) {
			// Process @XlsFreeElement
			if (f.isAnnotationPresent(XlsFreeElement.class)) {

				XlsFreeElement xlsFreeElement = (XlsFreeElement) f.getAnnotation(XlsFreeElement.class);

				if (f.getName().equals("integerFreeAttribute") && StringUtils.isNotBlank(xlsFreeElement.decorator())) {
					assertEquals(xlsFreeElement.decorator(), "myDecorator");
				}
			}
		}
	}

	/**
	 * Test formatMask attribute.
	 */
	@Test
	public void testFormatMaskAttribute() {
		Class<XMenFactory.ProfessorX> oC = XMenFactory.ProfessorX.class;

		List<Field> fL = Arrays.asList(oC.getDeclaredFields());
		for (Field f : fL) {
			// Process @XlsFreeElement
			if (f.isAnnotationPresent(XlsFreeElement.class)) {

				XlsFreeElement xlsFreeElement = (XlsFreeElement) f.getAnnotation(XlsFreeElement.class);

				if (f.getName().equals("integerFreeAttribute") && StringUtils.isNotBlank(xlsFreeElement.formatMask())) {
					assertEquals(xlsFreeElement.formatMask(), "0.0");
				}
			}
		}
	}

	/**
	 * Test transformMask attribute.
	 */
	@Test
	public void testTransformMaskAttribute() {
		Class<XMenFactory.ProfessorX> oC = XMenFactory.ProfessorX.class;

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
