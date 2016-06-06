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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

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

	@Test
	public void marshalSimpleObject() throws Exception {
		SimpleObject fastTest = SimpleObjectBuilder.buildSimpleObject();

		IGeneratorCSV en = new CGen();
		en.marshalAndSave(fastTest, TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test a simple object unmarshal.
	 * 
	 * @throws Exception
	 */
	@Test
	public void unmarshalSimpleObject() throws Exception {
		SimpleObject charged = new SimpleObject();

		IGeneratorCSV en = new CGen();
		en.unmarshalFromPath(charged, TestUtils.WORKING_DIR_GENERATED_I);

		SimpleObjectBuilder.validateSimpleObject(charged);
	}

	/**
	 * Test a multiple type object marshal.
	 * 
	 * @throws Exception
	 */
	@Test
	public void marshalMultipleTypes() throws Exception {
		MultiTypeObject mto = MultiTypeObjectBuilder.buildMultiTypeObject();

		IGeneratorCSV en = new CGen();
		en.marshalAndSave(mto, TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test a multiple type object unmarshal.
	 * 
	 * @throws Exception
	 */
	@Test
	public void unmarshalMultipleTypes() throws Exception {
		MultiTypeObject charged = new MultiTypeObject();

		IGeneratorCSV en = new CGen();
		en.unmarshalFromPath(charged, TestUtils.WORKING_DIR_GENERATED_I);

		MultiTypeObjectBuilder.validateMultiTypeObject(charged);
	}

	/**
	 * Test a list of multiple type object marshal.
	 * 
	 * @throws Exception
	 */
	@Test
	public void marshalAsCollection() throws Exception {
		List<MultiTypeObject> listMulti = MultiTypeObjectBuilder.buildListOfMultiTypeObject(objectNo);

		IGeneratorCSV en = new CGen();
		en.marshalAsCollectionAndSave(listMulti, TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test a list of multiple type object unmarshal.
	 * 
	 * @throws Exception
	 */
	@Test
	public void unmarshalAsCollection() throws Exception {
		List<MultiTypeObject> charged = new ArrayList<MultiTypeObject>();

		IGeneratorCSV en = new CGen();
		en.unmarshalAsCollectionFromPath(MultiTypeObject.class, charged, TestUtils.WORKING_DIR_GENERATED_II);

		assertNotNull(charged);
		assertEquals(charged.size(), objectNo);
	}

	@Test
	public void marshalObjectEmpty() throws Exception {
		SimpleObject mto = new SimpleObject();

		CConfigCriteria criteria = new CConfigCriteria();
		criteria.setFileName("csvEmptyTest");

		IGeneratorCSV en = new CGen();
		en.marshalAndSave(criteria, mto, TestUtils.WORKING_DIR_GENERATED_I);
	}

	@Test
	public void unmarshalObjectEmpty() throws Exception {
		SimpleObject charged = new SimpleObject();

		CConfigCriteria criteria = new CConfigCriteria();
		criteria.setFileName("csvEmptyTest");

		IGeneratorCSV en = new CGen();
		en.unmarshalFromPath(criteria, charged, TestUtils.WORKING_DIR_GENERATED_I);
	}

	@Test
	public void marshalAsCollectionEmpty() throws Exception {
		List<MultiTypeObject> collection = new ArrayList<MultiTypeObject>();

		CConfigCriteria criteria = new CConfigCriteria();
		criteria.setFileName("csvEmptyCollection");

		IGeneratorCSV en = new CGen();
		en.marshalAsCollectionAndSave(criteria, collection, TestUtils.WORKING_DIR_GENERATED_I);

		assertNotNull(collection);
		assertEquals(collection.size(), 0);
	}

	@Test
	public void unmarshalAsCollectionEmpty() throws Exception {
		List<MultiTypeObject> collection = new ArrayList<MultiTypeObject>();

		CConfigCriteria criteria = new CConfigCriteria();
		criteria.setFileName("csvEmptyCollection");

		IGeneratorCSV en = new CGen();
		en.unmarshalAsCollectionFromPath(criteria, MultiTypeObject.class, collection, TestUtils.WORKING_DIR_GENERATED_I);

		assertNotNull(collection);
		assertEquals(collection.size(), 0);
	}
}
