package net.ceos.project.poi.annotated.bean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;

import junit.framework.TestCase;

public class ObjectNullBuilder extends TestCase {

	/**
	 * Create a ObjectNull for tests.
	 * 
	 * @return the {@link ObjectNull}
	 */
	public static ObjectNull buildObjectNull() {
		return buildObjectNull(2);
	}

	/**
	 * Create a ObjectNull for tests.
	 * 
	 * @return the {@link ObjectNull}
	 */
	public static ObjectNull buildObjectNull(int multiplier) {
		ObjectNull obj = new ObjectNull();

		//obj.setDoubleAttribute(Double.valueOf(2));
		//obj.setDoublePrimitiveAttribute(Double.valueOf(2));
		/*obj.setDateAttribute(new Date());
		obj.setStringAttribute("some string");
		obj.setIntegerAttribute(46 * multiplier);
		obj.setDoubleAttribute(Double.valueOf("25.3") * multiplier);
		obj.setLongAttribute(Long.valueOf("1234567890") * multiplier);
		obj.setBooleanAttribute(Boolean.FALSE);*/
		/* create sub object Job */
		/*Job job = new Job();
		job.setJobCode(0005);
		job.setJobFamily("Family Job Name");
		job.setJobName("Job Name");
		obj.setJob(job);
		obj.setIntegerPrimitiveAttribute(121 * multiplier);
		obj.setDoublePrimitiveAttribute(44.6 * multiplier);
		obj.setLongPrimitiveAttribute(987654321L * multiplier);
		obj.setBooleanPrimitiveAttribute(true);*/
		/* create sub object AddressInfo */
		/*AddressInfo ai = new AddressInfo();
		ai.setAddress("this is the street");
		ai.setNumber(99);
		ai.setCity("this is the city");
		ai.setCityCode(70065);
		ai.setCountry("This is a Country");
		obj.setAddressInfo(ai);
		obj.setFloatAttribute(14.765f * multiplier);
		obj.setFloatPrimitiveAttribute(11.1125f * multiplier);
		obj.setUnitFamily(UnitFamily.COMPONENTS);
		obj.setBigDecimalAttribute(BigDecimal.valueOf(24.777).multiply(BigDecimal.valueOf(multiplier)));*/
		// TODO add new fields below

		return obj;
	}

	/**
	 * Create a list of ObjectNull for tests.
	 * 
	 * @return the {@link ObjectNull}
	 */
	public static List<ObjectNull> buildListOfObjectNull(int entryNumber) {

		List<ObjectNull> returnList = new ArrayList<>();
		for (int i = 0; i < entryNumber; i++) {
			returnList.add(buildObjectNull(RandomUtils.nextInt(1, entryNumber)));
		}
		return returnList;
	}

	/**
	 * Validate the ObjectNull based on the object build with the method
	 * 'buildObjectNull'
	 * 
	 * @param toValidate
	 *            the object to validate
	 */
	public static void validateObjectNull(ObjectNull toValidate) {
		ObjectNull base = buildObjectNull();

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
		assertEquals(base.getIntegerPrimitiveAttribute(), toValidate.getIntegerPrimitiveAttribute());
		assertEquals(base.getDoublePrimitiveAttribute(), toValidate.getDoublePrimitiveAttribute());
		assertEquals(base.getLongPrimitiveAttribute(), toValidate.getLongPrimitiveAttribute());
		assertEquals(base.isBooleanPrimitiveAttribute(), toValidate.isBooleanPrimitiveAttribute());
		assertEquals(base.getAddressInfo().getAddress(), toValidate.getAddressInfo().getAddress());
		assertEquals(base.getAddressInfo().getNumber(), toValidate.getAddressInfo().getNumber());
		assertEquals(base.getAddressInfo().getCity(), toValidate.getAddressInfo().getCity());
		assertEquals(base.getAddressInfo().getCityCode(), toValidate.getAddressInfo().getCityCode());
		assertEquals(base.getAddressInfo().getCountry(), toValidate.getAddressInfo().getCountry());
		assertEquals(base.getFloatAttribute(), toValidate.getFloatAttribute());
		assertEquals(base.getFloatPrimitiveAttribute(), toValidate.getFloatPrimitiveAttribute());
		assertEquals(base.getUnitFamily(), toValidate.getUnitFamily());
		assertEquals(base.getBigDecimalAttribute(), toValidate.getBigDecimalAttribute());
		// TODO add new validation below
	}

}
