package org.jaexcel.framework.JAEX.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({ TYPE, FIELD })
@Retention(RUNTIME)
public @interface XlsDecorator {

	/**
	 * Define if the font is bold or not at the element.
	 * 
	 * @return
	 */
	boolean fontBold() default false;

	/**
	 * Define if the font is italic or not at the element.
	 * 
	 * @return
	 */
	boolean fontItalic() default false;

	/**
	 * Define the alignment of the element.
	 * 
	 * @return
	 */
	short alignment() default 0;

	/**
	 * Define the vertical alignment of the element.
	 * 
	 * @return
	 */
	short verticalAlignment() default 0;

	/**
	 * Define the border of the element.
	 * 
	 * @return
	 */
	short border() default 0;

	/**
	 * Define the left border of the element.
	 * 
	 * @return
	 */
	short borderLeft() default 0;

	/**
	 * Define the right border of the element.
	 * 
	 * @return
	 */
	short borderRight() default 0;

	/**
	 * Define the top border of the element.
	 * 
	 * @return
	 */
	short borderTop() default 0;

	/**
	 * Define the bottom border of the element.
	 * 
	 * @return
	 */
	short borderBottom() default 0;

	/**
	 * Define the background color of the element.
	 * 
	 * @return
	 */
	short backgroundColor() default 0;

	/**
	 * Define the foreground color of the element.
	 * 
	 * @return
	 */
	short foregroundColor() default 0;

	/**
	 * Define if the text is wrapped or not at the element.
	 * 
	 * @return
	 */
	boolean wrapText() default false;
}
