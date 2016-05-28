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
 * <ul>
 * <li>generate
 * <li>inject
 * <li>validate
 * </ul>
 * data at the object {@link CascadeL2}
 * <p>
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public class CascadeL2Builder {

	/**
	 * Create a CascadeL2 for tests.
	 * 
	 * @return the {@link CascadeL2}
	 */
	public static CascadeL2 buildCascadeL2() {
		return buildCascadeL2(2);
	}

	/**
	 * Create a CascadeL2 for tests.
	 * 
	 * @return the {@link CascadeL2}
	 */
	public static CascadeL2 buildCascadeL2(int multiplier) {
		CascadeL2 toValidate = new CascadeL2();

		toValidate.setDateAttribute(new Date());
		toValidate.setStringAttribute("Cascade L2");
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

		CascadeL3 l3 = CascadeL3Builder.buildCascadeL3();
		ArrayList<CascadeL3> l3List = new ArrayList<>();
		l3List.add(l3);
		l3List.add(l3);
		l3List.add(l3);
		l3List.add(l3);
		l3List.add(l3);
		
		toValidate.setObjectsList(l3List);
		// TODO add new fields below

		return toValidate;
	}

	/**
	 * Create a list of CascadeL2 for tests.
	 * 
	 * @return the {@link CascadeL2}
	 */
	public static List<CascadeL2> buildListOfCascadeL2(int entryNumber) {

		List<CascadeL2> returnList = new ArrayList<>();
		for (int i = 0; i < entryNumber; i++) {
			returnList.add(buildCascadeL2(RandomUtils.nextInt(1, entryNumber)));
		}
		return returnList;
	}

	/**
	 * Validate the CascadeL2 based on the object build with the method
	 * 'buildCascadeL2'
	 * 
	 * @param toValidate
	 *            the object to validate
	 */
	public static void validateCascadeL2(CascadeL2 toValidate) {
		CascadeL2 base = buildCascadeL2();

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
