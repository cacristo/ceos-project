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
import java.util.Collection;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

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
import net.ceos.project.poi.annotated.definition.PropagationType;

/**
 * Test the {@link ElementException}
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public class ElementExceptionTest {

	@DataProvider
	public Object[][] collectionProvider() {
		/* collection null */
		List<Object> collectionNull = null;
		/* Collection empty */
		List<Object> collectionEmpty = new ArrayList<Object>();
		/* Collection with object null */
		List<Object> collection = new ArrayList<Object>();
		SimpleObject so = null;
		collection.add(so);

		return new Object[][] {
				/* Null Collection */
				{ collectionNull, "CollectionNull", ExtensionFileType.XLS },
				/* Collection empty */
				{ collectionEmpty, "CollectionEmpty", ExtensionFileType.XLS },
				/* Collection with object empty */
				{ collection, "CollectionWithObjectEmpty", ExtensionFileType.XLSX } };
	}

	@DataProvider
	public Object[][] overwriteCellProvider() {
		return new Object[][] {
				/* XlsElement with 2 attributes at the same position */
				{ AvengersFactory.instanceHankPym() },
				/* XlsFreeElement with 2 attributes at the same position */
				{ AvengersFactory.instanceLukeCage() } };
	}

	@DataProvider
	public Object[][] invalidPositionProvider() {
		return new Object[][] {
				/*
				 * PropagationType HORIZONTAL and element entry with a invalid
				 * position
				 */
				{ MarvelBadGuysFactory.instanceLoki() },
				/*
				 * PropagationType VERTICAL and element entry with a invalid
				 * position
				 */
				{ MarvelBadGuysFactory.instanceRedSkull() },
				/* Free element entry with a invalid cell position */
				{ MarvelBadGuysFactory.instanceMystique() },
				/* Free element entry with a invalid row position */
				{ MarvelBadGuysFactory.instanceThanos() } };
	}

	@DataProvider
	public Object[][] xlsConflictFormulaProvider() {
		return new Object[][] {
				/*
				 * Conflict caused by the PropagationType HORIZONTAL and formula
				 * orientation
				 */
				{ AvengersFactory.instanceCaptainAmerica() },
				/*
				 * Conflict caused by the PropagationType VERTICAL and formula
				 * orientation
				 */
				{ AvengersFactory.instanceCaptainMarvel() } };
	}

	@DataProvider
	public Object[][] xlsConflictAnnotationProvider() {
		return new Object[][] {
				/*
				 * (PropagationType HORIZONTAL) Conflict annotation type:
				 * impossible to have XlsElement & XlsFreeElement at the same
				 * attribute
				 */
				{ AvengersFactory.instanceBlackPanther() },
				/*
				 * (PropagationType VERTICAL) Conflict annotation type:
				 * impossible to have XlsElement & XlsFreeElement at the same
				 * attribute
				 */
				{ AvengersFactory.instanceBlackWidow() } };
	}

	@DataProvider
	public Object[][] fantasticFourFactoryProvider() {
		return new Object[][] {
				/* Comment rules @String which does not exist a method */
				{ FantasticFourDecoratorFactory.instanceMrFantastic() },
				/* Comment rules @Integer which does not exist a method */
				{ FantasticFourDecoratorFactory.instanceInvisibleWoman() },
				/* Comment rules @Double which does not exist a method */
				{ FantasticFourDecoratorFactory.instanceThing() },
				/* Comment rules @Date which does not exist a method */
				{ FantasticFourDecoratorFactory.instanceHumanTorch() },
				/* Comment rules @BigDecimal which does not exist a method */
				{ FantasticFourDecoratorFactory.instanceAntMan() },
				/* Comment rules @Float which does not exist a method */
				{ FantasticFourDecoratorFactory.instanceBlackPanther() },
				/* Comment rules @Boolean which does not exist a method */
				{ FantasticFourDecoratorFactory.instanceCrystal() },
				/* Comment rules @Long which does not exist a method */
				{ FantasticFourDecoratorFactory.instanceDrDoom() },
				/* Comment rules @Object which does not exist a method */
				{ FantasticFourDecoratorFactory.instanceFlux() } };
	}

	/**
	 * Test an null object
	 * 
	 * @throws Exception
	 */
	@Test(expectedExceptions = ElementException.class, expectedExceptionsMessageRegExp = "The entry object is null. Make sure you are sending a correct object.")
	public void marsharObjectNullElementException() throws Exception {
		MultiTypeObject objNull = null;

		IEngine en = new Engine();
		en.marshalToByte(objNull);
	}

	/**
	 * Test an empty object
	 */
	@Test(expectedExceptions = ElementException.class, expectedExceptionsMessageRegExp = "The entry object is null. Make sure you are sending a correct object.")
	public void unmarshalObjectNullElementException() throws Exception {
		MultiTypeObject objEmpty = new MultiTypeObject();

		IEngine en = new Engine();
		byte[] generatedBytes = en.marshalToByte(objEmpty);

		MultiTypeObject charger = null;
		en.unmarshalFromByte(charger, generatedBytes);
	}

	/**
	 * Test an empty list & list with null object
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@Test(dataProvider = "collectionProvider", expectedExceptions = ElementException.class, expectedExceptionsMessageRegExp = "The entry object is null. Make sure you are sending a correct object.")
	public void marshalListElementException(Collection collection, String fileName, ExtensionFileType extension)
			throws Exception {
		XConfigCriteria configCriteria = new XConfigCriteria();
		configCriteria.setFileName(fileName);
		configCriteria.overrideExtensionType(extension);

		IEngine en = new Engine();
		en.marshalAsCollectionAndSave(configCriteria, collection, TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test a {@link XlsFreeElement} trying use a complex object
	 */
	@Test(expectedExceptions = ElementException.class, expectedExceptionsMessageRegExp = "Complex objects are not allowed for this type! Review your configuration.")
	public void marshalXlsFreeElementInvalidObjectElementException() throws Exception {
		Hawkeye complexObject = AvengersFactory.instanceHawkeye();

		IEngine en = new Engine();
		en.marshalToSheet(complexObject);
	}

	/**
	 * Test a {@link XlsElement} & {@link XlsFreeElement} trying write at one
	 * cell already used
	 */
	@Test(dataProvider = "overwriteCellProvider", expectedExceptions = ElementException.class, expectedExceptionsMessageRegExp = "The element entry is trying to be set at one position already used. Review your configuration.")
	public void marshalXlsElementOverwriteCellElementException(Object object) throws Exception {
		IEngine en = new Engine();
		en.marshalToWorkbook(object);
	}

	/**
	 * Test marshal and save with a invalid position
	 * <ul>
	 * <li>Test a {@link XlsElement} trying write at invalid position
	 * <li>Test a {@link XlsFreeElement} trying write at invalid cell position
	 * <li>Test a {@link XlsFreeElement} trying write at invalid row position
	 * </ul>
	 */
	@Test(dataProvider = "invalidPositionProvider", expectedExceptions = ElementException.class, expectedExceptionsMessageRegExp = "The element entry has a invalid position, make sure you are setting a positive value and start at least by 1. Review your configuration.")
	public void invalidPositionElementMarshalException(Object object) throws Exception {
		IEngine en = new Engine();
		en.marshalAndSave(object, TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test unmarshal and save with a invalid position
	 * <ul>
	 * <li>Test a {@link XlsElement} trying write at invalid position
	 * <li>Test a {@link XlsFreeElement} trying write at invalid cell position
	 * <li>Test a {@link XlsFreeElement} trying write at invalid row position
	 * </ul>
	 */
	@Test(dataProvider = "invalidPositionProvider", expectedExceptions = ElementException.class, expectedExceptionsMessageRegExp = "The element entry has a invalid position, make sure you are setting a positive value and start at least by 1. Review your configuration.")
	public void invalidPositionElementUnmarshalException(Object object) throws Exception {
		IEngine en = new Engine();
		en.unmarshalFromPath(object, TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test a configuration conflict caused by the {@link PropagationType} and
	 * formula orientation
	 */
	@Test(dataProvider = "xlsConflictFormulaProvider", expectedExceptions = ElementException.class, expectedExceptionsMessageRegExp = "Conflict caused by the PropagationType and formula orientation. Review your configuration.")
	public void configurationConflictByPropagationFormulaElementException(Object incompatibleConfig) throws Exception {
		IEngine en = new Engine();
		en.marshalAndSave(incompatibleConfig, TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test a non-conflict annotation type: impossible to have
	 * {@link XlsElement} & {@link XlsFreeElement} at the same attribute
	 */
	@Test(dataProvider = "xlsConflictAnnotationProvider", expectedExceptions = ElementException.class, expectedExceptionsMessageRegExp = "Conflict with annotation of type @XlsElement and @XlsFreeElement. Only one annotation type is valid per attribute.")
	public void conflictMultipleAnnotationAtAttributeElementException(Object incompatibleConfig) throws Exception {
		IEngine en = new Engine();
		en.marshalAndSave(incompatibleConfig, TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test a non-conflict annotation type: impossible to have
	 * {@link XlsElement} & {@link XlsFreeElement} at the same attribute
	 */
	@Test(dataProvider = "fantasticFourFactoryProvider", expectedExceptions = ElementException.class, expectedExceptionsMessageRegExp = "There is one XlsDecorator missing. Review your configuration.")
	public void configurationMissingDecoratorElementException(Object incompatibleConfig) throws Exception {
		IEngine en = new Engine();
		en.marshalAndSave(incompatibleConfig, TestUtils.WORKING_DIR_GENERATED_I);
	}

}
