package de.visualdependencies.util.translator;

import de.visualdependencies.data.entity.SchemaConnection;
import edu.umd.cs.findbugs.annotations.NonNull;

/**
 * Connection data map translator.
 * 
 * This utility class provides type-safe access to connection properties.
 * 
 * @author Jan Philipp
 */
public class ConnectionDataTranslator extends AbstractDataTranslator {

	private static final String URL = "url";

	private static final String SCHEMA = "schema";

	private static final String CONNECTION_BY = "connectionBy";

	private static final String USERNAME = "username";

	private static final String PASSWORD = "password";

	public static ConnectionDataTranslator create(@NonNull final SchemaConnection connection) {
		return new ConnectionDataTranslator(connection);
	}

	protected ConnectionDataTranslator(@NonNull final SchemaConnection connection) {
		super(connection.getData());
	}

	public String getConnectionBy() {
		return getString(CONNECTION_BY);
	}

	public String getPassword() {
		return getString(PASSWORD);
	}

	public String getSchema() {
		return getString(SCHEMA);
	}

	public String getUrl() {
		return getString(URL);
	}

	public String getUsername() {
		return getString(USERNAME);
	}

	public void setConnectionBy(final String value) {
		setString(CONNECTION_BY, value);
	}

	public void setPassword(final String value) {
		setString(PASSWORD, value);
	}

	public void setSchema(final String value) {
		setString(SCHEMA, value);
	}

	public void setUrl(final String value) {
		setString(URL, value);
	}

	public void setUsername(final String value) {
		setString(USERNAME, value);
	}

}
