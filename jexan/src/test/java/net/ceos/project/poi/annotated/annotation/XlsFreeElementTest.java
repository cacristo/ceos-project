/**
 * Copyright 2016 Carlos CRISTO ABREU
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.ceos.project.poi.annotated.annotation;

import static org.testng.Assert.assertEquals;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.Test;

import net.ceos.project.poi.annotated.bean.factory.XMenFactory;
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
	public void checkDefaultConfiguration() {
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
	 * Test initialization of the title attribute according
	 * <ul>
	 * <li>the title
	 * <li>is title visible
	 * <li>title orientation
	 * <li>start row and cell
	 * </ul>
	 */
	@Test
	public void checkTitleAttribute() {
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
	 * Test initialization of the comment attribute with specific value.
	 */
	@Test
	public void checkCommentAttribute() {
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
	 * Test initialization of the decorator attribute with specific value.
	 */
	@Test
	public void checkDecoratorAttribute() {
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
	 * Test initialization of the formatMask attribute with specific value.
	 */
	@Test
	public void checkFormatMaskAttribute() {
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
	 * Test initialization of the transformMask attribute with specific value.
	 */
	@Test
	public void checkTransformMaskAttribute() {
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
