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
 * data at the object {@link ObjectNull}
 * <p>
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
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
		// TODO add new validation below
	}

}
