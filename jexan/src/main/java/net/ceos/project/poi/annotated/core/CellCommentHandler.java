package net.ceos.project.poi.annotated.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;

import net.ceos.project.poi.annotated.definition.ExceptionMessage;
import net.ceos.project.poi.annotated.exception.CustomizedRulesException;

/**
 * This class manage the comment to apply to one cell.
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
	 * @param o
	 *            the object
	 * @param c
	 *            the {@link Cell}
	 * @throws CustomizedRulesException
	 */
	protected static void applyComment(final XConfigCriteria configCriteria, final Object o, final Cell c)
			throws CustomizedRulesException {
		if (StringUtils.isNotBlank(configCriteria.getElement().comment())) {
			// apply the comment
			try {
				CellStyleHandler.applyComment(configCriteria,
						(Boolean) applyCommentRules(o, configCriteria.getElement().commentRules()), c);
			} catch (Exception e) {
				throw new CustomizedRulesException(
						ExceptionMessage.CustomizedRulesException_NoSuchCommentMethod.getMessage(), e);
			}
		}
	}

	/**
	 * Apply a explicit formula value at the Cell.
	 * 
	 * @param o
	 *            the object
	 * @param f
	 *            the field
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private static Object applyCommentRules(final Object o, final String method)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		if (StringUtils.isNotBlank(method)) {
			@SuppressWarnings("rawtypes")
			Class[] argTypes = {};

			Method m = o.getClass().getDeclaredMethod(method, argTypes);

			return m.invoke(o, (Object[]) null);
		}
		return true;
	}
}
