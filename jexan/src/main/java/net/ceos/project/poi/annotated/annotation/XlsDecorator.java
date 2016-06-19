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

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import net.ceos.project.poi.annotated.core.CellStyleHandler;

/**
 * This annotation define all the attributes related to one cell decoration.
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
@Target({ TYPE, FIELD })
@Retention(RUNTIME)
public @interface XlsDecorator {

	/**
	 * Define the decorator name to be related at the element.
	 * 
	 * @return the decorator name
	 */
	String decoratorName();

	/**
	 * Define the font name at the element.
	 * <p>
	 * By default is Arial.
	 * 
	 * @return the font name
	 */
	String fontName() default CellStyleHandler.FONT_NAME;

	/**
	 * Define the font size at the element.
	 * <p>
	 * By default is 10.
	 * 
	 * @return the size of the font
	 */
	short fontSize() default CellStyleHandler.FONT_SIZE;

	/**
	 * Define the font color at the element.
	 * <p>
	 * By default is 0 ('Black').
	 * 
	 * @return the color of the font
	 */
	short fontColor() default CellStyleHandler.FONT_COLOR;

	/**
	 * Define if the font is bold or not at the element.
	 * <p>
	 * By default is false.
	 * 
	 * @return true if the font is bold, otherwise false
	 */
	boolean fontBold() default CellStyleHandler.FONT_BOLD;

	/**
	 * Define if the font is italic or not at the element.
	 * <p>
	 * By default is false.
	 * 
	 * @return true if the font is italic, otherwise false
	 */
	boolean fontItalic() default CellStyleHandler.FONT_ITALIC;

	/**
	 * Define the font underline at the element.
	 * <p>
	 * By default is 0 (default value equivalent to
	 * FontUnderline.NONE.getByteValue()).
	 * 
	 * @return the type of the font underline
	 */
	byte fontUnderline() default CellStyleHandler.FONT_UNDERLINE;

	/**
	 * Define the alignment of the element.
	 * <p>
	 * By default is 0.
	 * 
	 * @return the alignment
	 */
	short alignment() default CellStyleHandler.FONT_ALIGNMENT;

	/**
	 * Define the vertical alignment of the element.
	 * <p>
	 * By default is 0.
	 * 
	 * @return the vertical alignment
	 */
	short verticalAlignment() default CellStyleHandler.FONT_VERTICAL_ALIGNMENT;

	/**
	 * Define the border of the element.
	 * <p>
	 * By default is 0 (default value equivalent to CellStyle.BORDER_NONE).
	 * 
	 * @return the type of the border
	 */
	short border() default CellStyleHandler.CELL_BORDER;

	/**
	 * Define the left border of the element.
	 * <p>
	 * By default is 0 (default value equivalent to CellStyle.BORDER_NONE).
	 * 
	 * @return the type of the left border
	 */
	short borderLeft() default CellStyleHandler.CELL_BORDER;

	/**
	 * Define the right border of the element.
	 * <p>
	 * By default is 0 (default value equivalent to CellStyle.BORDER_NONE).
	 * 
	 * @return the type of the right border
	 */
	short borderRight() default CellStyleHandler.CELL_BORDER;

	/**
	 * Define the top border of the element.
	 * <p>
	 * By default is 0 (default value equivalent to CellStyle.BORDER_NONE).
	 * 
	 * @return the type of the top border
	 */
	short borderTop() default CellStyleHandler.CELL_BORDER;

	/**
	 * Define the bottom border of the element.
	 * <p>
	 * By default is 0 (default value equivalent to CellStyle.BORDER_NONE).
	 * 
	 * @return the type of the bottom border
	 */
	short borderBottom() default CellStyleHandler.CELL_BORDER;

	/**
	 * Define the border color of the element.
	 * <p>
	 * By default is 0 (default value equivalent to CellStyle.BORDER_NONE).
	 * 
	 * @return the type of the border
	 */
	short borderColor() default 0;

	/**
	 * Define the left border color of the element.
	 * <p>
	 * By default is 0 (default value equivalent to CellStyle.BORDER_NONE).
	 * 
	 * @return the type of the left border
	 */
	short borderLeftColor() default 0;

	/**
	 * Define the right border color of the element.
	 * <p>
	 * By default is 0 (default value equivalent to CellStyle.BORDER_NONE).
	 * 
	 * @return the type of the right border
	 */
	short borderRightColor() default 0;

	/**
	 * Define the top border color of the element.
	 * <p>
	 * By default is 0 (default value equivalent to CellStyle.BORDER_NONE).
	 * 
	 * @return the type of the top border
	 */
	short borderTopColor() default 0;

	/**
	 * Define the bottom border color of the element.
	 * <p>
	 * By default is 0 (default value equivalent to CellStyle.BORDER_NONE).
	 * 
	 * @return the type of the bottom border
	 */
	short borderBottomColor() default 0;

	/**
	 * Define the background color of the element.
	 * <p>
	 * By default is 9.
	 * 
	 * @return the background color
	 */
	short backgroundColor() default CellStyleHandler.CELL_BACKGROUND_COLOR;

	/**
	 * Define the foreground color of the element.
	 * <p>
	 * By default is 9.
	 * 
	 * @return the foreground color
	 */
	short foregroundColor() default CellStyleHandler.CELL_BACKGROUND_COLOR;

	/**
	 * Define if the text is wrapped or not at the element.
	 * <p>
	 * By default is false.
	 * 
	 * @return true if the text is wrapped, otherwise false
	 */
	boolean wrapText() default CellStyleHandler.CELL_WRAP_TEXT;
}
