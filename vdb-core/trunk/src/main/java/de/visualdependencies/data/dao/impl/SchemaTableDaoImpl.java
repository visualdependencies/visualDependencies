package de.visualdependencies.data.dao.impl;

import org.springframework.stereotype.Repository;

import de.visualdependencies.data.dao.SchemaTableDao;
import de.visualdependencies.data.entity.SchemaTable;

@Repository
public class SchemaTableDaoImpl extends AbstractGeneralDaoImpl<SchemaTable> implements SchemaTableDao {}
