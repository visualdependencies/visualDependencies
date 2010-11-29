package de.visualdependencies.plugin.oracle.impl;

import org.springframework.stereotype.Component;

import de.visualdependencies.plugin.MetadataWorker;
import de.visualdependencies.plugin.Plugin;
import de.visualdependencies.plugin.helper.MetadataWorkerParameters;
import de.visualdependencies.plugin.helper.MetadataWorkerResult;

@Component("Oracle Metadata Worker")
public class OracleMetadataWorkerImpl extends AbstractOraclePluginImpl implements MetadataWorker {

	@Override
	public boolean isCompatible(Plugin otherPlugin) {
		// Accept the common connection provider plugin.
		if (otherPlugin instanceof de.visualdependencies.plugin.common.impl.CommonConnectionProviderImpl) { return true; }
		return super.isCompatible(otherPlugin);
	}

	@Override
	public MetadataWorkerResult loadModel(MetadataWorkerParameters parameters) {

		// Create the result object. Both parameters and result live in this call.
		MetadataWorkerResult result = MetadataWorkerResult.create();
		result.markStart();

		// DEMO 1s.
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {}

		result.markEnd();

		return result;
	}

}
