package org.jaexcel.framework.JAEX;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.jaexcel.framework.JAEX.annotation.XlsConfiguration;
import org.jaexcel.framework.JAEX.annotation.XlsElement;
import org.jaexcel.framework.JAEX.annotation.XlsMasterHeader;
import org.jaexcel.framework.JAEX.annotation.XlsSheet;
import org.jaexcel.framework.JAEX.bean.AddressInfo;
import org.jaexcel.framework.JAEX.bean.Job;
import org.jaexcel.framework.JAEX.bean.MultiTypeObject;
import org.jaexcel.framework.JAEX.bean.SimpleObject;
import org.jaexcel.framework.JAEX.definition.CascadeType;
import org.jaexcel.framework.JAEX.definition.ExtensionFileType;
import org.jaexcel.framework.JAEX.definition.PropagationType;
import org.jaexcel.framework.JAEX.engine.CellDecorator;
import org.jaexcel.framework.JAEX.engine.Engine;

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
	 * Test the annotation {@link XlsConfiguration} XlsConfiguration
	 */
	public void testReadAnnotationXlsConfiguration() {
		// Read the object
		Class<SimpleObject> obj = SimpleObject.class;

		// Process @XlsConfiguration
		if (obj.isAnnotationPresent(XlsConfiguration.class)) {
			XlsConfiguration xlsAnnotation = (XlsConfiguration) obj
					.getAnnotation(XlsConfiguration.class);

			// add here the annotations attributes
			assertEquals(xlsAnnotation.extensionFile(), ExtensionFileType.XLS);
		//	assertEquals(xlsAnnotation.cascadeLevel(), CascadeType.CASCADE_BASE);
		}
	}

	/**
	 * Test the annotation {@link XlsSheet} XlsSheet
	 */
	public void testReadAnnotationXlsSheet() {
		Class<SimpleObject> oC = SimpleObject.class;

		// Process @XlsSheet
		if (oC.isAnnotationPresent(XlsSheet.class)) {
			XlsSheet xlsAnnotation = (XlsSheet) oC
					.getAnnotation(XlsSheet.class);

			assertEquals(xlsAnnotation.title(), "Simple object sample");
			assertEquals(xlsAnnotation.propagation(),
					PropagationType.PROPAGATION_HORIZONTAL);
		}
	}

	/**
	 * Test the annotation {@link XlsElement} XlsElement
	 */
	public void testReadAnnotationXlsElement() {
		Class<SimpleObject> oC = SimpleObject.class;

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
	 * Test the annotation {@link XlsMasterHeader} XlsMasterHeader
	 */
	public void testReadAnnotationXlsMasterHeader() {
		Class<SimpleObject> oC = SimpleObject.class;

		List<Field> fL = Arrays.asList(oC.getDeclaredFields());
		for (Field f : fL) {
			// Process @XlsMasterHeader
			if (f.isAnnotationPresent(XlsMasterHeader.class)) {
				XlsMasterHeader xlsAnnotation = (XlsMasterHeader) f
						.getAnnotation(XlsMasterHeader.class);

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
	public void testCollectionSimpleExcel() throws Exception {
		SimpleObject fastTest1 = new SimpleObject();
		fastTest1.setDateAttribute(new Date());
		fastTest1.setStringAttribute("some string");
		fastTest1.setIntegerAttribute(46);
		
		SimpleObject fastTest2 = new SimpleObject();
		fastTest2.setDateAttribute(new Date());
		fastTest2.setStringAttribute("some string 2");
		fastTest2.setIntegerAttribute(47);

		List<SimpleObject> collectionSimpleObject = new ArrayList<SimpleObject>();
		collectionSimpleObject.add(fastTest1);
		collectionSimpleObject.add(fastTest2);
		collectionSimpleObject.add(fastTest1);
		collectionSimpleObject.add(fastTest2);
		collectionSimpleObject.add(fastTest1);
		collectionSimpleObject.add(fastTest2);
		collectionSimpleObject.add(fastTest1);
		collectionSimpleObject.add(fastTest2);
		collectionSimpleObject.add(fastTest1);
		collectionSimpleObject.add(fastTest2);
		collectionSimpleObject.add(fastTest1);
		collectionSimpleObject.add(fastTest2);
		collectionSimpleObject.add(fastTest1);
		collectionSimpleObject.add(fastTest2);
		
		Engine en = new Engine();
		/*CellDecorator configuration = new CellDecorator();
		configuration.setAlignment(CellStyle.ALIGN_CENTER);
		configuration.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

		configuration.setBorderLeft(CellStyle.BORDER_THIN);
		configuration.setBorderRight(CellStyle.BORDER_THIN);
		configuration.setBorderTop(CellStyle.BORDER_THIN);
		configuration.setBorderBottom(CellStyle.BORDER_THIN);
		
		configuration.setBackgroundColor(HSSFColor.DARK_RED.index);
		configuration.setFontBold(true);
		configuration.setFontItalic(true);
		configuration.setWrapText(true);
		en.setHeaderCellDecorator(configuration);*/
		
		en.marshalAsCollection(collectionSimpleObject, "filename" , ExtensionFileType.XLSX);
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

				
				for (SimpleObject fastTest : collectionSimpleObject){
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
	
	
}
