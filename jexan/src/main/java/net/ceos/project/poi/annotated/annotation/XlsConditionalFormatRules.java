/**
 * Copyright 2016 Carlos CRISTO ABREU
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.ceos.project.poi.annotated.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * This annotation create an conditional format rule.
 * <p>
 * In another words, define a Conditional Formatting rule, which triggers
 * formatting when cell's value is <i>'operator'</i> than <i>'first formula'</i>
 * (and <i>'second formula'</i>, if necessary) and applies patternFormatting
 * defined.
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
@Target(TYPE)
@Retention(RUNTIME)
public @interface XlsConditionalFormatRules {

	/**
	 * Define the operator to be used by the rule.
	 * <p>
	 * Example ComparisonOperator.BETWEEN
	 * 
	 * @return the operator
	 */
	byte operator();

	/**
	 * Define the first formula to apply.
	 * 
	 * @return the the formula
	 */
	String formula1();

	/**
	 * Define the second formula to apply.
	 * 
	 * @return the formula
	 */
	String formula2() default "";

}
