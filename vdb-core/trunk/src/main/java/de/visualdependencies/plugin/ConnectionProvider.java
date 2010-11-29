package de.visualdependencies.plugin;

import java.sql.Connection;

import de.visualdependencies.data.entity.SchemaConnection;
import edu.umd.cs.findbugs.annotations.NonNull;

/**
 * A connection provider can create a new {@link Connection} for a specified {@link SchemaConnection}.
 * 
 * @author Jan Philipp
 */
public interface ConnectionProvider extends Plugin {

	void closeConnection(Connection connection);

	Connection createConnection(@NonNull SchemaConnection schemaConnection);

	void initializeDriver() throws IllegalStateException;

	boolean testConnection(@NonNull SchemaConnection schemaConnection) throws Exception;

}
