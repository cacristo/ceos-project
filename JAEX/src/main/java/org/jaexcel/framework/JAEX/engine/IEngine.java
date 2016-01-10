package org.jaexcel.framework.JAEX.engine;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;

import org.apache.poi.ss.usermodel.Workbook;
import org.jaexcel.framework.JAEX.definition.CascadeType;
import org.jaexcel.framework.JAEX.definition.ExtensionFileType;
import org.jaexcel.framework.JAEX.definition.PropagationType;
import org.jaexcel.framework.JAEX.exception.JAEXConverterException;
import org.jaexcel.framework.JAEX.exception.JAEXElementException;

public interface IEngine {

	/* ######################## Marshal methods ########################## */

	/**
	 * Generate the workbook from the object passed as parameter and save it at
	 * the path send as parameter.
	 * 
	 * @param object
	 *            the object to apply at the workbook.
	 * @param pathFile
	 *            the file path where will be the file saved
	 */
	void marshalAndSave(Object object, String pathFile) throws Exception;

	/**
	 * Generate the workbook from the object and return the workbook generated.
	 * 
	 * @param object
	 *            the object to apply at the workbook.
	 * @return the {@link Workbook} generated
	 */
	Workbook marshalToWorkbook(Object object) throws Exception;

	/**
	 * Generate the workbook from the object and return the byte[] generated.
	 * 
	 * @param object
	 *            the object to apply at the workbook.
	 * @return the {@link FileOutputStream} generated
	 */
	byte[] marshalToByte(Object object) throws Exception;

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
	void marshalAsCollection(Collection<?> collection, final String filename, final ExtensionFileType extensionFileType)
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
	 * @return the {@link Object} filled up
	 */
	Object unmarshalFromPath(Object object, String pathFile) throws Exception;

	/**
	 * Generate the object from the workbook passed as parameter.
	 * 
	 * @param object
	 *            the object to fill up.
	 * @param workbook
	 *            the {@link Workbook} to read and pass the information to the
	 *            object
	 * @return the {@link Object} filled up
	 */
	Object unmarshalFromWorkbook(Object object, Workbook workbook) throws Exception;

	/**
	 * Generate the object from the byte array passed as parameter.
	 * 
	 * @param object the object to fill up.
	 * @param inputByte the byte array to read and pass the information to the
	 *            object
	 * @return the {@link Object} filled up
	 */
	Object unmarshalFromByte(Object object, byte[] byteArray) throws Exception;



	Collection<?> unmarshalToCollection(Object object);

	/**
	 * 
	 * @param collection
	 */
	void marshalAsCollection(Collection<?> collection);

	/* ####################### Configuration methods ##################### */

	/**
	 * Force the header cell decorator.
	 * 
	 * @param decorator
	 *            the {@link CellDecorator} to apply
	 */
	void overrideHeaderCellDecorator(CellDecorator decorator);

	/**
	 * Force the numeric cell decorator.
	 * 
	 * @param decorator
	 *            the {@link CellDecorator} to apply
	 */
	void overrideNumericCellDecorator(CellDecorator decorator);

	/**
	 * Force the boolean cell decorator.
	 * 
	 * @param decorator
	 *            the {@link CellDecorator} to apply
	 */
	void overrideBooleanCellDecorator(CellDecorator decorator);

	/**
	 * Force the date cell decorator.
	 * 
	 * @param decorator
	 *            the {@link CellDecorator} to apply
	 */
	void overrideDateCellDecorator(CellDecorator decorator);

	/**
	 * Add a new cell decorator for a specific use case.
	 * 
	 * @param decoratorName
	 *            the decorator name
	 * @param decorator
	 *            the {@link CellDecorator} to apply
	 */
	void addSpecificCellDecorator(String decoratorName, CellDecorator decorator);

	/**
	 * Force the propagation type to apply at the Sheet.
	 * 
	 * @param type
	 *            the {@link PropagationType} to apply
	 */
	void overridePropagationType(PropagationType type);

	/**
	 * Force the cascade level to apply at the Sheet.
	 * 
	 * @param type
	 *            the {@link CascadeType} to apply
	 */
	void overrideCascadeLevel(CascadeType type);

	/* ############################################# */
	/* ################## TO REVIEW ################ */
	/* ############################################# */

	// <T> Object unmarshal(T obecjt);
	// <T> Collection<T> unmarshalToCollection(T object);

	/**
	 * 
	 * @param object
	 * @throws Exception
	 */
	void marshal(Object object) throws Exception;

	Object unmarshal(Object obecjt) throws IOException, IllegalArgumentException, IllegalAccessException,
			JAEXConverterException, InstantiationException, JAEXElementException;
	
	/**
	 * Generate the workbook from the object and return the FileOutputStream
	 * generated.
	 * 
	 * @param object
	 *            the object to apply at the workbook.
	 * @return the {@link FileOutputStream} generated
	 */
	FileOutputStream marshalToFileOutputStream(Object object) throws Exception;

	Object unmarshalFromFileInputStream(Object object, FileInputStream stream) throws IOException,
			IllegalArgumentException, IllegalAccessException, JAEXConverterException, InstantiationException;
}
