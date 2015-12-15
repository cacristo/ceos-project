package org.jaexcel.framework.JAEX;

import java.util.Date;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.jaexcel.framework.JAEX.bean.AddressInfo;
import org.jaexcel.framework.JAEX.bean.Job;
import org.jaexcel.framework.JAEX.bean.MultiTypeObject;
import org.jaexcel.framework.JAEX.engine.Engine;
import org.jaexcel.framework.JAEX.engine.CellDecorator;

/**
 * Unit test for simple App.
 */
public class MultiTypeObjectTest extends TestCase {

	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public MultiTypeObjectTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(MultiTypeObjectTest.class);
	}

	/**
	 * Test one basic object
	 */
	public void testMarshalMultiObject() throws Exception {
		MultiTypeObject mto = buildMultiTypeObject();

		Engine en = new Engine();

		CellDecorator configuration = new CellDecorator();
		configuration.setAlignment(CellStyle.ALIGN_CENTER);
		configuration.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

		configuration.setBorder(CellStyle.BORDER_THIN);
		
		configuration.setBackgroundColor(HSSFColor.DARK_YELLOW.index);
		configuration.setFontBold(true);
		configuration.setFontItalic(true);
		configuration.setWrapText(true);
		en.initializeHeaderDecorator(configuration);

		en.marshal(mto);

		// TODO validation result
		// assertEquals(true, false);
	}

	/**
	 * Test one basic object
	 */
	public void testUnmarshalMultiObject() throws Exception {
		MultiTypeObject mto = buildMultiTypeObject();

		Engine en = new Engine();

		en.unmarshal(mto);

		// TODO validation result
		// assertEquals(true, false);
	}

	
	
	/**
	 * Create a multi type object for tests.
	 * 
	 * @return
	 */
	private MultiTypeObject buildMultiTypeObject() {
		MultiTypeObject mto = new MultiTypeObject();
		mto.setDateAttribute(new Date());
		mto.setStringAttribute("some string");
		mto.setIntegerAttribute(46);
		mto.setDoubleAttribute(Double.valueOf("25.3"));
		mto.setLongAttribute(Long.valueOf("1234567890"));
		mto.setBooleanAttribute(Boolean.FALSE);

		Job job = new Job();
		job.setJobCode(0005);
		job.setJobFamily("Family Job Name");
		job.setJobName("Job Name");
		mto.setJob(job);

		mto.setIntegerPrimitiveAttribute(121);
		mto.setDoublePrimitiveAttribute(44.6);
		mto.setLongPrimitiveAttribute(987654321L);
		mto.setBooleanPrimitiveAttribute(true);

		AddressInfo ai = new AddressInfo();
		ai.setAddress("this is the street");
		ai.setNumber(99);
		ai.setCity("this is the city");
		ai.setCityCode(70065);
		ai.setCountry("This is a Country");
		mto.setAddressInfo(ai);
		return mto;
	}

}
