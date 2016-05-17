package net.ceos.project.poi.annotated.bean;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;


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
		ArrayList<CascadeL2> l2List = new ArrayList<>();
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

		List<CascadeL1> returnList = new ArrayList<>();
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
