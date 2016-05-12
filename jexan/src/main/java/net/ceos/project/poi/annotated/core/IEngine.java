package net.ceos.project.poi.annotated.core;

import java.util.Collection;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import net.ceos.project.poi.annotated.exception.WorkbookException;

public interface IEngine {

	/* ######################## Marshal methods ########################## */

	/**
	 * Generate the sheet based at the object passed as parameter and return the
	 * sheet generated.
	 * 
	 * @param object
	 *            the object to apply at the workbook.
	 * @return the {@link Sheet} generated
	 * @throws WorkbookException
	 */
	Sheet marshalToSheet(final Object object) throws WorkbookException;

	/**
	 * Generate the sheet based at the {@link XConfigCriteria} and the object
	 * passed as parameters and return the sheet generated.
	 * 
	 * @param configCriteria
	 *            the {@link XConfigCriteria} to use
	 * @param object
	 *            the object to apply at the workbook.
	 * @return the {@link Sheet} generated
	 * @throws WorkbookException
	 */
	Sheet marshalToSheet(final XConfigCriteria configCriteria, final Object object) throws WorkbookException;

	/**
	 * Generate the workbook based at the object passed as parameter and return
	 * the workbook generated.
	 * 
	 * @param object
	 *            the object to apply at the workbook.
	 * @return the {@link Workbook} generated
	 * @throws WorkbookException
	 */
	Workbook marshalToWorkbook(final Object object) throws WorkbookException;

	/**
	 * Generate the workbook based at the {@link XConfigCriteria} and the object
	 * passed as parameters and return the workbook generated.
	 * 
	 * @param configCriteria
	 *            the {@link XConfigCriteria} to use
	 * @param object
	 *            the object to apply at the workbook.
	 * @return the {@link Workbook} generated
	 * @throws WorkbookException
	 */
	Workbook marshalToWorkbook(final XConfigCriteria configCriteria, final Object object) throws WorkbookException;

	/**
	 * Generate the workbook based at the object passed as parameter and return
	 * the byte[] generated.
	 * 
	 * @param object
	 *            the object to apply at the workbook.
	 * @return the {@link Workbook} generated
	 * @throws WorkbookException
	 */
	byte[] marshalToByte(final Object object) throws WorkbookException;

	/**
	 * Generate the workbook based at the {@link XConfigCriteria} and the object
	 * passed as parameter and return the byte[] generated.
	 * 
	 * @param configCriteria
	 *            the {@link XConfigCriteria} to use
	 * @param object
	 *            the object to apply at the workbook.
	 * @return the {@link Workbook} generated
	 * @throws WorkbookException
	 */
	byte[] marshalToByte(final XConfigCriteria configCriteria, final Object object) throws WorkbookException;

	/**
	 * Generate the workbook based at the object passed as parameters and save
	 * it at the path send as parameter.
	 * 
	 * @param object
	 *            the object to apply at the workbook.
	 * @param pathFile
	 *            the file path where will be the file saved
	 * @throws WorkbookException
	 */
	void marshalAndSave(final Object object, final String pathFile) throws WorkbookException;

	/**
	 * Generate the workbook based at the {@link XConfigCriteria} and the object
	 * passed as parameters and save it at the path send as parameter.
	 * 
	 * @param configCriteria
	 *            the {@link XConfigCriteria} to use
	 * @param object
	 *            the object to apply at the workbook.
	 * @param pathFile
	 *            the file path where will be the file saved
	 * @throws WorkbookException
	 */
	void marshalAndSave(final XConfigCriteria configCriteria, final Object object, final String pathFile)
			throws WorkbookException;

	/**
	 * Generate the sheet based at the collection of objects passed as parameter
	 * and return the sheet generated.
	 * 
	 * @param listObject
	 *            the collection to apply at the workbook.
	 * @return the {@link Sheet} generated
	 * @throws WorkbookException
	 */
	Sheet marshalCollectionToSheet(final Collection<?> listObject) throws WorkbookException;

	/**
	 * Generate the sheet based at the {@link XConfigCriteria} and the
	 * collection of objects passed as parameters and return the sheet
	 * generated.
	 * 
	 * @param configCriteria
	 *            the {@link XConfigCriteria} to use
	 * @param listObject
	 *            the collection to apply at the workbook.
	 * @return the {@link Sheet} generated
	 * @throws WorkbookException
	 */
	Sheet marshalCollectionToSheet(final XConfigCriteria configCriteria, final Collection<?> listObject)
			throws WorkbookException;

	/**
	 * Generate the workbook based at the collection of objects passed as
	 * parameter and return the workbook generated.
	 * 
	 * @param listObject
	 *            the collection to apply at the workbook.
	 * @return the {@link Workbook} generated
	 * @throws WorkbookException
	 */
	Workbook marshalCollectionToWorkbook(final Collection<?> listObject) throws WorkbookException;

	/**
	 * Generate the workbook based at the {@link XConfigCriteria} and the
	 * collection of objects passed as parameters and return the workbook
	 * generated.
	 * 
	 * @param configCriteria
	 *            the {@link XConfigCriteria} to use
	 * @param listObject
	 *            the collection to apply at the workbook.
	 * @return the {@link Workbook} generated
	 * @throws WorkbookException
	 */
	Workbook marshalCollectionToWorkbook(final XConfigCriteria configCriteria, final Collection<?> listObject)
			throws WorkbookException;

	/**
	 * Generate the workbook based at the collection of objects passed as
	 * parameter and save it at the path send as parameter.
	 * 
	 * @param listObject
	 *            the collection to apply at the workbook.
	 * @param pathFile
	 *            the file path where will be the file saved
	 * @throws WorkbookException
	 */
	void marshalAsCollectionAndSave(final Collection<?> listObject, final String pathFile) throws WorkbookException;

	/**
	 * Generate the workbook based at the {@link XConfigCriteria} and the
	 * collection of objects passed as parameter and save it at the path send as
	 * parameter.
	 * 
	 * @param configCriteria
	 *            the {@link XConfigCriteria} to use
	 * @param listObject
	 *            the collection to apply at the workbook.
	 * @param pathFile
	 *            the file path where will be the file saved
	 * @throws WorkbookException
	 */
	void marshalAsCollectionAndSave(final XConfigCriteria configCriteria, final Collection<?> listObject,
			final String pathFile) throws WorkbookException;

	/**
	 * Generate the workbook based at the collection of objects and return the
	 * byte[] generated.
	 * 
	 * @param listObject
	 *            the collection to apply at the workbook.
	 * @return the byte[] generated
	 * @throws WorkbookException
	 */
	byte[] marshalCollectionToByte(final Collection<?> listObject) throws WorkbookException;

	/**
	 * Generate the workbook based at the {@link XConfigCriteria} and the
	 * collection of objects and return the byte[] generated.
	 * 
	 * @param listObject
	 *            the collection to apply at the workbook.
	 * @return the byte[] generated
	 * @throws WorkbookException
	 */
	byte[] marshalCollectionToByte(final XConfigCriteria configCriteria, final Collection<?> listObject)
			throws WorkbookException;

	/* ######################## Unmarshal methods ######################## */

	/**
	 * Generate the object from the workbook passed as parameter.
	 * 
	 * @param object
	 *            the object to fill up.
	 * @param workbook
	 *            the {@link Workbook} to read and pass the information to the
	 *            object
	 * @throws WorkbookException
	 */
	void unmarshalFromWorkbook(final Object object, final Workbook workbook) throws WorkbookException;

	/**
	 * Generate the object from the path file passed as parameter.
	 * 
	 * @param object
	 *            the object to fill up.
	 * @param pathFile
	 *            the path where is found the file to read and pass the
	 *            information to the object
	 * @throws WorkbookException
	 */
	void unmarshalFromPath(final Object object, final String pathFile) throws WorkbookException;

	/**
	 * Generate the object from the byte array passed as parameter.
	 * 
	 * @param object
	 *            the object to fill up.
	 * @param inputByte
	 *            the byte[] to read and pass the information to the object
	 * @throws WorkbookException
	 */
	void unmarshalFromByte(final Object object, final byte[] byteArray) throws WorkbookException;

	/**
	 * 
	 * @param collection
	 */
	void marshalAsCollection(final Collection<?> collection);

	Collection<?> unmarshalToCollection(XConfigCriteria configCriteria, Object object, String excelFilePath)
			throws WorkbookException;

}
