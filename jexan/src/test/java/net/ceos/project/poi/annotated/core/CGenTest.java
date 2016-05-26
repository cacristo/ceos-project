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

	/**
	 * Test a simple object marshal.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testMarshalSimpleObject() throws Exception {
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
	public void testUnmarshalSimpleObject() throws Exception {
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
	public void testMarshalMultipleTypes() throws Exception {
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
	public void testUnmarshalMultipleTypes() throws Exception {
		MultiTypeObject charged = new MultiTypeObject();

		IGeneratorCSV en = new CGen();
		en.unmarshalFromPath(charged, TestUtils.WORKING_DIR_GENERATED_I);

		MultiTypeObjectBuilder.validateMultiTypeObject(charged);
	}
	
	/**
	 * Test a list of multiple type object marshal.
	 * @throws Exception
	 */
	@Test
	public void testMarshalAsCollection() throws Exception {
		List<MultiTypeObject> listMulti = MultiTypeObjectBuilder.buildListOfMultiTypeObject(10000);

		IGeneratorCSV en = new CGen();
		en.marshalAsCollectionAndSave(listMulti, TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test a list of multiple type object unmarshal.
	 * @throws Exception
	 */
	@Test
	public void testUnmarshalAsCollection() throws Exception {
		List<MultiTypeObject> charged = new ArrayList<MultiTypeObject>();
		
		IGeneratorCSV en = new CGen();
		en.unmarshalAsCollectionFromPath(MultiTypeObject.class, charged, TestUtils.WORKING_DIR_GENERATED_II);
	}

	@Test
	public void testMarshalObjectEmpty() throws Exception {
		SimpleObject mto = new SimpleObject();

		CConfigCriteria criteria = new CConfigCriteria();
		criteria.setFileName("csvEmptyTest");
		
		IGeneratorCSV en = new CGen();
		en.marshalAndSave(criteria, mto, TestUtils.WORKING_DIR_GENERATED_I);
	}

	@Test
	public void testUnmarshalObjectEmpty() throws Exception {
		SimpleObject charged = new SimpleObject();
		
		CConfigCriteria criteria = new CConfigCriteria();
		criteria.setFileName("csvEmptyTest");
		
		IGeneratorCSV en = new CGen();
		en.unmarshalFromPath(criteria, charged, TestUtils.WORKING_DIR_GENERATED_I);
	}


	@Test
	public void testMarshalAsCollectionEmpty() throws Exception {
		List<MultiTypeObject> listMulti = new ArrayList<MultiTypeObject>();

		IGeneratorCSV en = new CGen();
		en.marshalAsCollectionAndSave(listMulti, TestUtils.WORKING_DIR_GENERATED_I);
	}

	@Test
	public void testUnmarshalAsCollectionEmpty() throws Exception {
		List<MultiTypeObject> charged = new ArrayList<MultiTypeObject>();

		IGeneratorCSV en = new CGen();
		en.unmarshalAsCollectionFromPath(MultiTypeObject.class, charged, TestUtils.WORKING_DIR_MANUALLY);

	}
}
