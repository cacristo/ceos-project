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
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Assert;
import org.junit.Test;

import net.ceos.project.poi.annotated.bean.CascadeObject;
import net.ceos.project.poi.annotated.bean.CascadeObjectBuilder;
import net.ceos.project.poi.annotated.bean.MultiTypeObject;
import net.ceos.project.poi.annotated.bean.MultiTypeObjectBuilder;
import net.ceos.project.poi.annotated.definition.ExtensionFileType;
import net.ceos.project.poi.annotated.definition.PropagationType;

/**
 * Test the available methods at interface {@link IEngine}
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public class MarshalUnmarshalTest {

	/**
	 * Test the method 'marshalToSheet' to generate the Excel from the object
	 * and return the {@link Sheet} generated.<br>
	 */
	@Test
	public void testMarshalToSheet() throws Exception {
		MultiTypeObject mto = MultiTypeObjectBuilder.buildMultiTypeObject();

		IEngine en = new Engine();
		Collection<Sheet> sheetList = en.marshalToSheet(mto);

		Assert.assertNotNull(sheetList);
		Assert.assertEquals(sheetList.size(), 1);
		Assert.assertNotNull(sheetList.iterator().next());
	}

	/**
	 * Test the method 'marshalToSheet', using {@link XConfigCriteria}, to
	 * generate the Excel from the object and return the {@link Sheet}
	 * generated.<br>
	 */
	@Test
	public void testMarshalToSheetWithConfigCriteria() throws Exception {
		MultiTypeObject mto = MultiTypeObjectBuilder.buildMultiTypeObject();

		XConfigCriteria configuration = new XConfigCriteria();
		configuration.overridePropagationType(PropagationType.PROPAGATION_VERTICAL);

		IEngine en = new Engine();
		Collection<Sheet> sheetList = en.marshalToSheet(configuration, mto);

		Assert.assertNotNull(sheetList);
		Assert.assertEquals(sheetList.size(), 1);
		Assert.assertNotNull(sheetList.iterator().next());
	}

	/**
	 * Test the method 'marshalToSheet', using {@link XConfigCriteria}, to
	 * generate the Excel from the object and return the {@link Sheet}
	 * generated.<br>
	 */
	@Test
	public void testMarshalToMultiSheetWithConfigCriteria() throws Exception {
		CascadeObject cascade = CascadeObjectBuilder.buildCascadeObject();

		XConfigCriteria configuration = new XConfigCriteria();
		configuration.overridePropagationType(PropagationType.PROPAGATION_VERTICAL);

		// FIXME problem here in case of vertical propagation (Work ok at Horizontal)
		
		IEngine en = new Engine();
		Collection<Sheet> sheetList = en.marshalToSheet(configuration, cascade);

		Assert.assertNotNull(sheetList);
		Assert.assertEquals(sheetList.size(), 6);
	}

	/**
	 * Test the method 'marshalToWorkbook' to generate the Excel from the object
	 * and return the {@link Workbook} generated.<br>
	 */
	@Test
	public void testMarshalToWorkbook() throws Exception {
		MultiTypeObject mto = MultiTypeObjectBuilder.buildMultiTypeObject();

		IEngine en = new Engine();
		Workbook w = en.marshalToWorkbook(mto);

		Assert.assertNotNull(w);
	}

	/**
	 * Test the method 'marshalToWorkbook', using {@link XConfigCriteria}, to
	 * generate the Excel from the object and return the {@link Workbook}
	 * generated.<br>
	 */
	@Test
	public void testMarshalToWorkbookWithConfigCriteria() throws Exception {
		MultiTypeObject mto = MultiTypeObjectBuilder.buildMultiTypeObject();

		XConfigCriteria configuration = new XConfigCriteria();
		configuration.overridePropagationType(PropagationType.PROPAGATION_VERTICAL);

		IEngine en = new Engine();
		Workbook w = en.marshalToWorkbook(configuration, mto);

		Assert.assertNotNull(w);
	}

	/**
	 * Test the method 'marshalAndSave' to generate the Excel from the object
	 * and save it at the path file indicated.
	 */
	@Test
	public void testMarshalAndSave() throws Exception {
		MultiTypeObject mto = MultiTypeObjectBuilder.buildMultiTypeObject();
		String outputPath = TestUtils.WORKING_DIR_GENERATED_I + "\\";

		IEngine en = new Engine();
		en.marshalAndSave(mto, outputPath);
	}

	/**
	 * Test the method 'marshalAndSave', using {@link XConfigCriteria}, to
	 * generate the Excel from the object and save it at the path file
	 * indicated.
	 */
	@Test
	public void testMarshalAndSaveWithConfigCriteria() throws Exception {
		MultiTypeObject mto = MultiTypeObjectBuilder.buildMultiTypeObject();
		String outputPath = TestUtils.WORKING_DIR_GENERATED_II;

		XConfigCriteria configuration = new XConfigCriteria();
		configuration.overrideExtensionType(ExtensionFileType.XLS);

		IEngine en = new Engine();
		en.marshalAndSave(mto, outputPath);
	}

	/**
	 * Test the method 'marshalToByte' to generate the Excel from the object and
	 * return the byte[] generated.<br>
	 */
	@Test
	public void testMarshalToByte() throws Exception {
		MultiTypeObject mto = MultiTypeObjectBuilder.buildMultiTypeObject();

		IEngine en = new Engine();
		byte[] generatedBytes = en.marshalToByte(mto);

		Assert.assertNotNull(generatedBytes);
	}

	/**
	 * Test the method 'marshalToByte' to generate the Excel from the object and
	 * return the byte[] generated.<br>
	 */
	@Test
	public void testMarshalToByteWithConfigCriteria() throws Exception {
		MultiTypeObject mto = MultiTypeObjectBuilder.buildMultiTypeObject();

		XConfigCriteria configuration = new XConfigCriteria();
		configuration.overrideExtensionType(ExtensionFileType.XLS);

		IEngine en = new Engine();
		byte[] generatedBytes = en.marshalToByte(configuration, mto);

		Assert.assertNotNull(generatedBytes);
	}

	/**
	 * Test the method 'marshalCollectionToSheet' to generate the Excel from the
	 * collection of objects and return the {@link Sheet} generated.<br>
	 */
	@Test
	public void testMarshalCollectionToSheet() throws Exception {
		List<MultiTypeObject> mto = MultiTypeObjectBuilder.buildListOfMultiTypeObject(100);

		IEngine en = new Engine();
		Sheet s = en.marshalCollectionToSheet(mto);

		Assert.assertNotNull(s);
	}

	/**
	 * Test the method 'marshalCollectionToSheet', using {@link XConfigCriteria}
	 * , to generate the Excel from the collection of objects and return the
	 * {@link Sheet} generated.<br>
	 */
	@Test
	public void testMarshalCollectionToSheetWithConfigCriteria() throws Exception {
		List<MultiTypeObject> mto = MultiTypeObjectBuilder.buildListOfMultiTypeObject(100);

		XConfigCriteria configuration = new XConfigCriteria();
		configuration.overridePropagationType(PropagationType.PROPAGATION_VERTICAL);

		IEngine en = new Engine();
		Sheet s = en.marshalCollectionToSheet(configuration, mto);

		Assert.assertNotNull(s);
	}

	/**
	 * Test the method 'marshalCollectionToWorkbook' to generate the Excel from
	 * the collection of objects and return the {@link Workbook} generated.<br>
	 */
	@Test
	public void testMarshalCollectionToWorkbook() throws Exception {
		List<MultiTypeObject> collection = MultiTypeObjectBuilder.buildListOfMultiTypeObject(100);

		IEngine en = new Engine();
		Workbook w = en.marshalCollectionToWorkbook(collection);

		Assert.assertNotNull(w);
	}

	/**
	 * Test the method 'marshalCollectionToWorkbook', using
	 * {@link XConfigCriteria}, to generate the Excel from the object and return
	 * the {@link Workbook} generated.<br>
	 */
	@Test
	public void testMarshalCollectionToWorkbookWithConfigCriteria() throws Exception {
		List<MultiTypeObject> collection = MultiTypeObjectBuilder.buildListOfMultiTypeObject(100);

		XConfigCriteria configuration = new XConfigCriteria();
		configuration.overridePropagationType(PropagationType.PROPAGATION_VERTICAL);

		IEngine en = new Engine();
		Workbook w = en.marshalCollectionToWorkbook(configuration, collection);

		Assert.assertNotNull(w);
	}

	/**
	 * Test the method 'marshalAsCollectionAndSave' to generate the Excel from
	 * the collection of objects and save it at the path file indicated.
	 */
	@Test
	public void testMarshalAsCollectionAndSave() throws Exception {
		List<MultiTypeObject> collection = MultiTypeObjectBuilder.buildListOfMultiTypeObject(100);

		IEngine en = new Engine();
		en.marshalAsCollectionAndSave(collection, TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test the method 'marshalAsCollectionAndSave', using
	 * {@link XConfigCriteria}, to generate the Excel from the collection of
	 * objects and save it at the path file indicated.
	 */
	@Test
	public void testMarshalAsCollectionAndSaveWithConfigCriteria() throws Exception {
		List<MultiTypeObject> collection = MultiTypeObjectBuilder.buildListOfMultiTypeObject(100);

		XConfigCriteria configCriteria = new XConfigCriteria();
		configCriteria.setFileName("CollectionMultiType");
		configCriteria.overrideExtensionType(ExtensionFileType.XLSX);

		IEngine en = new Engine();
		en.marshalAsCollectionAndSave(configCriteria, collection, TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test the method 'marshalCollectionToByte' to generate the Excel from the
	 * collection of objects and return the byte[] generated.<br>
	 */
	@Test
	public void testMarshalCollectionToByte() throws Exception {
		List<MultiTypeObject> collection = MultiTypeObjectBuilder.buildListOfMultiTypeObject(100);

		IEngine en = new Engine();
		byte[] generatedBytes = en.marshalCollectionToByte(collection);

		Assert.assertNotNull(generatedBytes);
	}

	/**
	 * Test the method 'marshalCollectionToByte', using {@link XConfigCriteria},
	 * to generate the Excel from the collection of objects and return the
	 * byte[] generated.<br>
	 */
	@Test
	public void testMarshalCollectionToByteWithConfigCriteria() throws Exception {
		List<MultiTypeObject> collection = MultiTypeObjectBuilder.buildListOfMultiTypeObject(100);

		XConfigCriteria configCriteria = new XConfigCriteria();
		configCriteria.setFileName("CollectionMultiTypeII");
		configCriteria.overrideExtensionType(ExtensionFileType.XLSX);
		
		IEngine en = new Engine();
		byte[] generatedBytes = en.marshalCollectionToByte(configCriteria, collection);

		Assert.assertNotNull(generatedBytes);
	}

	/**
	 * DataProvider : Test the method 'marshalToWorkbook' to generate the Excel
	 * from the object and return the Workbook generated.<br>
	 * 
	 * Test the method 'unmarshalFromWorkbook' reading the Excel from the
	 * Workbook passed as parameter and bring the data to the object.
	 */
	@Test
	public void testUnmarshalFromWorkbook() throws Exception {

		MultiTypeObject mto = MultiTypeObjectBuilder.buildMultiTypeObject();
		IEngine en = new Engine();
		Workbook workbook = en.marshalToWorkbook(mto);

		MultiTypeObject charger = new MultiTypeObject();
		IEngine en1 = new Engine();
		en1.unmarshalFromWorkbook(charger, workbook);

		MultiTypeObjectBuilder.validateMultiTypeObject(charger);
	}

	/**
	 * DataProvider : Test the method 'marshalToByte' to generate the Excel from
	 * the object and return the byte[] generated.<br>
	 * 
	 * Test the method 'unmarshalFromByte' reading the Excel from the byte[]
	 * passed as parameter and bring the data to the object.
	 */
	@Test
	public void testlUnmarshalFromByte() throws Exception {

		MultiTypeObject mto = MultiTypeObjectBuilder.buildMultiTypeObject();
		IEngine en1 = new Engine();
		byte[] generatedBytes = en1.marshalToByte(mto);

		MultiTypeObject charger = new MultiTypeObject();
		IEngine en = new Engine();
		en.unmarshalFromByte(charger, generatedBytes);

		MultiTypeObjectBuilder.validateMultiTypeObject(charger);
	}

	/**
	 * Test the method 'unmarshalFromPath' reading the Excel from a specific
	 * path file indicated and bring the data to the object.
	 */
	@Test
	public void testUnmarshalFromPath() throws Exception {
		MultiTypeObject mto = MultiTypeObjectBuilder.buildMultiTypeObject();
		String outputPath = TestUtils.WORKING_DIR_GENERATED_I + "\\";

		IEngine eW = new Engine();
		eW.marshalAndSave(mto, outputPath);
		
		MultiTypeObject charger = new MultiTypeObject();

		IEngine eR = new Engine();
		eR.unmarshalFromPath(charger, TestUtils.WORKING_DIR_GENERATED_I);

		MultiTypeObjectBuilder.validateMultiTypeObject(charger);
	}

	/**
	 * Test the method 'unmarshalFromPath' reading the Excel from a specific
	 * path file indicated and bring the data to the object.
	 */
	@Test
	public void testUnmarshalFromCollection() throws Exception {
		/* TODO create this test */
	}

}
