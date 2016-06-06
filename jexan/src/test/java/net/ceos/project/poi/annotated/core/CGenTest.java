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

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import net.ceos.project.poi.annotated.bean.MultiTypeObject;
import net.ceos.project.poi.annotated.bean.MultiTypeObjectBuilder;
import net.ceos.project.poi.annotated.bean.SimpleObject;
import net.ceos.project.poi.annotated.bean.SimpleObjectBuilder;

/**
 * Test the {@link CGen} engine.
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public class CGenTest {

	private int objectNo = 10000;
	
	@DataProvider
	public Object[][] objectProvider() throws Exception {
		return new Object[][] { 
			{ new SimpleObject(), "csvEmptyObject" },
			{ SimpleObjectBuilder.buildSimpleObject(), null },
			{ MultiTypeObjectBuilder.buildMultiTypeObject(), "multiObject" }	};
	}

	@DataProvider
	public Object[][] collectionProvider() throws Exception {
		return new Object[][] { 
			{ new ArrayList<>(), MultiTypeObject.class, "csvEmptyCollection" },
			{ MultiTypeObjectBuilder.buildListOfMultiTypeObject(objectNo), MultiTypeObject.class, null } };
	}

	/**
	 * Test the marshal with:
	 * <ul>
	 * <li>ConfigCrieria and empty object.
	 * <li>basic object
	 * </ul>
	 * 
	 * @throws Exception
	 */
	@Test(dataProvider = "objectProvider")
	public void marshalObject(Object object, String filename) throws Exception {

		IGeneratorCSV en = new CGen();

		if (StringUtils.isNotBlank(filename)) {
			/* marshal with criteria */
			CConfigCriteria criteria = new CConfigCriteria();
			criteria.setFileName(filename);

			en.marshalAndSave(criteria, object, TestUtils.WORKING_DIR_GENERATED_I);
		} else {
			/* marshal without criteria */
			en.marshalAndSave(object, TestUtils.WORKING_DIR_GENERATED_I);
		}
	}

	/**
	 * Test the unmarshal with:
	 * <ul>
	 * <li>ConfigCrieria and empty object.
	 * <li>basic object
	 * </ul>
	 * 
	 * @throws Exception
	 */
	@Test(dataProvider = "objectProvider")
	public void unmarshalObject(Object object, String filename) throws Exception {

		IGeneratorCSV en = new CGen();

		if (StringUtils.isNotBlank(filename)) {
			/* unmarshal with criteria */
			CConfigCriteria criteria = new CConfigCriteria();
			criteria.setFileName(filename);

			en.unmarshalFromPath(criteria, object, TestUtils.WORKING_DIR_GENERATED_I);
		} else {
			/* unmarshal without criteria */
			en.unmarshalFromPath(object, TestUtils.WORKING_DIR_GENERATED_I);
		}
	}

	/**
	 * Test the marshal collection with:
	 * <ul>
	 * <li>ConfigCrieria and empty collection.
	 * <li>collection with 10 000 entries
	 * </ul>
	 * 
	 * @throws Exception
	 */
	@Test(dataProvider="collectionProvider")
	public void marshalAsCollection(Collection<?> collection, Class<?> clazz, String filename) throws Exception {

		IGeneratorCSV en = new CGen();

		if (StringUtils.isNotBlank(filename)) {
			/* marshal with criteria */
			CConfigCriteria criteria = new CConfigCriteria();
			criteria.setFileName(filename);

			en.marshalAsCollectionAndSave(criteria, collection, TestUtils.WORKING_DIR_GENERATED_I);

			assertNotNull(collection);
			assertEquals(collection.size(), 0);
		} else {
			/* marshal without criteria */
			en.marshalAsCollectionAndSave(collection, TestUtils.WORKING_DIR_GENERATED_I);

			assertNotNull(collection);
			assertEquals(collection.size(), objectNo);
		}
	}

	

	/**
	 * Test the marshal with an empty list.
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@Test(dataProvider="collectionProvider")
	public void unmarshalAsCollectionEmpty(Collection collection, Class<?> clazz, String filename) throws Exception {

		IGeneratorCSV en = new CGen();

		if (StringUtils.isNotBlank(filename)) {
			/* unmarshal with criteria */
			CConfigCriteria criteria = new CConfigCriteria();
			criteria.setFileName(filename);

			en.unmarshalAsCollectionFromPath(criteria, clazz, collection, TestUtils.WORKING_DIR_GENERATED_I);
		} else {
			/* unmarshal without criteria */
			en.unmarshalAsCollectionFromPath(clazz, collection, TestUtils.WORKING_DIR_GENERATED_I);
		}
	}
}
