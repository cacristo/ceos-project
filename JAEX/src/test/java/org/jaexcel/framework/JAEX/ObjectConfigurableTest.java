package org.jaexcel.framework.JAEX;

import java.util.Calendar;
import java.util.Date;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.jaexcel.framework.JAEX.bean.ObjectConfigurable;
import org.jaexcel.framework.JAEX.engine.CellDecorator;
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
		ObjectConfigurable oc = new ObjectConfigurable();

		oc.setBooleanAttribute(true);
		oc.setDateAttribute(new Date());
		oc.setDoubleAttribute(22.127);
		oc.setIntegerAttribute(46);
		oc.setLongAttribute(98765312L);
		oc.setStringAttribute("Some string value");
		oc.setDateAttribute1(new Date());

		IEngine en = new Engine();

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
		en.overrideHeaderCellDecorator(configurationHeader);

		CellDecorator dateDecorator = new CellDecorator();
		dateDecorator.setDecoratorName("anotherDate");
		dateDecorator.setAlignment(CellStyle.ALIGN_CENTER);
		dateDecorator.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

		dateDecorator.setForegroundColor(HSSFColor.ORANGE.index);
		dateDecorator.setFontItalic(true);
		dateDecorator.setWrapText(true);

		// en.addSpecificCellDecorator("anotherDate", dateDecorator);

		en.marshal(oc);

		// TODO validation result
		// assertEquals(true, false);
	}

	/**
	 * Test one basic object
	 */
	public void testUnmarshalMultiObject() throws Exception {
		ObjectConfigurable oc = new ObjectConfigurable();

		Engine en = new Engine();

		en.unmarshal(oc);

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		
		Calendar calendarUnmarshal = Calendar.getInstance();
		calendarUnmarshal.setTime(oc.getDateAttribute());
		assertEquals(calendar.get(Calendar.YEAR), calendarUnmarshal.get(Calendar.YEAR));
		assertEquals(calendar.get(Calendar.MONTH), calendarUnmarshal.get(Calendar.MONTH));
		assertEquals(calendar.get(Calendar.DAY_OF_MONTH), calendarUnmarshal.get(Calendar.DAY_OF_MONTH));
		assertEquals(Double.valueOf(22.127), oc.getDoubleAttribute());
		assertEquals(Integer.valueOf(46), oc.getIntegerAttribute());
		assertEquals(Long.valueOf(98765312L), oc.getLongAttribute());
		assertEquals("Some string value", oc.getStringAttribute());
		Calendar calendarUnmarshal1 = Calendar.getInstance();
		calendarUnmarshal1.setTime(oc.getDateAttribute1());
		assertEquals(calendar.get(Calendar.YEAR), calendarUnmarshal1.get(Calendar.YEAR));
		assertEquals(calendar.get(Calendar.MONTH), calendarUnmarshal1.get(Calendar.MONTH));
		assertEquals(calendar.get(Calendar.DAY_OF_MONTH), calendarUnmarshal1.get(Calendar.DAY_OF_MONTH));
		
		// the object is null
		assertEquals(0, oc.getJob().getJobCode());
		assertEquals("", oc.getJob().getJobFamily());
		assertEquals("", oc.getJob().getJobName());
	}
}
