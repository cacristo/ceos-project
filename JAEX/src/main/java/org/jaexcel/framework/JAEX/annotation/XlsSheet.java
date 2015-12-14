package org.jaexcel.framework.JAEX.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.jaexcel.framework.JAEX.definition.PropagationType;

//@Target({ TYPE })
@Target(TYPE)
@Retention(RUNTIME)
public @interface XlsSheet {

	/**
	 * Define the sheet title.
	 * 
	 * @return
	 */
	String title();

	/**
	 * Define the propagation type when writing at he Excel.
	 * 
	 * @return the {@link PropagationType}, by default is PROPAGATION_HORIZONTAL
	 */
	PropagationType propagation() default PropagationType.PROPAGATION_HORIZONTAL;

	int startRow() default 1;

	int startCell() default 1;
}
