package org.jaexcel.framework.JAEX.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Annotation responsible to manage the master header at the Sheet.
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
@Target(FIELD)
@Retention(RUNTIME)
public @interface XlsMasterHeader {
	/**
	 * Define the master element title.
	 * 
	 * @return the title of the element
	 */
	String title();

	/**
	 * Define the start cell position.<br>
	 * By default is 0.
	 * 
	 * @return the start cell position
	 */
	int startX() default 0;

	/**
	 * Define the end cell position.<br>
	 * By default is 0.
	 * 
	 * @return the end cell position
	 */
	int endX() default 0;

	/**
	 * Define the start row position.<br>
	 * By default is 0.
	 * 
	 * @return the start row position
	 */
	int startY() default 0;

	/**
	 * Define the end row position.<br>
	 * By default is 0.
	 * 
	 * @return the end row position
	 */
	int endY() default 0;

}
