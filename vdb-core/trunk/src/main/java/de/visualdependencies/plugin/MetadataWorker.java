package de.visualdependencies.plugin;

import de.visualdependencies.data.entity.SchemaConnection;

public interface MetadataWorker extends Plugin {

	void loadModel(SchemaConnection schemaConnection, ConnectionProvider connectionProvider,
	        DataStore dataStore);

}
