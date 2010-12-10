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
