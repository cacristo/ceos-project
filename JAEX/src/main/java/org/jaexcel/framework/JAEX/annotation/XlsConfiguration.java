package org.jaexcel.framework.JAEX.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.jaexcel.framework.JAEX.definition.CascadeType;
import org.jaexcel.framework.JAEX.definition.ExtensionFileType;

//@Target({ TYPE })
@Target(TYPE)
@Retention(RUNTIME)
public @interface XlsConfiguration {

	String nameFile();
	
	ExtensionFileType extensionFile() default ExtensionFileType.XLS;

	CascadeType cascadeLevel() default CascadeType.CASCADE_BASE;
}
