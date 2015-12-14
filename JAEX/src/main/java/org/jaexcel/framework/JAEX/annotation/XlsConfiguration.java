package org.jaexcel.framework.JAEX.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.jaexcel.framework.JAEX.definition.ExtensionFileType;

/**
 * 
 * @author CristoAbreu
 */
@Target(TYPE)
@Retention(RUNTIME)
public @interface XlsConfiguration {

	/**
	 * Define the file name.
	 * 
	 * @return the name of the file
	 */
	String nameFile();
	
	/**
	 * Define the extension file.
	 * 
	 * @return the {@link ExtensionFileType}, by default is XLS
	 */
	ExtensionFileType extensionFile() default ExtensionFileType.XLS;
}
