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

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.util.CellReference;

import net.ceos.project.poi.annotated.definition.ExceptionMessage;
import net.ceos.project.poi.annotated.exception.ConfigurationException;

/**
 * This class converter the input formula to a valid excel formula.
 * <p>
 * Below you have listed the multiple converters available:
 * <ul>
 * <li>
 * <li>
 * </ul>
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
class CellFormulaConverter {

	private static final String[] splitCharacters = { ":", "," };

	private CellFormulaConverter() {
		/* private constructor to hide the implicit public */
	}

	/**
	 * =SUMA(D11,E11,F11) =SUMA(D11:F11)
	 */
	protected void convertFormulaToDynamicList() {

	}

	/**
	 * Calculate the range address based at template range address passed as
	 * parameter and also the propagation type.
	 * <p>
	 * Assuming we having 15 registry, start at 5
	 * <ul>
	 * <li>(input) => D => (output) => D5:D20
	 * <li>(input) => D:F => (output) => D5:F20
	 * <li>(input) => 7 => (output) => E7:S7
	 * <li>(input) => 7:8 => (output) => E7:S8
	 * <li>
	 * </ul>
	 * 
	 * @param configCriteria
	 *            the {@link XConfigCriteria}
	 * @param templateRangeAddress
	 *            the template range address
	 * @return the range address calculated
	 */
	protected static String calculateRangeAddressFromTemplate(final XConfigCriteria configCriteria,
			String templateRangeAddress) throws ConfigurationException {

		String start = "";
		String end = "";

		/* remove all possible separators */
		String rangeAddressWithoutSeparator = removeRangeSeparator(templateRangeAddress);

		/* validate propagation with template */
		validatePropagationWithRangeAddress(configCriteria, rangeAddressWithoutSeparator);

		/* check if specific range group */
		if (PredicateFactory.isReadyRangeAddress.test(rangeAddressWithoutSeparator)) {
			/* if true, return the range address */
			return templateRangeAddress;
		}

		/* split by separator all possible separators */
		String[] splitted = splitRangeSeparator(templateRangeAddress);

		if (splitted.length == 2) {
			/* range group */
			start = transformToStartRange(configCriteria, splitted[0]);
			end = transformToEndRange(configCriteria, splitted[1]);

		} else if (splitted.length == 1) {
			/* dynamic list */
			if (StringUtils.isNumeric(splitted[0])) {
				start = transformToRange(configCriteria, splitted[0], configCriteria.getStartCellInmutable());
				end = transformToRange(configCriteria, splitted[0], configCriteria.getLastCellIndex());
			} else if (StringUtils.isAlpha(splitted[0])) {
				start = transformToRange(configCriteria, splitted[0], configCriteria.getStartRowInmutable());
				end = transformToRange(configCriteria, splitted[0], configCriteria.getSheet().getLastRowNum() + 1);
			}
		}
		return start + ":" + end;
	}

	/**
	 * Calculate the range address based at baseRangeAddress passed as parameter
	 * and also the propagation type.
	 * 
	 * @param configCriteria
	 *            the {@link XConfigCriteria}
	 * @param baseRangeAddress
	 *            the base range address: letter if horizontal, number if
	 *            vertical
	 * @return the range address calculated
	 */
	protected static String calculateRangeAddress(final XConfigCriteria configCriteria, String baseRangeAddress) {
		String start;
		String end;
		if (PredicateFactory.isPropagationHorizontal.test(configCriteria.getPropagation())) {
			start = baseRangeAddress + configCriteria.getStartRowInmutable();
			end = baseRangeAddress + (configCriteria.getSheet().getLastRowNum() + 1);
		} else {
			start = CellReference.convertNumToColString(configCriteria.getStartCellInmutable()) + baseRangeAddress;
			end = CellReference.convertNumToColString(configCriteria.getLastCellIndex()) + baseRangeAddress;
		}
		return start + ":" + end;
	}

	/* internal methods */

	/**
	 * Remove all the possible characters allowed to separate a range address.
	 * 
	 * @param templateRangeAddress
	 *            the template range address
	 * @return the template range address without separator characters
	 */
	private static String removeRangeSeparator(String templateRangeAddress) {
		String cleanRangeAddress = templateRangeAddress;
		for (String value : splitCharacters) {
			if (cleanRangeAddress.contains(value)) {
				/* remove character if exists */
				cleanRangeAddress = cleanRangeAddress.replace(value, StringUtils.EMPTY);
			}
		}
		return cleanRangeAddress;
	}

	/**
	 * Split the template range address by the possibles characters allowed.
	 * 
	 * @param templateRangeAddress
	 *            the template range address to be treated
	 * @return the template range address as Array
	 */
	private static String[] splitRangeSeparator(String templateRangeAddress) {
		String[] splitted = null;

		for (String value : splitCharacters) {
			if (templateRangeAddress.contains(value)) {
				/* remove ':' if exists */
				splitted = templateRangeAddress.split(value);
				break;
			}
		}
		if (splitted == null) {
			/* nothing to remove */
			splitted = new String[1];
			splitted[0] = templateRangeAddress;
		}
		return splitted;
	}

	/**
	 * Validate propagation with template range address passed as parameter.
	 * <p>
	 * Acceptable values are:
	 * <ul>
	 * <li>if PropagationType.HORIZONTAL, only numeric values allowed
	 * <li>if PropagationType.VERTICAL, only letters values allowed
	 * </ul>
	 * 
	 * @param configCriteria
	 *            the {@link XConfigCriteria}
	 * @param templateRangeAddress
	 *            the template range address
	 * @throws ConfigurationException
	 */
	private static void validatePropagationWithRangeAddress(final XConfigCriteria configCriteria,
			String templateRangeAddress) throws ConfigurationException {
		if (PredicateFactory.isPropagationHorizontal.test(configCriteria.getPropagation())
				&& StringUtils.isNumeric(templateRangeAddress)
				|| PredicateFactory.isPropagationVertical.test(configCriteria.getPropagation())
						&& StringUtils.isAlpha(templateRangeAddress)) {
			throw new ConfigurationException(ExceptionMessage.CONFIGURATION_CONFLICT_CONDITIONAL_FORMAT.getMessage());
		}
	}

	/**
	 * Transform the range address to a valid cell position.
	 * <p>
	 * Apply this method in case of the template contain a single row or column
	 * (eg. '4' or 'D').
	 * 
	 * @param configCriteria
	 *            the {@link XConfigCriteria}
	 * @param templateRangeAddress
	 *            the template range address to be treated
	 * @param position
	 *            the position to at the sheet
	 * @return the range address converted to a valid cell position
	 */
	private static String transformToRange(XConfigCriteria configCriteria, String templateRangeAddress, int position) {
		String cellPosition;
		if (PredicateFactory.isPropagationHorizontal.test(configCriteria.getPropagation())) {
			cellPosition = templateRangeAddress + position;
		} else {
			cellPosition = CellReference.convertNumToColString(position) + templateRangeAddress;
		}
		return cellPosition;
	}

	/**
	 * Transform the start range address to a valid cell position.
	 * <p>
	 * Apply this method in case of the template contain a template range
	 * address (eg. 'N:M' or '4:5').
	 * <p>
	 * The start range address will be treated here.
	 * 
	 * @param configCriteria
	 *            the {@link XConfigCriteria}
	 * @param splitted
	 *            the base range address to transform
	 * @return the range address converted to a valid cell position
	 */
	private static String transformToStartRange(final XConfigCriteria configCriteria, String startRange) {
		String start = null;
		if (StringUtils.isNumeric(startRange)) {
			start = transformToRange(configCriteria, startRange, configCriteria.getStartCellInmutable());
		} else if (StringUtils.isAlpha(startRange)) {
			start = transformToRange(configCriteria, startRange, configCriteria.getStartRowInmutable());
		}
		return start;
	}

	/**
	 * Transform the end range address to a valid cell position.
	 * <p>
	 * Apply this method in case of the template contain a template range
	 * address (eg. 'N:M' or '4:5').
	 * <p>
	 * The end range address will be treated here.
	 * 
	 * @param configCriteria
	 *            the {@link XConfigCriteria}
	 * @param endRange
	 *            the base range address to transform
	 * @return the range address converted to a valid cell position
	 */
	private static String transformToEndRange(final XConfigCriteria configCriteria, String endRange) {
		String end = null;
		if (StringUtils.isNumeric(endRange)) {
			end = transformToRange(configCriteria, endRange, configCriteria.getLastCellIndex());
		} else if (StringUtils.isAlpha(endRange)) {
			end = transformToRange(configCriteria, endRange, configCriteria.getSheet().getLastRowNum() + 1);
		}
		return end;
	}

}
