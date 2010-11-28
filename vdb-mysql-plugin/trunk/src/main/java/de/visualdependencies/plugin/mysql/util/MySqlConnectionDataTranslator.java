package de.visualdependencies.plugin.mysql.util;

import de.visualdependencies.data.entity.SchemaConnection;
import de.visualdependencies.plugin.util.AbstractDataTranslator;

/**
 * MySQL connection data map translator.
 * 
 * This utility class provides type-safe access to MySQL connection object properties.
 * 
 * @author Jan Philipp
 * 
 */
final public class MySqlConnectionDataTranslator extends AbstractDataTranslator {

	private static final String URL = "url";

	protected MySqlConnectionDataTranslator(SchemaConnection schemaConnection) {
		super(schemaConnection.getData());
	}

	public String getUrl() {
		return getString(URL);
	}

	public void setUrl(String value) {
		set(URL, value);
	}

	public static MySqlConnectionDataTranslator create(SchemaConnection schemaConnection) {
		return new MySqlConnectionDataTranslator(schemaConnection);
	}

}
