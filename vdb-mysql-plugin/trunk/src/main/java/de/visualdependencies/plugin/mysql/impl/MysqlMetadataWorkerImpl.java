package de.visualdependencies.plugin.mysql.impl;

import java.sql.Connection;

import org.springframework.stereotype.Component;

import de.visualdependencies.data.entity.Schema;
import de.visualdependencies.data.entity.SchemaConnection;
import de.visualdependencies.data.entity.SchemaTable;
import de.visualdependencies.plugin.ConnectionProvider;
import de.visualdependencies.plugin.DataStore;
import de.visualdependencies.plugin.MetadataWorker;
import de.visualdependencies.plugin.Plugin;
import de.visualdependencies.plugin.helper.MetadataWorkerParameters;
import de.visualdependencies.plugin.helper.MetadataWorkerResult;

@Component("MySQL Metadata Worker")
public class MysqlMetadataWorkerImpl extends AbstractMysqlPluginImpl implements MetadataWorker {

	@Override
	public boolean isCompatible(Plugin otherPlugin) {
		if (otherPlugin instanceof de.visualdependencies.plugin.common.impl.CommonConnectionProviderImpl) { return true; }
		return super.isCompatible(otherPlugin);
	}

	@Override
	public MetadataWorkerResult loadModel(MetadataWorkerParameters parameters) {

		// Create the result object. Both parameters and result live in this call.
		MetadataWorkerResult result = MetadataWorkerResult.create();
		result.markStart();

		ConnectionProvider connectionProvider = parameters.getConnectionProvider();
		Schema schema = parameters.getSchema();
		SchemaConnection schemaConnection = schema.getConnection();
		DataStore dataStore = parameters.getDataStore();

		// Create a real JDBC connection.
		connectionProvider.initializeDriver();
		Connection connection = connectionProvider.createConnection(schemaConnection);
		parameters.setConnection(connection);

		// DEMO
		SchemaTable schemaTable = dataStore.createSchemaTable();
		schemaTable.setName("TEST1");
		schemaTable.getData().put("mysql:test", "123");
		dataStore.saveSchemaTable(schemaTable);
		schema.getTables().add(schemaTable);
		dataStore.saveSchema(schema);

		result.markEnd();

		return result;
	}
}
