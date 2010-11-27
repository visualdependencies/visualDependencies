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

	protected CommonConnectionDataTranslator(SchemaConnection schemaConnection) {
		super(schemaConnection.getData());
	}

	public String getUrl() {
		return getString("url");
	}

	public static CommonConnectionDataTranslator create(SchemaConnection schemaConnection) {
		return new CommonConnectionDataTranslator(schemaConnection);
	}

}
