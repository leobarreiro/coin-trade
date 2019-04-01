package org.javaleo.cointrade.server.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@EqualsAndHashCode
@Table(name = "market")
@SequenceGenerator(name = "seqMarket", sequenceName = "market_seq", allocationSize = 1, initialValue = 1)
public class Market implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "market_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqMarket")
	@JsonIgnore
	private Long id;

	@Column(name = "market_name")
	private String name;

	@ManyToOne
	@JoinColumn(name = "exchange_id", referencedColumnName = "exchange_id")
	@JsonIgnore
	private Exchange exchange;

	@ManyToOne
	@JoinColumn(name = "reference_coin_id", referencedColumnName = "currency_id")
	private Currency referenceCoin;

	@ManyToOne
	@JoinColumn(name = "change_coin_id", referencedColumnName = "currency_id")
	private Currency changeCoin;

	@Column(name = "active")
	@JsonIgnore
	private Boolean active;

	@Column(name = "trace")
	@JsonIgnore
	private Boolean trace;

}
