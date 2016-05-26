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
 * data at the object {@link PropagationVerticalObject}<br>
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
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
