package net.ceos.project.poi.annotated.annotation;

import static org.testng.Assert.assertEquals;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;

import net.ceos.project.poi.annotated.bean.XMenFactory;

/**
 * Test the annotation {@link XlsNestedHeader}
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public class XlsNestedHeaderTest {

	/**
	 * Test default configuration.
	 */
	@Test
	public void testDefaultConfiguration() {
		Class<XMenFactory.DefaultConfig> oC = XMenFactory.DefaultConfig.class;

		List<Field> fL = Arrays.asList(oC.getDeclaredFields());
		for (Field f : fL) {
			// Process @XlsNestedHeader
			if (f.isAnnotationPresent(XlsNestedHeader.class)) {

				XlsNestedHeader xlsNestedHeader = (XlsNestedHeader) f.getAnnotation(XlsNestedHeader.class);

				// add here the annotations attributes
				assertEquals(xlsNestedHeader.startX(), 0);
				assertEquals(xlsNestedHeader.startY(), 0);
				assertEquals(xlsNestedHeader.endX(), 0);
				assertEquals(xlsNestedHeader.endY(), 0);
			}
		}
	}

	/**
	 * Test cell configuration.
	 */
	@Test
	public void testCellConfiguration() {
		Class<XMenFactory.Cyclops> oC = XMenFactory.Cyclops.class;

		List<Field> fL = Arrays.asList(oC.getDeclaredFields());
		for (Field f : fL) {
			// Process @XlsNestedHeader
			if (f.isAnnotationPresent(XlsNestedHeader.class)) {

				XlsNestedHeader xlsNestedHeader = (XlsNestedHeader) f.getAnnotation(XlsNestedHeader.class);

				// add here the annotations attributes
				assertEquals(xlsNestedHeader.startX(), 1);
				assertEquals(xlsNestedHeader.endX(), 3);
			}
		}
	}

	/**
	 * Test row configuration.
	 */
	@Test
	public void testRowConfiguration() {
		Class<XMenFactory.IronMan> oC = XMenFactory.IronMan.class;

		List<Field> fL = Arrays.asList(oC.getDeclaredFields());
		for (Field f : fL) {
			// Process @XlsNestedHeader
			if (f.isAnnotationPresent(XlsNestedHeader.class)) {

				XlsNestedHeader xlsNestedHeader = (XlsNestedHeader) f.getAnnotation(XlsNestedHeader.class);

				// add here the annotations attributes
				assertEquals(xlsNestedHeader.startY(), 1);
				assertEquals(xlsNestedHeader.endY(), 2);
			}
		}
	}
}
