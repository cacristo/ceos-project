package org.jaexcel.framework.JAEX.exception;

import java.util.ArrayList;
import java.util.List;

import org.jaexcel.framework.JAEX.annotation.XlsElement;
import org.jaexcel.framework.JAEX.annotation.XlsFreeElement;
import org.jaexcel.framework.JAEX.bean.MultiTypeObject;
import org.jaexcel.framework.JAEX.bean.SimpleObject;
import org.jaexcel.framework.JAEX.bean.XlsElementInvalidPosition;
import org.jaexcel.framework.JAEX.bean.XlsElementOverwriteCell;
import org.jaexcel.framework.JAEX.bean.XlsFreeElementInvalidObject;
import org.jaexcel.framework.JAEX.bean.XlsFreeElementInvalidPositionCell;
import org.jaexcel.framework.JAEX.bean.XlsFreeElementInvalidPositionRow;
import org.jaexcel.framework.JAEX.bean.XlsFreeElementOverwriteCell;
import org.jaexcel.framework.JAEX.definition.ExceptionMessage;
import org.jaexcel.framework.JAEX.definition.ExtensionFileType;
import org.jaexcel.framework.JAEX.engine.Engine;
import org.jaexcel.framework.JAEX.engine.IEngine;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ElementExceptionTest extends TestCase {
	private Class<?> c = null;
	private String message = "";

	private void reset() {
		this.c = null;
		this.message = "";
	}

	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public ElementExceptionTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(ElementExceptionTest.class);
	}

	@Override
	protected void setUp() throws Exception {
		reset();
		super.setUp();
	}

	/**
	 * Test an null object
	 * 
	 * @throws Exception
	 */
	public void testMarsharObjectNull() throws Exception {
		MultiTypeObject objNull = null;

		try {
			IEngine en = new Engine();
			en.marshalToByte(objNull);
		} catch (Exception e) {
			this.c = e.getClass();
			this.message = e.getMessage();
		}
		assertEquals(ElementException.class, this.c);
		assertEquals(ExceptionMessage.ElementException_NullObject.getMessage(), this.message);
	}

	/**
	 * Test an empty object
	 */
	public void testUnmarshalObjectNull() throws Exception {
		MultiTypeObject objEmpty = new MultiTypeObject();

		IEngine en = new Engine();

		byte[] generatedBytes = en.marshalToByte(objEmpty);

		MultiTypeObject charger = null;
		try {
			en.unmarshalFromByte(charger, generatedBytes);

		} catch (Exception e) {
			this.c = e.getClass();
			this.message = e.getMessage();
		}
		assertEquals(ElementException.class, this.c);
		assertEquals(ExceptionMessage.ElementException_NullObject.getMessage(), this.message);
	}

	/**
	 * Test an null list
	 */
	public void testMarshalListNull() throws Exception {
		List<Object> collectionNull = null;

		try {
			IEngine en = new Engine();
			en.marshalAsCollection(collectionNull, "CollectionNull", ExtensionFileType.XLS);
		} catch (Exception e) {
			this.c = e.getClass();
			this.message = e.getMessage();
		}
		assertEquals(ElementException.class, this.c);
		assertEquals(ExceptionMessage.ElementException_NullObject.getMessage(), this.message);
	}

	/**
	 * Test an empty list
	 * 
	 * @throws Exception
	 */
	public void testMarshalListEmpty() throws Exception {
		List<Object> collectionEmpty = new ArrayList<Object>();

		try {
			IEngine en = new Engine();
			en.marshalAsCollection(collectionEmpty, "CollectionEmpty", ExtensionFileType.XLSX);
		} catch (Exception e) {
			this.c = e.getClass();
			this.message = e.getMessage();
		}
		assertEquals(ElementException.class, this.c);
		assertEquals(ExceptionMessage.ElementException_EmptyObject.getMessage(), this.message);
	}

	/**
	 * Test an empty list
	 * 
	 * @throws Exception
	 */
	public void testMarshalListWithObjectEmpty() throws Exception {
		List<Object> collection = new ArrayList<Object>();
		SimpleObject so = null;
		collection.add(so);

		try {
			IEngine en = new Engine();
			en.marshalAsCollection(collection, "CollectionWithObjectEmpty", ExtensionFileType.XLSX);
		} catch (Exception e) {
			this.c = e.getClass();
			this.message = e.getMessage();
		}
		assertEquals(ElementException.class, this.c);
		assertEquals(ExceptionMessage.ElementException_EmptyObject.getMessage(), this.message);
	}

	/**
	 * Test a {@link XlsFreeElement} trying use a complex object
	 */
	public void testMarshalXlsFreeElementInvalidObject() throws Exception {
		XlsFreeElementInvalidObject complexObject = new XlsFreeElementInvalidObject();

		try {
			IEngine en = new Engine();
			en.marshalToSheet(complexObject);
		} catch (Exception e) {
			this.c = e.getClass();
			this.message = e.getMessage();
		}
		assertEquals(ElementException.class, this.c);
		assertEquals(ExceptionMessage.ElementException_ComplexObject.getMessage(), this.message);
	}

	/**
	 * Test a {@link XlsElement} trying write at one cell already used
	 */
	public void testMarshalXlsElementOverwriteCell() throws Exception {
		XlsElementOverwriteCell overwrite = new XlsElementOverwriteCell();

		try {
			IEngine en = new Engine();
			en.marshalToWorkbook(overwrite);
		} catch (Exception e) {
			this.c = e.getClass();
			this.message = e.getMessage();
		}
		assertEquals(ElementException.class, this.c);
		assertEquals(ExceptionMessage.ElementException_OverwriteCell.getMessage(), this.message);
	}

	/**
	 * Test a {@link XlsFreeElement} trying write at one cell already used
	 */
	public void testMarshalXlsFreeElementOverwriteCell() throws Exception {
		XlsFreeElementOverwriteCell overwrite = new XlsFreeElementOverwriteCell();

		try {
			IEngine en = new Engine();
			en.marshalToSheet(overwrite);
		} catch (Exception e) {
			c = e.getClass();
			message = e.getMessage();
		}
		assertEquals(ElementException.class, this.c);
		assertEquals(ExceptionMessage.ElementException_OverwriteCell.getMessage(), this.message);
	}

	/**
	 * Test a {@link XlsElement} trying write at invalid position
	 */
	public void testMarshalXlsElementInvalidPosition() throws Exception {
		XlsElementInvalidPosition invalidPosition = new XlsElementInvalidPosition();

		try {
			IEngine en = new Engine();
			en.marshalToFileOutputStream(invalidPosition);
		} catch (Exception e) {
			c = e.getClass();
			message = e.getMessage();
		}
		assertEquals(ElementException.class, this.c);
		assertEquals(ExceptionMessage.ElementException_InvalidPosition.getMessage(), this.message);
	}

	/**
	 * Test a {@link XlsFreeElement} trying write at invalid cell position
	 */
	public void testMarshalXlsFreeElementInvalidPositionCell() throws Exception {
		XlsFreeElementInvalidPositionCell overwrite = new XlsFreeElementInvalidPositionCell();

		try {
			IEngine en = new Engine();
			en.marshalToSheet(overwrite);
		} catch (Exception e) {
			c = e.getClass();
			message = e.getMessage();
		}
		assertEquals(ElementException.class, this.c);
		assertEquals(ExceptionMessage.ElementException_InvalidPosition.getMessage(), this.message);
	}

	/**
	 * Test a {@link XlsFreeElement} trying write at invalid row position
	 */
	public void testMarshalXlsFreeElementInvalidPositionRow() throws Exception {
		XlsFreeElementInvalidPositionRow overwrite = new XlsFreeElementInvalidPositionRow();

		try {
			IEngine en = new Engine();
			en.marshalToSheet(overwrite);
		} catch (Exception e) {
			c = e.getClass();
			message = e.getMessage();
		}
		assertEquals(ElementException.class, this.c);
		assertEquals(ExceptionMessage.ElementException_InvalidPosition.getMessage(), this.message);
	}
}
