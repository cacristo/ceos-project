package net.ceos.project.poi.annotated.annotation;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import net.ceos.project.poi.annotated.bean.ObjectsBuilderTest;
import net.ceos.project.poi.annotated.definition.CascadeType;
import net.ceos.project.poi.annotated.definition.PropagationType;

/**
 * Test the annotation {@link XlsNestedHeader}
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public class XlsSheetTest {

	/**
	 * Test default configuration.
	 */
	@Test
	public void testDefaultConfigurationAttibutes() {
		Class<ObjectsBuilderTest.ObjectWithDefaultConfig> oC = ObjectsBuilderTest.ObjectWithDefaultConfig.class;

		// Process @XlsSheet
		if (oC.isAnnotationPresent(XlsSheet.class)) {

			XlsSheet xlsSheet = (XlsSheet) oC.getAnnotation(XlsSheet.class);

			// add here the annotations attributes
			assertEquals(xlsSheet.startRow(), 1);
			assertEquals(xlsSheet.startCell(), 1);
			assertEquals(xlsSheet.propagation(), PropagationType.PROPAGATION_HORIZONTAL);
			assertEquals(xlsSheet.cascadeLevel(), CascadeType.CASCADE_BASE);
		}
	}

	/**
	 * Test title attribute.
	 */
	@Test
	public void testTitleAttibute() {
		Class<ObjectsBuilderTest.ObjectWithDefaultConfig> oC = ObjectsBuilderTest.ObjectWithDefaultConfig.class;

		// Process @XlsSheet
		if (oC.isAnnotationPresent(XlsSheet.class)) {

			XlsSheet xlsSheet = (XlsSheet) oC.getAnnotation(XlsSheet.class);

			// add here the annotations attributes
			assertEquals(xlsSheet.title(), "Default configuration sample");
		}
	}

	/**
	 * Test propagation attribute with type PROPAGATION_VERTICAL.
	 */
	@Test
	public void testPropagationVerticalAttibute() {
		Class<ObjectsBuilderTest.IronMan> oC = ObjectsBuilderTest.IronMan.class;

		// Process @XlsSheet
		if (oC.isAnnotationPresent(XlsSheet.class)) {

			XlsSheet xlsSheet = (XlsSheet) oC.getAnnotation(XlsSheet.class);

			// add here the annotations attributes
			assertEquals(xlsSheet.propagation(), PropagationType.PROPAGATION_VERTICAL);
		}
	}

	/**
	 * Test propagation attribute with type PROPAGATION_HORIZONTAL.
	 */
	@Test
	public void testPropagationHorizontalAttibute() {
		Class<ObjectsBuilderTest.Thor> oC = ObjectsBuilderTest.Thor.class;

		// Process @XlsSheet
		if (oC.isAnnotationPresent(XlsSheet.class)) {

			XlsSheet xlsSheet = (XlsSheet) oC.getAnnotation(XlsSheet.class);

			// add here the annotations attributes
			assertEquals(xlsSheet.propagation(), PropagationType.PROPAGATION_HORIZONTAL);
		}
	}

	/**
	 * Test cascade level attribute with type CASCADE_BASE.
	 */
	@Test
	public void testCascadeLevelBaseAttibute() {
		Class<ObjectsBuilderTest.IronMan> oC = ObjectsBuilderTest.IronMan.class;

		// Process @XlsSheet
		if (oC.isAnnotationPresent(XlsSheet.class)) {

			XlsSheet xlsSheet = (XlsSheet) oC.getAnnotation(XlsSheet.class);

			// add here the annotations attributes
			assertEquals(xlsSheet.cascadeLevel(), CascadeType.CASCADE_BASE);
		}
	}

	/**
	 * Test cascade level attribute with type CASCADE_LEVEL_ONE.
	 */
	@Test
	public void testCascadeLevelOneAttibute() {
		Class<ObjectsBuilderTest.Wolverine> oC = ObjectsBuilderTest.Wolverine.class;

		// Process @XlsSheet
		if (oC.isAnnotationPresent(XlsSheet.class)) {

			XlsSheet xlsSheet = (XlsSheet) oC.getAnnotation(XlsSheet.class);

			// add here the annotations attributes
			assertEquals(xlsSheet.cascadeLevel(), CascadeType.CASCADE_LEVEL_ONE);
		}
	}

	/**
	 * Test cascade level attribute with type CASCADE_LEVEL_TWO.
	 */
	@Test
	public void testCascadeLevelTwoAttibute() {
		Class<ObjectsBuilderTest.SpiderMan> oC = ObjectsBuilderTest.SpiderMan.class;

		// Process @XlsSheet
		if (oC.isAnnotationPresent(XlsSheet.class)) {

			XlsSheet xlsSheet = (XlsSheet) oC.getAnnotation(XlsSheet.class);

			// add here the annotations attributes
			assertEquals(xlsSheet.cascadeLevel(), CascadeType.CASCADE_LEVEL_TWO);
		}
	}

	/**
	 * Test cascade level attribute with type CASCADE_FULL.
	 */
	@Test
	public void testCascadeLevelFullAttibute() {
		Class<ObjectsBuilderTest.DoctorX> oC = ObjectsBuilderTest.DoctorX.class;

		// Process @XlsSheet
		if (oC.isAnnotationPresent(XlsSheet.class)) {

			XlsSheet xlsSheet = (XlsSheet) oC.getAnnotation(XlsSheet.class);

			// add here the annotations attributes
			assertEquals(xlsSheet.cascadeLevel(), CascadeType.CASCADE_FULL);
		}
	}
}
