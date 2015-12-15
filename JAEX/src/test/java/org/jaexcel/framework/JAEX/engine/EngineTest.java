package org.jaexcel.framework.JAEX.engine;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class EngineTest extends TestCase {
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
		assertEquals(true, false);
	}

	/**
	 * Test with propagation type is HORIZONTAL
	 */
	public void testPropagationTypeHorizontal() {
		assertEquals(true, false);
	}

	/**
	 * Test with propagation type is VERTICAL
	 */
	public void testPropagationTypeVertical() {
		assertEquals(true, false);
	}

	/**
	 * Test with different ROW & CELL
	 */
	public void testRowCellSpecified() {
		assertEquals(true, false);
	}

	/**
	 * Test with cascade type is CASCADE_LEVEL_ONE
	 */
	public void testCascadeTypeLevelOne() {
		assertEquals(true, false);
	}

	/**
	 * Test with cascade type is CASCADE_LEVEL_TWO
	 */
	public void testCascadeTypeLevelTwo() {
		assertEquals(true, false);
	}

	/**
	 * Test with cascade type is CASCADE_FULL
	 */
	public void testCascadeTypeFull() {
		assertEquals(true, false);
	}

	/**
	 * Test an empty object
	 */
	public void testObjectEmpty() {
		assertEquals(true, false);
	}

	/**
	 * Test an null object
	 */
	public void testObjectNulll() {
		assertEquals(true, false);
	}

	/**
	 * Test an empty list
	 */
	public void testListEmpty() {
		assertEquals(true, false);
	}

	/**
	 * Test an null list
	 */
	public void testListNulll() {
		assertEquals(true, false);
	}
}
