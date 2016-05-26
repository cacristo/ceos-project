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
 * data at the object {@link BasicObject}<br>
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
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

		List<BasicObject> returnList = new ArrayList<BasicObject>();
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
		assertEquals(base.getFloatPrimitiveAttribute(), toValidate.getFloatPrimitiveAttribute(), 0.001);
		assertEquals(base.getUnitFamily(), toValidate.getUnitFamily());
		assertEquals(base.getBigDecimalAttribute(), toValidate.getBigDecimalAttribute());
		// TODO add new validation below
	}

}
