package net.ceos.project.poi.annotated.core;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import net.ceos.project.poi.annotated.definition.ExtensionFileType;
import net.ceos.project.poi.annotated.exception.ConfigurationException;
import net.ceos.project.poi.annotated.exception.ConverterException;
import net.ceos.project.poi.annotated.exception.CustomizedRulesException;
import net.ceos.project.poi.annotated.exception.ElementException;
import net.ceos.project.poi.annotated.exception.SheetException;

public interface IEngine {

	/* ######################## Marshal methods ########################## */

	/**
	 * Generate the sheet from the object and return the sheet generated.
	 * 
	 * @param object
	 *            the object to apply at the workbook.
	 * @return the {@link Sheet} generated
	 */
	Sheet marshalToSheet(final Object object)
			throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException,
			ElementException, ConfigurationException, SheetException, CustomizedRulesException, ConverterException;

	/**
	 * Generate the sheet from the object and return the sheet generated.
	 * 
	 * @param configCriteria
	 *            the {@link XConfigCriteria} to use
	 * @param object
	 *            the object to apply at the workbook.
	 * @return the {@link Sheet} generated
	 */
	Sheet marshalToSheet(final XConfigCriteria configCriteria, final Object object)
			throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException,
			ElementException, ConfigurationException, SheetException, CustomizedRulesException, ConverterException;

	/**
	 * Generate the workbook from the object and return the workbook generated.
	 * 
	 * @param object
	 *            the object to apply at the workbook.
	 * @return the {@link Workbook} generated
	 */
	Workbook marshalToWorkbook(final Object object)
			throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException,
			ElementException, ConfigurationException, SheetException, CustomizedRulesException, ConverterException;

	/**
	 * Generate the workbook from the object and return the workbook generated.
	 * 
	 * @param configCriteria
	 *            the {@link XConfigCriteria} to use
	 * @param object
	 *            the object to apply at the workbook.
	 * @return the {@link Workbook} generated
	 */
	Workbook marshalToWorkbook(final XConfigCriteria configCriteria, final Object object)
			throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException,
			ElementException, ConfigurationException, SheetException, CustomizedRulesException, ConverterException;

	/**
	 * Generate the workbook from the object passed as parameter and save it at
	 * the path send as parameter.
	 * 
	 * @param object
	 *            the object to apply at the workbook.
	 * @param pathFile
	 *            the file path where will be the file saved
	 */
	void marshalAndSave(final Object object, final String pathFile) throws IllegalAccessException, InvocationTargetException,
			InstantiationException, NoSuchMethodException, ElementException, ConfigurationException, SheetException,
			CustomizedRulesException, ConverterException, IOException;

	/**
	 * Generate the workbook from the object passed as parameter and save it at
	 * the path send as parameter.
	 * 
	 * @param configCriteria
	 *            the {@link XConfigCriteria} to use
	 * @param object
	 *            the object to apply at the workbook.
	 * @param pathFile
	 *            the file path where will be the file saved
	 */
	void marshalAndSave(final XConfigCriteria configCriteria, final Object object, final String pathFile) throws IllegalAccessException,
			InvocationTargetException, InstantiationException, NoSuchMethodException, ElementException,
			ConfigurationException, SheetException, CustomizedRulesException, ConverterException, IOException;

	/**
	 * Generate the workbook from the object and return the byte[] generated.
	 * 
	 * @param object
	 *            the object to apply at the workbook.
	 * @return the {@link FileOutputStream} generated
	 */
	byte[] marshalToByte(final Object object) throws IllegalAccessException, InvocationTargetException,
			InstantiationException, NoSuchMethodException, ElementException, ConfigurationException, SheetException,
			CustomizedRulesException, ConverterException, IOException;

	/**
	 * Generate the workbook from the collection of objects.
	 * 
	 * @param collection
	 *            the collection of objects to apply at the workbook.
	 * @param filename
	 *            the file name
	 * @param extensionFileType
	 *            the file extension
	 * @throws Exception
	 */
	void marshalAsCollection(final Collection<?> collection, final String filename, final ExtensionFileType extensionFileType)
			throws Exception;

	/* ######################## Unmarshal methods ######################## */

	/**
	 * Generate the object from the path file passed as parameter.
	 * 
	 * @param object
	 *            the object to fill up.
	 * @param pathFile
	 *            the path where is found the file to read and pass the
	 *            information to the object
	 */
	void unmarshalFromPath(final Object object, final String pathFile) throws ElementException, ConfigurationException,
			IOException, IllegalAccessException, InstantiationException, SheetException, ConverterException;

	/**
	 * Generate the object from the workbook passed as parameter.
	 * 
	 * @param object
	 *            the object to fill up.
	 * @param workbook
	 *            the {@link Workbook} to read and pass the information to the
	 *            object
	 */
	void unmarshalFromWorkbook(final Object object, final Workbook workbook) throws ElementException, ConfigurationException,
			IllegalAccessException, InstantiationException, SheetException, ConverterException;

	/**
	 * Generate the object from the byte array passed as parameter.
	 * 
	 * @param object
	 *            the object to fill up.
	 * @param inputByte
	 *            the byte array to read and pass the information to the object
	 */
	void unmarshalFromByte(final Object object, final byte[] byteArray) throws ElementException, ConfigurationException,
			IOException, IllegalAccessException, InstantiationException, SheetException, ConverterException;

	Collection<?> unmarshalToCollection(final Object object);

	/**
	 * 
	 * @param collection
	 */
	void marshalAsCollection(final Collection<?> collection);

	/* ############################################# */
	/* ################## TO REVIEW ################ */
	/* ############################################# */

	/**
	 * Generate the workbook from the object and return the FileOutputStream
	 * generated.
	 * 
	 * @param object
	 *            the object to apply at the workbook.
	 * @return the {@link FileOutputStream} generated
	 */
	FileOutputStream marshalToFileOutputStream(Object object) throws IllegalAccessException, InvocationTargetException,
			InstantiationException, NoSuchMethodException, ElementException, ConfigurationException, SheetException,
			CustomizedRulesException, ConverterException, IOException;

	Object unmarshalFromFileInputStream(Object object, FileInputStream stream) throws Exception;
}
