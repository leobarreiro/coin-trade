package org.javaleo.cointrade.server.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.javaleo.cointrade.server.enums.Symbol;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "currency", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "symbol" }, name = "uk_currency_symbol") })
@SequenceGenerator(name = "seqCurrency", sequenceName = "currency_seq", allocationSize = 1, initialValue = 1)
public class Currency {

	@Id
	@Column(name = "currency_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqCurrency")
	@JsonIgnore
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(name = "symbol", length = 30)
	private Symbol name;

	@Column(name = "description", nullable = true, length = 60)
	@JsonIgnore
	private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Symbol getName() {
		return name;
	}

	public void setSymbol(Symbol symbol) {
		this.name = symbol;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Currency other = (Currency) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name != other.name)
			return false;
		return true;
	}

}
