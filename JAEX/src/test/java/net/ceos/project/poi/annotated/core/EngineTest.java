package net.ceos.project.poi.annotated.core;

import static org.testng.Assert.assertEquals;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.testng.annotations.Test;

import net.ceos.project.poi.annotated.bean.BasicObject;
import net.ceos.project.poi.annotated.bean.BasicObjectBuilder;
import net.ceos.project.poi.annotated.bean.PropagationHorizontalObject;
import net.ceos.project.poi.annotated.bean.PropagationHorizontalObjectBuilder;
import net.ceos.project.poi.annotated.bean.PropagationVerticalObject;
import net.ceos.project.poi.annotated.bean.PropagationVerticalObjectBuilder;

public class EngineTest {

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
	 * Test with propagation type is HORIZONTAL
	 * 
	 * @throws Exception
	 */
	@Test
	public void testPropagationTypeHorizontal() throws Exception {
		PropagationHorizontalObject pHO = PropagationHorizontalObjectBuilder.buildPropagationHorizontalObject();

		IEngine en = new Engine();
		CellDecorator anotherDate = new CellDecorator();
		anotherDate.setDecoratorName("anotherDate");
		anotherDate.setAlignment(CellStyle.ALIGN_CENTER);
		anotherDate.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		anotherDate.setForegroundColor(HSSFColor.LIGHT_GREEN.index);
		anotherDate.setFontItalic(true);
		anotherDate.setWrapText(true);

		ConfigCriteria configCriteria = new ConfigCriteria();
		configCriteria.addSpecificCellDecorator("anotherDate", anotherDate);

		en.marshalAndSave(configCriteria, pHO, TestUtils.WORKING_DIR_GENERATED_I);

		PropagationHorizontalObject charger = new PropagationHorizontalObject();

		en.unmarshalFromPath(charger, TestUtils.WORKING_DIR_GENERATED_II);
		PropagationHorizontalObjectBuilder.validatePropagationHorizontalObject(charger);
	}

	/**
	 * Test with propagation type is VERTICAL
	 */
	@Test
	public void testPropagationTypeVertical() throws Exception {
		PropagationVerticalObject pVO = PropagationVerticalObjectBuilder.buildPropagationVerticalObject();

		IEngine en = new Engine();
		en.marshalAndSave(pVO, TestUtils.WORKING_DIR_GENERATED_II);

		PropagationVerticalObject charger = new PropagationVerticalObject();

		en.unmarshalFromPath(charger, TestUtils.WORKING_DIR_GENERATED_I);
		PropagationVerticalObjectBuilder.validatePropagationVerticalObject(charger);
	}

	/**
	 * Test with different ROW & CELL
	 */
	@Test
	public void testRowCellSpecified() {
		// FIXME apply test case
		assertEquals(true, false);
	}

	/**
	 * Test with cascade type is CASCADE_LEVEL_ONE
	 */
	@Test
	public void testCascadeTypeLevelOne() {
		// FIXME apply test case
		assertEquals(true, false);
	}

	/**
	 * Test with cascade type is CASCADE_LEVEL_TWO
	 */
	@Test
	public void testCascadeTypeLevelTwo() {
		// FIXME apply test case
		assertEquals(true, false);
	}

	/**
	 * Test with cascade type is CASCADE_FULL
	 */
	@Test
	public void testCascadeTypeFull() {
		// FIXME apply test case
		assertEquals(true, false);
	}
}