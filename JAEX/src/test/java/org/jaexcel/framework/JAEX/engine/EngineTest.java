package org.jaexcel.framework.JAEX.engine;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.jaexcel.framework.JAEX.TestUtils;
import org.jaexcel.framework.JAEX.bean.BasicObject;
import org.jaexcel.framework.JAEX.bean.BasicObjectBuilder;
import org.jaexcel.framework.JAEX.bean.MultiTypeObject;
import org.jaexcel.framework.JAEX.bean.PropagationHorizontalObject;
import org.jaexcel.framework.JAEX.bean.PropagationHorizontalObjectBuilder;
import org.jaexcel.framework.JAEX.bean.PropagationVerticalObject;
import org.jaexcel.framework.JAEX.bean.PropagationVerticalObjectBuilder;
import org.jaexcel.framework.JAEX.definition.ExceptionMessage;
import org.jaexcel.framework.JAEX.definition.ExtensionFileType;
import org.jaexcel.framework.JAEX.exception.ElementException;

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
	public void testBasicConfiguration() throws Exception {
		BasicObject bO = BasicObjectBuilder.buildBasicObject();

		IEngine en = new Engine();
		en.marshalAndSave(bO, TestUtils.WORKING_DIR_GENERATED_I);
		
		BasicObject charger = new BasicObject();
		
		en.unmarshalFromPath(charger, TestUtils.WORKING_DIR_GENERATED_I);
		BasicObjectBuilder.validateBasicObject(charger);
	}

	/**
	 * Test with propagation type is HORIZONTAL
	 * @throws Exception 
	 */
	public void testPropagationTypeHorizontal() throws Exception {
		PropagationHorizontalObject pHO = PropagationHorizontalObjectBuilder.buildPropagationHorizontalObject();

		IEngine en = new Engine();
		CellDecorator anotherDate = new CellDecorator();
		anotherDate.setDecoratorName("anotherDate");
		anotherDate.setAlignment(CellStyle.ALIGN_CENTER);
		anotherDate.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		anotherDate.setForegroundColor(HSSFColor.LIGHT_GREEN.index);
		anotherDate.setFontItalic(true);
		anotherDate.setWrapText(true);

		ConfigCriteria configCriteria = new ConfigCriteria();
		configCriteria.addSpecificCellDecorator("anotherDate", anotherDate);
		
		en.marshalAndSave(configCriteria, pHO, TestUtils.WORKING_DIR_GENERATED_I);
		
		PropagationHorizontalObject charger = new PropagationHorizontalObject();
		
		en.unmarshalFromPath(charger, TestUtils.WORKING_DIR_GENERATED_II);
		PropagationHorizontalObjectBuilder.validatePropagationHorizontalObject(charger);
	}

	/**
	 * Test with propagation type is VERTICAL
	 */
	public void testPropagationTypeVertical() throws Exception {
		PropagationVerticalObject pVO = PropagationVerticalObjectBuilder.buildPropagationVerticalObject();

		IEngine en = new Engine();
		en.marshalAndSave(pVO, TestUtils.WORKING_DIR_GENERATED_II);
		
		PropagationVerticalObject charger = new PropagationVerticalObject();
		
		en.unmarshalFromPath(charger, TestUtils.WORKING_DIR_GENERATED_I);
		PropagationVerticalObjectBuilder.validatePropagationVerticalObject(charger);
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
	public void testObjectNull() throws Exception {

		MultiTypeObject objNull = new MultiTypeObject();

		IEngine en = new Engine();

		byte[] generatedBytes = en.marshalToByte(objNull);

		MultiTypeObject charger = null;
		try {
			en.unmarshalFromByte(charger, generatedBytes);
			
		} catch (Exception e) {
			if (e.getClass().equals(ElementException.class)
					&& e.getMessage().equals(ExceptionMessage.ElementException_NullObject)) {
				assertEquals(true, true);
			}
		}
	}

	/**
	 * Test an empty list
	 * 
	 * @throws Exception
	 */
	public void testListEmpty() throws Exception {

		List<Object> collectionEmpty = new ArrayList<Object>();
		
		IEngine en = new Engine();
		en.marshalAsCollection(collectionEmpty, "CollectionEmpty" , ExtensionFileType.XLSX);
		// FIXME ??? add error ???
	}

	/**
	 * Test an null list
	 */
	public void testListNull() throws Exception {

		List<Object> collectionNull = null;
		
		IEngine en = new Engine();
		en.marshalAsCollection(collectionNull, "CollectionNull" , ExtensionFileType.XLS);
		// FIXME ??? add error ???
	}
}
