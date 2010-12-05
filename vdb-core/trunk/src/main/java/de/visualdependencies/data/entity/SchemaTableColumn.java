package de.visualdependencies.data.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("table")
public class SchemaTableColumn extends SchemaColumn {

	private static final long serialVersionUID = -21971115593825263L;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn
	private SchemaTable table;

	public SchemaTable getTable() {
		return table;
	}

	public void setTable(final SchemaTable table) {
		this.table = table;
	}

}
