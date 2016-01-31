package org.jaexcel.framework.JAEX.engine;

import org.jaexcel.framework.JAEX.bean.MultiTypeObject;
import org.jaexcel.framework.JAEX.definition.JAEXExceptionMessage;
import org.jaexcel.framework.JAEX.exception.JAEXElementException;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class EngineTest extends TestCase {

	/*
	 * TODO (1) see the behavior of using only one instance of the JAEX object
	 * inside one project
	 */
	/* (1.1) see the wb object */
	/* (1.2) see the configuration object */
	/* (1.3) see the stylesMap object */
	/* (1.4) see the cellDecoratorMap object */
	/* (1.4) see the headerDecorator object */

	/* TODO (2) manage the internal value of an Enum */

	/*
	 * TODO (3) fix numeric code like 00005 parsed to excel will maintain the
	 * same code to do it you just have to add '00005
	 */

	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public EngineTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(EngineTest.class);
	}

	/**
	 * Test with default settings
	 */
	public void testBasicConfiguration() {
		// FIXME apply test case
		assertEquals(true, false);
	}

	/**
	 * Test with propagation type is HORIZONTAL
	 */
	public void testPropagationTypeHorizontal() {
		// FIXME apply test case
		assertEquals(true, false);
	}

	/**
	 * Test with propagation type is VERTICAL
	 */
	public void testPropagationTypeVertical() {
		// FIXME apply test case
		assertEquals(true, false);
	}

	/**
	 * Test with different ROW & CELL
	 */
	public void testRowCellSpecified() {
		// FIXME apply test case
		assertEquals(true, false);
	}

	/**
	 * Test with cascade type is CASCADE_LEVEL_ONE
	 */
	public void testCascadeTypeLevelOne() {
		// FIXME apply test case
		assertEquals(true, false);
	}

	/**
	 * Test with cascade type is CASCADE_LEVEL_TWO
	 */
	public void testCascadeTypeLevelTwo() {
		// FIXME apply test case
		assertEquals(true, false);
	}

	/**
	 * Test with cascade type is CASCADE_FULL
	 */
	public void testCascadeTypeFull() {
		// FIXME apply test case
		assertEquals(true, false);
	}

	/**
	 * Test an empty object
	 */
	public void testObjectEmpty() {
		// FIXME apply test case
		assertEquals(true, false);
	}

	/**
	 * Test an null object
	 * 
	 * @throws Exception
	 */
	public void testObjectNulll() throws Exception {

		MultiTypeObject objNull = new MultiTypeObject();

		IEngine en = new Engine();

		byte[] generatedBytes = en.marshalToByte(objNull);

		MultiTypeObject charger = null;
		try {
			en.unmarshalFromByte(charger, generatedBytes);
			
		} catch (Exception e) {
			if (e.getClass().equals(JAEXElementException.class)
					&& e.getMessage().equals(JAEXExceptionMessage.JAEXElementException_NullObject)) {
				assertEquals(true, true);
			}
		}
	}

	/**
	 * Test an empty list
	 */
	public void testListEmpty() {
		// FIXME apply test case
		assertEquals(true, false);
	}

	/**
	 * Test an null list
	 */
	public void testListNulll() {
		// FIXME apply test case
		assertEquals(true, false);
	}
}
