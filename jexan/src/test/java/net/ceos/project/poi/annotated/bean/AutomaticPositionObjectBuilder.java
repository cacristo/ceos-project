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

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;

/**
 * Builder to:
 * <ul>
 * <li>generate
 * <li>inject
 * <li>validate
 * </ul>
 * data at the object {@link PerformanceObject}
 * <p>
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public class AutomaticPositionObjectBuilder {

	/**
	 * Create a PerformanceObject for tests.
	 * 
	 * @return the {@link PerformanceObject}
	 */
	public static AutomaticPositionObject buildAutomaticPositionObject() {
		return buildAutomaticPositionObject(2);
	}

	/**
	 * Create a PerformanceObject for tests.
	 * 
	 * @return the {@link PerformanceObject}
	 */
	public static AutomaticPositionObject buildAutomaticPositionObject(int multiplier) {
		AutomaticPositionObject toValidate = new AutomaticPositionObject();

		toValidate.setDateAttribute1(new Date());
		toValidate.setDateAttribute2(new Date());
		toValidate.setDateAttribute3(new Date());

		toValidate.setStringAttribute1("some string");
		toValidate.setStringAttribute2("some string");
		toValidate.setStringAttribute3("some string");
		toValidate.setStringAttribute4("some string");
		toValidate.setStringAttribute5("some string");
		toValidate.setStringAttribute6("some string");
		toValidate.setStringAttribute7("some string");
		toValidate.setStringAttribute8("some string");
		toValidate.setStringAttribute9("some string");
		toValidate.setStringAttribute10("some string");
		toValidate.setStringAttribute11("some string");
		toValidate.setStringAttribute12("some string");

		toValidate.setIntegerAttribute1(6 * multiplier);
		toValidate.setIntegerAttribute2(11 * multiplier);
		toValidate.setIntegerAttribute3(33 * multiplier);
		toValidate.setIntegerAttribute4(46 * multiplier);
		toValidate.setIntegerAttribute5(2 * multiplier);
		toValidate.setIntegerAttribute6(7 * multiplier);
		toValidate.setIntegerAttribute7(9 * multiplier);

		toValidate.setDoubleAttribute1(Double.valueOf("11.3") * multiplier);
		toValidate.setDoubleAttribute2(Double.valueOf("237.131") * multiplier);
		toValidate.setDoubleAttribute3(Double.valueOf("25.777") * multiplier);

		toValidate.setLongAttribute(Long.valueOf("1234567890") * multiplier);

		toValidate.setBooleanAttribute1(Boolean.TRUE);
		toValidate.setBooleanAttribute2(Boolean.TRUE);
		toValidate.setBooleanAttribute3(Boolean.FALSE);
		toValidate.setBooleanAttribute4(Boolean.TRUE);
		/* create sub object Job */
		Job job = new Job();
		job.setJobCode(0005);
		job.setJobFamily("Family Job Name");
		job.setJobName("Job Name");
		toValidate.setJob(job);
		toValidate.setIntegerPrimitiveAttribute1(2 * multiplier);
		toValidate.setIntegerPrimitiveAttribute2(3 * multiplier);
		toValidate.setIntegerPrimitiveAttribute3(11 * multiplier);
		toValidate.setIntegerPrimitiveAttribute4(5 * multiplier);
		toValidate.setIntegerPrimitiveAttribute5(12 * multiplier);

		toValidate.setDoublePrimitiveAttribute(44.6 * multiplier);

		toValidate.setLongPrimitiveAttribute(987654321L * multiplier);

		toValidate.setBooleanPrimitiveAttribute(true);
		/* create sub object AddressInfo */
		AddressInfo ai = new AddressInfo();
		ai.setAddress("this is the street");
		ai.setNumber(99);
		ai.setCity("this is the city");
		ai.setCityCode(70065);
		ai.setCountry("This is a Country");
		toValidate.setAddressInfo(ai);
		toValidate.setFloatAttribute(14.765f * multiplier);
		toValidate.setFloatPrimitiveAttribute(11.1125f * multiplier);
		toValidate.setUnitFamily(UnitFamily.COMPONENTS);
		toValidate.setBigDecimalAttribute(BigDecimal.valueOf(24.777).multiply(BigDecimal.valueOf(multiplier)));
		// TODO add new fields below

		return toValidate;
	}

	/**
	 * Create a list of AutomaticPositionObject for tests.
	 * 
	 * @return the {@link AutomaticPositionObject}
	 */
	public static List<AutomaticPositionObject> buildListOfAutomaticPositionObject(int entryNumber) {

		List<AutomaticPositionObject> returnList = new ArrayList<>();
		for (int i = 0; i < entryNumber; i++) {
			returnList.add(buildAutomaticPositionObject(RandomUtils.nextInt(1, entryNumber)));
		}
		return returnList;
	}

	/**
	 * Validate the AutomaticPositionObject passed as paratemers
	 * 
	 * @param base
	 *            the object base
	 * @param toValidate
	 *            the object to validate
	 */
	public static void validateAutomaticPositionObject(AutomaticPositionObject base, AutomaticPositionObject toValidate,
			boolean validateChilds) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());

		Calendar calendarUnmarshal1 = Calendar.getInstance();
		calendarUnmarshal1.setTime(toValidate.getDateAttribute1());
		assertEquals(calendar.get(Calendar.YEAR), calendarUnmarshal1.get(Calendar.YEAR));
		assertEquals(calendar.get(Calendar.MONTH), calendarUnmarshal1.get(Calendar.MONTH));
		assertEquals(calendar.get(Calendar.DAY_OF_MONTH), calendarUnmarshal1.get(Calendar.DAY_OF_MONTH));
		Calendar calendarUnmarshal2 = Calendar.getInstance();
		calendarUnmarshal2.setTime(toValidate.getDateAttribute2());
		assertEquals(calendar.get(Calendar.YEAR), calendarUnmarshal2.get(Calendar.YEAR));
		assertEquals(calendar.get(Calendar.MONTH), calendarUnmarshal2.get(Calendar.MONTH));
		assertEquals(calendar.get(Calendar.DAY_OF_MONTH), calendarUnmarshal2.get(Calendar.DAY_OF_MONTH));
		Calendar calendarUnmarshal3 = Calendar.getInstance();
		calendarUnmarshal3.setTime(toValidate.getDateAttribute3());
		assertEquals(calendar.get(Calendar.YEAR), calendarUnmarshal3.get(Calendar.YEAR));
		assertEquals(calendar.get(Calendar.MONTH), calendarUnmarshal3.get(Calendar.MONTH));
		assertEquals(calendar.get(Calendar.DAY_OF_MONTH), calendarUnmarshal3.get(Calendar.DAY_OF_MONTH));

		assertEquals(base.getStringAttribute1(), toValidate.getStringAttribute1());
		assertEquals(base.getStringAttribute2(), toValidate.getStringAttribute2());
		assertEquals(base.getStringAttribute3(), toValidate.getStringAttribute3());
		assertEquals(base.getStringAttribute4(), toValidate.getStringAttribute4());
		assertEquals(base.getStringAttribute5(), toValidate.getStringAttribute5());
		assertEquals(base.getStringAttribute6(), toValidate.getStringAttribute6());
		assertEquals(base.getStringAttribute7(), toValidate.getStringAttribute7());
		assertEquals(base.getStringAttribute8(), toValidate.getStringAttribute8());
		assertEquals(base.getStringAttribute9(), toValidate.getStringAttribute9());
		assertEquals(base.getStringAttribute10(), toValidate.getStringAttribute10());
		assertEquals(base.getStringAttribute11(), toValidate.getStringAttribute11());
		assertEquals(base.getStringAttribute12(), toValidate.getStringAttribute12());

		assertEquals(base.getIntegerAttribute1(), toValidate.getIntegerAttribute1());
		assertEquals(base.getIntegerAttribute2(), toValidate.getIntegerAttribute2());
		assertEquals(base.getIntegerAttribute3(), toValidate.getIntegerAttribute3());
		assertEquals(base.getIntegerAttribute4(), toValidate.getIntegerAttribute4());
		assertEquals(base.getIntegerAttribute5(), toValidate.getIntegerAttribute5());
		assertEquals(base.getIntegerAttribute6(), toValidate.getIntegerAttribute6());
		assertEquals(base.getIntegerAttribute7(), toValidate.getIntegerAttribute7());

		assertEquals(base.getDoubleAttribute1(), toValidate.getDoubleAttribute1());
		assertEquals(base.getDoubleAttribute2(), toValidate.getDoubleAttribute2());
		assertEquals(base.getDoubleAttribute3(), toValidate.getDoubleAttribute3(), 0.001);

		assertEquals(base.getLongAttribute(), toValidate.getLongAttribute());
		assertEquals(base.getBooleanAttribute1(), toValidate.getBooleanAttribute1());
		assertEquals(base.getBooleanAttribute2(), toValidate.getBooleanAttribute2());
		assertEquals(base.getBooleanAttribute3(), toValidate.getBooleanAttribute3());
		assertEquals(base.getBooleanAttribute4(), toValidate.getBooleanAttribute4());

		if (validateChilds) {
			assertEquals(base.getJob().getJobCode(), toValidate.getJob().getJobCode());
			assertEquals(base.getJob().getJobFamily(), toValidate.getJob().getJobFamily());
			assertEquals(base.getJob().getJobName(), toValidate.getJob().getJobName());
		}

		assertEquals(base.getIntegerPrimitiveAttribute1(), toValidate.getIntegerPrimitiveAttribute1());
		assertEquals(base.getIntegerPrimitiveAttribute2(), toValidate.getIntegerPrimitiveAttribute2());
		assertEquals(base.getIntegerPrimitiveAttribute3(), toValidate.getIntegerPrimitiveAttribute3());
		assertEquals(base.getIntegerPrimitiveAttribute4(), toValidate.getIntegerPrimitiveAttribute4());
		assertEquals(base.getIntegerPrimitiveAttribute5(), toValidate.getIntegerPrimitiveAttribute5());
		assertEquals(base.getDoublePrimitiveAttribute(), toValidate.getDoublePrimitiveAttribute(), 0.001);
		assertEquals(base.getLongPrimitiveAttribute(), toValidate.getLongPrimitiveAttribute());
		assertEquals(base.isBooleanPrimitiveAttribute(), toValidate.isBooleanPrimitiveAttribute());

		if (validateChilds) {
			assertEquals(base.getAddressInfo().getAddress(), toValidate.getAddressInfo().getAddress());
			assertEquals(base.getAddressInfo().getNumber(), toValidate.getAddressInfo().getNumber());
			assertEquals(base.getAddressInfo().getCity(), toValidate.getAddressInfo().getCity());
			assertEquals(base.getAddressInfo().getCityCode(), toValidate.getAddressInfo().getCityCode());
			assertEquals(base.getAddressInfo().getCountry(), toValidate.getAddressInfo().getCountry());
		}

		assertEquals(base.getFloatAttribute(), toValidate.getFloatAttribute());
		assertEquals(base.getFloatPrimitiveAttribute(), toValidate.getFloatPrimitiveAttribute(), 0.001);
		assertEquals(base.getUnitFamily(), toValidate.getUnitFamily());
		assertEquals(base.getBigDecimalAttribute().setScale(2, RoundingMode.HALF_UP), 
				toValidate.getBigDecimalAttribute().setScale(2, RoundingMode.HALF_UP));
		// TODO add new validation below
	}

}
