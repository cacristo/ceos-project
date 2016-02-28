package net.ceos.project.poi.annotated.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import net.ceos.project.poi.annotated.core.CellStyleUtils;

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
	String fontName() default CellStyleUtils.FONT_NAME;

	/**
	 * Define the font size at the element.<br>
	 * By default is 10.
	 * 
	 * @return the size of the font
	 */
	short fontSize() default CellStyleUtils.FONT_SIZE;

	/**
	 * Define the font color at the element.<br>
	 * By default is 0 ('Black').
	 * 
	 * @return the color of the font
	 */
	short fontColor() default CellStyleUtils.FONT_COLOR;

	/**
	 * Define if the font is bold or not at the element.<br>
	 * By default is false.
	 * 
	 * @return true if the font is bold, otherwise false
	 */
	boolean fontBold() default CellStyleUtils.FONT_BOLD;

	/**
	 * Define if the font is italic or not at the element.<br>
	 * By default is false.
	 * 
	 * @return true if the font is italic, otherwise false
	 */
	boolean fontItalic() default CellStyleUtils.FONT_ITALIC;

	/**
	 * Define the font underline at the element.<br>
	 * By default is 0 (default value equivalent to
	 * FontUnderline.NONE.getByteValue()).
	 * 
	 * @return the type of the font underline
	 */
	byte fontUnderline() default CellStyleUtils.FONT_UNDERLINE;

	/**
	 * Define the alignment of the element.<br>
	 * By default is 0.
	 * 
	 * @return the alignment
	 */
	short alignment() default CellStyleUtils.FONT_ALIGNMENT;

	/**
	 * Define the vertical alignment of the element.<br>
	 * By default is 0.
	 * 
	 * @return the vertical alignment
	 */
	short verticalAlignment() default CellStyleUtils.FONT_VERTICAL_ALIGNMENT;

	/**
	 * Define the border of the element.<br>
	 * By default is 0 (default value equivalent to CellStyle.BORDER_NONE).
	 * 
	 * @return the type of the border
	 */
	short border() default CellStyleUtils.CELL_BORDER;

	/**
	 * Define the left border of the element.<br>
	 * By default is 0 (default value equivalent to CellStyle.BORDER_NONE).
	 * 
	 * @return the type of the left border
	 */
	short borderLeft() default CellStyleUtils.CELL_BORDER;

	/**
	 * Define the right border of the element.<br>
	 * By default is 0 (default value equivalent to CellStyle.BORDER_NONE).
	 * 
	 * @return the type of the right border
	 */
	short borderRight() default CellStyleUtils.CELL_BORDER;

	/**
	 * Define the top border of the element.<br>
	 * By default is 0 (default value equivalent to CellStyle.BORDER_NONE).
	 * 
	 * @return the type of the top border
	 */
	short borderTop() default CellStyleUtils.CELL_BORDER;

	/**
	 * Define the bottom border of the element.<br>
	 * By default is 0 (default value equivalent to CellStyle.BORDER_NONE).
	 * 
	 * @return the type of the bottom border
	 */
	short borderBottom() default CellStyleUtils.CELL_BORDER;

	/**
	 * Define the background color of the element.<br>
	 * By default is 9.
	 * 
	 * @return the background color
	 */
	short backgroundColor() default CellStyleUtils.CELL_BACKGROUND_COLOR;

	/**
	 * Define the foreground color of the element.<br>
	 * By default is 9.
	 * 
	 * @return the foreground color
	 */
	short foregroundColor() default CellStyleUtils.CELL_BACKGROUND_COLOR;

	/**
	 * Define if the text is wrapped or not at the element.<br>
	 * By default is false.
	 * 
	 * @return true if the text is wrapped, otherwise false
	 */
	boolean wrapText() default CellStyleUtils.CELL_WRAP_TEXT;
}
