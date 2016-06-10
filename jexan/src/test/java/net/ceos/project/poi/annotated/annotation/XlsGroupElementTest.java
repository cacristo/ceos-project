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
package net.ceos.project.poi.annotated.annotation;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import org.testng.annotations.Test;

import net.ceos.project.poi.annotated.bean.factory.XMenFactory;

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
	public void checkDefaultConfiguration() {
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

	/**
	 * Test initialization of the group elements by column with specific value.
	 */
	@Test
	public void checkGroupElementByColumns() {
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

	/**
	 * Test initialization of the group elements by row with specific value.
	 */
	@Test
	public void checkGroupElementByRows() {
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
