package org.jaexcel.framework.JAEX;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import net.ceos.project.poi.annotated.bean.ObjectWithDefaultConfig;



public class ReflectionTest {

	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
		ReflectionTest en = new ReflectionTest();
		ObjectWithDefaultConfig fastTest = new ObjectWithDefaultConfig();
		fastTest.setDateAttribute(new Date());
		fastTest.setIntegerAttribute(46);
		fastTest.setStringAttribute("some string");
		//fastTest.setCascade(EnumTest.CASCADE_BASE);
		en.marshal(fastTest);
	}

	public void marshal(Object object) throws IllegalArgumentException, IllegalAccessException {
		Class<?> objClass = object.getClass();

		List<Field> fieldList = Arrays.asList(objClass.getDeclaredFields());
		for (Field field : fieldList) {

			String fieldName = field.getName();
			String getFieldMethod = "get"
				+ field.getName().substring(0, 1).toUpperCase()
				+ field.getName().substring(1);

			System.out.println(fieldName);
			System.out.println(getFieldMethod);

			
			Class<?> fieldType = field.getType();
			if(fieldType.equals(String.class)){
				System.out.println("String type");
			}
			if(fieldType.equals(Integer.class)){
				System.out.println("Integer type");
			}
			if(fieldType.equals(Date.class)){
				System.out.println("Date type");
			}
			if(fieldType.isEnum()){
				System.out.println("Enum type");
			}
			if(fieldType.isPrimitive()){
				System.out.println(fieldType);
				System.out.println("primitive type");
			}
			
			field.setAccessible(true);
			Object value = field.get(object);
			System.out.println(value);

			System.out.println("----------------------------");
		}
	}
}
