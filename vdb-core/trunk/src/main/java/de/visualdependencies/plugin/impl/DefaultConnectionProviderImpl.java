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

package de.visualdependencies.plugin.impl;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.util.Assert;

import de.visualdependencies.data.entity.SchemaConnection;
import de.visualdependencies.plugin.ConnectionProvider;

/**
 * Default implementation of a {@link ConnectionProvider}. Its propose is a lighter vendor-specific implementation.
 * 
 * @author Jan Philipp
 */
public class DefaultConnectionProviderImpl extends AbstractPluginImpl implements ConnectionProvider {

	protected Connection buildConnection(final SchemaConnection schemaConnection) throws SQLException {
		Assert.state(false, "This method is not implemented.");
		return null;
	}

	@Override
	public void closeConnection(final Connection connection) {
		JdbcUtils.closeConnection(connection);
	}

	@Override
	public Connection createConnection(final SchemaConnection schemaConnection) {
		try {
			final Connection connection = buildConnection(schemaConnection);
			Assert.notNull(connection, "The jdbc connection could not be created.");
			return connection;
		} catch (final SQLException e) {
			logger.warn("Connection could not be created.", e);
		}
		return null;
	}

	@Override
	public void initializeDriver() throws IllegalStateException {}

	@Override
	public boolean testConnection(final SchemaConnection schemaConnection) throws Exception {
		Connection connection = null;
		try {
			connection = buildConnection(schemaConnection);
			return true;
		} catch (final Exception e) {
			throw e;
		} finally {
			closeConnection(connection);
		}
	}

}
