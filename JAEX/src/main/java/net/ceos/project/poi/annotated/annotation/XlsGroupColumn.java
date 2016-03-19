package net.ceos.project.poi.annotated.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * This annotation create an outline for the provided column range.<br>
 * In another words, tie a range of columns together and give us the possibility to
 * collapse or expand the group.
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
@Target(TYPE)
@Retention(RUNTIME)
public @interface XlsGroupColumn {

	/**
	 * Define the beginning of the column range.
	 * 
	 * @return the start column
	 */
	int fromColumn();

	/**
	 * Define the end of the column range.
	 * 
	 * @return the end column
	 */
	int toColumn();
}
