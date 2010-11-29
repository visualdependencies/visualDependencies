package de.visualdependencies.plugin.oracle.impl;

import de.visualdependencies.plugin.Plugin;
import de.visualdependencies.plugin.impl.AbstractPluginImpl;
import de.visualdependencies.plugin.oracle.OraclePlugin;

/**
 * Abstract base implementation of an {@link OraclePlugin}.
 * 
 * @author Jan Philipp
 */
public abstract class AbstractOraclePluginImpl extends AbstractPluginImpl implements OraclePlugin {

	public boolean isCompatible(Plugin otherPlugin) {
		if (otherPlugin instanceof OraclePlugin) { return true; }
		return super.isCompatible(otherPlugin);
	}

}
