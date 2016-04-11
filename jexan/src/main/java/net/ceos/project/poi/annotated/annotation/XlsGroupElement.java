package net.ceos.project.poi.annotated.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * This annotation is responsible for grouping, columns or rows, according the
 * column range or row range respectively.<br>
 * 
 * To manage the group of columns use {@link XlsGroupColumn} for each group<br>
 * To manage the group of rows use {@link XlsGroupRow} for each group<br>
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
@Target(TYPE)
@Retention(RUNTIME)
public @interface XlsGroupElement {

	/**
	 * Define the array of group columns to be used when marshal the object into
	 * Excel.
	 * 
	 * @return the array of {@link XlsGroupColumn}
	 */
	XlsGroupColumn[] groupColumns();

	/**
	 * Define the array of group rows to be used when marshal the object into
	 * Excel.
	 * 
	 * @return the array of {@link XlsGroupRow}
	 */
	XlsGroupRow[] groupRows();

}
