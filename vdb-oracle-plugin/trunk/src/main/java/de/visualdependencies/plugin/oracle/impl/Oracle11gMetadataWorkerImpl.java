package de.visualdependencies.plugin.oracle.impl;

import org.springframework.stereotype.Component;

import de.visualdependencies.data.entity.SchemaConnection;
import de.visualdependencies.data.entity.SchemaTable;
import de.visualdependencies.plugin.ConnectionProvider;
import de.visualdependencies.plugin.DataStore;
import de.visualdependencies.plugin.MetadataWorker;

@Component("Oracle 11g Metadata Worker")
public class Oracle11gMetadataWorkerImpl extends AbstractOraclePluginImpl implements MetadataWorker {

	@Override
	public void loadModel(SchemaConnection schemaConnection, ConnectionProvider connectionProvider, DataStore dataStore) {
		SchemaTable schemaTable = dataStore.createSchemaTable();
		schemaTable.setName("TEST1");
		schemaTable.getData().put("oracle:test", "123");
		dataStore.saveSchemaTable(schemaTable);
		schemaConnection.getTables().add(schemaTable);
		dataStore.saveSchemaConnection(schemaConnection);
	}

}
