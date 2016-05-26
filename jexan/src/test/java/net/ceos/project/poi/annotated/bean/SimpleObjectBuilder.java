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
 * <li>generate</li>
 * <li>inject</li>
 * <li>validate</li><br>
 * data at the object {@link SimpleObject}<br>
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public class SimpleObjectBuilder {

	/**
	 * Create a SimpleObject for tests.
	 * 
	 * @return the {@link SimpleObject}
	 */
	public static SimpleObject buildSimpleObject() {
		return buildSimpleObject(2);
	}

	/**
	 * Create a SimpleObject for tests.
	 * 
	 * @return the {@link SimpleObject}
	 */
	public static SimpleObject buildSimpleObject(int multiplier) {
		SimpleObject toValidate = new SimpleObject();
		toValidate.setDateAttribute(new Date());
		toValidate.setStringAttribute("some string value");
		toValidate.setIntegerAttribute(117 * multiplier);
		// TODO add new fields below

		return toValidate;
	}

	/**
	 * Create a list of SimpleObject for tests.
	 * 
	 * @return the {@link SimpleObject}
	 */
	public static List<SimpleObject> buildListOfSimpleObject(int entryNumber) {

		List<SimpleObject> returnList = new ArrayList<>();
		for (int i = 0; i < entryNumber; i++) {
			returnList.add(buildSimpleObject(RandomUtils.nextInt(1, entryNumber)));
		}
		return returnList;
	}

	/**
	 * Validate the SimpleObject based on the object build with the method
	 * 'buildSimpleObject'
	 * 
	 * @param toValidate
	 *            the object to validate
	 */
	public static void validateSimpleObject(SimpleObject toValidate) {
		SimpleObject base = buildSimpleObject();

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());

		Calendar calendarUnmarshal = Calendar.getInstance();
		calendarUnmarshal.setTime(toValidate.getDateAttribute());
		assertEquals(calendar.get(Calendar.YEAR), calendarUnmarshal.get(Calendar.YEAR));
		assertEquals(calendar.get(Calendar.MONTH), calendarUnmarshal.get(Calendar.MONTH));
		assertEquals(calendar.get(Calendar.DAY_OF_MONTH), calendarUnmarshal.get(Calendar.DAY_OF_MONTH));
		assertEquals(base.getStringAttribute(), toValidate.getStringAttribute());
		assertEquals(base.getIntegerAttribute(), toValidate.getIntegerAttribute());
		// TODO add new validation below
	}

}
