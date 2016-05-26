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
package net.ceos.project.poi.annotated.core;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.junit.Test;

import net.ceos.project.poi.annotated.annotation.XlsConfiguration;
import net.ceos.project.poi.annotated.annotation.XlsElement;
import net.ceos.project.poi.annotated.annotation.XlsNestedHeader;
import net.ceos.project.poi.annotated.annotation.XlsSheet;
import net.ceos.project.poi.annotated.bean.SimpleObject;
import net.ceos.project.poi.annotated.bean.SimpleObjectBuilder;
import net.ceos.project.poi.annotated.definition.CascadeType;
import net.ceos.project.poi.annotated.definition.ExtensionFileType;
import net.ceos.project.poi.annotated.definition.PropagationType;

/**
 * Unit test for simple App.
 */
public class SimpleExcelTest {

	/**
	 * Test the annotation {@link XlsConfiguration} XlsConfiguration
	 */
	@Test
	public void testReadAnnotationXlsConfiguration() {
		// Read the object
		Class<SimpleObject> obj = SimpleObject.class;

		// Process @XlsConfiguration
		if (obj.isAnnotationPresent(XlsConfiguration.class)) {
			XlsConfiguration xlsAnnotation = (XlsConfiguration) obj.getAnnotation(XlsConfiguration.class);

			// add here the annotations attributes
			assertEquals(xlsAnnotation.extensionFile(), ExtensionFileType.XLS);
		}
	}

	/**
	 * Test the annotation {@link XlsSheet} XlsSheet
	 */
	@Test
	public void testReadAnnotationXlsSheet() {
		Class<SimpleObject> oC = SimpleObject.class;

		// Process @XlsSheet
		if (oC.isAnnotationPresent(XlsSheet.class)) {
			XlsSheet xlsAnnotation = (XlsSheet) oC.getAnnotation(XlsSheet.class);

			assertEquals(xlsAnnotation.title(), "Simple object sample");
			assertEquals(xlsAnnotation.propagation(), PropagationType.PROPAGATION_HORIZONTAL);
			assertEquals(xlsAnnotation.cascadeLevel(), CascadeType.CASCADE_BASE);
		}
	}

	/**
	 * Test the annotation {@link XlsElement} XlsElement
	 */
	@Test
	public void testReadAnnotationXlsElement() {
		Class<SimpleObject> oC = SimpleObject.class;

		List<Field> fL = Arrays.asList(oC.getDeclaredFields());
		for (Field f : fL) {
			// Process @XlsElement
			if (f.isAnnotationPresent(XlsElement.class)) {
				XlsElement xlsAnnotation = (XlsElement) f.getAnnotation(XlsElement.class);

				if (f.getName().equals("dateAttribute")) {
					assertEquals(xlsAnnotation.title(), "Date value");
				} else if (f.getName().equals("stringAttribute")) {
					assertEquals(xlsAnnotation.title(), "String value");
				} else if (f.getName().equals("integerAttribute")) {
					assertEquals(xlsAnnotation.title(), "Integer value");
				}
			}
		}
	}

	/**
	 * Test the annotation {@link XlsNestedHeader} XlsMasterHeader
	 */
	@Test
	public void testReadAnnotationXlsMasterHeader() {
		Class<SimpleObject> oC = SimpleObject.class;

		List<Field> fL = Arrays.asList(oC.getDeclaredFields());
		for (Field f : fL) {
			// Process @XlsMasterHeader
			if (f.isAnnotationPresent(XlsNestedHeader.class)) {
				XlsNestedHeader xlsAnnotation = (XlsNestedHeader) f.getAnnotation(XlsNestedHeader.class);

				if (f.getName().equals("dateAttribute")) {
					assertEquals(xlsAnnotation.title(), "Main info");
					assertEquals(xlsAnnotation.startX(), 1);
					assertEquals(xlsAnnotation.endX(), 3);
				}
			}
		}
	}

	/**
	 * Test marshal
	 */
	@Test
	public void testMarshal() throws Exception {
		SimpleObject fastTest = SimpleObjectBuilder.buildSimpleObject();

		IEngine en = new Engine();
		CellDecorator configuration = new CellDecorator();
		configuration.setAlignment(CellStyle.ALIGN_CENTER);
		configuration.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

		configuration.setBorderLeft(CellStyle.BORDER_THIN);
		configuration.setBorderRight(CellStyle.BORDER_THIN);
		configuration.setBorderTop(CellStyle.BORDER_THIN);
		configuration.setBorderBottom(CellStyle.BORDER_THIN);

		configuration.setForegroundColor(HSSFColor.RED.index);
		configuration.setFontBold(true);
		configuration.setFontItalic(true);
		configuration.setWrapText(true);

		XConfigCriteria configCriteria = new XConfigCriteria();
		configCriteria.overrideHeaderCellDecorator(configuration);
		
		en.marshalAndSave(configCriteria, fastTest, TestUtils.WORKING_DIR_GENERATED_II);
	}

	/**
	 * Test unmarshal
	 */
	@Test
	public void testUnmarshal() throws Exception {
		SimpleObject charged = new SimpleObject();

		IEngine en = new Engine();
		en.unmarshalFromPath(charged, TestUtils.WORKING_DIR_GENERATED_II);

		SimpleObjectBuilder.validateSimpleObject(charged);
	}
	
}
