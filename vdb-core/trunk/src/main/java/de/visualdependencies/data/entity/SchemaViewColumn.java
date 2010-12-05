package de.visualdependencies.data.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("view")
public class SchemaViewColumn extends SchemaColumn {

	private static final long serialVersionUID = -21971115593825263L;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn
	private SchemaView view;

	public SchemaView getView() {
		return view;
	}

	public void setView(final SchemaView view) {
		this.view = view;
	}
}
