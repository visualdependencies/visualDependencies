package de.visualdependencies.plugin.common.util;

import de.visualdependencies.data.entity.SchemaConnection;
import de.visualdependencies.plugin.util.AbstractDataTranslator;

/**
 * Common connection data map translator.
 * 
 * This utility class provides type-safe access to common connection properties.
 * 
 * @author Jan Philipp
 * 
 */
public class CommonConnectionDataTranslator extends AbstractDataTranslator {

	private static final String URL = "url";

	protected CommonConnectionDataTranslator(SchemaConnection schemaConnection) {
		super(schemaConnection.getData());
	}

	public String getUrl() {
		return getString(URL);
	}

	public void setUrl(String value) {
		set(URL, value);
	}

	public static CommonConnectionDataTranslator create(SchemaConnection schemaConnection) {
		return new CommonConnectionDataTranslator(schemaConnection);
	}

}
