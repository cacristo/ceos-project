package org.jaexcel.framework.JAEX;

import java.util.Date;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.jaexcel.framework.JAEX.bean.AddressInfo;
import org.jaexcel.framework.JAEX.bean.Job;
import org.jaexcel.framework.JAEX.bean.MultiTypeObject;
import org.jaexcel.framework.JAEX.engine.CellDecorator;
import org.jaexcel.framework.JAEX.engine.Engine;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

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
		configuration.setDecoratorName("header");
		configuration.setAlignment(CellStyle.ALIGN_CENTER);
		configuration.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		configuration.setBorder(CellStyle.BORDER_DOTTED);
		configuration.setForegroundColor(HSSFColor.ORANGE.index);
		configuration.setFontBold(true);
		configuration.setFontItalic(true);
		configuration.setWrapText(true);

		CellDecorator anotherDate = new CellDecorator();
		anotherDate.setDecoratorName("anotherDate");
		anotherDate.setAlignment(CellStyle.ALIGN_CENTER);
		anotherDate.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		anotherDate.setForegroundColor(HSSFColor.ORANGE.index);
		anotherDate.setFontItalic(true);
		anotherDate.setWrapText(true);

		en.setHeaderCellDecorator(configuration);
		en.addSpecificCellDecorator("anotherDate", anotherDate);

		en.marshal(mto);

		assertEquals(true, true);
	}

	/**
	 * Test one basic object
	 */
	public void testUnmarshalMultiObject() throws Exception {
		MultiTypeObject mto = new MultiTypeObject();

		Engine en = new Engine();

		en.unmarshal(mto);

		assertEquals("some string", mto.getStringAttribute());
		assertEquals(Integer.valueOf(46), mto.getIntegerAttribute());
		assertEquals(Double.valueOf("25.3"), mto.getDoubleAttribute());
		assertEquals(Long.valueOf("1234567890"), mto.getLongAttribute());
		assertEquals(Boolean.FALSE, mto.getBooleanAttribute());

		assertEquals(5, mto.getJob().getJobCode());
		assertEquals("Family Job Name", mto.getJob().getJobFamily());
		assertEquals("Job Name", mto.getJob().getJobName());

		assertEquals(121, mto.getIntegerPrimitiveAttribute());
		assertEquals(44.6, mto.getDoublePrimitiveAttribute());
		assertEquals(987654321L, mto.getLongPrimitiveAttribute());
		assertEquals(true, mto.isBooleanPrimitiveAttribute());

		assertEquals("this is the street", mto.getAddressInfo().getAddress());
		assertEquals(99, mto.getAddressInfo().getNumber());
		assertEquals("this is the city", mto.getAddressInfo().getCity());
		assertEquals(70065, mto.getAddressInfo().getCityCode());
		assertEquals("This is a Country", mto.getAddressInfo().getCountry());

		assertEquals(14.765f, mto.getFloatAttribute());
		assertEquals(11.1125f, mto.getFloatPrimitiveAttribute());
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

		mto.setFloatAttribute(14.765f);
		mto.setFloatPrimitiveAttribute(11.1125f);
		return mto;
	}

}
