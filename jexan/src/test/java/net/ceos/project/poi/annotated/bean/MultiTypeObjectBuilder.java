package net.ceos.project.poi.annotated.bean;

import static org.testng.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;


public class MultiTypeObjectBuilder {

	/**
	 * Create a MultiTypeObject for tests.
	 * 
	 * @return the {@link MultiTypeObject}
	 */
	public static MultiTypeObject buildMultiTypeObject() {
		return buildMultiTypeObject(2);
	}

	/**
	 * Create a MultiTypeObject for tests.
	 * 
	 * @return the {@link MultiTypeObject}
	 */
	public static MultiTypeObject buildMultiTypeObject(int multiplier) {
		MultiTypeObject toValidate = new MultiTypeObject();

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
		toValidate.setIntegerPrimitiveAttribute(121 * multiplier);
		toValidate.setDoublePrimitiveAttribute(44.6 * multiplier);
		toValidate.setLongPrimitiveAttribute(987654321L * multiplier);
		toValidate.setBooleanPrimitiveAttribute(true);
		/* create sub object AddressInfo */
		AddressInfo ai = new AddressInfo();
		ai.setAddress("this is the street");
		ai.setNumber(99);
		ai.setCity("this is the city");
		ai.setCityCode(70065);
		ai.setCountry("This is a Country");
		toValidate.setAddressInfo(ai);
		toValidate.setFloatAttribute(14.765f * multiplier);
		toValidate.setFloatPrimitiveAttribute(11.1125f * multiplier);
		toValidate.setUnitFamily(UnitFamily.COMPONENTS);
		toValidate.setBigDecimalAttribute(BigDecimal.valueOf(24.777).multiply(BigDecimal.valueOf(multiplier)));
		// TODO add new fields below

		return toValidate;
	}

	/**
	 * Create a list of MultiTypeObject for tests.
	 * 
	 * @return the {@link MultiTypeObject}
	 */
	public static List<MultiTypeObject> buildListOfMultiTypeObject(int entryNumber) {

		List<MultiTypeObject> returnList = new ArrayList<>();
		for (int i = 0; i < entryNumber; i++) {
			returnList.add(buildMultiTypeObject(RandomUtils.nextInt(1, entryNumber)));
		}
		return returnList;
	}

	/**
	 * Validate the MultiTypeObject based on the object build with the method
	 * 'buildMultiTypeObject'
	 * 
	 * @param toValidate
	 *            the object to validate
	 */
	public static void validateMultiTypeObject(MultiTypeObject toValidate) {
		MultiTypeObject base = buildMultiTypeObject();

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
