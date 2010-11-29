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
