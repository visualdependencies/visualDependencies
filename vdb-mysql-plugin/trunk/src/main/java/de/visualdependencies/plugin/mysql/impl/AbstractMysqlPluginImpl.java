package de.visualdependencies.plugin.mysql.impl;

import de.visualdependencies.plugin.Plugin;
import de.visualdependencies.plugin.impl.AbstractPluginImpl;
import de.visualdependencies.plugin.mysql.MysqlPlugin;

/**
 * Abstract base implementation of a {@link MysqlPlugin}.
 * 
 * @author Jan Philipp
 */
public abstract class AbstractMysqlPluginImpl extends AbstractPluginImpl implements MysqlPlugin {

	public boolean isCompatible(Plugin otherPlugin) {
		if (otherPlugin instanceof MysqlPlugin) { return true; }
		return super.isCompatible(otherPlugin);
	}

}
