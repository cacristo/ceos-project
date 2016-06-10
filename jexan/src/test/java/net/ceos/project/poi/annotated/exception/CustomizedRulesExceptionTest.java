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

import java.util.Date;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import net.ceos.project.poi.annotated.bean.factory.FantasticFourFactory;
import net.ceos.project.poi.annotated.bean.factory.GuardiansOfTheGalaxyFactory;
import net.ceos.project.poi.annotated.bean.factory.GuardiansOfTheGalaxyFactory.Drax;
import net.ceos.project.poi.annotated.bean.factory.GuardiansOfTheGalaxyFactory.Gamora;
import net.ceos.project.poi.annotated.bean.factory.GuardiansOfTheGalaxyFactory.Groot;
import net.ceos.project.poi.annotated.bean.factory.GuardiansOfTheGalaxyFactory.RocketRaccoon;
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

	@DataProvider
	public Object[][] fantasticFourFactoryProvider() {
		return new Object[][] { { FantasticFourFactory.instanceMrFantastic() },
				{ FantasticFourFactory.instanceInvisibleWoman() }, { FantasticFourFactory.instanceThing() },
				{ FantasticFourFactory.instanceHumanTorch() }, { FantasticFourFactory.instanceAntMan() },
				{ FantasticFourFactory.instanceBlackPanther() }, { FantasticFourFactory.instanceCrystal() },
				{ FantasticFourFactory.instanceDrDoom() }, { FantasticFourFactory.instanceFlux() } };
	}

	/**
	 * Test a customized rules which does not exist a method
	 * 
	 * @throws Exception
	 */
	@Test(expectedExceptions = CustomizedRulesException.class, expectedExceptionsMessageRegExp = "The customized method entry does not exist. Review your configuration.")
	public void customizedRuleNoSuchMethod() throws Exception {
		Groot custom = GuardiansOfTheGalaxyFactory.instanceGroot();
		custom.setDateAttribute(new Date());
		custom.setStringAttribute("Touch this team!");
		custom.setIntegerAttribute(300);

		IEngine en = new Engine();
		en.marshalToWorkbook(custom);
	}

	/**
	 * Test a simple customized rules defined if value under 450
	 * 
	 * @throws Exception
	 */
	@Test(expectedExceptions = CustomizedRulesException.class, expectedExceptionsMessageRegExp = "The customized method entry does not exist. Review your configuration.")
	public void customizedRuleAtNumeric() throws Exception {
		Gamora custom = GuardiansOfTheGalaxyFactory.instanceGamora();
		custom.setStringAttribute("Touch down to this team!");
		custom.setIntegerAttribute(300);
		custom.setDateAttribute(new Date());

		IEngine en = new Engine();
		en.marshalAndSave(custom, TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test a simple customized rules defined if a string contains the word
	 * "down"
	 * 
	 * @throws Exception
	 */
	@Test(expectedExceptions = CustomizedRulesException.class, expectedExceptionsMessageRegExp = "The customized method entry does not exist. Review your configuration.")
	public void customizedRuleAtString() throws Exception {
		RocketRaccoon custom = GuardiansOfTheGalaxyFactory.instanceRocketRaccoon();
		custom.setStringAttribute("Touch down to this team!");
		custom.setIntegerAttribute(300);
		custom.setDateAttribute(new Date());

		IEngine en = new Engine();
		en.marshalAndSave(custom, TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test a multiples customized rules defined at the same object
	 * 
	 * @throws Exception
	 */
	@Test(expectedExceptions = CustomizedRulesException.class, expectedExceptionsMessageRegExp = "The customized method entry does not exist. Review your configuration.")
	public void customizedRuleMyRuleMethod() throws Exception {
		Drax custom = GuardiansOfTheGalaxyFactory.instanceDrax();
		custom.setDateAttribute(new Date());
		custom.setStringAttribute("Touch down to this team!");
		custom.setIntegerAttribute1(300);
		custom.setIntegerAttribute2(300);
		custom.setDoublePrimitiveAttribute(10);

		IEngine en = new Engine();
		en.marshalAndSave(custom, TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test a multiples customized rules defined at the same object
	 * 
	 * @throws Exception
	 */
	@Test(expectedExceptions = CustomizedRulesException.class, expectedExceptionsMessageRegExp = "The customized method entry does not exist. Review your configuration.")
	public void customizedRuleAnotherRule() throws Exception {
		Drax custom = GuardiansOfTheGalaxyFactory.instanceDrax();
		custom.setDateAttribute(new Date());
		custom.setStringAttribute("Touch this team!");
		custom.setIntegerAttribute1(300);
		custom.setIntegerAttribute2(300);
		custom.setDoublePrimitiveAttribute(0);

		IEngine en = new Engine();
		en.marshalToSheet(custom);
	}

	/**
	 * Test a multiples customized rules defined at the same object
	 * 
	 * @throws Exception
	 */
	@Test(expectedExceptions = CustomizedRulesException.class, expectedExceptionsMessageRegExp = "The customized method entry does not exist. Review your configuration.")
	public void customizedRuleSampleRule() throws Exception {
		Drax custom = GuardiansOfTheGalaxyFactory.instanceDrax();
		custom.setDateAttribute(new Date());
		custom.setStringAttribute("Touch this team!");
		custom.setIntegerAttribute1(300);
		custom.setIntegerAttribute2(200);
		custom.setDoublePrimitiveAttribute(15);

		IEngine en = new Engine();
		en.marshalToWorkbook(custom);
	}

	/**
	 * Test a comment rules which does not exist a method
	 * 
	 * @throws Exception
	 */
	@Test(dataProvider = "fantasticFourFactoryProvider", expectedExceptions = CustomizedRulesException.class, expectedExceptionsMessageRegExp = "The method entry at commentRules does not exist or the return type is incorrect. Review your configuration.")
	public void commentRuleNoSuchMethod(Object fantasticFour) throws Exception {
		IEngine en = new Engine();
		en.marshalToWorkbook(fantasticFour);
	}
}
