package org.jaexcel.framework.JAEX.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Annotation responsible to manage the fields attributes.
 * 
 * @author CristoAbreu
 */
@Target(FIELD)
@Retention(RUNTIME)
public @interface XlsElement {

	/**
	 * Define the title of the element.
	 * 
	 * @return
	 */
	String title();

	/**
	 * The position of the element at the Sheet.
	 * 
	 * @return the position
	 */
	int position();

	/**
	 * Define the commentary of the element.
	 * 
	 * @return by default is empty
	 */
	String comment() default "";

	/**
	 * The decorator mask to apply to the element.
	 * 
	 * @return by default is empty
	 */
	String decorator() default "";
}
