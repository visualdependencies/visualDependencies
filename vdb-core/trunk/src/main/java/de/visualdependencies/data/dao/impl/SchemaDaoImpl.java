package de.visualdependencies.data.dao.impl;

import org.springframework.stereotype.Repository;

import de.visualdependencies.data.dao.SchemaDao;
import de.visualdependencies.data.entity.Schema;

@Repository
public class SchemaDaoImpl extends AbstractGeneralDaoImpl<Schema> implements SchemaDao {}
