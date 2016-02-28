package net.ceos.project.poi.annotated.core;

import org.apache.poi.ss.usermodel.Workbook;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import net.ceos.project.poi.annotated.bean.MultiTypeObject;
import net.ceos.project.poi.annotated.bean.MultiTypeObjectBuilder;
import net.ceos.project.poi.annotated.core.Engine;
import net.ceos.project.poi.annotated.core.IEngine;

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
	 * Test the method 'marshalAndSave' to generate the Excel from the object
	 * and save it at the path file indicated.
	 */
	public void testMarshalPath() throws Exception {

		MultiTypeObject mto = MultiTypeObjectBuilder.buildMultiTypeObject();
		String outputPath = TestUtils.WORKING_DIR_GENERATED_I + "\\";

		IEngine en = new Engine();
		en.marshalAndSave(mto, outputPath);
	}

	/**
	 * Test the method 'unmarshalFromPath' reading the Excel from a specific
	 * path file indicated and bring the data to the object.
	 */
	public void testUnmarshalPath() throws Exception {

		MultiTypeObject charger = new MultiTypeObject();

		IEngine en = new Engine();
		en.unmarshalFromPath(charger, TestUtils.WORKING_DIR_GENERATED_I);

		MultiTypeObjectBuilder.validateMultiTypeObject(charger);
	}

	/**
	 * Test the method 'marshalToSheet' to generate the Excel from the object
	 * and return the Sheet generated.<br>
	 * After that, test the method 'unmarshalFromSheet' reading the Excel from
	 * the Sheet passed as parameter and bring the data to the object.
	 */
	public void testMarshalSheet() throws Exception {
		MultiTypeObject mto = MultiTypeObjectBuilder.buildMultiTypeObject();

		IEngine en = new Engine();
		en.marshalToSheet(mto);

	}

	/**
	 * Test the method 'marshalToWorkbook' to generate the Excel from the object
	 * and return the Workbook generated.<br>
	 * After that, test the method 'unmarshalFromWorkbook' reading the Excel
	 * from the Workbook passed as parameter and bring the data to the object.
	 */
	public void testMarshalUnmarshalWorkbook() throws Exception {
		MultiTypeObject mto = MultiTypeObjectBuilder.buildMultiTypeObject();

		IEngine en = new Engine();
		Workbook wb = en.marshalToWorkbook(mto);

		MultiTypeObject charger = new MultiTypeObject();
		en.unmarshalFromWorkbook(charger, wb);

		MultiTypeObjectBuilder.validateMultiTypeObject(charger);
	}

	/**
	 * Test the method 'marshalToByte' to generate the Excel from the object and
	 * return the byte[] generated.<br>
	 * After that, test the method 'unmarshalFromByte' reading the Excel from
	 * the byte[] passed as parameter and bring the data to the object.
	 */
	public void testMarshalUnmarshalByte() throws Exception {

		MultiTypeObject mto = MultiTypeObjectBuilder.buildMultiTypeObject();

		IEngine en = new Engine();
		byte[] generatedBytes = en.marshalToByte(mto);

		MultiTypeObject charger = new MultiTypeObject();
		en.unmarshalFromByte(charger, generatedBytes);

		MultiTypeObjectBuilder.validateMultiTypeObject(charger);
	}

}
