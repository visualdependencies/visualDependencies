package de.visualdependencies.plugin.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import de.visualdependencies.plugin.Plugin;
import de.visualdependencies.plugin.PluginService;
import edu.umd.cs.findbugs.annotations.NonNull;

@Service
public class PluginServiceImpl implements PluginService {

	@Autowired
	private ApplicationContext applicationContext;

	@Override
	public <T extends Plugin> Collection<T> getPlugins(@NonNull final Class<T> pluginType) {

		Assert.notNull(pluginType, "The plugin type must not be null.");

		final Collection<T> plugins = new ArrayList<T>();
		plugins.addAll(getPluginsAsMap(pluginType).values());

		return plugins;
	}

	/**
	 * @see ApplicationContext#getBeansOfType(Class)
	 */
	@Override
	public <T extends Plugin> Map<String, T> getPluginsAsMap(@NonNull final Class<T> pluginType) {

		Assert.notNull(pluginType, "The plugin type must not be null.");

		return applicationContext.getBeansOfType(pluginType);
	}

	@Override
	public <T extends Plugin> T resolvePlugin(final String name, final Class<T> pluginType) {
		return applicationContext.getBean(name, pluginType);
	}

	@Override
	public String resolvePluginName(final Plugin plugin) {
		final Map<String, ? extends Plugin> plugins = getPluginsAsMap(plugin.getClass());
		for (final Map.Entry<String, ? extends Plugin> entry : plugins.entrySet()) {
			if (entry.getValue() == plugin) { return entry.getKey(); }
		}
		return null;
	}

}
