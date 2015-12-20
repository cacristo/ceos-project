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

	/**
	 * Format the field visually and the original value will remains at the
	 * element.
	 * 
	 * @return by default is empty
	 */
	String formatMask() default "";

	/**
	 * Transform the field with the mask applied and we will lost the original
	 * value at the element.
	 * 
	 * @return by default is empty
	 */
	String transformMask() default "";
	
	/**
	 * Define the decimal scale of the Double / BigDecimal objects.
	 * 
	 * @return the decimal scale to apply
	 */
	// FIXME int decimalScale() default 2;
}
