package org.jaexcel.framework.JAEX;

import java.util.Date;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.jaexcel.framework.JAEX.bean.Job;
import org.jaexcel.framework.JAEX.bean.ObjectConfigurable;
import org.jaexcel.framework.JAEX.engine.CellDecorator;
import org.jaexcel.framework.JAEX.engine.Engine;

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
		
		Job job = new Job();
		job.setJobCode(0005);
		job.setJobFamily("Family Job Name");
		job.setJobName("Job Name");
		//oc.setJob(job);
		
		/************/
		Engine en = new Engine();

		CellDecorator configurationHeader = new CellDecorator();
		// override default header configuration
		configurationHeader.setDecoratorName("header");
		configurationHeader.setAlignment(CellStyle.ALIGN_CENTER);
		configurationHeader.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

		configurationHeader.setBorder(CellStyle.BORDER_THIN);
		
		configurationHeader.setForegroundColor(HSSFColor.WHITE.index);
		configurationHeader.setFontBold(true);
		configurationHeader.setFontItalic(true);
		configurationHeader.setWrapText(true);
		//en.setHeaderCellDecorator(configurationHeader);
		
		CellDecorator dateDecorator = new CellDecorator();
		dateDecorator.setDecoratorName("anotherDate");
		dateDecorator.setAlignment(CellStyle.ALIGN_CENTER);
		dateDecorator.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		
		dateDecorator.setForegroundColor(HSSFColor.ORANGE.index);
		dateDecorator.setFontItalic(true);
		dateDecorator.setWrapText(true);
		
		//en.addSpecificCellDecorator("anotherDate", dateDecorator);

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


		System.out.println(oc.getDateAttribute());
		System.out.println(oc.getStringAttribute());
		System.out.println(oc.getIntegerAttribute());
		System.out.println(oc.getDoubleAttribute());
		System.out.println(oc.getLongAttribute());
		System.out.println(oc.getBooleanAttribute());
		System.out.println(oc.getDateAttribute1());

		System.out.println(oc.getJob().getJobCode());
		System.out.println(oc.getJob().getJobName());
		System.out.println(oc.getJob().getJobFamily());
		// TODO validation result
		// assertEquals(true, false);
	}
}
