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

import net.ceos.project.poi.annotated.bean.BasicObject;
import net.ceos.project.poi.annotated.bean.BasicObjectBuilder;
import net.ceos.project.poi.annotated.bean.MultiTypeObject;
import net.ceos.project.poi.annotated.bean.MultiTypeObjectBuilder;
import net.ceos.project.poi.annotated.bean.cascade.CascadeObject;
import net.ceos.project.poi.annotated.bean.cascade.CascadeObjectBuilder;
import net.ceos.project.poi.annotated.definition.CascadeType;
import net.ceos.project.poi.annotated.definition.ExtensionFileType;
import net.ceos.project.poi.annotated.definition.PropagationType;

/**
 * Test the available methods at interface {@link IEngine}
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public class MarshalUnmarshalTest {

	/*
	 * TODO (1) see the behavior of using only one instance of the JAEX object
	 * inside one project
	 */
	/* (1.1) see the wb object */
	/* (1.2) see the configuration object */
	/* (1.3) see the stylesMap object */
	/* (1.4) see the cellDecoratorMap object */
	/* (1.4) see the headerDecorator object */

	/* TODO (2) manage the internal value of an Enum */

	/*
	 * TODO (3) fix numeric code like 00005 parsed to excel will maintain the
	 * same code to do it you just have to add '00005
	 */

	/**
	 * Test with default settings
	 */
	@Test
	public void testBasicConfiguration() throws Exception {
		BasicObject bO = BasicObjectBuilder.buildBasicObject();

		IEngine en = new Engine();
		en.marshalAndSave(bO, TestUtils.WORKING_DIR_GENERATED_I);

		BasicObject charger = new BasicObject();

		en.unmarshalFromPath(charger, TestUtils.WORKING_DIR_GENERATED_I);
		BasicObjectBuilder.validateBasicObject(charger);
	}

	/**
	 * Test empty object
	 */
	@Test
	public void testEmptyObject() throws Exception {
		MultiTypeObject mto = new MultiTypeObject();

		IEngine en = new Engine();
		en.marshalAndSave(mto, TestUtils.WORKING_DIR_GENERATED_I);

		BasicObject charger = new BasicObject();

		en.unmarshalFromPath(charger, TestUtils.WORKING_DIR_GENERATED_I);
		BasicObjectBuilder.validateBasicObject(charger);
	}

	/**
	 * Test the method 'marshalToSheet' to generate the Excel from the object
	 * and return the {@link Sheet} generated.
	 */
	@Test
	public void validateMarshalToSheet() throws Exception {
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
	 * generated.
	 */
	@Test
	public void validateMarshalToSheetWithConfigCriteria() throws Exception {
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
	 * generated.
	 */
	@Test
	public void validateMarshalToMultiSheetWithConfigCriteria() throws Exception {
		CascadeObject cascade = CascadeObjectBuilder.buildCascadeObject();

		XConfigCriteria configuration = new XConfigCriteria();
		configuration.overridePropagationType(PropagationType.PROPAGATION_HORIZONTAL);

		configuration.overrideCascadeLevel(CascadeType.CASCADE_L3);
		IEngine en = new Engine();
		Collection<Sheet> sheetList = en.marshalToSheet(configuration, cascade);

		Assert.assertNotNull(sheetList);
		Assert.assertEquals(sheetList.size(), 6);
	}

	/**
	 * Test the method 'marshalToWorkbook' to generate the Excel from the object
	 * and return the {@link Workbook} generated.
	 */
	@Test
	public void validateMarshalToWorkbook() throws Exception {
		MultiTypeObject mto = MultiTypeObjectBuilder.buildMultiTypeObject();

		IEngine en = new Engine();
		Workbook w = en.marshalToWorkbook(mto);

		Assert.assertNotNull(w);
	}

	/**
	 * Test the method 'marshalToWorkbook', using {@link XConfigCriteria}, to
	 * generate the Excel from the object and return the {@link Workbook}
	 * generated.
	 */
	@Test
	public void validateMarshalToWorkbookWithConfigCriteria() throws Exception {
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
	public void validateMarshalAndSave() throws Exception {
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
	public void validateMarshalAndSaveWithConfigCriteria() throws Exception {
		MultiTypeObject mto = MultiTypeObjectBuilder.buildMultiTypeObject();
		String outputPath = TestUtils.WORKING_DIR_GENERATED_II;

		XConfigCriteria configuration = new XConfigCriteria();
		configuration.overrideExtensionType(ExtensionFileType.XLS);

		IEngine en = new Engine();
		en.marshalAndSave(mto, outputPath);
	}

	/**
	 * Test the method 'marshalToByte' to generate the Excel from the object and
	 * return the byte[] generated.
	 */
	@Test
	public void validateMarshalToByte() throws Exception {
		MultiTypeObject mto = MultiTypeObjectBuilder.buildMultiTypeObject();

		IEngine en = new Engine();
		byte[] generatedBytes = en.marshalToByte(mto);

		Assert.assertNotNull(generatedBytes);
	}

	/**
	 * Test the method 'marshalToByte' to generate the Excel from the object and
	 * return the byte[] generated.
	 */
	@Test
	public void validateMarshalToByteWithConfigCriteria() throws Exception {
		MultiTypeObject mto = MultiTypeObjectBuilder.buildMultiTypeObject();

		XConfigCriteria configuration = new XConfigCriteria();
		configuration.overrideExtensionType(ExtensionFileType.XLS);

		IEngine en = new Engine();
		byte[] generatedBytes = en.marshalToByte(configuration, mto);

		Assert.assertNotNull(generatedBytes);
	}

	/**
	 * Test the method 'marshalCollectionToSheet' to generate the Excel from the
	 * collection of objects and return the {@link Sheet} generated.
	 */
	@Test
	public void validateMarshalCollectionToSheet() throws Exception {
		List<MultiTypeObject> mto = MultiTypeObjectBuilder.buildListOfMultiTypeObject(100);

		IEngine en = new Engine();
		Sheet s = en.marshalCollectionToSheet(mto);

		Assert.assertNotNull(s);
	}

	/**
	 * Test the method 'marshalCollectionToSheet', using {@link XConfigCriteria}
	 * , to generate the Excel from the collection of objects and return the
	 * {@link Sheet} generated.
	 */
	@Test
	public void validateMarshalCollectionToSheetWithConfigCriteria() throws Exception {
		List<MultiTypeObject> mto = MultiTypeObjectBuilder.buildListOfMultiTypeObject(100);

		XConfigCriteria configuration = new XConfigCriteria();
		configuration.overridePropagationType(PropagationType.PROPAGATION_VERTICAL);

		IEngine en = new Engine();
		Sheet s = en.marshalCollectionToSheet(configuration, mto);

		Assert.assertNotNull(s);
	}

	/**
	 * Test the method 'marshalCollectionToWorkbook' to generate the Excel from
	 * the collection of objects and return the {@link Workbook} generated.
	 */
	@Test
	public void validateMarshalCollectionToWorkbook() throws Exception {
		List<MultiTypeObject> collection = MultiTypeObjectBuilder.buildListOfMultiTypeObject(100);

		IEngine en = new Engine();
		Workbook w = en.marshalCollectionToWorkbook(collection);

		Assert.assertNotNull(w);
	}

	/**
	 * Test the method 'marshalCollectionToWorkbook', using
	 * {@link XConfigCriteria}, to generate the Excel from the object and return
	 * the {@link Workbook} generated.
	 */
	@Test
	public void validateMarshalCollectionToWorkbookWithConfigCriteria() throws Exception {
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
	public void validateMarshalAsCollectionAndSave() throws Exception {
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
	public void validateMarshalAsCollectionAndSaveWithConfigCriteria() throws Exception {
		List<MultiTypeObject> collection = MultiTypeObjectBuilder.buildListOfMultiTypeObject(100);

		XConfigCriteria configCriteria = new XConfigCriteria();
		configCriteria.setFileName("CollectionMultiType");
		configCriteria.overrideExtensionType(ExtensionFileType.XLSX);

		IEngine en = new Engine();
		en.marshalAsCollectionAndSave(configCriteria, collection, TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test the method 'marshalCollectionToByte' to generate the Excel from the
	 * collection of objects and return the byte[] generated.
	 */
	@Test
	public void validateMarshalCollectionToByte() throws Exception {
		List<MultiTypeObject> collection = MultiTypeObjectBuilder.buildListOfMultiTypeObject(100);

		IEngine en = new Engine();
		byte[] generatedBytes = en.marshalCollectionToByte(collection);

		Assert.assertNotNull(generatedBytes);
	}

	/**
	 * Test the method 'marshalCollectionToByte', using {@link XConfigCriteria},
	 * to generate the Excel from the collection of objects and return the
	 * byte[] generated.
	 */
	@Test
	public void validateMarshalCollectionToByteWithConfigCriteria() throws Exception {
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
	 * from the object and return the Workbook generated.
	 * 
	 * Test the method 'unmarshalFromWorkbook' reading the Excel from the
	 * Workbook passed as parameter and bring the data to the object.
	 */
	@Test
	public void validateUnmarshalFromWorkbook() throws Exception {
		MultiTypeObject mto = MultiTypeObjectBuilder.buildMultiTypeObject();
		MultiTypeObject charger = new MultiTypeObject();

		IEngine en = new Engine();
		Workbook wb = en.marshalToWorkbook(mto);

		en.unmarshalFromWorkbook(charger, wb);

		MultiTypeObjectBuilder.validateMultiTypeObject(charger);
	}

	/**
	 * DataProvider : Test the method 'marshalToByte' to generate the Excel from
	 * the object and return the byte[] generated.
	 * <p>
	 * Test the method 'unmarshalFromByte' reading the Excel from the byte[]
	 * passed as parameter and bring the data to the object.
	 */
	@Test
	public void validatelUnmarshalFromByte() throws Exception {
		MultiTypeObject mto = MultiTypeObjectBuilder.buildMultiTypeObject();
		MultiTypeObject charger = new MultiTypeObject();

		IEngine en = new Engine();
		byte[] bytes = en.marshalToByte(mto);

		en.unmarshalFromByte(charger, bytes);

		MultiTypeObjectBuilder.validateMultiTypeObject(charger);
	}

	/**
	 * Test the method 'unmarshalFromPath' reading the Excel from a specific
	 * path file indicated and bring the data to the object.
	 */
	@Test
	public void validateUnmarshalFromPath() throws Exception {
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
	public void validateUnmarshalFromCollection() throws Exception {
		/* TODO create this test */
	}

}
