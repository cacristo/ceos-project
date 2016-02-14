package org.jaexcel.framework.JAEX.bean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;

import junit.framework.TestCase;

public class FreeElementAdvancedObjectBuilder extends TestCase {

	/**
	 * Create a FreeElementAdvancedObject for tests.
	 * 
	 * @return the {@link FreeElementAdvancedObject}
	 */
	public static FreeElementAdvancedObject buildFreeElementAdvancedObject() {
		return buildFreeElementAdvancedObject(2);
	}

	/**
	 * Create a FreeElementAdvancedObject for tests.
	 * 
	 * @return the {@link FreeElementAdvancedObject}
	 */
	public static FreeElementAdvancedObject buildFreeElementAdvancedObject(int multiplier) {
		FreeElementAdvancedObject toValidate = new FreeElementAdvancedObject();

		toValidate.setFreeString("The string attribute");
		toValidate.setFreeDouble(Double.valueOf("1169.21") * multiplier);
		toValidate.setFreePrimitiveInt(333 * multiplier);
		toValidate.setFreeDate(new Date());
		toValidate.setFreeLong(Long.valueOf("1511243017") * multiplier);
		toValidate.setFreePrimitiveBoolean(Boolean.FALSE);
		// TODO add new fields below

		return toValidate;
	}

	/**
	 * Create a list of FreeElementAdvancedObject for tests.
	 * 
	 * @return the {@link FreeElementAdvancedObject}
	 */
	public static List<FreeElementAdvancedObject> buildListOfFreeElementAdvancedObject(int entryNumber) {

		List<FreeElementAdvancedObject> returnList = new ArrayList<>();
		for (int i = 0; i < entryNumber; i++) {
			returnList.add(buildFreeElementAdvancedObject(RandomUtils.nextInt(1, entryNumber)));
		}
		return returnList;
	}

	/**
	 * Validate the FreeElementAdvancedObject based on the object build with the method
	 * 'buildFreeElementAdvancedObject'
	 * 
	 * @param toValidate
	 *            the object to validate
	 */
	public static void validateFreeElementAdvancedObject(FreeElementAdvancedObject toValidate) {
		FreeElementAdvancedObject base = buildFreeElementAdvancedObject();

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());

		Calendar calendarUnmarshal = Calendar.getInstance();
		calendarUnmarshal.setTime(toValidate.getFreeDate());
		assertEquals(calendar.get(Calendar.YEAR), calendarUnmarshal.get(Calendar.YEAR));
		assertEquals(calendar.get(Calendar.MONTH), calendarUnmarshal.get(Calendar.MONTH));
		assertEquals(calendar.get(Calendar.DAY_OF_MONTH), calendarUnmarshal.get(Calendar.DAY_OF_MONTH));
		assertEquals(base.getFreeString(), toValidate.getFreeString());
		assertEquals(base.getFreeDouble(), toValidate.getFreeDouble());
		assertEquals(base.getFreePrimitiveInt(), toValidate.getFreePrimitiveInt());
		assertEquals(base.getFreeLong(), toValidate.getFreeLong());
		assertEquals(base.isFreePrimitiveBoolean(), toValidate.isFreePrimitiveBoolean());
		// TODO add new validation below
	}

}
