package net.ceos.project.poi.annotated.bean;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;

public class ObjectNullBuilder {

	/**
	 * Create a ObjectNull for tests.
	 * 
	 * @return the {@link ObjectNull}
	 */
	public static ObjectNull buildObjectNull() {
		return buildObjectNull(2);
	}

	/**
	 * Create a ObjectNull for tests.
	 * 
	 * @return the {@link ObjectNull}
	 */
	public static ObjectNull buildObjectNull(int multiplier) {
		ObjectNull obj = new ObjectNull();

		/* this object has no values at the attributes */

		return obj;
	}

	/**
	 * Create a list of ObjectNull for tests.
	 * 
	 * @return the {@link ObjectNull}
	 */
	public static List<ObjectNull> buildListOfObjectNull(int entryNumber) {

		List<ObjectNull> returnList = new ArrayList<>();
		for (int i = 0; i < entryNumber; i++) {
			returnList.add(buildObjectNull(RandomUtils.nextInt(1, entryNumber)));
		}
		return returnList;
	}

	/**
	 * Validate the ObjectNull based on the object build with the method
	 * 'buildObjectNull'
	 * 
	 * @param toValidate
	 *            the object to validate
	 */
	public static void validateObjectNull(ObjectNull toValidate) {
		ObjectNull base = buildObjectNull();

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());

		Calendar calendarUnmarshal = Calendar.getInstance();
		calendarUnmarshal.setTime(toValidate.getDateAttribute());
		assertEquals(calendar.get(Calendar.YEAR), calendarUnmarshal.get(Calendar.YEAR));
		assertEquals(calendar.get(Calendar.MONTH), calendarUnmarshal.get(Calendar.MONTH));
		assertEquals(calendar.get(Calendar.DAY_OF_MONTH), calendarUnmarshal.get(Calendar.DAY_OF_MONTH));
		assertEquals(base.getStringAttribute(), toValidate.getStringAttribute());
		assertEquals(base.getIntegerAttribute(), toValidate.getIntegerAttribute());
		assertEquals(base.getDoubleAttribute(), toValidate.getDoubleAttribute());
		assertEquals(base.getLongAttribute(), toValidate.getLongAttribute());
		assertEquals(base.getBooleanAttribute(), toValidate.getBooleanAttribute());
		assertEquals(base.getJob().getJobCode(), toValidate.getJob().getJobCode());
		assertEquals(base.getJob().getJobFamily(), toValidate.getJob().getJobFamily());
		assertEquals(base.getJob().getJobName(), toValidate.getJob().getJobName());
		assertEquals(base.getIntegerPrimitiveAttribute(), toValidate.getIntegerPrimitiveAttribute());
		assertEquals(base.getDoublePrimitiveAttribute(), toValidate.getDoublePrimitiveAttribute(), 0.001);
		assertEquals(base.getLongPrimitiveAttribute(), toValidate.getLongPrimitiveAttribute());
		assertEquals(base.isBooleanPrimitiveAttribute(), toValidate.isBooleanPrimitiveAttribute());
		assertEquals(base.getAddressInfo().getAddress(), toValidate.getAddressInfo().getAddress());
		assertEquals(base.getAddressInfo().getNumber(), toValidate.getAddressInfo().getNumber());
		assertEquals(base.getAddressInfo().getCity(), toValidate.getAddressInfo().getCity());
		assertEquals(base.getAddressInfo().getCityCode(), toValidate.getAddressInfo().getCityCode());
		assertEquals(base.getAddressInfo().getCountry(), toValidate.getAddressInfo().getCountry());
		assertEquals(base.getFloatAttribute(), toValidate.getFloatAttribute());
		assertEquals(base.getFloatPrimitiveAttribute(), toValidate.getFloatPrimitiveAttribute(), 0.001);
		assertEquals(base.getUnitFamily(), toValidate.getUnitFamily());
		assertEquals(base.getBigDecimalAttribute(), toValidate.getBigDecimalAttribute());
		assertEquals(base.getShortAttribute(), toValidate.getShortAttribute());
		assertEquals(base.getShortPrimitiveAttribute(), toValidate.getShortPrimitiveAttribute());
		// TODO add new validation below
	}

}
