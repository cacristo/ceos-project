package org.jaexcel.framework.JAEX;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.jaexcel.framework.JAEX.bean.ObjectConfigurable;
import org.jaexcel.framework.JAEX.bean.ObjectConfigurableBuilder;
import org.jaexcel.framework.JAEX.engine.CellDecorator;
import org.jaexcel.framework.JAEX.engine.ConfigCriteria;
import org.jaexcel.framework.JAEX.engine.Engine;
import org.jaexcel.framework.JAEX.engine.IEngine;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ObjectConfigurableTest extends TestCase {

	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public ObjectConfigurableTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(ObjectConfigurableTest.class);
	}

	/**
	 * Test one basic object
	 */
	public void testMarshalMultiObject() throws Exception {
		ObjectConfigurable oc = ObjectConfigurableBuilder.buildObjectConfigurable();

		IEngine en = new Engine();

		ConfigCriteria configCriteria = new ConfigCriteria();

		CellDecorator configurationHeader = new CellDecorator();
		// override default header configuration
		configurationHeader.setDecoratorName("header");
		configurationHeader.setAlignment(CellStyle.ALIGN_CENTER);
		configurationHeader.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

		configurationHeader.setBorder(CellStyle.BORDER_THIN);

		configurationHeader.setForegroundColor(HSSFColor.BLUE.index);
		configurationHeader.setFontBold(true);
		configurationHeader.setFontItalic(true);
		configurationHeader.setWrapText(true);
		// en.overrideHeaderCellDecorator(configurationHeader);
		configCriteria.overrideHeaderCellDecorator(configurationHeader);

		CellDecorator dateDecorator = new CellDecorator();
		dateDecorator.setDecoratorName("anotherDate");
		dateDecorator.setAlignment(CellStyle.ALIGN_CENTER);
		dateDecorator.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

		dateDecorator.setForegroundColor(HSSFColor.RED.index);
		dateDecorator.setFontItalic(true);
		dateDecorator.setWrapText(true);

		// en.addSpecificCellDecorator("anotherDate", dateDecorator);
		configCriteria.addSpecificCellDecorator("anotherDate", dateDecorator);

		en.marshalAndSave(configCriteria, oc, TestUtils.WORKING_DIR_GENERATED_II);
	}

	/**
	 * Test one basic object
	 */
	public void testUnmarshalMultiObject() throws Exception {
		ObjectConfigurable oc = new ObjectConfigurable();

		Engine en = new Engine();

		en.unmarshalFromPath(oc, TestUtils.WORKING_DIR_GENERATED_I + "\\");

		ObjectConfigurableBuilder.validateObjectConfigurable(oc);
	}
}
