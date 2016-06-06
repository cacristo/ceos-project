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

import java.util.Collection;

import net.ceos.project.poi.annotated.definition.CascadeType;

/**
 * Manage the cascade level behavior.
 * 
 * @version 2.0
 * @author Carlos CRISTO ABREU
 */
class CascadeHandler {

	private CascadeHandler() {
		/* private constructor to hide the implicit public */
	}

	/**
	 * 
	 * @return true if the current cascade level is authorized otherwise false
	 */
	protected static boolean isAuthorizedCascadeLevel(final XConfigCriteria configCriteria, final int current,
			final Object object) {

		if (object == null || current > configCriteria.getCascadeLevel().getCode()) {
			return false;
		}
		
		if (CascadeType.CASCADE_BASE.equals(configCriteria.getCascadeLevel())) {
			return levelBase(current, object);

		} else if (CascadeType.CASCADE_L1.equals(configCriteria.getCascadeLevel())) {
			return level1(current, object);

		} else if (CascadeType.CASCADE_L2.equals(configCriteria.getCascadeLevel())) {
			return level2(current, object);

		} else if (CascadeType.CASCADE_L3.equals(configCriteria.getCascadeLevel())) {
			return level3(current, object);

		} else if (CascadeType.CASCADE_FULL.equals(configCriteria.getCascadeLevel())) {
			return levelFull(current, object);

		}
		return false;
	}

	private static boolean levelBase(final int current, final Object object) {
		if (current == 1 && Collection.class.isAssignableFrom(object.getClass())) {
			return false;
		}
		return true;
	}

	private static boolean level1(final int current, final Object object) {
		if (current == 3 && Collection.class.isAssignableFrom(object.getClass())) {
			return false;
		}
		return true;
	}

	private static boolean level2(final int current, final Object object) {
		if (current == 5 && Collection.class.isAssignableFrom(object.getClass())) {
			return false;
		}
		return true;
	}

	private static boolean level3(final int current, final Object object) {
		if (current == 7 && Collection.class.isAssignableFrom(object.getClass())) {
			return false;
		}
		return true;
	}

	private static boolean levelFull(final int current, final Object object) {
		if (current == 1 && Collection.class.isAssignableFrom(object.getClass())) {
			return false;
		}
		return true;
	}

}
