package net.ceos.project.poi.annotated.annotation;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import org.testng.annotations.Test;

import net.ceos.project.poi.annotated.bean.XMenFactory;

/**
 * Test the annotation {@link XlsGroupElement}, {@link XlsGroupColumn} &
 * {@link XlsGroupRow}
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public class XlsGroupElementTest {

	/**
	 * Test default configuration.
	 */
	@Test
	public void testDefaultConfiguration() {
		Class<XMenFactory.DefaultConfig> oC = XMenFactory.DefaultConfig.class;

		// Process @XlsSheet
		if (oC.isAnnotationPresent(XlsSheet.class)) {

			XlsSheet xlsSheet = (XlsSheet) oC.getAnnotation(XlsSheet.class);

			XlsGroupColumn[] column = xlsSheet.groupElement().groupColumns();
			assertNotNull(column);
			assertEquals(column[0].fromColumn(), 0);
			assertEquals(column[0].toColumn(), 0);

			XlsGroupRow[] rows = xlsSheet.groupElement().groupRows();
			assertNotNull(rows);
			assertEquals(rows[0].fromRow(), 0);
			assertEquals(rows[0].toRow(), 0);
		}
	}

	@Test
	public void testGroupElementByColumns() {
		Class<XMenFactory.Wolverine> o = XMenFactory.Wolverine.class;

		// Process @XlsDecorator
		if (o.isAnnotationPresent(XlsSheet.class)) {

			XlsSheet xlsSheet = o.getAnnotation(XlsSheet.class);

			XlsGroupColumn[] column = xlsSheet.groupElement().groupColumns();
			assertNotNull(column);
			assertEquals(column[0].fromColumn(), 1);
			assertEquals(column[0].toColumn(), 3);
		}
	}

	@Test
	public void testGroupElementByRows() {
		Class<XMenFactory.IronMan> o = XMenFactory.IronMan.class;

		// Process @XlsDecorator
		if (o.isAnnotationPresent(XlsSheet.class)) {

			XlsSheet xlsSheet = o.getAnnotation(XlsSheet.class);

			XlsGroupRow[] rows = xlsSheet.groupElement().groupRows();
			assertNotNull(rows);
			assertEquals(rows[0].fromRow(), 1);
			assertEquals(rows[0].toRow(), 2);
		}
	}
}
