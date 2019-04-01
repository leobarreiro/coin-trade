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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
@Table(name = "balance")
@SequenceGenerator(name = "seqBalance", sequenceName = "balance_seq", allocationSize = 1, initialValue = 1)
public class Balance implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "balance_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqBalance")
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

}
