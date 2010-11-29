package de.visualdependencies.data.entity;

import java.util.ArrayList;
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

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "tables")
public class SchemaTable extends AbstractEntity<Long> {

	private static final long serialVersionUID = 358386476013342777L;

	@Id
	@GeneratedValue
	private long id;

	@ElementCollection
	@JoinTable(name = "tables_data", joinColumns = @JoinColumn(name = "tableId"))
	// @MapKeyEnumerated(EnumType.STRING)
	@Column(name = "value")
	private Map<String, String> data;

	@OneToMany(fetch = FetchType.EAGER)
	@Cascade(CascadeType.ALL)
	private List<SchemaColumn> columns;

	private String name;

	public List<SchemaColumn> getColumns() {
		if (columns == null) {
			setColumns(new ArrayList<SchemaColumn>());
		}
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
