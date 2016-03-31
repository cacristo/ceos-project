package net.ceos.project.poi.annotated.annotation;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import org.testng.annotations.Test;

import net.ceos.project.poi.annotated.bean.XMenFactory;

/**
 * Test the annotation {@link XlsFreezePane}
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public class XlsFreezePaneTest {

	/**
	 * Test default configuration.
	 */
	@Test
	public void testDefaultConfiguration() {
		Class<XMenFactory.DefaultConfig> oC = XMenFactory.DefaultConfig.class;

		// Process @XlsSheet
		if (oC.isAnnotationPresent(XlsSheet.class)) {

			XlsSheet xlsSheet = (XlsSheet) oC.getAnnotation(XlsSheet.class);

			// add here the annotations attributes
			assertNotNull(xlsSheet.freezePane());
			assertEquals(xlsSheet.freezePane().topRow(), 0);
			assertEquals(xlsSheet.freezePane().leftMostColumn(), 0);
		}
	}

	@Test
	public void testFreezePaneHorizontal() {
		Class<XMenFactory.ProfessorX> o = XMenFactory.ProfessorX.class;

		// Process @XlsSheet
		if (o.isAnnotationPresent(XlsSheet.class)) {

			XlsSheet xlsSheet = (XlsSheet) o.getAnnotation(XlsSheet.class);

			// add here the annotations attributes
			assertNotNull(xlsSheet.freezePane());
			assertEquals(xlsSheet.freezePane().colSplit(), 0);
			assertEquals(xlsSheet.freezePane().rowSplit(), 4);

		}
	}

	@Test
	public void testFreezePaneHorizontalAdvanced() {
		Class<XMenFactory.Cyclops> o = XMenFactory.Cyclops.class;

		// Process @XlsSheet
		if (o.isAnnotationPresent(XlsSheet.class)) {

			XlsSheet xlsSheet = o.getAnnotation(XlsSheet.class);

			// add here the annotations attributes
			assertNotNull(xlsSheet.freezePane());
			assertEquals(xlsSheet.freezePane().colSplit(), 0);
			assertEquals(xlsSheet.freezePane().rowSplit(), 4);
			assertEquals(xlsSheet.freezePane().topRow(), 1);
			assertEquals(xlsSheet.freezePane().leftMostColumn(), 1);

		}
	}

}
