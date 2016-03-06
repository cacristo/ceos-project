package net.ceos.project.poi.annotated.core;

import org.testng.annotations.Test;

import net.ceos.project.poi.annotated.annotation.XlsElement;
import net.ceos.project.poi.annotated.bean.ObjectFormula;
import net.ceos.project.poi.annotated.bean.ObjectFormulaBuilder;

/**
 * Test multiple formulas to apply at {@link XlsElement}
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public class FormulaTest {

	/**
	 * Test the marshal of one object applying formulas at the
	 * {@link XlsElement}
	 */
	@Test
	public void testMarshalObjectFormula() throws Exception {
		ObjectFormula of = ObjectFormulaBuilder.buildObjectFormula();

		IEngine en = new Engine();
		en.marshalAndSave(of, TestUtils.WORKING_DIR_GENERATED_II);
	}

	/**
	 * Test if the object who applied formulas at the {@link XlsElement} cause any damage at the moment
	 * of unmarshal.
	 */
	@Test
	public void testUnmarshalObjectFormula() throws Exception {
		ObjectFormula of = new ObjectFormula();

		IEngine en = new Engine();
		en.unmarshalFromPath(of, TestUtils.WORKING_DIR_GENERATED_II);

		ObjectFormulaBuilder.validateObjectFormula(of);
	}

}
