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
package net.ceos.project.poi.annotated.bean;

import java.util.Date;

import net.ceos.project.poi.annotated.annotation.XlsConfiguration;
import net.ceos.project.poi.annotated.annotation.XlsElement;
import net.ceos.project.poi.annotated.annotation.XlsNestedHeader;
import net.ceos.project.poi.annotated.annotation.XlsSheet;
import net.ceos.project.poi.annotated.exception.CustomizedRulesException;

public class GuardiansOfTheGalaxyFactory {

	private static GuardiansOfTheGalaxyFactory factory = new GuardiansOfTheGalaxyFactory();

	/**
	 * More info at {@link Groot}
	 */
	public static Groot instanceGroot() {
		return factory.new Groot();
	}

	/**
	 * More info at {@link Drax}
	 */
	public static Drax instanceDrax() {
		return factory.new Drax();
	}

	/**
	 * More info at {@link Gamora}
	 */
	public static Gamora instanceGamora() {
		return factory.new Gamora();
	}

	/**
	 * More info at {@link RocketRaccoon}
	 */
	public static RocketRaccoon instanceRocketRaccoon() {
		return factory.new RocketRaccoon();
	}

	// public static StarLord instanceStarLord() {
	// return factory.new StarLord();
	// }

	/**
	 * Test a scenario of calling a customized rules which is not defined<br>
	 * <br>
	 * Configuration :
	 * <li>{@link XlsElement} : apply customized rule inexistent</li><br>
	 * 
	 * @version 1.0
	 * @author Carlos CRISTO ABREU
	 */
	@XlsSheet(title = "Xls Element customized rules", startRow = 2)
	@XlsConfiguration(nameFile = "ElementCustomizedRule")
	public class Groot {

		@XlsNestedHeader(title = "Main", startX = 1, endX = 3)
		@XlsElement(title = "Date value", position = 1)
		private Date dateAttribute;

		@XlsElement(title = "String value", position = 2, comment = "This is an simple comment", customizedRules = "sumMethod")
		private String stringAttribute;

		@XlsElement(title = "Integer value", position = 3)
		private Integer integerAttribute = 0;

		/**
		 * @return the dateAttribute
		 */
		public Date getDateAttribute() {
			return dateAttribute;
		}

		/**
		 * @param dateAttribute
		 *            the dateAttribute to set
		 */
		public void setDateAttribute(Date dateAttribute) {
			this.dateAttribute = dateAttribute;
		}

		/**
		 * @return the stringAttribute
		 */
		public String getStringAttribute() {
			return stringAttribute;
		}

		/**
		 * @param stringAttribute
		 *            the stringAttribute to set
		 */
		public void setStringAttribute(String stringAttribute) {
			this.stringAttribute = stringAttribute;
		}

		/**
		 * @return the integerAttribute
		 */
		public Integer getIntegerAttribute() {
			return integerAttribute;
		}

		/**
		 * @param integerAttribute
		 *            the integerAttribute to set
		 */
		public void setIntegerAttribute(Integer integerAttribute) {
			this.integerAttribute = integerAttribute;
		}

		/**
		 * Check if the attribute 'stringAttribute' contains a determined value.
		 * 
		 * @throws CustomizedRulesException
		 */
		public void myRuleMethod() throws CustomizedRulesException {
			if (this.stringAttribute.contains("down")) {
				throw new CustomizedRulesException("Sample how to manage the content of one string");
			}
		}
	}

	/**
	 * Test a multiples customized rules defined at the same object. According
	 * the rule some action will be launched<br>
	 * <br>
	 * Configuration :
	 * <li>{@link XlsElement} : apply customized rules if true launch exception
	 * </li><br>
	 * 
	 * @version 1.0
	 * @author Carlos CRISTO ABREU
	 */
	@XlsSheet(title = "XlsElement customized rules", startRow = 2)
	@XlsConfiguration(nameFile = "ElementCustomizedRule")
	public class Drax {

		@XlsNestedHeader(title = "Main", startX = 1, endX = 3)
		@XlsElement(title = "Date value", position = 1)
		private Date dateAttribute;

		@XlsElement(title = "String value", position = 2, comment = "This is an simple comment", customizedRules = "myRuleMethod")
		private String stringAttribute;

		@XlsElement(title = "Integer value", position = 3)
		private Integer integerAttribute1 = 0;

		@XlsElement(title = "Integer value", position = 4, customizedRules = "sampleRule")
		private Integer integerAttribute2 = 0;

		@XlsElement(title = "Integer value", position = 5, customizedRules = "anotherRule")
		private double doublePrimitiveAttribute = 0;

		/**
		 * @return the dateAttribute
		 */
		public Date getDateAttribute() {
			return dateAttribute;
		}

		/**
		 * @param dateAttribute
		 *            the dateAttribute to set
		 */
		public void setDateAttribute(Date dateAttribute) {
			this.dateAttribute = dateAttribute;
		}

		/**
		 * @return the stringAttribute
		 */
		public String getStringAttribute() {
			return stringAttribute;
		}

		/**
		 * @param stringAttribute
		 *            the stringAttribute to set
		 */
		public void setStringAttribute(String stringAttribute) {
			this.stringAttribute = stringAttribute;
		}

		/**
		 * @return the integerAttribute1
		 */
		public Integer getIntegerAttribute1() {
			return integerAttribute1;
		}

		/**
		 * @param integerAttribute1
		 *            the integerAttribute1 to set
		 */
		public void setIntegerAttribute1(Integer integerAttribute1) {
			this.integerAttribute1 = integerAttribute1;
		}

		/**
		 * @return the integerAttribute2
		 */
		public Integer getIntegerAttribute2() {
			return integerAttribute2;
		}

		/**
		 * @param integerAttribute2
		 *            the integerAttribute2 to set
		 */
		public void setIntegerAttribute2(Integer integerAttribute2) {
			this.integerAttribute2 = integerAttribute2;
		}

		/**
		 * @return the doublePrimitiveAttribute
		 */
		public double getDoublePrimitiveAttribute() {
			return doublePrimitiveAttribute;
		}

		/**
		 * @param doublePrimitiveAttribute
		 *            the doublePrimitiveAttribute to set
		 */
		public void setDoublePrimitiveAttribute(double doublePrimitiveAttribute) {
			this.doublePrimitiveAttribute = doublePrimitiveAttribute;
		}

		/**
		 * Check if the attribute 'stringAttribute' contains a determined value.
		 * 
		 * @throws CustomizedRulesException
		 */
		public void myRuleMethod() throws CustomizedRulesException {
			if (this.stringAttribute.contains("down")) {
				throw new CustomizedRulesException("Sample how to manage the content of one string");
			}
		}

		/**
		 * Check if the attribute 'integerAttribute2' is equals to a determined
		 * value.
		 * 
		 * @throws CustomizedRulesException
		 */
		public void sampleRule() throws CustomizedRulesException {
			if (this.integerAttribute2.equals(200)) {
				throw new CustomizedRulesException("Sample how to manage the content of one integer");
			}
		}

		/**
		 * Check if the attribute 'doublePrimitiveAttribute' is equals to a
		 * determined value.
		 * 
		 * @throws CustomizedRulesException
		 */
		public void anotherRule() throws CustomizedRulesException {
			if (this.doublePrimitiveAttribute == 0.0) {
				throw new CustomizedRulesException("Sample how to manage the content of one double");
			}
		}
	}

	/**
	 * Test a simple customized rules defined if value under 450. According the
	 * rule some action will be launched<br>
	 * <br>
	 * Configuration :
	 * <li>{@link XlsElement} : apply customized rules value under 450 jexan
	 * launch an exception</li><br>
	 * 
	 * @version 1.0
	 * @author Carlos CRISTO ABREU
	 */
	@XlsSheet(title = "Xls Element customized rules", startRow = 2)
	@XlsConfiguration(nameFile = "ElementCustomizedRule")
	public class Gamora {

		@XlsNestedHeader(title = "Main", startX = 1, endX = 3)
		@XlsElement(title = "Date value", position = 1)
		private Date dateAttribute;

		@XlsElement(title = "String value", position = 2, comment = "This is an simple comment")
		private String stringAttribute;

		@XlsElement(title = "Integer value", position = 3, customizedRules = "numericRule")
		private Integer integerAttribute = 0;

		/**
		 * @return the dateAttribute
		 */
		public Date getDateAttribute() {
			return dateAttribute;
		}

		/**
		 * @param dateAttribute
		 *            the dateAttribute to set
		 */
		public void setDateAttribute(Date dateAttribute) {
			this.dateAttribute = dateAttribute;
		}

		/**
		 * @return the stringAttribute
		 */
		public String getStringAttribute() {
			return stringAttribute;
		}

		/**
		 * @param stringAttribute
		 *            the stringAttribute to set
		 */
		public void setStringAttribute(String stringAttribute) {
			this.stringAttribute = stringAttribute;
		}

		/**
		 * @return the integerAttribute
		 */
		public Integer getIntegerAttribute() {
			return integerAttribute;
		}

		/**
		 * @param integerAttribute
		 *            the integerAttribute to set
		 */
		public void setIntegerAttribute(Integer integerAttribute) {
			this.integerAttribute = integerAttribute;
		}

		/**
		 * Check if the attribute 'integerAttribute' if value under 450.
		 * 
		 * @throws CustomizedRulesException
		 */
		public void numericRule() throws CustomizedRulesException {
			if (this.integerAttribute < 450) {
				throw new CustomizedRulesException("Sample how to manage the content of one numeric");
			}
		}
	}

	/**
	 * Test a simple customized rules defined if a string contains the word
	 * "down". According the rule some action will be launched<br>
	 * <br>
	 * Configuration :
	 * <li>{@link XlsElement} : apply customized rules</li><br>
	 * 
	 * @version 1.0
	 * @author Carlos CRISTO ABREU
	 */
	@XlsSheet(title = "Xls Element customized rules", startRow = 2)
	@XlsConfiguration(nameFile = "ElementCustomizedRule")
	public class RocketRaccoon {

		@XlsNestedHeader(title = "Main", startX = 1, endX = 3)
		@XlsElement(title = "Date value", position = 1)
		private Date dateAttribute;

		@XlsElement(title = "String value", position = 2, comment = "This is an simple comment", customizedRules = "myRuleMethod")
		private String stringAttribute;

		@XlsElement(title = "Integer value", position = 3)
		private Integer integerAttribute = 0;

		/**
		 * @return the dateAttribute
		 */
		public Date getDateAttribute() {
			return dateAttribute;
		}

		/**
		 * @param dateAttribute
		 *            the dateAttribute to set
		 */
		public void setDateAttribute(Date dateAttribute) {
			this.dateAttribute = dateAttribute;
		}

		/**
		 * @return the stringAttribute
		 */
		public String getStringAttribute() {
			return stringAttribute;
		}

		/**
		 * @param stringAttribute
		 *            the stringAttribute to set
		 */
		public void setStringAttribute(String stringAttribute) {
			this.stringAttribute = stringAttribute;
		}

		/**
		 * @return the integerAttribute
		 */
		public Integer getIntegerAttribute() {
			return integerAttribute;
		}

		/**
		 * @param integerAttribute
		 *            the integerAttribute to set
		 */
		public void setIntegerAttribute(Integer integerAttribute) {
			this.integerAttribute = integerAttribute;
		}

		/**
		 * Check if the attribute 'stringAttribute' contains a determined value.
		 * 
		 * @throws CustomizedRulesException
		 */
		public void myRuleMethod() throws CustomizedRulesException {
			if (this.stringAttribute.contains("down")) {
				throw new CustomizedRulesException("Sample how to manage the content of one string");
			}
		}
	}
}
