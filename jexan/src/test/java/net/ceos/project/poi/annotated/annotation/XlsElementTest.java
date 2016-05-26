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

import net.ceos.project.poi.annotated.bean.XMenFactory;

/**
 * Test the annotation {@link XlsElement}
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public class XlsElementTest {
	
	/**
	 * Test default configuration.
	 */
	@Test
	public void testDefaultConfiguration() {
		Class<XMenFactory.DefaultConfig> oC = XMenFactory.DefaultConfig.class;

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
	@Test
	public void testTitleAttribute() {
		Class<XMenFactory.Cyclops> oC = XMenFactory.Cyclops.class;

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
	@Test
	public void testCommentAttribute() {
		Class<XMenFactory.Cyclops> oC = XMenFactory.Cyclops.class;

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
	@Test
	public void testDecoratorAttribute() {
		Class<XMenFactory.Cyclops> oC = XMenFactory.Cyclops.class;

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
	@Test
	public void testFormatMaskAttribute() {
		Class<XMenFactory.Cyclops> oC = XMenFactory.Cyclops.class;

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
	@Test
	public void testTransformMaskAttribute() {
		Class<XMenFactory.Cyclops> oC = XMenFactory.Cyclops.class;

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
	@Test
	public void testFormulaAttribute() {
		Class<XMenFactory.Cyclops> oC = XMenFactory.Cyclops.class;

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
