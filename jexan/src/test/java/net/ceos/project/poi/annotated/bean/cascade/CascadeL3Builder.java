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
package net.ceos.project.poi.annotated.bean.cascade;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;

import net.ceos.project.poi.annotated.bean.Job;

/**
 * Builder to:
 * <ul>
 * <li>generate
 * <li>inject
 * <li>validate
 * </ul>
 * data at the object {@link CascadeL3}
 * <p>
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public class CascadeL3Builder {

	/**
	 * Create a CascadeL3 for tests.
	 * 
	 * @return the {@link CascadeL3}
	 */
	public static CascadeL3 buildCascadeL3() {
		return buildCascadeL3(2);
	}

	/**
	 * Create a CascadeL3 for tests.
	 * 
	 * @return the {@link CascadeL3}
	 */
	public static CascadeL3 buildCascadeL3(int multiplier) {
		CascadeL3 toValidate = new CascadeL3();

		toValidate.setDateAttribute(new Date());
		toValidate.setStringAttribute("Cascade L3");
		toValidate.setIntegerAttribute(46 * multiplier);
		toValidate.setDoubleAttribute(Double.valueOf("25.3") * multiplier);
		toValidate.setLongAttribute(Long.valueOf("1234567890") * multiplier);
		toValidate.setBooleanAttribute(Boolean.FALSE);
		/* create sub object Job */
		Job job = new Job();
		job.setJobCode(0005);
		job.setJobFamily("Family Job Name L2");
		job.setJobName("Job Name L2");
		toValidate.setJob(job);

		// TODO add new fields below

		return toValidate;
	}

	/**
	 * Create a list of CascadeL3 for tests.
	 * 
	 * @return the {@link CascadeL3}
	 */
	public static List<CascadeL3> buildListOfCascadeL3(int entryNumber) {

		List<CascadeL3> returnList = new ArrayList<>();
		for (int i = 0; i < entryNumber; i++) {
			returnList.add(buildCascadeL3(RandomUtils.nextInt(1, entryNumber)));
		}
		return returnList;
	}

	/**
	 * Validate the CascadeL3 based on the object build with the method
	 * 'buildCascadeL3'
	 * 
	 * @param toValidate
	 *            the object to validate
	 */
	public static void validateCascadeL3(CascadeL3 toValidate) {
		CascadeL3 base = buildCascadeL3();

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

		// TODO add new validation below
	}

}
