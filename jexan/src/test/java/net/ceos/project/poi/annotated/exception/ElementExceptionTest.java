package net.ceos.project.poi.annotated.exception;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import net.ceos.project.poi.annotated.annotation.XlsElement;
import net.ceos.project.poi.annotated.annotation.XlsFreeElement;
import net.ceos.project.poi.annotated.bean.MultiTypeObject;
import net.ceos.project.poi.annotated.bean.SimpleObject;
import net.ceos.project.poi.annotated.bean.XlsConflitFormulaHorizIncompatible;
import net.ceos.project.poi.annotated.bean.XlsConflitFormulaVertiIncompatible;
import net.ceos.project.poi.annotated.bean.XlsElementInvalidPosition;
import net.ceos.project.poi.annotated.bean.XlsElementOverwriteCell;
import net.ceos.project.poi.annotated.bean.XlsFreeElementInvalidObject;
import net.ceos.project.poi.annotated.bean.XlsFreeElementInvalidPositionCell;
import net.ceos.project.poi.annotated.bean.XlsFreeElementInvalidPositionRow;
import net.ceos.project.poi.annotated.bean.XlsFreeElementOverwriteCell;
import net.ceos.project.poi.annotated.core.Engine;
import net.ceos.project.poi.annotated.core.IEngine;
import net.ceos.project.poi.annotated.core.TestUtils;
import net.ceos.project.poi.annotated.core.XConfigCriteria;
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
	@Test(expected = ElementException.class)
	public void testMarsharObjectNull() throws Exception {
		MultiTypeObject objNull = null;

		IEngine en = new Engine();
		en.marshalToByte(objNull);
	}

	/**
	 * Test an empty object
	 */
	@Test(expected = ElementException.class)
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
	@Test(expected = ElementException.class)
	public void testMarshalListNull() throws Exception {
		List<Object> collectionNull = null;
		
		XConfigCriteria configCriteria = new XConfigCriteria();
		configCriteria.setFileName("CollectionNull");
		configCriteria.overrideExtensionType(ExtensionFileType.XLS);
		
		IEngine en = new Engine();
		en.marshalAsCollectionAndSave(configCriteria, collectionNull, TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test an empty list & list with null object
	 * 
	 * @throws Exception
	 */
	@Test(expected = ElementException.class)
	public void testMarshalListEmpty() throws Exception {
		/* Collection empty */
		List<Object> collectionEmpty = new ArrayList<Object>();
		
		XConfigCriteria configCriteria = new XConfigCriteria();
		configCriteria.setFileName("CollectionEmpty");
		configCriteria.overrideExtensionType(ExtensionFileType.XLS);
		
		IEngine en = new Engine();
		en.marshalAsCollectionAndSave(configCriteria, collectionEmpty, TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test an empty list & list with null object
	 * 
	 * @throws Exception
	 */
	@Test(expected = ElementException.class)
	public void testMarshalListWithElementEmpty() throws Exception {
		/* Collection with object null */
		List<Object> collection = new ArrayList<Object>();
		SimpleObject so = null;
		collection.add(so);

		
		XConfigCriteria configCriteria = new XConfigCriteria();
		configCriteria.setFileName("CollectionWithObjectEmpty");
		configCriteria.overrideExtensionType(ExtensionFileType.XLSX);
		
		IEngine en = new Engine();
		en.marshalAsCollectionAndSave(configCriteria, collection, TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test a {@link XlsFreeElement} trying use a complex object
	 */
	@Test(expected = ElementException.class)
	public void testMarshalXlsFreeElementInvalidObject() throws Exception {
		XlsFreeElementInvalidObject complexObject = new XlsFreeElementInvalidObject();

		IEngine en = new Engine();
		en.marshalToSheet(complexObject);
	}

	/**
	 * Test a {@link XlsElement} & {@link XlsFreeElement} trying write at one cell already used
	 */
	@Test(expected = ElementException.class)
	public void testMarshalXlsElementOverwriteCell() throws Exception {
		IEngine en = new Engine();
		en.marshalToWorkbook(new XlsElementOverwriteCell());
	}

	/**
	 * Test a {@link XlsElement} & {@link XlsFreeElement} trying write at one cell already used
	 */
	@Test(expected = ElementException.class)
	public void testMarshalXlsFreeElementOverwriteCell() throws Exception {
		IEngine en = new Engine();
		en.marshalToWorkbook(new XlsFreeElementOverwriteCell());
	}

	/**
	 * Test a {@link XlsElement} trying write at invalid position<br>
	 */
	@Test(expected = ElementException.class)
	public void testMarshalXlsElementInvalidPosition() throws Exception {
		IEngine en = new Engine();
		en.marshalToFileOutputStream(new XlsElementInvalidPosition());
	}

	/**
	 * Test a {@link XlsFreeElement} trying write at invalid cell position<br>
	 */
	@Test(expected = ElementException.class)
	public void testMarshalXlsFreeElementInvalidPositionCell() throws Exception {
		IEngine en = new Engine();
		en.marshalToFileOutputStream(new XlsFreeElementInvalidPositionCell());
	}

	/**
	 * Test a {@link XlsFreeElement} trying write at invalid row position<br>
	 */
	@Test(expected = ElementException.class)
	public void testMarshalXlsFreeElementInvalidPositionRow() throws Exception {
		IEngine en = new Engine();
		en.marshalToFileOutputStream(new XlsFreeElementInvalidPositionRow());
	}

	/**
	 * Test a horizontal configuration exception conflict
	 */
	@Test(expected = ElementException.class)
	public void testXlsConflictConfigurationExceptionHorizontal() throws Exception {
		IEngine en = new Engine();
		en.marshalAndSave(new XlsConflitFormulaHorizIncompatible(), TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test a vertical configuration exception conflict
	 */
	@Test(expected = ElementException.class)
	public void testXlsConflictConfigurationExceptionVertical() throws Exception {
		IEngine en = new Engine();
		en.marshalAndSave(new XlsConflitFormulaVertiIncompatible(), TestUtils.WORKING_DIR_GENERATED_I);
	}
	
}
