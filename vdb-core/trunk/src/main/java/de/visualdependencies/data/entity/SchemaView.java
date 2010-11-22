package de.visualdependencies.data.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "views")
public class SchemaView extends AbstractEntity<Long> {

	private static final long serialVersionUID = 1055880855284285381L;

	@Id
	@GeneratedValue
	private long id;

	@Column
	private String name;

	@OneToMany(fetch = FetchType.EAGER)
	private List<SchemaColumn> columns;

	@ElementCollection
	@JoinTable(name = "views_data", joinColumns = @JoinColumn(name = "viewId"))
	// @MapKeyEnumerated(EnumType.STRING)
	@Column(name = "value")
	private Map<String, String> data;

	public List<SchemaColumn> getColumns() {
		return columns;
	}

	public Map<String, String> getData() {
		if (data == null) {
			setData(new HashMap<String, String>());
		}
		return data;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setColumns(final List<SchemaColumn> columns) {
		this.columns = columns;
	}

	public void setData(final Map<String, String> data) {
		this.data = data;
	}

	public void setId(final long id) {
		this.id = id;
	}

	public void setName(final String name) {
		this.name = name;
	}

}
