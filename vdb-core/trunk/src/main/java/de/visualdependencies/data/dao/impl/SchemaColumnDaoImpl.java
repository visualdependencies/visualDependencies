package de.visualdependencies.data.dao.impl;

import org.springframework.stereotype.Repository;

import de.visualdependencies.data.dao.SchemaColumnDao;
import de.visualdependencies.data.entity.SchemaColumn;

@Repository
public class SchemaColumnDaoImpl extends AbstractGeneralDaoImpl<SchemaColumn> implements SchemaColumnDao {}
