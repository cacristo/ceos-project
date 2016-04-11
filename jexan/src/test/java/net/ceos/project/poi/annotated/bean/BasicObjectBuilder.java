package net.ceos.project.poi.annotated.bean;

import static org.testng.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;

public class BasicObjectBuilder {

	/**
	 * Create a BasicObject for tests.
	 * 
	 * @return the {@link BasicObject}
	 */
	public static BasicObject buildBasicObject() {
		return buildBasicObject(6);
	}

	/**
	 * Create a BasicObject for tests.
	 * 
	 * @return the {@link BasicObject}
	 */
	public static BasicObject buildBasicObject(int multiplier) {
		BasicObject toValidate = new BasicObject();

		toValidate.setDateAttribute(new Date());
		toValidate.setStringAttribute("some string");
		toValidate.setDoubleAttribute(Double.valueOf("25.3") * multiplier);
		toValidate.setLongAttribute(Long.valueOf("1234567890") * multiplier);
		toValidate.setBooleanPrimitiveAttribute(true);
		toValidate.setFloatPrimitiveAttribute(11.1125f * multiplier);
		toValidate.setUnitFamily(UnitFamily.COMPONENTS);
		toValidate.setBigDecimalAttribute(BigDecimal.valueOf(24.777).multiply(BigDecimal.valueOf(multiplier)));
		// TODO add new fields below

		return toValidate;
	}

	/**
	 * Create a list of BasicObject for tests.
	 * 
	 * @return the {@link BasicObject}
	 */
	public static List<BasicObject> buildListOfBasicObject(int entryNumber) {

		List<BasicObject> returnList = new ArrayList<>();
		for (int i = 0; i < entryNumber; i++) {
			returnList.add(buildBasicObject(RandomUtils.nextInt(1, entryNumber)));
		}
		return returnList;
	}

	/**
	 * Validate the BasicObject based on the object build with the method
	 * 'buildBasicObject'
	 * 
	 * @param toValidate
	 *            the object to validate
	 */
	public static void validateBasicObject(BasicObject toValidate) {
		BasicObject base = buildBasicObject();

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());

		Calendar calendarUnmarshal = Calendar.getInstance();
		calendarUnmarshal.setTime(toValidate.getDateAttribute());
		assertEquals(calendar.get(Calendar.YEAR), calendarUnmarshal.get(Calendar.YEAR));
		assertEquals(calendar.get(Calendar.MONTH), calendarUnmarshal.get(Calendar.MONTH));
		assertEquals(calendar.get(Calendar.DAY_OF_MONTH), calendarUnmarshal.get(Calendar.DAY_OF_MONTH));
		assertEquals(base.getStringAttribute(), toValidate.getStringAttribute());
		assertEquals(base.getDoubleAttribute(), toValidate.getDoubleAttribute());
		assertEquals(base.getLongAttribute(), toValidate.getLongAttribute());
		assertEquals(base.isBooleanPrimitiveAttribute(), toValidate.isBooleanPrimitiveAttribute());
		assertEquals(base.getFloatPrimitiveAttribute(), toValidate.getFloatPrimitiveAttribute());
		assertEquals(base.getUnitFamily(), toValidate.getUnitFamily());
		assertEquals(base.getBigDecimalAttribute(), toValidate.getBigDecimalAttribute());
		// TODO add new validation below
	}

}
