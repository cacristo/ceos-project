package net.ceos.project.poi.annotated.core;

import org.junit.Test;

import net.ceos.project.poi.annotated.annotation.XlsElement;
import net.ceos.project.poi.annotated.bean.ObjectMask;
import net.ceos.project.poi.annotated.bean.ObjectMaskBuilder;

/**
 * Test multiple (format/transform) mask to apply at {@link XlsElement}
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public class MaskTest {

	/**
	 * 
	 * Test the marshal/unmarshal of one object applying mask at the
	 * {@link XlsElement}
	 */
	@Test
	public void testMarshalObjectMask() throws Exception {
		ObjectMask om = ObjectMaskBuilder.buildObjectMask();

		IEngine en = new Engine();
		en.marshalAndSave(om, TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test if the object who applied mask at the {@link XlsElement} cause any damage at the moment
	 * of unmarshal.
	 */
	@Test
	public void testUnmarshalObjectMask() throws Exception {
		ObjectMask charger = new ObjectMask();

		IEngine en = new Engine();
		en.unmarshalFromPath(charger, TestUtils.WORKING_DIR_GENERATED_II);

		ObjectMaskBuilder.validateObjectMask(charger);
	}
}
