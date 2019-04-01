package org.javaleo.cointrade.server.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.javaleo.cointrade.server.enums.CandleInterval;

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
@Table(name = "candle")
@SequenceGenerator(name = "seqCandle", sequenceName = "candle_seq", allocationSize = 1, initialValue = 1)
public class Candle implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqCandle")
	@Column(name = "candle_id")
	@JsonIgnore
	private Long id;

	@ManyToOne
	@JoinColumn(name = "exchange_id", referencedColumnName = "exchange_id")
	@JsonIgnore
	private Exchange exchange;

	@ManyToOne
	@JoinColumn(name = "market_id", referencedColumnName = "market_id")
	@JsonIgnore
	private Market market;

	@Column(name = "opening")
	private Double opening;

	@Column(name = "closure")
	private Double closure;

	@Column(name = "lowest")
	private Double lowest;

	@Column(name = "highest")
	private Double highest;

	@Column(name = "currency_amount")
	private Double currencyAmount;

	@Column(name = "mkcap_amount")
	private Double marketCapAmount;

	@Column(name = "dt_collected")
	private Long collectedTime;

	@Column(name = "cd_year", length = 4)
	private Integer year;

	@Column(name = "cd_month", length = 2)
	private Integer month;

	@Column(name = "cd_day", length = 2)
	private Integer day;

	@Column(name = "cd_hour", length = 2)
	private Integer hour;

	@Column(name = "cd_minute", length = 2)
	private Integer minute;

	@Enumerated(EnumType.STRING)
	@Column(name = "cd_interval", length = 20)
	@JsonIgnore
	private CandleInterval interval;

}
