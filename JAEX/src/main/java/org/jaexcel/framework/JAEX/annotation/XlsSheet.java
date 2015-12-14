package org.jaexcel.framework.JAEX.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.jaexcel.framework.JAEX.definition.CascadeType;
import org.jaexcel.framework.JAEX.definition.PropagationType;

/**
 * Annotation responsible to manage the basic Sheet configuration.
 * 
 * @author CristoAbreu
 */
@Target(TYPE)
@Retention(RUNTIME)
public @interface XlsSheet {

	// FIXME pass the attribute at @XlsConfiguration to this annotation

	/**
	 * Define the sheet title.
	 * 
	 * @return the title
	 */
	String title();

	/**
	 * Define the row start position.
	 * 
	 * @return by default is 1.
	 */
	int startRow() default 1;

	/**
	 * Define the cell start position.
	 * 
	 * @return by default is 1.
	 */
	int startCell() default 1;

	/**
	 * Define the propagation type when writing/reading at the Excel.
	 * 
	 * @return the {@link PropagationType}, by default is PROPAGATION_HORIZONTAL
	 */
	PropagationType propagation() default PropagationType.PROPAGATION_HORIZONTAL;

	/**
	 * Define the cascade level.
	 * 
	 * @return the {@link CascadeType}, by default is CASCADE_BASE
	 */
	CascadeType cascadeLevel() default CascadeType.CASCADE_BASE;
}
