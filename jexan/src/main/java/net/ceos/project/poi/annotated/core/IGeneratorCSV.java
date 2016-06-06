/**
 * Copyright 2016 Carlos CRISTO ABREU
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.ceos.project.poi.annotated.core;

import java.util.Collection;

import net.ceos.project.poi.annotated.exception.WorkbookException;

public interface IGeneratorCSV {

	/**
	 * Generate the CSV file based at the object passed as parameters and save
	 * it at the path send as parameter.
	 * 
	 * @param object
	 *            the object to apply at the CSV file.
	 * @param pathFile
	 *            the path where is found the file to read and pass the
	 *            information to the object
	 * @throws WorkbookException given when a not supported action.
	 */
	void marshalAndSave(final Object object, final String pathFile) throws WorkbookException;

	/**
	 * Generate the CSV file based at {@link CConfigCriteria} and the object
	 * passed as parameters and save it at the path send as parameter.
	 * 
	 * @param configCriteria
	 *            the {@link CConfigCriteria} to use
	 * @param object
	 *            the object to apply at the CSV file.
	 * @param pathFile
	 *            the path where is found the file to read and pass the
	 *            information to the object
	 * @throws WorkbookException given when a not supported action.
	 */
	void marshalAndSave(final CConfigCriteria configCriteria, final Object object, final String pathFile)
			throws WorkbookException;

	/**
	 * Generate the CSV file from the collection of objects.
	 * 
	 * @param listObject
	 *            the collection of objects to apply at the CSV file.
	 * @param pathFile
	 *            the path where is found the file to read and pass the
	 *            information to the object
	 * @throws WorkbookException given when a not supported action.
	 */
	void marshalAsCollectionAndSave(final Collection<?> listObject, final String pathFile) throws WorkbookException;

	/**
	 * Generate the CSV file, based at {@link CConfigCriteria}, from the collection of objects.
	 * 
	 * @param configCriteria
	 *            the {@link CConfigCriteria} to use
	 * @param listObject
	 *            the collection of objects to apply at the CSV file.
	 * @param pathFile
	 *            the path where is found the file to read and pass the
	 *            information to the object
	 * @throws WorkbookException given when a not supported action.
	 */
	void marshalAsCollectionAndSave(final CConfigCriteria configCriteria, final Collection<?> listObject,
			final String pathFile) throws WorkbookException;

	/**
	 * Generate the object from the path file passed as parameter.
	 * 
	 * @param object
	 *            the object to fill up.
	 * @param pathFile
	 *            the path where is found the file to read and pass the
	 *            information to the object
	 * @throws WorkbookException given when a not supported action.
	 */
	void unmarshalFromPath(final Object object, final String pathFile) throws WorkbookException;

	/**
	 * Generate the object from, based at {@link CConfigCriteria}, the path file
	 * passed as parameter.
	 * 
	 * @param configCriteria
	 *            the {@link CConfigCriteria} to use
	 * @param object
	 *            the object to fill up.
	 * @param pathFile
	 *            the path where is found the file to read and pass the
	 *            information to the object
	 * @throws WorkbookException given when a not supported action.
	 */
	void unmarshalFromPath(final CConfigCriteria configCriteria, final Object object, final String pathFile)
			throws WorkbookException;

	/**
	 * Charge the collection of object from the path file passed as parameter.
	 * 
	 * @param oC
	 *            the object class will read and inserted into the collection
	 * @param listObject
	 *            the collection to fill up.
	 * @param pathFile
	 *            the path where is found the file to read and pass the
	 *            information to the collection
	 * @throws WorkbookException given when a not supported action.
	 */
	@SuppressWarnings("rawtypes")
	void unmarshalAsCollectionFromPath(final Class<?> oC, final Collection listObject, final String pathFile)
			throws WorkbookException;

	/**
	 * Charge the collection of object, based at {@link CConfigCriteria}, from
	 * the path file passed as parameter.
	 * 
	 * @param configCriteria
	 *            the {@link CConfigCriteria} to use
	 * @param oC
	 *            the object class will read and inserted into the collection
	 * @param listObject
	 *            the collection to fill up.
	 * @param pathFile
	 *            the path where is found the file to read and pass the
	 *            information to the collection
	 * @throws WorkbookException given when a not supported action.
	 */
	@SuppressWarnings("rawtypes")
	void unmarshalAsCollectionFromPath(final CConfigCriteria configCriteria, final Class<?> oC,
			final Collection listObject, final String pathFile) throws WorkbookException;

}
