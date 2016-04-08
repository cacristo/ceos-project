package net.ceos.project.poi.annotated.core;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.testng.annotations.Test;

import net.ceos.project.poi.annotated.annotation.XlsElement;
import net.ceos.project.poi.annotated.bean.MultiTypeObject;
import net.ceos.project.poi.annotated.bean.MultiTypeObjectBuilder;

/**
 * Test multiple type of attributes at {@link XlsElement} to verify if
 * everything work properly.
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public class MultiTypeAttributesTest {

	/**
	 * Test marshal to multiple types of objects
	 */
	@Test
	public void testMarshalMultiObject() throws Exception {
		MultiTypeObject mto = MultiTypeObjectBuilder.buildMultiTypeObject();

		IEngine en = new Engine();

		CellDecorator configuration = new CellDecorator();
		configuration.setDecoratorName("header");
		configuration.setAlignment(CellStyle.ALIGN_CENTER);
		configuration.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		configuration.setBorder(CellStyle.BORDER_DOTTED);
		configuration.setForegroundColor(HSSFColor.YELLOW.index);
		configuration.setFontBold(true);
		configuration.setFontItalic(true);
		configuration.setWrapText(true);

		CellDecorator anotherDate = new CellDecorator();
		anotherDate.setDecoratorName("anotherDate");
		anotherDate.setAlignment(CellStyle.ALIGN_CENTER);
		anotherDate.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		anotherDate.setForegroundColor(HSSFColor.LIGHT_GREEN.index);
		anotherDate.setFontItalic(true);
		anotherDate.setWrapText(true);

		ConfigCriteria configCriteria = new ConfigCriteria();
		configCriteria.overrideHeaderCellDecorator(configuration);
		configCriteria.addSpecificCellDecorator("anotherDate", anotherDate);

		en.marshalAndSave(configCriteria, mto, TestUtils.WORKING_DIR_GENERATED_I);

	}

	/**
	 * Test if the unmarshal to multiple types of objects works properly.
	 */
	@Test
	public void testUnmarshalMultiObject() throws Exception {
		MultiTypeObject mto = new MultiTypeObject();

		Engine en = new Engine();
		en.unmarshalFromPath(mto, TestUtils.WORKING_DIR_GENERATED_I + "\\");

		MultiTypeObjectBuilder.validateMultiTypeObject(mto);

	}
}
