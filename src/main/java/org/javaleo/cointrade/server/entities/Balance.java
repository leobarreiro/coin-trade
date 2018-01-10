package org.javaleo.cointrade.server.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "balance")
public class Balance implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "balance_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "exchange_id", referencedColumnName = "exchange_id")
	private Exchange exchange;

	@ManyToOne
	@JoinColumn(name = "currency_id", referencedColumnName = "currency_id")
	private Currency currency;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_time")
	private Date dTime;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((currency == null) ? 0 : currency.hashCode());
		result = prime * result + ((dTime == null) ? 0 : dTime.hashCode());
		result = prime * result + ((exchange == null) ? 0 : exchange.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Balance other = (Balance) obj;
		if (currency == null) {
			if (other.currency != null) return false;
		} else if (!currency.equals(other.currency)) return false;
		if (dTime == null) {
			if (other.dTime != null) return false;
		} else if (!dTime.equals(other.dTime)) return false;
		if (exchange == null) {
			if (other.exchange != null) return false;
		} else if (!exchange.equals(other.exchange)) return false;
		if (id == null) {
			if (other.id != null) return false;
		} else if (!id.equals(other.id)) return false;
		return true;
	}

}
