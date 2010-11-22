package de.visualdependencies.data.dao.impl;

import org.springframework.stereotype.Repository;

import de.visualdependencies.data.dao.SchemaConnectionDao;
import de.visualdependencies.data.entity.SchemaConnection;

@Repository
public class SchemaConnectionDaoImpl extends AbstractGeneralDaoImpl<SchemaConnection> implements SchemaConnectionDao {}
