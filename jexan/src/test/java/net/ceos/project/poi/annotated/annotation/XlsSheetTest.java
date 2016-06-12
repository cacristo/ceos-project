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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import net.ceos.project.poi.annotated.bean.factory.XMenFactory;
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
	public void checkDefaultConfigurationAttibutes() {
		Class<XMenFactory.DefaultConfig> oC = XMenFactory.DefaultConfig.class;

		// Process @XlsSheet
		if (oC.isAnnotationPresent(XlsSheet.class)) {

			XlsSheet xlsSheet = (XlsSheet) oC.getAnnotation(XlsSheet.class);

			// add here the annotations attributes
			assertEquals(xlsSheet.startRow(), 1);
			assertEquals(xlsSheet.startCell(), 1);
			assertEquals(xlsSheet.tabColor(), -1);
			assertEquals(xlsSheet.freezePane().colSplit(), 0);
			assertEquals(xlsSheet.freezePane().rowSplit(), 0);
			assertEquals(xlsSheet.freezePane().leftMostColumn(), 0);
			assertEquals(xlsSheet.freezePane().topRow(), 0);
			XlsGroupColumn[] column = xlsSheet.groupElement().groupColumns();
			assertNotNull(column);
			assertEquals(column[0].fromColumn(), 0);
			assertEquals(column[0].toColumn(), 0);
			XlsGroupRow[] rows = xlsSheet.groupElement().groupRows();
			assertNotNull(rows);
			assertEquals(rows[0].fromRow(), 0);
			assertEquals(rows[0].toRow(), 0);
			assertEquals(xlsSheet.propagation(), PropagationType.PROPAGATION_HORIZONTAL);
			assertEquals(xlsSheet.cascadeLevel(), CascadeType.CASCADE_BASE);
		}
	}

	/**
	 * Test initialization of the title sheet attribute with specific value.
	 */
	@Test
	public void checkTitleAttibute() {
		Class<XMenFactory.DefaultConfig> oC = XMenFactory.DefaultConfig.class;
		// Process @XlsSheet
		if (oC.isAnnotationPresent(XlsSheet.class)) {

			XlsSheet xlsSheet = (XlsSheet) oC.getAnnotation(XlsSheet.class);
			assertEquals(xlsSheet.title(), "Default configuration sample");
		}
	}

	/**
	 * Test initialization of the propagation attribute with type
	 * PROPAGATION_HORIZONTAL.
	 */
	@Test
	public void checkPropagationAttibuteHorizontal() {
		Class<?> oC = XMenFactory.SpiderMan.class;
		// Process @XlsSheet
		if (oC.isAnnotationPresent(XlsSheet.class)) {

			XlsSheet xlsSheet = (XlsSheet) oC.getAnnotation(XlsSheet.class);
			assertEquals(xlsSheet.propagation(), PropagationType.PROPAGATION_HORIZONTAL);
		}
	}

	/**
	 * Test initialization of the propagation attribute with type
	 * PROPAGATION_VERTICAL.
	 */
	@Test
	public void checkPropagationAttibuteVertical() {
		Class<?> oC = XMenFactory.IronMan.class;
		// Process @XlsSheet
		if (oC.isAnnotationPresent(XlsSheet.class)) {

			XlsSheet xlsSheet = (XlsSheet) oC.getAnnotation(XlsSheet.class);
			assertEquals(xlsSheet.propagation(), PropagationType.PROPAGATION_VERTICAL);
		}
	}

	/**
	 * Test initialization of the cascade level attribute with type
	 * CASCADE_BASE.
	 */
	@Test
	public void checkCascadeLevelAttibuteBase() {
		Class<?> oC = XMenFactory.IronMan.class;
		// Process @XlsSheet
		if (oC.isAnnotationPresent(XlsSheet.class)) {

			XlsSheet xlsSheet = (XlsSheet) oC.getAnnotation(XlsSheet.class);
			assertEquals(xlsSheet.cascadeLevel(), CascadeType.CASCADE_BASE);
		}
	}

	/**
	 * Test initialization of the cascade level attribute with type CASCADE_L1.
	 */
	@Test
	public void checkCascadeLevelAttibuteL1() {
		Class<?> oC = XMenFactory.Wolverine.class;
		// Process @XlsSheet
		if (oC.isAnnotationPresent(XlsSheet.class)) {

			XlsSheet xlsSheet = (XlsSheet) oC.getAnnotation(XlsSheet.class);
			assertEquals(xlsSheet.cascadeLevel(), CascadeType.CASCADE_L1);
		}
	}

	/**
	 * Test initialization of the cascade level attribute with type CASCADE_L2.
	 */
	@Test
	public void checkCascadeLevelBaseAttibute() {
		Class<?> oC = XMenFactory.SpiderMan.class;
		// Process @XlsSheet
		if (oC.isAnnotationPresent(XlsSheet.class)) {

			XlsSheet xlsSheet = (XlsSheet) oC.getAnnotation(XlsSheet.class);
			assertEquals(xlsSheet.cascadeLevel(), CascadeType.CASCADE_L2);
		}
	}

	/**
	 * Test initialization of the cascade level attribute with type CASCADE_L3.
	 */
	@Test
	public void checkCascadeLevelAttibuteL3() {
		Class<?> oC = XMenFactory.Cyclops.class;
		// Process @XlsSheet
		if (oC.isAnnotationPresent(XlsSheet.class)) {

			XlsSheet xlsSheet = (XlsSheet) oC.getAnnotation(XlsSheet.class);
			assertEquals(xlsSheet.cascadeLevel(), CascadeType.CASCADE_L3);
		}
	}

	/**
	 * Test initialization of the cascade level attribute with type
	 * CASCADE_FULL.
	 */
	@Test
	public void checkCascadeLevelAttibuteFull() {
		Class<?> oC = XMenFactory.DoctorX.class;
		// Process @XlsSheet
		if (oC.isAnnotationPresent(XlsSheet.class)) {

			XlsSheet xlsSheet = (XlsSheet) oC.getAnnotation(XlsSheet.class);
			assertEquals(xlsSheet.cascadeLevel(), CascadeType.CASCADE_FULL);
		}
	}

}
