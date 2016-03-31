package net.ceos.project.poi.annotated.exception;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.testng.annotations.DataProvider;
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

	@DataProvider
	public Object[][] collectionProvider() {
		/* Collection empty */
		List<Object> collectionEmpty = new ArrayList<Object>();
		/* Collection with object null */
		List<Object> collection = new ArrayList<Object>();
		SimpleObject so = null;
		collection.add(so);
		
		return new Object[][] { 
			{ collectionEmpty, "CollectionEmpty", ExtensionFileType.XLS },
			{ collection, "CollectionWithObjectEmpty", ExtensionFileType.XLSX }
		};
	}

	@DataProvider
	public Object[][] overwriteCellProvider(){
		return new Object[][] { 
			{ new XlsElementOverwriteCell() },
			{ new XlsFreeElementOverwriteCell() }
		};
	}

	@DataProvider
	public Object[][] invalidPositionProvider(){
		return new Object[][] { 
			{ new XlsElementInvalidPosition() },
			{ new XlsFreeElementInvalidPositionCell() },
			{ new XlsFreeElementInvalidPositionRow() }
		};
	}

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
	 * Test an empty list & list with null object
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@Test(dataProvider = "collectionProvider", expectedExceptions = ElementException.class, expectedExceptionsMessageRegExp = "The entry object is empty. Make sure you are sending a correct object.")
	public void testMarshalListEmpty(Collection collection, String fileName, ExtensionFileType extension) throws Exception {
		IEngine en = new Engine();
		en.marshalAsCollection(collection, fileName, extension);
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
	 * Test a {@link XlsElement} & {@link XlsFreeElement} trying write at one cell already used
	 */
	@Test(dataProvider = "overwriteCellProvider", expectedExceptions = ElementException.class, expectedExceptionsMessageRegExp = "The element entry is trying to be set at one position already used. Review your configuration.")
	public void testMarshalXlsElementOverwriteCell(Object object) throws Exception {
		IEngine en = new Engine();
		en.marshalToWorkbook(object);
	}

	/**
	 * Test a {@link XlsElement} trying write at invalid position<br>
	 * Test a {@link XlsFreeElement} trying write at invalid cell position<br>
	 * Test a {@link XlsFreeElement} trying write at invalid row position<br>
	 */
	@Test(dataProvider = "invalidPositionProvider", expectedExceptions = ElementException.class, expectedExceptionsMessageRegExp = "The element entry has a invalid position, make sure you are setting a positive value and start at least by 1. Review your configuration.")
	public void testMarshalXlsElementInvalidPosition(Object object) throws Exception {
		IEngine en = new Engine();
		en.marshalToFileOutputStream(object);
	}
}
