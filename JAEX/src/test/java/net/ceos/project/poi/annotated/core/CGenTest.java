package net.ceos.project.poi.annotated.core;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import net.ceos.project.poi.annotated.bean.MultiTypeObject;
import net.ceos.project.poi.annotated.bean.MultiTypeObjectBuilder;
import net.ceos.project.poi.annotated.bean.SimpleObject;
import net.ceos.project.poi.annotated.core.CGen;
import net.ceos.project.poi.annotated.core.IGeneratorCSV;

public class CGenTest extends TestCase {

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
		en.marshalAndSave(fastTest, TestUtils.WORKING_DIR_GENERATED_I, "csvTest.csv");

		SimpleObject fastTestReader = new SimpleObject();

		en.unmarshalFromPath(fastTestReader, TestUtils.WORKING_DIR_GENERATED_I);

		// TODO add validator
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void testMarshalUnmarshalAsCollectionSimple() throws Exception {
		
		IGeneratorCSV en = new CGen();

		List listMulti = MultiTypeObjectBuilder.buildListOfMultiTypeObject(10000);

		en.marshalAsCollectionAndSave(listMulti, TestUtils.WORKING_DIR_GENERATED_I, "Boom");

		List<Object> listReader = new ArrayList<>();

		en.unmarshalAsCollectionFromPath(listReader, TestUtils.WORKING_DIR_MANUALLY, "csvTestList");

	}

	public void testMarshalUnmarshalMulti() throws Exception {
		MultiTypeObject mto = MultiTypeObjectBuilder.buildMultiTypeObject();

		IGeneratorCSV en = new CGen();
		en.marshalAndSave(mto, TestUtils.WORKING_DIR_GENERATED_I, "csvMultiTest.csv");

		MultiTypeObject mtoReader = new MultiTypeObject();

		en.unmarshalFromPath(mtoReader, TestUtils.WORKING_DIR_GENERATED_I);

		MultiTypeObjectBuilder.validateMultiTypeObject(mtoReader);
	}
}
