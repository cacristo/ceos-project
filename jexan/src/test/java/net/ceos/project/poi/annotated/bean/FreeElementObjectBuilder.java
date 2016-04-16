package net.ceos.project.poi.annotated.bean;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;

public class FreeElementObjectBuilder {

	/**
	 * Create a FreeElementObject for tests.
	 * 
	 * @return the {@link FreeElementObject}
	 */
	public static FreeElementObject buildFreeElementObject() {
		return buildFreeElementObject(2);
	}

	/**
	 * Create a FreeElementObject for tests.
	 * 
	 * @return the {@link FreeElementObject}
	 */
	public static FreeElementObject buildFreeElementObject(int multiplier) {
		FreeElementObject toValidate = new FreeElementObject();

		toValidate.setFreeString("some string");
		toValidate.setFreeDouble(Double.valueOf("11.21") * multiplier);
		toValidate.setFreePrimitiveInt(12 * multiplier);
		toValidate.setFreeDate(new Date());
		toValidate.setFreeLong(Long.valueOf("1234567890") * multiplier);
		toValidate.setFreePrimitiveBoolean(Boolean.FALSE);
		// TODO add new fields below

		return toValidate;
	}

	/**
	 * Create a list of FreeElementObject for tests.
	 * 
	 * @return the {@link FreeElementObject}
	 */
	public static List<FreeElementObject> buildListOfFreeElementObject(int entryNumber) {

		List<FreeElementObject> returnList = new ArrayList<>();
		for (int i = 0; i < entryNumber; i++) {
			returnList.add(buildFreeElementObject(RandomUtils.nextInt(1, entryNumber)));
		}
		return returnList;
	}

	/**
	 * Validate the FreeElementObject based on the object build with the method
	 * 'buildFreeElementObject'
	 * 
	 * @param toValidate
	 *            the object to validate
	 */
	public static void validateFreeElementObject(FreeElementObject toValidate) {
		FreeElementObject base = buildFreeElementObject();

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
