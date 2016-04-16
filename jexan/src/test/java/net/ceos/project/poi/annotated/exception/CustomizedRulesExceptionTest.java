package net.ceos.project.poi.annotated.exception;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import net.ceos.project.poi.annotated.bean.FantasticFourFactory;
import net.ceos.project.poi.annotated.bean.XlsElementCustomizedRulesMultipleMethods;
import net.ceos.project.poi.annotated.bean.XlsElementCustomizedRulesNoSuchMethod;
import net.ceos.project.poi.annotated.bean.XlsElementCustomizedRulesNumeric;
import net.ceos.project.poi.annotated.bean.XlsElementCustomizedRulesString;
import net.ceos.project.poi.annotated.core.Engine;
import net.ceos.project.poi.annotated.core.IEngine;
import net.ceos.project.poi.annotated.core.TestUtils;

/**
 * Test the {@link CustomizedRulesException}
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public class CustomizedRulesExceptionTest {
	
	private boolean exceptionDetected = false;

	private void reset() {
		this.exceptionDetected = false;

	}

	@Before
	public void setUp() throws Exception {
		reset();
	}

	
	/**
	 * Test a simple customized rules defined if value under 450
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCustomizedRuleAtNumeric() throws Exception {
		XlsElementCustomizedRulesNumeric custom = new XlsElementCustomizedRulesNumeric();
		custom.setStringAttribute("Touch down to this team!");
		custom.setIntegerAttribute(300);
		custom.setDateAttribute(new Date());
		try {
			IEngine en = new Engine();
			en.marshalAndSave(custom, TestUtils.WORKING_DIR_GENERATED_I);
		} catch (Exception e) {
			this.exceptionDetected = e.getCause().toString().contains(CustomizedRulesException.class.getSimpleName());
		}
		assertEquals(true, this.exceptionDetected);
	}

	/**
	 * Test a simple customized rules defined if a string contains the word
	 * "down"
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCustomizedRuleAtString() throws Exception {
		XlsElementCustomizedRulesString custom = new XlsElementCustomizedRulesString();
		custom.setStringAttribute("Touch down to this team!");
		custom.setIntegerAttribute(300);
		custom.setDateAttribute(new Date());
		try {
			IEngine en = new Engine();
			en.marshalAndSave(custom, TestUtils.WORKING_DIR_GENERATED_I);
		} catch (Exception e) {
			this.exceptionDetected = e.getCause().toString().contains(CustomizedRulesException.class.getSimpleName());
		}
		assertEquals(true, this.exceptionDetected);
	}

	/**
	 * Test a multiples customized rules defined at the same object
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCustomizedRuleAtMultipleMethods1() throws Exception {
		XlsElementCustomizedRulesMultipleMethods custom = new XlsElementCustomizedRulesMultipleMethods();
		custom.setDateAttribute(new Date());
		custom.setStringAttribute("Touch down to this team!");
		custom.setIntegerAttribute1(300);
		custom.setIntegerAttribute2(300);
		custom.setDoublePrimitiveAttribute(10);
		try {
			IEngine en = new Engine();
			en.marshalAndSave(custom, TestUtils.WORKING_DIR_GENERATED_I);
		} catch (Exception e) {
			this.exceptionDetected = e.getCause().toString().contains(CustomizedRulesException.class.getSimpleName());
		}
		assertEquals(true, this.exceptionDetected);
	}

	/**
	 * Test a multiples customized rules defined at the same object
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCustomizedRuleAtMultipleMethods2() throws Exception {
		XlsElementCustomizedRulesMultipleMethods custom = new XlsElementCustomizedRulesMultipleMethods();
		custom.setDateAttribute(new Date());
		custom.setStringAttribute("Touch this team!");
		custom.setIntegerAttribute1(300);
		custom.setIntegerAttribute2(300);
		custom.setDoublePrimitiveAttribute(0);
		try {
			IEngine en = new Engine();
			en.marshalToSheet(custom);
		} catch (Exception e) {
			this.exceptionDetected = e.getCause().toString().contains(CustomizedRulesException.class.getSimpleName());
		}
		assertEquals(true, this.exceptionDetected);
	}

	/**
	 * Test a multiples customized rules defined at the same object
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCustomizedRuleAtMultipleMethods3() throws Exception {
		XlsElementCustomizedRulesMultipleMethods custom = new XlsElementCustomizedRulesMultipleMethods();
		custom.setDateAttribute(new Date());
		custom.setStringAttribute("Touch this team!");
		custom.setIntegerAttribute1(300);
		custom.setIntegerAttribute2(200);
		custom.setDoublePrimitiveAttribute(15);
		try {
			IEngine en = new Engine();
			en.marshalToWorkbook(custom);
		} catch (Exception e) {
			this.exceptionDetected = e.getCause().toString().contains(CustomizedRulesException.class.getSimpleName());
		}
		assertEquals(true, this.exceptionDetected);
	}

	/**
	 * Test a customized rules which does not exist a method
	 * 
	 * @throws Exception
	 */
	@Test(expected = CustomizedRulesException.class)
	public void testCustomizedRuleNoSuchMethod() throws Exception {
		XlsElementCustomizedRulesNoSuchMethod custom = new XlsElementCustomizedRulesNoSuchMethod();
		custom.setDateAttribute(new Date());
		custom.setStringAttribute("Touch this team!");
		custom.setIntegerAttribute(300);

		IEngine en = new Engine();
		en.marshalToWorkbook(custom);
	}

	/**
	 * Test a comment rules which does not exist a method
	 * 
	 * @throws Exception
	 */
	@Test(expected = CustomizedRulesException.class)
	public void testCommentRuleNoSuchMethodMrFantastic() throws Exception {
		IEngine en = new Engine();
		en.marshalToWorkbook(FantasticFourFactory.instanceMrFantastic());
	}

	/**
	 * Test a comment rules which does not exist a method
	 * 
	 * @throws Exception
	 */
	@Test(expected = CustomizedRulesException.class)
	public void testCommentRuleNoSuchMethodInvisibleWoman() throws Exception {
		IEngine en = new Engine();
		en.marshalToWorkbook(FantasticFourFactory.instanceInvisibleWoman());
	}

	/**
	 * Test a comment rules which does not exist a method
	 * 
	 * @throws Exception
	 */
	@Test(expected = CustomizedRulesException.class)
	public void testCommentRuleNoSuchMethodThing() throws Exception {
		IEngine en = new Engine();
		en.marshalToWorkbook(FantasticFourFactory.instanceThing());
	}

	/**
	 * Test a comment rules which does not exist a method
	 * 
	 * @throws Exception
	 */
	@Test(expected = CustomizedRulesException.class)
	public void testCommentRuleNoSuchMethodHumanTorch() throws Exception {
		IEngine en = new Engine();
		en.marshalToWorkbook(FantasticFourFactory.instanceHumanTorch());
	}

	/**
	 * Test a comment rules which does not exist a method
	 * 
	 * @throws Exception
	 */
	@Test(expected = CustomizedRulesException.class)
	public void testCommentRuleNoSuchMethodAntMan() throws Exception {
		IEngine en = new Engine();
		en.marshalToWorkbook(FantasticFourFactory.instanceAntMan());
	}

	/**
	 * Test a comment rules which does not exist a method
	 * 
	 * @throws Exception
	 */
	@Test(expected = CustomizedRulesException.class)
	public void testCommentRuleNoSuchMethodBlackPanther() throws Exception {
		IEngine en = new Engine();
		en.marshalToWorkbook(FantasticFourFactory.instanceBlackPanther());
	}

	/**
	 * Test a comment rules which does not exist a method
	 * 
	 * @throws Exception
	 */
	@Test(expected = CustomizedRulesException.class)
	public void testCommentRuleNoSuchMethodCrystal() throws Exception {
		IEngine en = new Engine();
		en.marshalToWorkbook(FantasticFourFactory.instanceCrystal());
	}

	/**
	 * Test a comment rules which does not exist a method
	 * 
	 * @throws Exception
	 */
	@Test(expected = CustomizedRulesException.class)
	public void testCommentRuleNoSuchMethodDrDoom() throws Exception {
		IEngine en = new Engine();
		en.marshalToWorkbook(FantasticFourFactory.instanceDrDoom());
	}

	/**
	 * Test a comment rules which does not exist a method
	 * 
	 * @throws Exception
	 */
	@Test(expected = CustomizedRulesException.class)
	public void testCommentRuleNoSuchMethodFlux() throws Exception {
		IEngine en = new Engine();
		en.marshalToWorkbook(FantasticFourFactory.instanceFlux());
	}
}
