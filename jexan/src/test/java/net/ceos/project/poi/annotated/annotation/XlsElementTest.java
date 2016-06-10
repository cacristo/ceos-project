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
	public void checkDefaultConfiguration() {
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
	 * Test initialization of the title attribute over multiples types with
	 * specific value.
	 */
	@Test
	public void checkTitleAttribute() {
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
	 * Test initialization of the comment attribute with specific value.
	 */
	@Test
	public void checkCommentAttribute() {
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
	 * Test initialization of the decorator attribute with specific value.
	 */
	@Test
	public void checkDecoratorAttribute() {
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
	 * Test initialization of the formatMask attribute with specific value.
	 */
	@Test
	public void checkFormatMaskAttribute() {
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
	 * Test initialization of the transformMask attribute with specific value.
	 */
	@Test
	public void checkTransformMaskAttribute() {
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
	 * Test initialization of the isFormula & formula attribute with specific
	 * value.
	 */
	@Test
	public void checkFormulaAttribute() {
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
