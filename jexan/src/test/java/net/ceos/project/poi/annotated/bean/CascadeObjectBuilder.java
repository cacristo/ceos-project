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
 * data at the object {@link CascadeObject}
 * <p>
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public class CascadeObjectBuilder {

	/**
	 * Create a CascadeObject for tests.
	 * 
	 * @return the {@link CascadeObject}
	 */
	public static CascadeObject buildCascadeObject() {
		return buildCascadeObject(2);
	}

	/**
	 * Create a CascadeObject for tests.
	 * 
	 * @return the {@link CascadeObject}
	 */
	public static CascadeObject buildCascadeObject(int multiplier) {
		CascadeObject toValidate = new CascadeObject();

		toValidate.setDateAttribute(new Date());
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

		BasicObject bo = BasicObjectBuilder.buildBasicObject();
		List<BasicObject> boList = new ArrayList<>();
		boList.add(bo);
		boList.add(bo);
		boList.add(bo);
		boList.add(bo);
		boList.add(bo);
		
		toValidate.setBasicObjectList(boList);
		
		ObjectConfigurable oc = ObjectConfigurableBuilder.buildObjectConfigurable();
		ArrayList<ObjectConfigurable> ocList = new ArrayList<>();
		ocList.add(oc);
		ocList.add(oc);
		ocList.add(oc);
		
		toValidate.setObjectConfList(ocList);
		
		CascadeL1 l1 = CascadeL1Builder.buildCascadeL1();
		ArrayList<CascadeL1> objectsList = new ArrayList<>();
		objectsList.add(l1);
		objectsList.add(l1);
		
		toValidate.setObjectsList(objectsList);
		/*ArrayList<CascadeObject> ownList = new ArrayList<>();
		ownList.add(toValidate);
		ownList.add(toValidate);
		
		toValidate.setObjectsList(ownList);*/
		// TODO add new fields below

		return toValidate;
	}

	/**
	 * Create a list of CascadeObject for tests.
	 * 
	 * @return the {@link CascadeObject}
	 */
	public static List<CascadeObject> buildListOfCascadeObject(int entryNumber) {

		List<CascadeObject> returnList = new ArrayList<>();
		for (int i = 0; i < entryNumber; i++) {
			returnList.add(buildCascadeObject(RandomUtils.nextInt(1, entryNumber)));
		}
		return returnList;
	}

	/**
	 * Validate the CascadeObject based on the object build with the method
	 * 'buildCascadeObject'
	 * 
	 * @param toValidate
	 *            the object to validate
	 */
	public static void validateCascadeObject(CascadeObject toValidate) {
		CascadeObject base = buildCascadeObject();

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

		assertEquals(base.getBasicObjectList(), toValidate.getBasicObjectList());
		assertEquals(base.getObjectConfList(), toValidate.getObjectConfList());
		// TODO add new validation below
	}

}
