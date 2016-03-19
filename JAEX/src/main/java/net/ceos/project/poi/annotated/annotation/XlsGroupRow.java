package net.ceos.project.poi.annotated.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * This annotation create an outline for the provided row range.<br>
 * In another words, tie a range of rows together and give us the possibility to
 * collapse or expand the group.
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
@Target(TYPE)
@Retention(RUNTIME)
public @interface XlsGroupRow {

	/**
	 * Define the beginning of the row range.
	 * 
	 * @return the start row
	 */
	int fromRow();

	/**
	 * Define the end of the row range.
	 * 
	 * @return the end row
	 */
	int toRow();

}
