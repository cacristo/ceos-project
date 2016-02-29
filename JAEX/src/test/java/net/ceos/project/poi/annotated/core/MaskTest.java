package net.ceos.project.poi.annotated.core;

import org.testng.annotations.Test;

import net.ceos.project.poi.annotated.bean.ObjectMask;
import net.ceos.project.poi.annotated.bean.ObjectMaskBuilder;
import net.ceos.project.poi.annotated.core.Engine;
import net.ceos.project.poi.annotated.core.IEngine;

public class MaskTest {

	/**
	 * Test one basic object
	 */
	@Test
	public void testObject() throws Exception {
		ObjectMask om = ObjectMaskBuilder.buildObjectMask();

		IEngine en = new Engine();
		en.marshalAndSave(om, TestUtils.WORKING_DIR_GENERATED_I);

		// TODO validation result
		ObjectMask charger = new ObjectMask();
		en.unmarshalFromPath(charger, TestUtils.WORKING_DIR_GENERATED_II);
		
		ObjectMaskBuilder.validateObjectMask(charger);
	}

}
