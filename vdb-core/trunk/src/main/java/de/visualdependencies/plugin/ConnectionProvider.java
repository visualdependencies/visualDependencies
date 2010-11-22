package de.visualdependencies.plugin;

import java.sql.Connection;

import de.visualdependencies.data.entity.SchemaConnection;

public interface ConnectionProvider extends Plugin {

	void closeConnection(Connection connection);

	Connection createConnection(SchemaConnection schemaConnection);

	void initializeDriver() throws IllegalStateException;

	boolean testConnection(SchemaConnection schemaConnection);

}
