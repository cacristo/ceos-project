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
 * data at the object {@link ObjectConfigurable}<br>
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public class ObjectConfigurableBuilder {

	/**
	 * Create a SimpleObject for tests.
	 * 
	 * @return the {@link SimpleObject}
	 */
	public static ObjectConfigurable buildObjectConfigurable() {
		return buildObjectConfigurable(1);
	}

	/**
	 * Create a SimpleObject for tests.
	 * 
	 * @return the {@link SimpleObject}
	 */
	public static ObjectConfigurable buildObjectConfigurable(int multiplier) {
		ObjectConfigurable toValidate = new ObjectConfigurable();

		toValidate.setBooleanAttribute(true);
		toValidate.setDateAttribute(new Date());
		toValidate.setDoubleAttribute(22.127 * multiplier);
		toValidate.setIntegerAttribute(46 * multiplier);
		toValidate.setLongAttribute(9870012L * multiplier);
		toValidate.setStringAttribute("This is a sample string");
		toValidate.setDateAttribute1(new Date());
		// TODO add new fields below

		return toValidate;
	}

	/**
	 * Create a list of SimpleObject for tests.
	 * 
	 * @return the {@link SimpleObject}
	 */
	public static List<ObjectConfigurable> buildListOfObjectConfigurable(int entryNumber) {

		List<ObjectConfigurable> returnList = new ArrayList<ObjectConfigurable>();
		for (int i = 0; i < entryNumber; i++) {
			returnList.add(buildObjectConfigurable(RandomUtils.nextInt(1, entryNumber)));
		}
		return returnList;
	}

	/**
	 * Validate the ObjectConfigurable based on the object build with the method
	 * 'buildObjectConfigurable'
	 * 
	 * @param toValidate
	 *            the object to validate
	 */
	public static void validateObjectConfigurable(ObjectConfigurable toValidate) {
		ObjectConfigurable base = buildObjectConfigurable();

		assertEquals(base.getBooleanAttribute(), toValidate.getBooleanAttribute());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		Calendar calendarUnmarshal = Calendar.getInstance();
		calendarUnmarshal.setTime(toValidate.getDateAttribute());
		assertEquals(calendar.get(Calendar.YEAR), calendarUnmarshal.get(Calendar.YEAR));
		assertEquals(calendar.get(Calendar.MONTH), calendarUnmarshal.get(Calendar.MONTH));
		assertEquals(calendar.get(Calendar.DAY_OF_MONTH), calendarUnmarshal.get(Calendar.DAY_OF_MONTH));
		assertEquals(base.getDoubleAttribute(), toValidate.getDoubleAttribute());
		assertEquals(base.getIntegerAttribute(), toValidate.getIntegerAttribute());
		assertEquals(base.getLongAttribute(), toValidate.getLongAttribute());
		assertEquals(base.getStringAttribute(), toValidate.getStringAttribute());
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(new Date());
		Calendar calendarUnmarshal1 = Calendar.getInstance();
		calendarUnmarshal1.setTime(toValidate.getDateAttribute1());
		assertEquals(calendar1.get(Calendar.YEAR), calendarUnmarshal1.get(Calendar.YEAR));
		assertEquals(calendar1.get(Calendar.MONTH), calendarUnmarshal1.get(Calendar.MONTH));
		assertEquals(calendar1.get(Calendar.DAY_OF_MONTH), calendarUnmarshal1.get(Calendar.DAY_OF_MONTH));

		// TODO the object is null : review
		assertEquals(0, toValidate.getJob().getJobCode());
		assertEquals(null, toValidate.getJob().getJobFamily());
		assertEquals(null, toValidate.getJob().getJobName());
		// TODO add new validation below
	}

}
