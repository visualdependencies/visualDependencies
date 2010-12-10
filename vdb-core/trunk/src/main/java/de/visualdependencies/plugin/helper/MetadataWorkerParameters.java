/*
 * visualDependencies (visualize your database)
 * Copyright (C) 2009-2010 Andre Kasper, Jan Philipp <knallisworld@googlemail.com>
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

package de.visualdependencies.plugin.helper;

import java.sql.Connection;

import org.springframework.util.Assert;

import de.visualdependencies.data.entity.Schema;
import de.visualdependencies.data.entity.SchemaConnection;
import de.visualdependencies.plugin.ConnectionProvider;
import de.visualdependencies.plugin.DataStore;
import edu.umd.cs.findbugs.annotations.NonNull;

final public class MetadataWorkerParameters {

	public static MetadataWorkerParameters create(@NonNull final Schema schema,
	        @NonNull final SchemaConnection schemaConnection,
	        @NonNull final ConnectionProvider connectionProvider, @NonNull final DataStore dataStore) {
		return new MetadataWorkerParameters(schema, schemaConnection, connectionProvider, dataStore);
	}

	private final Schema schema;

	private final ConnectionProvider connectionProvider;

	private final DataStore dataStore;

	private Connection connection;

	private SchemaConnection schemaConnection;

	private MetadataWorkerParameters(@NonNull final Schema schema, final SchemaConnection schemaConnection,
	        @NonNull final ConnectionProvider connectionProvider, @NonNull final DataStore dataStore) {

		Assert.notNull(schema, "No schema specified.");
		Assert.notNull(schemaConnection, "No schema connection specified.");
		Assert.notNull(connectionProvider, "No connection provider specified.");
		Assert.notNull(dataStore, "No data store specified.");

		this.schema = schema;
		this.schemaConnection = schemaConnection;
		this.connectionProvider = connectionProvider;
		this.dataStore = dataStore;
	}

	public Connection getConnection() {
		return connection;
	}

	public ConnectionProvider getConnectionProvider() {
		return connectionProvider;
	}

	public DataStore getDataStore() {
		return dataStore;
	}

	public Schema getSchema() {
		return schema;
	}

	public SchemaConnection getSchemaConnection() {
		return schemaConnection;
	}

	public void setConnection(final Connection connection) {
		this.connection = connection;
	}

	public void setSchemaConnection(final SchemaConnection schemaConnection) {
		this.schemaConnection = schemaConnection;
	}
}
