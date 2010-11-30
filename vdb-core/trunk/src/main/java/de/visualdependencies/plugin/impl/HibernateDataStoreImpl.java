package de.visualdependencies.plugin.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import de.visualdependencies.data.dao.SchemaColumnDao;
import de.visualdependencies.data.dao.SchemaConnectionDao;
import de.visualdependencies.data.dao.SchemaDao;
import de.visualdependencies.data.dao.SchemaFunctionDao;
import de.visualdependencies.data.dao.SchemaProcedureDao;
import de.visualdependencies.data.dao.SchemaTableDao;
import de.visualdependencies.data.dao.SchemaTriggerDao;
import de.visualdependencies.data.dao.SchemaViewDao;
import de.visualdependencies.data.dao.TableTriggerDependencyDao;
import de.visualdependencies.data.entity.Schema;
import de.visualdependencies.data.entity.SchemaColumn;
import de.visualdependencies.data.entity.SchemaConnection;
import de.visualdependencies.data.entity.SchemaFunction;
import de.visualdependencies.data.entity.SchemaProcedure;
import de.visualdependencies.data.entity.SchemaTable;
import de.visualdependencies.data.entity.SchemaTrigger;
import de.visualdependencies.data.entity.SchemaView;
import de.visualdependencies.data.entity.TableTriggerDependency;
import de.visualdependencies.plugin.DataStore;
import de.visualdependencies.plugin.Plugin;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class HibernateDataStoreImpl implements DataStore {

	@Autowired
	private SchemaDao schemaDao;

	@Autowired
	private SchemaColumnDao columnDao;

	@Autowired
	private SchemaConnectionDao connectionDao;

	@Autowired
	private SchemaTableDao tableDao;

	@Autowired
	private TableTriggerDependencyDao tableTriggerDependencyDao;

	@Autowired
	private SchemaViewDao viewDao;

	@Autowired
	private SchemaTriggerDao triggerDao;

	@Autowired
	private SchemaProcedureDao procedureDao;

	@Autowired
	private SchemaFunctionDao functionDao;

	@Override
	public Schema createSchema() {
		return schemaDao.create();
	}

	@Override
	public SchemaColumn createSchemaColumn() {
		return columnDao.create();
	}

	@Override
	public SchemaConnection createSchemaConnection() {
		return connectionDao.create();
	}

	@Override
	public SchemaFunction createSchemaFunction() {
		return functionDao.create();
	}

	@Override
	public SchemaProcedure createSchemaProcedure() {
		return procedureDao.create();
	}

	@Override
	public SchemaTable createSchemaTable() {
		return tableDao.create();
	}

	@Override
	public SchemaTrigger createSchemaTrigger() {
		return triggerDao.create();
	}

	@Override
	public SchemaView createSchemaView() {
		return viewDao.create();
	}

	@Override
	public TableTriggerDependency createTableTriggerDependency() {
		return tableTriggerDependencyDao.create();
	}

	@Override
	public List<Schema> getAllSchemas() {
		return schemaDao.list();
	}

	@PostConstruct
	protected void initialize() {}

	@Override
	public boolean isCompatible(final Plugin otherPlugin) {
		return false;
	}

	@Override
	public boolean saveSchema(final Schema schema) {
		schemaDao.save(schema);
		return true;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public boolean saveSchemaConnection(final SchemaConnection schemaConnection) {
		connectionDao.save(schemaConnection);
		return true;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public boolean saveSchemaTable(final SchemaTable schemaTable) {
		tableDao.save(schemaTable);
		return true;
	}

	@Override
	public void shutdown() {}

}
