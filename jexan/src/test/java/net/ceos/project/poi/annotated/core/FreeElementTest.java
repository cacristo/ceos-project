package net.ceos.project.poi.annotated.core;

import org.junit.Test;

import net.ceos.project.poi.annotated.annotation.XlsFreeElement;
import net.ceos.project.poi.annotated.bean.FreeElementAdvancedObject;
import net.ceos.project.poi.annotated.bean.FreeElementAdvancedObjectBuilder;
import net.ceos.project.poi.annotated.bean.FreeElementObject;
import net.ceos.project.poi.annotated.bean.FreeElementObjectBuilder;

/**
 * Test the annotation {@link XlsFreeElement}
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public class FreeElementTest {

	/**
	 * Test the marshal of one simple object with the annotation
	 * {@link XlsFreeElement}
	 */
	@Test
	public void testMarshalFreeElementObject() throws Exception {
		FreeElementObject fe = FreeElementObjectBuilder.buildFreeElementObject();

		IEngine en = new Engine();
		en.marshalAndSave(fe, TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test the unmarshal of one simple object with the annotation
	 * {@link XlsFreeElement}
	 */
	@Test
	public void testUnmarshalFreeElementObject() throws Exception {
		FreeElementObject fe = new FreeElementObject();

		IEngine en = new Engine();
		en.unmarshalFromPath(fe, TestUtils.WORKING_DIR_GENERATED_I);

		FreeElementObjectBuilder.validateFreeElementObject(fe);
	}

	/**
	 * Test the marshal of one complex object with the annotation
	 * {@link XlsFreeElement}
	 */
	@Test
	public void testMarshalFreeElementAdvancedObject() throws Exception {
		FreeElementAdvancedObject fea = FreeElementAdvancedObjectBuilder.buildFreeElementAdvancedObject();

		IEngine en = new Engine();
		en.marshalAndSave(fea, TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test the unmarshal of one complex object with the annotation
	 * {@link XlsFreeElement}
	 */
	@Test
	public void testUnmarshalFreeElementAdvancedObject() throws Exception {
		FreeElementAdvancedObject fea = new FreeElementAdvancedObject();

		IEngine en = new Engine();
		en.unmarshalFromPath(fea, TestUtils.WORKING_DIR_GENERATED_I);

		FreeElementAdvancedObjectBuilder.validateFreeElementAdvancedObject(fea);
	}
}
