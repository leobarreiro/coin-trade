package org.javaleo.cointrade.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "market")
public class Market implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "market_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "market_name")
	private String name;

	@ManyToOne
	@JoinColumn(name = "exchange_id", referencedColumnName = "exchange_id")
	private Exchange exchange;

	@ManyToOne
	@JoinColumn(name = "reference_coin_id", referencedColumnName = "currency_id")
	private Currency referenceCoin;

	@ManyToOne
	@JoinColumn(name = "change_coin_id", referencedColumnName = "currency_id")
	private Currency changeCoin;

	@Column(name = "active")
	private Boolean active;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Exchange getExchange() {
		return exchange;
	}

	public void setExchange(Exchange exchange) {
		this.exchange = exchange;
	}

	public Currency getReferenceCoin() {
		return referenceCoin;
	}

	public void setReferenceCoin(Currency referenceCoin) {
		this.referenceCoin = referenceCoin;
	}

	public Currency getChangeCoin() {
		return changeCoin;
	}

	public void setChangeCoin(Currency changeCoin) {
		this.changeCoin = changeCoin;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((active == null) ? 0 : active.hashCode());
		result = prime * result + ((changeCoin == null) ? 0 : changeCoin.hashCode());
		result = prime * result + ((exchange == null) ? 0 : exchange.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((referenceCoin == null) ? 0 : referenceCoin.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Market other = (Market) obj;
		if (active == null) {
			if (other.active != null) return false;
		} else if (!active.equals(other.active)) return false;
		if (changeCoin == null) {
			if (other.changeCoin != null) return false;
		} else if (!changeCoin.equals(other.changeCoin)) return false;
		if (exchange == null) {
			if (other.exchange != null) return false;
		} else if (!exchange.equals(other.exchange)) return false;
		if (id == null) {
			if (other.id != null) return false;
		} else if (!id.equals(other.id)) return false;
		if (name == null) {
			if (other.name != null) return false;
		} else if (!name.equals(other.name)) return false;
		if (referenceCoin == null) {
			if (other.referenceCoin != null) return false;
		} else if (!referenceCoin.equals(other.referenceCoin)) return false;
		return true;
	}

}
