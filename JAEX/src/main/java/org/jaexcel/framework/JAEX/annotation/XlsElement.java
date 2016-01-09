package org.jaexcel.framework.JAEX.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Annotation responsible to manage the fields attributes. <br>
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
@Target(FIELD)
@Retention(RUNTIME)
public @interface XlsElement {

	/**
	 * Define the title of the element.
	 * 
	 * @return the header title
	 */
	String title();

	/**
	 * The position of the element at the Sheet.
	 * 
	 * @return the position
	 */
	int position();

	/**
	 * Define the commentary of the element.<br>
	 * By default is empty.
	 * 
	 * @return the commentary
	 */
	String comment() default "";

	/**
	 * The decorator to apply to the element.<br>
	 * By default is empty.
	 * 
	 * @return the decorator name
	 */
	String decorator() default "";

	/**
	 * Format the field visually and the original value will remains at the
	 * element.<br>
	 * By default is empty.
	 * 
	 * @return the format mask
	 */
	String formatMask() default "";

	/**
	 * Transform the field with the mask applied and we will lost the original
	 * value at the element.<br>
	 * By default is empty.
	 * 
	 * @return the transformation mask
	 */
	String transformMask() default "";

	/**
	 * Define the decimal scale of the Double / BigDecimal objects.
	 * 
	 * @return the decimal scale to apply
	 */
	// FIXME int decimalScale() default 2;

	/**
	 * Define if the cell is a formula.<br>
	 * By default is false.
	 * 
	 * @return true if formula, otherwise false
	 */
	boolean isFormula() default false;

	/**
	 * Define the formula to apply at the cell.<br>
	 * By default is empty.
	 * 
	 * @return the formula to apply
	 */
	String formula() default "";
}
