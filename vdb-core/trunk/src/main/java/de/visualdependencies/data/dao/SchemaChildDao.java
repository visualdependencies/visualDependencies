package de.visualdependencies.data.dao;

import java.io.Serializable;
import java.util.List;

import de.visualdependencies.data.entity.AbstractEntity;
import de.visualdependencies.data.entity.Schema;

public interface SchemaChildDao<E extends AbstractEntity<? extends Serializable>> extends GeneralDao<E> {

	List<E> list(Schema schema);

}
