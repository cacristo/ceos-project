package net.ceos.project.poi.annotated.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import net.ceos.project.poi.annotated.definition.ExtensionFileType;

/**
 * This annotation define all the attributes related to the Excel definitions.
 * <br>
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
@Target(TYPE)
@Retention(RUNTIME)
public @interface XlsConfiguration {

	/**
	 * Define the file name of the Excel.
	 * 
	 * @return the name of the file
	 */
	String nameFile();

	/**
	 * Define the Excel extension file.<br>
	 * By default is {@value = ExtensionFileType.XLS}.
	 * 
	 * @return the {@link ExtensionFileType}
	 */
	ExtensionFileType extensionFile() default ExtensionFileType.XLS;
}
