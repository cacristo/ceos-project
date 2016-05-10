package net.ceos.project.poi.annotated.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import net.ceos.project.poi.annotated.definition.CascadeType;
import net.ceos.project.poi.annotated.definition.PropagationType;

/**
 * Annotation responsible to manage the basic Sheet configuration.
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
@Target(TYPE)
@Retention(RUNTIME)
public @interface XlsSheet {

	/**
	 * Define the sheet title.
	 * 
	 * @return the title
	 */
	String title();

	/**
	 * Define the row start position.<br>
	 * By default is 1.
	 * 
	 * @return the initial row position
	 */
	int startRow() default 1;

	/**
	 * Define the cell start position.<br>
	 * By default is 1.
	 * 
	 * @return the initial cell position
	 */
	int startCell() default 1;

	/**
	 * Define the coordinates of the freeze pane to apply at the sheet
	 * 
	 * @return the freeze pane to apply
	 */
	XlsFreezePane freezePane() default @XlsFreezePane(colSplit = 0, rowSplit = 0)
	;

	/**
	 * Define a group, columns or rows, according the column range or row range
	 * respectively
	 * 
	 * @return the group of elements to apply
	 */
	XlsGroupElement groupElement() default @XlsGroupElement(groupColumns = {
			@XlsGroupColumn(fromColumn = 0, toColumn = 0) }, groupRows = { @XlsGroupRow(fromRow = 0, toRow = 0) })
	;

	/**
	 * Define the propagation type when writing/reading at the Excel.<br>
	 * By default is PROPAGATION_HORIZONTAL.
	 * 
	 * @return the {@link PropagationType}
	 */
	PropagationType propagation() default PropagationType.PROPAGATION_HORIZONTAL;

	/**
	 * Define the cascade level.<br>
	 * By default is CASCADE_BASE.
	 * 
	 * @return the {@link CascadeType}
	 */
	CascadeType cascadeLevel() default CascadeType.CASCADE_BASE;
}
