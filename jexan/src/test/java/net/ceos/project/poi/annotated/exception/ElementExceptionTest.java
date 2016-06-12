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
package net.ceos.project.poi.annotated.exception;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import net.ceos.project.poi.annotated.annotation.XlsElement;
import net.ceos.project.poi.annotated.annotation.XlsFreeElement;
import net.ceos.project.poi.annotated.bean.MultiTypeObject;
import net.ceos.project.poi.annotated.bean.SimpleObject;
import net.ceos.project.poi.annotated.bean.factory.AvengersFactory;
import net.ceos.project.poi.annotated.bean.factory.AvengersFactory.Hawkeye;
import net.ceos.project.poi.annotated.bean.factory.FantasticFourDecoratorFactory;
import net.ceos.project.poi.annotated.bean.factory.MarvelBadGuysFactory;
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
		Hawkeye complexObject = AvengersFactory.instanceHawkeye();

		IEngine en = new Engine();
		en.marshalToSheet(complexObject);
	}

	/**
	 * Test a {@link XlsElement} & {@link XlsFreeElement} trying write at one
	 * cell already used
	 */
	@Test(expected = ElementException.class)
	public void testMarshalXlsElementOverwriteCell() throws Exception {
		IEngine en = new Engine();
		en.marshalToWorkbook(AvengersFactory.instanceHankPym());
	}

	/**
	 * Test a {@link XlsElement} & {@link XlsFreeElement} trying write at one
	 * cell already used
	 */
	@Test(expected = ElementException.class)
	public void testMarshalXlsFreeElementOverwriteCell() throws Exception {
		IEngine en = new Engine();
		en.marshalToWorkbook(AvengersFactory.instanceLukeCage());
	}

	/**
	 * Test marshal and save with a invalid position: PropagationType HORIZONTAL
	 * and element entry with a invalid position
	 */
	@Test(expected = ElementException.class)
	public void invalidPositionHorizontalElementMarshalException() throws Exception {
		IEngine en = new Engine();
		en.marshalAndSave(MarvelBadGuysFactory.instanceLoki(), TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test marshal and save with a invalid position PropagationType VERTICAL
	 * and element entry with a invalid position
	 */
	@Test(expected = ElementException.class)
	public void invalidPositionVerticalElementMarshalException() throws Exception {
		IEngine en = new Engine();
		en.marshalAndSave(MarvelBadGuysFactory.instanceRedSkull(), TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test marshal and save with a invalid position Free element entry with a
	 * invalid cell position
	 */
	@Test(expected = ElementException.class)
	public void invalidPositionCellElementMarshalException() throws Exception {
		IEngine en = new Engine();
		en.marshalAndSave(MarvelBadGuysFactory.instanceMystique(), TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test marshal and save with a invalid position Free element entry with a
	 * invalid row position
	 */
	@Test(expected = ElementException.class)
	public void invalidPositionRowElementMarshalException() throws Exception {
		IEngine en = new Engine();
		en.marshalAndSave(MarvelBadGuysFactory.instanceThanos(), TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test unmarshal and save with a invalid position PropagationType
	 * HORIZONTAL and element entry with a invalid position
	 */
	@Test(expected = ElementException.class)
	public void invalidPositionHorizontalElementUnmarshalException() throws Exception {
		IEngine en = new Engine();
		en.unmarshalFromPath(MarvelBadGuysFactory.instanceLoki(), TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test unmarshal and save with a invalid position PropagationType VERTICAL
	 * and element entry with a invalid position
	 */
	@Test(expected = ElementException.class)
	public void invalidPositionVerticalElementUnmarshalException() throws Exception {
		IEngine en = new Engine();
		en.unmarshalFromPath(MarvelBadGuysFactory.instanceRedSkull(), TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test unmarshal and save with a invalid position Free element entry with a
	 * invalid cell position
	 */
	@Test(expected = ElementException.class)
	public void invalidPositionCellElementUnmarshalException() throws Exception {
		IEngine en = new Engine();
		en.unmarshalFromPath(MarvelBadGuysFactory.instanceMystique(), TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test unmarshal and save with a invalid position Free element entry with a
	 * invalid row position
	 */
	@Test(expected = ElementException.class)
	public void invalidPositionElementUnmarshalException() throws Exception {
		IEngine en = new Engine();
		en.unmarshalFromPath(MarvelBadGuysFactory.instanceThanos(), TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test a horizontal configuration exception conflict
	 */
	@Test(expected = ElementException.class)
	public void testXlsConflictConfigurationExceptionHorizontal() throws Exception {
		IEngine en = new Engine();
		en.marshalAndSave(AvengersFactory.instanceCaptainAmerica(), TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test a vertical configuration exception conflict
	 */
	@Test(expected = ElementException.class)
	public void testXlsConflictConfigurationExceptionVertical() throws Exception {
		IEngine en = new Engine();
		en.marshalAndSave(AvengersFactory.instanceCaptainMarvel(), TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test a horizontal non-conflict annotation type
	 */
	@Test(expected = ElementException.class)
	public void testXlsConflictAnnotationHorizontal() throws Exception {
		IEngine en = new Engine();
		en.marshalAndSave(AvengersFactory.instanceBlackPanther(), TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test a vertical non-conflict annotation type
	 */
	@Test(expected = ElementException.class)
	public void testXlsConflictAnnotationVertical() throws Exception {
		IEngine en = new Engine();
		en.marshalAndSave(AvengersFactory.instanceBlackWidow(), TestUtils.WORKING_DIR_GENERATED_I);
	}

	/*
	 * Test a comment rules @String which does not exist a method
	 */
	@Test(expected = ElementException.class)
	public void configurationMissingDecoratorStringException() throws Exception {
		IEngine en = new Engine();
		en.marshalAndSave(FantasticFourDecoratorFactory.instanceMrFantastic(), TestUtils.WORKING_DIR_GENERATED_I);
	}

	/*
	 * Test a comment rules @Integer which does not exist a method
	 */
	@Test(expected = ElementException.class)
	public void configurationMissingDecoratorIntegerException() throws Exception {
		IEngine en = new Engine();
		en.marshalAndSave(FantasticFourDecoratorFactory.instanceInvisibleWoman(), TestUtils.WORKING_DIR_GENERATED_I);
	}

	/*
	 * Test a comment rules @Double which does not exist a method
	 */
	@Test(expected = ElementException.class)
	public void configurationMissingDecoratorDoubleException() throws Exception {
		IEngine en = new Engine();
		en.marshalAndSave(FantasticFourDecoratorFactory.instanceThing(), TestUtils.WORKING_DIR_GENERATED_I);
	}

	/*
	 * Test a comment rules @Date which does not exist a method
	 */
	@Test(expected = ElementException.class)
	public void configurationMissingDecoratorDateException() throws Exception {
		IEngine en = new Engine();
		en.marshalAndSave(FantasticFourDecoratorFactory.instanceHumanTorch(), TestUtils.WORKING_DIR_GENERATED_I);
	}

	/*
	 * Test a comment rules @BigDecimal which does not exist a method
	 */
	@Test(expected = ElementException.class)
	public void configurationMissingDecoratorBigDecimalException() throws Exception {
		IEngine en = new Engine();
		en.marshalAndSave(FantasticFourDecoratorFactory.instanceAntMan(), TestUtils.WORKING_DIR_GENERATED_I);
	}

	/*
	 * Test a comment rules @Float which does not exist a method
	 */
	@Test(expected = ElementException.class)
	public void configurationMissingDecoratorFloatException() throws Exception {
		IEngine en = new Engine();
		en.marshalAndSave(FantasticFourDecoratorFactory.instanceBlackPanther(), TestUtils.WORKING_DIR_GENERATED_I);
	}

	/*
	 * Test a comment rules @Boolean which does not exist a method
	 */
	@Test(expected = ElementException.class)
	public void configurationMissingDecoratorBooleanException() throws Exception {
		IEngine en = new Engine();
		en.marshalAndSave(FantasticFourDecoratorFactory.instanceCrystal(), TestUtils.WORKING_DIR_GENERATED_I);
	}

	/*
	 * Test a comment rules @Long which does not exist a method
	 */
	@Test(expected = ElementException.class)
	public void configurationMissingDecoratorLongException() throws Exception {
		IEngine en = new Engine();
		en.marshalAndSave(FantasticFourDecoratorFactory.instanceDrDoom(), TestUtils.WORKING_DIR_GENERATED_I);
	}

	/*
	 * Test a comment rules @Object which does not exist a method
	 */
	@Test(expected = ElementException.class)
	public void configurationMissingDecoratorObjectException() throws Exception {
		IEngine en = new Engine();
		en.marshalAndSave(FantasticFourDecoratorFactory.instanceFlux(), TestUtils.WORKING_DIR_GENERATED_I);
	}

}
