package org.jaexcel.framework.JAEX.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

//@Target({ FIELD })
@Target(FIELD)
@Retention(RUNTIME)
public @interface XlsMasterHeader {
	/**
	 * Define the master cell title.
	 * 
	 * @return
	 */
	String title();

	int startX() default 0;

	int endX() default 0;

	int startY() default 0;

	int endY() default 0;

	// org.apache.poi.ss.util.CellRangeAddress;
	int mergedRegion() default 0;

	int decorator() default 0;

	int border() default 0;

}
