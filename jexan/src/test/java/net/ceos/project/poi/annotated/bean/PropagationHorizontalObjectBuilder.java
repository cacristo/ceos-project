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
 * data at the object {@link PropagationHorizontalObject}<br>
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public class PropagationHorizontalObjectBuilder {

	/**
	 * Create a PropagationHorizontalObject for tests.
	 * 
	 * @return the {@link PropagationHorizontalObject}
	 */
	public static PropagationHorizontalObject buildPropagationHorizontalObject() {
		return buildPropagationHorizontalObject(2);
	}

	/**
	 * Create a PropagationHorizontalObject for tests.
	 * 
	 * @return the {@link PropagationHorizontalObject}
	 */
	public static PropagationHorizontalObject buildPropagationHorizontalObject(int multiplier) {
		PropagationHorizontalObject toValidate = new PropagationHorizontalObject();

		toValidate.setDateAttribute(new Date());
		toValidate.setStringAttribute("some string");
		toValidate.setIntegerAttribute(46 * multiplier);
		toValidate.setBooleanAttribute(Boolean.FALSE);
		toValidate.setDoublePrimitiveAttribute(44.6 * multiplier);
		toValidate.setLongPrimitiveAttribute(987654321L * multiplier);
		toValidate.setFloatAttribute(14.765f * multiplier);
		toValidate.setUnitFamily(UnitFamily.COMPONENTS);
		toValidate.setBigDecimalAttribute(BigDecimal.valueOf(24.777).multiply(BigDecimal.valueOf(multiplier)));
		// TODO add new fields below

		return toValidate;
	}

	/**
	 * Create a list of PropagationHorizontalObject for tests.
	 * 
	 * @return the {@link PropagationHorizontalObject}
	 */
	public static List<PropagationHorizontalObject> buildListOfPropagationHorizontalObject(int entryNumber) {

		List<PropagationHorizontalObject> returnList = new ArrayList<>();
		for (int i = 0; i < entryNumber; i++) {
			returnList.add(buildPropagationHorizontalObject(RandomUtils.nextInt(1, entryNumber)));
		}
		return returnList;
	}

	/**
	 * Validate the PropagationHorizontalObject based on the object build with the method
	 * 'buildPropagationHorizontalObject'
	 * 
	 * @param toValidate
	 *            the object to validate
	 */
	public static void validatePropagationHorizontalObject(PropagationHorizontalObject toValidate) {
		PropagationHorizontalObject base = buildPropagationHorizontalObject();

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());

		Calendar calendarUnmarshal = Calendar.getInstance();
		calendarUnmarshal.setTime(toValidate.getDateAttribute());
		assertEquals(calendar.get(Calendar.YEAR), calendarUnmarshal.get(Calendar.YEAR));
		assertEquals(calendar.get(Calendar.MONTH), calendarUnmarshal.get(Calendar.MONTH));
		assertEquals(calendar.get(Calendar.DAY_OF_MONTH), calendarUnmarshal.get(Calendar.DAY_OF_MONTH));
		assertEquals(base.getStringAttribute(), toValidate.getStringAttribute());
		assertEquals(base.getIntegerAttribute(), toValidate.getIntegerAttribute());
		assertEquals(base.getBooleanAttribute(), toValidate.getBooleanAttribute());
		assertEquals(base.getDoublePrimitiveAttribute(), toValidate.getDoublePrimitiveAttribute());
		assertEquals(base.getLongPrimitiveAttribute(), toValidate.getLongPrimitiveAttribute());
		assertEquals(base.getFloatAttribute(), toValidate.getFloatAttribute());
		assertEquals(base.getUnitFamily(), toValidate.getUnitFamily());
		assertEquals(base.getBigDecimalAttribute(), toValidate.getBigDecimalAttribute());
		// TODO add new validation below
	}

}
