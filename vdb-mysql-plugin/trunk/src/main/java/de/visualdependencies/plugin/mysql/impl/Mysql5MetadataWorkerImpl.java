package de.visualdependencies.plugin.mysql.impl;

import org.springframework.stereotype.Component;

import de.visualdependencies.data.entity.SchemaConnection;
import de.visualdependencies.plugin.ConnectionProvider;
import de.visualdependencies.plugin.DataStore;
import de.visualdependencies.plugin.MetadataWorker;

@Component("MySQL 5 Metadata Worker")
public class Mysql5MetadataWorkerImpl extends AbstractMysqlPluginImpl implements MetadataWorker {

	@Override
	public void loadModel(SchemaConnection schemaConnection, ConnectionProvider connectionProvider, DataStore dataStore) {
		// TODO Auto-generated method stub

	}

}
