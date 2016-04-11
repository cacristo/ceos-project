package net.ceos.project.poi.annotated.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * This annotation create a split (freeze pane). Be aware if any existing freeze
 * pane or split pane is overwritten.<br>
 * 
 * If both colSplit & rowSplit are zero then the existing freeze pane is
 * removed.<br>
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
@Target(TYPE)
@Retention(RUNTIME)
public @interface XlsFreezePane {

	/**
	 * Define the horizontal position of split.
	 * 
	 * @return the horizontal position
	 */
	int colSplit();

	/**
	 * Define the vertical position of split.
	 * 
	 * @return the vertical position
	 */
	int rowSplit();

	/**
	 * Define the left column visible in right pane.
	 * 
	 * @return the left column visible (by default 0)
	 */
	int leftMostColumn() default 0;

	/**
	 * Define the top row visible in bottom pane.
	 * 
	 * @return the top row visible (by default 0)
	 */
	int topRow() default 0;

}
