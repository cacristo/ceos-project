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
package net.ceos.project.poi.annotated.bean;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;

/**
 * Builder to:
 * <ul>
 * <li>generate
 * <li>inject
 * <li>validate
 * </ul>
 * data at the object {@link ObjectFormula}
 * <p>
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public class ObjectFormulaBuilder {

	/**
	 * Create a ObjectFormula for tests.
	 * 
	 * @return the {@link ObjectFormula}
	 */
	public static ObjectFormula buildObjectFormula() {
		return buildObjectFormula(3);
	}

	/**
	 * Create a ObjectFormula for tests.
	 * 
	 * @return the {@link ObjectFormula}
	 */
	public static ObjectFormula buildObjectFormula(int multiplier) {
		ObjectFormula toValidate = new ObjectFormula();

		toValidate.setStore(200 * multiplier);
		toValidate.setWebStore(455 * multiplier);

		toValidate.setValueLocal(1623.99 * multiplier);
		toValidate.setValueRegion(3199.99 * multiplier);
		toValidate.setValueCountry(8421.80 * multiplier);

		toValidate.setSomeString(null);
		// TODO add new fields below

		return toValidate;
	}

	/**
	 * Create a list of ObjectFormula for tests.
	 * 
	 * @return the {@link ObjectFormula}
	 */
	public static List<ObjectFormula> buildListOfObjectFormula(int entryNumber) {

		List<ObjectFormula> returnList = new ArrayList<>();
		for (int i = 0; i < entryNumber; i++) {
			returnList.add(buildObjectFormula(RandomUtils.nextInt(1, entryNumber)));
		}
		return returnList;
	}

	/**
	 * Validate the ObjectFormula based on the object build with the method
	 * 'buildObjectFormula'
	 * 
	 * @param toValidate
	 *            the object to validate
	 */
	public static void validateObjectFormula(ObjectFormula toValidate) {
		ObjectFormula base = buildObjectFormula();

		assertEquals(base.getStore(), toValidate.getStore());
		assertEquals(base.getWebStore(), toValidate.getWebStore());
		assertEquals(base.getValueLocal(), toValidate.getValueLocal());
		assertEquals(base.getValueRegion(), toValidate.getValueRegion());
		assertEquals(base.getValueCountry(), toValidate.getValueCountry());

		// prepare simulation data
		simulateFormulaResults(base);

		assertEquals(base.getTotalValue(), toValidate.getTotalValue());
		assertEquals(base.getTotalValueTax(), toValidate.getTotalValueTax());
		assertEquals(base.getAvgValue(), toValidate.getAvgValue());
		assertEquals(base.getSomeString(), toValidate.getSomeString());

		// TODO add new validation below
	}

	/**
	 * Prepare simulation data
	 * 
	 * @param base the object to generate the simulation values
	 */
	private static void simulateFormulaResults(ObjectFormula base) {
		base.setTotalValue(base.getValueLocal() + base.getValueRegion() + base.getValueCountry());
		base.setTotalValueTax(base.getTotalValue() * 1.21);
		base.setAvgValue((base.getValueLocal() + base.getValueRegion() + base.getValueCountry()) / 3);
	}

}
