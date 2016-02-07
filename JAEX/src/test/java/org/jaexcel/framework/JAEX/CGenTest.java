package org.jaexcel.framework.JAEX;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jaexcel.framework.JAEX.bean.MultiTypeObject;
import org.jaexcel.framework.JAEX.bean.MultiTypeObjectBuilder;
import org.jaexcel.framework.JAEX.bean.SimpleObject;
import org.jaexcel.framework.JAEX.engine.CGen;
import org.jaexcel.framework.JAEX.engine.IGeneratorCSV;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class CGenTest extends TestCase {

	private static final String WORKING_DIR = "D:\\projects\\";

	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public CGenTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(CGenTest.class);
	}

	public void testMarshalUnmarshalSimple() throws Exception {
		SimpleObject fastTest = new SimpleObject();
		fastTest.setDateAttribute(new Date());
		fastTest.setStringAttribute("some string");
		fastTest.setIntegerAttribute(46);

		IGeneratorCSV en = new CGen();
		en.marshalAndSave(fastTest, WORKING_DIR, "csvTest.csv");

		SimpleObject fastTestReader = new SimpleObject();

		en.unmarshalFromPath(fastTestReader, WORKING_DIR);

		// TODO add validator
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void testMarshalUnmarshalAsCollectionSimple() throws Exception {
		
		IGeneratorCSV en = new CGen();

		List listMulti = MultiTypeObjectBuilder.buildListOfMultiTypeObject(10000);

		en.marshalAsCollectionAndSave(listMulti, WORKING_DIR, "Boom");

		List<Object> listReader = new ArrayList<>();

		en.unmarshalAsCollectionFromPath(listReader, WORKING_DIR, "csvTestList");

	}

	public void testMarshalUnmarshalMulti() throws Exception {
		MultiTypeObject mto = MultiTypeObjectBuilder.buildMultiTypeObject();

		IGeneratorCSV en = new CGen();
		en.marshalAndSave(mto, WORKING_DIR, "csvMultiTest.csv");

		MultiTypeObject mtoReader = new MultiTypeObject();

		en.unmarshalFromPath(mtoReader, WORKING_DIR);

		MultiTypeObjectBuilder.validateMultiTypeObject(mtoReader);
	}
}
