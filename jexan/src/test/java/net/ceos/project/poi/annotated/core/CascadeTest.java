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

import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import net.ceos.project.poi.annotated.bean.cascade.CascadeObject;
import net.ceos.project.poi.annotated.bean.cascade.CascadeObjectBuilder;
import net.ceos.project.poi.annotated.definition.CascadeType;
import net.ceos.project.poi.annotated.exception.WorkbookException;

/**
 * Test the cascade level
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public class CascadeTest {

	/**
	 * Test cascade type CASCADE_BASE
	 * 
	 * @throws WorkbookException
	 */
	@Test
	public void checkCascadeTypeBase() throws WorkbookException {
		CascadeObject cascadeObj = CascadeObjectBuilder.buildCascadeObject();

		XConfigCriteria criteria = new XConfigCriteria();
		criteria.overrideCascadeLevel(CascadeType.CASCADE_BASE);

		IEngine en = new Engine();
		Workbook wb = en.marshalToWorkbook(criteria, cascadeObj);

		assertNotNull(wb);
		assertNotNull(wb.getSheet("Cascade analyser"));
		assertEquals(wb.getNumberOfSheets(), 1);
	}

	/**
	 * Test cascade type CASCADE_L1
	 * 
	 * @throws WorkbookException
	 */
	@Test
	public void checkCascadeTypeLevelOne() throws WorkbookException {
		CascadeObject cascadeObj = CascadeObjectBuilder.buildCascadeObject();

		XConfigCriteria criteria = new XConfigCriteria();
		criteria.overrideCascadeLevel(CascadeType.CASCADE_L1);

		IEngine en = new Engine();
		Workbook wb = en.marshalToWorkbook(criteria, cascadeObj);

		assertNotNull(wb);
		assertNotNull(wb.getSheet("Cascade analyser"));
		assertNotNull(wb.getSheet("Basic object"));
		assertNotNull(wb.getSheet("Decorators"));
		assertNotNull(wb.getSheet("Cascade L1"));
		assertEquals(wb.getNumberOfSheets(), 4);
	}

	/**
	 * Test cascade type CASCADE_L2
	 * 
	 * @throws WorkbookException
	 */
	@Test
	public void checkCascadeTypeLevelTwo() throws WorkbookException {
		CascadeObject cascadeObj = CascadeObjectBuilder.buildCascadeObject();

		XConfigCriteria criteria = new XConfigCriteria();
		criteria.overrideCascadeLevel(CascadeType.CASCADE_L2);

		IEngine en = new Engine();
		Workbook wb = en.marshalToWorkbook(criteria, cascadeObj);

		assertNotNull(wb);
		assertNotNull(wb.getSheet("Cascade analyser"));
		assertNotNull(wb.getSheet("Basic object"));
		assertNotNull(wb.getSheet("Decorators"));
		assertNotNull(wb.getSheet("Cascade L1"));
		assertNotNull(wb.getSheet("Cascade L2"));
		assertEquals(wb.getNumberOfSheets(), 5);
	}

	/**
	 * Test cascade type CASCADE_L3
	 * 
	 * @throws WorkbookException
	 */
	@Test
	public void checkCascadeTypeLevelThree() throws WorkbookException {
		CascadeObject cascadeObj = CascadeObjectBuilder.buildCascadeObject();

		XConfigCriteria criteria = new XConfigCriteria();
		criteria.overrideCascadeLevel(CascadeType.CASCADE_L3);

		IEngine en = new Engine();
		Workbook wb = en.marshalToWorkbook(criteria, cascadeObj);

		assertNotNull(wb);
		assertNotNull(wb.getSheet("Cascade analyser"));
		assertNotNull(wb.getSheet("Basic object"));
		assertNotNull(wb.getSheet("Decorators"));
		assertNotNull(wb.getSheet("Cascade L1"));
		assertNotNull(wb.getSheet("Cascade L2"));
		assertNotNull(wb.getSheet("Cascade L3"));
		assertEquals(wb.getNumberOfSheets(), 6);
	}

	/**
	 * Test cascade type CASCADE_FULL
	 * 
	 * @throws WorkbookException
	 */
	@Test
	public void checkCascadeTypeFull() throws WorkbookException {
		CascadeObject cascadeObj = CascadeObjectBuilder.buildCascadeObject();

		XConfigCriteria criteria = new XConfigCriteria();
		criteria.overrideCascadeLevel(CascadeType.CASCADE_FULL);

		IEngine en = new Engine();
		Workbook wb = en.marshalToWorkbook(criteria, cascadeObj);

		assertNotNull(wb);
		assertNotNull(wb.getSheet("Cascade analyser"));
		assertNotNull(wb.getSheet("Basic object"));
		assertNotNull(wb.getSheet("Decorators"));
		assertNotNull(wb.getSheet("Cascade L1"));
		assertNotNull(wb.getSheet("Cascade L2"));
		assertNotNull(wb.getSheet("Cascade L3"));
		assertEquals(wb.getNumberOfSheets(), 6);
	}
}
