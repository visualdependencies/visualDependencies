package de.visualdependencies.plugin.util;

import java.util.Map;

/**
 * Abstract data map translator.
 * 
 * This utility class provides type-safe access to object properties.
 * 
 * @author Jan Philipp
 * 
 */
abstract public class AbstractDataTranslator {

	private final Map<String, String> data;

	public AbstractDataTranslator(final Map<String, String> data) {
		this.data = data;
	}

	final protected boolean contains(final String name) {
		return data.containsKey(name);
	}

	final protected String get(final String name) {
		return data.get(name);
	}

	protected String getString(final String name) {
		if (contains(name)) {
			return get(name);
		} else {
			return null;
		}
	}

}
