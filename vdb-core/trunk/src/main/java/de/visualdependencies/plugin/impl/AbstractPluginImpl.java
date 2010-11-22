package de.visualdependencies.plugin.impl;

import de.visualdependencies.plugin.Plugin;

public abstract class AbstractPluginImpl implements Plugin {

	/**
	 * Returns false unless anything else was implemented.
	 */
	@Override
	public boolean isCompatible(Plugin otherPlugin) {
		return false;
	}

}
