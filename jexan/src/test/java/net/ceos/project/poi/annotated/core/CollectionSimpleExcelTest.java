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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import net.ceos.project.poi.annotated.annotation.XlsElement;
import net.ceos.project.poi.annotated.annotation.XlsNestedHeader;
import net.ceos.project.poi.annotated.annotation.XlsSheet;
import net.ceos.project.poi.annotated.bean.SimpleObjectListPropHorizontal;
import net.ceos.project.poi.annotated.bean.SimpleObjectListPropVertical;
import net.ceos.project.poi.annotated.definition.ExtensionFileType;
import net.ceos.project.poi.annotated.definition.PropagationType;

/**
 * Unit test for simple App.
 */
public class CollectionSimpleExcelTest {
	
	/**
	 * Test the annotation {@link XlsSheet} XlsSheet
	 */
	@Test
	public void testReadAnnotationXlsSheetHoriz() {
		Class<SimpleObjectListPropHorizontal> oC = SimpleObjectListPropHorizontal.class;

		// Process @XlsSheet
		if (oC.isAnnotationPresent(XlsSheet.class)) {
			XlsSheet xlsAnnotation = (XlsSheet) oC
					.getAnnotation(XlsSheet.class);

			assertEquals(xlsAnnotation.title(), "List Simple object horizontal");
			assertEquals(xlsAnnotation.propagation(),
					PropagationType.PROPAGATION_HORIZONTAL);
		}
	}

	/**
	 * Test the annotation {@link XlsElement} XlsElement
	 */
	@Test
	public void testReadAnnotationXlsElementHoriz() {
		Class<SimpleObjectListPropHorizontal> oC = SimpleObjectListPropHorizontal.class;

		List<Field> fL = Arrays.asList(oC.getDeclaredFields());
		for (Field f : fL) {
			// Process @XlsElement
			if (f.isAnnotationPresent(XlsElement.class)) {
				XlsElement xlsAnnotation = (XlsElement) f
						.getAnnotation(XlsElement.class);

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
	public void testReadAnnotationXlsMasterHeaderHoriz() {
		Class<SimpleObjectListPropHorizontal> oC = SimpleObjectListPropHorizontal.class;

		List<Field> fL = Arrays.asList(oC.getDeclaredFields());
		for (Field f : fL) {
			// Process @XlsMasterHeader
			if (f.isAnnotationPresent(XlsNestedHeader.class)) {
				XlsNestedHeader xlsAnnotation = (XlsNestedHeader) f
						.getAnnotation(XlsNestedHeader.class);

				if (f.getName().equals("dateAttribute")) {
					assertEquals(xlsAnnotation.title(), "Main info");
					assertEquals(xlsAnnotation.startX(), 1);
					assertEquals(xlsAnnotation.endX(), 3);
				}
			}
		}
	}

	/**
	 * Test one basic object
	 */
	@Test
	public void testCollectionSimpleExcelHorizontal() throws Exception {
		SimpleObjectListPropHorizontal simpleObject = new SimpleObjectListPropHorizontal();
		simpleObject.setDateAttribute(new Date());
		simpleObject.setStringAttribute("some string");
		simpleObject.setIntegerAttribute(46);
		
		SimpleObjectListPropHorizontal simpleObject2 = new SimpleObjectListPropHorizontal();
		simpleObject2.setDateAttribute(new Date());
		simpleObject2.setStringAttribute("some string 2");
		simpleObject2.setIntegerAttribute(47);

		List<SimpleObjectListPropHorizontal> collectionSimpleObject= new ArrayList<SimpleObjectListPropHorizontal>();
		collectionSimpleObject.add(simpleObject);
		collectionSimpleObject.add(simpleObject2);
		collectionSimpleObject.add(simpleObject);
		collectionSimpleObject.add(simpleObject2);
		collectionSimpleObject.add(simpleObject);
		collectionSimpleObject.add(simpleObject2);
		collectionSimpleObject.add(simpleObject);
		collectionSimpleObject.add(simpleObject2);
		collectionSimpleObject.add(simpleObject);
		collectionSimpleObject.add(simpleObject2);
		collectionSimpleObject.add(simpleObject);
		collectionSimpleObject.add(simpleObject2);
		collectionSimpleObject.add(simpleObject);
		collectionSimpleObject.add(simpleObject2);
		
		Engine en = new Engine();
		en.marshalAsCollectionAndSave(collectionSimpleObject, TestUtils.WORKING_DIR_GENERATED_II);
/*
		// start validation
		Class<SimpleObject> oC = (Class<SimpleObject>) collectionSimpleObject.getClass();

		String nameFile = "";
		String extensionFile = "";
		// int cascadeLevel = -1;
		// Process @XlsConfiguration
		if (oC.isAnnotationPresent(XlsConfiguration.class)) {
			XlsConfiguration xlsAnnotation = (XlsConfiguration) oC
					.getAnnotation(XlsConfiguration.class);

			// add here the annotations attributes
			nameFile = xlsAnnotation.nameFile();
			extensionFile = xlsAnnotation.extensionFile().getExtension();
			// cascadeLevel = xlsAnnotation.cascadeLevel().getCode();
		}

		String titleSheet = "";
		// int propagationType = -1;
		int idxRow = -1;
		int idxCell = -1;
		// Process @XlsSheet
		if (oC.isAnnotationPresent(XlsSheet.class)) {
			XlsSheet xlsAnnotation = (XlsSheet) oC
					.getAnnotation(XlsSheet.class);

			titleSheet = xlsAnnotation.title();
			// propagationType = xlsAnnotation.propagation().getCode();
			idxRow = xlsAnnotation.startRow();
			idxCell = xlsAnnotation.startCell();
		}

		FileInputStream input = new FileInputStream("D:\\excel\\" + nameFile
				+ extensionFile);
		HSSFWorkbook wb = new HSSFWorkbook(input);
		HSSFSheet sheet = wb.getSheet(titleSheet);

		List<Field> fL = Arrays.asList(oC.getDeclaredFields());
		for (Field f : fL) {
			// Process @XlsElement
			if (f.isAnnotationPresent(XlsElement.class)) {
				XlsElement xlsAnnotation = (XlsElement) f
						.getAnnotation(XlsElement.class);

				// header row
				HSSFRow headerRow = sheet.getRow(idxRow);
				HSSFCell headerCell = headerRow.getCell(idxCell
						+ xlsAnnotation.position());
				// content row
				HSSFRow contentRow = sheet.getRow(idxRow + 1);
				HSSFCell contentCell = contentRow.getCell(idxCell
						+ xlsAnnotation.position());

				
				for (SimpleObjectListPropHorizontalfastTest : collectionSimpleObject){
					if (xlsAnnotation.position() == 1) {
						TestUtils.validationDate(fastTest.getDateAttribute(),
								xlsAnnotation, headerCell, contentCell);
					} else if (xlsAnnotation.position() == 2) {
						TestUtils.validationString(fastTest.getStringAttribute(),
								xlsAnnotation, headerCell, contentCell);
					} else if (xlsAnnotation.position() == 3) {
						TestUtils.validationNumeric(fastTest.getIntegerAttribute(),
								xlsAnnotation, headerCell, contentCell);
					}
				}
				
			}
		}*/
	}
	
	
	/**
	 * Test the annotation {@link XlsSheet} XlsSheet
	 */
	@Test
	public void testReadAnnotationXlsSheetVert() {
		Class<SimpleObjectListPropVertical> oC = SimpleObjectListPropVertical.class;

		// Process @XlsSheet
		if (oC.isAnnotationPresent(XlsSheet.class)) {
			XlsSheet xlsAnnotation = (XlsSheet) oC
					.getAnnotation(XlsSheet.class);

			assertEquals(xlsAnnotation.title(), "List Simple object vertical");
			assertEquals(xlsAnnotation.propagation(),
					PropagationType.PROPAGATION_VERTICAL);
		}
	}

	/**
	 * Test the annotation {@link XlsElement} XlsElement
	 */
	@Test
	public void testReadAnnotationXlsElementVert() {
		Class<SimpleObjectListPropVertical> oC = SimpleObjectListPropVertical.class;

		List<Field> fL = Arrays.asList(oC.getDeclaredFields());
		for (Field f : fL) {
			// Process @XlsElement
			if (f.isAnnotationPresent(XlsElement.class)) {
				XlsElement xlsAnnotation = (XlsElement) f
						.getAnnotation(XlsElement.class);

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
	public void testReadAnnotationXlsMasterHeaderVert() {
		Class<SimpleObjectListPropVertical> oC = SimpleObjectListPropVertical.class;

		List<Field> fL = Arrays.asList(oC.getDeclaredFields());
		for (Field f : fL) {
			// Process @XlsMasterHeader
			if (f.isAnnotationPresent(XlsNestedHeader.class)) {
				XlsNestedHeader xlsAnnotation = (XlsNestedHeader) f
						.getAnnotation(XlsNestedHeader.class);

				if (f.getName().equals("dateAttribute")) {
					assertEquals(xlsAnnotation.title(), "Main info");
					assertEquals(xlsAnnotation.startY(), 1);
					assertEquals(xlsAnnotation.endY(), 3);
				}
			}
		}
	}

	
	/**
	 * Test one basic object
	 */
	@Test
	public void testCollectionSimpleExcelVertical() throws Exception {
		SimpleObjectListPropVertical simpleObject = new SimpleObjectListPropVertical();
		simpleObject.setDateAttribute(new Date());
		simpleObject.setStringAttribute("some string");
		simpleObject.setIntegerAttribute(46);
		
		SimpleObjectListPropVertical simpleObject2 = new SimpleObjectListPropVertical();
		simpleObject2.setDateAttribute(new Date());
		simpleObject2.setStringAttribute("some string 2");
		simpleObject2.setIntegerAttribute(47);

		List<SimpleObjectListPropVertical> collectionSimpleObject= new ArrayList<SimpleObjectListPropVertical>();
		collectionSimpleObject.add(simpleObject);
		collectionSimpleObject.add(simpleObject2);
		collectionSimpleObject.add(simpleObject);
		collectionSimpleObject.add(simpleObject2);
		collectionSimpleObject.add(simpleObject);
		collectionSimpleObject.add(simpleObject2);
		collectionSimpleObject.add(simpleObject);
		collectionSimpleObject.add(simpleObject2);
		collectionSimpleObject.add(simpleObject);
		collectionSimpleObject.add(simpleObject2);
		collectionSimpleObject.add(simpleObject);
		collectionSimpleObject.add(simpleObject2);
		collectionSimpleObject.add(simpleObject);
		collectionSimpleObject.add(simpleObject2);
		
		XConfigCriteria configCriteria = new XConfigCriteria();
		configCriteria.setFileName("file_list_object_simple_vertical");
		configCriteria.overrideExtensionType(ExtensionFileType.XLSX);
		
		Engine en = new Engine();
		en.marshalAsCollectionAndSave(configCriteria, collectionSimpleObject, TestUtils.WORKING_DIR_GENERATED_I);
	}
	
	
}
