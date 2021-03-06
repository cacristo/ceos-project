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
import net.ceos.project.poi.annotated.definition.PropagationType;

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
	public void checkDefaultConfiguration() {
		Class<XMenFactory.DefaultConfig> oC = XMenFactory.DefaultConfig.class;
		// Process @XlsSheet
		if (oC.isAnnotationPresent(XlsSheet.class)) {

			XlsSheet xlsSheet = (XlsSheet) oC.getAnnotation(XlsSheet.class);
			assertNotNull(xlsSheet.freezePane());
			assertEquals(xlsSheet.freezePane().topRow(), 0);
			assertEquals(xlsSheet.freezePane().leftMostColumn(), 0);
		}
	}

	/**
	 * Test initialization of the basic freeze pane according the
	 * {@link PropagationType} horizontal
	 */
	@Test
	public void checkFreezePaneHorizontal() {
		Class<XMenFactory.ProfessorX> o = XMenFactory.ProfessorX.class;
		// Process @XlsSheet
		if (o.isAnnotationPresent(XlsSheet.class)) {

			XlsSheet xlsSheet = (XlsSheet) o.getAnnotation(XlsSheet.class);
			assertNotNull(xlsSheet.freezePane());
			assertEquals(xlsSheet.freezePane().colSplit(), 0);
			assertEquals(xlsSheet.freezePane().rowSplit(), 4);
		}
	}

	/**
	 * Test initialization of the advanced freeze pane according the
	 * {@link PropagationType} horizontal
	 */
	@Test
	public void checkFreezePaneHorizontalAdvanced() {
		Class<XMenFactory.Cyclops> o = XMenFactory.Cyclops.class;
		// Process @XlsSheet
		if (o.isAnnotationPresent(XlsSheet.class)) {

			XlsSheet xlsSheet = o.getAnnotation(XlsSheet.class);
			assertNotNull(xlsSheet.freezePane());
			assertEquals(xlsSheet.freezePane().colSplit(), 0);
			assertEquals(xlsSheet.freezePane().rowSplit(), 4);
			assertEquals(xlsSheet.freezePane().topRow(), 1);
			assertEquals(xlsSheet.freezePane().leftMostColumn(), 1);
		}
	}

	/**
	 * Test initialization of the basic freeze pane according the
	 * {@link PropagationType} vertical
	 */
	@Test
	public void checkFreezePaneVertical() {
		Class<XMenFactory.IronMan> o = XMenFactory.IronMan.class;
		// Process @XlsSheet
		if (o.isAnnotationPresent(XlsSheet.class)) {

			XlsSheet xlsSheet = (XlsSheet) o.getAnnotation(XlsSheet.class);
			assertNotNull(xlsSheet.freezePane());
			assertEquals(xlsSheet.freezePane().colSplit(), 2);
			assertEquals(xlsSheet.freezePane().rowSplit(), 0);
		}
	}

	/**
	 * Test initialization of the advanced freeze pane according the
	 * {@link PropagationType} vertical
	 */
	@Test
	public void checkFreezePaneVerticalAdvanced() {
		Class<XMenFactory.Thor> o = XMenFactory.Thor.class;
		// Process @XlsSheet
		if (o.isAnnotationPresent(XlsSheet.class)) {

			XlsSheet xlsSheet = o.getAnnotation(XlsSheet.class);
			assertNotNull(xlsSheet.freezePane());
			assertEquals(xlsSheet.freezePane().colSplit(), 3);
			assertEquals(xlsSheet.freezePane().rowSplit(), 0);
			assertEquals(xlsSheet.freezePane().topRow(), 1);
			assertEquals(xlsSheet.freezePane().leftMostColumn(), 1);
		}
	}

	/**
	 * Test initialization of the freeze pane according the
	 * {@link PropagationType} horizontal split by row and column.
	 */
	@Test
	public void checkFreezePaneHorizontalSplitRowColumn() {
		Class<XMenFactory.SpiderMan> o = XMenFactory.SpiderMan.class;
		// Process @XlsSheet
		if (o.isAnnotationPresent(XlsSheet.class)) {

			XlsSheet xlsSheet = (XlsSheet) o.getAnnotation(XlsSheet.class);
			assertNotNull(xlsSheet.freezePane());
			assertEquals(xlsSheet.freezePane().colSplit(), 3);
			assertEquals(xlsSheet.freezePane().rowSplit(), 2);
		}
	}

}
