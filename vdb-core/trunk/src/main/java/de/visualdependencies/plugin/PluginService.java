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

import java.util.Collection;
import java.util.Map;

import edu.umd.cs.findbugs.annotations.NonNull;

public interface PluginService {

	/**
	 * Returns the list of plugins which are instances of the specified plugin type. This method will return always a
	 * collection object.
	 * 
	 * @param pluginType
	 * @return
	 */
	<T extends Plugin> Collection<T> getPlugins(@NonNull Class<T> pluginType);

	/**
	 * Returns the map of plugins which are instances of the specified plugin type. This method will return always a
	 * map object.
	 * 
	 * @param pluginType
	 * @return
	 */
	<T extends Plugin> Map<String, T> getPluginsAsMap(@NonNull Class<T> pluginType);

	<T extends Plugin> T resolvePlugin(@NonNull String name, @NonNull Class<T> pluginType);

	/**
	 * Returns the context bean name of the given plugin.
	 * 
	 * @param plugin
	 * @return
	 */
	String resolvePluginName(Plugin plugin);

}
