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

import static org.junit.Assert.assertEquals;

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
 * data at the object {@link CascadeL1}
 * <p>
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public class CascadeL1Builder {

	/**
	 * Create a CascadeL1 for tests.
	 * 
	 * @return the {@link CascadeL1}
	 */
	public static CascadeL1 buildCascadeL1() {
		return buildCascadeL1(2);
	}

	/**
	 * Create a CascadeL1 for tests.
	 * 
	 * @return the {@link CascadeL1}
	 */
	public static CascadeL1 buildCascadeL1(int multiplier) {
		CascadeL1 toValidate = new CascadeL1();

		toValidate.setDateAttribute(new Date());
		toValidate.setStringAttribute("cascade L1");
		toValidate.setIntegerAttribute(46 * multiplier);
		toValidate.setDoubleAttribute(Double.valueOf("25.3") * multiplier);
		toValidate.setLongAttribute(Long.valueOf("1234567890") * multiplier);
		toValidate.setBooleanAttribute(Boolean.FALSE);
		/* create sub object Job */
		Job job = new Job();
		job.setJobCode(0005);
		job.setJobFamily("Family Job Name L1");
		job.setJobName("Job Name L1");
		toValidate.setJob(job);

		CascadeL2 l2 = CascadeL2Builder.buildCascadeL2();
		ArrayList<CascadeL2> l2List = new ArrayList<CascadeL2>();
		l2List.add(l2);
		l2List.add(l2);
		l2List.add(l2);
		l2List.add(l2);
		l2List.add(l2);
		
		toValidate.setObjectsList(l2List);

		// TODO add new fields below

		return toValidate;
	}

	/**
	 * Create a list of CascadeL1 for tests.
	 * 
	 * @return the {@link CascadeL1}
	 */
	public static List<CascadeL1> buildListOfCascadeL1(int entryNumber) {

		List<CascadeL1> returnList = new ArrayList<CascadeL1>();
		for (int i = 0; i < entryNumber; i++) {
			returnList.add(buildCascadeL1(RandomUtils.nextInt(1, entryNumber)));
		}
		return returnList;
	}

	/**
	 * Validate the CascadeL1 based on the object build with the method
	 * 'buildCascadeL1'
	 * 
	 * @param toValidate
	 *            the object to validate
	 */
	public static void validateCascadeL1(CascadeL1 toValidate) {
		CascadeL1 base = buildCascadeL1();

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
