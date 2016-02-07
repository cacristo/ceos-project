package org.jaexcel.framework.JAEX;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.jaexcel.framework.JAEX.bean.MultiTypeObject;
import org.jaexcel.framework.JAEX.bean.MultiTypeObjectBuilder;
import org.jaexcel.framework.JAEX.engine.CellDecorator;
import org.jaexcel.framework.JAEX.engine.ConfigCriteria;
import org.jaexcel.framework.JAEX.engine.Engine;
import org.jaexcel.framework.JAEX.engine.IEngine;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class MultiTypeAttributesTest extends TestCase {

	private static final String WORKING_DIR = "D:\\projects";

	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public MultiTypeAttributesTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(MultiTypeAttributesTest.class);
	}

	/**
	 * Test one basic object
	 */
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

		en.marshalAndSave(configCriteria, mto, WORKING_DIR);

		assertEquals(true, true);
	}

	/**
	 * Test one basic object
	 */
	public void testUnmarshalMultiObject() throws Exception {
		MultiTypeObject mto = new MultiTypeObject();

		Engine en = new Engine();

		en.unmarshalFromPath(mto, WORKING_DIR + "\\");

		MultiTypeObjectBuilder.validateMultiTypeObject(mto);
		
	}
}