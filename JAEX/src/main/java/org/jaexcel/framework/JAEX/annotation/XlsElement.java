package org.jaexcel.framework.JAEX.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

//@Target({ FIELD })
@Target(FIELD)
@Retention(RUNTIME)
public @interface XlsElement {
	
	/**
	 * Define the cell title.
	 * 
	 * @return
	 */
	String title();

	// org.apache.poi.ss.usermodel.Comment
	String comment() default "";

	// FIXME org.apache.poi.ss.usermodel.CellStyle;
	int cellStyle() default 0;

	int position();

	String decorator() default "";
}
