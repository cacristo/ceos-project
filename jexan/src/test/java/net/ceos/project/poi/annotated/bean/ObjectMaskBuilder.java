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

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;

import net.ceos.project.poi.annotated.annotation.XlsElement;

/**
 * Builder to:
 * <ul>
 * <li>generate
 * <li>inject
 * <li>validate
 * </ul>
 * data at the object {@link ObjectMask}
 * <p>
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public class ObjectMaskBuilder {

	/**
	 * Create a SimpleObject for tests.
	 * 
	 * @return the {@link SimpleObject}
	 */
	public static ObjectMask buildObjectMask() {
		return buildObjectMask(1);
	}

	/**
	 * Create a SimpleObject for tests.
	 * 
	 * @return the {@link SimpleObject}
	 */
	public static ObjectMask buildObjectMask(int multiplier) {
		ObjectMask toValidate = new ObjectMask();

		Date date = new Date();
		// Date
		toValidate.setDateAttribute1(date);
		toValidate.setDateAttribute2(date);
		toValidate.setDateAttribute3(date);
		toValidate.setDateAttribute4(date);
		toValidate.setDateAttribute5(date);
		toValidate.setDateAttribute6(date);
		toValidate.setDateAttribute7(date);
		// Double
		toValidate.setDoubleAttribute1(1625.487 * multiplier);
		toValidate.setDoubleAttribute2(1625.487 * multiplier);
		toValidate.setDoubleAttribute3(1625.487 * multiplier);
		toValidate.setDoubleAttribute4(1625.487 * multiplier);
		toValidate.setDoubleAttribute5(1625.487 * multiplier);
		// BigDecimal
		toValidate.setBigDecimalAttribute1(BigDecimal.valueOf(111.366 * multiplier));
		toValidate.setBigDecimalAttribute2(BigDecimal.valueOf(111.366 * multiplier));
		toValidate.setBigDecimalAttribute3(BigDecimal.valueOf(111.366 * multiplier));
		toValidate.setBigDecimalAttribute4(BigDecimal.valueOf(111.366 * multiplier));
		toValidate.setBigDecimalAttribute5(BigDecimal.valueOf(111.366 * multiplier));
		toValidate.setBigDecimalAttribute6(BigDecimal.valueOf(111.366 * multiplier));
		// Float
		toValidate.setFloatAttribute1(46.445f * multiplier);
		toValidate.setFloatAttribute2(46.445f * multiplier);
		toValidate.setFloatAttribute3(46.445f * multiplier);
		toValidate.setFloatAttribute4(46.445f * multiplier);
		toValidate.setFloatAttribute5(46.445f * multiplier);
		// Boolean
		toValidate.setBooleanAttribute1(true);
		toValidate.setBooleanAttribute2(true);
		toValidate.setBooleanAttribute3(false);
		toValidate.setBooleanAttribute4(false);
		// Integer
		toValidate.setIntegerAttribute1(23);
		toValidate.setIntAttribute2(10);
		// Short
		toValidate.setShortAttribute1((short)5);
		toValidate.setShortAttribute2((short)44);
		// TODO add new fields below

		return toValidate;
	}

	/**
	 * Create a list of SimpleObject for tests.
	 * 
	 * @return the {@link SimpleObject}
	 */
	public static List<ObjectMask> buildListOfObjectMask(int entryNumber) {

		List<ObjectMask> returnList = new ArrayList<>();
		for (int i = 0; i < entryNumber; i++) {
			returnList.add(buildObjectMask(RandomUtils.nextInt(1, entryNumber)));
		}
		return returnList;
	}

	/**
	 * Validate the ObjectMask based on the object build with the method
	 * 'buildObjectMask'
	 * 
	 * @param toValidate
	 *            the object to validate
	 */
	public static void validateObjectMask(ObjectMask toValidate) {
		ObjectMask base = buildObjectMask();

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		Calendar calendarUnmarshal = Calendar.getInstance();
		calendarUnmarshal.setTime(toValidate.getDateAttribute1());
		assertEquals(calendar.get(Calendar.YEAR), calendarUnmarshal.get(Calendar.YEAR));
		assertEquals(calendar.get(Calendar.MONTH), calendarUnmarshal.get(Calendar.MONTH));
		assertEquals(calendar.get(Calendar.DAY_OF_MONTH), calendarUnmarshal.get(Calendar.DAY_OF_MONTH));

		assertEquals(base.getDoubleAttribute1(), toValidate.getDoubleAttribute1());
		assertEquals(base.getDoubleAttribute2(), toValidate.getDoubleAttribute2());
		assertEquals(base.getDoubleAttribute3(), toValidate.getDoubleAttribute3());
		assertEquals(transformValuesToValidate("doubleAttribute4", base.getDoubleAttribute4()),
				toValidate.getDoubleAttribute4());
		assertEquals(base.getDoubleAttribute5(), toValidate.getDoubleAttribute5());
		assertEquals(base.getBigDecimalAttribute1(), toValidate.getBigDecimalAttribute1());
		assertEquals(base.getBigDecimalAttribute2(), toValidate.getBigDecimalAttribute2());
		assertEquals(base.getBigDecimalAttribute3(), toValidate.getBigDecimalAttribute3());
		assertEquals(transformValuesToValidate("bigDecimalAttribute4", base.getBigDecimalAttribute4()),
				toValidate.getBigDecimalAttribute4());
		assertEquals(base.getBigDecimalAttribute5(), toValidate.getBigDecimalAttribute5());
		assertEquals(transformValuesToValidate("bigDecimalAttribute6", base.getBigDecimalAttribute6()),
				toValidate.getBigDecimalAttribute6());
		assertEquals(base.getFloatAttribute1(), toValidate.getFloatAttribute1());
		assertEquals(base.getFloatAttribute2(), toValidate.getFloatAttribute2());
		assertEquals(base.getFloatAttribute3(), toValidate.getFloatAttribute3());
		assertEquals(base.getFloatAttribute4(), toValidate.getFloatAttribute4());
		assertEquals(base.getFloatAttribute5(), toValidate.getFloatAttribute5());
		assertEquals(base.isBooleanAttribute1(), toValidate.isBooleanAttribute1());
		assertEquals(base.getBooleanAttribute2(), toValidate.getBooleanAttribute2());
		assertEquals(base.getBooleanAttribute3(), toValidate.getBooleanAttribute3());
		assertEquals(base.isBooleanAttribute4(), toValidate.isBooleanAttribute4());
		// TODO add new validation below
		assertEquals(base.getIntegerAttribute1(), toValidate.getIntegerAttribute1());
		assertEquals(base.getIntAttribute2(), toValidate.getIntAttribute2());
		assertEquals(base.getShortAttribute1(), toValidate.getShortAttribute1());
		assertEquals(base.getShortAttribute2(), toValidate.getShortAttribute2());
	}

	/**
	 * Transform Double values to validate.
	 * 
	 * @param fieldName
	 * @param value
	 * @return
	 */
	private static Double transformValuesToValidate(String fieldName, Double value) {
		ObjectMask a = new ObjectMask();

		try {
			Field f = a.getClass().getDeclaredField(fieldName);

			if (f.isAnnotationPresent(XlsElement.class)) {
				XlsElement xlsAnnotation = (XlsElement) f.getAnnotation(XlsElement.class);

				if (StringUtils.isNotBlank(xlsAnnotation.transformMask())) {
					DecimalFormat df = new DecimalFormat(xlsAnnotation.transformMask());
					String formattedValue = df.format((Double) value);
					value = Double.valueOf(formattedValue.replace(",", "."));
				}
			}

		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		return value;
	}

	/**
	 * Transform BigDecimal values to validate.
	 * 
	 * @param fieldName
	 * @param value
	 * @return
	 */
	private static BigDecimal transformValuesToValidate(String fieldName, BigDecimal value) {
		ObjectMask a = new ObjectMask();
		try {
			Field f = a.getClass().getDeclaredField(fieldName);

			if (f.isAnnotationPresent(XlsElement.class)) {
				XlsElement xlsAnnotation = (XlsElement) f.getAnnotation(XlsElement.class);

				if (StringUtils.isNotBlank(xlsAnnotation.transformMask())) {
					DecimalFormat df = new DecimalFormat(xlsAnnotation.transformMask());
					String formattedValue = df.format((BigDecimal) value);
					value = BigDecimal.valueOf(Double.valueOf(formattedValue.replace(",", ".")));
				}
			}

		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		return value;
	}
}
