package de.visualdependencies.data.dao;

import de.visualdependencies.data.entity.Schema;
import de.visualdependencies.data.entity.SchemaConnection;

public interface SchemaConnectionDao extends GeneralDao<SchemaConnection> {

	SchemaConnection getBy(Schema schema);

}
