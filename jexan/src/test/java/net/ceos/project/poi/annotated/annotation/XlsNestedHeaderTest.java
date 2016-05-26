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

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import net.ceos.project.poi.annotated.bean.XMenFactory;

/**
 * Test the annotation {@link XlsNestedHeader}
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public class XlsNestedHeaderTest {

	/**
	 * Test default configuration.
	 */
	@Test
	public void testDefaultConfiguration() {
		Class<XMenFactory.DefaultConfig> oC = XMenFactory.DefaultConfig.class;

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
	@Test
	public void testCellConfiguration() {
		Class<XMenFactory.Cyclops> oC = XMenFactory.Cyclops.class;

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
	@Test
	public void testRowConfiguration() {
		Class<XMenFactory.IronMan> oC = XMenFactory.IronMan.class;

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
