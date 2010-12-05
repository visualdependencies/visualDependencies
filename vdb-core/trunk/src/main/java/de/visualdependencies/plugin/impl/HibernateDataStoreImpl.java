package de.visualdependencies.plugin.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import de.visualdependencies.data.dao.SchemaConnectionDao;
import de.visualdependencies.data.dao.SchemaDao;
import de.visualdependencies.data.dao.SchemaFunctionDao;
import de.visualdependencies.data.dao.SchemaProcedureDao;
import de.visualdependencies.data.dao.SchemaTableColumnDao;
import de.visualdependencies.data.dao.SchemaTableDao;
import de.visualdependencies.data.dao.SchemaTriggerDao;
import de.visualdependencies.data.dao.SchemaViewColumnDao;
import de.visualdependencies.data.dao.SchemaViewDao;
import de.visualdependencies.data.dao.TableTriggerDependencyDao;
import de.visualdependencies.data.entity.Schema;
import de.visualdependencies.data.entity.SchemaConnection;
import de.visualdependencies.data.entity.SchemaFunction;
import de.visualdependencies.data.entity.SchemaProcedure;
import de.visualdependencies.data.entity.SchemaTable;
import de.visualdependencies.data.entity.SchemaTableColumn;
import de.visualdependencies.data.entity.SchemaTrigger;
import de.visualdependencies.data.entity.SchemaView;
import de.visualdependencies.data.entity.SchemaViewColumn;
import de.visualdependencies.data.entity.TableTriggerDependency;
import de.visualdependencies.plugin.DataStore;
import de.visualdependencies.plugin.Plugin;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class HibernateDataStoreImpl implements DataStore {

	private static final Logger LOGGER = Logger.getLogger(HibernateDataStoreImpl.class);

	@Autowired
	private SchemaDao schemaDao;

	@Autowired
	private SchemaTableColumnDao tableColumnDao;

	@Autowired
	private SchemaViewColumnDao viewColumnDao;

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
	public SchemaConnection createSchemaConnection() {
		return connectionDao.create();
	}

	@Override
	public SchemaFunction createSchemaFunction(final Schema schema) {
		final SchemaFunction entity = functionDao.create();
		entity.setSchema(schema);
		return entity;
	}

	@Override
	public SchemaProcedure createSchemaProcedure(final Schema schema) {
		final SchemaProcedure entity = procedureDao.create();
		entity.setSchema(schema);
		return entity;
	}

	@Override
	public SchemaTable createSchemaTable(final Schema schema) {
		final SchemaTable entity = tableDao.create();
		entity.setSchema(schema);
		return entity;
	}

	@Override
	public SchemaTableColumn createSchemaTableColumn(final SchemaTable table) {
		final SchemaTableColumn entity = tableColumnDao.create();
		entity.setTable(table);
		return entity;
	}

	@Override
	public SchemaTrigger createSchemaTrigger(final Schema schema) {
		final SchemaTrigger entity = triggerDao.create();
		entity.setSchema(schema);
		return entity;
	}

	@Override
	public SchemaView createSchemaView(final Schema schema) {
		final SchemaView entity = viewDao.create();
		entity.setSchema(schema);
		return entity;
	}

	@Override
	public SchemaViewColumn createSchemaViewColumn(final SchemaView view) {
		final SchemaViewColumn entity = viewColumnDao.create();
		entity.setView(view);
		return entity;
	}

	@Override
	public TableTriggerDependency createTableTriggerDependency(final Schema schema) {
		final TableTriggerDependency entity = tableTriggerDependencyDao.create();
		entity.setSchema(schema);
		return entity;
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
	public List<SchemaTableColumn> loadColumns(final SchemaTable table) {
		return tableColumnDao.list(table);
	}

	@Override
	public List<SchemaViewColumn> loadColumns(final SchemaView view) {
		return viewColumnDao.list(view);
	}

	@Override
	public SchemaConnection loadConnection(final Schema schema) {
		LOGGER.info("Loading connection...");
		return connectionDao.getBy(schema);
	}

	@Override
	public List<SchemaFunction> loadFunctions(final Schema schema) {
		LOGGER.info("Loading functions...");
		return functionDao.list(schema);
	}

	@Override
	public List<SchemaProcedure> loadProcedures(final Schema schema) {
		LOGGER.info("Loading procedures...");
		return procedureDao.list(schema);
	}

	@Override
	public List<SchemaTable> loadTables(final Schema schema) {
		LOGGER.info("Loading tables...");
		return tableDao.list(schema);
	}

	@Override
	public List<TableTriggerDependency> loadTableTriggerDependencies(final Schema schema) {
		LOGGER.info("Loading table trigger dependencies...");
		return tableTriggerDependencyDao.list(schema);
	}

	@Override
	public List<SchemaTrigger> loadTriggers(final Schema schema) {
		LOGGER.info("Loading triggers...");
		return triggerDao.list(schema);
	}

	@Override
	public List<SchemaView> loadViews(final Schema schema) {
		LOGGER.info("Loading views...");
		return viewDao.list(schema);
	}

	@Override
	public boolean save(final Schema schema) {
		LOGGER.info("Saving schema...");
		return schemaDao.save(schema);
	}

	@Override
	public boolean save(final SchemaConnection schemaConnection) {
		LOGGER.info("Saving connection...");
		return connectionDao.save(schemaConnection);
	}

	@Override
	public boolean save(final SchemaTable table) {
		LOGGER.info("Saving table...");
		return tableDao.save(table);
	}

	@Override
	public boolean save(final SchemaTableColumn column) {
		LOGGER.info("Saving table column...");
		return tableColumnDao.save(column);
	}

	@Override
	public void save(final SchemaTrigger trigger) {
		LOGGER.info("Saving trigger...");
		triggerDao.save(trigger);
	}

	@Override
	public boolean save(final SchemaView view) {
		LOGGER.info("Saving view...");
		return viewDao.save(view);
	}

	@Override
	public boolean save(final SchemaViewColumn column) {
		LOGGER.info("Saving view column...");
		return viewColumnDao.save(column);
	}

	@Override
	public boolean save(final TableTriggerDependency dependency) {
		LOGGER.info("Saving table trigger dependency...");
		return tableTriggerDependencyDao.save(dependency);
	}

	@Override
	public void shutdown() {}

}
