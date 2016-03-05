package net.ceos.project.poi.annotated.exception;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import net.ceos.project.poi.annotated.annotation.XlsElement;
import net.ceos.project.poi.annotated.annotation.XlsFreeElement;
import net.ceos.project.poi.annotated.bean.MultiTypeObject;
import net.ceos.project.poi.annotated.bean.SimpleObject;
import net.ceos.project.poi.annotated.bean.XlsElementInvalidPosition;
import net.ceos.project.poi.annotated.bean.XlsElementOverwriteCell;
import net.ceos.project.poi.annotated.bean.XlsFreeElementInvalidObject;
import net.ceos.project.poi.annotated.bean.XlsFreeElementInvalidPositionCell;
import net.ceos.project.poi.annotated.bean.XlsFreeElementInvalidPositionRow;
import net.ceos.project.poi.annotated.bean.XlsFreeElementOverwriteCell;
import net.ceos.project.poi.annotated.core.Engine;
import net.ceos.project.poi.annotated.core.IEngine;
import net.ceos.project.poi.annotated.definition.ExtensionFileType;

/**
 * Test the {@link ElementException}
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public class ElementExceptionTest {

	/**
	 * Test an null object
	 * 
	 * @throws Exception
	 */
	@Test(expectedExceptions = ElementException.class, expectedExceptionsMessageRegExp = "The entry object is null. Make sure you are sending a correct object.")
	public void testMarsharObjectNull() throws Exception {
		MultiTypeObject objNull = null;

		IEngine en = new Engine();
		en.marshalToByte(objNull);
	}

	/**
	 * Test an empty object
	 */
	@Test(expectedExceptions = ElementException.class, expectedExceptionsMessageRegExp = "The entry object is null. Make sure you are sending a correct object.")
	public void testUnmarshalObjectNull() throws Exception {
		MultiTypeObject objEmpty = new MultiTypeObject();

		IEngine en = new Engine();
		byte[] generatedBytes = en.marshalToByte(objEmpty);

		MultiTypeObject charger = null;
		en.unmarshalFromByte(charger, generatedBytes);
	}

	/**
	 * Test an null list
	 */
	@Test(expectedExceptions = ElementException.class, expectedExceptionsMessageRegExp = "The entry object is null. Make sure you are sending a correct object.")
	public void testMarshalListNull() throws Exception {
		List<Object> collectionNull = null;

		IEngine en = new Engine();
		en.marshalAsCollection(collectionNull, "CollectionNull", ExtensionFileType.XLS);
	}

	/**
	 * Test an empty list
	 * 
	 * @throws Exception
	 */
	@Test(expectedExceptions = ElementException.class, expectedExceptionsMessageRegExp = "The entry object is empty. Make sure you are sending a correct object.")
	public void testMarshalListEmpty() throws Exception {
		List<Object> collectionEmpty = new ArrayList<Object>();

		IEngine en = new Engine();
		en.marshalAsCollection(collectionEmpty, "CollectionEmpty", ExtensionFileType.XLSX);
	}

	/**
	 * Test an empty list
	 * 
	 * @throws Exception
	 */
	@Test(expectedExceptions = ElementException.class, expectedExceptionsMessageRegExp = "The entry object is empty. Make sure you are sending a correct object.")
	public void testMarshalListWithObjectEmpty() throws Exception {
		List<Object> collection = new ArrayList<Object>();
		SimpleObject so = null;
		collection.add(so);

		IEngine en = new Engine();
		en.marshalAsCollection(collection, "CollectionWithObjectEmpty", ExtensionFileType.XLSX);
	}

	/**
	 * Test a {@link XlsFreeElement} trying use a complex object
	 */
	@Test(expectedExceptions = ElementException.class, expectedExceptionsMessageRegExp = "Complex objects are not allowed for this type! Review your configuration.")
	public void testMarshalXlsFreeElementInvalidObject() throws Exception {
		XlsFreeElementInvalidObject complexObject = new XlsFreeElementInvalidObject();

		IEngine en = new Engine();
		en.marshalToSheet(complexObject);
	}

	/**
	 * Test a {@link XlsElement} trying write at one cell already used
	 */
	@Test(expectedExceptions = ElementException.class, expectedExceptionsMessageRegExp = "The element entry is trying to be set at one position already used. Review your configuration.")
	public void testMarshalXlsElementOverwriteCell() throws Exception {
		XlsElementOverwriteCell overwrite = new XlsElementOverwriteCell();

		IEngine en = new Engine();
		en.marshalToWorkbook(overwrite);
	}

	/**
	 * Test a {@link XlsFreeElement} trying write at one cell already used
	 */
	@Test(expectedExceptions = ElementException.class, expectedExceptionsMessageRegExp = "The element entry is trying to be set at one position already used. Review your configuration.")
	public void testMarshalXlsFreeElementOverwriteCell() throws Exception {
		XlsFreeElementOverwriteCell overwrite = new XlsFreeElementOverwriteCell();

		IEngine en = new Engine();
		en.marshalToSheet(overwrite);
	}

	/**
	 * Test a {@link XlsElement} trying write at invalid position
	 */
	@Test(expectedExceptions = ElementException.class, expectedExceptionsMessageRegExp = "The element entry has a invalid position, make sure you are setting a positive value and start at least by 1. Review your configuration.")
	public void testMarshalXlsElementInvalidPosition() throws Exception {
		XlsElementInvalidPosition invalidPosition = new XlsElementInvalidPosition();

		IEngine en = new Engine();
		en.marshalToFileOutputStream(invalidPosition);
	}

	/**
	 * Test a {@link XlsFreeElement} trying write at invalid cell position
	 */
	@Test(expectedExceptions = ElementException.class, expectedExceptionsMessageRegExp = "The element entry has a invalid position, make sure you are setting a positive value and start at least by 1. Review your configuration.")
	public void testMarshalXlsFreeElementInvalidPositionCell() throws Exception {
		XlsFreeElementInvalidPositionCell overwrite = new XlsFreeElementInvalidPositionCell();

		IEngine en = new Engine();
		en.marshalToSheet(overwrite);
	}

	/**
	 * Test a {@link XlsFreeElement} trying write at invalid row position
	 */
	@Test(expectedExceptions = ElementException.class, expectedExceptionsMessageRegExp = "The element entry has a invalid position, make sure you are setting a positive value and start at least by 1. Review your configuration.")
	public void testMarshalXlsFreeElementInvalidPositionRow() throws Exception {
		XlsFreeElementInvalidPositionRow overwrite = new XlsFreeElementInvalidPositionRow();

		IEngine en = new Engine();
		en.marshalToSheet(overwrite);
	}
}
