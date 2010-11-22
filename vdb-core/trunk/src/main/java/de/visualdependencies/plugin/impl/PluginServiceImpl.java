package de.visualdependencies.plugin.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.sun.istack.internal.NotNull;

import de.visualdependencies.plugin.Plugin;
import de.visualdependencies.plugin.PluginService;

@Service
public class PluginServiceImpl implements PluginService {

	@Autowired
	private ApplicationContext applicationContext;

	@Override
	public <T extends Plugin> Collection<T> getPlugins(@NotNull final Class<T> pluginType) {

		Assert.notNull(pluginType, "The plugin type must not be null.");

		final Collection<T> plugins = new ArrayList<T>();
		plugins.addAll(getPluginsAsMap(pluginType).values());

		return plugins;
	}

	/**
	 * @see ApplicationContext#getBeansOfType(Class)
	 */
	@Override
	public <T extends Plugin> Map<String, T> getPluginsAsMap(@NotNull final Class<T> pluginType) {
		return applicationContext.getBeansOfType(pluginType);
	}

}
