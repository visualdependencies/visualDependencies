package de.visualdependencies.util.translator;

import de.visualdependencies.data.entity.Schema;
import edu.umd.cs.findbugs.annotations.NonNull;

/**
 * Schema data map translator.
 * 
 * This utility class provides type-safe access to schema properties.
 * 
 * @author Jan Philipp
 */
public class SchemaDataTranslator extends AbstractDataTranslator {

	private static final String LAST_USED_CONNECTION_PROVIDER = "lastUsedConnectionProvider";

	private static final String LAST_USED_METADATA_WORKER = "lastUsedMetadataWorker";

	public static SchemaDataTranslator create(@NonNull final Schema schema) {
		return new SchemaDataTranslator(schema);
	}

	protected SchemaDataTranslator(@NonNull final Schema schema) {
		super(schema.getData());
	}

	public String getLastUsedConnectionProvider() {
		return getString(LAST_USED_CONNECTION_PROVIDER);
	}

	public String getLastUsedMetadataWorker() {
		return getString(LAST_USED_METADATA_WORKER);
	}

	public void setLastUsedConnectionProvider(final String value) {
		setString(LAST_USED_CONNECTION_PROVIDER, value);
	}

	public void setLastUsedMetadataWorker(final String value) {
		setString(LAST_USED_METADATA_WORKER, value);
	}

}
