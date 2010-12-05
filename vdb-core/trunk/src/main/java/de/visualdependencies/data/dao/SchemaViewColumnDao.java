package de.visualdependencies.data.dao;

import java.util.List;

import de.visualdependencies.data.entity.SchemaView;
import de.visualdependencies.data.entity.SchemaViewColumn;

public interface SchemaViewColumnDao extends GeneralDao<SchemaViewColumn> {

	List<SchemaViewColumn> list(SchemaView view);

}
