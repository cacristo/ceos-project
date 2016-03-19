package net.ceos.project.poi.annotated.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target(TYPE)
@Retention(RUNTIME)
public @interface XlsFreezePane {
	
	int colSplit();
	
	int rowSplit();
	
	int leftMostColumn() default -1;
	
	int topRow() default -1;

}
