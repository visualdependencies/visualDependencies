package de.visualdependencies.plugin.impl;

import org.apache.log4j.Logger;

import de.visualdependencies.plugin.Plugin;

public abstract class AbstractPluginImpl implements Plugin {

	protected final Logger logger = Logger.getLogger(getClass());

	/**
	 * Returns false unless anything else was implemented.
	 */
	@Override
	public boolean isCompatible(final Plugin otherPlugin) {
		return false;
	}

}
