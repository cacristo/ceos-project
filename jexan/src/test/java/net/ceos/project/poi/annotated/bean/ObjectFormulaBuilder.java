package net.ceos.project.poi.annotated.bean;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;

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

		List<ObjectFormula> returnList = new ArrayList<ObjectFormula>();
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
		assertEquals(base.getValueLocal(), toValidate.getValueLocal(), 0.001);
		assertEquals(base.getValueRegion(), toValidate.getValueRegion(), 0.001);
		assertEquals(base.getValueCountry(), toValidate.getValueCountry(), 0.001);
		// FIXME review how get formula result
		// assertEquals(base.getSomeString(), toValidate.getSomeString());
		assertEquals("0.0", toValidate.getSomeString());
		// TODO add new validation below
	}

}
