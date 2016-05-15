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
package net.ceos.project.poi.annotated.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;

import net.ceos.project.poi.annotated.definition.ExceptionMessage;
import net.ceos.project.poi.annotated.exception.CustomizedRulesException;

/**
 * This class manage the comment to apply to one cell.<br><br>
 * 
 * By default, all comments are enabled. If you need to show this comment only
 * in certain conditions you may need to define the rules where the comment
 * appears or not, and for that, you have to declare a method inside your
 * object.<br><br>
 * 
 * This method will manage for you, according the rules you declare, if the
 * comment will appear or not.<br><br>
 * 
 * At the attribute commentRules you have to indicate exactly the name of the
 * method you have created.<br><br>
 * 
 * Be aware, this method has to return a Boolean value and no other type.
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
class CellCommentHandler {

	private CellCommentHandler() {
		/* private constructor to hide the implicit public */
	}

	/**
	 * Apply the comment, if exists, to a cell.
	 * 
	 * @param configCriteria
	 *            the {@link XConfigCriteria}
	 * @param object
	 *            the object
	 * @param cell
	 *            the {@link Cell} will be applied the comment
	 * @throws CustomizedRulesException
	 */
	protected static void applyComment(final XConfigCriteria configCriteria, final Object object, final Cell cell)
			throws CustomizedRulesException {
		if (StringUtils.isNotBlank(configCriteria.getElement().comment())) {
			// apply the comment
			try {
				CellStyleHandler.applyComment(configCriteria,
						(Boolean) applyCommentRules(object, configCriteria.getElement().commentRules()), cell);
			} catch (Exception e) {
				throw new CustomizedRulesException(
						ExceptionMessage.CUSTOMIZEDRULES_NO_SUCH_COMMENT_METHOD.getMessage(), e);
			}
		}
	}

	/**
	 * Apply a explicit formula value at the Cell.
	 * 
	 * @param object
	 *            the object
	 * @param method
	 *            the method will be read
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private static Object applyCommentRules(final Object object, final String method)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		if (StringUtils.isNotBlank(method)) {
			@SuppressWarnings("rawtypes")
			Class[] argTypes = {};

			Method m = object.getClass().getDeclaredMethod(method, argTypes);

			return m.invoke(object, (Object[]) null);
		}
		return true;
	}
}
