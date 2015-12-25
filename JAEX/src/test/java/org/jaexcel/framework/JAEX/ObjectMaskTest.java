package org.jaexcel.framework.JAEX;

import java.math.BigDecimal;
import java.util.Date;

import org.jaexcel.framework.JAEX.bean.ObjectMask;
import org.jaexcel.framework.JAEX.engine.Engine;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ObjectMaskTest extends TestCase {

	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public ObjectMaskTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(ObjectMaskTest.class);
	}

	/**
	 * Test one basic object
	 */
	public void testObject() throws Exception {
		ObjectMask om = new ObjectMask();

		Date d = new Date();

		// Date
		om.setDateAttribute1(d);
		om.setDateAttribute2(d);
		om.setDateAttribute3(d);
		om.setDateAttribute4(d);
		om.setDateAttribute5(d);
		om.setDateAttribute6(d);
		om.setDateAttribute7(d);
		// Double
		om.setDoubleAttribute1(1625.487);
		om.setDoubleAttribute2(1625.487);
		om.setDoubleAttribute3(1625.487);
		om.setDoubleAttribute4(1625.487);
		om.setDoubleAttribute5(1625.487);
		// BigDecimal
		om.setBigDecimalAttribute1(BigDecimal.valueOf(111.366));
		om.setBigDecimalAttribute2(BigDecimal.valueOf(111.366));
		om.setBigDecimalAttribute3(BigDecimal.valueOf(111.366));
		om.setBigDecimalAttribute4(BigDecimal.valueOf(111.366));
		om.setBigDecimalAttribute5(BigDecimal.valueOf(111.366));
		om.setBigDecimalAttribute6(BigDecimal.valueOf(111.366));
		// Float
		om.setFloatAttribute1(46.445f);
		om.setFloatAttribute2(46.445f);
		om.setFloatAttribute3(46.445f);
		om.setFloatAttribute4(46.445f);
		om.setFloatAttribute5(46.445f);
		
		Engine en = new Engine();

		en.marshal(om);

		// TODO validation result
		// assertEquals(true, false);
	}

}
