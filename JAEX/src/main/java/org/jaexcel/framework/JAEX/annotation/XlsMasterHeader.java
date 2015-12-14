package org.jaexcel.framework.JAEX.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Annotation responsible to manage the master header at the Sheet.
 * 
 * @author CristoAbreu
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
	 * Define the start cell position.
	 * 
	 * @return by default is 0
	 */
	int startX() default 0;

	/**
	 * Define the end cell position.
	 * 
	 * @return by default is 0
	 */
	int endX() default 0;

	/**
	 * Define the start row position.
	 * 
	 * @return by default is 0
	 */
	int startY() default 0;

	/**
	 * Define the end row position.
	 * 
	 * @return by default is 0
	 */
	int endY() default 0;

}
