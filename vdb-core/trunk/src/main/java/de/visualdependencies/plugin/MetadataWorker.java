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

import de.visualdependencies.plugin.helper.MetadataWorkerParameters;
import de.visualdependencies.plugin.helper.MetadataWorkerResult;
import edu.umd.cs.findbugs.annotations.NonNull;

public interface MetadataWorker extends Plugin {

	/**
	 * Loads the model from the specified parameters and persists all data.
	 * 
	 * This will create a JDBC connection, load all objects and store the data via the provided data store.
	 * 
	 * @param parameters
	 */
	@NonNull
	MetadataWorkerResult loadModel(@NonNull MetadataWorkerParameters parameters);

}
