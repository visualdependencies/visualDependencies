package de.visualdependencies.plugin;

import java.sql.Connection;

import de.visualdependencies.data.entity.SchemaConnection;
import edu.umd.cs.findbugs.annotations.NonNull;

public interface ConnectionProvider extends Plugin {

	void closeConnection(Connection connection);

	Connection createConnection(@NonNull SchemaConnection schemaConnection);

	void initializeDriver() throws IllegalStateException;

	boolean testConnection(@NonNull SchemaConnection schemaConnection);

}
