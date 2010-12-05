package de.visualdependencies.data.dao;

import java.util.List;

import de.visualdependencies.data.entity.SchemaTable;
import de.visualdependencies.data.entity.SchemaTableColumn;

public interface SchemaTableColumnDao extends GeneralDao<SchemaTableColumn> {

	List<SchemaTableColumn> list(SchemaTable table);

}
