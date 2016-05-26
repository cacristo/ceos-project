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

import org.testng.annotations.Test;

import net.ceos.project.poi.annotated.bean.XMenFactory;

/**
 * Test the annotation {@link XlsDecorator}
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public class XlsDecoratorTest {

	/**
	 * Test default configuration.
	 */
	@Test
	public void testDefaultConfiguration() {
		Class<XMenFactory.DefaultConfig> o = XMenFactory.DefaultConfig.class;

		// Process @XlsDecorator
		if (o.isAnnotationPresent(XlsDecorator.class)) {

			XlsDecorator xlsDecorator = (XlsDecorator) o.getAnnotation(XlsDecorator.class);

			// add here the annotations attributes
			assertEquals(xlsDecorator.fontName(), "Arial");
			assertEquals(xlsDecorator.fontSize(), 10);
			assertEquals(xlsDecorator.fontColor(), 0);
			assertEquals(xlsDecorator.fontBold(), false);
			assertEquals(xlsDecorator.fontItalic(), false);
			assertEquals(xlsDecorator.fontUnderline(), 0);
			assertEquals(xlsDecorator.alignment(), 0);
			assertEquals(xlsDecorator.verticalAlignment(), 0);
			assertEquals(xlsDecorator.border(), 0);
			assertEquals(xlsDecorator.borderLeft(), 0);
			assertEquals(xlsDecorator.borderRight(), 0);
			assertEquals(xlsDecorator.borderTop(), 0);
			assertEquals(xlsDecorator.borderBottom(), 0);
			assertEquals(xlsDecorator.backgroundColor(), 0);
			assertEquals(xlsDecorator.foregroundColor(), 0);
			assertEquals(xlsDecorator.wrapText(), false);

		}
	}
}
