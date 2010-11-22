package de.visualdependencies.plugin;

import java.util.Collection;
import java.util.Map;

public interface PluginService {

	/**
	 * Returns the list of plugins which are instances of the specified plugin type. This method will return always a
	 * collection object.
	 * 
	 * @param pluginType
	 * @return
	 */
	<T extends Plugin> Collection<T> getPlugins(Class<T> pluginType);

	/**
	 * Returns the map of plugins which are instances of the specified plugin type. This method will return always a
	 * map object.
	 * 
	 * @param pluginType
	 * @return
	 */
	<T extends Plugin> Map<String, T> getPluginsAsMap(Class<T> pluginType);

}
