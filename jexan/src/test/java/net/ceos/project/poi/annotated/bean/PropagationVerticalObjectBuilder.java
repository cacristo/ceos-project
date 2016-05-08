package net.ceos.project.poi.annotated.bean;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;

public class PropagationVerticalObjectBuilder {

	/**
	 * Create a PropagationVerticalObject for tests.
	 * 
	 * @return the {@link PropagationVerticalObject}
	 */
	public static PropagationVerticalObject buildPropagationVerticalObject() {
		return buildPropagationVerticalObject(3);
	}

	/**
	 * Create a PropagationVerticalObject for tests.
	 * 
	 * @return the {@link PropagationVerticalObject}
	 */
	public static PropagationVerticalObject buildPropagationVerticalObject(int multiplier) {
		PropagationVerticalObject toValidate = new PropagationVerticalObject();

		toValidate.setIntegerPrimitiveAttribute(121 * multiplier);
		toValidate.setDoublePrimitiveAttribute(44.6 * multiplier);
		toValidate.setLongPrimitiveAttribute(987654321L * multiplier);
		toValidate.setBooleanPrimitiveAttribute(true);
		toValidate.setFloatPrimitiveAttribute(11.1125f * multiplier);
		// TODO add new fields below

		return toValidate;
	}

	/**
	 * Create a list of PropagationVerticalObject for tests.
	 * 
	 * @return the {@link PropagationVerticalObject}
	 */
	public static List<PropagationVerticalObject> buildListOfPropagationVerticalObject(int entryNumber) {

		List<PropagationVerticalObject> returnList = new ArrayList<PropagationVerticalObject>();
		for (int i = 0; i < entryNumber; i++) {
			returnList.add(buildPropagationVerticalObject(RandomUtils.nextInt(1, entryNumber)));
		}
		return returnList;
	}

	/**
	 * Validate the PropagationVerticalObject based on the object build with the method
	 * 'buildPropagationVerticalObject'
	 * 
	 * @param toValidate
	 *            the object to validate
	 */
	public static void validatePropagationVerticalObject(PropagationVerticalObject toValidate) {
		PropagationVerticalObject base = buildPropagationVerticalObject();

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		assertEquals(base.getIntegerPrimitiveAttribute(), toValidate.getIntegerPrimitiveAttribute());
		assertEquals(base.getDoublePrimitiveAttribute(), toValidate.getDoublePrimitiveAttribute(), 0.001);
		assertEquals(base.getLongPrimitiveAttribute(), toValidate.getLongPrimitiveAttribute());
		assertEquals(base.isBooleanPrimitiveAttribute(), toValidate.isBooleanPrimitiveAttribute());
		assertEquals(base.getFloatPrimitiveAttribute(), toValidate.getFloatPrimitiveAttribute(), 0.001);
		// TODO add new validation below
	}

}
