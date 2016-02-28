package net.ceos.project.poi.annotated.core;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import net.ceos.project.poi.annotated.annotation.XlsElement;
import net.ceos.project.poi.annotated.annotation.XlsNestedHeader;
import net.ceos.project.poi.annotated.annotation.XlsSheet;
import net.ceos.project.poi.annotated.bean.SimpleObjectListPropHorizontal;
import net.ceos.project.poi.annotated.bean.SimpleObjectListPropVertical;
import net.ceos.project.poi.annotated.core.Engine;
import net.ceos.project.poi.annotated.definition.ExtensionFileType;
import net.ceos.project.poi.annotated.definition.PropagationType;

/**
 * Unit test for simple App.
 */
public class CollectionSimpleExcelTest extends TestCase {
	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public CollectionSimpleExcelTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(CollectionSimpleExcelTest.class);
	}



	/**
	 * Test the annotation {@link XlsSheet} XlsSheet
	 */
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
		en.marshalAsCollection(collectionSimpleObject, "file_list_object_simple_horiz" , ExtensionFileType.XLSX);
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
		
		Engine en = new Engine();
		en.marshalAsCollection(collectionSimpleObject, "file_list_object_simple_vertical" , ExtensionFileType.XLSX);
	}
	
	
}
