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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;

/**
 * Builder to:
 * <li>generate</li>
 * <li>inject</li>
 * <li>validate</li><br>
 * data at the object {@link MultiTypeObject}<br>
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public class MultiTypeObjectBuilder {

	/**
	 * Create a MultiTypeObject for tests.
	 * 
	 * @return the {@link MultiTypeObject}
	 */
	public static MultiTypeObject buildMultiTypeObject() {
		return buildMultiTypeObject(2);
	}

	/**
	 * Create a MultiTypeObject for tests.
	 * 
	 * @return the {@link MultiTypeObject}
	 */
	public static MultiTypeObject buildMultiTypeObject(int multiplier) {
		MultiTypeObject toValidate = new MultiTypeObject();

		toValidate.setDateAttribute(new Date());
		toValidate.setLocalDateAttribute(LocalDate.now());
		toValidate.setLocalDateTimeAttribute(LocalDateTime.now());
		toValidate.setStringAttribute("some string");
		toValidate.setIntegerAttribute(46 * multiplier);
		toValidate.setDoubleAttribute(Double.valueOf("25.3") * multiplier);
		toValidate.setLongAttribute(Long.valueOf("1234567890") * multiplier);
		toValidate.setBooleanAttribute(Boolean.FALSE);
		/* create sub object Job */
		Job job = new Job();
		job.setJobCode(0005);
		job.setJobFamily("Family Job Name");
		job.setJobName("Job Name");
		toValidate.setJob(job);
		toValidate.setIntegerPrimitiveAttribute(121 * multiplier);
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
		toValidate.setShortAttribute((short) 17);
		toValidate.setShortPrimitiveAttribute((short) 4);
		// TODO add new fields below

		return toValidate;
	}

	/**
	 * Create a list of MultiTypeObject for tests.
	 * 
	 * @return the {@link MultiTypeObject}
	 */
	public static List<MultiTypeObject> buildListOfMultiTypeObject(int entryNumber) {

		List<MultiTypeObject> returnList = new ArrayList<>();
		for (int i = 0; i < entryNumber; i++) {
			returnList.add(buildMultiTypeObject(RandomUtils.nextInt(1, entryNumber)));
		}
		return returnList;
	}

	/**
	 * Validate the MultiTypeObject based on the object build with the method
	 * 'buildMultiTypeObject'
	 * 
	 * @param toValidate
	 *            the object to validate
	 */
	public static void validateMultiTypeObject(MultiTypeObject toValidate) {
		MultiTypeObject base = buildMultiTypeObject();

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());

		Calendar calendarUnmarshal = Calendar.getInstance();
		calendarUnmarshal.setTime(toValidate.getDateAttribute());
		assertEquals(calendar.get(Calendar.YEAR), calendarUnmarshal.get(Calendar.YEAR));
		assertEquals(calendar.get(Calendar.MONTH), calendarUnmarshal.get(Calendar.MONTH));
		assertEquals(calendar.get(Calendar.DAY_OF_MONTH), calendarUnmarshal.get(Calendar.DAY_OF_MONTH));
		LocalDate localDate = LocalDate.now();
		assertEquals(localDate.getDayOfMonth(), toValidate.getLocalDateAttribute().getDayOfMonth());
		assertEquals(localDate.getMonth(), toValidate.getLocalDateAttribute().getMonth());
		assertEquals(localDate.getYear(), toValidate.getLocalDateAttribute().getYear());
		LocalDateTime localDateTime = LocalDateTime.now();
		assertEquals(localDateTime.getDayOfMonth(), toValidate.getLocalDateTimeAttribute().getDayOfMonth());
		assertEquals(localDateTime.getMonth(), toValidate.getLocalDateTimeAttribute().getMonth());
		assertEquals(localDateTime.getYear(), toValidate.getLocalDateTimeAttribute().getYear());
		assertEquals(localDateTime.getHour(), toValidate.getLocalDateTimeAttribute().getHour());
		
		/* it is possible to have an error below due the time of execution of the test */
		assertEquals(localDateTime.getMinute(), toValidate.getLocalDateTimeAttribute().getMinute());
		
		assertEquals(base.getStringAttribute(), toValidate.getStringAttribute());
		assertEquals(base.getIntegerAttribute(), toValidate.getIntegerAttribute());
		assertEquals(base.getDoubleAttribute(), toValidate.getDoubleAttribute());
		assertEquals(base.getLongAttribute(), toValidate.getLongAttribute());
		assertEquals(base.getBooleanAttribute(), toValidate.getBooleanAttribute());
		assertEquals(base.getJob().getJobCode(), toValidate.getJob().getJobCode());
		assertEquals(base.getJob().getJobFamily(), toValidate.getJob().getJobFamily());
		assertEquals(base.getJob().getJobName(), toValidate.getJob().getJobName());
		assertEquals(base.getIntegerPrimitiveAttribute(), toValidate.getIntegerPrimitiveAttribute());
		assertEquals(base.getDoublePrimitiveAttribute(), toValidate.getDoublePrimitiveAttribute());
		assertEquals(base.getLongPrimitiveAttribute(), toValidate.getLongPrimitiveAttribute());
		assertEquals(base.isBooleanPrimitiveAttribute(), toValidate.isBooleanPrimitiveAttribute());
		assertEquals(base.getAddressInfo().getAddress(), toValidate.getAddressInfo().getAddress());
		assertEquals(base.getAddressInfo().getNumber(), toValidate.getAddressInfo().getNumber());
		assertEquals(base.getAddressInfo().getCity(), toValidate.getAddressInfo().getCity());
		assertEquals(base.getAddressInfo().getCityCode(), toValidate.getAddressInfo().getCityCode());
		assertEquals(base.getAddressInfo().getCountry(), toValidate.getAddressInfo().getCountry());
		assertEquals(base.getFloatAttribute(), toValidate.getFloatAttribute());
		assertEquals(base.getFloatPrimitiveAttribute(), toValidate.getFloatPrimitiveAttribute());
		assertEquals(base.getUnitFamily(), toValidate.getUnitFamily());
		assertEquals(base.getBigDecimalAttribute(), toValidate.getBigDecimalAttribute());
		assertEquals(base.getShortAttribute(), toValidate.getShortAttribute());
		assertEquals(base.getShortPrimitiveAttribute(), toValidate.getShortPrimitiveAttribute());
		// TODO add new validation below
	}

}
