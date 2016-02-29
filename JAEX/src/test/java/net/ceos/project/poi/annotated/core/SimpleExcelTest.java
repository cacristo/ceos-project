package net.ceos.project.poi.annotated.core;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.testng.annotations.Test;

import net.ceos.project.poi.annotated.annotation.XlsConfiguration;
import net.ceos.project.poi.annotated.annotation.XlsElement;
import net.ceos.project.poi.annotated.annotation.XlsNestedHeader;
import net.ceos.project.poi.annotated.annotation.XlsSheet;
import net.ceos.project.poi.annotated.bean.SimpleObject;
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
	 * Test one basic object
	 */
	@Test
	public void testSimpleExcel() throws Exception {
		SimpleObject fastTest = new SimpleObject();
		fastTest.setDateAttribute(new Date());
		fastTest.setStringAttribute("some string");
		fastTest.setIntegerAttribute(46);

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

		ConfigCriteria configCriteria = new ConfigCriteria();
		configCriteria.overrideHeaderCellDecorator(configuration);

		en.marshalAndSave(configCriteria, fastTest, TestUtils.WORKING_DIR_GENERATED_II);

		// start validation
		Class<SimpleObject> oC = SimpleObject.class;

		String nameFile = "";
		String extensionFile = "";
		// int cascadeLevel = -1;
		// Process @XlsConfiguration
		if (oC.isAnnotationPresent(XlsConfiguration.class)) {
			XlsConfiguration xlsAnnotation = (XlsConfiguration) oC.getAnnotation(XlsConfiguration.class);

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
			XlsSheet xlsAnnotation = (XlsSheet) oC.getAnnotation(XlsSheet.class);

			titleSheet = xlsAnnotation.title();
			// propagationType = xlsAnnotation.propagation().getCode();
			idxRow = xlsAnnotation.startRow();
			idxCell = xlsAnnotation.startCell();
		}

		FileInputStream input = new FileInputStream("D:\\" + nameFile + extensionFile);
		HSSFWorkbook wb = new HSSFWorkbook(input);
		HSSFSheet sheet = wb.getSheet(titleSheet);

		List<Field> fL = Arrays.asList(oC.getDeclaredFields());
		for (Field f : fL) {
			// Process @XlsElement
			if (f.isAnnotationPresent(XlsElement.class)) {
				XlsElement xlsAnnotation = (XlsElement) f.getAnnotation(XlsElement.class);

				// header row
				HSSFRow headerRow = sheet.getRow(idxRow);
				HSSFCell headerCell = headerRow.getCell(idxCell + xlsAnnotation.position());
				// content row
				HSSFRow contentRow = sheet.getRow(idxRow + 1);
				HSSFCell contentCell = contentRow.getCell(idxCell + xlsAnnotation.position());

				if (xlsAnnotation.position() == 1) {
					// FIXME
					// TestUtils.validationString(fastTest.getDateAttribute(),
					// xlsAnnotation, headerCell, contentCell);
				} else if (xlsAnnotation.position() == 2) {
					TestUtils.validationString(fastTest.getStringAttribute(), xlsAnnotation, headerCell, contentCell);
				} else if (xlsAnnotation.position() == 3) {
					TestUtils.validationNumeric(fastTest.getIntegerAttribute(), xlsAnnotation, headerCell, contentCell);
				}
			}
		}
		wb.close();
	}
}
