package de.visualdependencies.plugin.mysql.impl;

import java.sql.Connection;

import org.springframework.stereotype.Component;

import de.visualdependencies.data.entity.SchemaConnection;
import de.visualdependencies.data.entity.SchemaTable;
import de.visualdependencies.plugin.ConnectionProvider;
import de.visualdependencies.plugin.DataStore;
import de.visualdependencies.plugin.MetadataWorker;
import de.visualdependencies.plugin.helper.MetadataWorkerParameters;
import de.visualdependencies.plugin.helper.MetadataWorkerResult;

@Component("MySQL Metadata Worker")
public class MysqlMetadataWorkerImpl extends AbstractMysqlPluginImpl implements MetadataWorker {

	@Override
	public MetadataWorkerResult loadModel(MetadataWorkerParameters parameters) {

		MetadataWorkerResult result = MetadataWorkerResult.create();
		result.markStart();

		ConnectionProvider connectionProvider = parameters.getConnectionProvider();
		SchemaConnection schemaConnection = parameters.getSchemaConnection();
		DataStore dataStore = parameters.getDataStore();

		// Create a real JDBC connection.
		Connection connection = connectionProvider.createConnection(schemaConnection);
		parameters.setConnection(connection);

		// DEMO
		SchemaTable schemaTable = dataStore.createSchemaTable();
		schemaTable.setName("TEST1");
		schemaTable.getData().put("mysql:test", "123");
		dataStore.saveSchemaTable(schemaTable);
		schemaConnection.getTables().add(schemaTable);
		dataStore.saveSchemaConnection(schemaConnection);

		result.markEnd();

		return result;
	}
}
