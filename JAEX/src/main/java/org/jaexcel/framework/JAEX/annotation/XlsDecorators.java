package org.jaexcel.framework.JAEX.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * This annotation define the array of decorators. <br>
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
@Target(TYPE)
@Retention(RUNTIME)
public @interface XlsDecorators {

	/**
	 * Define the array of decorators to be used when marshal the object into
	 * Excel.
	 * 
	 * @return the array of {@link XlsDecorator}
	 */
	XlsDecorator[] values();
}
