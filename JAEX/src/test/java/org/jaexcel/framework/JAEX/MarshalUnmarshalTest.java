package org.jaexcel.framework.JAEX;

import java.util.Calendar;
import java.util.Date;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.jaexcel.framework.JAEX.bean.AddressInfo;
import org.jaexcel.framework.JAEX.bean.Job;
import org.jaexcel.framework.JAEX.bean.MultiTypeObject;
import org.jaexcel.framework.JAEX.bean.UnitFamily;
import org.jaexcel.framework.JAEX.engine.Engine;
import org.jaexcel.framework.JAEX.engine.IEngine;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class MarshalUnmarshalTest extends TestCase {

	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public MarshalUnmarshalTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(MarshalUnmarshalTest.class);
	}

	/**
	 * Test the method 'marshalToSheet' to generate the Excel from the object and
	 * return the Sheet generated.<br>
	 * After that, test the method 'unmarshalFromSheet' reading the Excel from
	 * the Sheet passed as parameter and bring the data to the object.
	 */
	public void testMarshalSheet() throws Exception {
		MultiTypeObject mto = buildMultiTypeObject();

		IEngine en = new Engine();
		Sheet s = en.marshalToSheet(mto);

		/*MultiTypeObject charger = new MultiTypeObject();
		en.unmarshalFromWorkbook(charger, wb);

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());

		Calendar calendarUnmarshal = Calendar.getInstance();
		calendarUnmarshal.setTime(mto.getDateAttribute());
		assertEquals(calendar.get(Calendar.YEAR), calendarUnmarshal.get(Calendar.YEAR));
		assertEquals(calendar.get(Calendar.MONTH), calendarUnmarshal.get(Calendar.MONTH));
		assertEquals(calendar.get(Calendar.DAY_OF_MONTH), calendarUnmarshal.get(Calendar.DAY_OF_MONTH));
		assertEquals("some string", charger.getStringAttribute());
		assertEquals(Integer.valueOf(46), charger.getIntegerAttribute());
		assertEquals(Double.valueOf("25.3"), charger.getDoubleAttribute());
		assertEquals(Long.valueOf("1234567890"), charger.getLongAttribute());
		assertEquals(Boolean.FALSE, charger.getBooleanAttribute());

		assertEquals(5, charger.getJob().getJobCode());
		assertEquals("Family Job Name", charger.getJob().getJobFamily());
		assertEquals("Job Name", charger.getJob().getJobName());

		assertEquals(121, charger.getIntegerPrimitiveAttribute());
		assertEquals(44.6, charger.getDoublePrimitiveAttribute());
		assertEquals(987654321L, charger.getLongPrimitiveAttribute());
		assertEquals(true, charger.isBooleanPrimitiveAttribute());

		assertEquals("this is the street", charger.getAddressInfo().getAddress());
		assertEquals(99, charger.getAddressInfo().getNumber());
		assertEquals("this is the city", charger.getAddressInfo().getCity());
		assertEquals(70065, charger.getAddressInfo().getCityCode());
		assertEquals("This is a Country", charger.getAddressInfo().getCountry());

		assertEquals(14.765f, charger.getFloatAttribute());
		assertEquals(11.1125f, charger.getFloatPrimitiveAttribute());

		assertEquals(UnitFamily.COMPONENTS, charger.getUnitFamily());*/
	}

	/**
	 * Test the method 'marshalToWorkbook' to generate the Excel from the object and
	 * return the Workbook generated.<br>
	 * After that, test the method 'unmarshalFromWorkbook' reading the Excel from
	 * the Workbook passed as parameter and bring the data to the object.
	 */
	public void testMarshalUnmarshalWorkbook() throws Exception {
		MultiTypeObject mto = buildMultiTypeObject();

		IEngine en = new Engine();
		Workbook wb = en.marshalToWorkbook(mto);

		MultiTypeObject charger = new MultiTypeObject();
		en.unmarshalFromWorkbook(charger, wb);

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());

		Calendar calendarUnmarshal = Calendar.getInstance();
		calendarUnmarshal.setTime(mto.getDateAttribute());
		assertEquals(calendar.get(Calendar.YEAR), calendarUnmarshal.get(Calendar.YEAR));
		assertEquals(calendar.get(Calendar.MONTH), calendarUnmarshal.get(Calendar.MONTH));
		assertEquals(calendar.get(Calendar.DAY_OF_MONTH), calendarUnmarshal.get(Calendar.DAY_OF_MONTH));
		assertEquals("some string", charger.getStringAttribute());
		assertEquals(Integer.valueOf(46), charger.getIntegerAttribute());
		assertEquals(Double.valueOf("25.3"), charger.getDoubleAttribute());
		assertEquals(Long.valueOf("1234567890"), charger.getLongAttribute());
		assertEquals(Boolean.FALSE, charger.getBooleanAttribute());

		assertEquals(5, charger.getJob().getJobCode());
		assertEquals("Family Job Name", charger.getJob().getJobFamily());
		assertEquals("Job Name", charger.getJob().getJobName());

		assertEquals(121, charger.getIntegerPrimitiveAttribute());
		assertEquals(44.6, charger.getDoublePrimitiveAttribute());
		assertEquals(987654321L, charger.getLongPrimitiveAttribute());
		assertEquals(true, charger.isBooleanPrimitiveAttribute());

		assertEquals("this is the street", charger.getAddressInfo().getAddress());
		assertEquals(99, charger.getAddressInfo().getNumber());
		assertEquals("this is the city", charger.getAddressInfo().getCity());
		assertEquals(70065, charger.getAddressInfo().getCityCode());
		assertEquals("This is a Country", charger.getAddressInfo().getCountry());

		assertEquals(14.765f, charger.getFloatAttribute());
		assertEquals(11.1125f, charger.getFloatPrimitiveAttribute());

		assertEquals(UnitFamily.COMPONENTS, charger.getUnitFamily());
	}

	/**
	 * Test the method 'marshalAndSave' to generate the Excel from the object
	 * and save it at the path file indicated.<br>
	 * After that, test the method 'unmarshalFromPath' reading the Excel from a
	 * specific path file indicated and bring the data to the object.
	 */
	public void testMarshalUnmarshalPath() throws Exception {

		MultiTypeObject mto = buildMultiTypeObject();
		String outputPath = "D:\\projects\\";
		String inputPath = "D:\\projects";

		IEngine en = new Engine();
		en.marshalAndSave(mto, outputPath);

		MultiTypeObject charger = new MultiTypeObject();
		en.unmarshalFromPath(charger, inputPath);

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());

		Calendar calendarUnmarshal = Calendar.getInstance();
		calendarUnmarshal.setTime(mto.getDateAttribute());
		assertEquals(calendar.get(Calendar.YEAR), calendarUnmarshal.get(Calendar.YEAR));
		assertEquals(calendar.get(Calendar.MONTH), calendarUnmarshal.get(Calendar.MONTH));
		assertEquals(calendar.get(Calendar.DAY_OF_MONTH), calendarUnmarshal.get(Calendar.DAY_OF_MONTH));
		assertEquals("some string", charger.getStringAttribute());
		assertEquals(Integer.valueOf(46), charger.getIntegerAttribute());
		assertEquals(Double.valueOf("25.3"), charger.getDoubleAttribute());
		assertEquals(Long.valueOf("1234567890"), charger.getLongAttribute());
		assertEquals(Boolean.FALSE, charger.getBooleanAttribute());

		assertEquals(5, charger.getJob().getJobCode());
		assertEquals("Family Job Name", charger.getJob().getJobFamily());
		assertEquals("Job Name", charger.getJob().getJobName());

		assertEquals(121, charger.getIntegerPrimitiveAttribute());
		assertEquals(44.6, charger.getDoublePrimitiveAttribute());
		assertEquals(987654321L, charger.getLongPrimitiveAttribute());
		assertEquals(true, charger.isBooleanPrimitiveAttribute());

		assertEquals("this is the street", charger.getAddressInfo().getAddress());
		assertEquals(99, charger.getAddressInfo().getNumber());
		assertEquals("this is the city", charger.getAddressInfo().getCity());
		assertEquals(70065, charger.getAddressInfo().getCityCode());
		assertEquals("This is a Country", charger.getAddressInfo().getCountry());

		assertEquals(14.765f, charger.getFloatAttribute());
		assertEquals(11.1125f, charger.getFloatPrimitiveAttribute());

		assertEquals(UnitFamily.COMPONENTS, charger.getUnitFamily());
	}

	/**
	 * Test the method 'marshalToByte' to generate the Excel from the object and
	 * return the byte[] generated.<br>
	 * After that, test the method 'unmarshalFromByte' reading the Excel from
	 * the byte[] passed as parameter and bring the data to the object.
	 */
	public void testMarshalUnmarshalByte() throws Exception {

		MultiTypeObject mto = buildMultiTypeObject();

		IEngine en = new Engine();
		byte[] generatedBytes = en.marshalToByte(mto);

		MultiTypeObject charger = new MultiTypeObject();
		en.unmarshalFromByte(charger, generatedBytes);

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());

		Calendar calendarUnmarshal = Calendar.getInstance();
		calendarUnmarshal.setTime(mto.getDateAttribute());
		assertEquals(calendar.get(Calendar.YEAR), calendarUnmarshal.get(Calendar.YEAR));
		assertEquals(calendar.get(Calendar.MONTH), calendarUnmarshal.get(Calendar.MONTH));
		assertEquals(calendar.get(Calendar.DAY_OF_MONTH), calendarUnmarshal.get(Calendar.DAY_OF_MONTH));
		assertEquals("some string", charger.getStringAttribute());
		assertEquals(Integer.valueOf(46), charger.getIntegerAttribute());
		assertEquals(Double.valueOf("25.3"), charger.getDoubleAttribute());
		assertEquals(Long.valueOf("1234567890"), charger.getLongAttribute());
		assertEquals(Boolean.FALSE, charger.getBooleanAttribute());

		assertEquals(5, charger.getJob().getJobCode());
		assertEquals("Family Job Name", charger.getJob().getJobFamily());
		assertEquals("Job Name", charger.getJob().getJobName());

		assertEquals(121, charger.getIntegerPrimitiveAttribute());
		assertEquals(44.6, charger.getDoublePrimitiveAttribute());
		assertEquals(987654321L, charger.getLongPrimitiveAttribute());
		assertEquals(true, charger.isBooleanPrimitiveAttribute());

		assertEquals("this is the street", charger.getAddressInfo().getAddress());
		assertEquals(99, charger.getAddressInfo().getNumber());
		assertEquals("this is the city", charger.getAddressInfo().getCity());
		assertEquals(70065, charger.getAddressInfo().getCityCode());
		assertEquals("This is a Country", charger.getAddressInfo().getCountry());

		assertEquals(14.765f, charger.getFloatAttribute());
		assertEquals(11.1125f, charger.getFloatPrimitiveAttribute());

		assertEquals(UnitFamily.COMPONENTS, charger.getUnitFamily());
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

		mto.setUnitFamily(UnitFamily.COMPONENTS);

		return mto;
	}
}
