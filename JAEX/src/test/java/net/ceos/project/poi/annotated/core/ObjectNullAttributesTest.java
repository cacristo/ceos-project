package net.ceos.project.poi.annotated.core;

import org.testng.annotations.Test;

import net.ceos.project.poi.annotated.bean.ObjectNull;
import net.ceos.project.poi.annotated.bean.ObjectNullBuilder;

/**
 * Unit test for simple App.
 */
public class ObjectNullAttributesTest {

	/**
	 * Test one basic object
	 */
	@Test
	public void testMarshalMultiObject() throws Exception {
		ObjectNull mto = ObjectNullBuilder.buildObjectNull();

		IEngine en = new Engine();
		en.marshalAndSave(mto, TestUtils.WORKING_DIR_GENERATED_I);

		// ObjectNull charger = new ObjectNull();
		// ObjectNullBuilder.validateObjectNull(charger);
	}

	/**
	 * Test one basic object
	 */
	@Test
	public void testUnmarshalMultiObject() throws Exception {
		ObjectNull mto = new ObjectNull();

		Engine en = new Engine();

		en.unmarshalFromPath(mto, TestUtils.WORKING_DIR_GENERATED_I);

		// ObjectNullBuilder.validateObjectNull(mto);

	}
}
