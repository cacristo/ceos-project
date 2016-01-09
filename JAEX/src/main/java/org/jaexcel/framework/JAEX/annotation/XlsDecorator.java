package org.jaexcel.framework.JAEX.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

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
	 * Define the font name at the element.<br>
	 * By default is Arial.
	 * 
	 * @return the font name
	 */
	String fontName() default "Arial";

	/**
	 * Define the font size at the element.<br>
	 * By default is 10.
	 * 
	 * @return the size of the font
	 */
	short fontSize() default 10;

	/**
	 * Define the font color at the element.<br>
	 * By default is 0 ('Black').
	 * 
	 * @return the color of the font
	 */
	short fontColor() default 0;

	/**
	 * Define if the font is bold or not at the element.<br>
	 * By default is false.
	 * 
	 * @return true if the font is bold, otherwise false
	 */
	boolean fontBold() default false;

	/**
	 * Define if the font is italic or not at the element.<br>
	 * By default is false.
	 * 
	 * @return true if the font is italic, otherwise false
	 */
	boolean fontItalic() default false;

	/**
	 * Define the font underline at the element.<br>
	 * By default is 0 (default value equivalent to
	 * FontUnderline.NONE.getByteValue()).
	 * 
	 * @return the type of the font underline
	 */
	byte fontUnderline() default 0;

	/**
	 * Define the alignment of the element.<br>
	 * By default is 0.
	 * 
	 * @return the alignment
	 */
	short alignment() default 0;

	/**
	 * Define the vertical alignment of the element.<br>
	 * By default is 0.
	 * 
	 * @return the vertical alignment
	 */
	short verticalAlignment() default 0;

	/**
	 * Define the border of the element.<br>
	 * By default is 0 (default value equivalent to CellStyle.BORDER_NONE).
	 * 
	 * @return the type of the border
	 */
	short border() default 0;

	/**
	 * Define the left border of the element.<br>
	 * By default is 0 (default value equivalent to CellStyle.BORDER_NONE).
	 * 
	 * @return the type of the left border
	 */
	short borderLeft() default 0;

	/**
	 * Define the right border of the element.<br>
	 * By default is 0 (default value equivalent to CellStyle.BORDER_NONE).
	 * 
	 * @return the type of the right border
	 */
	short borderRight() default 0;

	/**
	 * Define the top border of the element.<br>
	 * By default is 0 (default value equivalent to CellStyle.BORDER_NONE).
	 * 
	 * @return the type of the top border
	 */
	short borderTop() default 0;

	/**
	 * Define the bottom border of the element.<br>
	 * By default is 0 (default value equivalent to CellStyle.BORDER_NONE).
	 * 
	 * @return the type of the bottom border
	 */
	short borderBottom() default 0;

	/**
	 * Define the background color of the element.<br>
	 * By default is 9.
	 * 
	 * @return the background color
	 */
	short backgroundColor() default 9;

	/**
	 * Define the foreground color of the element.<br>
	 * By default is 9.
	 * 
	 * @return the foreground color
	 */
	short foregroundColor() default 9;

	/**
	 * Define if the text is wrapped or not at the element.<br>
	 * By default is false.
	 * 
	 * @return true if the text is wrapped, otherwise false
	 */
	boolean wrapText() default false;
}
